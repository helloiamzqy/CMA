package dao;

import pojo.*;

import java.util.List;

/**
 * @author Dunn
 */
public interface CommentDao {
    Pager findAllComment(int curPage, int pageSize);
    Comment addComment(Comment Comment);
    void deleteFood(String id);
    Pager findCommentByOrder(int curPage, int pageSize,Order order);
    Pager findCommentByMerchant(int curPage, int pageSize,Merchant merchant);
    String findRemarkByMerchant(Merchant merchant);
}
