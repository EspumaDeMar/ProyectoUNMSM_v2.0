package Controlador.ClienteControllers;

import Controlador.CtrlLogin;

import Modelo.Cliente;

import Vista.ClienteViews.FrmClienteView;
import Vista.ClienteViews.FrmComprar;
import Vista.ClienteViews.FrmConsultarCompras;
import Vista.FrmLogin;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class CtrlClienteView {

    FrmClienteView vista;
    Cliente cliente;

    public CtrlClienteView(FrmClienteView vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;

        vista.itCerrarSesion.addActionListener((ActionEvent e) -> {
            FrmLogin fLogin = new FrmLogin();
            CtrlLogin cLogin = new CtrlLogin(fLogin);
            fLogin.txtCorreo.setText(cliente.getCuenta().getCorreo());
            fLogin.txtContraseña.requestFocus();
            
            cLogin.inicializar();
            
            vista.dispose();
        });

        vista.itConsultaHistorial.addActionListener((ActionEvent e) -> {
            FrmConsultarCompras fConsultarCompras = new FrmConsultarCompras();
            redimensionar(fConsultarCompras);
            
            CtrlConsultarCompras cConsultarCompras = new CtrlConsultarCompras(fConsultarCompras, cliente);
            cConsultarCompras.inicializar();
        });

        vista.itComprar.addActionListener((ActionEvent e) -> {
            FrmComprar fComprar = new FrmComprar();
            redimensionar(fComprar);
            
            CtrlComprar cComprar = new CtrlComprar(fComprar, cliente);
            cComprar.inicializar();
        });

    }

    private void redimensionar(Component componente) {
        vista.panelEscritorio.add(componente);
        componente.setSize(vista.panelEscritorio.getWidth(), vista.panelEscritorio.getHeight());
    }

    public void inicializar() {
        this.vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
