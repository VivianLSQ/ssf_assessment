package vttp2023.batch3.ssf.frontcontroller.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import ch.qos.logback.core.net.LoginAuthenticator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;
import vttp2023.batch3.ssf.frontcontroller.model.LoginUsers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

// TODO: Task 2, Task 3, Task 4, Task 6

@Controller
@RequestMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
				headers="Accept=text/html)")
public class FrontController {

	@Autowired
	private LoginUsers login ; 

	@Autowired
	private AuthenticationService authSvc; 

	//Landing Page 
	@PostMapping(path="/login")
	public String loginUser(Model model, HttpSession session, 
	@Valid LoginUsers login, BindingResult result){
		if(result.hasErrors()){
			return "view0";
		
			List<ObjectError> errors = authSvc.validateLoginUsers(login);
			if(!errors.isEmpty()){
			    for(ObjectError e :errors)
			        login.addError(e);
			    return "view0";
			}

		authSvc.setAttribute("login", login);
		//m.addAttribute("object", new Object());
		return "view1";
		}
	
		}

	
	//write Http request handler, use AuthenticationService
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		//Use AuthenticationService.authenticate();
	
	public ResponseEntity<String> saveLogin(@Valid LoginUsers login, BindingResult result, Model model){
		if(result.hasErrors()){
			return "view0";
		}
		authSvc.save(login, model);
		LoginUsers.addAttribute("successMessage", "Correct login credentials inputted, with status code:" + HttpStatus.CREATED + "."); 
		return "view1"; 
	}
	}

       
	//Task 3
	//Invalid payload (incorrect Payload Request)
	if(result == null) {
		return ResponseEntity
		.status(HttpStatus.BAD_REQUEST) // 400 status code
		.contentType(MediaType.APPLICATION_JSON)
		.body("Invalid payload");
		}
	
	//invalid username and/or password
	if(result == null) {
		return ResponseEntity
		.status(HttpStatus.UNAUTHORIZED) // 401 status code
		.contentType(MediaType.APPLICATION_JSON)
		.body("Invalid username and/or password");
		}
	
		//Redisplay View 0 with error message and captcha 
		return "view0" ; 



	//remember to invalidate session at the end (task 6; logout button)
	//authSvc.invalidate(); 
    }
}

	


