/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import asteroidit.domain.Ammus;
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
public class AmmusTest {
    
    public AmmusTest() {
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
    public void vaakaliike() {
        Ammus ammus = new Ammus(100,100,0);
        ammus.liiku();
        assertEquals(105, ammus.getX());
    }
    
    @Test
    public void pystyliike() {
        Ammus ammus = new Ammus(100,100,270);
        ammus.liiku();
        assertEquals(105, ammus.getY());
    }
}
