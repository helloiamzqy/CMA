package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Customer;
import service.CustomerService;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 11:39 AM 8/2/2018
 * @modified By:
 */

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = "/checkLoginCustomer")
    public Customer loginCustomer(@RequestBody Customer customer) {
        customer.setPassword(DigestUtils.md5DigestAsHex(customer.getPassword().getBytes()));
        System.out.println(customer.getName()+"/" + customer.getPassword());
        return customerService.checkLoginCustomer(customer);
    }

    @ResponseBody
    @RequestMapping(value = "/registCustomer")
    public Customer registCustomer(@RequestBody Customer customer) {
        customer.setPassword(DigestUtils.md5DigestAsHex(customer.getPassword().getBytes()));
      return customerService.addCustomer(customer);
    }
    @ResponseBody
    @RequestMapping(value = "/customerInfo/{id}")
    public Customer customerInfo(@PathVariable String id) {
        System.out.println("customerInfo id:" + id);
        return customerService.findCustomerById(id);
    }
}