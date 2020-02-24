package facades;

import dto.JokesDTO;
import entities.Jokes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class JokesFacade {

    private static JokesFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokesFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokesFacade getJokesFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getJokesCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokesCount = (long) em.createQuery("SELECT COUNT(j) FROM Jokes j").getSingleResult();
            return jokesCount;
        } finally {
            em.close();
        }
    }

    public List<JokesDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jokes> query
                    = em.createQuery("SELECT j FROM Jokes j", Jokes.class);
            List<Jokes> jokes = query.getResultList();
            List<JokesDTO> jokesdto = new ArrayList<>();
            for (Jokes l : jokes) {
                jokesdto.add(new JokesDTO(l));
            }
            return jokesdto;
        } finally {
            em.close();
        }
    }

    public List<JokesDTO> getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jokes> query
                    = em.createQuery("SELECT j FROM Jokes j WHERE j.id = :id", Jokes.class);
            query.setParameter("id", Long.valueOf(id));
            List<Jokes> joke = query.getResultList();
            List<JokesDTO> jokesdto = new ArrayList<>();
            for (Jokes j : joke) {
                jokesdto.add(new JokesDTO(j));
                
            }
//            Jokes line = (Jokes) query.getSingleResult();
//            return line;
            return jokesdto;
        } finally {
            em.close();
        }
    }

    public Jokes getRandomJoke() {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        int max = 60;
        int min = 1;
        int randomid = random.nextInt((max - min) + 1) + min;
        try {
            TypedQuery<Jokes> query
                    = em.createQuery("SELECT j FROM Jokes j WHERE j.id = :id", Jokes.class);
            query.setParameter("id", randomid);
            Jokes line = (Jokes) query.getSingleResult();
            return line;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        JokesFacade lf = new JokesFacade();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        System.out.println(lf.getJokeById(35));
        System.out.println(lf.getRandomJoke());
    }

}
