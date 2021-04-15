package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private int ID;
    private double Monto;
    private List<Producto> Productos;
    private Cliente Cliente;

    public Compra(int ID, double monto, Cliente cliente) {
        this.ID = ID;
        this.Monto = monto;
        this.Productos = new ArrayList<Producto>();
        this.Cliente = cliente;
    }

    public int getID() {
        return ID;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double monto) {
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
