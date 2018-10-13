package exo2coloriage;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

/**
 *
 * @author Jean-Gabriel Fischbach
 */
public class Exo2Coloriage {

    public static void main(String[] args) {
        
        /*
        On décide d'associer à chaque couleur un entier afin de simplifier le modèle et accélérer les comparaisons de couleurs de zones. On pose arbitrairement:
        Bleu = 1
        Rouge = 2
        Jaune = 3
        Vert = 4
        */
        
        Model model = new Model();
        
        /*
        On déclare l'ensemble des zones sous la forme d'un entier correspond à la couleur avec laquelle chacune sera coloriée.
        */
        IntVar z1 = model.intVar("z1",1,4,true);
        IntVar z2 = model.intVar("z2",1,4,true);
        IntVar z3 = model.intVar("z3",1,4,true);
        IntVar z4 = model.intVar("z4",1,4,true);
        IntVar z5 = model.intVar("z5",1,4,true);
        IntVar z6 = model.intVar("z6",1,4,true);
        IntVar z7 = model.intVar("z7",1,4,true);
        IntVar z8 = model.intVar("z8",1,4,true);
        IntVar z9 = model.intVar("z9",1,4,true);
        IntVar z10 = model.intVar("z10",1,4,true);
        IntVar z11 = model.intVar("z11",1,4,true);
        IntVar z12 = model.intVar("z12",1,4,true);
        IntVar z13 = model.intVar("z13",1,4,true);
        IntVar z14 = model.intVar("z14",1,4,true);
        
        /*
        On établit alors la liste des contraintes qui nous permettront de nous assurer que deux zones adjacentes ne seront pas coloriées de la même couleur:
        avec ce modèle, cela revient à une liste de comparaisons d'entiers en s'assurant qu'ils soient différents si les zones concernées doivent être adjacentes.
        */
        
        model.post(model.notAllEqual(z1,z7));
        model.post(model.notAllEqual(z1,z9));
        model.post(model.notAllEqual(z1,z10));
        model.post(model.notAllEqual(z1,z11));
        model.post(model.notAllEqual(z1,z12));
        model.post(model.notAllEqual(z1,z13));
        
        model.post(model.notAllEqual(z2,z8));
        model.post(model.notAllEqual(z2,z12));
        model.post(model.notAllEqual(z2,z14));
        
        model.post(model.notAllEqual(z3,z7));
        model.post(model.notAllEqual(z3,z10));
        model.post(model.notAllEqual(z3,z14));
        
        model.post(model.notAllEqual(z4,z9));
        model.post(model.notAllEqual(z4,z11));
        model.post(model.notAllEqual(z4,z14));
        
        model.post(model.notAllEqual(z5,z8));
        model.post(model.notAllEqual(z5,z11));
        model.post(model.notAllEqual(z5,z12));
        
        model.post(model.notAllEqual(z6,z7));
        model.post(model.notAllEqual(z6,z13));
        model.post(model.notAllEqual(z6,z14));
        
        model.post(model.notAllEqual(z7,z10));
        model.post(model.notAllEqual(z7,z13));
        model.post(model.notAllEqual(z7,z14));
        
        model.post(model.notAllEqual(z8,z12));
        
        model.post(model.notAllEqual(z9,z10));
        model.post(model.notAllEqual(z9,z11));
        model.post(model.notAllEqual(z9,z14));
        
        model.post(model.notAllEqual(z10,z14));
        
        model.post(model.notAllEqual(z11,z12));
        
        model.post(model.notAllEqual(z12,z13));
        model.post(model.notAllEqual(z12,z14));
        
        model.post(model.notAllEqual(z13,z14));
        
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
        }
        else{
            System.out.println("Aucune solution trouvée.");
        }
    }   
}