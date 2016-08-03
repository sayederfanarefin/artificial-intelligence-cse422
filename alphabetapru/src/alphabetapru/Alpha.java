package alphabetapru;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author erfan
 */
public class Alpha {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        alphabetapruning abp = new alphabetapruning();
        
        abp.pruning_counts=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
            abp.turns_count=Integer.parseInt(br.readLine());
            abp.depth=2*abp.turns_count;
            abp.number_of_childs=Integer.parseInt(br.readLine()); 
            abp.total_nodes=(int)(Math.pow(abp.number_of_childs, (abp.depth+1))-1)/(abp.number_of_childs-1);
            String temp[]= br.readLine().split(" ");
            abp.min_value=Integer.parseInt(temp[0]);
            abp.max_value=Integer.parseInt(temp[1]);
        }catch(IOException | NumberFormatException e){
            System.out.println(e);
        }
        
        abp.trees= new int[abp.total_nodes];
        
        
        // Number of terminal states
        abp.terminal_States=(int)Math.pow(abp.number_of_childs, 2);
        // Number of terminal states is equals to number of child per node to the power number of depth
        abp.inputvals= new int[abp.terminal_States];
        System.out.println("Depth: "+abp.depth);
        System.out.println("Branch: "+abp.number_of_childs);
        System.out.println("Terminal States: "+abp.terminal_States);
        System.out.print("Set:{");
        
        abp.generateLeafNodes();
        
        System.out.println(" }");
        System.out.println("Max Amount Counted:"+abp.abc(0, abp.depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
        System.out.println("Number of comparisons  :"+abp.terminal_States);
        System.out.println("Number of comparisons Skiped :"+abp.pruning_counts);
    }
        
    
}
