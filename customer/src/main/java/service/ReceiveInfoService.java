package service;

import pojo.Customer;
import pojo.ReceiveInfo;

import java.util.List;

public interface ReceiveInfoService {
    public ReceiveInfo addReceiveInfo(ReceiveInfo receiveInfo, Customer customer);
    public List<ReceiveInfo> findReceiveInfo(Customer customer);
    public ReceiveInfo updateReceiveInfo(ReceiveInfo receiveInfo);
    public ReceiveInfo deleteReceiveInfo(String id);
}
