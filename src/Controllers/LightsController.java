/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author kamus
 */
public class LightsController {
   private static int[][] colorsForPosition=new int[][]{new int[]{0,0,0},new int[]{0,0,1},new int[]{0,1,0}
     ,new int[]{0,1,1},new int[]{1,0,0},new int[]{1,0,1},new int[]{1,1,0},new int[]{1,1,1}};
   
    public static int[] getColors(int column){
        if(column>7 || column<0){
            throw new IllegalArgumentException("Bad argument for getting colors");
        }
        return colorsForPosition[column];
    }
    

    
}
