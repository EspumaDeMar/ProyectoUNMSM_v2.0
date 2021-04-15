package Principal;

import Conexion.Conexion;

import Modelo.Producto;
import java.awt.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AppEngine {

    public static List<Producto> productos = new ArrayList<Producto>();

    public static List<Producto> getProductos(Component componente) {
        try {
            String SQL = "SELECT * FROM Producto";
            ResultSet rs = Conexion.GetStatement(SQL, componente);

            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("ID"),
                                                 rs.getDouble("PRECIO"),
                                                 rs.getString("NOMBRE"));
                productos.add(producto);
            }
            return productos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(componente, e.toString());
            return null;
        }        
    }

}
