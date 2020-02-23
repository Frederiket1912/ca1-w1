package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Cars;
import facades.CarsFacade;
import utils.EMF_Creator;
import facades.JokesFacade;
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

@Path("jokes")
public class JokesResource {

    private static final EntityManagerFactory EMF
            = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);

    private static final JokesFacade FACADE = JokesFacade.getJokesFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        return GSON.toJson(FACADE.getAllJokes());
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getSpecifcJokeById(@PathParam("id") int id) {
       return GSON.toJson(FACADE.getJokeById(id));
    }
    
}
