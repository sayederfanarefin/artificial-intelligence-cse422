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
    public int trees[];
    public int c;

    public Alphabetapruning(int turns_count, int number_of_childs ,int min, int max){
        pruning_counts = 0;
        this.turns_count = turns_count;
        this.depth = 2 * turns_count;
        this.number_of_childs = number_of_childs;
        this.total_nodes = (int) (Math.pow(number_of_childs, (this.depth + 1)) - 1) / (number_of_childs - 1);
        min_value = min;
        max_value = max;
    }
    public void generateLeafNodes() {
        for (int i = total_nodes - 1; i >= (total_nodes - terminal_States); i--) {
            trees[i] = min_value + (int) (Math.random() * ((max_value - min_value) + 1));
            System.out.print(trees[i] + ",");
        }

    }

    public int abc(int node, int depth, int aplha, int beta, boolean maxi) {
        if (depth == 0) {
            return trees[node];
        }
        if (maxi) {
            int v = Integer.MIN_VALUE;
            int child = 3 * node + 1;
            int i = 0;
            while (i < number_of_childs) {
                v = (int) Math.max(v, abc(child, depth - 1, aplha, beta, false));
                aplha = (int) Math.max(aplha, v);
                if (beta <= aplha) {
                    pruning_counts++;
                    break;
                }
                child++;
                i++;
            }
            return v;
        }
        if (!maxi) {
            int v = Integer.MAX_VALUE;
            int child = 3 * node + 1;
            int i = 0;
            while (i < number_of_childs) {
                v = (int) Math.min(v, abc(child, depth - 1, aplha, beta, true));
                beta = (int) Math.min(beta, v);
                if (beta <= aplha) {
                    pruning_counts++;
                    break;
                }
                child++;
                i++;
            }
            return v;
        }

        return -1;
    }

}
