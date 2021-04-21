package Modelo;

/**
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public enum Cargo {
    GERENTE_GENERAL("Gerente general"),
    GERENTE_DEPARTAMENTO("Gerente de departamento"),
    DIRECTOR_DISTRITO("Director de distrito"),
    ATENCION_CLIENTE("Atención al cliente"),
    ASESOR_VENTA("Asesor de venta");

    private final String descripcion;

    /**
     *
     * @param descripcion
     */
    private Cargo(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return descripcion;
    }
}
