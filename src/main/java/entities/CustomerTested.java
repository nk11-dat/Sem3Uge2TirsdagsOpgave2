package entities;

import facades.CustomerFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CustomerTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        CustomerFacade cf = new CustomerFacade();
        System.out.println("FIND BY ID:");
        System.out.println(cf.findByID(2));
        System.out.println();

        System.out.println("FIND BY LASTNAME:");
        cf.findByLastName("Bobsen").forEach(System.out::println);
        System.out.println();

        System.out.println("GET NUMBER OF CUSTOMERS:");
        System.out.println(cf.getNumberOfCustomers());
        System.out.println();

        System.out.println("ALL CUSTOMERS:");
        cf.allCustomers().forEach(System.out::println);
        System.out.println();

        System.out.println("ADD CUSTOMER:");
        System.out.println(cf.addCustomer("Senge", "Tid"));
        System.out.println();

        em.close();
        emf.close();
    }
}
