package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Cars;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarsResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/ca1/api";
    private Cars c1, c2, c3, c4, c5, c6, c7;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    //private static Gson gson;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
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

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/cars/all").then().statusCode(200);
    }

//    @Test
//    public void testShowAllCars() throws Exception {
//        given()
//        .contentType("application/json")
//        .get("/cars/all").then()
//        .assertThat()
//        .statusCode(HttpStatus.OK_200.getStatusCode())
//        .body("count", equalTo(2));   
//    }
    
    @Test
    //@Ignore
    public void testGetSpecificManufactor() throws Exception {
        ArrayList expected = new ArrayList();
        expected.add("i8");
        given()
                .contentType("application/json")
                .get("/cars/BMW").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("model", equalTo(expected));
                //.body("model", equalTo(GSON.toJson(car.getModel())));
                //.body("model", equalTo(GSON.toJson("i8")));
    }
    
    @Test
    public void testGetSpecificManufactor1() throws Exception {
        ArrayList test = new ArrayList();
        test.add("i8");
        given()
                .contentType("application/json")
                .get("/cars/BMW").then().log().body().and().assertThat().body("model", equalTo(test));
    }
}
