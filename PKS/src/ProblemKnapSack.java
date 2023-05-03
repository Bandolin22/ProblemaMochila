


public class ProblemKnapSack extends Problem {
    
    
    
    @Override
    public double Evaluate(Individual Indiv) {
        return KNAPSACK(Indiv) ;
    
    }

    private double KNAPSACK(Individual indiv) {
        //Se toman los datos del problema de la mochila específico
        String[] valores = (String[]) datos.get(1);
        String[][] coeficientesRestricciones = new String[datos.size()-3][valores.length];
        for (int i=0;i<datos.size()-3;i++){
            coeficientesRestricciones[i] = (String[]) datos.get(i+2);
        }
        String[] resultadosRestricciones = (String[]) datos.get(datos.size()-1); 
        
        double f=0;
        double suma;
        //Se toma el cromosoma que representa una solución y se evalúa su fitness
        for (int j=0; j<datos.size()-3;j++){
            suma=0;
            //Primero se comprueba que dicho cromosoma, y por tanto, cada uno de sus genes (objetos) cumplen las restricciones impuestas 
            for (int i=0;i<valores.length;i++){
                if (j==0){
                    f+=indiv.get_allele(i)*Double.parseDouble(valores[i]);
                }
                suma+=indiv.get_allele(i)*Double.parseDouble(coeficientesRestricciones[j][i]);
            }
            //Si no se cumple la restricción se aplica un fitness nulo y se termina de evaluar la solución
            if (suma>Double.parseDouble(resultadosRestricciones[j])){
                f=0;
                break;
            }
        }
        //En el caso de que se hayan cumplido todas las restricciones se almacena el fitness con su cromosoma correspondiente
        indiv.set_fitness(f); 
        return f;
    }
}

