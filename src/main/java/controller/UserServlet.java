package controller;

import Dto.User;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        System.out.println("run user");
        User user = new User(req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("phone"),
                req.getParameter("address"),
                req.getParameter("password"));
        UserService userService=new UserServiceImpl();
        String result = userService.registerUser(user);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();

        json.addProperty("Response", "test"+result);
        writer.print(json.toString());
        writer.flush();
    }
}
