package facades;

import dto.CarsDTO;
import entities.Cars;
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
public class FacadeCarsTest {

    private static EntityManagerFactory emf;
    private static CarsFacade facade;
    private Cars c1, c2, c3, c4, c5, c6, c7;

    public FacadeCarsTest() {
    }

    @BeforeAll
    public static void setUpClassV() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = CarsFacade.getCarsFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        c1 = new Cars("Tesla", 2020, "Model X", 779800, 28);
        c2 = new Cars("BMW", 2014, "i8", 857000, 5);
        c3 = new Cars("Audi", 2020, "R8 4,2 FSi Spyder quattro", 1279900, 1);
        c4 = new Cars("Audi", 2010, "A8", 1655662, 15);
        c5 = new Cars("Sedan", 2020, "Camry", 158750, 6);
        c6 = new Cars("Volvo", 2005, "V70", 1152285, 5);
        c7 = new Cars("Volvo", 2020, "XC90", 1152285, 70);
                
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(c5);
            em.persist(c6);
            em.persist(c7);
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
    public void testCarCount() {
        assertEquals(7, facade.getCarsCount(), "Expects 7 rows in the database");
    }
    
    @Test
    public void testCreationOfCar() {
        facade.addCar(new Cars("testCar", 4020, "The Future", 99, 100));
        assertEquals(8, facade.getCarsCount(), "Expects 8 rows in the database");
    }
    
    @Test
    public void testGetAllCars() {
        List<CarsDTO> allCars = facade.getAllCars();
        assertThat(allCars, hasSize(7));
    }

}
