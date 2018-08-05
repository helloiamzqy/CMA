package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Order;
import pojo.Pager;
import service.OrderManager;

@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    private OrderManager orderManager;
    @GetMapping(value = "/{mId}/orders/{status}")
    public Pager findOrderByMerchant(@PathVariable(name = "mId") String mId, @PathVariable(name = "status") String status,@RequestParam int curPage, @RequestParam int pageSize){
        return orderManager.findOrderByMerchant(mId,status,curPage,pageSize);
    }
    @GetMapping(value = "/customer/{cId}/{status}")
    public Pager findOrderByCustomer(@PathVariable(name = "cId") String cId,@PathVariable(name = "status") String status,@RequestParam int curPage, @RequestParam int pageSize){
        return orderManager.findOrderByCustomer(cId,status,curPage,pageSize);
    }
    @GetMapping(value = "/customer/{cId}")
    public Pager findAllOrderByCustomer(@PathVariable(name = "cId") String cId,@RequestParam int curPage, @RequestParam int pageSize){
        return orderManager.findAllOrderByCustomer(cId,curPage,pageSize);
    }

    @GetMapping(value = "/{mId}/orders")
    public Pager findAllOrderByMerchant(@PathVariable(name = "mId") String mId,@RequestParam int curPage, @RequestParam int pageSize){

        return orderManager.findAllOrderByMerchant(mId,curPage,pageSize);
    }


    @PostMapping(value = "/{mId}/{cId}/orders")
    public Order addOrder(@RequestBody Order order,@PathVariable(name = "mId") String mId,@PathVariable(name = "cId") String cId){
        return orderManager.addOrder(order,mId,cId);
    }
    @PutMapping(value = "orders")
    public Order updateOrder(@RequestBody Order order){
        return orderManager.updateOrder(order);
    }
    @GetMapping(value = "/{mId}/newOrders")
    public int findNewOrders(@PathVariable(value = "mId") String mId){
        return orderManager.findNewOrders(mId);
    }
}
