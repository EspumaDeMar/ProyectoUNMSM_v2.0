package Controlador;

import Conexion.Conexion;

import Modelo.Colaborador;
import Modelo.Cuenta;
import Vista.FrmColaboradorView;

import Vista.FrmLogin;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class CtrlLogin {

    FrmLogin vista;

    public CtrlLogin(FrmLogin vista) {
        this.vista = vista;

        Action accion = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean hasResults = false;

                String correo = vista.txtCorreo.getText().trim();
                String contraseña = vista.txtContraseña.getText().trim();

                if (correo.isEmpty() || contraseña.isEmpty() || !(correo.contains("@unmsm.edu.pe") || correo.contains("@gmail.com"))) {
                    JOptionPane.showMessageDialog(vista, "Ingresa un usuario y/o contraseña válido(s)", "Iniciar sesión", 0);
                    vista.txtContraseña.requestFocus();

                    return;
                }

                try {
                    String SQL = "SELECT * FROM CUENTA "
                                + "WHERE CORREO = '" + correo + "' AND CONTRASEÑA = '" + contraseña + "'";
                    ResultSet rs = Conexion.GetStatement(SQL, vista);

                    while (rs.next()) {
                        Cuenta cuenta = new Cuenta(rs.getString("CORREO"), rs.getString("CONTRASEÑA"));
                        int ID = rs.getInt("ID");

                        SQL = "SELECT TIPO FROM ENTIDADBASE WHERE ID = " + ID;
                        rs = Conexion.GetStatement(SQL, vista);

                        while (rs.next()) {
                            if (rs.getInt("TIPO") == 1) {
                                SQL = "SELECT EntidadBase.ID, DNI, NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, SEXO, TIPO, CARGO, TURNO "
                                     +  "FROM EntidadBase INNER JOIN Colaborador ON Colaborador.ID = EntidadBase.ID "
                                     + "WHERE EntidadBase.ID = " + ID;
                                rs = Conexion.GetStatement(SQL, vista);

                                while (rs.next()) {
                                    Colaborador colaborador = new Colaborador(
                                            rs.getInt("ID"),
                                            rs.getInt("DNI"),
                                            rs.getString("NOMBRE"),
                                            rs.getString("APELLIDOPATERNO"),
                                            rs.getString("APELLIDOMATERNO"),
                                            rs.getString("SEXO"));
                                    colaborador.setCuenta(cuenta);
                                    colaborador.setCargo(rs.getString("CARGO"));
                                    if (rs.getInt("TURNO") == 1) {
                                        colaborador.setTurno("MAÑANA");
                                    } else {
                                        colaborador.setTurno(("NOCHE"));
                                    }
                                    
                                    FrmColaboradorView fColaboradorV = new FrmColaboradorView();
                                    CtrlColaboradorView cColaboradorV = new CtrlColaboradorView(fColaboradorV, colaborador);
                                    cColaboradorV.inicializar();
                                    
                                    vista.dispose();
                                    
                                    hasResults = true;
                                }
                            } else {
                                SQL = "SELECT EntidadBase.ID, DNI, NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, SEXO, TIPO, ID_COMPRA "
                                        + "FROM EntidadBase INNER JOIN Cliente ON EntidadBase.ID = Cliente.ID"
                                        + "WHERE EntidadBase.ID = " + ID;
                                rs = Conexion.GetStatement(SQL, vista);

                            }
                        }
                    }
                    
                    if(!hasResults){
                        String mensaje = "Al parecer aún no estás registrado, ¡únete a nosotros registrándote! "
                                + "o en caso hayas olvidado tu contraseña, ¡intenta reestableciéndola!";
                        JOptionPane.showMessageDialog(vista, mensaje, "Iniciar sesión", 1);
                        vista.btnRegistrarme.requestFocus();
                    }
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.toString(), "SQL", 0);
                }
            }
        };

        this.vista.btnIniciarSesion.addActionListener(accion);
        this.vista.txtCorreo.addActionListener(accion);
        this.vista.txtContraseña.addActionListener(accion);

    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
