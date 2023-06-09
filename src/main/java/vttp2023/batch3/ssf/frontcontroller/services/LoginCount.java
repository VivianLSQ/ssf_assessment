package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.Date;

import org.apache.catalina.session.StandardSession;

import jakarta.servlet.http.HttpSession;

public class LoginCount {

    //Assuming user is using the same browser 

            // // (1) Log in valid user
            // login.setUsername(username);
            // login.setPassword(password);

            // // establish a user session
            // session.setAttribute("username", username);
            // session.setAttribute("password", password);
           
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

       
