package dao.impl;

import dao.BasicInfoDao;
import org.springframework.stereotype.Repository;
import pojo.BasicInfo;
import pojo.Merchant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class BasicInfoDaoImpl implements BasicInfoDao {
    @PersistenceContext(name = "em")
    private EntityManager manager;

    @Override
    public BasicInfo addBasicInfo(BasicInfo basicInfo) {
        manager.persist(basicInfo);
        return basicInfo;
    }

    @Override
    public BasicInfo updateBasicInfo(BasicInfo basicInfo) {
        manager.merge(basicInfo);
        return basicInfo;
    }

    @Override
    public BasicInfo findBasicInfoByMerchant(Merchant merchant) {
        String jpql="from pojo.BasicInfo b where b.merchant=:merchant";
        Query query=manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        BasicInfo basicInfo= (BasicInfo) query.getSingleResult();
        return basicInfo;
    }
}
