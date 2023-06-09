package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

			if(("username"||"password") != ("login.username"|"login.password")){
				return "view0"; 


			}
		
	}



	// Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		//if(30 min not up), return to View 2
		//else return to View 0 to try login again 
		return false;
	}



}