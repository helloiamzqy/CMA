package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Complaint;
import service.ComplainService;

import java.util.Date;

@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "Content-Type",methods ={RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@RequestMapping("/complain")
public class ComplainController {
    @Autowired
    private ComplainService complainService;

    @PostMapping
    public Complaint sendComplainToAdmin(@RequestBody Complaint complaint) {
        if(complaint.getId()==null) {
            complaint.setCreateTime(new Date());
            complainService.sendComplainToAdmin(complaint);
            return complaint;
        }
        return null;
    }
}
