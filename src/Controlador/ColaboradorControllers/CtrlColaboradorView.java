package Controlador.ColaboradorControllers;

import Modelo.Colaborador;

import Vista.ColaboradorViews.FrmColaboradorView;
import Vista.ColaboradorViews.FrmConsultarClientes;
import Vista.ColaboradorViews.FrmConsultarProductos;
import Vista.ColaboradorViews.FrmMantenimientoClientes;
import Vista.ColaboradorViews.FrmMantenimientoProductos;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlColaboradorView {

    FrmColaboradorView vista;
    Colaborador colaborador;

    public CtrlColaboradorView(FrmColaboradorView vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;

        vista.itConsultaProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmConsultarProductos fcProductos = new FrmConsultarProductos();
                redimensionar(fcProductos);

                CtrlConsultarProductos cProductos = new CtrlConsultarProductos(fcProductos, colaborador);
                cProductos.inicializar();
            }
        });

        vista.itConsultaClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmConsultarClientes fcClientes = new FrmConsultarClientes();
                redimensionar(fcClientes);

                CtrlConsultarClientes cClientes = new CtrlConsultarClientes(fcClientes, colaborador);
                cClientes.inicializar();
            }
        });

        vista.itMantenimientoClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMantenimientoClientes fmClientes = new FrmMantenimientoClientes();
                redimensionar(fmClientes);
                
                CtrlMantenimientoClientes mClientes = new CtrlMantenimientoClientes(fmClientes, colaborador);
                mClientes.inicializar();                
            }
        });

        vista.itMantenimientoProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMantenimientoProductos fmProductos = new FrmMantenimientoProductos();
                redimensionar(fmProductos);
                
                CtrlMantenimientoProductos mProductos = new CtrlMantenimientoProductos(fmProductos, colaborador);
                mProductos.inicializar();
            }
        });
    }

    private void redimensionar(Component componente) {
        vista.panelEscritorio.add(componente);
        componente.setSize(vista.panelEscritorio.getWidth(), vista.panelEscritorio.getHeight());
    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

}
