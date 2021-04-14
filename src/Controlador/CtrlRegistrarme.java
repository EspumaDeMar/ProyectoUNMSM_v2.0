package Controlador;

import Conexion.Conexion;
import Conexion.DBParametro;
import Vista.FrmLogin;
import Vista.FrmRegistrarme;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class CtrlRegistrarme {
    
    FrmRegistrarme vista;
    
    public CtrlRegistrarme(FrmRegistrarme vista) {
        this.vista = vista;
        
        Action accion = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = vista.txtNombre.getText().toUpperCase();
                    String apellidoMaterno = vista.txtApellidoMaterno.getText().toUpperCase();
                    String apellidoPaterno = vista.txtApellidoPaterno.getText().toUpperCase();
                    if (nombre.isBlank()
                            || apellidoMaterno.isBlank()
                            || apellidoPaterno.isBlank()
                            || (!vista.rdoFemenino.isSelected() && !vista.rdoMasculino.isSelected())) {
                        JOptionPane.showMessageDialog(vista, "¡Complete todos los campos para continuar con el registro!", "Registro", 0);
                        
                        return;
                    }
                    
                    String correo = "";
                    if (validarCorreo(vista.txtCorreo.getText())) {
                        correo = vista.txtCorreo.getText();
                    }
                    
                    String contraseña = "";
                    if (validarContraseña(vista.txtContraseña.getText(), vista.txtRepetirContraseña.getText())) {
                        contraseña = vista.txtContraseña.getText();
                    }
                    
                    int DNI = Integer.parseInt(vista.txtDNI.getText());
                    String sexo = "";
                    if (vista.rdoMasculino.isSelected()) {
                        sexo = "MASCULINO";
                    } else {
                        sexo = "FEMENINO";
                    }
                    
                    List<DBParametro> parametros = new ArrayList<DBParametro>();
                    parametros.add(new DBParametro("correo", correo));
                    parametros.add(new DBParametro("contraseña", contraseña));
                    parametros.add(new DBParametro("DNI", DNI));
                    parametros.add(new DBParametro("nombre", nombre));
                    parametros.add(new DBParametro("apellidoMaterno", apellidoMaterno));
                    parametros.add(new DBParametro("apellidoPaterno", apellidoPaterno));
                    parametros.add(new DBParametro("sexo", sexo));
                    
                    Conexion.setSP("SETNuevoCliente(?,?,?,?,?,?,?)", vista, parametros);
                    
                    JOptionPane.showMessageDialog(vista, "Bienvenid@ " + nombre + " " + apellidoPaterno + "¡Se ha registrado con éxito!");
                    
                    FrmLogin fLogin = new FrmLogin();
                    CtrlLogin cLogin = new CtrlLogin(fLogin);
                    cLogin.inicializar();
                    
                    vista.dispose();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "!Ingrese un número de DNI válido!", "Registro", 0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.toString(), "SQL", 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, ex.getMessage(), "Registro", 0);
                }
            }
        };
        
        vista.btnRegistrarme.addActionListener(accion);
        vista.txtNombre.addActionListener(accion);
        vista.txtApellidoMaterno.addActionListener(accion);
        vista.txtApellidoPaterno.addActionListener(accion);
        vista.txtContraseña.addActionListener(accion);
        vista.txtRepetirContraseña.addActionListener(accion);
        vista.txtDNI.addActionListener(accion);
        vista.txtCorreo.addActionListener(accion);
    }
    
    private boolean validarCorreo(String correo) throws Exception {
        if (!(correo.contains("@gmail.com") || correo.contains("@unmsm.edu.pe")) || correo.isBlank()) {
            vista.txtCorreo.setSelectionStart(0);
            vista.txtCorreo.setSelectionEnd(vista.txtCorreo.getText().length());
            throw new Exception("¡Ingresa un correo valido para continuar con el registro!");
            
        }
        return true;
    }
    
    private boolean validarContraseña(String contraseña, String repetirContraseña) throws Exception {
        if (!contraseña.equals(repetirContraseña) || contraseña.isBlank() || repetirContraseña.isBlank()) {
            vista.txtRepetirContraseña.setSelectionStart(0);
            vista.txtRepetirContraseña.setSelectionEnd(vista.txtRepetirContraseña.getText().length());
            throw new Exception("¡Las dos contraseñas no coinciden! Recuerda no usar espacios en blanco.");
            
        }
        return true;
    }
    
    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
