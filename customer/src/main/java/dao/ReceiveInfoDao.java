package dao;

import pojo.Customer;
import pojo.ReceiveInfo;

import java.util.List;

public interface ReceiveInfoDao {
    public ReceiveInfo addReceiveInfo(ReceiveInfo receiveInfo, Customer customer);
    public List<ReceiveInfo> findReceiveInfo(Customer customer);
    public ReceiveInfo updateReceiveInfo(ReceiveInfo receiveInfo);
    public ReceiveInfo deleteReceiveInfo(String id);
}
