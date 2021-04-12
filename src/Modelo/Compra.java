package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private int ID;
    private int Monto;
    private List<Producto> Productos;
    private Cliente Cliente;

    public Compra(int monto, Cliente cliente) {
        Monto = monto;
        Productos = new ArrayList<Producto>();
        Cliente = cliente;
    }

    public int getID() {
        return ID;
    }

    public int getMonto() {
        return Monto;
    }

    public void setMonto(int monto) {
        Monto = monto;
    }

    public List<Producto> getProductos() {
        return Productos;
    }

    public void setProductos(List<Producto> productos) {
        Productos = productos;
    }

    public Modelo.Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Modelo.Cliente cliente) {
        Cliente = cliente;
    }
}
