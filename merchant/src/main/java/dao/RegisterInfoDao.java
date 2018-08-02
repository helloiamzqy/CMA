package dao;

import org.springframework.stereotype.Service;
import pojo.Merchant;
import pojo.Pager;
import pojo.RegisterInfo;

import javax.persistence.PersistenceContext;

/**
 * @author Dunn
 */
public interface RegisterInfoDao {
    RegisterInfo getRegisterInfo(Merchant Merchant);
    RegisterInfo addRegisterInfo(RegisterInfo registerInfo);
}
