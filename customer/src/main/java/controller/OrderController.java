package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Order;
import pojo.Pager;
import service.OrderService;

@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "Content-Type",methods ={RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping(value = "/merchant/{mId}/{status}")
    public Pager findOrderByMerchant(@PathVariable(name = "mId") String mId, @PathVariable(name = "status") String status,@RequestParam int curPage, @RequestParam int pageSize){
        return orderService.findOrderByMerchant(mId,status,curPage,pageSize);
    }
    @GetMapping(value = "/customer/{cId}/{status}")
    public Pager findOrderByCustomer(@PathVariable(name = "cId") String cId,@PathVariable(name = "status") String status,@RequestParam int curPage, @RequestParam int pageSize){
        return orderService.findOrderByCustomer(cId,status,curPage,pageSize);
    }
    @GetMapping(value = "/customer/{cId}")
    public Pager findAllOrderByCustomer(@PathVariable(name = "cId") String cId,@RequestParam int curPage, @RequestParam int pageSize){
        return orderService.findAllOrderByCustomer(cId,curPage,pageSize);
    }

    @GetMapping(value = "/merchant/{mId}")
    public Pager findAllOrderByMerchant(@PathVariable(name = "mId") String mId,@RequestParam int curPage, @RequestParam int pageSize){
        return orderService.findAllOrderByMerchant(mId,curPage,pageSize);
    }


    @PostMapping(value = "/{mId}/{cId}")
    public Order addOrder(@RequestBody Order order,@PathVariable(name = "mId") String mId,@PathVariable(name = "cId") String cId){
        return orderService.addOrder(order,mId,cId);
    }
    @PutMapping
    public Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }
}
