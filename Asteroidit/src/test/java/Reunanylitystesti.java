/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.peli;

import asteroidit.domain.Asteroidi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markku
 */
public class Reunanylitystesti {
    
    public Reunanylitystesti() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void reunanylitystesti1() {
        Asteroidipeli peli = new Asteroidipeli();
        Asteroidi a =new Asteroidi(-500, 1300, 0.5, 0.5);
        peli.hoidaReunanYlitykset(a);
        assertEquals(a.getX(),1050);
        assertEquals(a.getY(),-50);
        
        
    }
}
