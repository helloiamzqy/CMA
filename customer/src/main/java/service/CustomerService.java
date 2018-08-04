package service;

import pojo.Customer;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 1:34 PM 8/2/2018
 * @modified By:
 */
public interface CustomerService {

    public Customer checkLoginCustomer(Customer customer);

    public List<Customer> findCustomer();
    public Customer updateCustomer(Customer customer);
    public Customer addCustomer(Customer customer);

    public Customer findCustomerById(String id);
}
