/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cse221assignment3;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 *
 * @author Erfan
 */
public class DFS {
    public static boolean flag[];
    public static int counting =0;
    
public void DFSVisit(String G[][], int u){
 flag[u] =true;
 int temp = adj(G,u);
 if(temp != -1){
   flag[u] = true;
   DFSVisit(G,temp);
 }
 flag[u]=true;
}
public int adj(String G[][],int n){
    int nodeIndex = -1;
    for(int i=1;i<G[n].length;i++){
      if( flag[i] == false && (G[n][i].equals("1"))){
          nodeIndex = i;
          break;
          
      }
    }
return nodeIndex;
}
    public int count(String G[][]){
        int size= G[0].length;
        flag = new boolean[size+1];
          for(int j =0; j<=size; j++){
              flag[j]= false;
          }
    for(int i=1; i <= size;i++){
     if(flag[i] == false){
        // int fu =findIndex(G, G[0][i]);
       DFSVisit(G,i);
       counting = counting+1;
     }
  }
        
        return counting;
    }
    public int findIndex(String G[][],String s){
        int temp = -1;
        for(int j=1; j <G[0].length; j++){
                if(G[0][j].equals(s)){
                    temp = j;
                }
            }
        return temp;
    }
    public String[][] inputAdjMat(){
        Scanner nemesis= new Scanner(System.in);
        String x = nemesis.nextLine();
        StringTokenizer st = new StringTokenizer(x);
          int numberOfNodes = Integer.parseInt(st.nextToken());
          int numberOfEdges = Integer.parseInt(st.nextToken());
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
             String first = st2.nextToken();
            String second = st2.nextToken();
                            
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
    public void adjMatrixPrinter(String grph[][]){
        System.out.println("Printing the adj matrix");
        for(int i=0; i <grph[0].length; i++){
            for(int j = 0; j<grph[0].length; j++){
                 System.out.print(grph[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
