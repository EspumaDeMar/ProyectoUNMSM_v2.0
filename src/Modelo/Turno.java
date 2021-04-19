package Modelo;

public enum Turno {    
    MAÑANA("Mañana", 1),
    TARDE("Tarde", 2),
    NOCHE("Noche", 3);
    
    private final String descripcion;
    private final int turno;

    private Turno(String descripcion, int turno) {
        this.descripcion = descripcion;
        this.turno = turno;
    }

    public int getTurno() {
        return turno;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}
