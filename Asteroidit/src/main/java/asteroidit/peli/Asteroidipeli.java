/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.peli;

import asteroidit.domain.Alus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.logiikka.Kirjanpitaja;
import asteroidit.logiikka.TormaystenKasittelija;
import asteroidit.gui.Piirtoalusta;
import asteroidit.gui.Ylapaneeli;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 * Luokka on asteroidipelin pääluokka, joka sisältää oliomuuttujina muut peliin
 * liittyvät oliot.
 *
 * @author Markku
 */
public class Asteroidipeli extends Timer implements ActionListener {

    private static final int REUNUKSEN_LEVEYS = 25;
    private static final int PAIVITYSVALI = 20;
    private static final int ASTEROIDIEN_ALKUNOPEUS = 2;
    private static final int RUUDUN_LEVEYS = 1000;
    private static final int RUUDUN_KORKEUS = 600;

    private int asteroidienNopeus = ASTEROIDIEN_ALKUNOPEUS;
    private Alus alus;
    private boolean vasenNuolinappain;
    private boolean oikeaNuolinappain;
    private boolean ylosNuolinappain;
    private boolean alasNuolinappain;
    private boolean valilyonti;
    Asteroidi a;
    Random random;

    private ArrayList<Asteroidi> asteroidilista = new ArrayList<Asteroidi>();
    private ArrayList<Ammus> ammuslista = new ArrayList<Ammus>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private Piirtoalusta piirtoalusta;
    private Ylapaneeli ylapaneeli;
    private Kirjanpitaja kirjanpitaja;
    private TormaystenKasittelija tormaystenKasittelija;

    public Asteroidipeli() {
        super(1000, null);

        this.kirjanpitaja = new Kirjanpitaja(1, this);
        this.tormaystenKasittelija = new TormaystenKasittelija(this);
        this.alus = new Alus(RUUDUN_LEVEYS / 2, RUUDUN_KORKEUS / 2, 90);
        this.alus.laskeAlusPolygoni();

        vasenNuolinappain = false;
        oikeaNuolinappain = false;
        valilyonti = false;

        random = new Random();
        for (int index = 0; index < 4; index++) {
            this.a = new Asteroidi(0, 0, 0, 0, this.random);
            this.a.alusta(this.random, RUUDUN_LEVEYS, RUUDUN_KORKEUS, REUNUKSEN_LEVEYS, asteroidienNopeus);
            this.asteroidilista.add(a);
        }

        addActionListener(this);
        setInitialDelay(PAIVITYSVALI);
    }

    public int getRuudunLeveys() {
        return RUUDUN_LEVEYS;
    }

    public Kirjanpitaja getKirjanpitaja() {
        return this.kirjanpitaja;
    }

    public int getRuudunKorkeus() {
        return RUUDUN_KORKEUS;
    }

    public Alus getAlus() {
        return this.alus;
    }

    public Random getRandom() {
        return this.random;
    }

    /**
     * Metodi kasvattaa asteroidien nopeutta. Vaikutus ulottuu vain
     * tulevaisuudessa luotaviin uusiin asteroideihin.
     */
    public void kasvataAsteroidienNopeutta() {
        this.asteroidienNopeus++;
    }

    public int getAsteroidienNopeus() {
        return this.asteroidienNopeus;
    }

    /**
     * Metodi luo uuden asteroidin muodon ja alustaa sille alkusijainnin.
     */
    public void uusiAsteroidi() {
        a = new Asteroidi(0, 0, 0, 0, random);
        a.alusta(random, RUUDUN_LEVEYS, RUUDUN_KORKEUS, REUNUKSEN_LEVEYS, asteroidienNopeus);
        this.asteroidilista.add(a);
    }

