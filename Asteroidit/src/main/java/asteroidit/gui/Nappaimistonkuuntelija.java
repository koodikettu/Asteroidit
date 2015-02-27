/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

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
        this.tl = peli.getTilanteenLaskija();
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
        this.tl = peli.getTilanteenLaskija();
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                tl.vasenNuoliTila(true);
                break;

            case KeyEvent.VK_RIGHT:
                tl.oikeaNuoliTila(true);
                break;

            case KeyEvent.VK_UP:
                tl.ylaNuoliTila(true);
                break;

            case KeyEvent.VK_DOWN:
                tl.alaNuoliTila(true);
                break;

            case KeyEvent.VK_SPACE:
                tl.valilyontiTila(true);
                break;

            case KeyEvent.VK_C:
                this.peli.getAsteroidilista().clear();
                break;

            case KeyEvent.VK_S:
                this.peli.getKirjanpitaja().setTila(-2);
                break;

            case KeyEvent.VK_Q:
                this.peli.getKirjanpitaja().setTila(-3);
                break;

        }

    }

    /**
     * Metodi käsittelee havaitut näppäinten vapautukset
     *
     * @param ke KeyEvent-olio
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        this.tl = peli.getTilanteenLaskija();
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                tl.vasenNuoliTila(false);
                break;

            case KeyEvent.VK_RIGHT:
                tl.oikeaNuoliTila(false);
                break;

            case KeyEvent.VK_UP:
                tl.ylaNuoliTila(false);
                break;

            case KeyEvent.VK_DOWN:
                tl.alaNuoliTila(false);
                break;

            case KeyEvent.VK_SPACE:
                tl.valilyontiTila(false);
                break;

        }
    }

}
