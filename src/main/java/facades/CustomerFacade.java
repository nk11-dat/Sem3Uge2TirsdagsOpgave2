package facades;

import entities.Customer;

import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CustomerFacade implements DataFacade{
    //Step 1: Create Factory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); //Se persistance.xml (<persistence-unit>)
    //Step 2: Create getEntitiesManager method
    private EntityManager getEntityManger(){
        return emf.createEntityManager();
    }

    @Override
    public Customer findByID(Integer id) {
        EntityManager em = getEntityManger();
        Customer found = em.find(Customer.class, id);
        em.close();
        return found;
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        List<Customer> customers;

        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lastName", Customer.class);
            query.setParameter("lastName", lastName);
            customers = query.getResultList();
        }finally {
            em.close();
        }

        return customers;
    }

    @Override
    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        List<Customer> customers;

        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            customers = query.getResultList();
        } finally {
            em.close();
        }
        return customers.size();
    }

    @Override
    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        List<Customer> customers;

        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            customers = query.getResultList();
        } finally {
            em.close();
        }
        return customers;
    }

    @Override
    public Customer addCustomer(String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        Customer c;

        try {
            em.getTransaction().begin();
            c = new Customer(fName, lName);
            em.persist(c);
            em.getTransaction().commit();
        }finally {
            em.close();
        }

        return c;
    }
}
