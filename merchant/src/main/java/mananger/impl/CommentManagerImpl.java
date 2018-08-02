package mananger.impl;

import dao.CommentDao;
import mananger.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Comment;
import pojo.Order;
import pojo.Pager;

import java.util.List;

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
    public Comment addComment(Comment comment) {
        return  commentDao.addComment(comment);
    }

    @Override
    public void deleteFood(String id) {
        commentDao.deleteFood(id);
    }

    @Override
    public Pager findCommentByOrder(int curPage, int pageSize,Order order) {
        Pager comments = commentDao.findCommentByOrder(curPage, pageSize,order);
        return comments;
    }

}
