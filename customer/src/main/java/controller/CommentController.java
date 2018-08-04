package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Comment;
import pojo.Order;
import pojo.Pager;
import pojo.enums.OrderStatusEnum;
import service.CommentService;
import service.OrderService;

import java.util.Date;

/**
 * @author Harper
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private OrderService orderService;
    @GetMapping(value = "/{mid}/comment")
    public Pager getMerchantComment(@RequestParam int curPage, @RequestParam int pageSize,@PathVariable String mid){
        return  commentService.findCommentByMerchant(curPage,pageSize,mid);
    }
    @GetMapping(value = "/comment/{cid}")
    public Pager getCommentByOrderId(@RequestParam int curPage, @RequestParam int pageSize,@PathVariable String oid){
        return  commentService.findCommentByOrder(curPage,pageSize,oid);
    }
    @PostMapping(value = "/{oid}")
    public Comment addComment(@PathVariable String oid,@RequestBody Comment comment){
        Pager comments = commentService.findCommentByOrder(1, 1, oid);
        if(comments.getList().size()==0){
            comment.setCreateTime(new Date());
            comment=commentService.addComment(oid,comment);
            if(comment.getId()!=null){
                Order order = comment.getOrder();
                order.setStatus(OrderStatusEnum.COMMENT);
                orderService.updateOrder(order);
            }
            return comment;
        }
        return null;
    }
}
