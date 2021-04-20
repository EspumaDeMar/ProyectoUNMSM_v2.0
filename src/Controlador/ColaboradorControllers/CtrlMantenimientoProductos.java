package Controlador.ColaboradorControllers;

import Conexion.Conexion;
import Conexion.DBParametro;
import Modelo.Colaborador;
import Modelo.Producto;
import Principal.AppEngine;
import Utilitario.ModeloJTable;

import Vista.ColaboradorViews.FrmMantenimientoProductos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CtrlMantenimientoProductos {
    
    FrmMantenimientoProductos vista;
    Colaborador colaborador;
    
    List<Producto> productos;
    int ID_PRODUCTO = 0;
    String nombre;
    String detalle;
    double precio;
    
    public CtrlMantenimientoProductos(FrmMantenimientoProductos vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;
        
        try {
            obtenerProductos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
        }
        
        vista.btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                establecerDatos();
                
                List<DBParametro> parametros = new ArrayList<DBParametro>();
                parametros.add(new DBParametro("@ID_PRODUCTO", ID_PRODUCTO));
                parametros.add(new DBParametro("@NOMBRE", nombre));
                parametros.add(new DBParametro("@PRECIO", precio));
                parametros.add(new DBParametro("@DETALLE", detalle));
                parametros.add(new DBParametro("@USUARIO_MODIFICACION", colaborador.getID()));
                Conexion.setSP("SETActualizarProducto(?,?,?,?,?)", parametros);
                
                JOptionPane.showMessageDialog(vista, "Se actualizaron los datos del producto exitosamente.", "Mantenimiento de colaboradores", 1);
                
                obtenerProductos();
                limpiarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
            }
        });
        
        vista.btnAgregar.addActionListener((ActionEvent e) -> {
            try {
                establecerDatos();
                
                List<DBParametro> parametros = new ArrayList<DBParametro>();
                parametros.add(new DBParametro("@NOMBRE", nombre));
                parametros.add(new DBParametro("@PRECIO", precio));
                parametros.add(new DBParametro("@DETALLE", detalle));
                parametros.add(new DBParametro("@USUARIO_CREACION", colaborador.getID()));
                Conexion.setSP("SETNuevoProducto(?,?,?,?)", parametros);
                
                JOptionPane.showMessageDialog(vista, "Se ha concluido el proceso exitosamente.", "Mantenimiento de productos", 1);
                
                obtenerProductos();
                limpiarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
            }
        });
        
        vista.btnEliminar.addActionListener((ActionEvent e) -> {
            try {
                int dialogResult = JOptionPane.showConfirmDialog(vista, "Está a punto de eliminar los registros de un colaborador, ¿desea continuar?", "Eliminar colaborador", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if (dialogResult == JOptionPane.YES_OPTION) {
                    establecerDatos();
                    
                    List<DBParametro> parametros = new ArrayList<DBParametro>();
                    parametros.add(new DBParametro("@ID_PRODUCTO", ID_PRODUCTO));
                    Conexion.setSP("SETEliminarProducto(?)", parametros);
                    
                    JOptionPane.showMessageDialog(vista, "Se ha concluido el proceso exitosamente.", "Mantenimiento de productos", 1);
                    
                    obtenerProductos();
                    limpiarDatos();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
            }
        });
        
        vista.tblConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable tbl = (JTable) evt.getSource();
                if (evt.getClickCount() == 2 && tbl.getSelectedRow() != -1) {
                    limpiarDatos();
                    vista.btnEliminar.setEnabled(true);
                    vista.btnGuardar.setEnabled(true);
                    vista.btnGuardar.setBackground(new Color(3, 137, 57));
                    vista.btnAgregar.setEnabled(false);
                    vista.btnAgregar.setBackground(new Color(204, 204, 204));
                    ID_PRODUCTO = (int) vista.tblConsulta.getValueAt(tbl.getSelectedRow(), 0);
                    for (Producto productoTBL : productos) {
                        if (productoTBL.getID() == ID_PRODUCTO) {
                            vista.txtNombre.setText(productoTBL.getNombre());
                            vista.txtPrecio.setText(String.valueOf(productoTBL.getPrecio()));
                            vista.txtDetalle.setText(productoTBL.getDetalle());
                            
                            break;
                        }
                    }
                }
            }
        });        
        vista.btnLimpiar.addActionListener((ActionEvent e) -> {
            limpiarDatos();
        });
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
    
    private void establecerDatos() throws Exception {
        nombre = vista.txtNombre.getText().toUpperCase();
        detalle = vista.txtDetalle.getText().toUpperCase();
        if (nombre.isBlank() || detalle.isBlank() || vista.txtPrecio.getText().isBlank()) {
            throw new Exception("¡Complete todos los campos para continuar con el registro!");
        }
        
        precio = Double.parseDouble(vista.txtPrecio.getText());
    }
    
    private void limpiarDatos() {
        ID_PRODUCTO = 0;
        vista.txtNombre.setText("");
        vista.txtDetalle.setText("");
        vista.txtPrecio.setText("0.00");
        vista.btnEliminar.setEnabled(false);
        vista.btnGuardar.setEnabled(false);
        vista.btnGuardar.setBackground(new Color(204, 204, 204));
        vista.btnAgregar.setEnabled(true);
        vista.btnAgregar.setBackground(new Color(3, 137, 57));
    }
    
    public void inicializar() {
        vista.btnEliminar.setEnabled(false);
        vista.btnGuardar.setEnabled(false);
        vista.show();
    }
}
