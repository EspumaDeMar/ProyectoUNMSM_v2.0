package Controlador.ColaboradorControllers;

import Modelo.Colaborador;
import Vista.ColaboradorViews.FrmConsultarClientes;

public class CtrlConsultarClientes {
    FrmConsultarClientes vista;
    Colaborador colaborador;

    public CtrlConsultarClientes(FrmConsultarClientes vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        
    }
    
    public void inicializar(){
        this.vista.show();        
    }    
}
