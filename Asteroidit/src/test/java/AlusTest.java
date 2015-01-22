/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import asteroidit.domain.Alus;
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
public class AlusTest {
    
    public AlusTest() {
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
    public void kaantotestiOikea() {
        Alus alus = new Alus(100,100,359);
        alus.kaanna(1);
        
        assertEquals(7, alus.getSuunta());
    }
    public void kaantotestiVasen() {
        Alus alus = new Alus(100,100,5);
        alus.kaanna(-1);
        
        assertEquals(357, alus.getSuunta());
    }
}
