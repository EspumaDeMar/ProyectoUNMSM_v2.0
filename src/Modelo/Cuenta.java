package Modelo;

/**
 * *
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Cuenta {

    private String Correo;
    private String Contraseña;

    /**
     * *
     *
     * @param correo
     * @param contraseña
     */
    public Cuenta(String correo, String contraseña) {
        Correo = correo;
        Contraseña = contraseña;
    }

    /**
     * *
     *
     * @return
     */
    public String getCorreo() {
        return Correo;
    }

    /**
     * *
     *
     * @return
     */
    public String getContraseña() {
        return Contraseña;
    }

    /**
     * *
     *
     * @param contraseña
     */
    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
