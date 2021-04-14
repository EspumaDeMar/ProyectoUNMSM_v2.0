package Conexion;

import java.awt.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.swing.JOptionPane;

/**
 * <h1>Conexion</h1>
 * <p>
 * Es una clase que encapsula métodos utilizados para el establecimiento de la
 * comunicación entre la base de datos en SQL Server y la aplicación en Java.
 * <br>
 * En esta clase se establece la conexión con la base de datos a través de la
 * API JDBC que permite la ejecución de operaciones sobre bases de datos desde
 * el lenguaje de programación Java.
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Conexion {

    private static Connection con;

    /**
     *
     * Es un método de tipo estático que retorna un valor de tipo
     * {@code Connection}.
     *
     * @return {@code Connection}
     */
    private static Connection getConexion() {
        String url = "jdbc:sqlserver://PC-DIEGO:1433;"
                + "database=ProyectoUNMSM;"
                + "user=USERSQL;"
                + "password=soloesunjuego;";
/*
        String url = "jdbc:sqlserver://SISTEMAS14:1433;"
                + "database=ProyectoUNMSM;"
                + "user=unmsm;"
                + "password=sqlserver;";*/
        try {
            con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     *
     * Es un método de tipo estático que recibe un query para poder retornar un
     * conjunto de datos.
     *
     * @param SQL
     * @param componente
     * @return {@code ResultSet}
     */
    public static ResultSet GetStatement(String SQL, Component componente) {
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(componente, e.toString());
            return null;
        }
    }

    /**
     *
     * Es un método de tipo estático que recibe un query para poder ejecutar una acción
     * sin retorno de datos.
     *
     * @param SQL
     * @param componente
     */
    public static void SetStatement(String SQL, Component componente) {
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(componente, e.toString());
        }
    }

    /**
     * *
     *
     * Es un método de tipo estático que recibe un Stored Procedure y una lista 
     * de parámetros para poder retornar un conjunto de datos.
     * 
     * @param StoredProcedure
     * @param componente
     * @param parametros
     * @return {@code ResultSet}
     */
    public static ResultSet getSP(String StoredProcedure, Component componente, List<DBParametro> parametros) {
        try {
            con = Conexion.getConexion();
            CallableStatement cs = con.prepareCall("{call " + StoredProcedure + "}");
            for (DBParametro parametro : parametros) {
                cs.setObject(parametro.getNombre(), parametro.getValor());
            }
            cs.execute();
            ResultSet rs = cs.getResultSet();
            return rs;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(componente, e.toString());
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * *
     * Es un método de tipo estático que recibe un Stored Procedure y una lista 
     * de parámetros para poder ejecutar una acción sin retorno de datos.
     * @param StoredProcedure
     * @param componente
     * @param parametros
     */
    public static void setSP(String StoredProcedure, Component componente, List<DBParametro> parametros) {
        try {
            con = Conexion.getConexion();
            CallableStatement cs = con.prepareCall("{call " + StoredProcedure + "}");
            for (DBParametro parametro : parametros) {
                cs.setObject(parametro.getNombre(), parametro.getValor());
            }
            cs.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(componente, e.toString());
        }
    }
}
