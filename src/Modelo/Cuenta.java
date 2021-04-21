package Modelo;

/***
 * 
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Cuenta {
    
    private String Correo;
    private String Contraseña;
    private boolean Conectado;

    /***
     * 
     * @param correo
     * @param contraseña 
     */
    public Cuenta(String correo, String contraseña) {
        Correo = correo;
        Contraseña = contraseña;
        Conectado = false;
    }

    /***
     * 
     * @return 
     */
    public String getCorreo() {
        return Correo;
    }

    /***
     * 
     * @return 
     */
    public String getContraseña() {
        return Contraseña;
    }

    /***
     * 
     * @param contraseña 
     */
    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    /***
     * 
     * @return 
     */
    public boolean isConectado() {
        return Conectado;
    }

    /***
     * 
     * @param conectado 
     */
    public void setConectado(boolean conectado) {
        Conectado = conectado;
    }
}
