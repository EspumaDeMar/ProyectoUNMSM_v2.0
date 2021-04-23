package Controlador.ColaboradorControllers;

import Modelo.Colaborador;

import Vista.ColaboradorViews.FrmColaboradorView;
import Vista.ColaboradorViews.FrmMantenimientoClientes;
import Vista.ColaboradorViews.FrmMantenimientoColaboradores;
import Vista.ColaboradorViews.FrmMantenimientoProductos;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class CtrlColaboradorView {

    FrmColaboradorView vista;
    Colaborador colaborador;

    public CtrlColaboradorView(FrmColaboradorView vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        vista.itCerrarSesion.addActionListener((ActionEvent e) -> {
            vista.dispose();
        });

        vista.itMantenimientoProductos.addActionListener((ActionEvent e) -> {
            FrmMantenimientoProductos fmProductos = new FrmMantenimientoProductos();
            redimensionar(fmProductos);
            
            CtrlMantenimientoProductos mProductos = new CtrlMantenimientoProductos(fmProductos, colaborador);
            mProductos.inicializar();
        });
        
        vista.itMantenimientoColaboradores.addActionListener((ActionEvent e) -> {
            FrmMantenimientoColaboradores fmColaboradores = new FrmMantenimientoColaboradores();
            redimensionar(fmColaboradores);
            
            CtrlMantenimientoColaboradores cColaboradores = new CtrlMantenimientoColaboradores(fmColaboradores, colaborador);
            cColaboradores.inicializar();
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
