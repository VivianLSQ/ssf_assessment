package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random; 

@Controller
@RequestMapping("captcha")
public class CaptchaController {

    public static String generateMathCaptcha(){
    Random number = new Random();
    int randomNumber1 = number.nextInt(50-1)+1; 
    int randomNumber2 = number.nextInt(50-1)+1; 

    System.out.println(
        "What is"
        + randomNumber1
        + " "
        + "+-*/".charAt((new Random()).nextInt(4))
        + " "
        + randomNumber2);
    String answer = randomNumber1 + "operator" + randomNumber2;
    return answer; 

 
  }
}




    
