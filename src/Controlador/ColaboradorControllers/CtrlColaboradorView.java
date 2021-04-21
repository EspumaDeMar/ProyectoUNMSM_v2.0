package Controlador.ColaboradorControllers;

import Controlador.CtrlLogin;

import Modelo.Colaborador;

import Vista.ColaboradorViews.FrmColaboradorView;
import Vista.ColaboradorViews.FrmConsultarClientes;
import Vista.ColaboradorViews.FrmMantenimientoClientes;
import Vista.ColaboradorViews.FrmMantenimientoColaboradores;
import Vista.ColaboradorViews.FrmMantenimientoProductos;
import Vista.FrmLogin;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class CtrlColaboradorView {

    FrmColaboradorView vista;
    Colaborador colaborador;

    public CtrlColaboradorView(FrmColaboradorView vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        vista.itCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmLogin fLogin = new FrmLogin();
                CtrlLogin cLogin = new CtrlLogin(fLogin);
                fLogin.txtCorreo.setText(colaborador.getCuenta().getCorreo());
                fLogin.txtContraseña.requestFocus();

                cLogin.inicializar();

                vista.dispose();
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
        
        vista.itMantenimientoColaboradores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMantenimientoColaboradores fmColaboradores = new FrmMantenimientoColaboradores();
                redimensionar(fmColaboradores);

                CtrlMantenimientoColaboradores cColaboradores = new CtrlMantenimientoColaboradores(fmColaboradores, colaborador);
                cColaboradores.inicializar();
            }
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
