package controller;

import Dto.Login;
import Dto.User;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Login login = new Login(req.getParameter("name"), req.getParameter("password"));
        UserService userService = new UserServiceImpl();
        String result = userService.loginUser(login);
        String servicenames[] = userService.userservices(login);
        int serviceqty[] = userService.getqtyor(login);
        List<String> list = Arrays.asList(servicenames);
        String responseData = new Gson().toJson(list);
        String serviceqtyres = new Gson().toJson(serviceqty);
        System.out.println(serviceqtyres);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("serviceqty", serviceqtyres);
        json.addProperty("name", ""+result);
        json.addProperty("servicenames", responseData);
//        System.out.println(responseData);
        writer.print(json.toString());
        writer.flush();
    }
}
