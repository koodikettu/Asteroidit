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
        this.poistettavatAmmukset = new ArrayList<Ammus>();
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

    /**
     * Metodi välittää Näppäimistönkuuntelija-oliolta näppäinten tilojen
     * muutokset Asteroidipeli-olioon.
     *
     * @param vNuoli Vasemman nuolinäppäimen tila, 1 = painettu, 0 = vapautettu,
     * -1 = ei muutosta
     * @param oNuoli Oikean nuoinäppäimen tila
     * @param yNuoli Nuoli ylöspäin -näppäimen tila
     * @param aNuoli Nuoli alaspäin -näppäimen tila
     * @param vLyonti valilyönti-nappaimen tila
     */
    public void nappaimistonTila(int vNuoli, int oNuoli, int yNuoli, int aNuoli, int vLyonti) {
        if (vNuoli == 1) {
            this.vasenNuolinappain = true;
        }
        if (vNuoli == 0) {
            this.vasenNuolinappain = false;
        }
        if (oNuoli == 1) {
            this.oikeaNuolinappain = true;
        }
        if (oNuoli == 0) {
            this.oikeaNuolinappain = false;
        }
        if (vLyonti == 1) {
            this.valilyonti = true;
        }
        if (vLyonti == 0) {
            this.valilyonti = false;
        }
        if (yNuoli == 1) {
            this.ylosNuolinappain = true;
        }
        if (yNuoli == 0) {
            this.ylosNuolinappain = false;
        }
        if (aNuoli == 1) {
            this.alasNuolinappain = true;
        }
        if (aNuoli == 0) {
            this.alasNuolinappain = false;
        }
    }

}
