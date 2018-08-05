package service;

import dto.MerchantDto;
import pojo.Merchant;

import java.util.List;

public interface MerchantManager {
    public MerchantDto addMerchant(Merchant merchant);
    public List<Merchant> findMerchant();
    public Merchant updateMerchant(Merchant merchant);
    public MerchantDto merchantLogin(Merchant merchant);
}
