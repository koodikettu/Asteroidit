/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.domain.Alus;
import asteroidit.peli.Asteroidipeli;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Luokka vastaa pelaajan näppäimistön välityksellä tekemien toimintojen
 * välittämisestä sovelluslogiikalle.
 *
 * @author Markku
 */
public class Nappaimistonkuuntelija implements KeyListener {

    private Asteroidipeli peli;

    public Nappaimistonkuuntelija(Asteroidipeli peli) {
        this.peli = peli;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    /**
     * Metodi käsittelee havaitut näppäimen alaspainamiset.
     * @param ke KeyEvent-olio
     */
    
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            this.peli.nappaimistonTila(1, -1, -1, -1, -1);

        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.peli.nappaimistonTila(-1, 1, -1, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            this.peli.nappaimistonTila(-1, -1, 1, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            this.peli.nappaimistonTila(-1, -1, -1, 1, -1);
        }

        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            this.peli.nappaimistonTila(-1, -1, -1, -1, 1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_C) {
            this.peli.getAsteroidilista().clear();
        }
        if (ke.getKeyCode() == KeyEvent.VK_S) {
            this.peli.getKirjanpitaja().setTila(-2);
        }
        if (ke.getKeyCode() == KeyEvent.VK_Q) {
            this.peli.getKirjanpitaja().setTila(-3);
        }

    }
    
    /**
     * Metodi käsittelee havaitut näppäinten vapautukset
     * @param ke KeyEvent-olio
     */


    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            this.peli.nappaimistonTila(0, -1, -1, -1, -1);

        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.peli.nappaimistonTila(-1, 0, -1, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            this.peli.nappaimistonTila(-1, -1, 0, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            this.peli.nappaimistonTila(-1, -1, -1, 0, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            this.peli.nappaimistonTila(-1, -1, -1, -1, 0);
        }
    }

}
