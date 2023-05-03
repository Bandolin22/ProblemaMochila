
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sergio
 */
public class ReadFile {
    
   public static ArrayList readFile(int lineaInicio) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      ArrayList datos =new ArrayList();

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura cómoda (disponer del metodo readLine()).
         archivo = new File ("C:\\Users\\sig_o\\Documents\\NetBeansProjects\\PKS\\mknap1.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         int contador=1;
         while(!" ".equals(linea=br.readLine())||contador<lineaInicio){
             if (contador>=lineaInicio){
                 
                datos.add(linea.trim().split(" "));
             }
         contador++;
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return datos;
   }
}

