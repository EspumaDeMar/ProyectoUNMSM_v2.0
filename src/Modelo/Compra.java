package Modelo;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Compra {
    
    private final int ID;
    private double Monto;
    private List<Producto> Productos;
    private Cliente Cliente;

    /***
     * 
     * @param ID
     * @param monto
     * @param cliente 
     */
    public Compra(int ID, double monto, Cliente cliente) {
        this.ID = ID;
        this.Monto = monto;
        this.Productos = new ArrayList<Producto>();
        this.Cliente = cliente;
    }

    /***
     * 
     * @return 
     */
    public int getID() {
        return ID;
    }

    /***
     * 
     * @return 
     */
    public double getMonto() {
        return Monto;
    }

    /***
     * 
     * @param monto 
     */
    public void setMonto(double monto) {
        Monto = monto;
    }

    /***
     * 
     * @return 
     */
    public List<Producto> getProductos() {
        return Productos;
    }

    /***
     * 
     * @param productos 
     */
    public void setProductos(List<Producto> productos) {
        Productos = productos;
    }

    /***
     * 
     * @return 
     */
    public Modelo.Cliente getCliente() {
        return Cliente;
    }

    /***
     * 
     * @param cliente 
     */
    public void setCliente(Modelo.Cliente cliente) {
        Cliente = cliente;
    }
}
