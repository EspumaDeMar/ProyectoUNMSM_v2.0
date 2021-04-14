package Controlador;

import Controlador.ColaboradorControllers.CtrlColaboradorView;
import Conexion.Conexion;
import Conexion.DBParametro;

import Modelo.Colaborador;
import Modelo.Cuenta;

import Vista.ColaboradorViews.FrmColaboradorView;
import Vista.FrmLogin;
import Vista.FrmRegistrarme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

                String correo = vista.txtCorreo.getText();
                String contraseña = vista.txtContraseña.getText();

                if (correo.isBlank() || contraseña.isBlank() || !(correo.contains("@unmsm.edu.pe") || correo.contains("@gmail.com"))) {
                    JOptionPane.showMessageDialog(vista, "Ingresa un usuario y/o contraseña válido(s)", "Iniciar sesión", 0);
                    vista.txtContraseña.requestFocus();

                    return;
                }

                try {
                    List<DBParametro> parametros = new ArrayList<DBParametro>();
                    parametros.add(new DBParametro("correo", correo));
                    parametros.add(new DBParametro("contraseña", contraseña));

                    ResultSet rs = Conexion.getSP("GETLogin(?,?)", vista, parametros);

                    while (rs.next()) {
                        if (rs.getInt("RESULT") == 1) {
                            if (rs.getInt("TIPO") == 1) {
                                Colaborador colaborador = new Colaborador(
                                        rs.getInt("ID"),
                                        rs.getInt("DNI"),
                                        rs.getString("NOMBRE"),
                                        rs.getString("APELLIDOPATERNO"),
                                        rs.getString("APELLIDOMATERNO"),
                                        rs.getString("SEXO"));
                                colaborador.setCuenta(new Cuenta(correo, contraseña));
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
                            } else {

                            }
                        } else {
                            String mensaje = "Al parecer aún no estás registrado, ¡únete a nosotros registrándote! "
                                    + "o en caso hayas olvidado tu contraseña, ¡intenta reestableciéndola!";
                            JOptionPane.showMessageDialog(vista, mensaje, "Iniciar sesión", 1);
                            vista.btnRegistrarme.requestFocus();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.toString(), "SQL", 0);
                }
            }
        };

        this.vista.btnIniciarSesion.addActionListener(accion);
        this.vista.txtCorreo.addActionListener(accion);
        this.vista.txtContraseña.addActionListener(accion);

        this.vista.btnRegistrarme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                FrmRegistrarme fRegistrarme = new FrmRegistrarme();
                CtrlRegistrarme cRegistrarme = new CtrlRegistrarme(fRegistrarme);
                cRegistrarme.inicializar();
                
                vista.dispose();
            }
        });
        
        
    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
