/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.logiikka;

import asteroidit.peli.Asteroidipeli;

/**
 * Luokka sisältää pelin tilaan liittyvän tiedon käsittelyyn tarvittavat
 * metodit.
 *
 * @author Markku
 */
public class Kirjanpitaja {

    private int pisteet;
    private int tila;
    private Asteroidipeli peli;

    public Kirjanpitaja(int tila, Asteroidipeli peli) {
        this.pisteet = 0;
        this.tila = tila;
        this.peli = peli;
    }

    /**
     * Metodi kasvattaa pelaajan pistesaldoa ja tarkistaa, antaako pistemäärä
     * aihetta kasvattaa asteroidien nopeutta.
     *
     * @param n Määrä, jolla pistesaldoa kasvatetaan.
     */
    public void kasvataPisteita(int n) {
        this.pisteet += n;
        if (this.pisteet % 200 == 0) {
            this.peli.kasvataAsteroidienNopeutta();
        }
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public int getTila() {
        return this.tila;
    }

    public void setTila(int tila) {
        this.tila = tila;
    }

    public void reset() {
        this.tila = 0;
        this.pisteet = 0;
    }

}
