/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author monst
 */
public class Lote {
    
    private List<Producto> productos;

    public Lote() {
        this.productos=new ArrayList<>();
    }
    
    public void agregarProducto (Producto p){
        productos.add(p);
    }

    @Override
    public String toString() {
        return "Lote{" + "productos=" +"\n"+ productos + '}';
    }
    
    
    
}
