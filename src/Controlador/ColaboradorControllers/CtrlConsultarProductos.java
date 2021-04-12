package Controlador.ColaboradorControllers;

import Modelo.Colaborador;
import Vista.ColaboradorViews.FrmConsultarProductos;

public class CtrlConsultarProductos {

    FrmConsultarProductos vista;
    Colaborador colaborador;

    public CtrlConsultarProductos(FrmConsultarProductos vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        

    }

    public void inicializar() {
        this.vista.show();        
    }
}
