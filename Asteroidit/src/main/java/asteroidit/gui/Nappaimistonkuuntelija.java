/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.domain.Alus;
import asteroidit.logiikka.TilanteenLaskija;
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
    private TilanteenLaskija tl;

    public Nappaimistonkuuntelija(Asteroidipeli peli) {
        this.peli = peli;
        this.tl=peli.getTilanteenLaskija();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    /**
     * Metodi käsittelee havaitut näppäimen alaspainamiset.
     *
     * @param ke KeyEvent-olio
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        this.tl=peli.getTilanteenLaskija();
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            tl.nappaimistonTila(1, -1, -1, -1, -1);

        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            tl.nappaimistonTila(-1, 1, -1, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            tl.nappaimistonTila(-1, -1, 1, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            tl.nappaimistonTila(-1, -1, -1, 1, -1);
        }

        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            tl.nappaimistonTila(-1, -1, -1, -1, 1);
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
     *
     * @param ke KeyEvent-olio
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        this.tl=peli.getTilanteenLaskija();
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            tl.nappaimistonTila(0, -1, -1, -1, -1);

        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            tl.nappaimistonTila(-1, 0, -1, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            tl.nappaimistonTila(-1, -1, 0, -1, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            tl.nappaimistonTila(-1, -1, -1, 0, -1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            tl.nappaimistonTila(-1, -1, -1, -1, 0);
        }
    }

}
