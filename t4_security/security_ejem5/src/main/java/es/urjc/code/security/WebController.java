package es.urjc.code.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String home(Model model, HttpServletRequest request) {
    	
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	return "home";
    }
    
    @RequestMapping("/admin")
    public String admin() {
    	return "admin";
    }
}
