/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import java.awt.Polygon;

/**
 *
 * @author Markku
 */
public class Asteroidi {

    double x, y;
    double dx, dy;
    int suunta;
    int nopeus;
    Polygon polygoni;

    public Asteroidi(double x, double y, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        polygoni = new Polygon();
    }
    
    public void laskePolygoni() {
        this.polygoni.reset();
        this.polygoni.addPoint((int) Math.round(x), (int) Math.round(y - 40));
        this.polygoni.addPoint((int) Math.round(x + 25), (int) Math.round(y + 10));
        this.polygoni.addPoint((int) Math.round(x - 10), (int) Math.round(y + 25));
        this.polygoni.addPoint((int) Math.round(x - 36), (int) Math.round(y + 3));
    }

    public Polygon getAsteroidiPolygoni() {
        
        return this.polygoni;
    }

    public void liiku() {
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return (int) this.x;
    }

    public int getY() {
        return (int) this.y;
    }

    public void setX(int x) {
        this.x = (double) x;
    }

    public void setY(int y) {
        this.y = (double) y;
    }

}
