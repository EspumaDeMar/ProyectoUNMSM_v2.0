package Modelo;

/**
 * *
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public abstract class EntidadBase {

    private final int ID;
    private int DNI;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private Cuenta Cuenta;
    private String Sexo;

    /**
     * *
     *
     * @param ID
     * @param DNI
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param sexo
     */
    public EntidadBase(int ID, int DNI, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo) {
        this.ID = ID;
        this.DNI = DNI;
        this.Nombre = nombre;
        this.ApellidoPaterno = apellidoPaterno;
        this.ApellidoMaterno = apellidoMaterno;
        this.Sexo = sexo;
    }

    /**
     * *
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * *
     *
     * @return
     */
    public int getDNI() {
        return DNI;
    }

    /**
     * *
     *
     * @param DNI
     */
    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    /**
     * *
     *
     * @return
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * *
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    /**
     * *
     *
     * @return
     */
    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    /**
     * *
     *
     * @param apellidoPaterno
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    /**
     * *
     *
     * @return
     */
    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    /**
     * *
     *
     * @param apellidoMaterno
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    /**
     * *
     *
     * @return
     */
    public Modelo.Cuenta getCuenta() {
        return Cuenta;
    }

    /**
     * *
     *
     * @param cuenta
     */
    public void setCuenta(Modelo.Cuenta cuenta) {
        Cuenta = cuenta;
    }

    /**
     * *
     *
     * @return
     */
    public String getSexo() {
        return Sexo;
    }

    /**
     * *
     *
     * @param sexo
     */
    public void setSexo(String sexo) {
        Sexo = sexo;
    }
}
