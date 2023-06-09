package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Size;

import java.io.OutputStream;
import java.util.Random; 

@Controller
@RequestMapping("captcha")
public class CaptchaController {
randomNumber1 = number.nextInt(50-1)+1; 
randomNumber2 = number.nextInt(50-1)+1; 

System.out.println(
    "What is"
    + randomNumber1
    + " "
    + "+-*/".charAt((new Random()).newInt(4))
    + " "
    + randomNumber2); 

answerTotal = randomNumber1 + operator + randomNumber2; 

// OutputStream os = response.getOutputStream();
// CaptchaMath.write(os);
// HttpSession session = request.getSession();
// session.setAttribute("captcha_security", )

}




    
