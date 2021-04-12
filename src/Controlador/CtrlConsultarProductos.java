package Controlador;

import Modelo.Colaborador;
import Vista.FrmColaboradorView;

public class CtrlConsultarProductos {

    FrmColaboradorView vista;
    Colaborador colaborador;

    public CtrlConsultarProductos(FrmColaboradorView vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        

    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
