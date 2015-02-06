/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

/**
 * Luokka sisältää pelin tilaan liittyvän tiedon käsittelyyn tarvittavat metodit.
 * 
 * @author Markku
 */
public class Kirjanpitaja {
    
    private int pisteet;
    private int tila;
    
    public Kirjanpitaja(int tila) {
        this.pisteet=0;
        this.tila=tila;
    }
    
    public void kasvataPisteita(int n) {
        this.pisteet+=n;
    }
    
    public int getPisteet() {
        return this.pisteet;
    }
    
    public int getTila() {
        return this.tila;
    }
    
    public void setTila(int tila) {
        this.tila=tila;
    }
    
}
