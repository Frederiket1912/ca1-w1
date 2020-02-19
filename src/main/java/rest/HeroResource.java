package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HeroDTO;
import facades.FacadeExample;
import facades.HeroFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("hero")
public class HeroResource {
//    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/startcode",
//                "dev",
//                "ax2",
//                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    
    private static final HeroFacade FACADE =  HeroFacade.getHeroFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllHeroes() {
        List<HeroDTO> heroes = FACADE.allHeroes();
        return GSON.toJson(heroes);
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getHeroCount();
        return "{\"count\":"+count+"}"; 
    }
    
    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populateDB(){
        FACADE.populateDBWithHeroes();
        return "Database populated with heroes";
    }
}
