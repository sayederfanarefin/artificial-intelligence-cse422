/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetapru;

/**
 *
 * @author erfan
 */
public class Alphabetapruning {

    public int pruning_counts;
    public int turns_count;
    public int total_nodes;
    public int depth;
    public int number_of_childs;
    public int min_value;
    public int max_value;
    public int terminal_States;
    public int inputvals[];
    public int[] tree;
    public int c;

    public Alphabetapruning(int turns_count, int number_of_childs ,int min, int max){
        pruning_counts = 0;
        this.turns_count = turns_count;
        this.depth = 2 * turns_count;
        this.number_of_childs = number_of_childs;
        this.total_nodes = (int) (Math.pow(number_of_childs, (this.depth + 1)) - 1) / (number_of_childs - 1);
        min_value = min;
        max_value = max;
        tree = new int[total_nodes];
        terminal_States = (int) Math.pow(number_of_childs, 2);
         // Number of terminal states is equals to number of child per node to the power number of depth
        inputvals = new int[terminal_States];
        
    }
    public void buildNodes() {
        for (int i = total_nodes - 1; i >= (total_nodes - terminal_States); i--) {
            tree[i] = min_value + (int) (Math.random() * ((max_value - min_value) + 1));
            System.out.print(tree[i] + ",");
        }

    }

    public int compare(int node, int depth, int alpa, int beta, boolean maxim) {
        if (depth == 0) {
            return tree[node];
        }
        if (maxim) {
            int v = Integer.MIN_VALUE;
            int child = 3 * node + 1;
            
            for (int i = 0;i < number_of_childs;i++) {
                v = (int) Math.max(v, compare(child, depth - 1, alpa, beta, false));
                alpa = (int) Math.max(alpa, v);
                if (beta <= alpa) {
                    pruning_counts=pruning_counts + number_of_childs - (i+1);
                    break;
                }
                child++;
                
            }
            return v;
        }
        if (!maxim) {
            int v = Integer.MAX_VALUE;
            int child = 3 * node + 1;
            for (int i = 0;i < number_of_childs;i++) {
                v = (int) Math.min(v, compare(child, depth - 1, alpa, beta, true));
                beta = (int) Math.min(beta, v);
                if (beta <= alpa) {
                    pruning_counts = pruning_counts + number_of_childs - (i+1);
                    break;
                }
                child++;
                
            }
            return v;
        }

        return -1;
    }

}
