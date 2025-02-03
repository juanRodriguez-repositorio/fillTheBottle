/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameLogic;

/**
 *
 * @author kamus
 */
public class GameLogic {
    private static int[] gameRules=new int[]{3,0,6,2,5,1,4,7};
    public static boolean isCorrect(int[] answer){
        if(gameRules[answer[0]]==-1){
            throw new Error("Not initialized rules");
        }
        return gameRules[answer[0]]==answer[1];
        
    }
    
    
}
