package Modelo;

public class Colaborador extends EntidadBase{
    private String Cargo;
    private String Turno;

    public Colaborador(int ID, int DNI, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo) {
        super(ID, DNI, nombre, apellidoPaterno, apellidoMaterno, sexo);
        Cargo = "";
        Turno = "";
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getTurno() {
        return Turno;
    }

    public void setTurno(String Turno) {
        this.Turno = Turno;
    }    
}
