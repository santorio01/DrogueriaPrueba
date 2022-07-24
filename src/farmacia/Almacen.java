/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

/**
 *
 * @author monst
 */
public class Almacen {
    
    private String codigo;
    private String nombre;
    private Lote lote;

    public Almacen(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.lote = new Lote();
    }
     
    public void agregarProducto (Producto p){
        lote.agregarProducto(p);
    }

    @Override
    public String toString() {
        return "Almacen{" + "codigo=" + codigo + ", nombre=" + nombre + ", lote=" + lote + '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Lote getLote() {
        return lote;
    }
    
    
    
}
