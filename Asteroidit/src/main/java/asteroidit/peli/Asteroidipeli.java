/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.peli;

import asteroidit.domain.Alus;
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

    private ArrayList<Asteroidi> asteroidilista = new ArrayList<Asteroidi>();
    private Piirtoalusta piirtoalusta;

    public Asteroidipeli(int delay, ActionListener listener) {
        super(delay, listener);

        this.ruudunKorkeus = 600;
        this.ruudunLeveys = 1000;
        this.alus = new Alus(this.ruudunLeveys / 2, this.ruudunKorkeus / 2, 180);

        asteroidilista.add(new Asteroidi(50, 50, 0.5, 0.5));
        asteroidilista.add(new Asteroidi(600, -30, 2.5, -0.5));
        asteroidilista.add(new Asteroidi(1000, -30, -2.5, 1.0));
        asteroidilista.add(new Asteroidi(-50, 300, -1.0, -1.5));

        addActionListener(this);
        setInitialDelay(delay);
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

    public ArrayList<Asteroidi> getAsteroidilista() {
        return this.asteroidilista;
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Asteroidi a : this.asteroidilista) {
            a.liiku();
            hoidaReunanYlitykset(a);
            a.laskePolygoni();

        }
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

}
