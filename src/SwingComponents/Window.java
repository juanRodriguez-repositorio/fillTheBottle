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
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Controllers.CluesController;
import Controllers.TimerController;


public class Window extends JFrame {

    private Matrix matrixComponent;
    private Lights lightsComponent;
    private Clues cluesComponent;
    private MoveButtons moveButtonsComponent;
    private SubmitButton submitButtonComponent; // Añadimos el SubmitButton
    private JLabel waterJugLabel;
    private JLabel bottleLabel;
    private TimerLabel timerLabel;
    private TimerController timerController;


    public Window() {
        // Configuración básica de la ventana
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/bottleIcon.png"));
        setIconImage(icon.getImage());
        setTitle("llena la botella!");
        setSize(900, 600);  // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centra la ventana

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(30,30,30));
        mainPanel.setOpaque(true);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        // Crear los componentes y controllers
        
        lightsComponent = new Lights(); 
        timerLabel = new TimerLabel();
        timerController = new TimerController(timerLabel);
        matrixComponent = new Matrix(lightsComponent);  
        cluesComponent = new Clues();    
        
        // Crear MoveButtons y pasarle la instancia de la matriz
        moveButtonsComponent = new MoveButtons(matrixComponent);

        // Crear SubmitButton y pasarle la instancia de matrix y cluesController
        submitButtonComponent = new SubmitButton(matrixComponent, new CluesController(cluesComponent),this);
     
        //Cargar imagen de la jarra
        
        ImageIcon waterJug = new ImageIcon(getClass().getResource("/resources/waterJug.png")); // Ruta de la imagen
        waterJugLabel = new JLabel(waterJug);
        JPanel imageJugPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Asegura alineación a la izquierda
        imageJugPanel.setBackground(new Color(30,30,30)); // Fondo oscuro como el resto
        imageJugPanel.add(waterJugLabel);
        imageJugPanel.setPreferredSize(new Dimension(90,90));
        
        //imagen de la botella.
        ImageIcon bottleImage=new ImageIcon(getClass().getResource("/resources/emptyBottle.png"));
        bottleLabel=new JLabel(bottleImage);
        //crear el contenedor de los botenes moves y la botella
        JPanel movesAndBottlePanel = new JPanel(new GridBagLayout());
        movesAndBottlePanel.setBackground(new Color(30, 30, 30)); // Fondo oscuro
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Espaciador flexible para empujar la botella a la derecha
        gbc.gridx = 0; 
        gbc.weightx = 1.0;  // Expande este espacio
        gbc.fill = GridBagConstraints.HORIZONTAL;  
        movesAndBottlePanel.add(Box.createHorizontalGlue(), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar los botones
        moveButtonsComponent.setBorder(BorderFactory.createEmptyBorder(0,80,0,0));
        movesAndBottlePanel.add(moveButtonsComponent, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END; // Alinear la botella a la derecha
        movesAndBottlePanel.add(bottleLabel, gbc);

        // Panel izquierdo (Matriz)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30,30,30));
        leftPanel.setOpaque(true);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(imageJugPanel);  
        leftPanel.add(Box.createVerticalStrut(2));
        leftPanel.add(matrixComponent);
        leftPanel.add(movesAndBottlePanel);
        moveButtonsComponent.setBackground(new Color(30,30,30));
        matrixComponent.setMinimumSize(new Dimension(200, 200));  
        matrixComponent.setMaximumSize(new Dimension(400, 400)); 
        
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        leftPanel.setMinimumSize(new Dimension(500, 600));

        // Panel derecho (contiene las luces en la parte superior, las pistas en la parte inferior y el botón Submit)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(30,30,30));
        rightPanel.setOpaque(true);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); // Usamos BoxLayout para control total de las secciones

        // Panel superior de la derecha (Luces)
        JPanel lightsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        lightsPanel.setBackground(new Color(30,30,30));
        lightsPanel.add(lightsComponent);
        lightsComponent.setBackground(new Color(30,30,30));
        lightsPanel.add(Box.createHorizontalStrut(20)); // Espaciado entre luces y temporizador
        lightsPanel.add(timerLabel);
        lightsPanel.setMinimumSize(new Dimension(200, 100));  // Tamaño controlado para las luces
        rightPanel.add(lightsPanel);

        
        //definir el label explicativo
        JLabel cluesExplanationLabel = new JLabel("<html>"
        + "<style>"
        + "body { font-family: Arial; font-size: 10px; color: #989898; }"
        + "</style>"
        + "<font color='#64aaff'> Importante! </font> el signo ' → ' significa mirar hacia... desde la luz indicada, por ejemplo: extremo izquierdo → derecha; esto se refiere a la luz que está a la derecha de la luz en el extremo izquierdo.<br>"
        + "Ejemplo: Centro → izquierda = verde; <br>"
        + "Esto significa que la luz que está a la izquierda de la luz central es de color verde.<br>"
        + "En la tranlación de luces, la dirección se toma con respecto a la traslación anterior."
        +" Buena suerte!"
        + "</html>");
        JPanel cluesExplanationPanel = new JPanel();
        cluesExplanationPanel.setLayout(new BorderLayout());
        cluesExplanationPanel.add(cluesExplanationLabel,BorderLayout.SOUTH);
        cluesExplanationPanel.setBackground(new Color(30,30,30));
        cluesExplanationPanel.setOpaque(true);
        cluesExplanationPanel.setBorder(new EmptyBorder(0,15,25,15));
        // Panel inferior de la derecha (Pistas)
        JPanel cluesPanel = new JPanel();
        cluesPanel.setBackground(new Color(30,30,30));
        cluesPanel.setOpaque(true);
        cluesPanel.setLayout(new BoxLayout(cluesPanel, BoxLayout.Y_AXIS));
        cluesPanel.add(Box.createVerticalStrut(10));
        cluesPanel.add(cluesComponent);
        cluesPanel.setBorder(BorderFactory.createLineBorder(new Color(240,240,240),1));
        cluesPanel.add(cluesExplanationPanel,BorderLayout.SOUTH);
        cluesPanel.setPreferredSize(new Dimension(300,400));
        cluesComponent.setBackground(new Color(30,30,30));
        cluesComponent.setOpaque(true);
        rightPanel.add(cluesPanel);

        // Panel para el botón Submit
        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(new Color(30,30,30));
        submitPanel.setOpaque(true);
        submitPanel.add(submitButtonComponent);  
        submitPanel.setPreferredSize(new Dimension(200, 50)); 
        submitPanel.setMinimumSize(new Dimension(300,50));
        rightPanel.add(submitPanel);

        // Agregar margen superior y margen lateral al panel derecho
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 60));  
        rightPanel.setMinimumSize(new Dimension(400, 600));  

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        setSize(900, 700);  

        // Agregar el panel principal a la ventana
        add(mainPanel);
        setVisible(true);
    }
    public void flipWaterJug(boolean isFilling){
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
                if (isFilling) {
                waterJugLabel.setIcon(new ImageIcon(getClass().getResource("/resources/waterJugFlipped.png")));
                } else {
                waterJugLabel.setIcon(new ImageIcon(getClass().getResource("/resources/waterJug.png")));
                }
           
            }
        });

    }
    public void fillThebottle(boolean isFull){
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
                if (isFull) {
                bottleLabel.setIcon(new ImageIcon(getClass().getResource("/resources/fullBottle.png")));
                } else {
                bottleLabel.setIcon(new ImageIcon(getClass().getResource("/resources/emptyBottle.png")));
                }
           
            }
        });
    }
    public TimerController getTimerController(){
        return timerController;
    }
    
    
}