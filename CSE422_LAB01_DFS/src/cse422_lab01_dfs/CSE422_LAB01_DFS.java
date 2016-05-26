/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse422_lab01_dfs;

import java.util.Scanner;
import java.util.StringTokenizer;

/** http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Graph/dfs.html
 *
 * @author erfan
 */
public class CSE422_LAB01_DFS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner nemesis= new Scanner(System.in);
        String x = nemesis.nextLine();
        StringTokenizer st = new StringTokenizer(x);
        int numberOfNodes = Integer.parseInt(st.nextToken(" "));
        int numberOfEdges = Integer.parseInt(st.nextToken(" "));
        String start = nemesis.nextLine();
        String end = nemesis.nextLine();
        
        //take input
        inputAdjMat(numberOfNodes,numberOfEdges);
        
    }
    public static String[][] inputAdjMat(int numberOfNodes, int numberOfEdges){
        Scanner nemesis= new Scanner(System.in);
        
          String mat[][] = new String [numberOfNodes+1][numberOfNodes+1];
          
          for(int i=1;i<=numberOfNodes;i++){
            for(int j = 1;j <=numberOfNodes;j++){
              mat[i][j] = "0";
            }
          }
          mat[0][0] = " ";
          for(int i=0; i <numberOfEdges; i ++){
            String y = nemesis.nextLine();
            StringTokenizer st2 = new StringTokenizer(y);
            String first = st2.nextToken(",");
            String second = st2.nextToken(",");
                            
            boolean temp =false ;
            for(int j=1; j <=numberOfNodes; j++){
                if(mat[0][j] != null && mat[0][j].equals(first) ){
                    temp = true;
                }
            }
            if(temp == false){
                for(int j=1; j <=numberOfNodes; j++){
                    if(mat[0][j] == null ){
                            mat[0][j] = first;
                            mat[j][0] = first;
                            break;
                    }
                }
            }
            temp = false;
            for(int j=1; j <=numberOfNodes; j++){
                if(mat[0][j] != null && mat[0][j].equals(second) ){
                    temp = true;
                }
            }
            if(temp == false){
                for(int j=1; j <=numberOfNodes; j++){
                    if(mat[0][j] == null ){
                            mat[0][j] = second;
                            mat[j][0] = second;
                            break;
                    }
                }
            }
           for(int a=1; a<=numberOfNodes; a++){
                if(mat[0][a] != null && mat[0][a].equals(first)){
                     for(int b=1; b<=numberOfNodes; b++){
                      if(mat[0][b] != null && mat[0][b].equals(second)){
                           mat[a][b] = "1";
                           mat[b][a] =  "1";
                       }
                    }
                }
            } 
            
          }
          return mat;
    }
    public static void adjMatrixPrinter(String grph[][]){
        System.out.println("Printing the adj matrix");
        for(int i=0; i <grph[0].length; i++){
            for(int j = 0; j<grph[0].length; j++){
                 System.out.print(grph[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
