package dao;

import pojo.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> findCustomer();
    public Customer updateCustomer(Customer customer);
    public Customer addCustomer(Customer customer);
}
