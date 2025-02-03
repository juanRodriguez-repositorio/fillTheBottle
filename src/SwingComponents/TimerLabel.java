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

public class TimerLabel extends JLabel {
    public TimerLabel() {
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 20));
        setText("Tiempo: 15:00");
    }

    public void updateTime(int minutes, int seconds) {
        setText(String.format("Tiempo: %02d:%02d", minutes, seconds));
    }
}
