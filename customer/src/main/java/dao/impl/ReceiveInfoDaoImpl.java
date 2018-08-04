package dao.impl;

import dao.ReceiveInfoDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pojo.Customer;
import pojo.ReceiveInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ReceiveInfoDaoImpl implements ReceiveInfoDao {
    @PersistenceContext(name = "em")
    private EntityManager manager;

    @Override
    public ReceiveInfo addReceiveInfo(ReceiveInfo receiveInfo, Customer customer) {
        receiveInfo.setCustomer(customer);
        manager.persist(receiveInfo);
        return receiveInfo;
    }

    @Override
    public ReceiveInfo updateReceiveInfo(ReceiveInfo receiveInfo) {
        return manager.merge(receiveInfo);
    }

    @Override
    public List<ReceiveInfo> findReceiveInfo(Customer customer) {
        String jpql = "from ReceiveInfo where customer=:customer";
        return (List<ReceiveInfo>) manager.createQuery(jpql).setParameter("customer",customer).getResultList();
    }

    @Override
    public ReceiveInfo deleteReceiveInfo(String id) {
        Query query = manager.createQuery("delete from  ReceiveInfo where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();//执行
        return null;
    }
}
