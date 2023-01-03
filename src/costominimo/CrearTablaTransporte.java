/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costominimo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static UI.CostoMinimoUI.restricciones;
import static UI.CostoMinimoUI.datos;



public class CrearTablaTransporte {
    
    public CrearTablaTransporte(){
        
    }
    
        //metodos
    
        public void CrearTablaRestricciones(int destino, int origen, JTable tb_restricciones, int des){
            
            Object cabeza[] = new String [destino + 2+des];
            cabeza[0] = (" ");
            cabeza[destino+1+des] = ("Oferta");
            
            for(int i = 1; 1 <= destino+des; i++){
                cabeza[i]=("Destino "+i);
            }
            
            restricciones = new DefaultTableModel(cabeza,0){
              
                public boolean isCellEditable(int row, int column){
                    if (column ==0 || (row == tb_restricciones.getRowCount()-1 && column == tb_restricciones.getColumnCount()-1)){
                        return false;
                    }
                    return true;
                }
            };
            
            tb_restricciones.setModel(restricciones);
            String fila[]=new String[destino+2+des];
            
            for(int i=0; i<origen; i++){
                fila[0]=new String("Origen " + (i+1));
                restricciones.addRow(fila);
            }
            fila[0] = new String("Demanda ");
            restricciones.addRow(fila);
        }
        
        public void CrearTablaSolucion (JTable tb_restricciones, JTable tb_solucion, int extraRow, int extraColumn){
            int row = tb_restricciones.getRowCount();
            int column = tb_restricciones.getColumnCount();
            
            extraerDatos(tb_restricciones);
            
            //cabecera de la tabla
            Object cabeza[] = new String [column+extraColumn];
            for(int i = 0; i<(column + extraColumn); i++)
                cabeza[i]=("<>");
            
            //modelo de la tabla, no todas las celdas seran editables.
            DefaultTableModel modeloSolucion = new DefaultTableModel(cabeza,0){
            
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            
            //agregamos el modelo de la tabla
            tb_solucion.setModel(modeloSolucion);
            
            //agregamos filas a la tabla
            String origen[];
            
            for (int fila = 0; fila < row+extraRow; fila++){
                origen = new String[column+extraColumn];
                
                if(fila>0 && fila<row){
                    origen[0]=""+fila;
                }
                modeloSolucion.addRow(origen);
            }
            
            for (int i=0; i<modeloSolucion.getColumnCount(); i++){
                tb_solucion.getColumnModel().getColumn(i).setPreferredWidth(70);
            }
            
            //agregamos la oferta
            
            String temp;
            
            for(int fil = 0; fil < row-1; fil++){
                
                temp=""+restricciones.getValueAt(fil, column-1);
                modeloSolucion.setValueAt(temp, fil+1,column-1);
            }
            
            //agregamos demandas
            
            for(int col = 0; col <row-1; col++){
                
                temp = ""+restricciones.getValueAt(row-1, col);
                modeloSolucion.setValueAt(temp, modeloSolucion.getRowCount()-1-extraColumn, col);
                modeloSolucion.setValueAt(col, 0, col);
            }
            
            modeloSolucion.setValueAt("Demanda", row, 0);
            modeloSolucion.setValueAt("Oferta ", 0, column-1);
            
            if(extraColumn > 0)
                modeloSolucion.setValueAt("Penaliza",0, column);
            if(extraRow>0)
                modeloSolucion.setValueAt("Penaliza", row+1, 0);
        }
        
        private void extraerDatos(JTable tb_restricciones){
            
            int row = tb_restricciones.getRowCount();
            int column = tb_restricciones.getColumnCount();
            
            datos = new Datos [row-1][column-2];
            
            for(int i = 0; i<row-1; i++){
                
                for (int j=0; j<column-2; j++){
                    datos[i][j] = new Datos(0, ""+tb_restricciones.getValueAt(i, j+1), false);
                }
            }
        }
}
