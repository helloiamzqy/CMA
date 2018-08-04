package dao.impl;

import dao.MerchantInfoDao;
import org.springframework.stereotype.Repository;
import pojo.MerchantInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MerchantInfoDaoImpl implements MerchantInfoDao {

    @PersistenceContext(name = "em")
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
    public List<MerchantInfo> findMechantInfosByStatus(String status) {
        String jpql = "from pojo.MerchantInfo where status =?1";
        Query query = manager.createQuery(jpql);
        query.setParameter(1,status);
        List<MerchantInfo> merchantInfos = query.getResultList();
        return merchantInfos;
    }

    @Override
    public MerchantInfo findMechantInfoByMerchantId(String merchantId) {
        String jpql = "from pojo.MerchantInfo where merchant_id =?1";
        Query query = manager.createQuery(jpql);
        query.setParameter(1,merchantId);
        MerchantInfo merchantInfo = null;
        try{
            merchantInfo = (MerchantInfo) query.getSingleResult();
        }catch(javax.persistence.NoResultException e){

        }finally{
            return merchantInfo;
        }

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
