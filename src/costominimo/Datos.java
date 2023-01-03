/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costominimo;

/**
 *
 * @author Abner
 */
public class Datos {
    
    //atributos
    private double cantidad;
    private String precio;
    private boolean lleno;
    
    
    //metodos
    
    public Datos (double cantidad, String precio, boolean lleno){
        
        this.cantidad = cantidad;
        this.precio = precio;
        this.lleno = lleno;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public boolean isLleno() {
        return lleno;
    }

    public void setLleno(boolean lleno) {
        this.lleno = lleno;
    }
    
    
}
