package service.impl;

import dao.CommentDao;
import org.omg.CORBA.ORB;
import pojo.Merchant;
import service.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Comment;
import pojo.Order;
import pojo.Pager;

import java.beans.Transient;

/**
 * @author Dunn
 */
@Service
public class CommentManagerImpl implements CommentManager {

    @Autowired
    private CommentDao commentDao;
    @Override

    public Pager findAllComment(int curPage, int pageSize) {
        Pager comments = commentDao.findAllComment(curPage,pageSize);
        return comments;
    }
    @Transactional
    @Override
    public Comment addComment(String oid,Comment comment) {
        Order order = new Order();
        order.setId(oid);
        comment.setOrder(order);
        return  commentDao.addComment(comment);
    }

    @Transactional
    @Override
    public void deleteComment(String id) {
        commentDao.deleteFood(id);
    }

    @Override
    public Pager findCommentByOrder(int curPage, int pageSize,String oId) {
        Order order = new Order();
        order.setId(oId);
        Pager comments = commentDao.findCommentByOrder(curPage, pageSize,order);
        return comments;
    }

    @Override
    public Pager findCommentByMerchant(int curPage, int pageSize, String merchantId) {
        Merchant merchant = new Merchant();
        merchant.setId(merchantId);
        Pager comments = commentDao.findCommentByMerchant(curPage, pageSize,merchant);
        return comments;
    }

    @Override
    public Comment findCommentByOrder(String oId) {
        Order order = new Order();
        order.setId(oId);
        Comment comment = commentDao.findCommentByOrder(order);
        return comment;
    }
}
