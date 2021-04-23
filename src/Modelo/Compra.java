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
    
    private int ID;
    private double Monto;
    private List<Producto> Productos;
    private Cliente Cliente;

    /***
     * 
     * @param cliente 
     */
    public Compra(Cliente cliente) {
        this.Cliente = cliente;
        this.Productos = new ArrayList<Producto>();
        this.Monto = 0;
    }
    
    public void agregarProducto(Producto producto){
        Productos.add(producto);
        Monto = Monto + producto.getPrecio();
    }
    
    public void eliminarProducto(Producto producto){
        Productos.remove(producto);
        Monto = Monto - producto.getPrecio();
    }

    /***
     * 
     * @return 
     */
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
     * @param Monto 
     */
    public void setMonto(double Monto) {
        this.Monto = Monto;
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
     * @return 
     */
    public Modelo.Cliente getCliente() {
        return Cliente;
    }
}
