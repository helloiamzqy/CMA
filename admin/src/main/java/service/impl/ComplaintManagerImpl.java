package service.impl;

import dao.ComplaintDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Complaint;
import pojo.Page;
import service.ComplaintManager;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/2/2018 4:04 PM
 */
@Service
public class ComplaintManagerImpl implements ComplaintManager {

    @Autowired
    private ComplaintDao complaintDao;
    @Transactional
    @Override
    public Complaint addComplaint(Complaint complaint) {
        return complaintDao.addComplaint(complaint);
    }
    @Transactional
    @Override
    public void deleteComplaint(String id) {
        complaintDao.deleteComplaint(id);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintDao.getAllComplaints();
    }

    @Override
    public Page<Complaint> getComplaintByPage(int currentPage, int pageSize) {
        Page<Complaint> page=new Page<Complaint>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        int totalCount=complaintDao.getComplaintCount();
        page.setTotalCount(totalCount);
        int totalPage=0;
        if (totalCount%pageSize==0){
            totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        page.setTotalPage(totalPage);
        int begin= (currentPage-1)*pageSize;
        List<Complaint> list=complaintDao.getComplaintsByPage(begin,begin+pageSize);
        page.setDataList(list);
        return page;
    }

    @Override
    public List<Complaint> getComplaintById(String id) {
        return complaintDao.getComplaintById(id);
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        return complaintDao.updateComplaint(complaint);
    }
}
