package Modelo.Enum;

/**
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public enum CodigoPromocional {
    LAGARTO("LAGARTO", 0.5),
    FISILIDERANDOELCAMBIO("FISILIDERANDOELCAMBIO", 0.25),
    TASENSEGUNDA("TASENSEGUNDA", 0.1),
    ELUNICOGRANDE("ELUNICOGRANDE", 0.45);    

    private final String codigo;
    private final double descuento;

    private CodigoPromocional(String codigo, double descuento) {
        this.codigo = codigo;
        this.descuento = descuento;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getDescuento() {
        return descuento;
    }

    @Override
    public String toString() {
        return codigo;
    }

}
