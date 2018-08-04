package service.impl;

import dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Customer;
import service.CustomerService;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 1:34 PM 8/2/2018
 * @modified By:
 */

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Transactional
    @Override
    public List<Customer> findCustomer() {
        return customerDao.findCustomer();
    }

    @Transactional
    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Transactional
    @Override
    public Customer addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }
    @Transactional
    @Override
    public Customer checkLoginCustomer(Customer customer) {
        return customerDao.checkLoginCustomer(customer);
    }
    @Transactional
    @Override
    public Customer findCustomerById(String id) {
        return customerDao.findCustomerById(id);
    }
}
