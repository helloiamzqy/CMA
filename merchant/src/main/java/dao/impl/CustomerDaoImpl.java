package dao.impl;

import dao.CustomerDao;
import org.springframework.stereotype.Repository;
import pojo.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext(name="em")
    private EntityManager manager;
    @Override
    public List<Customer> findCustomer() {
        String jpql="from pojo.Customer";
        List<Customer> customers=manager.createQuery(jpql).getResultList();
        return customers;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customer1=manager.merge(customer);
        return customer1;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        manager.persist(customer);
        return customer;
    }
}
