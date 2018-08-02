package dao.impl;

import dao.MerchantInfoDao;
import org.springframework.stereotype.Repository;
import pojo.MerchantInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MerchantInfoDaoImpl implements MerchantInfoDao {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public MerchantInfo addMerchantInfo(MerchantInfo merchantInfo) {
        manager.persist(merchantInfo);
        return merchantInfo;
    }

    @Override
    public MerchantInfo updateMerchantInfo(MerchantInfo merchantInfo) {
        return manager.merge(merchantInfo);
    }


    @Override
    public List<MerchantInfo> findAllMerchantInfos() {
        String jpql = "from pojo.MerchantInfo";
        List<MerchantInfo> merchantInfos = manager
                .createQuery(jpql)
                .getResultList();
        return merchantInfos;
    }
}
