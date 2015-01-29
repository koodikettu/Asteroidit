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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Markku
 */
public class Asteroidipeli extends Timer implements ActionListener {

    private final int REUNUKSEN_LEVEYS = 50;
    private final int PAIVITYSVALI = 20;
    private int ruudunLeveys;
    private int ruudunKorkeus;
    private Alus alus;
    private boolean vasenNuolinappain;
    private boolean oikeaNuolinappain;
    private boolean valilyonti;

    private ArrayList<Asteroidi> asteroidilista = new ArrayList<Asteroidi>();
    private ArrayList<Asteroidi> poistettavatAsteroidit = new ArrayList<Asteroidi>();
    private ArrayList<Ammus> ammuslista = new ArrayList<Ammus>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private Piirtoalusta piirtoalusta;

    public Asteroidipeli() {
        super(1000, null);

        this.ruudunKorkeus = 600;
        this.ruudunLeveys = 1000;
        this.alus = new Alus(this.ruudunLeveys / 2, this.ruudunKorkeus / 2, 0, this);

        asteroidilista.add(new Asteroidi(50, 50, 0.5, 0.5));
        asteroidilista.add(new Asteroidi(600, -30, 2.5, -0.5));
        asteroidilista.add(new Asteroidi(1000, -30, -2.5, 1.0));
        asteroidilista.add(new Asteroidi(-50, 300, -1.0, -1.5));

        vasenNuolinappain = false;
        oikeaNuolinappain = false;
        valilyonti = false;

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
        if (oikeaNuolinappain) {
            alus.kaanna(-1);
        }
        if (vasenNuolinappain) {
            alus.kaanna(1);
        }
        if (valilyonti) {
            alus.ammu();
        }
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
                }
            }
        }
        for (Asteroidi ast : this.poistettavatAsteroidit) {
            this.asteroidilista.remove(ast);
        }
        this.poistettavatAsteroidit.clear();

    }

}
