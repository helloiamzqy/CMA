package controller;

import jersey.Config;
import jersey.ServerInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dunn
 */
@RestController
public class LoginController {
    @Autowired
    private ServerInteraction serverInteraction;

    @GetMapping("/send")
    public String send(){
        System.out.println(serverInteraction.interact(Config.ask_url+"879"," "));
        return "1";
    }
}
