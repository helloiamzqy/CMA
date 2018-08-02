package dao.impl;

import dao.CustomerDao;
import org.springframework.stereotype.Repository;
import pojo.Customer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext(name="em")
    private EntityManager manager;

    @Override
    public Customer checkLoginCustomer(Customer customer){
        String jpql = "FROM pojo.Customer c WHERE c.name =:name and c.password=:password";
        try {
            Customer customer1 = (Customer) manager.createQuery(jpql)
                    .setParameter("name",customer.getName())
                    .setParameter("password",customer.getPassword())
                    .getSingleResult();
            return customer1;
        }catch (NoResultException e){
            System.out.println("没有这个用户名和密码对应的用户");
        }
       return null;
    }

    @Override
    public List<Customer> findCustomer() {
        String spql="from pojo.Customer";
        List<Customer> customers=manager.createQuery(spql).getResultList();
        return customers;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customer1=manager.merge(customer);
        return customer1;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        try{
            manager.persist(customer);
            return customer;
        }catch (Exception e){
            return null;
        }
    }
}
