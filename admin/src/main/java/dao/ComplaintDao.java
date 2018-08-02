package dao;

import pojo.Complaint;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/2/2018 3:30 PM
 */
public interface ComplaintDao {
    public Complaint addComplaint(Complaint complaint);
    public void deleteComplaint(String id);
    public List<Complaint> getAllComplaints();
    public List<Complaint> getComplaintsByPage(int begin,int end);
    public int getComplaintCount();
    public Complaint getComplaintById(String id);
}
