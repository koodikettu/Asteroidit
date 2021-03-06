/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit;

import asteroidit.gui.Kayttoliittyma;
import asteroidit.peli.Asteroidipeli;
import javax.swing.SwingUtilities;

/**
 * Luokka sisältää main-metodin, joka luo pelin tärkeimmät komponentit, eli
 * asteroidipeli- ja kayttoliittyma-oliot.
 *
 * @author Markku
 */
public class Main {

    public static void main(String[] args) {

        Asteroidipeli asteroidipeli = new Asteroidipeli();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(asteroidipeli);

        SwingUtilities.invokeLater(kayttoliittyma);
        System.out.println("Käyttöliittymän luominen.");
        while (kayttoliittyma.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException ex) {
                System.out.println("Thread.sleep exception.");
            }
        }

        asteroidipeli.setPiirtoalusta(kayttoliittyma.getPiirtoalusta());
        asteroidipeli.setYlapaneeli(kayttoliittyma.getYlapaneeli());

        asteroidipeli.start();

    }

}
