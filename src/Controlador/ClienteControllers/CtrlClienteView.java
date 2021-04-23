package Controlador.ClienteControllers;

import Modelo.Cliente;
import Modelo.Interface.IControlador;
import Utilitario.ImagenFondo;

import Vista.ClienteViews.FrmClienteView;
import Vista.ClienteViews.FrmComprar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlClienteView implements IControlador {

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
            try {
                cComprar.inicializar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
            }
        });

    }

    private void redimensionar(Component componente) {
        vista.panelEscritorio.add(componente);
        componente.setSize(vista.panelEscritorio.getWidth(), vista.panelEscritorio.getHeight());
    }

    public void inicializar() throws Exception {
        this.vista.panelEscritorio.setBorder(new ImagenFondo());
        this.vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
