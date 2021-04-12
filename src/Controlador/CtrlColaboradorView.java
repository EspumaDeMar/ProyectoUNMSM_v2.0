package Controlador;

import Modelo.Colaborador;

import Vista.FrmColaboradorView;
import Vista.FrmConsultarClientes;
import Vista.FrmConsultarProductos;

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
                
                vista.panelEscritorio.add(fcProductos);
                int ancho = vista.panelEscritorio.getWidth();
                int largo = vista.panelEscritorio.getHeight();
                fcProductos.setSize(ancho, largo);
                
                CtrlConsultarProductos cProductos = new CtrlConsultarProductos(fcProductos, colaborador);
                cProductos.inicializar();
            }
        });

        vista.itConsultaClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmConsultarClientes fcClientes = new FrmConsultarClientes();
                
                vista.panelEscritorio.add(fcClientes);
                int ancho = vista.panelEscritorio.getWidth();
                int largo = vista.panelEscritorio.getHeight();
                fcClientes.setSize(ancho, largo);
                
                CtrlConsultarClientes cClientes = new CtrlConsultarClientes(fcClientes, colaborador);
                cClientes.inicializar();
            }
        });

    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

}
