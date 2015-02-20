/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import java.awt.Polygon;

/**
 * Luokka sisältää pelaajan ampumien ammuksien käsittelyyn tarvittavat metodit.
 *
 * @author Markku
 */
public class Ammus {

    private static double AMMUKSEN_NOPEUS = 8;
    private double x, y, dx, dy;
    private int suunta;

    Polygon polygoni;

    public Ammus(int x, int y, int suunta) {
        this.x = (double) x;
        this.y = (double) y;
        dx = AMMUKSEN_NOPEUS * Math.cos(Math.toRadians(suunta));
        dy = -AMMUKSEN_NOPEUS * Math.sin(Math.toRadians(suunta));
        this.polygoni = new Polygon();
        this.suunta = suunta;
    }
    
    /**
     * Metodi liikuttaa ammusta sen nopeuden mukaisesti.
     */

    public void liiku() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
    
    /**
     * Metodi luo ammuksen koordinaattien perusteella ammusta ruudulla edustavan polygonin.
     * 
     * @return palauttaa muodostetun Polygon-olion
     */

    public Polygon getAmmusPolygoni() {
        this.polygoni.reset();
        this.polygoni.addPoint((int) Math.round(x) - 1, (int) Math.round(y) - 1);
        this.polygoni.addPoint((int) Math.round(x) + 1, (int) Math.round(y) - 1);
        this.polygoni.addPoint((int) Math.round(x) + 1, (int) Math.round(y) + 1);
        this.polygoni.addPoint((int) Math.round(x) - 1, (int) Math.round(y) + 1);
        return polygoni;
    }

}
