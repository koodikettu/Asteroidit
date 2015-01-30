/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import asteroidit.peli.Asteroidipeli;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Markku
 */
public class TormaystenKasittelija {
    
    private Asteroidipeli peli;
    private ArrayList<Asteroidi> poistettavatAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private ArrayList<Asteroidi> uudetAsteroidit = new ArrayList<Asteroidi>();
    
    public TormaystenKasittelija(Asteroidipeli peli) {
        this.peli=peli;
    }
    
    public void tutkiTormaykset(ArrayList<Ammus> ammuslista, ArrayList<Asteroidi> asteroidilista) {
        int x, y;

        for (Ammus a : ammuslista) {
            x = a.getX();
            y = a.getY();
            for (Asteroidi ast : asteroidilista) {
                if (ast.getAsteroidiPolygoni().contains(x, y)) {
                    this.poistettavatAsteroidit.add(ast);
                    this.poistettavatAmmukset.add(a);
                    this.peli.getKirjanpitaja().kasvataPisteita(10);
                    if (this.peli.getKirjanpitaja().getPisteet() % 100 == 0) {
                        this.peli.kasvataAsteroidienNopeutta();
                    }
                    if (this.peli.getKirjanpitaja().getPisteet() % 100 == 0) {
                        this.uudetAsteroidit.add(peli.uusiAsteroidi(this.peli.getRandom()));
                    }
                }
            }
        }
        for (Asteroidi ast : this.poistettavatAsteroidit) {
            asteroidilista.remove(ast);
            asteroidilista.add(peli.uusiAsteroidi(this.peli.getRandom()));
        }
        for (Ammus a : this.poistettavatAmmukset) {
            ammuslista.remove(a);
        }
        this.poistettavatAsteroidit.clear();
        this.poistettavatAmmukset.clear();

    }
    
        public void tutkiAluksenTormaykset(ArrayList<Asteroidi> asteroidilista) {
        Polygon p = this.peli.getAlus().getAlusPolygoni();
        boolean tormays = false;
        int[] x = new int[3];
        int[] y = new int[3];
        for (Asteroidi a : asteroidilista) {
            x = p.xpoints;
            y = p.ypoints;
            for (int i = 0; i < 3; i++) {
                if (a.getAsteroidiPolygoni().contains(x[i], y[i])) {
                    tormays = true;
                }
            }

        }
        if (tormays) {
            this.peli.getKirjanpitaja().setTila(-1);
        }
    }
    
}
