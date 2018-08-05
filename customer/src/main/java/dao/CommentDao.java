package dao;

import pojo.Comment;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;

/**
 * @author Harper
 */
public interface CommentDao {
    Pager findAllComment(int curPage, int pageSize);
    Comment addComment(Comment Comment);
    void deleteFood(String id);
    Pager findCommentByOrder(int curPage, int pageSize, Order order);
    Pager findCommentByMerchant(int curPage, int pageSize, Merchant merchant);
    String findRankByMerchant(Merchant merchant);
}
