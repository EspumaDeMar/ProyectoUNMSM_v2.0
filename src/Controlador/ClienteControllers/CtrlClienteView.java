package Controlador.ClienteControllers;

import Modelo.Cliente;

import Vista.ClienteViews.FrmClienteView;
import Vista.ClienteViews.FrmComprar;

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
            vista.dispose();
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
