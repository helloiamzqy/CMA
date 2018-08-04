package service.impl;

import dao.ReceiveInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Customer;
import pojo.ReceiveInfo;
import service.ReceiveInfoService;

import java.util.List;

@Service
public class ReceiveInfoServiceImpl implements ReceiveInfoService {

    @Autowired
    ReceiveInfoDao receiveInfoDao;

    @Override
    @Transactional
    public ReceiveInfo addReceiveInfo(ReceiveInfo receiveInfo, Customer customer) {
        return receiveInfoDao.addReceiveInfo(receiveInfo, customer);
    }

    @Transactional
    @Override
    public List<ReceiveInfo> findReceiveInfo(Customer customer) {
        return receiveInfoDao.findReceiveInfo(customer);
    }

    @Transactional
    @Override
    public ReceiveInfo updateReceiveInfo(ReceiveInfo receiveInfo) {
        return receiveInfoDao.updateReceiveInfo(receiveInfo);
    }

    @Transactional
    @Override
    public ReceiveInfo deleteReceiveInfo(String id) {
        return receiveInfoDao.deleteReceiveInfo(id);
    }
}
