/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clicker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JTextArea;

/**
 *
 * @author Dragos-Alexandru
 */
public final class ClickerMotion extends Robot implements MouseListener, MouseMotionListener{
    
    ArrayList<Pair<Integer,Integer>> coordonates;
    JTextArea status;
    int nrPoz;
    int nrClick;
    int nrNume = 0;
    LinkedList<Integer> keysToPress = new LinkedList<>();
    long time = 0;
    boolean stop = false;
    
    /**
     *
     * @param nrPoz
     * @param nrClick
     * @param status
     * @throws java.awt.AWTException
     */
    public ClickerMotion(int nrPoz, int nrClick, JTextArea status) throws AWTException{
        super();
        coordonates = new ArrayList<>();
        this.nrClick = nrClick;
        this.nrPoz = nrPoz;
        this.status = status;
        keysToPress.add(KeyEvent.VK_0);
    }
    
    public void start(){
        int auxNrClick = nrClick;
        while(auxNrClick>0){
            auxNrClick--;
            int auxNrPoz = 0;
            while(auxNrPoz<nrPoz){
                int x = coordonates.get(auxNrPoz).getKey();
                int y = coordonates.get(auxNrPoz).getValue();
                
                auxNrPoz++;
                super.mouseMove(x, y);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClickerMotion.class.getName()).log(Level.SEVERE, null, ex);
                }
                super.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                super.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClickerMotion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClickerMotion.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(Integer key:keysToPress){
                super.keyPress(key);
                super.keyRelease(key);
            }
            super.keyPress(KeyEvent.VK_ENTER);
            super.keyRelease(KeyEvent.VK_ENTER);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClickerMotion.class.getName()).log(Level.SEVERE, null, ex);
            }
            nrNume++;
            setKeysToPress();
        }
    }

    public void setKeysToPress(){
        int aux = nrNume;
        keysToPress.clear();
        while(aux != 0){
            int lastDigit = aux%10;
            aux = aux/10;
            switch(lastDigit){
                case 0:{
                    keysToPress.addFirst(KeyEvent.VK_0);
                }break;
                case 1:{
                    keysToPress.addFirst(KeyEvent.VK_1);
                }break;
                case 2:{
                    keysToPress.addFirst(KeyEvent.VK_2);
                }break;
                case 3:{
                    keysToPress.addFirst(KeyEvent.VK_3);
                }break;
                case 4:{
                    keysToPress.addFirst(KeyEvent.VK_4);
                }break;
                case 5:{
                    keysToPress.addFirst(KeyEvent.VK_5);
                }break;
                case 6:{
                    keysToPress.addFirst(KeyEvent.VK_6);
                }break;
                case 7:{
                    keysToPress.addFirst(KeyEvent.VK_7);
                }break;
                case 8:{
                    keysToPress.addFirst(KeyEvent.VK_8);
                }break;
                case 9:{
                    keysToPress.addFirst(KeyEvent.VK_9);
                }break;
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(coordonates.size()<nrPoz){
            coordonates.add(new Pair(e.getXOnScreen(), e.getYOnScreen()));
            status.append("nrPoz: "+(nrPoz-coordonates.size())+"\n");
        }else{
            coordonates.stream().forEach((aux) -> {
                status.append("coordonate: "+aux+"\n");
            });
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Date d = new Date();
        if(d.getTime() - time > 10000){
            status.append(e.getXOnScreen()+" "+e.getYOnScreen()+"\n");
            time = d.getTime();
        }
    }
}
