
import java.util.ArrayList;



///////////////////////////////////////////////////////////////////////////////
///            Steady State Genetic Algorithm v1.0                          ///
///                by Enrique Alba, July 2000                               ///
///                                                                         ///
///   Executable: set parameters, problem, and execution details here       ///
///////////////////////////////////////////////////////////////////////////////



public class GA
{
  public static void main(String args[]) throws Exception
  {
      long inicio = System.currentTimeMillis();
      Thread.sleep(2000);
      //arg[0]: probabilidad de cruce
      //arg[1]: probabilidad de mutación
      //args[2]: línea inicio datos problema
    //int lineaInicio = Integer.parseInt(args[2]);
      ArrayList datos;
    if(args.length == 0){
        datos = ReadFile.readFile(45);
    }else{
        datos = ReadFile.readFile(Integer.parseInt(args[2]));
    }
    
    // PARAMETERS PROBLEM
    int    gn         = Integer.parseInt(((String[]) datos.get(0))[0]);                          // Gene number
    int    gl         = 1;                            // Gene length
    int    popsize    = 512;                          // Population size
    double pc;                                        // Crossover probability
    double pm;                                        // Mutation probability
    if(args.length == 0){
        pc = 1;
        pm = 1;
    //    pm = 1.0/(double)((double)gn*(double)gl);
    }else{
        pc = Double.parseDouble(args[0]);
        pm = Double.parseDouble(args[1]);
    }
    double tf         = Double.parseDouble(((String[]) datos.get(0))[2]);           // Target fitness being sought
    float MAX_ISTEPS;
    //Dependiendo del tipo de estudio a realizar se impone límite de evaluaciones o no
    if(args.length == 0 || Integer.parseInt(args[3]) == 1){
        MAX_ISTEPS = 50000;
    }else{
        MAX_ISTEPS = Float.POSITIVE_INFINITY;
    }
  
    
    
    Problem   problem;                             // The problem being solved

    // problem = new ProblemKnapSack(); 
    problem = new ProblemKnapSack();

    problem.set_geneN(gn);
    problem.set_geneL(gl);
    problem.set_target_fitness(tf);
    problem.set_datos(datos);

    // Una vez que se ha definido el objeto problem con los datos de partida se definen las características del algoritmo

    Algorithm ga;          // The ssGA being used
    ga = new Algorithm(problem, popsize, gn, gl, pc, pm);

    
    for (int step=0; step<MAX_ISTEPS; step++)
    {  
      ga.go_one_step();
      System.out.print(step); System.out.print("  ");
      System.out.println(ga.get_bestf() );

      if(     (problem.tf_known())                    &&
      (ga.get_solution()).get_fitness()>=problem.get_target_fitness()
      )
      { System.out.print("Solution Found! After ");
        System.out.print(problem.get_fitness_counter());
        System.out.println(" evaluations");
        
        
        break;
      }

    }

    // Print the solution
    for(int i=0;i<gn*gl;i++)
      System.out.print( (ga.get_solution()).get_allele(i) ); System.out.println();
    System.out.println((ga.get_solution()).get_fitness());
    
    
    long fin = System.currentTimeMillis();
         
        double tiempo = (double) ((fin - inicio)/1000);
         
        System.out.println(tiempo +" segundos"); 
 }
  
  

}
// END OF CLASS: Exe
