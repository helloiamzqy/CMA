package dao.impl;

import dao.AdvertisementDao;
import org.springframework.stereotype.Repository;
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
    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        manager.persist(advertisement);
        return advertisement;
    }

    @Override
    public List<Advertisement> findAdvertisementByMerchant(Merchant merchant) {
        String jpql="from pojo.Advertisement ad where ad.merchant=:merchant";
        Query query=manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        List<Advertisement> advertisements=query.getResultList();
        return advertisements;
    }
}
