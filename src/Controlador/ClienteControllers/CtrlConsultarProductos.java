package Controlador.ClienteControllers;

import Modelo.Cliente;
import Vista.ClienteViews.FrmConsultarProductos;

public class CtrlConsultarProductos {
    
    FrmConsultarProductos vista;
    Cliente cliente;

    public CtrlConsultarProductos(FrmConsultarProductos vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;
    }
    
    public void inicializar(){
        this.vista.show();
    }  
}
