/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse422_lab01_dfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 *
 * @author erfan
 */
public class CSE422_LAB01_DFS {

    /**
     * @param args the command line arguments
     */
    static List<ArrayList<String>> results = new ArrayList<ArrayList<String>>(); 
    static int indexx = 0;
    static String adj[][];
    static String start;
    static String end;
    static int numberOfBrokenLines;
    static int numberOfNodes;
    public static void main(String[] args) {
        // TODO code application logic here
        BufferedReader br = null;	      
            try {
                    String sCurrentLine;

                    br = new BufferedReader(new FileReader("input.txt"));
                    String [] number_of_vr_edg= br.readLine().split(",");
                    numberOfNodes = Integer.valueOf(number_of_vr_edg[0]);
                    adj = new String [numberOfNodes+1][numberOfNodes+1];
                    start = br.readLine();
                    end = br.readLine();
                    numberOfBrokenLines = Integer.valueOf(br.readLine());
                    while ((sCurrentLine = br.readLine()) != null) {
                        String temp_edge_nodes[] = sCurrentLine.split(",");
                        
                        
                        String first = temp_edge_nodes[0];
                        String second = temp_edge_nodes[1];

                        boolean temp =false ;
                        for(int j=1; j <=numberOfNodes; j++){
                            if(adj[0][j] != null && adj[0][j].equals(first) ){
                                temp = true;
                            }
                        }
                        if(temp == false){
                            for(int j=1; j <=numberOfNodes; j++){
                                if(adj[0][j] == null ){
                                        adj[0][j] = first;
                                        adj[j][0] = first;
                                        break;
                                }
                            }
                        }
                        temp = false;
                        for(int j=1; j <=numberOfNodes; j++){
                            if(adj[0][j] != null && adj[0][j].equals(second) ){
                                temp = true;
                            }
                        }
                        if(temp == false){
                            for(int j=1; j <=numberOfNodes; j++){
                                if(adj[0][j] == null ){
                                        adj[0][j] = second;
                                        adj[j][0] = second;
                                        break;
                                }
                            }
                        }
                       for(int a=1; a<=numberOfNodes; a++){
                            if(adj[0][a] != null && adj[0][a].equals(first)){
                                 for(int b=1; b<=numberOfNodes; b++){
                                  if(adj[0][b] != null && adj[0][b].equals(second)){
                                       adj[a][b] = "1";
                                       adj[b][a] =  "1";
                                   }
                                }
                            }
                        } 
                    }
            } catch (IOException e) {
                System.out.println("no file");
            } finally {
                try {
                        if (br != null)br.close();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
                for(int k=1;k<adj[0].length;k++){
                    for(int l=1;l<adj[0].length;l++){
                        if(adj[k][l] == null){
                            adj[k][l] = "0";
                        }
                    }
                }
            }
        
        adjMatrixPrinter(adj);
        ArrayList<String> x= traverser(start);
       for(int i =0; i < x.size();i++){
           System.out.print(x.get(i));
           System.out.print(" - ");
       }
       System.out.println("");
    }
    /*
    public static String[] bachha_finder(String bap){
        
    }*/

    public static ArrayList<String> traverser(String node){
        System.out.println("Debug: entering traverser "+ node);
        ArrayList<String> tobereturned = new ArrayList<String>();
      
        int node_idx = findIndex(adj,node);
        for(int f = 1; f<adj[0].length;f++){
             
            if(adj[node_idx][f].equals("1")){
                
                 System.out.println("Debug: found child");
                 
                adj[node_idx][f] = adj[node_idx][f]+"visited";
                if(adj[f][0].equals(end)){
                    System.out.println("*******Debug: found end from: "+ node);
                    tobereturned.add(adj[f][0]);
                    System.out.println("*******Debug: size of list: "+ tobereturned.size()+" added: "+adj[f][0]);
                }else{
                    System.out.println("Debug: end not found, start traverser: "+ adj[f][0]);
                    tobereturned = traverser(adj[f][0]); 
                }
                if(tobereturned.size()>0){
                    tobereturned.add(node);
                    System.out.println("*******Debug: size of list: "+ tobereturned.size()+ " last added: "+node);
                }
            }
            //add this to results
            results.add(tobereturned);
           //System.out.println("looper: "+f);
        }
        
        return tobereturned;
    }
    /*
    public static String[] childFinder(String node){
        
    }*/
    public static void adjMatrixPrinter(String grph[][]){
        System.out.println("Printing the adj matrix");
        for(int i=0; i <grph[0].length; i++){
            for(int j = 0; j<grph[0].length; j++){
                if(grph[i][j]!=null){
                 System.out.print(grph[i][j].charAt(0));
                 System.out.print(" ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    
    public static int findIndex(String G[][],String s){
        int temp = -1;
        for(int j=1; j <G[0].length; j++){
                if(G[0][j].equals(s)){
                    temp = j;
                }
            }
        return temp;
    }
}
