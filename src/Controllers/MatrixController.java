/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import GameLogic.GameLogic;
/**
 *
 * @author kamus
 */
public class MatrixController {
    private static int actualRow=0;
    private static int actualCol=0;
    private static boolean isGameOver=false;
    private static boolean isGameWon=false;
    public static boolean isCorrect(int[] cell){  
        if(cell[1]>7 || cell[1]<0 || cell[0]>7 || cell[0]<0){
            throw new IllegalArgumentException("Bad argument for prove");
        }
        return GameLogic.isCorrect(cell);
    }
    public static boolean isInRow(int row){
        return row==actualRow;
    }
    public static int getActualRow(){
        return actualRow;
    }
    public static int[][] getCellToFill(int[] cell){
        if(cell[1]>7 || cell[1]<0 || cell[0]>7 || cell[0]<0){
            throw new IllegalArgumentException("Bad argument for prove");
        }
        int columnDif=cell[1]-actualCol;
        int columnDifAbs=Math.abs(cell[1]-actualCol);
        int[][] cellsToPaint=new int[columnDifAbs==0?columnDifAbs+1:columnDifAbs][];
        if(columnDif>0){
            for(int i=0;i<columnDif;i++){
                cellsToPaint[i]=new int []{actualRow,actualCol+i+1};
            }
        }else if(columnDif<0){
            for (int i = 0; i < columnDifAbs; i++) {
                cellsToPaint[i] = new int[]{actualRow, actualCol - i-1};
            }
            
        }else{
            cellsToPaint[0]=new int[]{actualRow,actualCol};
        }
        actualRow++;
        actualCol=cell[1];
        return cellsToPaint;
    }
    public static void setGameOver(){
        isGameOver=true;
    }
    public static boolean getGameIsOver(){
        return isGameOver;
    }
    public static void setGameStatus(boolean isWon){
        isGameWon=isWon;
    }
    public static boolean getGameStatus(){
        return isGameWon;
    }
    
    
}
