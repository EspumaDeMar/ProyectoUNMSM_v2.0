package Modelo;

/***
 * 
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public enum Turno {    
    MAÑANA("Mañana", 1),
    TARDE("Tarde", 2),
    NOCHE("Noche", 3);
    
    private final String descripcion;
    private final int turno;

    /***
     * 
     * @param descripcion
     * @param turno 
     */
    private Turno(String descripcion, int turno) {
        this.descripcion = descripcion;
        this.turno = turno;
    }

    /***
     * 
     * @return 
     */
    public int getTurno() {
        return turno;
    }
    
    /***
     * 
     * @return 
     */
    @Override
    public String toString() {
        return descripcion;
    }
}
