/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cse221assignment3;

/**
 *
 * @author Erfan
 */
public class Cse221Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DFS tsk1 = new DFS();
        System.out.println(tsk1.count(tsk1.inputAdjMat()));  
        //String [][] f = tsk1.inputAdjMat();
        //tsk1.adjMatrixPrinter(f);
        //System.out.println(tsk1.findIndex(f, "3"));
        //tsk1.adjMatrixPrinter(tsk1.inputAdjMat());
    }
    
}
