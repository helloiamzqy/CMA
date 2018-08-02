package service.impl;

import dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Customer;
import service.CustomerManager;

import java.util.List;

@Service
public class CustomerManagerImpl implements CustomerManager {
    @Autowired
    private CustomerDao  customerDao;
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
}
