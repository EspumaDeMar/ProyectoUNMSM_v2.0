package Controlador.ColaboradorControllers;

import Conexion.Conexion;
import Conexion.DBParametro;

import Modelo.Cargo;
import Modelo.Colaborador;
import Modelo.Turno;

import Utilitario.ModeloJTable;

import Principal.AppEngine;

import Vista.ColaboradorViews.FrmMantenimientoColaboradores;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class CtrlMantenimientoColaboradores {

    FrmMantenimientoColaboradores vista;
    Colaborador colaborador;

    List<Colaborador> colaboradores;
    int ID = 0;
    int DNI;
    int turno;
    String nombre;
    String apellidoMaterno;
    String apellidoPaterno;
    String correo;
    String contraseña;
    String sexo;
    String cargo;
    String descripcionTurno;

    public CtrlMantenimientoColaboradores(FrmMantenimientoColaboradores vista, Colaborador colaborador) {
        this.vista = vista;
        this.colaborador = colaborador;

        try {
            obtenerColaboradores();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
        }

        vista.btnLimpiar.addActionListener((ActionEvent e) -> {
            limpiarDatos();
        });

        vista.btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                establecerDatos(false);

                List<DBParametro> parametros = new ArrayList<DBParametro>();
                parametros.add(new DBParametro("@ID", ID));
                parametros.add(new DBParametro("@NOMBRE", nombre));
                parametros.add(new DBParametro("@APELLIDOPATERNO", apellidoPaterno));
                parametros.add(new DBParametro("@APELLIDOMATERNO", apellidoMaterno));
                parametros.add(new DBParametro("@DNI", DNI));
                parametros.add(new DBParametro("@SEXO", sexo));
                parametros.add(new DBParametro("@CORREO", correo));
                parametros.add(new DBParametro("@CARGO", cargo));
                parametros.add(new DBParametro("@TURNO", turno));
                parametros.add(new DBParametro("@DES_TURNO", descripcionTurno));
                parametros.add(new DBParametro("@USUARIO_MODIFICACION", colaborador.getID()));

                Conexion.setSP("SETActualizarColaborador(?,?,?,?,?,?,?,?,?,?,?)", parametros);
                JOptionPane.showMessageDialog(vista, "Se actualizaron los datos del colaborador exitosamente.", "Mantenimiento de colaboradores", 1);

                obtenerColaboradores();
                limpiarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "!Ingrese un número de DNI válido!", "Mantenimiento de colaboradores", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
            }
        });

        vista.btnAgregar.addActionListener((ActionEvent e) -> {
            try {
                establecerDatos(true);

                List<DBParametro> parametros = new ArrayList<DBParametro>();
                parametros.add(new DBParametro("@NOMBRE", nombre));
                parametros.add(new DBParametro("@APELLIDOPATERNO", apellidoPaterno));
                parametros.add(new DBParametro("@APELLIDOMATERNO", apellidoMaterno));
                parametros.add(new DBParametro("@DNI", DNI));
                parametros.add(new DBParametro("@SEXO", sexo));
                parametros.add(new DBParametro("@USUARIO_CREACION", colaborador.getID()));
                parametros.add(new DBParametro("@CORREO", correo));
                parametros.add(new DBParametro("@CONTRASEÑA", contraseña));
                parametros.add(new DBParametro("@CARGO", cargo));
                parametros.add(new DBParametro("@TURNO", turno));
                parametros.add(new DBParametro("@DES_TURNO", descripcionTurno));

                Conexion.setSP("SETNuevoColaborador(?,?,?,?,?,?,?,?,?,?,?)", parametros);

                JOptionPane.showMessageDialog(vista, "Se ha concluido el proceso exitosamente.", "Mantenimiento de colaboradores", 1);

                obtenerColaboradores();
                limpiarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "!Ingrese un número de DNI válido!", "Mantenimiento de colaboradores", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
            }
        });

        vista.btnEliminar.addActionListener((ActionEvent e) -> {
            try {
                int dialogResult = JOptionPane.showConfirmDialog(vista, "Está a punto de eliminar los registros de un colaborador, ¿desea continuar?", "Eliminar colaborador", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (dialogResult == JOptionPane.YES_OPTION) {

                    establecerDatos(false);

                    List<DBParametro> parametros = new ArrayList<DBParametro>();
                    parametros.add(new DBParametro("@ID", ID));

                    Conexion.setSP("SETEliminarColaborador(?)", parametros);

                    JOptionPane.showMessageDialog(vista, "Se ha concluido el proceso exitosamente.", "Mantenimiento de colaboradores", 1);

                    obtenerColaboradores();
                    limpiarDatos();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido el siguiente error: " + ex.getMessage(), "SQL", 0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "!Ingrese un número de DNI válido!", "Mantenimiento de colaboradores", 0);
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
                    ID = (int) vista.tblConsulta.getValueAt(tbl.getSelectedRow(), 0);
                    for (Colaborador colaboradorTBL : colaboradores) {
                        if (colaboradorTBL.getID() == ID) {
                            vista.txtNombre.setText(colaboradorTBL.getNombre());
                            vista.txtApellidoPaterno.setText(colaboradorTBL.getApellidoPaterno());
                            vista.txtApellidoMaterno.setText(colaboradorTBL.getApellidoMaterno());
                            vista.txtCorreo.setText(colaboradorTBL.getCuenta().getCorreo());
                            vista.txtDNI.setText(String.valueOf(colaboradorTBL.getDNI()));
                            for (Cargo cargo : Cargo.values()) {
                                if (cargo.toString().toUpperCase().equals(colaboradorTBL.getCargo())) {
                                    vista.cboCargo.setSelectedIndex(cargo.ordinal());
                                    break;
                                }
                            }

                            for (Turno turno : Turno.values()) {
                                if (turno.toString().toUpperCase().equals(colaboradorTBL.getTurno())) {
                                    vista.cboTurno.setSelectedIndex(turno.ordinal());
                                    break;
                                }
                            }

                            if (colaboradorTBL.getSexo().equals("MASCULINO")) {
                                vista.rdoMasculino.setSelected(true);
                            } else {
                                vista.rdoFemenino.setSelected(true);
                            }

                            break;
                        }
                    }
                }
            }
        });
    }

    private void obtenerColaboradores() throws SQLException {
        String[] columnas = new String[8];
        for (int i = 0; i < 8; i++) {
            columnas[i] = vista.tblConsulta.getColumnName(i);
        }

        ModeloJTable modelo = new ModeloJTable(null, columnas);
        colaboradores = AppEngine.getColaboradores();
        Object[] registro = new Object[8];

        for (Colaborador colaboradorAux : colaboradores) {
            registro[0] = colaboradorAux.getID();
            registro[1] = colaboradorAux.getDNI();
            registro[2] = colaboradorAux.getNombre();
            registro[3] = colaboradorAux.getApellidoPaterno();
            registro[4] = colaboradorAux.getApellidoMaterno();
            registro[5] = colaboradorAux.getSexo();
            registro[6] = colaboradorAux.getCargo();
            registro[7] = colaboradorAux.getTurno();
            modelo.addRow(registro);
        }

        vista.tblConsulta.setModel(modelo);
    }

    private void establecerDatos(boolean contraseñaBoolean) throws Exception {
        nombre = vista.txtNombre.getText().toUpperCase();
        apellidoMaterno = vista.txtApellidoMaterno.getText().toUpperCase();
        apellidoPaterno = vista.txtApellidoPaterno.getText().toUpperCase();
        if (nombre.isBlank()
                || apellidoMaterno.isBlank()
                || apellidoPaterno.isBlank()
                || (!vista.rdoFemenino.isSelected() && !vista.rdoMasculino.isSelected())) {
            throw new Exception("¡Complete todos los campos para continuar con el registro!");
        }

        if (vista.cboCargo.getSelectedIndex() == -1 || vista.cboTurno.getSelectedIndex() == -1) {
            throw new Exception("¡Seleccione un cargo y un turno!");
        }
        cargo = vista.cboCargo.getSelectedItem().toString().toUpperCase();

        turno = (vista.cboTurno.getSelectedIndex() + 1);
        descripcionTurno = vista.cboTurno.getSelectedItem().toString().toUpperCase();

        correo = "";
        if (validarCorreo(vista.txtCorreo.getText())) {
            correo = vista.txtCorreo.getText();
        }

        if (contraseñaBoolean) {
            contraseña = "";
            if (validarContraseña(vista.txtContraseña.getText(), vista.txtRepetirContraseña.getText())) {
                contraseña = vista.txtContraseña.getText();
            }
        }

        DNI = Integer.parseInt(vista.txtDNI.getText());

        sexo = "";
        if (vista.rdoMasculino.isSelected()) {
            sexo = "MASCULINO";
        } else {
            sexo = "FEMENINO";
        }
    }

    private boolean validarCorreo(String correo) throws Exception {
        if (!(correo.contains("@gmail.com") || correo.contains("@unmsm.edu.pe")) || correo.isBlank()) {
            vista.txtCorreo.setSelectionStart(0);
            vista.txtCorreo.setSelectionEnd(vista.txtCorreo.getText().length() - 1);
            throw new Exception("¡Ingresa un correo valido para continuar con el registro!");
        }
        return true;
    }

    private boolean validarContraseña(String contraseña, String repetirContraseña) throws Exception {
        if (!contraseña.equals(repetirContraseña) || contraseña.isBlank() || repetirContraseña.isBlank()) {
            vista.txtRepetirContraseña.setSelectionStart(0);
            vista.txtRepetirContraseña.setSelectionEnd(vista.txtRepetirContraseña.getText().length());
            throw new Exception("¡Las dos contraseñas no coinciden! Recuerda no usar espacios en blanco.");
        }
        return true;
    }

    private void limpiarDatos() {
        ID = 0;
        vista.txtDNI.setText("");
        vista.txtNombre.setText("");
        vista.txtApellidoPaterno.setText("");
        vista.txtApellidoMaterno.setText("");
        vista.txtCorreo.setText("");
        vista.txtContraseña.setText("");
        vista.txtRepetirContraseña.setText("");
        vista.cboCargo.setSelectedIndex(-1);
        vista.cboTurno.setSelectedIndex(-1);
        vista.btnEliminar.setEnabled(false);
        vista.btnGuardar.setEnabled(false);
        vista.btnGuardar.setBackground(new Color(204, 204, 204));
        vista.btnAgregar.setEnabled(true);
        vista.btnAgregar.setBackground(new Color(3, 137, 57));
    }

    public void inicializar() {
        vista.rdoGrupo.add(this.vista.rdoFemenino);
        vista.rdoGrupo.add(this.vista.rdoMasculino);
        vista.cboCargo.setModel(new DefaultComboBoxModel(Cargo.values()));
        vista.cboCargo.setSelectedIndex(-1);
        vista.cboTurno.setModel(new DefaultComboBoxModel(Turno.values()));
        vista.cboTurno.setSelectedIndex(-1);
        vista.btnEliminar.setEnabled(false);
        vista.btnGuardar.setEnabled(false);
        vista.show();
    }
}
