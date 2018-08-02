package service;

import org.springframework.web.bind.annotation.RequestBody;
import pojo.Complaint;

public interface ComplainService {
    public void sendComplainToAdmin(Complaint complaint);
}
