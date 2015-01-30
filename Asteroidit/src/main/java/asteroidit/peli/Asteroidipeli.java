/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.peli;

import asteroidit.domain.Alus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.domain.Kirjanpitaja;
import asteroidit.domain.TormaystenKasittelija;
import asteroidit.gui.Piirtoalusta;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Markku
 */
public class Asteroidipeli extends Timer implements ActionListener {

    private final int REUNUKSEN_LEVEYS = 25;
    private final int PAIVITYSVALI = 20;
    private int ruudunLeveys;
    private int ruudunKorkeus;
    private int asteroidienNopeus = 2;
    private Alus alus;
    private boolean vasenNuolinappain;
    private boolean oikeaNuolinappain;
    private boolean valilyonti;
    Asteroidi a;
    Random random;

    private ArrayList<Asteroidi> asteroidilista = new ArrayList<Asteroidi>();
    private ArrayList<Asteroidi> poistettavatAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Asteroidi> uudetAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Ammus> ammuslista = new ArrayList<Ammus>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private Piirtoalusta piirtoalusta;
    private Kirjanpitaja kirjanpitaja;
    private TormaystenKasittelija tormaystenKasittelija;

    public Asteroidipeli() {
        super(1000, null);

        this.kirjanpitaja = new Kirjanpitaja(1);
        this.tormaystenKasittelija = new TormaystenKasittelija(this);
        this.ruudunKorkeus = 600;
        this.ruudunLeveys = 1000;
        this.alus = new Alus(this.ruudunLeveys / 2, this.ruudunKorkeus / 2, 90);

        vasenNuolinappain = false;
        oikeaNuolinappain = false;
        valilyonti = false;

        random = new Random();
        for (int index = 0; index < 4; index++) {
            this.a = new Asteroidi(0, 0, 0, 0);
            this.a.alusta(this.random, this.ruudunLeveys, this.ruudunKorkeus, this.ruudunLeveys, asteroidienNopeus);
            this.asteroidilista.add(a);
        }

        addActionListener(this);
        setInitialDelay(1000);
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

    public void uusiAsteroidi() {
        a = new Asteroidi(0,0,0,0);
        a.alusta(random, ruudunLeveys, ruudunKorkeus, REUNUKSEN_LEVEYS, asteroidienNopeus);
        this.asteroidilista.add(a);
    }

    public void nappaimistonTila(int vNuoli, int oNuoli, int vLyonti) {
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

    public void uusiAmmus(int x, int y, int suunta) {
        this.ammuslista.add(new Ammus(x, y, suunta));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.kirjanpitaja.getTila() == -1) {
            this.stop();
        }

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
        setDelay(PAIVITYSVALI);

    }

}
