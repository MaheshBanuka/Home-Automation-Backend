package controller;

import Dto.Login;
import Dto.User;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Login login = new Login(req.getParameter("name"), req.getParameter("password"));
        UserService userService = new UserServiceImpl();
        String result = userService.loginUser(login);
        String servicenames[] = userService.userservices(login);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("Response", "test"+result);
        json.addProperty("name", ""+result);
        json.addProperty("servicenames", ""+servicenames);
        System.out.println(servicenames[1]);
        writer.print(json.toString());
        writer.flush();
    }
}
