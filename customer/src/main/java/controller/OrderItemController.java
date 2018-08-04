package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.OrderItem;
import service.OrderItemManager;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("orderItems")
public class OrderItemController {
    @Autowired
    private OrderItemManager orderItemManager;
    @GetMapping(value = "{oId}")
    public List<OrderItem> findAllItemsByOrder(@PathVariable(name = "oId") String oId){
        return orderItemManager.findOrderItemByOrder(oId);
    }
    @DeleteMapping(value = "{id}")
    public void deleteOrderItemById(@PathVariable(name = "id") String id){
        orderItemManager.deleteOrderItemById(id);
    }
    @PostMapping(value = "{oId}/{fId}")
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem,@PathVariable("oId") String oId,@PathVariable("fId") String fId){
        return orderItemManager.addOrderItem(orderItem,oId,fId);
    }

}
