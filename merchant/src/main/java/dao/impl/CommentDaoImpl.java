package dao.impl;

import dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pojo.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Dunn
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext(name = "em")
    private EntityManager manager;

    @Override
    public Pager findAllComment(int curPage, int pageSize) {
        String jpql = "FROM pojo.Comment";
        Query query = manager.createQuery(jpql);
        List<Comment> comments = query.getResultList();
        int totalPage = comments.size() / pageSize;
        int totalRow = comments.size();
        query = manager.createQuery(jpql);
        comments = query.setFirstResult((curPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, comments);
        return pager;
    }

    @Override
    public Comment addComment(Comment comment) {
        manager.persist(comment);
        return comment;
    }

    @Transactional
    @Override
    public void deleteFood(String id) {
        Comment comment = manager.getReference(Comment.class, id);
        manager.remove(comment);
    }

    @Override
    public Pager findCommentByOrder(int curPage, int pageSize, Order order) {
        String jpql = "FROM pojo.Comment c WHERE c.order =:order";
        Query query = manager.createQuery(jpql).setParameter("order", order);
        List<Comment> comments = query.getResultList();
        int totalPage = comments.size() / pageSize;
        int totalRow = comments.size();
        query = manager.createQuery(jpql).setParameter("order", order);
        comments = query.setFirstResult((curPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, comments);
        return pager;
    }

    @Override
    public Pager findCommentByMerchant(int curPage, int pageSize, Merchant merchant) {
        String jpql = "FROM pojo.Comment c WHERE c.order.merchant =:merchant";
        Query query = manager.createQuery(jpql).setParameter("merchant", merchant);
        List<Comment> comments = query.getResultList();
        int totalPage = comments.size() / pageSize;
        int totalRow = comments.size();
        query = manager.createQuery(jpql).setParameter("merchant", merchant);
        comments = query.setFirstResult((curPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, comments);
        return pager;
    }


    @Override
    public String findRemarkByMerchant(Merchant merchant) {
        String jpql = "SELECT AVG(c.rank) FROM pojo.Comment c WHERE c.order.merchant =:merchant";
        Query query = manager.createQuery(jpql).setParameter("merchant", merchant);
        List<Double> remarks= query.getResultList();
        String avgRemark = "0";
        if(remarks.get(0)!=null){
            avgRemark = remarks.get(0)+"";
        }

        return avgRemark;
    }
}
