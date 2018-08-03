package service.impl;

import dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Comment;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;
import service.CommentManager;
import service.CommentService;

/**
 * @author Harper
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Override
    public Pager findAllComment(int curPage, int pageSize) {
        Pager comments = commentDao.findAllComment(curPage,pageSize);
        return comments;
    }
    @Transactional
    @Override
    public Comment addComment(Comment comment) {
        return  commentDao.addComment(comment);
    }

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
}
