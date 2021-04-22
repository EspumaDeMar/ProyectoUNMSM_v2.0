package Controlador.ClienteControllers;

import Modelo.Cliente;
import Modelo.Compra;
import Modelo.Interface.IControlador;
import Modelo.Producto;

import Principal.AppEngine;

import Utilitario.ModeloJTable;

import Vista.ClienteViews.FrmComprar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.JTable;
import java.util.List;

public class CtrlComprar implements IControlador {

    FrmComprar vista;
    Cliente cliente;

    List<Producto> productos;
    Compra compra = new Compra(cliente);
    int ID_PRODUCTO = 0;

    public CtrlComprar(FrmComprar vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;

        vista.tblConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable tbl = (JTable) evt.getSource();
                if (evt.getClickCount() == 2 && tbl.getSelectedRow() != -1) {
                    ID_PRODUCTO = (int) vista.tblConsulta.getValueAt(tbl.getSelectedRow(), 0);
                    for (Producto producto : productos) {
                        if (producto.getID() == ID_PRODUCTO) {
                            compra.agregarProductos(producto);
                            setCarritoDeCompras();
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setCarritoDeCompras() {
        String[] columnas = new String[4];
        for (int i = 0; i < 4; i++) {
            columnas[i] = vista.tblCompras.getColumnName(i);
        }

        ModeloJTable modelo = new ModeloJTable(null, columnas);
        Object[] registro = new Object[4];

        for (Producto productoAux : compra.getProductos()) {
            registro[0] = productoAux.getID();
            registro[1] = productoAux.getNombre();
            registro[2] = productoAux.getPrecio();
            registro[3] = productoAux.getDetalle();
            modelo.addRow(registro);
        }
        vista.tblCompras.setModel(modelo);
    }

    private void obtenerProductos() throws SQLException {
        String[] columnas = new String[4];
        for (int i = 0; i < 4; i++) {
            columnas[i] = vista.tblConsulta.getColumnName(i);
        }

        ModeloJTable modelo = new ModeloJTable(null, columnas);
        productos = AppEngine.getProductos();
        Object[] registro = new Object[4];

        for (Producto productoAux : productos) {
            registro[0] = productoAux.getID();
            registro[1] = productoAux.getNombre();
            registro[2] = productoAux.getPrecio();
            registro[3] = productoAux.getDetalle();
            modelo.addRow(registro);
        }
        vista.tblConsulta.setModel(modelo);
    }

    public void inicializar() {
        try {
            obtenerProductos();
            vista.show();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
        }
    }
}
