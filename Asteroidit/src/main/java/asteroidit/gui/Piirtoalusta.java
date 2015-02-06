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

        status = peli.getKirjanpitaja().getTila();
        if (status == 0 || status == -1) {
            g.drawString("PELI LOPPUI", 150, 150);
        }

        g.fillPolygon(peli.getAlus().getAlusPolygoni());
        g.setColor(Color.DARK_GRAY);
        s = "Asteroidien määrä: " + peli.getAsteroidienMaara();
        g.drawString(s, 10, 10);
        s = "Ammusten määrä: " + peli.getAmmustenMaara();
        g.drawString(s, 10, 30);
        s = "PISTEET: " + peli.getKirjanpitaja().getPisteet();
        g.drawString(s, 10, 50);
//        s="SUUNTA: " + peli.getAlus().getSuunta();
//        g.drawString(s, 10, 70);
//        s="KULKUSUUNTA: " + peli.getAlus().getKulkusuunta();
//        g.drawString(s, 10, 90);
//        s="NOPEUS: " + peli.getAlus().getNopeus();
//        g.drawString(s, 10, 110);
    }

    public void paivita() {
        super.repaint();
    }

}
