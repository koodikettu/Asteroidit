/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.logiikka;

import asteroidit.domain.Alus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.domain.Liikkuva;
import asteroidit.peli.Asteroidipeli;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Luokka sisältää metodit, joilla käsitellään ruudulla liikkuvien kohteiden
 * välisiä törmäyksiä.
 *
 * @author Markku
 */
public class TormaystenKasittelija {

    private Asteroidipeli peli;
    private ArrayList<Asteroidi> poistettavatAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private ArrayList<Asteroidi> uudetAsteroidit = new ArrayList<Asteroidi>();
    private Asteroidi asteroidi;
    private int korvaaviaAsteroideja = 0;

    public TormaystenKasittelija(Asteroidipeli peli) {
        this.peli = peli;
    }

    /**
     * Metodi tutkii ammusten ja asteroidien väliset törmäykset. Jos törmäys
     * havaitaan, siirretään kyseiset asteroidit ja ammukset odottamaan
     * poistamista erityisille poistolistoille.
     *
     * @param ammuslista lista ruudulla olevista ammuksista
     * @param asteroidilista lista pelissä olevista asteroideista
     */
    public int tutkiTormaykset(ArrayList<Ammus> ammuslista, ArrayList<Asteroidi> asteroidilista) {
        int x, y;

        for (Ammus a : ammuslista) {
            x = a.getX();
            y = a.getY();
            for (Asteroidi ast : asteroidilista) {
                if (ast.getAsteroidiPolygoni().contains(x, y)) {
                    this.poistettavatAsteroidit.add(ast);
                    this.poistettavatAmmukset.add(a);
                    this.peli.getKirjanpitaja().kasvataPisteita(10);
                    if (this.peli.getKirjanpitaja().getPisteet() % 200 == 0) {
                        korvaaviaAsteroideja++;
                    }
                }
            }
        }
        int temp=korvaaviaAsteroideja;
        poistaPoistettavat(ammuslista, asteroidilista);
        return temp;

    }

    /**
     * Metodi poistaa pelistä poistettavien listalla olevat ammukset ja
     * asteroidit.
     *
     * @param ammuslista
     * @param asteroidilista
     */
    public void poistaPoistettavat(ArrayList<Ammus> ammuslista, ArrayList<Asteroidi> asteroidilista) {
        for (Asteroidi ast : this.poistettavatAsteroidit) {
            asteroidilista.remove(ast);
            korvaaviaAsteroideja++;
        }
        for (Ammus a : this.poistettavatAmmukset) {
            ammuslista.remove(a);
        }
        for (int i = 0; i < korvaaviaAsteroideja; i++) {
            this.peli.uusiAsteroidi();
        }
        this.korvaaviaAsteroideja = 0;
        this.poistettavatAsteroidit.clear();
        this.poistettavatAmmukset.clear();
    }

    /**
     * Metodi tutkii pelaajan aluksen ja asteroidien väliset törmäykset. Jos
     * törmäys havaitaan, peli päättyy.
     *
     * @param asteroidilista lista pelissä olevista asteroideista
     */
    public boolean tutkiAluksenTormaykset(ArrayList<Asteroidi> asteroidilista) {
        boolean tormays = false;
        for (Asteroidi a : asteroidilista) {
            if (tutkiTormays(this.peli.getAlus(), a) > 0) {
                tormays = true;
            }
        }
        if (tormays) {
            this.peli.getKirjanpitaja().setTila(-1);
        }
        return tormays;
    }

    /**
     * Metodi tutkii, onko parametrina annettu Ammus-tyyppinen olio ruudulla.
     *
     * @param a tutkittava Ammus-tyypin olio
     * @return palauttaa true, jos ammus on ruudulla, false, jos ei ole
     */
    public boolean onRuudulla(Ammus a) {
        int x = a.getX();
        int y = a.getY();
        if (y < 0 || y > this.peli.getRuudunKorkeus()) {
            return false;
        } else if (x < 0 || x > this.peli.getRuudunLeveys()) {
            return false;
        }
        return true;
    }
    
    public boolean onRuudulla(int x, int y) {
        if (y < 0 || y > this.peli.getRuudunKorkeus()) {
            return false;
        } else if (x < 0 || x > this.peli.getRuudunLeveys()) {
            return false;
        }
        return true;
    }

    /**
     * Metodi tutkii, ovatko parametreinä olevat alus ja asteroidi törmänneet.
     *
     * @param alus Tutkittava alus
     * @param asteroidi Tutkittava asteroidi
     * @return Metodi palauttaa kokonaisluvun, jonka ykkösten paikalta voi
     * lukea, moniko aluksen kulmapisteistä on asteroidin polygonin sisällä, ja
     * jonka kymmenien paikalta voi lukea, moniko asteroidin kulmapisteistä on
     * aluksen polygonin sisällä. Jos törmäystä ei ole tapahtunut, paluuarvo on
     * 0.
     */
    public int tutkiTormays(Alus alus, Asteroidi asteroidi) {
        Polygon aluspolygoni = alus.getAlusPolygoni();
        Polygon asteroidipolygoni = asteroidi.getAsteroidiPolygoni();
        int tulos = 0;
        int i;

        // tutkitaan, onko joku aluksen polygonin piste asteroidin polygonin sisällä
        for (i = 0; i < aluspolygoni.npoints; i++) {
            if (asteroidipolygoni.contains(aluspolygoni.xpoints[i], aluspolygoni.ypoints[i])) {
                tulos++;
            }
        }

        // tutkitaan, onko joku asteroidin polygonin piste aluksen polygonin sisällä
        for (i = 0; i < asteroidipolygoni.npoints; i++) {
            if (aluspolygoni.contains(asteroidipolygoni.xpoints[i], asteroidipolygoni.ypoints[i])) {
                tulos += 10;
            }
        }

        return tulos;
    }

    /**
     * Metodi tutkii, onko Liikkuva-rajapinnan toteuttava olio ajautunut ulos
     * ruudulta. Jos näin on, se siirtää olion ruudun vastakkaiselle puolelle.
     *
     * @param a Liikkuva-rajapinnan toteuttava olio.
     * @param reunuksenLeveys pelin käyttämän ikkunan ulkopuolelle jäävän
     * reuna-alueen leveys
     */
    public void hoidaReunanYlitykset(Liikkuva a, int reunuksenLeveys) {
        if (a.getX() > this.peli.getRuudunLeveys() + reunuksenLeveys) {
            a.setX(-reunuksenLeveys);
        }
        if (a.getX() < -reunuksenLeveys) {
            a.setX(this.peli.getRuudunLeveys() + reunuksenLeveys);
        }
        if (a.getY() > this.peli.getRuudunKorkeus() + reunuksenLeveys) {
            a.setY(-reunuksenLeveys);
        }
        if (a.getY() < -reunuksenLeveys) {
            a.setY(this.peli.getRuudunKorkeus() + reunuksenLeveys);
        }
    }

    public ArrayList<Asteroidi> getPoistettavat() {
        return this.poistettavatAsteroidit;
    }

}
