/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.HeroDTO;
import entities.Hero;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author frede
 */
public class HeroFacadeTest {
    private static EntityManagerFactory emf;
    private static HeroFacade facade;
    private Hero h1, h2, h3;
    
    public HeroFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST,EMF_Creator.Strategy.DROP_AND_CREATE);
       facade = HeroFacade.getHeroFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        h1 = new Hero("Hans", 10, 10, 10, 10, 10, 10, "Hansemand", "Vestjydsk Comics");
        h2 = new Hero("Grethe", 20, 20, 20, 20, 20, 20, "The Destroyer", "aaa");
        h3 = new Hero("Ulla", 30, 30, 30, 30, 30, 30, "bbb", "ccc");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hero.deleteAllRows").executeUpdate();
            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    
    @Test
    public void testGetHeroCount(){
        assertEquals(facade.getHeroCount(), 3);
    }
    
    @Test
    public void testAddHero(){
        Hero h = new Hero("fff", 40, 40, 40, 40, 40, 40, "lll", "ooo");
        HeroDTO h4 = facade.addHero(h);
        assertThat(h4.getId(), notNullValue());
        assertEquals(facade.getHeroCount(), 4);
    }
    
    @Test
    public void testAllHeroes(){
        List<HeroDTO> heroes = facade.allHeroes();
        assertEquals(heroes.size(), 3);
    }
}
