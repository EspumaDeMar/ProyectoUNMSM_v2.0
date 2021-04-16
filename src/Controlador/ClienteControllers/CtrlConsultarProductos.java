package Controlador.ClienteControllers;

import Modelo.Cliente;
import Modelo.Producto;
import Principal.AppEngine;
import Vista.ClienteViews.FrmConsultarProductos;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CtrlConsultarProductos {

    FrmConsultarProductos vista;
    Cliente cliente;

    public CtrlConsultarProductos(FrmConsultarProductos vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;

        obtenerProductos(vista);

    }

    private void obtenerProductos(FrmConsultarProductos vista) {
        String[] columnas = new String[vista.tblConsulta.getColumnCount()];
        for (int i = 0; i < 4; i++) {
            columnas[i] = vista.tblConsulta.getColumnName(i);
        }

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Producto> productos = AppEngine.getProductos(vista);
        Object[] registro = new Object[4];
        for (Producto producto : productos) {
            registro[0] = producto.getID();
            registro[1] = producto.getNombre();
            registro[2] = producto.getPrecio();
            registro[3] = producto.getDetalle();
            
            modelo.addRow(registro);
        }
        
        vista.tblConsulta.setModel(modelo);
    }

    public void inicializar() {
        this.vista.show();
    }
}
