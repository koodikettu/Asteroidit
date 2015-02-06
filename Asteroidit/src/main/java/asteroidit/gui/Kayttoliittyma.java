/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.peli.Asteroidipeli;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Luokka luo pelin käyttämän graafien käyttöliittymän.
 * 
 * @author Markku
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Asteroidipeli peli;

    private Piirtoalusta piirtoalusta;


    public Kayttoliittyma(Asteroidipeli asteroidipeli) {
        super();
        this.peli = asteroidipeli;
    }

    @Override
    public void run() {
        frame = new JFrame("Asteroidit");
        int ruudunLeveys = peli.getRuudunLeveys();
        int ruudunKorkeus = peli.getRuudunKorkeus();

        frame.setPreferredSize(new Dimension(ruudunLeveys, ruudunKorkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        // Huom! Luo ensin piirtoalusta jonka lisäät container-olioon
        // Luo vasta tämän jälkeen näppäimistönkuuntelija, jonka lisäät frame-oliolle
        this.piirtoalusta = new Piirtoalusta(this.peli);
        container.add(this.piirtoalusta);

        frame.addKeyListener(new Nappaimistonkuuntelija(this.peli));
        
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
