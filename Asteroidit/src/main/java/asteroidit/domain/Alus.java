/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import java.awt.Polygon;

/**
 * Luokka sisältää pelaajan ohjaaman avaruusaluksen käsittelyyn tarvittavat
 * metodit.
 *
 * @author Markku
 */
public class Alus implements Liikkuva {

    private double x, y, suunta, nopeus, dx, dy, kulkusuunta;
    private static final int KAANTYMISNOPEUS = 5;
    private static final long AMMUSTEN_VALI = 100;
    private static final double NOPEUDENKASVATUSVAKIO = 0.5;
    private static final double HIDASTUMISVAKIO = 0.1;
    private long edellisenAmmuksenAmpumisaika = -1;
    private Polygon alusPolygoni;

    public Alus(int x, int y, int suunta) {
        this.x = (double) x;
        this.y = (double) y;
        this.suunta = suunta;
        this.nopeus = 0;
        this.kulkusuunta = suunta;
        this.alusPolygoni = new Polygon();
    }

    /**
     * Metodi kääntää avaruusalusta myötä- tai vastapäivään parametrin maara
     * ilmoittaman asteluvun.
     *
     * @param maara
     */
    public void kaanna(int maara) {
        suunta += maara * KAANTYMISNOPEUS;
        if (suunta > 359) {
            suunta -= 360;
        }
        if (suunta < 0) {
            suunta += 360;
        }
    }

    /**
     * Metodi liikuttaa alusta eteen- tai taaksepäin, jos aluksen nopeus > 0.
     * Metodi myös hoitaa vauhdin tasaisen hidastumisen.
     *
     */
    public void liiku() {
        this.x += nopeus * Math.cos(Math.toRadians(kulkusuunta));
        this.y -= nopeus * Math.sin(Math.toRadians(kulkusuunta));
        this.nopeus = nopeus - HIDASTUMISVAKIO;
        if (this.nopeus > 0) {
            this.nopeus -= HIDASTUMISVAKIO;
        } else if (this.nopeus < 0) {
            this.nopeus += HIDASTUMISVAKIO;
        }
        if (Math.abs(this.nopeus) < HIDASTUMISVAKIO) {
            this.nopeus = 0;
        }
    }

    /**
     * Metodi muuttaa aluksen nopeutta.
     *
     * @param eteenVaiTaakse true, jos kiihtyvyys on eteenpäin; false, jos
     * kiihtyvyys taaksepäin
     */
    public void kiihdyta(boolean eteenVaiTaakse) {
        double a, b;
        if (eteenVaiTaakse) {
            this.nopeus = laskeUusiNopeus(this.nopeus, this.kulkusuunta, NOPEUDENKASVATUSVAKIO, this.suunta);
        }
        if (!eteenVaiTaakse) {
            this.nopeus = laskeUusiNopeus(this.nopeus, this.kulkusuunta, NOPEUDENKASVATUSVAKIO, this.suunta - 180);
        }
        laskeUusiSuunta();
    }

    /**
     * Metodi laskee aluksen kärkipisteet sisältävän polygonin aluksen sijainnin
     * perusteella.
     */
    public void laskeAlusPolygoni() {
        double apu = Math.toRadians(150);
        double radiaanit = Math.toRadians(this.suunta);
        alusPolygoni.reset();
        // keulakulma
        double x1 = Math.cos(radiaanit) * 30.0 + this.x;
        double y1 = -Math.sin(radiaanit) * 30.0 + this.y;
        alusPolygoni.addPoint((int) Math.round(x1), (int) Math.round(y1));
        // vasen kulma
        x1 = Math.cos(radiaanit + apu) * 30.0 + this.x;
        y1 = -Math.sin(radiaanit + apu) * 30.0 + this.y;
        alusPolygoni.addPoint((int) Math.round(x1), (int) Math.round(y1));
        // oikea kulma
        x1 = Math.cos(radiaanit - apu) * 30.0 + this.x;
        y1 = -Math.sin(radiaanit - apu) * 30.0 + this.y;
        alusPolygoni.addPoint((int) Math.round(x1), (int) Math.round(y1));
    }

    public Polygon getAlusPolygoni() {
        return alusPolygoni;
    }

    /**
     * Metodi toteuttaa aluksen ampumistoiminnallisuuden. Jos edellisen ammuksen
     * ampumisesta on kulunut minimiaika, metodi luo uuden ammuksen, jonka
     * koordinaatit ja suunta määrittyvät aluksen sijainnin ja suunnan
     * perusteella.
     *
     * @return Ammus-luokan olio, jonka alus ampuu
     */
    public Ammus ammu() {
        long aikakoodi = System.currentTimeMillis();
        if (aikakoodi - edellisenAmmuksenAmpumisaika < AMMUSTEN_VALI) {
            return null;
        }
        double ax = Math.cos(Math.toRadians(this.suunta)) * 35.0;
        ax += this.x;
        double ay = -Math.sin(Math.toRadians(this.suunta)) * 35.0;
        ay += this.y;
        edellisenAmmuksenAmpumisaika = aikakoodi;
        return (new Ammus((int) ax, (int) ay, (int) this.suunta));
    }

    /**
     * Metodi laskee yhteen kaksi vektoria, jotka annetaan arvopareina pituus ja
     * suunta. Metodia käytetään laskemaan yhteen aluksen senhetkinen
     * nopeusvektori ja siihen kiihdyttämisen seurauksena lisättävä muutos.
     *
     * @param pituus1 Ensimmäisen vektorin pituus
     * @param suunta1 Ensimmäisen vektorin suunta
     * @param pituus2 Toisen vektorin pituus asteina
     * @param suunta2 Toisen vektorin suunta asteina
     * @return tulosvektorin pituus
     */
    public double laskeUusiNopeus(double pituus1, double suunta1, double pituus2, double suunta2) {
        this.dx = pituus1 * Math.cos(Math.toRadians(suunta1)) + pituus2 * Math.cos(Math.toRadians(suunta2));
        this.dy = -pituus1 * Math.sin(Math.toRadians(suunta1)) - pituus2 * Math.sin(Math.toRadians(suunta2));
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * Metodi palauttaa edellisen metodin yhteenlaskemien vektoreiden
     * summavektorin suunnan.
     */
    public void laskeUusiSuunta() {
        kulkusuunta = Math.toDegrees(Math.atan(-this.dy / this.dx));
        if (this.dx < 0) {
            kulkusuunta+=180;
        }
    }
    
    public double getSuunta() {
        return this.suunta;
    }

    public void setSuunta(double suunta) {
        this.suunta = suunta;
    }

    public double getKulkusuunta() {
        return this.kulkusuunta;
    }

    public double getNopeus() {
        return this.nopeus;
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
}
