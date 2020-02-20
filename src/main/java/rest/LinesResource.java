package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Cars;
import facades.CarsFacade;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

@Path("cars")
public class CarsResource {

    private static final EntityManagerFactory EMF
            = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);

    private static final CarsFacade FACADE = CarsFacade.getCarsFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCars() {
        return GSON.toJson(FACADE.getAllCars());
    }

    
    @GET
    @Path("/{manufactor}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getSpecificCarByManufactor(@PathParam("manufactor") String manufactor) {
       return GSON.toJson(FACADE.getCarsByManufactor(manufactor));
    }
    
//    @POST
//    @Path("/addcar/{car}")
//    public void createCar(@QueryParam("car") Cars car) {
//        FACADE.addCar(car);
//    }
    
}
