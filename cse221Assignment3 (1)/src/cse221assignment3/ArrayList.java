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
public class ArrayList {
    int size;
    Node al[];
    ArrayList(int s){
    size = s;
    al = new Node[size];
    }
    public int size(){
    return size;
    }
}
