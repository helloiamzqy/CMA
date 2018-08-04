package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Comment;
import pojo.Pager;
import service.CommentService;

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
        comment.setCreateTime(new Date());
        return commentService.addComment(oid,comment);
    }
}
