package Principal;

import Conexion.Conexion;
import Modelo.Colaborador;
import Modelo.Cuenta;

import Modelo.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppEngine {

    private static List<Producto> productos = new ArrayList<Producto>();
    private static List<Colaborador> colaboradores = new ArrayList<Colaborador>();

    public static List<Producto> getProductos() throws SQLException {
        productos.clear();

        String SQL = "SELECT * FROM Producto";
        ResultSet rs = Conexion.GetStatement(SQL);

        while (rs.next()) {
            Producto producto = new Producto(rs.getInt("ID_PRODUCTO"),
                    rs.getDouble("PRECIO"),
                    rs.getString("NOMBRE"),
                    rs.getString("DETALLE"));
            productos.add(producto);
        }
        return productos;
    }

    public static List<Colaborador> getColaboradores() throws SQLException {
        colaboradores.clear();

        String SQL = "SELECT EntidadBase.ID, DNI, NOMBRE, APELLIDOMATERNO, APELLIDOPATERNO, SEXO, CARGO, DES_TURNO, CORREO, CONTRASEÑA "
                + "FROM EntidadBase "
                + "INNER JOIN Colaborador "
                + "ON EntidadBase.ID = Colaborador.ID "
                + "INNER JOIN Cuenta "
                + "ON EntidadBase.ID = Cuenta.ID";
        ResultSet rs = Conexion.GetStatement(SQL);

        while (rs.next()) {
            Colaborador colaborador = new Colaborador(
                    rs.getInt("ID"),
                    rs.getInt("DNI"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDOPATERNO"),
                    rs.getString("APELLIDOMATERNO"),
                    rs.getString("SEXO"));
            colaborador.setCargo(rs.getString("CARGO"));
            colaborador.setTurno(rs.getString("DES_TURNO"));
            colaborador.setCuenta(new Cuenta(rs.getString("CORREO"), rs.getString("CONTRASEÑA")));

            colaboradores.add(colaborador);
        }
        return colaboradores;
    }

    public static void iniciarSesion(int ID) throws SQLException {
        String SQL = "UPDATE Cuenta SET CONECTADO = 1 WHERE Cuenta.ID = " + ID;
        Conexion.SetStatement(SQL);
    }
}
