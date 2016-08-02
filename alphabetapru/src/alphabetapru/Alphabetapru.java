/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetapru;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author erfan
 */
public class Alphabetapru {

    /**
     * @param args the command line arguments
     */
    
    public  static int skips;
    public static int numberOfTurns;
    public static int total_nodes;
    public static int depth;
    public static int numberOfChilds;
    public static int min_value;
    public static int max_value;
    public static int terminal_States;
    
    public static int inputvals[];
    public static int trees[];
    public static int c;
    
    
    public static void main(String[] args) {
        skips=0;
        fileReader();
        
        trees= new int[total_nodes];
        
        
        // Number of terminal states
        terminal_States=(int)Math.pow(numberOfChilds, 2);
        // Number of terminal states is equals to number of child per node to the power number of depth
        inputvals= new int[terminal_States];
        System.out.println("Depth: "+depth);
        System.out.println("Branch: "+numberOfChilds);
        System.out.println("Terminal States: "+terminal_States);
        System.out.print("Set:{");
        generateLeafNodes();
        System.out.println(" }");
        System.out.println("Max Amount Counted:"+abc(0, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
        System.out.println("Number of comparisons  :"+terminal_States);
        System.out.println("Number of comparisons Skiped :"+skips);
    }
        public static void fileReader(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
            numberOfTurns=Integer.parseInt(br.readLine());
            depth=2*numberOfTurns;
            numberOfChilds=Integer.parseInt(br.readLine()); 
            total_nodes=(int)(Math.pow(numberOfChilds, (depth+1))-1)/(numberOfChilds-1);
            String temp[]= br.readLine().split(" ");
            min_value=Integer.parseInt(temp[0]);
            max_value=Integer.parseInt(temp[1]);
        }catch(IOException | NumberFormatException e){
            System.out.println(e);
        }
    }
        
    public static void generateLeafNodes(){
         for (int i = total_nodes-1; i>=(total_nodes-terminal_States); i--) {
           trees[i]=min_value + (int)(Math.random() * ((max_value - min_value) + 1));
             System.out.print(trees[i]+",");
        }
        
       
    }
    
    public static int abc(int node, int depth, int aplha, int beta, boolean maxi){
        if(depth==0){  
            return trees[node];
        }
        if(maxi){
            int v=Integer.MIN_VALUE;
            int child=3*node+1;
            int i=0;
            while(i<numberOfChilds) {
                v=(int)Math.max(v, abc(child, depth-1, aplha , beta, false));
                aplha=(int)Math.max(aplha, v);
                if(beta<=aplha){
                    skips++;
                    break;
                }
                child++;
                i++;
            }
            return v; 
        }
        if(!maxi){
            int v=Integer.MAX_VALUE;
            int child=3*node+1;
            int i=0;
            while(i<numberOfChilds){
                v= (int)Math.min(v, abc(child, depth-1, aplha , beta, true));
                beta=(int)Math.min(beta, v);
                if(beta<=aplha){
                    skips++;
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
