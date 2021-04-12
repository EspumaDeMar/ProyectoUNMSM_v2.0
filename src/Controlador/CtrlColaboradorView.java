package Controlador;

import Modelo.Colaborador;
import Vista.FrmColaboradorView;
import Vista.FrmConsultarProductos;
import Vista.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlColaboradorView {

    FrmColaboradorView vista;
    Colaborador colaborador;

    public CtrlColaboradorView(FrmColaboradorView vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        vista.itConsultaProductos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmConsultarProductos fcProductos = new FrmConsultarProductos();
                vista.panelEscritorio.add(fcProductos);
                fcProductos.show();
            }
        });
        
    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

}
