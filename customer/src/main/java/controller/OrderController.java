package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.*;
import pojo.enums.OrderStatusEnum;
import service.FoodSerivce;
import service.OrderItemService;
import service.OrderService;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private FoodSerivce foodSerivce;
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
    public Order addOrder(@RequestBody List<CartItem> cartItems, @PathVariable(name = "mId") String mId, @PathVariable(name = "cId") String cId) {
        System.out.println(cartItems.toString());
        System.out.println("mid :"+mId + "cid :" +cId);
        Order order = new Order();
        order.setCreateTime(new Date());
        Order o = orderService.addOrder(order, mId, cId);

        for(CartItem cartItem :cartItems){
            OrderItem orderItem = new OrderItem();
            Food food = foodSerivce.findFoodByFoodId(cartItem.getId());
            orderItem.setFood(food);
            orderItem.setFoodNum(cartItem.getNum());//脏
            orderItem.setTotalPrice(food.getPrice()*cartItem.getNum());
            orderItem.setOrder(o);
            orderItemService.addOrderItem(orderItem);
        }
        return order;
    }

    @PutMapping
    public Order updateOrderStatus(@RequestBody Order order) {
        if(order.getStatus().equals(OrderStatusEnum.RECEIVE)) {
            order.setStatus(OrderStatusEnum.CANCLE);
        }else if(!order.getStatus().equals(OrderStatusEnum.COMPLETE)){
            order.setStatus(OrderStatusEnum.COMPLETE);
        }
        order.setFinishTime(new Date());
        return orderService.updateOrder(order);
    }
}
