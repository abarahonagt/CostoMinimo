/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costominimo;

import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abner
 */
public class Guardar implements Serializable{
    
    private String objetivo;
    private int filas = 0;
    private int columnas = 0;
    private DefaultTableModel restricciones = null;
    private String[][] coeficientes=null;
    
    public Guardar(DefaultTableModel restricciones, String objetivo){
        
        this.filas=restricciones.getRowCount();
        this.columnas=restricciones.getColumnCount();
        coeficientes=new String[filas][columnas];
        this.objetivo = objetivo;
        
        CrearMatrizCoeficientes(restricciones);
        
    }
    
    public void CrearMatrizCoeficientes(DefaultTableModel restricciones){
        
        for (int f=0; f<filas; f++){
            
            for(int c = 0; c<columnas; c++){
                coeficientes[f][c] = String.valueOf(restricciones.getValueAt(f, c));
            }
        }
    }
    
    public DefaultTableModel getRestricciones(){
        return restricciones;
    }
    
    public String getObjetivo(){
        return objetivo;
    }
    
    public void armarTabla(JTable tabla){
        Object cabeza[] = new String[columnas];
        cabeza[0]  =(" ");
        
        for(int i=1; i <= columnas -3; i++)
            cabeza[i] = ("X"+i);
        
        cabeza[columnas-1]=(" "); cabeza[columnas-2]=(" ");
        restricciones = new DefaultTableModel (cabeza, 0){
            public boolean isCellEditable(int row, int column){
                if(column==0)
                    return false;
                
                if(row==0 && column >= restricciones.getColumnCount()-2)
                    return false;
                
            return true;
            }
        };
        
        tabla.setModel(restricciones);
        String fila[] = new String[columnas];
        
        for(int f=0; f < filas; f++){
            for(int c=0; c<columnas; c++){
                fila[c]=coeficientes[f][c];
            }
            restricciones.addRow(fila);
        }
        restricciones.setValueAt("", 0, columnas-2);
        restricciones.setValueAt("", 0, columnas-1);
    }
}
