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
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;

public class Clues extends JPanel {
    private JTextPane clueTextPane;
    private JScrollPane scrollPane;

    public Clues() {
        setLayout(new BorderLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0), "Pistas !");
        titledBorder.setTitleColor(new Color(240, 240, 240));  // Color blanco para el título
        titledBorder.setTitleFont(new Font("Arial", Font.PLAIN, 16));  // Fuente del título
        setBorder(titledBorder);
        setBackground(new Color(30, 30, 30));

        // Crear el JTextPane en lugar de JLabel
        clueTextPane = new JTextPane();
        clueTextPane.setFont(new Font("Arial", Font.PLAIN, 16));
        clueTextPane.setForeground(new Color(240, 240, 240));
        clueTextPane.setOpaque(true);
        clueTextPane.setBackground(new Color(30, 30, 30));
        clueTextPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Configurar JTextPane para que no sea editable y permitir salto de línea
        clueTextPane.setEditable(false);
        clueTextPane.setContentType("text/html"); // Permite HTML

        // Agregar el JTextPane dentro de un JScrollPane
        scrollPane = new JScrollPane(clueTextPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Eliminar el borde predeterminado

        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadClue(String clue) {
        clueTextPane.setText("<html><body style='width: 250px; color: rgb(240, 240, 240); font-family: Arial, sans-serif; font-size: 14px;'>" + clue + "</body></html>");
        
        // Hacer scroll hacia arriba en caso de que la pista anterior haya sido larga
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
    }
}