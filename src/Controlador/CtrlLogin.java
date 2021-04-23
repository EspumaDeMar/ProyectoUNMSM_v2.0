package Controlador;

import Conexion.Conexion;
import Conexion.DBParametro;
import Controlador.ClienteControllers.CtrlClienteView;
import Controlador.ColaboradorControllers.CtrlColaboradorView;

import Principal.AppEngine;

import Modelo.Cliente;
import Modelo.Colaborador;
import Modelo.Compra;
import Modelo.Cuenta;
import Modelo.Producto;

import Vista.ClienteViews.FrmClienteView;
import Vista.ColaboradorViews.FrmColaboradorView;
import Vista.FrmLogin;
import Vista.FrmRecuperarContraseña;
import Vista.FrmRegistrarme;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlLogin {

    FrmLogin vista;

    public CtrlLogin(FrmLogin vista) {
        this.vista = vista;

        Action accion = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String correo = vista.txtCorreo.getText();
                String contraseña = vista.txtContraseña.getText();

                if (correo.isBlank() || contraseña.isBlank() || !(correo.contains("@unmsm.edu.pe") || correo.contains("@gmail.com"))) {
                    JOptionPane.showMessageDialog(vista, "Ingresa un usuario y/o contraseña válido(s)", "Iniciar sesión", 0);
                    vista.txtContraseña.requestFocus();

                    return;
                }

                try {
                    List<DBParametro> parametros = new ArrayList<DBParametro>();
                    parametros.add(new DBParametro("correo", correo));
                    parametros.add(new DBParametro("contraseña", contraseña));

                    ResultSet rs = Conexion.getSP("GETLogin(?,?)", parametros);

                    while (rs.next()) {
                        if (rs.getInt("RESULT") == 1) {
                            if (rs.getInt("TIPO") == 1) {
                                Colaborador colaborador = new Colaborador(
                                        rs.getInt("ID"),
                                        rs.getInt("DNI"),
                                        rs.getString("NOMBRE"),
                                        rs.getString("APELLIDOPATERNO"),
                                        rs.getString("APELLIDOMATERNO"),
                                        rs.getString("SEXO"));
                                colaborador.setCuenta(new Cuenta(correo, contraseña));
                                colaborador.setCargo(rs.getString("CARGO"));
                                if (rs.getInt("TURNO") == 1) {
                                    colaborador.setTurno("MAÑANA");
                                } else {
                                    colaborador.setTurno(("NOCHE"));
                                }

                                AppEngine.iniciarSesion(colaborador.getID());

                                FrmColaboradorView fColaboradorV = new FrmColaboradorView() {
                                    @Override
                                    public void dispose() {
                                        try {
                                            getFrame().setVisible(true);
                                            getFrame().setLocationRelativeTo(null);

                                            AppEngine.cerrarSesion(colaborador.getID());

                                            super.dispose();
                                        } catch (SQLException ex) {
                                            JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.getMessage(), "SQL", 0);
                                        }
                                    }
                                };
                                CtrlColaboradorView cColaboradorV = new CtrlColaboradorView(fColaboradorV, colaborador);
                                cColaboradorV.inicializar();
                            } else {
                                Cliente cliente = new Cliente(
                                        rs.getInt("ID"),
                                        rs.getInt("DNI"),
                                        rs.getString("NOMBRE"),
                                        rs.getString("APELLIDOPATERNO"),
                                        rs.getString("APELLIDOMATERNO"),
                                        rs.getString("SEXO"));
                                cliente.setCuenta(new Cuenta(correo, contraseña));

                                parametros.clear();
                                parametros.add(new DBParametro("ID", cliente.getID()));
                                rs = Conexion.getSP("GETComprasDeCliente(?)", parametros);

                                parametros.clear();
                                while (rs.next()) {
                                    Compra compra = new Compra(cliente);
                                    compra.setID(rs.getInt("ID_COMPRA"));

                                    parametros.add(new DBParametro("ID_COMPRA", compra.getID()));
                                    ResultSet rsTemp = Conexion.getSP("GETProductosDeCompra(?)", parametros);

                                    while (rsTemp.next()) {
                                        Producto producto = new Producto(
                                                rsTemp.getInt("ID_PRODUCTO"),
                                                rsTemp.getDouble("PRECIO"),
                                                rsTemp.getString("NOMBRE"),
                                                rsTemp.getString("DETALLE"));
                                        compra.agregarProductos(producto);
                                    }
                                    
                                    cliente.agregarCompras(compra);
                                }

                                AppEngine.iniciarSesion(cliente.getID());

                                FrmClienteView fClienteView = new FrmClienteView() {
                                    @Override
                                    public void dispose() {
                                        try {
                                            getFrame().setVisible(true);
                                            getFrame().setLocationRelativeTo(null);

                                            AppEngine.cerrarSesion(cliente.getID());

                                            super.dispose();
                                        } catch (SQLException ex) {
                                            JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.getMessage(), "SQL", 0);
                                        }
                                    }
                                };
                                CtrlClienteView cClienteView = new CtrlClienteView(fClienteView, cliente);

                                cClienteView.inicializar();
                            }
                            vista.dispose();
                        } else {
                            String mensaje = "Al parecer aún no estás registrado, ¡únete a nosotros registrándote! "
                                    + "o en caso hayas olvidado tu contraseña, ¡intenta reestableciéndola!";
                            JOptionPane.showMessageDialog(vista, mensaje, "Iniciar sesión", 1);
                            vista.btnRegistrarme.requestFocus();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Oops! Ha ocurrido un error: " + ex.getMessage(), "SQL", 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", 0);
                }
            }
        };

        this.vista.btnIniciarSesion.addActionListener(accion);
        this.vista.txtCorreo.addActionListener(accion);
        this.vista.txtContraseña.addActionListener(accion);

        this.vista.btnRegistrarme.addActionListener((ActionEvent e) -> {
            FrmRegistrarme fRegistrarme = new FrmRegistrarme() {
                @Override
                public void dispose() {
                    getFrame().setVisible(true);
                    getFrame().setLocationRelativeTo(null);

                    super.dispose();
                }
            };

            CtrlRegistrarme cRegistrarme = new CtrlRegistrarme(fRegistrarme);
            cRegistrarme.inicializar();

            vista.dispose();
        });

        this.vista.lvlOlvideContraseña.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmRecuperarContraseña fRecuperarContraseña = new FrmRecuperarContraseña() {
                    @Override
                    public void dispose() {
                        getFrame().setVisible(true);
                        getFrame().setLocationRelativeTo(null);

                        super.dispose();
                    }
                };

                CtrlRecuperarContraseña cRecuperarContraseña = new CtrlRecuperarContraseña(fRecuperarContraseña);
                cRecuperarContraseña.inicializar();

                vista.dispose();
            }
        });
    }

    private JFrame getFrame() {
        return vista;
    }

    public void inicializar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
