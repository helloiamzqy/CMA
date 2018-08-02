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

    @GetMapping(value = "/merchant/{mid}/comment")
    public Pager getMerchantComment(@RequestParam int curPage, @RequestParam int pageSize,@PathVariable String mid){
        return  commentManager.findCommentByMerchant(curPage,pageSize,mid);
    }
    @GetMapping(value = "/merchant/comment/{cid}")
    public Pager getCommentByOrderId(@RequestParam int curPage, @RequestParam int pageSize,@PathVariable String oid){
        return  commentManager.findCommentByOrder(curPage,pageSize,oid);
    }
    @PostMapping(value = "/merchant/{mid}/comment")
    public Comment addComment(@PathVariable String mid,@RequestBody Comment comment){
        return null;
    }
}
