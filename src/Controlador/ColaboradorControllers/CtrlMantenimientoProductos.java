package Controlador.ColaboradorControllers;

import Modelo.Colaborador;
import Vista.ColaboradorViews.FrmMantenimientoProductos;

public class CtrlMantenimientoProductos {
    
    FrmMantenimientoProductos vista;
    Colaborador colaborador;

    public CtrlMantenimientoProductos(FrmMantenimientoProductos vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
    }    
    
    public void inicializar(){
        this.vista.show();        
    }    
}
