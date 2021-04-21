package Modelo;

/***
 * 
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Colaborador extends EntidadBase{
    
    private String Cargo;
    private String Turno;

    /***
     * 
     * @param ID
     * @param DNI
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param sexo 
     */
    public Colaborador(int ID, int DNI, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo) {
        super(ID, DNI, nombre, apellidoPaterno, apellidoMaterno, sexo);
        Cargo = "";
        Turno = "";
    }

    /***
     * 
     * @return 
     */
    public String getCargo() {
        return Cargo;
    }

    /***
     * 
     * @param Cargo 
     */
    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    /***
     * 
     * @return 
     */
    public String getTurno() {
        return Turno;
    }

    /***
     * 
     * @param Turno 
     */
    public void setTurno(String Turno) {
        this.Turno = Turno;
    }    
}
