package service;

import pojo.Comment;
import pojo.Pager;

/**
 * @author Harper
 */
public interface CommentService {
    Pager findAllComment(int curPage, int pageSize);
    Comment addComment(String oid, Comment Comment);
    void deleteComment(String id);
    Pager findCommentByOrder(int curPage, int pageSize, String order);
    Pager findCommentByMerchant(int curPage, int pageSize, String merchant);
}
