/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import Grafica.VentanaPrincipal;
import Conexion.ConexionBd;

/**
 *
 * @author monst
 */
public class Drogueria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VentanaPrincipal principal = new VentanaPrincipal();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        principal.setResizable(false);
        ConexionBd();
       
        

        
        
        
        // TODO code application logic here
    }

    private static void ConexionBd() {
        ConexionBd conexcion = new ConexionBd();
         
    }

}
