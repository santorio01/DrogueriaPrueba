/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author monst
 */
public class ConexionBd {
// Configuracion de la conexion a la base de datos

    private String url = "";
    public Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
//Constructor sin parmetros

    public ConexionBd() {
        url = "jdbc:sqlite:DrogueriaBD.db";
        try {
            Class.forName("org.sqlite.JDBC");

// Realizar la conexion
            con = DriverManager.getConnection(url);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("Base de datos conectada " + meta.getDriverName());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBd.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public boolean setAutoCommitBD(boolean parametro) {
        try {
            con.setAutoCommit(parametro);
        } catch (SQLException sqlex) {
            System.out.println("Error al configurar el autoCommit " + sqlex.getMessage());
            return false;
        }
        return true;
    }
    // Metodo que realiza un INSERT y devuelve TRUE si la operacin fue existosa

    public boolean insertarBD(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean commitBD() {
        try {
            con.commit();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer commit " + sqlex.getMessage());
            return false;
        }
    }

    public boolean rollbackBD() {
        try {
            con.rollback();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer rollback " + sqlex.getMessage());
            return false;
        }
    }

    //Retornar la conexin
    public Connection getConnection() {
        return con;
    }
//Cerrar la conexin

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Mtodo que devuelve un ResultSet de una consulta (tratamiento de SELECT)

    public ResultSet consultarBd(String sentencia) {
        try {
            PreparedStatement pstm= con.prepareStatement(sentencia);
            ResultSet respuesta = pstm.executeQuery();
            return respuesta;       
        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   return null;
        
    }

    // Mtodo que realiza una operacin como UPDATE, DELETE, CREATE TABLE, entre otras
// y devuelve TRUE si la operacin fue existosa
    public boolean actualizarBD(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }

    public boolean eliminarBD(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;

    }
}
