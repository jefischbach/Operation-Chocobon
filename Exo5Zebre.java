package exo5zebre;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

/**
 *
 * @author Jean-Gabriel Fischbach
 */
public class Exo5Zebre {
    
    public static void main(String[] args) {
        
        Model model = new Model();
        
        /*
        On prend le problème à l'envers: au lieu de créer une variable par homme, on crée une variable pour chaque spécificité (couleur de maison, nationalité, animal, boisson, cigarette).
        On attribue alors une valeur de 1 à 5 correspondant à chaque homme (ou plutôt chaque maison), dans l'ordre dans lequel se trouve leurs maisons.
        */
        
        IntVar norvegien = model.intVar("norvegien",1,5,true);
        IntVar anglais = model.intVar("anglais",1,5,true);
        IntVar espagnol = model.intVar("espagnol",1,5,true);
        IntVar ukrainien = model.intVar("ukrainien",1,5,true);
        IntVar japonais = model.intVar("japonais",1,5,true);
        
        IntVar bleue = model.intVar("bleue",1,5,true);
        IntVar rouge = model.intVar("rouge",1,5,true);
        IntVar verte = model.intVar("verte",1,5,true);
        IntVar jaune = model.intVar("jaune",1,5,true);
        IntVar blanche = model.intVar("blanche",1,5,true);
        
        IntVar lait = model.intVar("lait",1,5,true);
        IntVar cafe = model.intVar("cafe",1,5,true);
        IntVar the = model.intVar("the",1,5,true);
        IntVar vin = model.intVar("vin",1,5,true);
        IntVar eau = model.intVar("eau",1,5,true);
        
        IntVar cheval = model.intVar("cheval",1,5,true);
        IntVar renard = model.intVar("renard",1,5,true);
        IntVar zebre = model.intVar("zebre",1,5,true);
        IntVar escargot = model.intVar("escargot",1,5,true);
        IntVar chien = model.intVar("chien",1,5,true);
        
        IntVar kools = model.intVar("kools",1,5,true);
        IntVar chesterfields = model.intVar("chesterfields",1,5,true);
        IntVar oldgolds = model.intVar("oldgolds",1,5,true);
        IntVar cravens = model.intVar("cravens",1,5,true);
        IntVar gitanes = model.intVar("gitanes",1,5,true);
        
        // Variable constante qui nous servira à simplifier les déclarations de contraintes
        IntVar un = model.intVar("un",1);
        
        /*
        Ces contraintes assurent que toutes les variables d'un même groupe (couleur, boisson...) ont une valeur différente.
        */
        model.post(model.allDifferent(norvegien,anglais,espagnol,ukrainien,japonais));
        model.post(model.allDifferent(bleue,rouge,verte,jaune,blanche));
        model.post(model.allDifferent(lait,cafe,the,vin,eau));
        model.post(model.allDifferent(cheval,renard,zebre,escargot,chien));
        model.post(model.allDifferent(kools,chesterfields,oldgolds,cravens,gitanes));
        
        /*
        On traduit ensuite les contraintes de l'exercice dans le CSP
        */
        // Le norvégien habite la première maison
        model.post(model.arithm(norvegien, "=", 1));
        // La maison à coté de celle du norvégien est bleue
        model.post(model.arithm(norvegien, "+", un, "=", bleue));
        // L'habitant de la troisième maison boit du lait
        model.post(model.arithm(lait, "=", 3));
        // L'anglais habite la maison rouge
        model.post(model.arithm(anglais, "=", rouge));
        // L'habitant de la maison verte boit du café
        model.post(model.arithm(verte, "=", cafe));
        // L'habitant de la maison jaune fume des kools
        model.post(model.arithm(jaune, "=", kools));
        // La maison blanche se trouve juste après la verte
        model.post(model.arithm(verte, "+", un, "=", blanche));
        // L'espagnol a un chien
        model.post(model.arithm(espagnol, "=", chien));
        // L'ukrainien boit du thé
        model.post(model.arithm(ukrainien, "=", the));
        // Le japonais fume des cravens
        model.post(model.arithm(japonais, "=", cravens));
        // Le fumeur de old golds a un escargot
        model.post(model.arithm(escargot, "=", oldgolds));
        // Le fumeur de gitaines boit du vin
        model.post(model.arithm(gitanes, "=", vin));
        // Le voisin du fumeur de Chesterfields a un renard
        model.post(model.arithm(chesterfields, "=", renard));
        // Le voisin du fumeur de kools a un cheval
        model.post(model.arithm(kools, "=", cheval));
        
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
            System.out.println("L'homme habitant la maison n°"+solution.getIntVal(eau)+" boit de l'eau.");
            System.out.println("Le zèbre appartient à l'homme habitant la maison n°"+solution.getIntVal(zebre)+" .");
        }
        else{
            System.out.println("Aucune solution trouvée.");
        }
    }
    
}
