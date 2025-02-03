/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import SwingComponents.TimerLabel;

public class TimerController {
    private int timeRemaining = 15 * 60; // 15 minutos en segundos
    private int failedAttempts = 0;
    private TimerLabel timerLabel;
    private Timer timer;

    public TimerController(TimerLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        timer.start();
    }

    private void updateTimer() {
        if (timeRemaining <= 0) {
            timer.stop();
            onGameLost();
            return;
        }
        timeRemaining--;
        updateLabel();
    }

    public boolean registerFailedAttempt() {
        int timePenalty =(int) Math.pow(++failedAttempts, 2); // Penalización cuadrática
        timeRemaining -= timePenalty+60;
        if (timeRemaining < 0) timeRemaining = 0;
        updateLabel();
        if (timeRemaining == 0) {
            timer.stop();
            onGameLost();
            return true;
        }
        return false;
    }

    private void updateLabel() {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        timerLabel.updateTime(minutes, seconds);
    }

    private void onGameLost() {
        JOptionPane.showMessageDialog(null, "¡Se acabó el tiempo! Has perdido.");
        // Puedo agregar lógica para reiniciar o cerrar el juego aquí
    }
}