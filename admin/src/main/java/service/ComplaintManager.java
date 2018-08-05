package service;

import pojo.Complaint;
import pojo.Page;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/2/2018 4:01 PM
 */
public interface ComplaintManager {
    public List<Complaint> getAllComplaints();
    public Complaint addComplaint(Complaint complaint);
    public void deleteComplaint(String id);
    public Page<Complaint> getComplaintByPage(int currentPage,int pageSize);
    public List<Complaint> getComplaintById(String id);
    public Complaint updateComplaint(Complaint complaint);
}
