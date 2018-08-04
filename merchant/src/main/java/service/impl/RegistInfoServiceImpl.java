package service.impl;

import com.google.gson.Gson;
import dao.RegisterInfoDao;
import dto.RegisterInfoDto;
import mananger.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.JmsMessage;
import pojo.Merchant;
import pojo.RegisterInfo;
import pojo.enums.JmsEnum;
import service.RegistInfoManger;

/**
 * @author Dunn
 */
@Service
public class RegistInfoServiceImpl implements RegistInfoManger {
    @Autowired
    private RegisterInfoDao registerInfoDao;
    @Autowired
    private JmsSender jmsSender;

    Gson gson = new Gson();

    @Override
    public RegisterInfo getRegisterInfo(Merchant merchant) {
        return registerInfoDao.getRegisterInfo(merchant);
    }

    @Override
    @Transactional
    public RegisterInfo addRegisterInfo(RegisterInfo registerInfo) {
        RegisterInfoDto registerInfoDto = registerInfo.toDto();
        JmsMessage jmsMessage = new JmsMessage(JmsEnum.APPLY,registerInfoDto);
        jmsSender.send(
                gson.toJson(jmsMessage)
        );
        registerInfoDao.addRegisterInfo(registerInfo);
        return registerInfo;
    }
}
