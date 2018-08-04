package dao.impl;

import dao.ComplaintDao;
import org.springframework.stereotype.Repository;
import pojo.Complaint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author JohnGao
 * @date 8/2/2018 3:51 PM
 */
@Repository
public class ComplaintDaoImpl implements ComplaintDao {
    @PersistenceContext(name = "em")
    private EntityManager em;
    @Override
    public Complaint addComplaint(Complaint complaint) {
        em.persist(complaint);
        return complaint;
    }

    @Override
    public void deleteComplaint(String id) {
        Complaint complaint = em.getReference(Complaint.class,id);
        em.remove(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        String jpql = "from pojo.Complaint";
        return em.createQuery(jpql).getResultList();
    }

    @Override
    public List<Complaint> getComplaintById(String id) {
        String jpql = "from pojo.Complaint comp where comp.merchantId = :id";
        return em
                .createQuery(jpql)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public int getComplaintCount() {
        String jpql="select count(*) from pojo.Complaint";
        return ((Number) em.createQuery(jpql).getSingleResult()).intValue();
    }

    @Override
    public List<Complaint> getComplaintsByPage(int begin, int end) {
        String jpql = "from pojo.Complaint comp order by comp.createTime desc";
        return em.createQuery(jpql)
                .setFirstResult(begin)
                .setMaxResults(end)
                .getResultList();
    }
}
