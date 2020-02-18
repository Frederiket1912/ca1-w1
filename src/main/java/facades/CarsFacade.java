package facades;

import dto.CarsDTO;
import entities.Cars;
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
public class CarsFacade {

    private static CarsFacade instance;
    private static EntityManagerFactory emf;
            //= EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    //Private Constructor to ensure Singleton
    private CarsFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarsFacade getCarsFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getCarsCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long carsCount = (long) em.createQuery("SELECT COUNT(c) FROM Cars c").getSingleResult();
            return carsCount;
        } finally {
            em.close();
        }
    }
    
    public List<CarsDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query
                    = em.createQuery("Select c from Cars c", Cars.class);
            List<Cars> cars = query.getResultList();
            List<CarsDTO> carsdto = new ArrayList<>();
            for (Cars c : cars) {
                carsdto.add(new CarsDTO(c));
            }
            return carsdto;
        } finally {
            em.close();
        }
    }

    public void addCar(Cars cars) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cars);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
