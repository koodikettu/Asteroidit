/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.domain.Alus;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Markku
 */
public class Nappaimistonkuuntelija implements KeyListener {
    
    private Alus alus;
    
    public Nappaimistonkuuntelija(Alus alus) {
        this.alus=alus;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_LEFT) {
            this.alus.kaanna(1);

        }
        if(ke.getKeyCode()==KeyEvent.VK_RIGHT) {
            this.alus.kaanna(-1);
        }
        if(ke.getKeyCode()==KeyEvent.VK_UP) {

        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN) {

        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
