package vttp2023.batch3.ssf.frontcontroller.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.LoginUsers;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;


@RestController
@RequestMapping(path="/api/authenticate")
public class FrontController {

	@Autowired
	private LoginUsers login; 

	@Autowired
	private AuthenticationService authSvc; 

	@Autowired
	private CaptchaController captcha; 

	@PostMapping(path ="view0/login",
				consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
				headers="Accept=text/html)")
	public String login(@ModelAttribute("login") LoginUsers login,BindingResult result,Model model,HttpSession session) {
 
    if (login.getUsername()==null || login.getUsername().equals(""))
        {
            login.setCaptcha("");
            model.addAttribute("message", "Username is required");
            return "view1";
        }
 
    if (login.getPassword()==null || login.getPassword().equals(""))
    {
        login.setCaptcha("");
        model.addAttribute("message", "Password is required");
        return "view1";
    }   
 
     String captcha=(String)session.getAttribute("CAPTCHA");
        if(captcha==null || (captcha!=null && !captcha.equals(login.getCaptcha()))){
            login.setCaptcha("");
            model.addAttribute("message", "Captcha does not match");
            return "view0";
        }
 
        if(login.getUsername().equals("fred") && login.getPassword().equals("fredfred")){
            System.out.println("user id and password matches");
            model.addAttribute("loginId", login.getUsername());
            return "view1";
 
        }
        else{
            login.setCaptcha("");
            model.addAttribute("message","Username or Password Incorrect");
            return "view0";
        }
 
}
	
	public String loginUser(Model model, HttpSession session, 
	@Valid LoginUsers login, BindingResult result){
		if(result.hasErrors()){
			return "view0";

		authSvc.setAttribute("login", login);
		login.addAttribute("Username", new Username());
		login.addAttribute("Password", new Password());
		return "view1";
		}
		}
	
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		//Use AuthenticationService.authenticate();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("username", getUsername);
		String password;
		map.add("password", password);
	}
	
	public String saveLogin(@Valid LoginUsers login, BindingResult result, Model model){
		if(result.hasErrors()){
			return "view0";
		}
		authSvc.save(login, model);
		model.addAttribute("successMessage", "Correct login credentials inputted, with status code:" + HttpStatus.CREATED + "."); 
		System.out.println("Authenticated <username>"); 
		return "view1"; 
	}
	

       
	//Task 3
	//Invalid payload (incorrect Payload Request)
	@GetMapping(path="loginId")
	public ResponseEntity<String> getUsername(
		@PathVariable String loginId) throws IOException{
	
		LoginUsers result = null; 
		{
			return ResponseEntity
			.status(HttpStatus.BAD_REQUEST) // 400 status code
			.contentType(MediaType.APPLICATION_JSON)
			.body("Invalid payload");
		

			return ResponseEntity
			.status(HttpStatus.UNAUTHORIZED) // 401 status code
			.contentType(MediaType.APPLICATION_JSON)
			.body("Incorrect username and/or password"); 

		
			//Redisplay View 0 with error message and captcha 
			//return "view0"; 
		}

		
	
	

	//Task 4: To set expiration timeout
	//https://docs.spring.io/spring-data/redis/docs/current/api/org/springframework/data/redis/core/ValueOperations.html
		//Code: set(K key, V value, Duration timeout)
	//--> Set the value and expiration timeout for key.

	public void LoginTimeout(){

	}
	
	//Task 6: Logout user when session ends, and redisplay view0 when "/logout" button is pressed
	@GetMapping(path="/logout")
    public String logout(Model m, HttpSession s){
        System.out.println("Logging out of current session ...");
		// Login l= (Login)s.getAttribute("cart");
        // if(null == l){
        //     l = new Login();
        //     s.setAttribute("login", l);
        // }
        // m.addAttribute("username", new Username());
        // m.addAttribute("login", l);
        s.invalidate();
        return "view0";
    }
}


	


