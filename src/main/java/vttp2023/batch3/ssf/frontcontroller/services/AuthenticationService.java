package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.Date;

import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.LoginUsers;

@Service
public class AuthenticationService {

@Value("${ssf_assessment.api.authenticate}")
private String authenticateUrl = 
		"https://authservice-production-e8b2.up.railway.app"; 

	// Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	@PostMapping (consumes= "application/json", headers="Accept=application/json)")
	public String authenticate(String username, String password) throws Exception {
	//changed from "void" to "String", and return view1 
	//(if auth fails, then view 0)


			//hardcode API URL or use URIbuilder
			String authUrl = UriComponentsBuilder
							.fromUriString(authenticateUrl)
							.queryParam("username", "fred")
							.queryParam("password", "fredfred")
							.toUriString(); 
		
			//to enclose RestTemplate in try/catch, and use exchange()				
			RequestEntity req = RequestEntity.get(authenticateUrl). build();
			RestTemplate template = new RestTemplate(); 
			ResponseEntity<String> resp = template.exchange(req, String.class);
			return authenticateUrl; 

			//if username or password inputted does not match/ aka incorrect, return view 0
			// if(("username"||"password") != ("login.username"||"login.password")){
			// 	return "view0"; 

			}


	// Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		// if(loginCount>= 3){//then disable user 
		// 	//can use session id to track (?); or use hashmap (invalid username, invalid password and incorrect captcha answer)
		
		int loginAttempt;{
            HttpSession session = new StandardSession(null);
            if (session.getAttribute("loginCount") != null)
                {
                    session.setAttribute("loginCount", 0);
                    loginAttempt = 0;
                }
            else
            {
                loginAttempt = (Integer) session.getAttribute("loginCount");
            }
		//3 attempts, counting from 0,1,2
		if (loginAttempt >= 2 )
		{        
			long lastAccessedTime = session.getLastAccessedTime();
			Date Date = new Date();
			long currentTime = Date.getTime();
			long timeDiff = currentTime - lastAccessedTime;
			// 30 minutes in milliseconds  
			if (timeDiff >= 1.8e+6)
			{
				//invalidate user session, so they can try again
				session.invalidate();
			}
			else
			{
				 // Error message 
				 session.setAttribute("message","You have exceeded the 3 failed login attempts. Please try logging in 30 minutes later.");
			}  

		}
		else
		{
			loginAttempt++;
			int allowLogin = 3-loginAttempt;
			session.setAttribute("message","loginAttempt= "+loginAttempt+". Invalid username or password, or wrong answer to Captcha. You have "+allowLogin+" attempts remaining!");
		}
		session.setAttribute("loginCount",loginAttempt);
	}

	}

	// Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		//if(30 min not up yet), return to View 2
		//else return to View 0 to try login again 

		return false;
	}


	public void save(@Valid LoginUsers login, Model model) {
	}


    public void setAttribute(String string, @Valid LoginUsers login) {
    }




}