package controller;

import Repository.ViewUserRepo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "UserBannedServlet", value = "/UserBannedServlet")
public class UserBannedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewUserRepo viewuser = new ViewUserRepo();
        String result = viewuser.userbanned(Integer.valueOf(request.getParameter("userid")));
        System.out.println("asdasdsda");
        System.out.println(Integer.valueOf(request.getParameter("userid")));
        String results = new Gson().toJson(result);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonObject json = new JsonObject();
        System.out.println(result);
        json.addProperty("result", results);
        writer.print(json.toString());
        writer.flush();
    }
}
