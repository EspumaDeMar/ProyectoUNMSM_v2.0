package Controlador.ClienteControllers;

import Modelo.Cliente;
import Modelo.Interface.IControlador;
import Vista.ClienteViews.FrmComprar;

public class CtrlComprar implements IControlador {
    FrmComprar vista;
    Cliente cliente;

    public CtrlComprar(FrmComprar vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;
    }    
    
    public void inicializar(){
        
    }    
}
