package Modelo;

public class Cuenta {
    private String Correo;
    private String Contraseña;
    private boolean Conectado;

    public Cuenta(String correo, String contraseña) {
        Correo = correo;
        Contraseña = contraseña;
        Conectado = false;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public boolean isConectado() {
        return Conectado;
    }

    public void setConectado(boolean conectado) {
        Conectado = conectado;
    }
}
