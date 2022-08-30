package facades;

import entities.Customer;

import java.util.List;

public interface DataFacade {
    Customer findByID(Integer id);
    List<Customer> findByLastName(String name);
    int getNumberOfCustomers();
    List<Customer> allCustomers();
    Customer addCustomer(String fName, String lName);
}
