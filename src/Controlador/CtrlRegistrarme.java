package Controlador;

import Vista.FrmRegistrarme;
import java.awt.event.ActionEvent;
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
                String nombre = vista.txtNombre.getText().toUpperCase().trim();
                String apellidoMaterno= vista.txtApellidoMaterno.getText().toUpperCase();
                String apellidoPaterno= vista.txtApellidoPaterno.getText().toUpperCase();
                String contraseña = vista.txtContraseña.getText();
                String repetirContraseña= vista.txtRepetirContraseña.getText();
                String DNI = vista.txtDNI.getText();
                String correo= vista.txtCorreo.getText();
                
                if( nombre.isEmpty() || 
                    apellidoMaterno.isEmpty() || 
                    apellidoPaterno.isEmpty() ||
                    contraseña.isEmpty() ||
                    repetirContraseña.isEmpty() ||
                    DNI.isEmpty() ||
                    correo.isEmpty() ||
                    (!vista.rdoFemenino.isSelected() && !vista.rdoMasculino.isSelected())){
                    JOptionPane.showMessageDialog(vista, "¡Complete todos los campos para continuar con el registro!", "Registro", 0);
                    
                    return;                    
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
    
    public void inicializar(){
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }    
}
