package dao.impl;

import dao.MerchantDao;
import org.springframework.stereotype.Repository;
import pojo.Merchant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MerchantDaoImpl implements MerchantDao {
    @PersistenceContext(name="em")
    private EntityManager manager;
    @Override
    public Merchant addMerchant(Merchant merchant) {
        manager.persist(merchant);
        return merchant;
    }

    @Override
    public List<Merchant> findMerchant() {
        String spql="from pojo.Merchant";
        List<Merchant> merchants=manager.createQuery(spql).getResultList();
        return merchants;
    }

    @Override
    public Merchant updateMerchant(Merchant merchant) {
        Merchant merchant1=manager.merge(merchant);
        return merchant1;

    }
}
