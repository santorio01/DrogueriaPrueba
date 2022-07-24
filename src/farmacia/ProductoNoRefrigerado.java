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
public class ProductoNoRefrigerado extends Producto {

    public ProductoNoRefrigerado(String nombre, String id, double temperatura, double valorBase) {
        super(nombre, id, temperatura, valorBase);
    }

    @Override
    public double calcularCostoDeAlmacenamiento() {
        return getValorBase()*1.1;
        

    }

}

