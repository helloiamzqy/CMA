package service;

import pojo.Comment;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;

import java.util.List;

/**
 * @author Dunn
 */
public interface CommentManager {
    Pager findAllComment(int curPage, int pageSize);
    Comment addComment(String oid,Comment Comment);
    void deleteComment(String id);
    Pager findCommentByOrder(int curPage, int pageSize,String order);
    Pager findCommentByMerchant(int curPage, int pageSize, String merchant);
}
