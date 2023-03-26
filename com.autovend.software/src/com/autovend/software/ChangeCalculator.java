package com.autovend.software;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class ChangeCalculator{

    private static HashMap<Problem, ArrayList<Integer> > solvedProblems = new HashMap<>();;
    
    private static void solveSubProblem(Problem currentProblem)
    {
        // base case, if sum is 0 then we can reach the sum no matter what the set
        if(currentProblem.sum == 0)
        {
            solvedProblems.put(currentProblem , new ArrayList<Integer>() );
            return;
        }
        // if sum is less than 0 we cannot reach the sum with positive integers
        if(currentProblem.sum < 0)
        {

            solvedProblems.put(currentProblem , new ArrayList<Integer>(Arrays.asList(-1)));
            return;
        }
        // if current set is empty then we cannot reach the positive sum
        ArrayList<Integer> currentSet = currentProblem.set;
        if(currentSet.size() == 0)
        {
            solvedProblems.put(currentProblem, new ArrayList<Integer>(Arrays.asList(-1)));
            return;
        }
        // sub problem where the item is used, remove the item and reduce the target sum by that items value
        Integer itemValue = currentSet.get(currentSet.size()-1);
        ArrayList<Integer> subSet = (ArrayList)currentSet.clone();
        subSet.remove(subSet.size()-1);
        
        Problem hasItem = new Problem(subSet, currentProblem.sum - itemValue);
        // if it doesn't contain the key solve it
        if(!solvedProblems.containsKey(hasItem))
        {
            solveSubProblem(hasItem);
        }
        // get the result
        ArrayList<Integer> hasItemRes = solvedProblems.get(hasItem);
        // if list isnt and has a -1 at the first index, it is not solvable
        // otherwise add the item and set that as the solution
        if(hasItemRes.size() == 0 || hasItemRes.get(0) != -1)
        {
            hasItemRes.add(itemValue);
            solvedProblems.put(currentProblem,hasItemRes);
            return;
        }
        // sub problem where item is not used, sum remains the same
        Problem doesNotContainItem = new Problem(subSet,currentProblem.sum);
        // see if solution has been found, if not solve for solution
        if(!solvedProblems.containsKey(doesNotContainItem))
        {
            solveSubProblem(doesNotContainItem);
        }
       
        ArrayList<Integer> noItemRes = solvedProblems.get(doesNotContainItem);
         // if list isnt and has a -1 at the first index, it is not solvable
        // otherwise set the solution to the subset 
        if(noItemRes.size() == 0 || noItemRes.get(0) != -1)
        {
            solvedProblems.put(currentProblem,noItemRes);
            return;
        }
        // no solution found
        solvedProblems.put(currentProblem , new ArrayList<Integer>(Arrays.asList(-1)));
        return;
    }
    public static ArrayList<Integer> calculateChange(Problem mainProblem)
    {
        solvedProblems = new HashMap<Problem, ArrayList<Integer>>();
        solveSubProblem(mainProblem);
        return solvedProblems.get(mainProblem);

    }
}