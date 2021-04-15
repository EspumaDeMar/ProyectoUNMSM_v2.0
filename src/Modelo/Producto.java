package Modelo;

public class Producto {
    private int ID;
    private double Precio;
    private String Nombre;

    public Producto(int ID, double Precio, String Nombre) {
        this.ID = ID;
        this.Precio = Precio;
        this.Nombre = Nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
