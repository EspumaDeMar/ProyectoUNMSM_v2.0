package Controlador.ClienteControllers;

import Conexion.DBParametro;

import Modelo.Cliente;
import Modelo.Compra;
import Modelo.Enum.CodigoPromocional;
import Modelo.Interface.IControlador;
import Modelo.Producto;

import Principal.AppEngine;

import Utilitario.ModeloJTable;

import Vista.ClienteViews.FrmComprar;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import java.util.List;
import javax.swing.JButton;

public class CtrlComprar implements IControlador {

    FrmComprar vista;
    Cliente cliente;

    Compra compra;
    List<Producto> productos;
    int ID_PRODUCTO = 0;

    public CtrlComprar(FrmComprar vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;

        compra = new Compra(cliente);

        vista.btnComprar.addActionListener((ActionEvent e) -> {
            try {
                List<DBParametro> parametros = new ArrayList<DBParametro>();
                parametros.add(new DBParametro("@MONTO", compra.getMonto()));
                parametros.add(new DBParametro("@ID_CLIENTE", compra.getCliente().getID()));

                ResultSet rs = Conexion.Conexion.getSP("SETNuevaCompra(?,?)", parametros);
                parametros.clear();
                while (rs.next()) {
                    compra.setID(rs.getInt("ID_COMPRA"));
                    for (Producto producto : compra.getProductos()) {
                        parametros.add(new DBParametro("@ID_PRODUCTO", producto.getID()));
                        parametros.add(new DBParametro("@ID_COMPRA", compra.getID()));
                        Conexion.Conexion.setSP("SETNuevaCompraDetalle(?,?)", parametros);
                    }
                }
                JOptionPane.showMessageDialog(vista, "¡Gracias por tu compra LAGART@!", "Lagarto Store", 1);
                compra = null;
                vista.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.getMessage(), "SQL", 0);
            }
        });

        vista.btnAplicar.addActionListener((ActionEvent e) -> {
            String codigoPromocional = vista.txtCodigoPromocional.getText().toUpperCase();
            for (CodigoPromocional codigo : CodigoPromocional.values()) {
                if (codigo.getCodigo().equals(codigoPromocional)) {
                    compra.setMonto(compra.getMonto() * (1 - codigo.getDescuento()));
                    vista.lblMonto.setText(String.valueOf(compra.getMonto()));
                    vista.txtCodigoPromocional.setEditable(false);
                    break;
                }
            }
        });

        vista.tblCompras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable tbl = (JTable) evt.getSource();
                if (evt.getClickCount() == 2 && tbl.getSelectedRow() != -1) {
                    ID_PRODUCTO = (int) vista.tblConsulta.getValueAt(tbl.getSelectedRow(), 0);
                    for (Producto producto : compra.getProductos()) {
                        compra.eliminarProducto(producto);
                        setCarritoDeCompras();
                        toggleBotones();

                        break;
                    }
                }
            }
        });

        vista.tblConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable tbl = (JTable) evt.getSource();
                if (evt.getClickCount() == 2 && tbl.getSelectedRow() != -1) {
                    ID_PRODUCTO = (int) vista.tblConsulta.getValueAt(tbl.getSelectedRow(), 0);
                    for (Producto producto : productos) {
                        if (producto.getID() == ID_PRODUCTO) {
                            compra.agregarProducto(producto);
                            setCarritoDeCompras();

                            toggleBotones();
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
        vista.lblMonto.setText(String.valueOf(compra.getMonto()));
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

    public void toggleBotones() {
        for (int i = 0; i < vista.panelCarritoCompras.getComponentCount(); i++) {
            if (vista.panelCarritoCompras.getComponent(i) instanceof JButton) {
                ((JButton) vista.panelCarritoCompras.getComponent(i)).setEnabled(vista.tblCompras.getRowCount() > 0);
            }
        }
    }

    public void inicializar() throws Exception {
        obtenerProductos();
        vista.show();
    }
}
