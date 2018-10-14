package exo3sel;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.BoolVar;

/**
 * @author Guillaume Gaudin
 */

public class Exo3Sel {
    public static void main(String[] args) { 
        
        Model model = new Model();
        
        //On modélise le v de condiment par un tableau de valeur : nom[0] pour le sel nom[1] pour la moutarde
        
        //Variable Barnabé
        BoolVar[] vB = model.boolVarArray("vB", 2);
        //Variable Casimir
        BoolVar[] vC = model.boolVarArray("vC", 2);
        //Variable Desiré
        BoolVar[] vD = model.boolVarArray("vD", 2);
        //Variable Ludovic
        BoolVar[] vL = model.boolVarArray("vL", 2);
        //Variable Martial
        BoolVar[] vM = model.boolVarArray("vM", 2);
        
        //Barnabé prend du sel si et seulement si Casimir ne prend que du sel ou que de la moutarde.
        BoolVar C1 = model.or(model.and(vC[0], vC[1].not()).reify(), model.and(vC[0].not(), vC[1]).reify()).reify();
        model.or(model.and(C1, vB[0]).reify(), model.and(C1.not(), vB[0].not()).reify()).post();
        
        //Il prend de la moutarde si et seulement si, ou bien Désiré ne prend ni sel ni moutarde, ou bien Martial prend les deux.
        BoolVar C2 = model.or(model.and(vD[0].not(), vD[1].not()).reify(), model.and(vM[0], vM[1]).reify()).reify();
        model.or(model.and(C2, vB[1]).reify(), model.and(C2.not(), vB[1].not()).reify()).post();
        
        //Casimir prend du sel si et seulement si, ou bien Barnabé ne prend qu'un des deux condiments, ou bien Martial n'en prend aucun.
        BoolVar C3 = model.or( model.or(model.and(vB[0], vB[1].not()).reify(),model.and(vB[0].not(), vB[1]).reify()).reify(), model.and(vM[0].not(), vM[1].not()).reify()).reify();
        model.or(model.and(C3, vC[0]).reify(), model.and(C3.not(), vC[0].not()).reify()).post();
        
        //Il prend de la moutarde si et seulement si Désiré ou Ludovic prennent les deux condiments.
        BoolVar C4 = model.or(model.and(vD[1], vD[0]).reify(), model.and(vL[1], vL[0]).reify()).reify();
        model.or(model.and(C4, vC[1]).reify(), model.and(C4.not(), vC[1].not()).reify()).post();
        
        //Désiré prend du sel si et seulement si ou bien Barnabé ne prend aucun condiment, ou bien Casimir prend les deux.
        BoolVar C5 = model.or(model.and(vB[0].not(), vB[1].not()).reify(), model.and(vC[0], vC[1]).reify()).reify();
        model.or(model.and(C5, vD[0]).reify(), model.and(C5.not(), vD[0].not()).reify()).post();
        
        //Il prend de la moutarde si et seulement si Ludovic ou Martial ne prennent ni sel ni moutarde.
         BoolVar C6 = model.or(model.and(vL[1].not(), vL[0].not()).reify(), model.and(vM[1].not(), vM[0].not()).reify()).reify();
        model.or(model.and(C6, vD[1]).reify(), model.and(C6.not(), vD[1].not()).reify()).post();
        
        //Ludovic prend du sel si et seulement si Barnabé ou Désiré ne prennent ni sel ni moutarde.
        BoolVar C7 = model.or(model.and(vB[1].not(), vB[0].not()).reify(), model.and(vD[1].not(), vD[0].not()).reify()).reify();
        model.or(model.and(C7, vL[0]).reify(), model.and(C7.not(), vL[0].not()).reify()).post();
        
        //Il prend de la moutarde si et seulement si Casimir ou Martial ne prennent ni sel, ni moutarde.
        BoolVar C8 = model.or(model.and(vC[0].not(), vC[1].not()).reify(), model.and(vM[0].not(), vM[1].not()).reify()).reify();
        model.or(model.and(C8, vL[1]).reify(), model.and(C8.not(), vL[1].not()).reify()).post();
        
        //Martial prend du sel si et seulement si Barnabé ou Ludovic prennent des deux condiments. 
        BoolVar C9 = model.or(model.and(vB[1], vB[0]).reify(), model.and(vL[0], vL[1]).reify()).reify();
        model.or(model.and(C9, vM[0]).reify(), model.and(C9.not(), vM[0].not()).reify()).post();
        
        //Il prend de la moutarde si et seulement si Casimir ou Désiré ne prennent qu'un seul condiment.
        BoolVar C10 = model.or(model.and(model.or(vC[1],vC[0]).reify(), model.and(vC[1],vC[0]).reify().not()), model.and(model.or(vD[1], vD[0]).reify(), model.and(vD[1], vD[0]).reify().not())).reify();
        model.or(model.and(C10, vM[1]).reify(), model.and(C10.not(), vM[1].not()).reify()).post();
        
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
        }
        else{
            System.out.println("Aucune solution trouvée.");
        }
    }
}
