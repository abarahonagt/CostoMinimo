/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costominimo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import static UI.CostoMinimoUI.extraColumn;
import static UI.CostoMinimoUI.extraRow;
import static UI.CostoMinimoUI.datos;
import static UI.CostoMinimoUI.numerosRepetidos;
import javax.swing.JPanel;


/**
 *
 * @author Abner
 */
public class RssFeedCellRender extends DefaultTableCellRenderer implements TableCellRenderer {

    //atributos
    private JLabel component;
    
    //constructor
    
    public RssFeedCellRender(){
        
    }
    
    //metodos
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        
        component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setIcon(null);
        
        if(numerosRepetidos!=null && String.valueOf(table.getValueAt(row, column)).equalsIgnoreCase(numerosRepetidos)){
            
            component.setBackground(new Color(252,147,147));
            return component;
        }
        
        if((row==0 && column < table.getColumnCount()-1-extraColumn) || column==0 && row < table.getRowCount()-1-extraRow){
            
            component.setBackground(Color.GREEN);
            component.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            
            if(row==0 && column ==0)
                component.setIcon(new javax.swing.ImageIcon(getClass().getResource("PENDIENTE DE AGREGAR IMAGEN")));
                component.setFont(new java.awt.Font("Tahoma",1,18));
            
            return component;
        }
        
        else if (row == table.getRowCount()-1-extraRow && column < table.getColumnCount()-1-extraColumn){
            
            component.setBackground(new Color(159, 237, 249));
            component.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            return component;
        }
        
        else if (column == table.getColumnCount()-1-extraColumn && row < table.getRowCount()-1){
            
            component.setBackground(Color.CYAN);
            component.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            return component;
        }
        
        else if(column == table.getColumnCount()-1-extraColumn && row == table.getRowCount()-1-extraColumn){
            return component;
        }
        
        if(extraColumn > 0 && (column == table.getColumnCount()-1 || row==table.getRowCount()-1)){
            
            component.setBackground(new Color(211,211,255));
            return component;
        }
        
        if(extraRow > 0 && (column == table.getColumnCount()-1 || row==table.getRowCount()-1))
            return component;
        
        JPanel panel = crearPanel(row, column, table);
        
        if(isSelected){
            
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,204)));
        }
        return panel;
        
    }

    private JPanel crearPanel(int row, int column, JTable table){
        
        JPanel panel = new JPanel();
        JLabel labelCantidad = new JLabel("");
        JLabel labelPrecio = new JLabel("");
        
        //me quede en la liena 94 en el if
     
        if(column > 0 && row > 0 && row < (table.getRowCount()-1-extraRow) && column < (table.getColumnCount()-1-extraColumn)){
            
            labelPrecio.setText(""+datos[row-1][column-1].getPrecio());
            
            panel.setLayout(null);
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
            
            panel.add(labelCantidad);
            panel.add(labelPrecio);
            panel.setBackground(Color.WHITE);
            
            labelPrecio.setBounds(3,3,64,22);
            labelCantidad.setBounds(3,25,64,22);
            labelCantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            
            labelPrecio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            labelPrecio.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            
            if(datos[row-1][column-1].getCantidad()>0){
                
                labelCantidad.setText(""+datos[row-1][column-1].getCantidad());
                panel.setBackground(new Color(253,137,160));
            }
            
            if(datos[row-1][column-1].isLleno() && datos[row-1][column-1].getCantidad() <=0 || datos[row -1][column-1].getPrecio().equals("-")){
                panel.setBackground(new Color(253,253,160));
            }
        }
        
        return panel;
    }

    
}
