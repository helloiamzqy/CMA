package service.impl;

import dao.AdvertisementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return advertisementDao.getAllAd();
    }

    @Transactional
    @Override
    public Advertisement updateAd(String id, String state) {
        Advertisement advertisement = advertisementDao.findAdById(id);
        advertisement.setStatus(state);
      return advertisementDao.updateAd(advertisement);
    }

    @Transactional
    @Override
    public Advertisement addAd(Advertisement ad) {
        return advertisementDao.addAd(ad);
    }

    @Transactional
    @Override
    public void deleteAdById(String id) {
        advertisementDao.deleteAdById(id);
    }

    @Override
    public List<Advertisement> sendAds() {
        return advertisementDao.sendAds();
    }

    @Override
    public Page<Advertisement> getAdsByPage(int currentPage, int pageSize) {
        Page<Advertisement> page=new Page<Advertisement>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        int totalCount=advertisementDao.findAdsCount();
        page.setTotalCount(totalCount);
        int totalPage=0;
        if (totalCount%pageSize==0){
            totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        page.setTotalPage(totalPage);
        int begin= (currentPage-1)*pageSize;
        List<Advertisement> list=advertisementDao.getAdByPage(begin,begin+pageSize);
        page.setDataList(list);
        return page;
    }

    @Transactional
    @Override
    public Advertisement updateAdvertisement(Advertisement advertisement) {
        return advertisementDao.updateAdvertisement(advertisement);
    }
}
