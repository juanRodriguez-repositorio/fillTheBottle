/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SwingComponents;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class MoveButtons extends JPanel {
    private JButton[] buttons=new JButton[4]; // Botones de movimiento
    private Matrix matrix; // Referencia a la matriz
    private Color backgroundColor=new Color(50,50,50);
    private Color fontColor=new Color(240,240,240);
    private Color hoverColor=new Color(80,80,80);
    private Color pressedColor=new Color(100,100,100);
    

    public MoveButtons(Matrix matrix) {
        this.matrix = matrix;
        

        // Configurar el layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Crear los botones
        JButton upButton = new JButton("↑");
        JButton downButton = new JButton("↓");
        JButton leftButton = new JButton("←");
        JButton rightButton = new JButton("→");
        
        buttons[0]=upButton;
        buttons[1]=downButton;
        buttons[2]=leftButton;
        buttons[3]=rightButton;
        
        //colores boton
        for(JButton button:buttons){
            button.setBackground(backgroundColor);
            button.setForeground(fontColor);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setFocusable(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setPreferredSize(new Dimension(40,40));
            button.setMaximumSize(new Dimension(60,60));
            button.setMinimumSize(new Dimension(40,40));
            button.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    button.setBackground(hoverColor);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(backgroundColor);
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                // Cambia el color de fondo cuando el botón es presionado
                    //button.setBackground(pressedColor);
                    UIManager.put("Button.select",pressedColor);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                // Vuelve al color de hover si el mouse fue liberado dentro del botón
                if (button.getBackground() != pressedColor) {
                    //button.setBackground(hoverColor);
                    UIManager.put("Button.select",hoverColor);
                    } else {
                        //button.setBackground(backgroundColor);
                        UIManager.put("Button.select",backgroundColor);
                    }
                }
                
            });
            
        }
        
        
        // Agregar funcionalidad a los botones
        buttons[0].addActionListener(e -> matrix.moveUp());
        buttons[1].addActionListener(e -> matrix.moveDown());
        buttons[2].addActionListener(e -> matrix.moveLeft());
        buttons[3].addActionListener(e -> matrix.moveRight());

        
        gbc.insets = new Insets(3, 3, 3, 3); 
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;

        // Botón Arriba
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(buttons[0], gbc);

        // Botón Izquierda
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttons[2], gbc);

        // Botón Derecha
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(buttons[3], gbc);

        // Botón Abajo
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(buttons[1], gbc);
        
         // Agregar eventos de teclado usando InputMap y ActionMap
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");

        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix.moveUp();
            }
        });
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix.moveDown();
            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix.moveLeft();
            }
        });
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix.moveRight();
            }
        });
    }
}
