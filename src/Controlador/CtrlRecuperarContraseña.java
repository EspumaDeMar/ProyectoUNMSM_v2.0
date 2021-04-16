package Controlador;

import Conexion.DBParametro;
import static Utilitario.EmitirMensaje.EnviarCorreo;
import Vista.FrmLogin;
import Vista.FrmRecuperarContraseña;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class CtrlRecuperarContraseña {

    FrmRecuperarContraseña vista;

    public CtrlRecuperarContraseña(FrmRecuperarContraseña vista) {
        this.vista = vista;

        Action accion = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String nombre = vista.txtNombre.getText().toUpperCase();
                    if (nombre.isBlank()) {
                        JOptionPane.showMessageDialog(vista, "¡Complete todos los campos para continuar con el registro!", "Registro", 0);

                        return;
                    }

                    String correo = "";
                    if (validarCorreo(vista.txtCorreo.getText())) {
                        correo = vista.txtCorreo.getText();
                    }

                    int DNI = Integer.parseInt(vista.txtDNI.getText());

                    List<DBParametro> parametros = new ArrayList<DBParametro>();
                    parametros.add(new DBParametro("nombre", nombre));
                    parametros.add(new DBParametro("correo", correo));
                    parametros.add(new DBParametro("DNI", DNI));

                    ResultSet rs = Conexion.Conexion.getSP("GETRecuperarContraseña(?,?,?)", vista, parametros);
                    while (rs.next()) {
                        if (rs.getInt("RESULT") == 1) {
                            String contraseña = rs.getString("CONTRASEÑA");
                            
                            String mensaje = "<html><h1>&nbsp;¡Hola <b>" + nombre + "!</b></h1>"
                                           + "<p>&nbsp;Al parecer olvidaste que eras un lagart@, JAAAAA!<br>"
                                           + "&nbsp;y también tu contraseña...<br>"
                                           + "&nbsp;Aquí te la recuerdo mi king:</p>"
                                           + "<p align=\"center\"><h2>" + contraseña + "</h2></p>"
                                           + "&nbsp;No te olvides, CÓDIGO: <b>LAGARTO</b><br>"
                                           + "&nbsp;Saludos mi rey!</html>";

                            EnviarCorreo(correo, mensaje, vista);

                            JOptionPane.showMessageDialog(vista, "Hemos enviado un email a tu correo con tu contraseña. ¡Revísalo e inicia sesión!", "Recuperar contraseña", 1);

                            FrmLogin fLogin = new FrmLogin();
                            CtrlLogin cLogin = new CtrlLogin(fLogin);
                            fLogin.txtCorreo.setText(correo);
                            fLogin.txtContraseña.requestFocus();

                            cLogin.inicializar();

                            vista.dispose();
                        } else {
                            JOptionPane.showMessageDialog(vista, "No hemos encontrado registros con los datos que proporcionaste, ¡inténtalo nuevamente!", "Recuperar contraseña", 1);
                            vista.txtCorreo.setSelectionStart(0);
                            vista.txtCorreo.setSelectionEnd(vista.txtCorreo.getText().length() - 1);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "!Ingrese un número de DNI válido!", "Recuperar contraseña", 0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.toString(), "SQL", 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, ex.getMessage(), "Recuperar contraseña", 0);
                }
            }
        };

        vista.txtCorreo.addActionListener(accion);
        vista.txtDNI.addActionListener(accion);
        vista.txtNombre.addActionListener(accion);
        vista.btnRecuperarContraseña.addActionListener(accion);

        vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmLogin fLogin = new FrmLogin();
                CtrlLogin cLogin = new CtrlLogin(fLogin);
                fLogin.txtCorreo.requestFocus();

                cLogin.inicializar();

                vista.dispose();
            }
        });
    }

    private boolean validarCorreo(String correo) throws Exception {
        if (!(correo.contains("@gmail.com") || correo.contains("@unmsm.edu.pe")) || correo.isBlank()) {
            vista.txtCorreo.setSelectionStart(0);
            vista.txtCorreo.setSelectionEnd(vista.txtCorreo.getText().length() - 1);
            throw new Exception("¡Ingresa un correo valido para continuar con el proceso!");

        }
        return true;
    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
