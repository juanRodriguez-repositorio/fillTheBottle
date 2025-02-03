/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SwingComponents;

/**
 *
 * @author kamus
 */
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import Controllers.MatrixController;
import Controllers.CluesController;
import Controllers.TimerController;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.JDialog;
import javax.swing.ImageIcon;


public class SubmitButton extends JButton {
    private Matrix matrix;
    private CluesController cluesController;
    private Window window;
    private TimerController timerController;
    private  Color backgroundColor=new Color(0, 180, 230);
    private Color hoverColor=new Color(0, 160, 200);
    private Color pressedColor=new Color(0, 130, 170);
    
    
    public SubmitButton(Matrix matrix, CluesController cluesController,Window window) {
        super("Submit");
        setBackground(backgroundColor);
        setForeground(new Color(240,240,240));
        setBorder(BorderFactory.createEmptyBorder());
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(true);
        setText("Llenar !");
        setPreferredSize(new Dimension(80,40));
        setMaximumSize(new Dimension(100,60));
        setMinimumSize(new Dimension(60,30));
        addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    setBackground(hoverColor);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(backgroundColor);
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                // Cambia el color de fondo cuando el botón es presionado
                    //button.setBackground(pressedColor);
                    UIManager.put("Button.select",pressedColor);
                    setPreferredSize(new Dimension(getWidth() - 4, getHeight()-2));
                    revalidate();
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    // Vuelve al color de hover si el mouse fue liberado dentro del botón
                    if (getBackground() != pressedColor) {
                        //button.setBackground(hoverColor);
                        UIManager.put("Button.select",hoverColor);
                        } else {
                            //button.setBackground(backgroundColor);
                            UIManager.put("Button.select",backgroundColor);
                        }
                    setPreferredSize(new Dimension(getWidth()+4, getHeight()+2));
                    revalidate();
                 
                }
                
                
            });
        this.matrix = matrix;
        this.cluesController= cluesController;
        this.window=window;
        this.timerController=window.getTimerController();
        
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = new Timer(300, new ActionListener() {  // Retraso de 200ms
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Llamar a la función checkAnswer() después del retraso
                        checkAnswer();
                    }
                });
                timer.setRepeats(false); // Evitar que el Timer se repita
                timer.start();
            }
        });
        
    
    }
    
    private void checkAnswer() {
    
        int[] selectedCell = matrix.getCell(); // Obtener la celda seleccionada [fila, columna]
        int selectedRow = selectedCell[0];
        int selectedCol = selectedCell[1];

        if (MatrixController.getGameIsOver()) {
            if (MatrixController.getGameStatus()) {
                showCustomDialog("Juego terminado, llenaste la botella !");
            } else {
                showCustomDialog("Juego terminado, la botella queda vacía...");
            }
            return;
        }

        if (MatrixController.isInRow(selectedRow)) {
            if (MatrixController.isCorrect(new int[]{selectedRow, selectedCol})) {
                if (selectedRow == 7) {
                    showCustomDialog("Felicitaciones, llenaste la botella !");
                    MatrixController.setGameOver();
                    MatrixController.setGameStatus(true);
                } else {
                    showCustomDialog("Correcto! Vamos a la siguiente pista!");
                }
                window.flipWaterJug(true);
                matrix.fillCells(selectedCell, window);
                cluesController.showNextOne();
            } else {
                showCustomDialog("Incorrecto! Intenta de nuevo");
                if (timerController.registerFailedAttempt()) {
                    MatrixController.setGameOver();
                    MatrixController.setGameStatus(false);
                }
            }
        } else {
            showCustomDialog("Opps... Debes completar la fila número: " + (MatrixController.getActualRow() + 1));
        }
    }

    private void showCustomDialog(String message) {
        // Crear el JOptionPane con el mensaje
        UIManager.put("OptionPane.buttonFont", new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
    
        // Crear el diálogo
        JDialog dialog = optionPane.createDialog("Mensaje");
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/bottleIcon.png"));  // Asegúrate de poner el camino correcto del ícono
        dialog.setIconImage(icon.getImage());

        // Obtener el panel de los botones de JOptionPane
        java.awt.Component[] components = optionPane.getComponents();
    
        // Buscar el panel que contiene los botones y personalizarlos
        for (java.awt.Component component : components) {
            if (component instanceof javax.swing.JPanel) {
                javax.swing.JPanel buttonPanel = (javax.swing.JPanel) component;
            
                // Iterar sobre los componentes dentro del panel de botones
                for (java.awt.Component btnComponent : buttonPanel.getComponents()) {
                    if (btnComponent instanceof JButton) {
                            JButton button = (JButton) btnComponent;
                            button.setBorderPainted(false);  // Eliminar borde
                            button.setFocusable(false);  // Deshabilitar enfoque
                            button.setFocusPainted(false);  // Deshabilitar el enfoque visual
                            button.setBackground(new Color(0, 180, 230));  
                    }
                }
            }
        }

        // Mostrar el diálogo
        dialog.setVisible(true);
    }
}

