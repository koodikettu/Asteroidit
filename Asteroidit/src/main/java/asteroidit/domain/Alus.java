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
public class Alus {

    private int x, y;
    private int suunta;
    private final int KAANTYMISNOPEUS=8;
    double keula_x = 0, keula_y = -30;
    double vasen_x = 15, vasen_y = -24;
    double oikea_x = 15, oikea_y = 24;

    Polygon alus_polygoni = new Polygon();

    public Alus(int x, int y, int suunta) {
        this.x = x;
        this.y = y;
        this.suunta = suunta;

    }

    public void kaanna(int maara) {
        suunta += maara * KAANTYMISNOPEUS;
        if (suunta > 359) {
            suunta -= 360;
        }
        if (suunta < 0) {
            suunta += 360;
        }
    }
    
    public int getSuunta() {
        return this.suunta;
    }
    
    public void setSuunta(int suunta) {
        this.suunta=suunta;
    }

    public void laskeAlusPolygoni() {
        alus_polygoni.reset();
        double x1, y1;

        double radiaanit;
        radiaanit = Math.toRadians(this.suunta);
        double apu = Math.toRadians(150);
        // kärkikulma
        x1 = Math.sin(radiaanit) * 30.0;
        x1 += this.x;
        y1 = Math.cos(radiaanit) * 30.0;
        y1 += this.y;
        alus_polygoni.addPoint((int) Math.round(x1), (int) Math.round(y1));
        // vasen kulma
        x1 = Math.sin(radiaanit - apu) * 30.0;
        x1 += this.x;
        y1 = Math.cos(radiaanit - apu) * 30.0;
        y1 += this.y;
        alus_polygoni.addPoint((int) Math.round(x1), (int) Math.round(y1));
        // oikea kulma
        x1 = Math.sin(radiaanit + apu) * 30.0;
        x1 += this.x;
        y1 = Math.cos(radiaanit + apu) * 30.0;
        y1 += this.y;
        alus_polygoni.addPoint((int) Math.round(x1), (int) Math.round(y1));
    }
    
    public Polygon getAlusPolygoni() {
        return alus_polygoni;
    }

}
