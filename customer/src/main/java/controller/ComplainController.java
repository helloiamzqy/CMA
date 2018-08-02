package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Complaint;

import java.util.Date;

@Controller
@RequestMapping("complain")
public class ComplainController {
    @PostMapping
    public void sendComplainToAdmin(@RequestBody Complaint complaint){
        //Complaint complaint = new Complaint(content, merchantId, orderId, new Date());
        System.out.println(complaint.toString());
    }

}
