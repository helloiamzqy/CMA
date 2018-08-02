package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Complaint;
import pojo.Page;
import service.ComplaintManager;

/**
 * @author JohnGao
 * @date 8/2/2018 4:09 PM
 */
@RestController
@RequestMapping(value = "complaint")
public class ComplaintController {
    @Autowired
    private ComplaintManager manager;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Complaint> getComplaint(@RequestParam int currentPage, @RequestParam int pageSize){
        Page<Complaint> page = manager.getComplaintByPage(currentPage,pageSize);
        return page;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    public String deleteComplaint(@PathVariable String id){
        manager.deleteComplaint(id);
        return "{}";
    }
}
