package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends EntidadBase{
    private List<Compra> Compras;

    public Cliente(int ID, int DNI, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo) {
        super(ID, DNI, nombre, apellidoPaterno, apellidoMaterno, sexo);
        Compras = new ArrayList<Compra>();
    }

    public List<Compra> getCompras() {
        return Compras;
    }

    public void setCompras(List<Compra> Compras) {
        this.Compras = Compras;
    }    
}
