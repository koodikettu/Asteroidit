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

    private final int REUNUKSEN_LEVEYS = 25;
    private final int PAIVITYSVALI = 20;
    private final int ASTEROIDIEN_ALKUNOPEUS = 2;
    private int ruudunLeveys;
    private int ruudunKorkeus;
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
    private ArrayList<Asteroidi> poistettavatAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Asteroidi> uudetAsteroidit = new ArrayList<Asteroidi>();
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
        this.ruudunKorkeus = 600;
        this.ruudunLeveys = 1000;
        this.alus = new Alus(this.ruudunLeveys / 2, this.ruudunKorkeus / 2, 90);
        this.alus.laskeAlusPolygoni();

        vasenNuolinappain = false;
        oikeaNuolinappain = false;
        valilyonti = false;

        random = new Random();
        for (int index = 0; index < 4; index++) {
            this.a = new Asteroidi(0, 0, 0, 0, this.random);
            this.a.alusta(this.random, this.ruudunLeveys, this.ruudunKorkeus, this.ruudunLeveys, asteroidienNopeus);
            this.asteroidilista.add(a);
        }

        addActionListener(this);
        setInitialDelay(PAIVITYSVALI);
    }

    public int getRuudunLeveys() {
        return this.ruudunLeveys;
    }

    public Kirjanpitaja getKirjanpitaja() {
        return this.kirjanpitaja;
    }

    public int getRuudunKorkeus() {
        return this.ruudunKorkeus;
    }

    public Alus getAlus() {
        return this.alus;
    }

    public Random getRandom() {
        return this.random;
    }

    public void kasvataAsteroidienNopeutta() {
        this.asteroidienNopeus++;
    }
    
    public int getAsteroidienNopeus() {
        return this.asteroidienNopeus;
    }

    public void uusiAsteroidi() {
        a = new Asteroidi(0, 0, 0, 0, random);
        a.alusta(random, ruudunLeveys, ruudunKorkeus, REUNUKSEN_LEVEYS, asteroidienNopeus);
        this.asteroidilista.add(a);
    }

    public void aloitaAlusta() {
        this.asteroidilista.clear();
        this.ammuslista.clear();
        this.uudetAsteroidit.clear();

        this.kirjanpitaja = new Kirjanpitaja(1, this);
        this.tormaystenKasittelija = new TormaystenKasittelija(this);
        this.alus = new Alus(this.ruudunLeveys / 2, this.ruudunKorkeus / 2, 90);
        this.alus.laskeAlusPolygoni();
        this.asteroidienNopeus = ASTEROIDIEN_ALKUNOPEUS;

        for (int index = 0; index < 4; index++) {
            this.a = new Asteroidi(0, 0, 0, 0, this.random);
            this.a.alusta(this.random, this.ruudunLeveys, this.ruudunKorkeus, this.ruudunLeveys, asteroidienNopeus);
            this.asteroidilista.add(a);
        }
    }

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

    public int getAmmustenMaara() {
        return this.ammuslista.size();
    }

    public int getAsteroidienMaara() {
        return this.asteroidilista.size();
    }

    public ArrayList<Ammus> getAmmuslista() {
        return this.ammuslista;
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
    }

    public void setYlapaneeli(Ylapaneeli ylapaneeli) {
        this.ylapaneeli = ylapaneeli;
    }

    public void uusiAmmus(int x, int y, int suunta) {
        this.ammuslista.add(new Ammus(x, y, suunta));
    }

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        luuppi();

    }
    public void luuppi() {
        if (kirjanpitaja.getTila() > 0) {

            aluksenHallinta();

            for (Asteroidi a : this.uudetAsteroidit) {
                this.asteroidilista.add(a);
            }
            this.uudetAsteroidit.clear();

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

            tormaystenKasittelija.tutkiTormaykset(this.ammuslista, this.asteroidilista);
            tormaystenKasittelija.tutkiAluksenTormaykset(this.asteroidilista);
            this.poistettavatAmmukset.clear();

            alus.laskeAlusPolygoni();
            piirtoalusta.paivita();
            ylapaneeli.paivita();
        }
        if (this.kirjanpitaja.getTila() < 0) {

            if (this.kirjanpitaja.getTila() == -2) {
                this.aloitaAlusta();
            }
            if (this.kirjanpitaja.getTila() == -3) {
                System.exit(0);
            }

        }

        setDelay(PAIVITYSVALI);
    }

}
