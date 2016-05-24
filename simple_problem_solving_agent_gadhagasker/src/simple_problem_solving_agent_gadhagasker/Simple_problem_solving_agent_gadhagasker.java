/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple_problem_solving_agent_gadhagasker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        System.out.println("baby gadhagasker: ababababab ( enter your ques )");
        percept p = new percept(sc.nextLine());
        action returned_action =  simple_problem_solving_agent(p);
        System.out.println("baby gadhagasker says:" + returned_action);
    }
    public static action simple_problem_solving_agent(percept p){
         action tobe_returned_action;
         
        List<action> seq = new ArrayList<action>();
        goal goal_var;
        state state_var = new state("gadhagasker");
        problem problem_var = new problem();
        
        state_var = update_state(state_var, p);
        
        if (seq.isEmpty()){
            goal_var = formulate_goal(state_var);
            problem_var = formulate_problem(state_var, goal_var);
            seq = search(problem_var);
        }
        tobe_returned_action = new action(seq.get(0).action_var);
        
        return tobe_returned_action;
    }
    
    public static state update_state(state s, percept p){
        state tobe_returned_state = new state();
        if(s.state_var.equals("gadhagasker")){
            tobe_returned_state.state_var = s.state_var+","+p.percept_string;
        }
        return tobe_returned_state;
    }
    public static goal formulate_goal(state s){
        goal x = new goal();
        if(s.state_var.contains("gadhagasker")){
            x.goal_var= "reply";
        }
        return x;
    }
    public static problem formulate_problem(state s, goal g){
        problem y = new problem();
        if(s.state_var.contains("gadhagasker") && g.goal_var.equals("reply")){
            y.problem_var = "replythesamethingthathasbeenasked";
        }
        return y;
    }
    public static List<action> search(problem p){
        
        List<action> tobereturned = new ArrayList<action>();
        
        action temp = new action();
        tobereturned.add(temp);
        
        return tobereturned;
    }
}
