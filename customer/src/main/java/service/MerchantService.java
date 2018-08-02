package service;

import pojo.Merchant;

import java.util.List;

public interface MerchantService {
    public Merchant addMerchant(Merchant merchant);
    public Merchant findMerchantById(String merchantId);
    public List<Merchant> findMerchant();
    public Merchant updateMerchant(Merchant merchant);
}
