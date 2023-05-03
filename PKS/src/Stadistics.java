
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sergio
 */

public class Stadistics {
	/**Calcula de los valores que contenga un vector de doubles facilitado por el usuario
	 *@param v		vector de doubles con los elemento introducidos por el usuario
	 *@return 		la media de los valores de v
	 *@see #media()
	 */
	public static double media(double[] v){
	   double res=0;
	   for(int i=0; i<v.length; i++){
	   	res+=v[i];
	   }
	   
	   return res/v.length;
	}
	/**Calcula la moda, el valor m�s repetido, del vector de doubles v introducido por el usuario
	 *@param v		vector de doubles con los elemento introducidos por el usuario
	 *@return 		valor m�s repetido del vector
	 *@see #moda()
	 */
	public static double moda(double[] v){
		int cont=0;
		double num=0;
		for(int i=0; i<v.length; i++){
		   int aux=0;	
		   for(int j=0; j<v.length; j++){
		      if(v[i]==v[j]) aux++;
		   }
		   if(aux>=cont){
		   	cont=aux;
		   	num=v[i];
		   } 
		 }
		return num;
	}
	/**Calcula la desviacion tipica del vector de doubles v introducido por el usuario
	 *@param v		vector de doubles con los elemento introducidos por el usuario
	 *@return 		desviacion tipica del vector
	 *@see #desviacion_tipica()
	 */
	public static double desviacion_tipica(double[] v){
		double res=Math.sqrt(varianza(v));
		return res;
	}
	/**Calcula la varianza del vector de doubles v introducido por el usuario
	 *@param v		vector de doubles con los elemento introducidos por el usuario
	 *@return 		la varianza del vector
	 *@see #varianza()
	 */
	public static double varianza(double[] v){
	   double m=media(v);
	   double sum=0;
	   for(int i=0; i<v.length; i++){
	      sum+=Math.pow(v[i],2.0);
	   }
	   
	   return sum/v.length-Math.pow(m,2.0);	
	}
        
        public static double mediana(double[] v){
            Arrays.sort(v);           
            double mediana;
            int mitad = v.length / 2;
            // Si la longitud es par, se deben promediar los del centro
            if (v.length % 2 == 0) {
                mediana = (v[mitad - 1] + v[mitad]) / 2;
            } else {
                mediana = v[mitad];
            }
            return mediana;
        }           
}

