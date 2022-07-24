/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import Conexion.ConexionBd;
import Grafica.VentanaPrincipal;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

/**
 *
 * @author monst
 */
public class Producto {

    private String nombre;
    private String id;
    private double temperatura;
    private double valorBase;

    public Producto(String nombre, String id, double temperatura, double valorBase) {
        this.nombre = nombre;
        this.id = id;
        this.temperatura = temperatura;
        this.valorBase = valorBase;
    }

    public Producto() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double calcularCostoDeAlmacenamiento() {
        double totalCosto = 0;
        if (this.temperatura >= 0 && this.temperatura <= 20) {
            totalCosto = this.valorBase * 1.20;
        } else if (this.temperatura >= 21) {
            totalCosto = this.valorBase * 1.10;
        }

        return totalCosto;

    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", id=" + id + ", temperatura=" + temperatura + ", valorBase=" + valorBase + '}';
    }

    public boolean guardarProducto() {
        ConexionBd conexion = new ConexionBd();
        String sentencia = "INSERT INTO Producto(id,nombre,temperatura,valorBase) "
                + " VALUES ('" + this.id + "','" + this.nombre + "','" + this.temperatura + "','"
                + this.valorBase + "');";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sentencia)) {
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public Producto consultarProducto(String id) {
        ConexionBd conexion = new ConexionBd();
        String sql = "select * from Producto where id='" + id + "';";
        ResultSet rs = conexion.consultarBd(sql);
        try {
            if (rs.next()) {
                this.id = rs.getString("id");
                this.nombre = rs.getString("nombre");
                this.temperatura = rs.getDouble("temperatura");
                this.valorBase = rs.getDouble("valorBase");
                conexion.closeConnection();
            } else {
                //JOptionPane.showMessageDialog(null, "No se encuentra ningun producto con ese ID", "Advertencia", JOptionPane.WARNING_MESSAGE);
                conexion.closeConnection();
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this;
    }

    public ArrayList<Producto> consultarTodosProducto() {
        ConexionBd conexion = new ConexionBd();
        String sql = "SELECT * FROM Producto;";
        ArrayList<Producto> productoLista = new ArrayList<>();

        //ResultSet rs = conexion.consultarBd(sql);
        try {
            ResultSet rs = conexion.consultarBd(sql);

            while (rs.next()) {
                Producto producto = new Producto(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
                productoLista.add(producto);

            }
            /*if (rs.next()) {
                this.id = rs.getString("id");
                this.nombre = rs.getString("nombre");
                this.temperatura = rs.getDouble("temperatura");
                this.valorBase = rs.getDouble("valorBase");
                System.out.println(rs.getArray(1));
                System.out.println(rs);
                conexion.closeConnection();  
                
                
            } else {
                //JOptionPane.showMessageDialog(null, "No se encuentra ningun producto con ese ID", "Advertencia", JOptionPane.WARNING_MESSAGE);
                conexion.closeConnection();
                return null;
            }*/
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productoLista;
    }

    public boolean eliminarProducto(String id) {
        String Sentencia = "DELETE FROM `Producto` WHERE `id`='" + id + "';";
        ConexionBd conexion = new ConexionBd();
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.eliminarBD(Sentencia)) {
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean actualizarProducto(String id) {
        ConexionBd conexion = new ConexionBd();
        String Sentencia = "UPDATE `Producto` SET nombre='" + this.nombre + "',temperatura='"
                + this.temperatura + "',valorBase='" + this.valorBase
                + "' WHERE id='" + id + "';";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
        } else {
            conexion.closeConnection();
            return false;
        }
    

    }
}
