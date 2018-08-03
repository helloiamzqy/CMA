package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.Complaint;
import service.ComplainService;

@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "Content-Type",methods ={RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
@Controller
@RequestMapping("/complain")
public class ComplainController {
    @Autowired
    private ComplainService complainService;

    @PostMapping
    public void sendComplainToAdmin(@RequestBody Complaint complaint) {
        complainService.sendComplainToAdmin(complaint);
    }
}
