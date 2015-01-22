/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.gui;

import asteroidit.domain.Asteroidi;
import asteroidit.peli.Asteroidipeli;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Markku
 */
public class Piirtoalusta extends JPanel {
    

    private Asteroidipeli peli;
    
    public Piirtoalusta(Asteroidipeli asteroidit) {
        this.peli=asteroidit;

    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Asteroidi a:peli.getAsteroidilista()) {
           g.setColor(Color.BLACK);
           g.drawPolygon(a.getAsteroidiPolygoni()); 
        }
        
        
        g.setColor(Color.RED);
        g.fillPolygon(peli.getAlus().getAlusPolygoni());
    }

    public void paivita() {
        super.repaint();
    }
            
}
