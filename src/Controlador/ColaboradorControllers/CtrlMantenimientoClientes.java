package Controlador.ColaboradorControllers;

import Modelo.Colaborador;
import Vista.ColaboradorViews.FrmMantenimientoClientes;

public class CtrlMantenimientoClientes {
    
    FrmMantenimientoClientes vista;
    Colaborador colaborador;

    public CtrlMantenimientoClientes(FrmMantenimientoClientes vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        
    }    
    
    public void inicializar(){
        
    }    
}
