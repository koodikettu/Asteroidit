/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

/**
 * Rajapinta, joka kokoaa yhteen oliot, jotka liikkuvat ruudulla siten,
 * että poistuttuaan yhdeltä laidalta ne palaavat toiselta.
 * 
 * @author Markku
 */
public interface Liikkuva {
    public int getX();
    
    public int getY();
    
    public void setX(int x);
    
    public void setY(int x);
}