    /**
     * Metodi palauttaa pelin alkuasetelmaan.
     */
    public void aloitaAlusta() {
        this.asteroidilista.clear();
        this.ammuslista.clear();

        this.kirjanpitaja = new Kirjanpitaja(1, this);
        this.tormaystenKasittelija = new TormaystenKasittelija(this);
        this.alus = new Alus(RUUDUN_LEVEYS / 2, RUUDUN_KORKEUS / 2, 90);
        this.alus.laskeAlusPolygoni();
        this.asteroidienNopeus = ASTEROIDIEN_ALKUNOPEUS;

        for (int index = 0; index < 4; index++) {
            this.a = new Asteroidi(0, 0, 0, 0, this.random);
            this.a.alusta(this.random, RUUDUN_LEVEYS, RUUDUN_KORKEUS, REUNUKSEN_LEVEYS, asteroidienNopeus);
            this.asteroidilista.add(a);
        }
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

    public ArrayList<Asteroidi> getAsteroidilista() {
        return this.asteroidilista;
    }

    public ArrayList<Ammus> getAmmuslista() {
        return this.ammuslista;
    }

    public ArrayList<Ammus> getPoistettavatAmmukset() {
        return this.poistettavatAmmukset;
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
    }

    public void setYlapaneeli(Ylapaneeli ylapaneeli) {
        this.ylapaneeli = ylapaneeli;
    }

    public TormaystenKasittelija getTormaystenKasittelija() {
        return this.tormaystenKasittelija;
    }

    /**
     * Metodi luo uuden Ammus-luokan olion, jolla on annetut koordinaatit ja
     * liikesuunta.
     *
     * @param x Uuden ammuksen x-koordinaatti.
     * @param y Uuden ammuksen y-koordinaatti.
     * @param suunta Uuden ammuksen suunta asteina.
     */
    public void uusiAmmus(int x, int y, int suunta) {
        this.ammuslista.add(new Ammus(x, y, suunta));
    }

    /**
     * Metodi tekee näppäimistön tilan mukaiset muutokset aluksen tilaan,
     * liikuttaa alusta, tarkistaa aluksen törmäykset sekä laskee aluksen uudet
     * koordinaatit.
     *
     */
    public void aluksenHallinta() {
        if (oikeaNuolinappain) {
            alus.kaanna(-1);
        }
        if (vasenNuolinappain) {
            alus.kaanna(1);
        }
        if (valilyonti) {
            Ammus a = this.alus.ammu();
            if (a != null) {
                this.ammuslista.add(a);
            }
        }
        if (ylosNuolinappain) {
            getAlus().kiihdyta(true);
        }
        if (alasNuolinappain) {
            getAlus().kiihdyta(false);
        }
        alus.liiku();
        tormaystenKasittelija.hoidaReunanYlitykset(alus, 0);
        alus.laskeAlusPolygoni();
    }

    /**
     * Metodi sisältää pelin pääsilmukan.
     *
     * @param e ActionEvent-olio
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (kirjanpitaja.getTila() > 0) {

            laskeTilanne();

            piirtoalusta.paivita();
            ylapaneeli.paivita();
        }
        if (this.kirjanpitaja.getTila() < 0) {
            piirtoalusta.paivita();

            if (this.kirjanpitaja.getTila() == -2) {
                this.aloitaAlusta();
            }
            if (this.kirjanpitaja.getTila() == -3) {
                System.exit(0);
            }

        }

        setDelay(PAIVITYSVALI);

    }

    /**
     * Metodi laskee ruudun päivitystä varten pelissä olevien objektien
     * seuraavan tilanteen.
     */
    public void laskeTilanne() {
        aluksenHallinta();

        for (Asteroidi a : this.asteroidilista) {
            a.liiku();
            tormaystenKasittelija.hoidaReunanYlitykset(a, REUNUKSEN_LEVEYS);
            a.laskePolygoni();

        }

        for (Ammus a : this.ammuslista) {
            a.liiku();
            if (!tormaystenKasittelija.onRuudulla(a)) {
                this.poistettavatAmmukset.add(a);
            }

        }
        for (Ammus a : this.poistettavatAmmukset) {
            this.ammuslista.remove(a);
        }

        this.poistettavatAmmukset.clear();

        tormaystenKasittelija.tutkiTormaykset(this.ammuslista, this.asteroidilista);
        tormaystenKasittelija.tutkiAluksenTormaykset(this.asteroidilista);

    }

}
