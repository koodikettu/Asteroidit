/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.logiikka;

import asteroidit.domain.Alus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.peli.Asteroidipeli;
import java.util.ArrayList;

/**
 * Luokka sisältää pelitilanteen laskemiseen liittyviä metodeja.
 *
 * @author Markku
 */
public class TilanteenLaskija {

    Asteroidipeli peli;
    TormaystenKasittelija kasittelija;
    ArrayList<Ammus> poistettavatAmmukset;

    private boolean vasenNuolinappain;
    private boolean oikeaNuolinappain;
    private boolean ylosNuolinappain;
    private boolean alasNuolinappain;
    private boolean valilyonti;

    public TilanteenLaskija(Asteroidipeli peli, TormaystenKasittelija kasittelija) {
        this.peli = peli;
        this.kasittelija = kasittelija;
        this.poistettavatAmmukset = new ArrayList<>();
        vasenNuolinappain = false;
        oikeaNuolinappain = false;
        valilyonti = false;
    }

    /**
     * Metodi laskee ruudun päivitystä varten pelissä olevien objektien
     * seuraavan tilanteen.
     */
    public void laskeTilanne() {
        ArrayList<Ammus> ammusLista = peli.getAmmuslista();
        ArrayList<Asteroidi> asteroidiLista = peli.getAsteroidilista();

        aluksenHallinta();

        for (Asteroidi a : asteroidiLista) {
            a.liiku();
            kasittelija.hoidaReunanYlitykset(a, peli.getReunuksenLeveys());
            a.laskePolygoni();

        }

        for (Ammus a : ammusLista) {
            a.liiku();
            if (!peli.getTormaystenKasittelija().onRuudulla(a)) {
                poistettavatAmmukset.add(a);
            }

        }
        for (Ammus a : poistettavatAmmukset) {
            ammusLista.remove(a);
        }

        this.poistettavatAmmukset.clear();

        kasittelija.tutkiTormaykset(ammusLista, asteroidiLista);
        kasittelija.tutkiAluksenTormaykset(asteroidiLista);

    }

    /**
     * Metodi tekee näppäimistön tilan mukaiset muutokset aluksen tilaan,
     * liikuttaa alusta, tarkistaa aluksen törmäykset sekä laskee aluksen uudet
     * koordinaatit.
     *
     */
    public void aluksenHallinta() {
        Alus alus = peli.getAlus();
        if (oikeaNuolinappain) {
            alus.kaanna(-1);
        }
        if (vasenNuolinappain) {
            alus.kaanna(1);
        }
        if (valilyonti) {
            Ammus a = alus.ammu();
            if (a != null) {
                peli.getAmmuslista().add(a);
            }
        }
        if (ylosNuolinappain) {
            alus.kiihdyta(true);
        }
        if (alasNuolinappain) {
            alus.kiihdyta(false);
        }
        alus.liiku();
        this.kasittelija.hoidaReunanYlitykset(alus, 0);
        alus.laskeAlusPolygoni();
    }

    public void vasenNuoliTila(boolean tila) {
        this.vasenNuolinappain = tila;
    }

    public void oikeaNuoliTila(boolean tila) {
        this.oikeaNuolinappain = tila;
    }

    public void ylaNuoliTila(boolean tila) {
        this.ylosNuolinappain = tila;
    }

    public void alaNuoliTila(boolean tila) {
        this.alasNuolinappain = tila;
    }

    public void valilyontiTila(boolean tila) {
        this.valilyonti = tila;
    }

    public boolean[] nappaintenTila() {
        boolean[] nappaintenTila = new boolean[5];
        nappaintenTila[0] = this.vasenNuolinappain;
        nappaintenTila[1] = this.oikeaNuolinappain;
        nappaintenTila[2] = this.ylosNuolinappain;
        nappaintenTila[3] = this.alasNuolinappain;
        nappaintenTila[4] = this.valilyonti;
        return nappaintenTila;
    }

}
