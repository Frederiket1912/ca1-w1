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

    
    public List<HeroDTO> addHeroes(List<Hero> heroes){
        EntityManager em = emf.createEntityManager();
        List<HeroDTO> heroDTOs = new ArrayList();
        try {
            for(Hero h : heroes){
                em.getTransaction().begin();
                em.persist(h);
                em.getTransaction().commit();
                heroDTOs.add(new HeroDTO(h));
            }
            return heroDTOs;            
        }finally {
            em.close();
        }
    }
    
    
}


