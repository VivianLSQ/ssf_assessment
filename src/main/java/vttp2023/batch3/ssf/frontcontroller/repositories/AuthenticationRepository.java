package vttp2023.batch3.ssf.frontcontroller.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	@Autowired
	@Qualifier("login")
	private RedisTemplate<String, Object> template; 

	// Task 5
	// Use this class to implement CRUD operations on Redis
	template.opsForValue().set(login.getUsername(), login.toJson(). toString());
	String result = (String) template
		.opsForValue()
		.get(login.getUsername()); 
	if(result !=null){
		return 1; 
	}
	return 0; 


}
