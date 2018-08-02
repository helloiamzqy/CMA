package service.impl;

import dao.MerchantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Merchant;
import service.MerchantService;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
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
}
