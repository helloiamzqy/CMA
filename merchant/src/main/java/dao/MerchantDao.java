package dao;

import pojo.Merchant;

import java.util.List;

public interface MerchantDao {
    public Merchant addMerchant(Merchant merchant);
    public List<Merchant> findMerchant();
    public Merchant updateMerchant(Merchant merchant);
    public Merchant merchantLogin(Merchant merchant);
}
