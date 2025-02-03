/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SwingComponents;

import Controllers.LightsController;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.awt.image.BufferedImage;


public class Lights extends JPanel {
    private static final int NUM_LIGHTS = 3;   // Tres luces en fila
    private CirclePanel[] lights;                  // Array para las luces
          // Instancia de LightsController

    public Lights() {
        
        
        this.lights = new CirclePanel[NUM_LIGHTS];

        // Configurar el layout de las luces en fila
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Crear las luces como círculos
        for (int i = 0; i < NUM_LIGHTS; i++) {
            lights[i] = new CirclePanel();
            lights[i].setPreferredSize(new Dimension(50, 50));
            lights[i].setOpaque(false);
            add(lights[i]);
        }
    }

    // Método para actualizar las luces según la columna
    public void updateLights(int column) {
        // Obtener los colores de la columna desde el controlador
        int[] colors = LightsController.getColors(column);

        // Asegurarse de que el tamaño del array de colores sea correcto
        if (colors.length == NUM_LIGHTS) {
            for (int i = 0; i < NUM_LIGHTS; i++) {
                // Cambiar el color de cada luz según el valor 1 (verde) o 0 (naranja)
                lights[i].setColor(colors[i] == 1 ? new Color(57, 255, 20) : new Color(255, 102, 0));
            }
            repaint();
        } else {
            throw new IllegalArgumentException("Bad array for update colors");
        }
    }
    private class CirclePanel extends JPanel {
        private Color color;

        // Constructor
        public CirclePanel() {
            this.color = new Color(255, 102, 0);  // Iniciar con el color de fondo
        }

        // Establecer el color de la luz
        public void setColor(Color color) {
            this.color = color;
            repaint();  // Volver a dibujar el componente con el nuevo color
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Dibujar el círculo con el color actual
            Graphics2D g2d = (Graphics2D) g;

            // Habilitar el anti-aliasing para un borde más suave
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Calcular el diámetro del círculo
            int diameter = Math.min(getWidth(), getHeight());  // Asegura que el círculo ocupe todo el panel
            g2d.setColor(color);
            g2d.fillOval(1, 1, diameter - 2, diameter - 2);  
        }
    }
   
}
