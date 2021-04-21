package Modelo;

/***
 * 
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Producto {

    private final int ID;
    private double Precio;
    private String Nombre;
    private String Detalle;

    /***
     * 
     * @param ID
     * @param Precio
     * @param Nombre
     * @param Detalle 
     */
    public Producto(int ID, double Precio, String Nombre, String Detalle) {
        this.ID = ID;
        this.Precio = Precio;
        this.Nombre = Nombre;
        this.Detalle = Detalle;
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
    public double getPrecio() {
        return Precio;
    }

    /***
     * 
     * @param Precio 
     */
    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    /***
     * 
     * @return 
     */
    public String getNombre() {
        return Nombre;
    }

    /***
     * 
     * @param Nombre 
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /***
     * 
     * @return 
     */
    public String getDetalle() {
        return Detalle;
    }

    /***
     * 
     * @param Detalle 
     */
    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }
}
