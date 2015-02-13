/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.peli.Asteroidipeli;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
    
    private int ruudunLeveys;
    private int ruudunKorkeus;

    private Piirtoalusta piirtoalusta;
    private Ylapaneeli ylapaneeli;
    private Nappaimistonkuuntelija nk;

    public Kayttoliittyma(Asteroidipeli asteroidipeli) {
        super();
        this.peli = asteroidipeli;
        
        frame = new JFrame("Asteroidit");
        ruudunLeveys = peli.getRuudunLeveys();
        ruudunKorkeus = peli.getRuudunKorkeus()+80;
        frame.setLayout(new BorderLayout(0, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(ruudunLeveys, ruudunKorkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        this.piirtoalusta = new Piirtoalusta(this.peli);
        
        this.ylapaneeli = new Ylapaneeli(this.peli);
        ylapaneeli.setPreferredSize(new Dimension(this.ruudunLeveys, 80));
        ylapaneeli.paivita();
        
        container.add(ylapaneeli, BorderLayout.NORTH);
        container.add(this.piirtoalusta, BorderLayout.CENTER);
        nk=new Nappaimistonkuuntelija(this.peli);
        frame.addKeyListener(nk);

    }
    
    public Nappaimistonkuuntelija getNappaimistonkuuntelija() {
        return nk;
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }
    
    public Ylapaneeli getYlapaneeli() {
        return this.ylapaneeli;
    }

    public JFrame getFrame() {
        return frame;
    }
}
