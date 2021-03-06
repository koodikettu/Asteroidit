/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.peli.Asteroidipeli;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Luokka sisältää pelin käyttöliittymän yläosassa olevan paneelin, jossa mm.
 * pisteet.
 *
 * @author Markku
 */
public class Ylapaneeli extends JPanel {

    private int status;
    private String s;

    private Asteroidipeli peli;

    public Ylapaneeli(Asteroidipeli a) {
        this.peli = a;
    }

    /**
     * Metodi piirtää yläpaneelin.
     *
     * @param g Graphics-olio
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.peli.getRuudunLeveys(), 80);

        status = peli.getKirjanpitaja().getTila();
        if (status == 0 || status == -1) {
            g.setColor(Color.BLACK);
            g.drawString("PELI LOPPUI", 250, 20);
            g.drawString("PAINA S ALOITTAAKSESI UUDEN PELIN", 250, 40);
            g.drawString("PAINA Q LOPETTAAKSESI", 250, 60);
        }

        g.setColor(Color.DARK_GRAY);
        s = "Asteroidien määrä: " + peli.getAsteroidilista().size();
        g.drawString(s, 10, 20);
        s = "Ammusten määrä: " + peli.getAmmuslista().size();
        g.drawString(s, 10, 40);
        s = "PISTEET: " + peli.getKirjanpitaja().getPisteet();
        g.drawString(s, 10, 60);
    }

    /**
     * Metodi päivittää yläpaneelin
     */
    public void paivita() {
        super.repaint();
    }

}
