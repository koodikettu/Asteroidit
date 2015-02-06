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
 * Luokka sisältää metodit, joilla käsitellään ruudulla liikkuvien kohteiden välisiä törmäyksiä.
 * 
 * @author Markku
 */
public class TormaystenKasittelija {

    private Asteroidipeli peli;
    private ArrayList<Asteroidi> poistettavatAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private ArrayList<Asteroidi> uudetAsteroidit = new ArrayList<Asteroidi>();
    private Asteroidi asteroidi;

    public TormaystenKasittelija(Asteroidipeli peli) {
        this.peli = peli;
    }

    public void tutkiTormaykset(ArrayList<Ammus> ammuslista, ArrayList<Asteroidi> asteroidilista) {
        int x, y;
        int korvaaviaAsteroideja=0;

        for (Ammus a : ammuslista) {
            x = a.getX();
            y = a.getY();
            for (Asteroidi ast : asteroidilista) {
                if (ast.getAsteroidiPolygoni().contains(x, y)) {
                    this.poistettavatAsteroidit.add(ast);
                    this.poistettavatAmmukset.add(a);
                    this.peli.getKirjanpitaja().kasvataPisteita(10);
                    if (this.peli.getKirjanpitaja().getPisteet() % 200 == 0) {
                        this.peli.kasvataAsteroidienNopeutta();
                    }
                    if (this.peli.getKirjanpitaja().getPisteet() % 200 == 0) {
                        korvaaviaAsteroideja++;
                    }
                }
            }
        }
        for (Asteroidi ast : this.poistettavatAsteroidit) {
            asteroidilista.remove(ast);
            korvaaviaAsteroideja++;
        }
        for (Ammus a : this.poistettavatAmmukset) {
            ammuslista.remove(a);
        }
        for(int i=0;i<korvaaviaAsteroideja;i++)
            this.peli.uusiAsteroidi();
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
                    System.out.println("TÖRMÄYS!");
                    System.out.println("Asteroidin koordinaatit: ");
                    for(int t=0;t<a.getAsteroidiPolygoni().npoints;t++) {
                        System.out.println(a.getAsteroidiPolygoni().xpoints[t] + ":" + a.getAsteroidiPolygoni().ypoints[t]);
                        
                    }
                    System.out.println("Asteroidin koord:");
                    System.out.println(x[i] + ":" + y[i]);
                    tormays = true;
                }
            }

        }
        if (tormays) {
            this.peli.getKirjanpitaja().setTila(-1);
        }
    }

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

}
