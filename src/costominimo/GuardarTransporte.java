/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costominimo;

import java.io.File;
import java.io.Serializable;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class GuardarTransporte  implements Serializable{

    //atributos
    public String[][] coeficiente;
    public String metodo;
    boolean filaArtificial;
    boolean colArtificial;
    
    public GuardarTransporte(String metodo, boolean filaArtificial, boolean colArtificial){
        
        this.metodo = metodo;
        this.filaArtificial = filaArtificial;
        this.colArtificial = colArtificial;
    }

    public String[][] getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(String[][] coeficiente) {
        this.coeficiente = coeficiente;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public boolean isFilaArtificial() {
        return filaArtificial;
    }

    public void setFilaArtificial(boolean filaArtificial) {
        this.filaArtificial = filaArtificial;
    }

    public boolean isColArtificial() {
        return colArtificial;
    }

    public void setColArtificial(boolean colArtificial) {
        this.colArtificial = colArtificial;
    }
    
    public void mostrarDatosTable(JTable table){
        
        int fila = table.getRowCount();
        int columna = table.getColumnCount();
        
        coeficiente = new String[fila][columna];
        
        for(int i = 0; i<fila; i++){
            for(int j=0; j<columna; j++){
                coeficiente [i][j] = ""+table.getValueAt(i, j);
            }
        }
    }
    
    public void guardarArchivo(String ruta, String nom, GuardarTransporte g){
        int op=0;
        
        if(!ruta.substring(ruta.length()-4, ruta.length()).equalsIgnoreCase(".abner")){
            ruta = ruta+".abner";
        }
        
        File f = new File(ruta);
        
        if(f.exists()){
            op = JOptionPane.showConfirmDialog(null, nom+"Ya existe \n Deseas reemplazarlo?", "Guardar", JOptionPane.WARNING_MESSAGE);
            
        }
        
        if(op==0){
            EscribirLeer archivo = new EscribirLeer();
            archivo.escribirFichero(ruta,g);
        }
    }
}
