package exo3sel;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

/**
 * @author Guillaume Gaudin
 */

public class Exo3Sel {
    public static void main(String[] args) { 
        
        Model model = new Model();
        
        //On modélise le choix de condiment par un tableau de valeur : nom[0] pour le sel nom[1] pour la moutarde
        
        //Variable Barnabé
        IntVar[] choixB = model.intVarArray("choixB", 2,0,1);
        //Variable Casimir
        IntVar[] choixC = model.intVarArray("choixC", 2,0,1);
        //Variable Desiré
        IntVar[] choixD = model.intVarArray("choixD", 2,0,1);
        //Variable Ludovic
        IntVar[] choixL = model.intVarArray("choixL", 2,0,1);
        //Variable Martial
        IntVar[] choixM = model.intVarArray("choixM", 2,0,1);
        
        //Barnabé prend du sel si et seulement si Casimir ne prend que du sel ou que de la moutarde.
        model.ifThen( model.or( model.and( model.arithm( choixC[0], "=", 1), model.arithm( choixC[1], "=", 0)), model.and( model.arithm( choixC[0], "=", 0), model.arithm( choixC[1], "=", 1))), model.arithm( choixB[0], "=", 1));
        //Il prend de la moutarde si et seulement si, ou bien Désiré ne prend ni sel ni moutarde, ou bien Martial prend les deux.
        model.ifThen( model.or( model.and( model.arithm( choixD[0], "=", 0), model.arithm( choixD[1], "=", 0)), model.and( model.arithm( choixM[0], "=", 1), model.arithm( choixM[1], "=", 1))), model.arithm( choixB[1], "=", 1));
        //Casimir prend du sel si et seulement si, ou bien Barnabé ne prend qu'un des deux condiments, ou bien Martial n'en prend aucun.
        model.ifThen( model.or( model.or( model.and( model.arithm( choixB[0] , "=", 1),model.arithm( choixB[1], "=", 0)), model.and( model.arithm( choixB[0], "=", 0), model.arithm( choixB[1],"=",1))), model.and(model.arithm(choixM[0],"=",0),model.arithm(choixM[1],"=",0))), model.arithm(choixC[0],"=",1));
        //Il prend de la moutarde si et seulement si Désiré ou Ludovic prennent les deux condiments.
        model.ifThen( model.or( model.and( model.arithm( choixD[0], "=", 1), model.arithm( choixD[1], "=", 1)), model.and( model.arithm( choixL[0], "=", 1), model.arithm( choixL[1], "=", 1))), model.arithm( choixC[1], "=", 1));
        //Désiré prend du sel si et seulement si ou bien Barnabé ne prend aucun condiment, ou bien Casimir prend les deux.
        model.ifThen( model.or( model.and( model.arithm( choixB[0], "=", 0), model.arithm( choixB[1], "=", 0)), model.and( model.arithm( choixC[0], "=", 1), model.arithm( choixC[1], "=", 1))), model.arithm( choixD[0], "=", 1));
        //Il prend de la moutarde si et seulement si Ludovic ou Martial ne prennent ni sel ni moutarde.
        model.ifThen( model.or( model.and( model.arithm( choixL[0], "=", 0), model.arithm( choixL[1], "=", 0)), model.and( model.arithm( choixM[0], "=", 0), model.arithm( choixM[1], "=", 0))), model.arithm( choixD[1], "=", 1));
        //Ludovic prend du sel si et seulement si Barnabé ou Désiré ne prennent ni sel ni moutarde.
        model.ifThen( model.or( model.and( model.arithm( choixB[0], "=", 0), model.arithm( choixB[1], "=", 0)), model.and( model.arithm( choixD[0], "=", 0), model.arithm( choixD[1], "=", 0))), model.arithm( choixL[0], "=", 1));
        //Il prend de la moutarde si et seulement si Casimir ou Martial ne prennent ni sel, ni moutarde.
        model.ifThen( model.or( model.and( model.arithm( choixC[0], "=", 0), model.arithm( choixC[1], "=", 0)), model.and( model.arithm( choixM[0], "=", 0), model.arithm( choixM[1], "=", 0))), model.arithm( choixL[1], "=", 1));
        //Martial prend du sel si et seulement si Barnabé ou Ludovic prennent des deux condiments. 
        model.ifThen( model.or( model.and( model.arithm( choixB[0], "=", 1), model.arithm( choixB[1], "=", 1)), model.and( model.arithm( choixL[0], "=", 1), model.arithm( choixL[1], "=", 1))), model.arithm( choixM[0], "=", 1));
        //Il prend de la moutarde si et seulement si Casimir ou Désiré ne prennent qu'un seul condiment.
        model.ifThen( model.or( model.or( model.and( model.arithm( choixC[0], "=", 1), model.arithm( choixC[1], "=", 0)), model.and( model.arithm( choixC[0], "=", 0), model.arithm( choixC[1], "=", 1))), model.or(model.and( model.arithm( choixD[0], "=", 1), model.arithm( choixD[1], "=", 0)), model.and( model.arithm( choixD[0], "=", 0), model.arithm( choixD[1], "=", 1)))), model.arithm( choixM[1], "=", 1));
        
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
        }
        else{
            System.out.println("Aucune solution trouvée.");
        }
    }
}
