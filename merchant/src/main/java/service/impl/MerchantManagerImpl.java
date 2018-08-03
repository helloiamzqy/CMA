package service.impl;

import dao.MerchantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Merchant;
import service.MerchantManager;

import java.util.List;

@Service
public class MerchantManagerImpl implements MerchantManager {
    @Autowired
    private MerchantDao merchantDao;
    @Transactional
    @Override
    public Merchant addMerchant(Merchant merchant) {
        return merchantDao.addMerchant(merchant);
    }

    @Transactional
    @Override
    public List<Merchant> findMerchant() {
        return merchantDao.findMerchant();
    }
    @Transactional
    @Override
    public Merchant updateMerchant(Merchant merchant) {
        return merchantDao.updateMerchant(merchant);
    }

    @Transactional
    @Override
    public Merchant findMerchantByName(String name) {
        return merchantDao.findMerchantByName(name);
    }
}
