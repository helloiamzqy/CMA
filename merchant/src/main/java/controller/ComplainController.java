package controller;

import jersey.Config;
import jersey.ServerInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dunn
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ComplainController {
    @Autowired
    private ServerInteraction serverInteraction;

    @GetMapping("{mid}/complain")
    public String getComplain(@PathVariable String mid) throws Exception{
        String result=  serverInteraction.interact(Config.complain_url+mid," ");
        return result;
    }

}
