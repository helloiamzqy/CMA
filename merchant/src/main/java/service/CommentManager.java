package service;

import pojo.Comment;
import pojo.Order;
import pojo.Pager;

import java.util.List;

/**
 * @author Dunn
 */
public interface CommentManager {
    Pager findAllComment(int curPage, int pageSize);
    Comment addComment(Comment Comment);
    void deleteFood(String id);
    Pager findCommentByOrder(int curPage, int pageSize,Order order);
}
