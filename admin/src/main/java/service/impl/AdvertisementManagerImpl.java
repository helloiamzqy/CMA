package service.impl;

import dao.AdvertisementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Advertisement;
import pojo.Page;
import service.AdvertisementManager;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/1/2018 9:05 PM
 */
@Service
public class AdvertisementManagerImpl implements AdvertisementManager {
    @Autowired
    private AdvertisementDao advertisementDao;

    public AdvertisementManagerImpl() {
        super();
    }

    @Override
    public List<Advertisement> getAllAds() throws Exception {
        return null;
    }

    @Override
    public void updateAd(int id, int state) {

    }

    @Override
    public boolean addAd(Advertisement ad) {
        return false;
    }

    @Override
    public String sendAds() {
        return null;
    }

    @Override
    public boolean deleteAdById(int id) {
        return false;
    }

    @Override
    public Page<Advertisement> getAdsByPage(int currentPage, int pageSize) {
        return null;
    }
}
