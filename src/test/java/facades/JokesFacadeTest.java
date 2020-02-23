package facades;

import dto.JokesDTO;
import entities.Jokes;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokesFacadeTest {

    private static EntityManagerFactory emf;
    private static JokesFacade facade;
    private Jokes j1, j2, j3, j4, j5;

    public JokesFacadeTest() {
    }

    @BeforeAll
    public static void setUpClassV() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = JokesFacade.getJokesFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        j1 = new Jokes("The way you shift, gives me sticky keys.");
        j2 = new Jokes("Why don't we go to my 127.0.0.1 and I'll give you sudo access?");
        j3 = new Jokes("Will you show me your source code if I make a formal pull request?");
        j4 = new Jokes("Oh you use IE? You must like it nice and slow.");
        j5 = new Jokes("Roses are #f00, Violets are #00f, if I show you my bar, will you show me your foo?");
                
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Jokes.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.persist(j4);
            em.persist(j5);
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
    public void testJokeCount() {
        assertEquals(5, facade.getJokesCount(), "Expects 5 rows in the database");
    }
    
    @Test
    public void testGetAllJokes() {
        List<JokesDTO> allJokes = facade.getAllJokes();
        assertThat(allJokes, hasSize(5));
    }
    
//    @Test
//    public void testGetJokeById() {
//        // Originally wanted to compare the content of the joke requested, with what was expected, but I can't figure out how.
//        List<JokesDTO> specificJoke = facade.getJokeById(4);
//        assertEquals(4, specificJoke.size(), "Expected that 1 joke with this ID is in the DB.");
//    }

}
