/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo1monnaie;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;

/**
 *
 * @author jean
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
        
        int rendu = 10;
        
        /*
        On déclare la liste des variables du CSP prenant la valeur d'un entier entre 0 et la réserve de pièces concernée.
        */
        IntVar nbE1 = model.intVar("nbE1",0,e1,true);
        IntVar nbE2 = model.intVar("nbE1",0,e2,true);
        IntVar nbC10 = model.intVar("nbE1",0,c10,true);
        IntVar nbC20 = model.intVar("nbE1",0,c20,true);
        IntVar nbC50 = model.intVar("nbE1",0,c50,true);
        
        /*
        On crée une array de vues sur nos variables CSP qui nous permettent de les multiplier par leurs valeurs respectives 
        afin de créer une contrainte sur la somme à rendre.
        */
        IntVar[] variables = new IntVar[]{model.intScaleView(nbE1,100),
                                          model.intScaleView(nbE2,200),
                                          model.intScaleView(nbC10,10),
                                          model.intScaleView(nbC20,20),
                                          model.intScaleView(nbC50,50)};
        
        Constraint contrainte = model.sum(variables,"=",rendu);
        
        model.post(contrainte);
        
        model.setObjective(model.MINIMIZE, (Variable) contrainte);
    }
    
}
