package Conexion;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection getConexion() {
        String url = "jdbc:sqlserver://PC-DIEGO:1433;"
                + "database=ProyectoUNMSM;"
                + "user=USERSQL;"
                + "password=soloesunjuego;";

        try {
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public static ResultSet GetStatement(String SQL, Component componente) {
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(componente, e.toString());
            return null;
        }
    }

    public static void SetStatement(String SQL, Component componente) {
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(componente, e.toString());
        }
    }
}
