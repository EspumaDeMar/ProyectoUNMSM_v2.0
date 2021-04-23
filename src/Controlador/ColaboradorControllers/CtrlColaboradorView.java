package Controlador.ColaboradorControllers;

import Modelo.Colaborador;
import Modelo.Interface.IControlador;
import Utilitario.ImagenFondo;

import Vista.ColaboradorViews.FrmColaboradorView;
import Vista.ColaboradorViews.FrmMantenimientoColaboradores;
import Vista.ColaboradorViews.FrmMantenimientoProductos;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlColaboradorView implements IControlador {

    FrmColaboradorView vista;
    Colaborador colaborador;

    public CtrlColaboradorView(FrmColaboradorView vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;

        vista.itCerrarSesion.addActionListener((ActionEvent e) -> {
            vista.dispose();
        });

        vista.itMantenimientoProductos.addActionListener((ActionEvent e) -> {
            try {
                FrmMantenimientoProductos fmProductos = new FrmMantenimientoProductos();
                redimensionar(fmProductos);

                CtrlMantenimientoProductos mProductos = new CtrlMantenimientoProductos(fmProductos, colaborador);
                mProductos.inicializar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
            }
        });

        vista.itMantenimientoColaboradores.addActionListener((ActionEvent e) -> {
            try {
                FrmMantenimientoColaboradores fmColaboradores = new FrmMantenimientoColaboradores();
                redimensionar(fmColaboradores);

                CtrlMantenimientoColaboradores cColaboradores = new CtrlMantenimientoColaboradores(fmColaboradores, colaborador);
                cColaboradores.inicializar();
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
