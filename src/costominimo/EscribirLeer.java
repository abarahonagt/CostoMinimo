/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costominimo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class EscribirLeer {
    
    public void escribirFichero(String fichero, Object o){
        
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
            oos.writeObject(o);
            oos.close();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public ArrayList leeFichero(String fichero) throws IOException{
        
        File archivoEntrada;
        FileInputStream canalEntrada = null;
        ObjectInputStream objetoEntrada = null;
        ArrayList<Guardar> aux = new ArrayList<Guardar>();
        boolean fin = true;
        
        try{ 
            archivoEntrada = new File(fichero);
            canalEntrada = new FileInputStream(archivoEntrada);
            objetoEntrada= new ObjectInputStream(canalEntrada);
            
            do{
                try{
                    aux.add((Guardar) objetoEntrada.readObject());
                }
                catch(IOException | ClassNotFoundException exc){
                    fin = false;
                }
            }
            while(fin==true); 
            objetoEntrada.close();
            canalEntrada.close();
        }
        catch(IOException e){
            System.out.print("Error al extraer");
        }
        
        return aux;
    }
        
}
