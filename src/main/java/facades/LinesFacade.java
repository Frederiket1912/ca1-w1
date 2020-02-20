package facades;

import dto.LinesDTO;
import entities.Lines;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class LinesFacade {

    private static LinesFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private LinesFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static LinesFacade getLinesFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LinesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getLinesCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long linesCount = (long) em.createQuery("SELECT COUNT(l) FROM Lines l").getSingleResult();
            return linesCount;
        } finally {
            em.close();
        }
    }

    public List<LinesDTO> getAllLines() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Lines> query
                    = em.createQuery("SELECT l FROM Lines l", Lines.class);
            List<Lines> lines = query.getResultList();
            List<LinesDTO> linesdto = new ArrayList<>();
            for (Lines l : lines) {
                linesdto.add(new LinesDTO(l));
            }
            return linesdto;
        } finally {
            em.close();
        }
    }

    public List<LinesDTO> getLineById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Lines> query
                    = em.createQuery("SELECT l FROM Lines l WHERE l.id = :id", Lines.class);
            query.setParameter("id", Long.valueOf(id));
            List<Lines> line = query.getResultList();
            List<LinesDTO> linesdto = new ArrayList<>();
            for (Lines l : line) {
                linesdto.add(new LinesDTO(l));
                
            }
//            Lines line = (Lines) query.getSingleResult();
//            return line;
            return linesdto;
        } finally {
            em.close();
        }
    }

    public Lines getRandomLine() {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        int randomid = random.nextInt();
        try {
            TypedQuery<Lines> query
                    = em.createQuery("SELECT l FROM Lines l WHERE l.id = :id", Lines.class);
            query.setParameter("id", randomid);
            Lines line = (Lines) query.getSingleResult();
            return line;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        LinesFacade lf = new LinesFacade();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        System.out.println(lf.getLineById(10));
    }

}
