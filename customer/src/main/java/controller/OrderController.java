package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Order;
import pojo.OrderItem;
import pojo.Pager;
import pojo.enums.OrderStatusEnum;
import service.OrderItemService;
import service.OrderService;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "Content-Type", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @GetMapping(value = "/merchant/{mId}/{status}")
    public Pager findOrderByMerchant(@PathVariable(name = "mId") String mId, @PathVariable(name = "status") String status, @RequestParam int curPage, @RequestParam int pageSize) {
        return orderService.findOrderByMerchant(mId, status, curPage, pageSize);
    }

    @GetMapping(value = "/customer/{cId}/{status}")
    public Pager findOrderByCustomer(@PathVariable(name = "cId") String cId, @PathVariable(name = "status") String status, @RequestParam int curPage, @RequestParam int pageSize) {
        if (status != null || status.equals("all")) {
            return orderService.findAllOrderByCustomer(cId, curPage, pageSize);
        } else {
            return orderService.findOrderByCustomer(cId, status, curPage, pageSize);
        }
    }

    @GetMapping(value = "/merchant/{mId}")
    public Pager findAllOrderByMerchant(@PathVariable(name = "mId") String mId, @RequestParam int curPage, @RequestParam int pageSize) {
        return orderService.findAllOrderByMerchant(mId, curPage, pageSize);
    }


    @PostMapping(value = "addOrder/{mId}/{cId}")
    public Order addOrder(@RequestBody List<OrderItem> orderItems, @PathVariable(name = "mId") String mId, @PathVariable(name = "cId") String cId) {
        System.out.println(orderItems.toString());
//        Order order = new Order();
//        order.setCreateTime(new Date());
//        order.setStatus(OrderStatusEnum.WATING);
//        Order o = orderService.addOrder(order, mId, cId);
//        for (OrderItem orderItem:orderItems){
//            orderItem.setOrder(o);
//            orderItemService.addOrderItem(orderItem);
//        }
//
//        return o;
        return null;
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }
}
