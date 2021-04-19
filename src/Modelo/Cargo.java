package Modelo;

public enum Cargo {
    GERENTE_GENERAL("Gerente general"),
    GERENTE_DEPARTAMENTO("Gerente de departamento"),
    DIRECTOR_DISTRITO("Director de distrito"),
    ATENCION_CLIENTE("Atención al cliente"),
    ASESOR_VENTA("Asesor de venta");

    private final String descripcion;

    private Cargo(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}
