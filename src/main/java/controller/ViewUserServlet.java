package controller;

import Repository.Myser;
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

@WebServlet(name = "ViewUserServlet", value = "/ViewUserServlet")
public class ViewUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewUserRepo viewuser = new ViewUserRepo();
        String usernames[] = viewuser.viewusername();
        int userid[] = viewuser.viewuserid();
        List<String> listnames = Arrays.asList(usernames);
        String usernamesres = new Gson().toJson(listnames);
        String useridst = new Gson().toJson(userid);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("userid", useridst);
        json.addProperty("usernames", usernamesres);
        writer.print(json.toString());
        writer.flush();
    }
}
