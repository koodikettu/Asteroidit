/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.peli.Asteroidipeli;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Luokka vastaa pelitilanteen piirtämisestä graafiseen käyttöliittymään.
 *
 * @author Markku
 */
public class Piirtoalusta extends JPanel {

    private int status;
    private String s;

    private Asteroidipeli peli;

    public Piirtoalusta(Asteroidipeli a) {
        this.peli = a;
    }

    /**
     * Metodi piirtää pelitilanteen.
     *
     * @param g Graphics-olio
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Asteroidi a : peli.getAsteroidilista()) {
            g.setColor(Color.BLACK);
            g.fillPolygon(a.getAsteroidiPolygoni());

        }

        for (Ammus a : peli.getAmmuslista()) {
            g.setColor(Color.BLUE);
            g.drawPolygon(a.getAmmusPolygoni());
        }

        g.setColor(Color.RED);

        g.fillPolygon(peli.getAlus().getAlusPolygoni());

    }

    /**
     * Metodi päivittää pelitilanteen ruudulle.
     */
    public void paivita() {
        super.repaint();
    }

}
