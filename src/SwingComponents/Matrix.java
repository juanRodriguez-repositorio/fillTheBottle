/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SwingComponents;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import Controllers.MatrixController;
public class Matrix extends JPanel {
    private static final int SIZE = 8; // Tamaño de la matriz (8x8)
    private JPanel[][] cells;         // Matriz de celdas
    private int selectedRow = 0;      // Fila seleccionada
    private int selectedCol = 0;      // Columna seleccionada
    private Lights lights;

    // Borde normal y borde enfocado
    private Border normalBorder = BorderFactory.createLineBorder(new Color(240,240,240), 1);
    private Border focusedBorder = BorderFactory.createLineBorder(new Color(255, 102, 0), 4);
    private Border blueBorder = BorderFactory.createLineBorder(new Color(0, 150, 200), 1);
    public Matrix(Lights lights) {
        this.lights=lights;
        // Establecer el layout en 8x8 (64 celdas)
        setLayout(new GridLayout(SIZE, SIZE));
        setBackground(new Color(30,30,30));
        //setBorder(BorderFactory.createLineBorder(new Color(240,240,240),2));

        // Inicializar el arreglo de celdas
        cells = new JPanel[SIZE][SIZE];

        // Crear las celdas y hacerlas enfocables
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Crear cada celda
                cells[i][j] = new JPanel();
                cells[i][j].setBackground(new Color(30,30,30));  // Fondo blanco
                cells[i][j].setBorder(normalBorder);    // Establecer borde inicial

                // Hacer la celda enfocada
                cells[i][j].setFocusable(true);

                // Cambiar el borde cuando la celda gane o pierda el enfoque
                cells[i][j].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        // Cambiar el borde cuando la celda recibe el enfoque
                        if(selectedRow!=0 || selectedCol!=0){
                            return;
                        }
                        ((JPanel) e.getComponent()).setBorder(focusedBorder);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // Restaurar el borde cuando la celda pierde el enfoque
                        if(selectedRow==0 || selectedCol==0){
                            return;
                        }
                        ((JPanel) e.getComponent()).setBorder(normalBorder);
                    }
                });

                // Agregar la celda al panel
                add(cells[i][j]);
            }
        }
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustCells();
            }
        });

        // Seleccionar la celda inicial
        if(selectedRow==0 && selectedCol==0){
            updateSelectedCell(false);
        }else{
            updateSelectedCell(true);
        }
        
    }

    // Método para obtener la celda en una posición específica
    private void adjustCells() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int cellSize = Math.min(panelWidth / SIZE, panelHeight / SIZE); // Mantener relación 1:1

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j].setPreferredSize(new Dimension(cellSize, cellSize));
            }
        }

        revalidate();
        repaint();
    }
    @Override
    public Dimension getPreferredSize() {
        int preferredSize = 400; // Ajusta según sea necesario
        return new Dimension(preferredSize, preferredSize);
    }
    public int[] getCell() {
       
            return new int[]{selectedRow,selectedCol};
         
    }
    public void fillCells(int [] selectedCell,Window window){
        int[][] cellsToPaint = MatrixController.getCellToFill(selectedCell);
        Color aquaBlue = new Color(0, 150, 200); // Azul agua ligeramente oscuro
        Timer timer = new Timer(150, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < cellsToPaint.length) {
                    int row = cellsToPaint[index][0];
                    int col = cellsToPaint[index][1];
                    cells[row][col].setBackground(aquaBlue);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(aquaBlue, 1));
                    index++;
                } else {
                    cells[selectedRow][selectedCol].setBorder(blueBorder);
                    if(selectedRow!=7){
                        selectedRow++;
                        updateSelectedCell(false);
                    }else{
                        window.fillThebottle(true);
                    }
                    window.flipWaterJug(false);
                    ((Timer) e.getSource()).stop(); // Detener el timer cuando se han pintado todas las celdas
                    
                }
            }
        });

        timer.setInitialDelay(0);
        timer.start();
        
    }

    // Método para actualizar la celda seleccionada
    private void updateSelectedCell(boolean isDeselect) {
        JPanel cell=cells[selectedRow][selectedCol];
        if(cell.getBackground().equals(new Color(0, 150, 200)) && isDeselect){
            cell.setBorder(blueBorder);
            return;
        }else if(isDeselect){
            cell.setBorder(normalBorder);
            return;
        }
        // Luego, actualizar el borde de la celda seleccionada
        cell.setBorder(focusedBorder);
    }
    // Métodos para mover la celda seleccionada
    public void moveUp() {
        if (selectedRow > 0) {
            updateSelectedCell(true);
            selectedRow--;
            updateSelectedCell(false);
            lights.updateLights(selectedCol);
            
        }
    }

    public void moveDown() {
        if (selectedRow < SIZE - 1) {
            updateSelectedCell(true);
            selectedRow++;
            updateSelectedCell(false);
            lights.updateLights(selectedCol);
        }
    }

    public void moveLeft() {
        if (selectedCol > 0) {
            updateSelectedCell(true);
            selectedCol--;
            updateSelectedCell(false);
            lights.updateLights(selectedCol);
        }
    }

    public void moveRight() {
        if (selectedCol < SIZE - 1) {
            updateSelectedCell(true);
            selectedCol++;
            updateSelectedCell(false);
            lights.updateLights(selectedCol);
        }
    }
}

