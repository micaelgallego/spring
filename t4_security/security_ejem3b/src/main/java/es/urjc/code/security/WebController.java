package es.urjc.code.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
    	return "login";
    }
    
    @RequestMapping("/loginerror")
    public String loginerror() {
    	return "loginerror";
    }

    @RequestMapping("/home")
    public String home() {
    	return "home";
    }
}
