package service;

import pojo.Merchant;

import java.util.List;

public interface MerchantManager {
    public Merchant addMerchant(Merchant merchant);
    public List<Merchant> findMerchant();
    public Merchant updateMerchant(Merchant merchant);
}
