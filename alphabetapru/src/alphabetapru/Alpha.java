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

        try {
            BufferedReader nemesis = new BufferedReader(new FileReader("input.txt"));
            int temp_turns_count = Integer.parseInt(nemesis.readLine());

            int temp_number_of_childs = Integer.parseInt(nemesis.readLine());

            String temp[] = nemesis.readLine().split(" ");
            int temp_min_value = Integer.parseInt(temp[0]);
            int temp_max_value = Integer.parseInt(temp[1]);

            Alphabetapruning abp = new Alphabetapruning(temp_turns_count, temp_number_of_childs, temp_min_value, temp_max_value);
            System.out.println("Depth: " + abp.depth);
            System.out.println("Branch: " + abp.number_of_childs);
            System.out.println("Terminal States: " + abp.terminal_States);
            System.out.print("Set:{");

            abp.buildNodes();

            System.out.println(" }");
            System.out.println("Max Amount Counted:" + abp.compare(0, abp.depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
            System.out.println("Number of comparisons  :" + abp.terminal_States);
            System.out.println("Number of comparisons Skiped :" + abp.pruning_counts);

        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }

    }

}
