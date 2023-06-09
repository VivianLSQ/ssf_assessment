package vttp2023.batch3.ssf.frontcontroller.model;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginUsers implements Serializable{
    @NotNull(message="Please input your username")
    @Size(min=2, message="Your name cannot be less than 2 chars")
    @NotBlank(message="Please input your username")
    private String username; 

    @NotNull(message="Please input your password")
    @Size(min=2, message="Your name cannot be less than 2 chars")
    @NotBlank(message="Please input your password")
    private String password; 

    private String captcha; 

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public static LoginUsers createFromJson(JsonObject o) { 
        LoginUsers l = new LoginUsers(); // instantiate new Java class object
        String login = o.getString("login"); // getString to get login 
        l.setUsername(login); // using setter
        l.setPassword(login); 
        return l; // return the Java object
        }
        // from Java to JSON
        public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder() // Json.createObjectBuilder
        .add("username", this.getUsername()) // using getter
        .add("password", this.getPassword()); // using getter
        } 
    
    

}
