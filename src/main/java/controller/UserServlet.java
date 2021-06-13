package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import Dto.User;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.JsonObject;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        System.out.println("hgfghf");
        writer.print("Response: \n");
        writer.print("appuhami silva");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("name"),req.getParameter("password"));
        System.out.println(user.getUserName());
        UserService userService=new UserServiceImpl();
        String result = userService.registerUser(user);
        String name = req.getParameter("name");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("Response", "test"+result);
        writer.print(json.toString());
        writer.flush();
    }
}
