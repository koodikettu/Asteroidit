/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import java.awt.Polygon;
import java.util.Random;

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
    
    public void alusta(Random random, int ruudunLeveys, int ruudunKorkeus, int reunuksenLeveys, int nopeus) {
        int i, suunta;
        double dx, dy;
        i = random.nextInt(4);
        if (i == 0) {
            this.x = random.nextInt(ruudunLeveys + 2 * reunuksenLeveys);
            this.y = -reunuksenLeveys;
        } else if (i == 1) {
            this.y = random.nextInt(ruudunKorkeus + 2 * reunuksenLeveys);
            this.x = ruudunLeveys + reunuksenLeveys;


        } else if (i == 2) {
            this.x = random.nextInt(ruudunLeveys + 2 * reunuksenLeveys);
            this.y = ruudunKorkeus + reunuksenLeveys;
            
        } else {
            this.y = random.nextInt(ruudunKorkeus + 2 * reunuksenLeveys);
            this.x = -reunuksenLeveys;

        }
        suunta=random.nextInt(360);
        this.dx = Math.cos(Math.toRadians(suunta)) * (random.nextFloat() * nopeus + 1);
        this.dy = Math.sin(Math.toRadians(suunta)) * (random.nextFloat() * nopeus + 1);
    }
    


}
