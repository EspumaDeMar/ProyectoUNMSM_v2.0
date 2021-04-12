package Modelo;

public abstract class EntidadBase {
    private int ID;
    private int DNI;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private Cuenta Cuenta;
    private String Sexo;

    public EntidadBase(int ID, int DNI, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo) {
        this.ID = ID;
        this.DNI = DNI;
        this.Nombre = nombre;
        this.ApellidoPaterno = apellidoPaterno;
        this.ApellidoMaterno = apellidoMaterno;
        this.Sexo = sexo;
    }

    public int getID() {
        return ID;
    }    

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public Modelo.Cuenta getCuenta() {
        return Cuenta;
    }

    public void setCuenta(Modelo.Cuenta cuenta) {
        Cuenta = cuenta;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }
}
