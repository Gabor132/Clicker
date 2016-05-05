/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clicker;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Dragos-Alexandru
 */
public class Clicker extends JFrame{

    /**
     * @param args the command line arguments
     * @throws java.awt.AWTException
     */
    public static void main(String[] args) throws AWTException {
        Clicker c = new Clicker();
    }
    
    JButton start;
    JTextArea text;
    JScrollPane scroll;
    int nrPoz = 0;
    int nrClick = 0;
    ClickerMotion cM;
    
    public Clicker() throws AWTException{
        super("Clicker");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(400, 400);
        this.setLocation(20, 20);
        this.setResizable(false);
        
        
        start = new JButton("Start");
        start.addActionListener((ActionEvent e) -> {
            cM.start();
        });
        text = new JTextArea("Welcome to clicker!\n");
        text.setEditable(false);
        scroll = new JScrollPane(text);
        String aux = JOptionPane.showInputDialog("Numar pozitii dorite:");
        nrPoz = Integer.parseInt(aux);
        aux = JOptionPane.showInputDialog("Numar de click-uri dorite:");
        nrClick = Integer.parseInt(aux);
        
        text.append("\n"+"nrPoz: "+nrPoz+"\n");
        text.append("nrClick: "+nrClick+"\n");
        
        this.add(scroll,BorderLayout.CENTER);
        this.add(start, BorderLayout.SOUTH);
        this.setResizable(true);
        
        cM = new ClickerMotion(nrPoz, nrClick, text);
        
        
        this.getGlassPane().addMouseListener(cM);
        this.getGlassPane().addMouseMotionListener(cM);
        text.addMouseListener(cM);
        text.addMouseMotionListener(cM);
        start.addMouseListener(cM);
        
        this.setVisible(true);
    }
    
}
