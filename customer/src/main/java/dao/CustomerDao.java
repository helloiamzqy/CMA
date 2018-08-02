package dao;

import pojo.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer checkLoginCustomer(Customer customer);
    public List<Customer> findCustomer();
    public Customer updateCustomer(Customer customer);
    public Customer addCustomer(Customer customer);
}
