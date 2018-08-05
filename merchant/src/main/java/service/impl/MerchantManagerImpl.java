package service.impl;

import dao.CommentDao;
import dao.MerchantDao;
import dto.MerchantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Comment;
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

//    @Transactional
//    @Override
//    public Merchant merchantLogin(Merchant merchant) {
//        String message="";
//        Merchant merchant1=merchantLogin(merchant);
//        if(merchant1==null){
//            message="用户名不存在！";
//        }else if(!merchant1.getPassword().equals(merchant.getPassword())){
//            message="密码错误！";
//        }
//        return merchant;
//    }
@Transactional
@Override
public MerchantDto merchantLogin(Merchant merchant) {
    MerchantDto merchantDto=new MerchantDto();
    Merchant merchant1=merchantDao.merchantLogin(merchant);
    if (merchant1==null){
        merchantDto.setNameError("用户名不存在！");
    }else if(!merchant.getPassword().equals(merchant1.getPassword())){
        merchantDto.setPswError("密码错误！");
    }
    merchantDto.setMerchant(merchant1);
    return merchantDto;
}

}
