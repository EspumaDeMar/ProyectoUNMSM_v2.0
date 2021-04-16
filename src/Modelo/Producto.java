package Modelo;

public class Producto {
    private int ID;
    private double Precio;
    private String Nombre;
    private String Detalle;

    public Producto(int ID, double Precio, String Nombre, String Detalle) {
        this.ID = ID;
        this.Precio = Precio;
        this.Nombre = Nombre;
        this.Detalle = Detalle;
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

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }
}
