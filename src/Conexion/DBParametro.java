package Conexion;

/**
 * <h1>DBParametro</h1>
 * <p>
 * Es una clase que define objetos que poseen atributos generales para poder ser 
 * enviados en la ejecución de un stored procedure.
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class DBParametro {
    
    private String nombre;
    private Object valor;

    /***
     * Es un método constructor de la clase y recibe dos parámetros de tipo String
     * y de tipo Object para asignarselo a los atributos de la nueva instancia, dígase, objeto.
     * @param nombre
     * @param valor 
     */
    public DBParametro(String nombre, Object valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    /***
     * 
     * @return {@code nombre}
     */
    public String getNombre() {
        return nombre;
    }

    /***
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /***
     * 
     * @return {@code valor}
     */
    public Object getValor() {
        return valor;
    }

    /***
     * 
     * @param valor 
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }    
}
