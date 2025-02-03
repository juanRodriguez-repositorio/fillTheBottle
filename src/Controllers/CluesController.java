/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author kamus
 */
import java.util.List;
import java.util.ArrayList;
import SwingComponents.Clues;

public class CluesController {
    private List<String> clues; // Lista de pistas
    private int currentClueIndex; // Índice de la pista actual
    private Clues cluesComponent; // Referencia al componente Clues

    public CluesController(Clues cluesComponent) {
        this.clues = new ArrayList<>();
        this.currentClueIndex = 0;
        this.cluesComponent = cluesComponent;
        loadClues(); // Cargar las pistas
    }

    // Método para cargar las pistas
    private void loadClues() {
        // ***** AGREGA AQUÍ TUS PISTAS *****
        clues.add("Pista 1: Extremo izquierdo → derecha = verde; extremo derecho → izquierda → derecha = verde;"
                + " al revés → centro → derecha = naranja");
        clues.add("Pista 2: Extremo izquierdo → derecha = naranja; extremo derecho → izquierda → izquierda = naranja;"
                + " todas iguales = verdadero");
        clues.add("Pista 3: Si extremo izquierdo se mueve hacia extremo derecho y centro se mueve hacia extremo izquierdo,"
                + " entonces : naranja,verde,verde");
        clues.add("Pista 4: Al revés = Al derecho; Color de preferencia = naranja;"
                + " todas iguales = falso");
        clues.add("Pista 5: Centro → izquierda = verde; centro → derecha = verde; todas iguales = falso");
        clues.add("Pista 6: Si extremo izquierdo se mueve hacia extremo derecho y centro se mueve hacia extremo izquierdo,"
                + " entonces : extremo izquierdo → derecha = naranja; centro → derecha = naranja; todas iguales = falso");
        clues.add("Pista 7: Centro = naranja; extremos = colores diferentes;"
                + " extremo izquierdo diferente de centro");
        clues.add("Pista 8: Check!");

        // Cargar pistas en el componente Clues
        cluesComponent.loadClue(clues.get(0));
    }

    // Marcar la pista actual como completada y pasar a la siguiente
    public  void showNextOne() {
        if (currentClueIndex < clues.size()-1) {
            currentClueIndex++;
            cluesComponent.loadClue(clues.get(currentClueIndex));
        }else{
            cluesComponent.loadClue("Felicitaciones! Llenaste la botella!");
        }
    }

    // Reiniciar las pistas
    public  void resetClues() {
        currentClueIndex = 0;
        cluesComponent.loadClue(clues.get(0));
    }
}
