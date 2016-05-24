/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple_problem_solving_agent_gadhagasker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erfan
 */
public class Simple_problem_solving_agent_gadhagasker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static action simple_problem_solving_agent(percept p){
         action tobe_returned_action;
         
        List<action> seq = new ArrayList<action>();
        goal goal_var;
        state state_var = new state("gadhagasker");
        problem problem_var;
        
        state_var = update_state(state_var, p);
        
        if (seq.isEmpty()){
            goal_var = formulate_goal(state_var);
            
        }
        return tobe_returned_action;
    }
    
    public static state update_state(state s, percept p){
        state tobe_returned_state = new state();
        if(s.state_var.equals("gadhagasker")){
            tobe_returned_state.state_var = s.state_var;
        }
        return tobe_returned_state;
    }
    public static goal formulate_goal(state s){
        goal x = new goal();
        if(s.state_var.equals("gadhagasker")){
            x.goal_var= "reply";
        }
        return x;
    }
    public static problem formulate_problem(state s, goal g){
    }
    public static List<action> search(problem p){
        
    }
}
