package exo4send;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

/**
 * @author Guillaume Gaudin
 */

public class Exo4Send {
    public static void main(String[] args) { 
        
        Model model = new Model();
        
        //On modélise les lettres S et M par des intVar dont les domaines sont restraints aux chiffres de 1 à 9 car S et M sont en tête de l'addition.
        IntVar S = model.intVar("S",1,9,true);
        IntVar M = model.intVar("M",1,9,true);
        
        //On modélise les lettres E, N, D, O, R, Y par des intVar dont les domaines sont restraints aux chiffres de 0 à 9.
        IntVar E = model.intVar("E",0,9,true);
        IntVar N = model.intVar("N",0,9,true);
        IntVar D = model.intVar("D",0,9,true);
        IntVar O = model.intVar("O",0,9,true);
        IntVar R = model.intVar("R",0,9,true);
        IntVar Y = model.intVar("Y",0,9,true);
        

        IntVar[] addition = new IntVar[]{
                        model.intScaleView(S,1000),
                        model.intScaleView(E, 100),
                        model.intScaleView(N, 10),
                        model.intScaleView(D, 1),
                        model.intScaleView(M,1000),
                        model.intScaleView(O, 100),
                        model.intScaleView(R, 10),
                        model.intScaleView(E, 1),
                        model.intScaleView(M,-10000),
                        model.intScaleView(O, -1000),
                        model.intScaleView(N, -100),
                        model.intScaleView(E, -10),
                        model.intScaleView(Y,-1)};
        
        model.sum(addition, "=", 0).post();
        
        model.allDifferent(S,M,E,N,D,O,R,Y).post();
        
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
        }
        else{
            System.out.println("Aucune solution trouvée.");
        }
    }
}