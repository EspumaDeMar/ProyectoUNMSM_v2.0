package Controlador;

import Modelo.Colaborador;
import Vista.FrmConsultarClientes;

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
