/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.HeroDTO;
import entities.Hero;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author frede
 */
public class HeroFacade {
    private static HeroFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private HeroFacade() {}
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HeroFacade getHeroFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HeroFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getHeroCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long heroCount = (long)em.createQuery("SELECT COUNT(h) FROM Hero h").getSingleResult();
            return heroCount;
        }finally{  
            em.close();
        }       
    }

    // bruger ikke DTO i argumentet fordi jeg først havde tænkt mig at få fat i 
    // heroes fra en anden API
    public HeroDTO addHero(Hero hero){
        EntityManager em = emf.createEntityManager();
        try {
                em.getTransaction().begin();
                em.persist(hero);
                em.getTransaction().commit();
            
            return new HeroDTO(hero);            
        }finally {
            em.close();
        }
    }
     
    public List<HeroDTO> allHeroes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hero> query
                    = em.createQuery("Select h from Hero h", Hero.class);
            List<Hero> heroes = query.getResultList();
            List<HeroDTO> herodtos = new ArrayList<>();
            for (Hero h : heroes) {
                herodtos.add(new HeroDTO(h));
            }
            return herodtos;
        } finally {
            em.close();
        }
    }
    
    public Hero populateDBWithHeroes(){
        Hero h1 = new Hero("A-Bomb", 38, 100, 17, 80, 24, 64, "Richard Milhouse Jones", "Marvel Comics");
        Hero h2 = new Hero("Cloak", 63, 10, 47, 64, 100, 56, "Tyrone Johnson", "Marvel Comics");
        Hero h3 = new Hero("Daphne Powell", 38, 10, 8, 10, 56, 10, "Daphne Powell", "ABC Studios");
        Hero h4 = new Hero("Flash", 63, 10, 100, 50, 68, 32, "Jay Garrick", "DC Comics");
        EntityManager em = emf.createEntityManager();
        try {
                em.getTransaction().begin();
                em.persist(h1);
                em.persist(h2);
                em.persist(h3);
                em.persist(h4);
                em.getTransaction().commit();  
                return h1;
        }finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
        HeroFacade hf = HeroFacade.getHeroFacade(emf);
        hf.populateDBWithHeroes();
    }
}


