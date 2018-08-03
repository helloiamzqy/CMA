package controller;

import dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Comment;
import pojo.Pager;
import service.CommentManager;

/**
 * @author Dunn
 */
@RestController
public class CommentController {
    @Autowired
    private CommentManager commentManager;

    @GetMapping(value = "{mid}/comment")
    public Pager getMerchantComment(@RequestParam int curPage, @RequestParam int pageSize,@PathVariable String mid){
        return  commentManager.findCommentByMerchant(curPage,pageSize,mid);
    }
    @GetMapping(value = "/order/comment/{cuId}")
    public Pager getCommentByOrderId(@RequestParam int curPage, @RequestParam int pageSize,@PathVariable String cuId){
        return  commentManager.findCommentByOrder(curPage,pageSize,cuId);
    }
    @PostMapping(value = "{oid}/comment")
    public Comment addComment(@PathVariable String oid,@RequestBody Comment comment){
       return  commentManager.addComment(oid,comment);
    }
}
