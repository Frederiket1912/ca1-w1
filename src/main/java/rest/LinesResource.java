package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.LinesFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

@Path("lines")
public class LinesResource {

    private static final EntityManagerFactory EMF
            = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);

    private static final LinesFacade FACADE = LinesFacade.getLinesFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllLines() {
        return GSON.toJson(FACADE.getAllLines());
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getSpecifcLineById(@PathParam("id") int id) {
       return GSON.toJson(FACADE.getLineById(id));
    }
    
}
