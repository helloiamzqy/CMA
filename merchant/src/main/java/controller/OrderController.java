package controller;

import oracle.jdbc.proxy.annotation.Post;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Customer;
import pojo.Order;
import service.OrderManager;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderManager orderManager;
    @GetMapping(value = "/merchant/{mId}/{status}")
    public List<Order> findOrderByMerchant(@PathVariable(name = "mId") String mId,@PathVariable(name = "status") String status){
        return orderManager.findOrderByMerchant(mId,status);
    }
    @GetMapping(value = "/customer/{cId}/{status}")
    public List<Order> findOrderByCustomer(@PathVariable(name = "cId") String cId,@PathVariable(name = "status") String status){
        return orderManager.findOrderByCustomer(cId,status);
    }
    @GetMapping(value = "/customer/{cId}")
    public List<Order> findAllOrderByCustomer(@PathVariable(name = "cId") String cId){
        return orderManager.findAllOrderByCustomer(cId);
    }

    @GetMapping(value = "/merchant/{mId}")
    public List<Order> findAllOrderByMerchant(@PathVariable(name = "mId") String mId){
        return orderManager.findAllOrderByMerchant(mId);
    }


    @PostMapping(value = "/{mId}/{cId}")
    public Order addOrder(@RequestBody Order order,@PathVariable(name = "mId") String mId,@PathVariable(name = "cId") String cId){
        return orderManager.addOrder(order,mId,cId);
    }
    @PutMapping
    public Order updateOrder(@RequestBody Order order){
        return orderManager.updateOrder(order);
    }
}
