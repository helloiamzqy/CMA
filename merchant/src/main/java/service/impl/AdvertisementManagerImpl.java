package service.impl;

import com.google.gson.Gson;
import dao.AdvertisementDao;
import dao.BasicInfoDao;
import dto.AdvertisementDto;
import mananger.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Advertisement;
import pojo.BasicInfo;
import pojo.JmsMessage;
import pojo.Merchant;
import pojo.enums.JmsEnum;
import service.AdvertisementManager;

import java.util.List;

@Service
public class AdvertisementManagerImpl implements AdvertisementManager {
    @Autowired
    private AdvertisementDao advertisementDao;
    @Autowired
    private BasicInfoDao basicInfoDao;
    @Autowired
    private JmsSender jmsSender;
    @Transactional
    @Override
    //发送广告到A端
    public Advertisement addAdvertisement(Advertisement advertisement, String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        BasicInfo basicInfo = basicInfoDao.findBasicInfoByMerchant(merchant);
        merchant.setName(basicInfo.getShopName());
        advertisement.setMerchant(merchant);
        AdvertisementDto dto = advertisement.toDto();
        JmsMessage jmsMessage = new JmsMessage(JmsEnum.ADVERSITMENT,dto);
        jmsSender.send(new Gson().toJson(jmsMessage));
        return advertisementDao.addAdvertisement(advertisement);
    }

    @Override
    public List<Advertisement> findAdvertisementByMerchant(String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        return advertisementDao.findAdvertisementByMerchant(merchant);
    }

    @Override
    public List<Advertisement> findAdvertisementById(List<String> ids) {
        return advertisementDao.findAdvertisementById(ids);
    }
}
