package exo1monnaie;

import java.util.Scanner;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;

/**
 * @author Jean-Gabriel Fischbach
 */
public class Exo1Monnaie {

    /*
    On déclare les réserves de la machine en pièces:
    e1 : réserve de pièces de 1€
    e2 : réserve de pièces de 2€
    c10 : réserve de pièces de 10 centimes
    c20 : réserve de pièces de 20 centimes
    c50 : réserve de pièces de 50 centimes
    */
    private static final int e1 = 50;
    private static final int e2 = 50;
    private static final int c10 = 50;
    private static final int c20 = 50;
    private static final int c50 = 50;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Model model = new Model();
        
        Scanner reader = new Scanner(System.in);
        /*
        On demande une somme à rendre en centimes pour ne manipuler que des entiers dans le solveur.
        Il serait possible de demander un float puis de le transformer en centimes mais il faudrait s'assurer qu'il n'y a
        que deux chiffres après la virgule. Demander une somme en centimes directement est plus simple.
        */
        System.out.println("Entrez la somme à rendre (centimes) : ");
        int rendu = reader.nextInt();
        reader.close();
        
        /*
        On déclare la liste des variables du CSP prenant la valeur d'un entier entre 0 et la réserve de pièces concernée.
        */
        IntVar nbE1 = model.intVar("nbE1",0,e1,true);
        IntVar nbE2 = model.intVar("nbE2",0,e2,true);
        IntVar nbC10 = model.intVar("nbC10",0,c10,true);
        IntVar nbC20 = model.intVar("nbC20",0,c20,true);
        IntVar nbC50 = model.intVar("nbC50",0,c50,true);
        
        // La variable quantiteTotale constituée de la somme des nombres des pièces à rendre nous servira pour trouver la solution optimale.
        IntVar quantiteTotale = model.intVar("quantiteTotale",0,e1+e2+c10+c20+c50,true);
        
        /*
        On crée une array de vues sur nos variables CSP qui nous permettent de les multiplier par leurs valeurs respectives 
        afin de créer une contrainte sur la somme à rendre.
        */
        IntVar[] sommeRendue = new IntVar[]{model.intScaleView(nbE1,100),
                                          model.intScaleView(nbE2,200),
                                          model.intScaleView(nbC10,10),
                                          model.intScaleView(nbC20,20),
                                          model.intScaleView(nbC50,50)};
        
        IntVar[] quantiteTotaleContrainte = new IntVar[]{nbE1,nbE2,nbC10,nbC20,nbC50};
        
        Constraint contrainteValeurs = model.sum(sommeRendue,"=",rendu);
        Constraint contrainteQt = model.sum(quantiteTotaleContrainte,"=",quantiteTotale);
        
        model.post(contrainteValeurs);
        model.post(contrainteQt);
        
        Solution solution = model.getSolver().findOptimalSolution(quantiteTotale, false); // false pour minimiser, true pour maximiser
        if(solution != null){
            if (solution.getIntVal(nbE2) > 0){
                System.out.println("Pièces de 2€ : "+solution.getIntVal(nbE2));
            }
            if (solution.getIntVal(nbE1) > 0){
                System.out.println("Pièces de 1€ : "+solution.getIntVal(nbE1));
            }
            if (solution.getIntVal(nbC50) > 0){
                System.out.println("Pièces de 50c : "+solution.getIntVal(nbC50));
            }
            if (solution.getIntVal(nbC20) > 0){
                System.out.println("Pièces de 20c : "+solution.getIntVal(nbC20));
            }
            if (solution.getIntVal(nbC10) > 0){
                System.out.println("Pièces de 10c : "+solution.getIntVal(nbC10));
            }
        }
        else{
            System.out.println("Aucune solution trouvée.");
        }
    }
    
}
