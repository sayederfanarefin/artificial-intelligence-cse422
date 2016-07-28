/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidirectional_fianl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Arshad
 */
public class Bidirectional_fianl {

    /**
     * @param args the command line arguments
     */
    
    static int number_of_cities;
    static int number_of_roads;
    static String start_city;
    static String end_city;
    static String[] city_name_list;
    static int graph_matrix[][];
    static boolean[] visited_flag;
    static int tuntuni[]=new int[2];
    static Object[] thread_saver=new Object[2];
    static int l[]=new int[2];
    

public static void main(String[] args) throws IOException {
    
        BufferedReader nemesis=new BufferedReader(new FileReader("input.txt"));
        String line=nemesis.readLine();
        String temp[]=line.split(",");
        number_of_cities=Integer.parseInt(temp[0]);
        number_of_roads=Integer.parseInt(temp[1]);
        city_name_list=new String[number_of_cities];
      
        visited_flag=new boolean[number_of_cities];
        graph_matrix=new int[number_of_cities][number_of_cities];
        
        start_city=nemesis.readLine();
        end_city=nemesis.readLine();

        int count=0;

        for (int i = 0; i <number_of_roads; i++) {
            String temp2=nemesis.readLine();
            String roads[]=temp2.split(",");
            
            int row=count;
           int r=getIndex(city_name_list, roads[0]);
           if(r==-1){
               city_name_list[count]=roads[0];
               count++;
           }else{
               row=r;
           }
           int col=count;
           r=getIndex(city_name_list, roads[1]);
           if(r==-1){
               city_name_list[count]=roads[1];
               count++;
           }else{
               col=r;
           }
            
            graph_matrix[row][col]=graph_matrix[col][row]=Integer.parseInt(roads[2]);

        }
        
        threads tuni = new threads("forward",getIndex(city_name_list, start_city), getIndex(city_name_list, end_city), 0);
        tuni.start();
        
        threads koushik = new threads("backward",getIndex(city_name_list, end_city), getIndex(city_name_list, start_city), 1);
        koushik.start();
    }
    
    
    public static void intersection(int point, int position, threads t, int level){
        
        tuntuni[point]=position;
        l[point]=level;
        thread_saver[point]=t;
        int i=Math.abs(point-1);
        
        if(tuntuni[point]==tuntuni[i]){
            
            
            Object p[]=((threads)thread_saver[0]).print(position);
            Object p1[]=((threads)thread_saver[1]).print(position);
            for (int j = p.length-1; j>0; j--) {
                System.out.print(p[j]+"->");
            }
            for (int j = 0; j<p1.length-1; j++) {
                System.out.print(p1[j]+"->");
            }
            
            System.out.print(p1[p1.length-1]);
            
            
            System.out.println();
            System.out.println("Length: "+(l[point]+l[i]));
            System.out.print("City: "+city_name_list[position]+" Direction: "+ ((threads)thread_saver[i]).t_name);
            System.out.println(" Roads: "+(l[i]));
            System.exit(0);
        }
    }
   
     
    
    public static int getIndex(String []list, String val){
       
        for (int i = 0; i < list.length; i++) {
            
           if(list[i]!=null){
                if( list[i].equalsIgnoreCase(val)){
                   
                return i;     
            }
           }
        }
        return -1;
    }
    
}
