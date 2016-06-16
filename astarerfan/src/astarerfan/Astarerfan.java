/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarerfan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Erfan
 */
public class Astarerfan {
static int number_of_cities =0;
static List<String> nodes = new ArrayList<String>();
static boolean visited[];
static double nodeDistances[][]; 
static double h[] ;
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      // TODO code application logic here
      
      String start = "";
      String end = "";
      
      
      
      BufferedReader br = null;	      
            try {
                String sCurrentLine;

                br = new BufferedReader(new FileReader("input.txt"));
                
                String [] number_of_vr_edg= br.readLine().split(" ");
                nodeDistances = new double[Integer.valueOf(number_of_vr_edg[0])][Integer.valueOf(number_of_vr_edg[1])];
                visited = new boolean[Integer.valueOf(number_of_vr_edg[0])];
                
                number_of_cities = Integer.valueOf(number_of_vr_edg[0]);
                
                h = new double[Integer.valueOf(number_of_vr_edg[0])];
                start = br.readLine();
                end = br.readLine();
                
                while ((sCurrentLine = br.readLine()) != null) {
                    String temp_edge_nodes[] = sCurrentLine.split(" ");


                    String first = temp_edge_nodes[0];
                    String second = temp_edge_nodes[1];
                    
                    if(temp_edge_nodes.length ==3){
                       
                       double distance = Double.valueOf(temp_edge_nodes[2]);
                       if(!nodes.contains(first)){
                          nodes.add(first);
                       }
                       if(!nodes.contains(second)){
                          nodes.add(second);
                       }
                       nodeDistances[nodes.indexOf(first)][nodes.indexOf(second)] = distance;
                       nodeDistances[nodes.indexOf(second)][nodes.indexOf(first)] = distance;
                    }else{
                       h[nodes.indexOf(first)] = Double.valueOf(second);
                    }
                  /*
                    for(int i = 0; i < nodes.size();i++){
                       System.out.println(nodes.get(i));
                    }*/
                    
                    
                }
            } catch (IOException e) {
                System.out.println("no file bro");
            }
            d(nodes.indexOf(start), nodes.indexOf(end));
            
   }
   public static void d(int start, int goal){
      int parents[] = new int[number_of_cities];
      double distance[]=new double[number_of_cities];
      Stack<String> nemesis=new Stack<String>();
      for (int i = 0; i < distance.length; i++) {
         if(i!=start){
            parents[i]=-999999999;
            distance[i]=999999999;
         }
     }
      parents[start]=start;
      distance[start]=0; 
      nemesis.push(nodes.get(start));
      visited[start]=true;
        
      
      int i=start;
      int disturbed = number_of_cities;
      while(disturbed!=1){
         for (int j = 0; j < number_of_cities; j++) { 
            if(nodeDistances[i][j]>0 && visited[j]!=true){
                if(distance[j]>nodeDistances[i][j]+distance[i]){
                    distance[j]=nodeDistances[i][j]+distance[i];
                    parents[j]=i;
                }
            }
         }
         double min=99999999999.999;
         int min_=0;
         for (int korn = 0; korn < number_of_cities; korn++) {
             if(nodeDistances[i][korn]>0  && visited[korn]!=true){
                 h[korn]=h[korn]+distance[korn];
                 
                 if(min>h[korn]){
                     min_=korn;
                     min=distance[korn];
                 }
             }
         }

         disturbed--;
         i=min_;
         visited[i]=true;
         nemesis.push(nodes.get(i));

        }
      
        int k=goal;
        Stack<String> temp2 = new Stack<String>();
        temp2.push(nodes.get(k));
        
        while(parents[k]!=start){
            temp2.push(nodes.get(parents[k]));
            k=parents[k];
        }
       
        
        temp2.push(nodes.get(start));
        
        while(!temp2.empty()){
            System.out.print(temp2.pop()+"-");
        }
        System.out.println();
        System.out.println("Distance: "+distance[goal]);
        }
       
   
   
   
}
