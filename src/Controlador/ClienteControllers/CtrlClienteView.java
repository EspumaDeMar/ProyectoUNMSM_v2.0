package Controlador.ClienteControllers;

import Controlador.CtrlLogin;
import Modelo.Cliente;
import Vista.ClienteViews.FrmClienteView;
import Vista.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class CtrlClienteView {
    
    FrmClienteView vista;
    Cliente cliente;

    public CtrlClienteView(FrmClienteView vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;
        
        vista.itCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmLogin fLogin = new FrmLogin();
                CtrlLogin cLogin = new CtrlLogin(fLogin);
                fLogin.txtCorreo.setText(cliente.getCuenta().getCorreo());
                fLogin.txtContraseña.requestFocus();

                cLogin.inicializar();

                vista.dispose();
            }
        });        
        
    }
    
    public void inicializar(){
        this.vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);        
    }
}
