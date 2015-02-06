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
public class Asteroidi implements Liikkuva {

    private double x, y;
    private double dx, dy;
    private final int KULMIEN_MINIMIMAARA=4;
    private final int KULMIEN_MAKSIMIMAARA=7;
    private final int KULMAN_VAIHTELU=15;
    private final int SADE_MIN=25;
    private final int SADE_MAX=60;
    private int suunta;
    private int nopeus;
    private Polygon muoto;
    private Polygon polygoni;
    Random random;

    public Asteroidi(double x, double y, double dx, double dy, Random random) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.random=random;
        this.muoto = arvoMuoto();
        this.polygoni = new Polygon();

    }
    
    public Polygon arvoMuoto() {
        Polygon p = new Polygon();
        int apu, sade, kulma;
        double px, py;
        int kulmienMaara=KULMIEN_MINIMIMAARA+this.random.nextInt(KULMIEN_MAKSIMIMAARA-KULMIEN_MINIMIMAARA+1);
        for(int i=0;i<kulmienMaara;i++) {
            sade=SADE_MIN+random.nextInt(SADE_MAX-SADE_MIN+1);
            apu=random.nextInt(KULMAN_VAIHTELU);
            apu=apu-KULMAN_VAIHTELU/2;
            kulma=(int) i*360/kulmienMaara+apu;
            px=(int) sade*Math.cos(Math.toRadians(kulma));
            py=(int) -sade*Math.sin(Math.toRadians(kulma));
            p.addPoint((int) px,(int) py);
        }
            
        
        
        return p;
    }
    
    public void laskePolygoni() {
        this.polygoni.reset();
        for(int i=0;i<this.muoto.npoints;i++) {
            this.polygoni.addPoint(this.muoto.xpoints[i]+(int) this.x, this.muoto.ypoints[i]+(int) this.y);
        }
    
    }

    public Polygon getAsteroidiPolygoni() {
        
        return this.polygoni;
    }

    public void liiku() {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public int getX() {
        return (int) this.x;
    }

    @Override
    public int getY() {
        return (int) this.y;
    }

    @Override
    public void setX(int x) {
        this.x = (double) x;
    }

    @Override
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
        float apu=random.nextFloat();
        this.dx = Math.cos(Math.toRadians(suunta)) * (apu * nopeus + 1);
        this.dy = Math.sin(Math.toRadians(suunta)) * (apu * nopeus + 1);
    }
    


}
