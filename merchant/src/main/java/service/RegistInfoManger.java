package service;

import pojo.Merchant;
import pojo.RegisterInfo;

/**
 * @author Dunn
 */
public interface RegistInfoManger {
    RegisterInfo getRegisterInfo(Merchant merchant);
    RegisterInfo addRegisterInfo(RegisterInfo registerInfo);
}
