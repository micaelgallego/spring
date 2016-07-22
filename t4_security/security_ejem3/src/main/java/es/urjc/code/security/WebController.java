package es.urjc.code.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
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
    public String login(Model model, HttpServletRequest request) {
    	
    	CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
    	model.addAttribute("token", token.getToken());   	
    	
    	return "login";
    }
    
    @RequestMapping("/loginerror")
    public String loginerror() {
    	return "loginerror";
    }

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
    	
    	CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
    	model.addAttribute("token", token.getToken());   	
    	
        return "home";
    }
}
