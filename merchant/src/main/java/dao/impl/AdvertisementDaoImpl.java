package dao.impl;

import dao.AdvertisementDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pojo.Advertisement;
import pojo.Merchant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AdvertisementDaoImpl implements AdvertisementDao {
    @PersistenceContext(name = "em")
    private EntityManager manager;

    @Transactional
    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        manager.persist(advertisement);
        return advertisement;
    }

    @Override
    public List<Advertisement> findAdvertisementByMerchant(Merchant merchant) {
        String jpql="FROM pojo.Advertisement ad where ad.merchant=:merchant";
        Query query=manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        List<Advertisement> advertisements=query.getResultList();
        return advertisements;
    }

    @Override
    public List<Advertisement> findAdvertisementById(List<String> ids) {
        String jpql = "FROM pojo.Advertisement ad WHERE ad.id in(:ids)";
        Query query=manager.createQuery(jpql);
        query.setParameter("ids",ids);
        List<Advertisement> advertisements=query.getResultList();
        return advertisements;
    }

}
