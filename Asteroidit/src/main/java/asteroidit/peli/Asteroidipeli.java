/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.peli;

import asteroidit.domain.Alus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
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
    private int tila = 1;
    private int pisteet = 0;
    private int asteroidienNopeus = 5;
    private Alus alus;
    private boolean vasenNuolinappain;
    private boolean oikeaNuolinappain;
    private boolean valilyonti;
    Random random;

    private ArrayList<Asteroidi> asteroidilista = new ArrayList<Asteroidi>();
    private ArrayList<Asteroidi> poistettavatAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Asteroidi> uudetAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Ammus> ammuslista = new ArrayList<Ammus>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private Piirtoalusta piirtoalusta;

    public Asteroidipeli() {
        super(1000, null);

        this.ruudunKorkeus = 600;
        this.ruudunLeveys = 1000;
        this.alus = new Alus(this.ruudunLeveys / 2, this.ruudunKorkeus / 2, 90);

        vasenNuolinappain = false;
        oikeaNuolinappain = false;
        valilyonti = false;

        random = new Random();

        asteroidilista.add(uusiAsteroidi(random));
        asteroidilista.add(uusiAsteroidi(random));
        asteroidilista.add(uusiAsteroidi(random));
        asteroidilista.add(uusiAsteroidi(random));

        addActionListener(this);
        setInitialDelay(1000);
    }

    public int getRuudunLeveys() {
        return this.ruudunLeveys;
    }

    public int getRuudunKorkeus() {
        return this.ruudunKorkeus;
    }

    public Alus getAlus() {
        return this.alus;
    }

    public Asteroidi uusiAsteroidi(Random random) {
        int x, y;
        double dx, dy;
        x = random.nextInt(4);
        if (x == 0) {
            x = random.nextInt(ruudunLeveys + 2 * REUNUKSEN_LEVEYS);
            y = -REUNUKSEN_LEVEYS;
            dx = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            dy = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            return (new Asteroidi(x, y, dx, dy));
        } else if (x == 1) {
            y = random.nextInt(ruudunKorkeus + 2 * REUNUKSEN_LEVEYS);
            x = ruudunLeveys + REUNUKSEN_LEVEYS;
            dx = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            dy = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            return (new Asteroidi(x, y, dx, dy));
        } else if (x == 2) {
            x = random.nextInt(ruudunLeveys + 2 * REUNUKSEN_LEVEYS);
            y = ruudunKorkeus + REUNUKSEN_LEVEYS;
            dx = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            dy = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            return (new Asteroidi(x, y, dx, dy));
        } else {
            y = random.nextInt(ruudunKorkeus + 2 * REUNUKSEN_LEVEYS);
            x = -REUNUKSEN_LEVEYS;
            dx = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            dy = random.nextFloat() * this.asteroidienNopeus - 0.5 * this.asteroidienNopeus;
            return (new Asteroidi(x, y, dx, dy));
        }
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

    public int getTila() {
        return this.tila;
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

    public int getPisteet() {
        return this.pisteet;
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

        if (tila == -1) {
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
            hoidaReunanYlitykset(a);
            a.laskePolygoni();

        }

        for (Ammus a : this.ammuslista) {
            a.liiku();
            if (!onRuudulla(a)) {
                this.poistettavatAmmukset.add(a);
            }

        }
        for (Ammus a : this.poistettavatAmmukset) {
            this.ammuslista.remove(a);
        }
        tutkiTormaykset();
        tutkiAluksenTormaykset();
        this.poistettavatAmmukset.clear();

        alus.laskeAlusPolygoni();
        piirtoalusta.paivita();
        setDelay(PAIVITYSVALI);

    }

    public void hoidaReunanYlitykset(Asteroidi a) {
        if (a.getX() > this.ruudunLeveys + REUNUKSEN_LEVEYS) {
            a.setX(-REUNUKSEN_LEVEYS);
        }
        if (a.getX() < -REUNUKSEN_LEVEYS) {
            a.setX(this.ruudunLeveys + REUNUKSEN_LEVEYS);
        }
        if (a.getY() > this.ruudunKorkeus + REUNUKSEN_LEVEYS) {
            a.setY(-REUNUKSEN_LEVEYS);
        }
        if (a.getY() < -REUNUKSEN_LEVEYS) {
            a.setY(this.ruudunKorkeus + REUNUKSEN_LEVEYS);
        }
    }

    public boolean onRuudulla(Ammus a) {
        int x = a.getX();
        int y = a.getY();
        if (y < 0 || y > ruudunKorkeus) {
            return false;
        } else if (x < 0 || x > ruudunLeveys) {
            return false;
        }
        return true;
    }

    public void tutkiTormaykset() {
        int x, y;
        for (Ammus a : this.ammuslista) {
            x = a.getX();
            y = a.getY();
            for (Asteroidi ast : this.asteroidilista) {
                if (ast.getAsteroidiPolygoni().contains(x, y)) {
                    this.poistettavatAsteroidit.add(ast);
                    this.poistettavatAmmukset.add(a);
                    this.pisteet += 10;
                    if (this.pisteet % 100 == 0) {
                        this.asteroidienNopeus++;
                    }
                    if (this.pisteet % 100 == 0) {
                        this.uudetAsteroidit.add(uusiAsteroidi(this.random));
                    }
                }
            }
        }
        for (Asteroidi ast : this.poistettavatAsteroidit) {
            this.asteroidilista.remove(ast);
            this.asteroidilista.add(uusiAsteroidi(this.random));
        }
        for (Ammus a : this.poistettavatAmmukset) {
            this.ammuslista.remove(a);
        }
        this.poistettavatAsteroidit.clear();
        this.poistettavatAmmukset.clear();

    }

    public void tutkiAluksenTormaykset() {
        Polygon p = this.alus.getAlusPolygoni();
        boolean tormays = false;
        int[] x = new int[3];
        int[] y = new int[3];
        for (Asteroidi a : this.asteroidilista) {
            x = p.xpoints;
            y = p.ypoints;
            for (int i = 0; i < 3; i++) {
                if (a.getAsteroidiPolygoni().contains(x[i], y[i])) {
                    tormays = true;
                }
            }

        }
        if (tormays) {
            this.tila = -1;
        }
    }

}
