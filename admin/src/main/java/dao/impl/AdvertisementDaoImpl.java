package dao.impl;

import dao.AdvertisementDao;
import org.springframework.stereotype.Repository;
import pojo.Advertisement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author JohnGao
 * @date 8/1/2018 9:06 PM
 */
@Repository
public class AdvertisementDaoImpl implements AdvertisementDao {
    @PersistenceContext(name = "em")
    private EntityManager em;

    @Override
    public List<Advertisement> getAllAd() {
        String jpql = "from pojo.Advertisement";
        return em.createQuery(jpql).getResultList();
    }

    @Override
    public Advertisement updateAd(Advertisement ad) {
        Advertisement advertisement = em.merge(ad);
        return advertisement;
    }

    @Override
    public Advertisement addAd(Advertisement ad) {
        em.persist(ad);
        return ad;
    }

    @Override
    public List<Advertisement> sendAds() {
        String jpql="select ad from pojo.Advertisement ad where ad.state=1 order by ad.price desc";
        return em.createQuery(jpql)
                .setFirstResult(1)
                .setMaxResults(3)
                .getResultList();
    }

    @Override
    public int deleteAdById(int i) {

        return 0;
    }

    @Override
    public int findAdsCount() {
        String jpql="select count(*) from pojo.Advertisement";
        return (Integer) em.createQuery(jpql).getSingleResult();
    }

    @Override
    public List<Advertisement> getAdByPage(int begin, int end) {
        String jpql = "from pojo.Advertisement";
        return em
                .createQuery(jpql)
                .setFirstResult(begin)
                .setMaxResults(end)
                .getResultList();
    }
}
