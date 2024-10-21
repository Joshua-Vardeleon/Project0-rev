package com.revature.controllers;
import com.revature.DAO.LoginDAO;
import com.revature.models.Login;
import com.revature.models.Trainer;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class LoginController {
    //Instantiate the AuthDAO so we can try to log in a user
    LoginDAO lDAO = new LoginDAO();

    //HttpSession object to store user info after successful login
    //This object will be initialized upon successful login, letting a user access the app
    public static HttpSession ses;

    //login will be a POST request, since we're sending a username and password (id and name for this)
    public Handler loginHandler = ctx -> {

        //Extract the Request body as a LoginDTO object
        Login login = ctx.bodyAsClass(Login.class);

        //Use the LoginDTO data to send to the DAO and try to log in!
        Trainer loggedInTrainer = lDAO.Login(login.getTrainer_id(), login.getTrainer_name());

        //The DAO will either return an Employee (login success!) or null (login fail!)
        if(loggedInTrainer != null){
            //Create a session object
            ses = ctx.req().getSession();

            //Store user info in the Session with the setAttribute() method
            ses.setAttribute("trainer_name", loggedInTrainer.getTrainer_name());

            //NOTE: we could have stored any kind of info in the Session like this (id? role?)

            /* Foreshadowing for P1: Each Employee will have a role of employee or manager
              Managers will be able to do things that Employees can't
              Their identity as a manager or employee will be stored in their session after login
              Which will let us control what functionalities they can and can't access */

            //ok... how do we access the data we stored in a session? .getAttribute()!
            System.out.println("Trainer name: " + ses.getAttribute("trainer_name"));

            //send a success message
            ctx.status(200);
            ctx.result("Login Successful! Welcome, " + ses.getAttribute("trainer_name"));


        } else {
            //If login fails, a good status code is 401 - Unauthorized
            ctx.status(401);
            ctx.result("Login failed! Try again.");
        }

    };
}
