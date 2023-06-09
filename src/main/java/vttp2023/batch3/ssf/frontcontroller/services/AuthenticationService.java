package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AuthenticationService {

@Value("${ssf_assessment.api.authenticate}")
private String authenticateUrl; 
//url: https://authservice-production-e8b2.up.railway.app

	// Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	@PostMapping (consumes= "application/json", headers="Accept=application/json)")
	public void authenticate(String username, String password) throws Exception {

			//hardcode API URL or use URIbuilder
			String authUrl = UriComponentsBuilder
							.fromUriString(authenticateUrl)
							.toUriString(); 


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