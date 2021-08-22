package controller;

import Dto.Login;
import Repository.ViewServicesRepo;
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

@WebServlet(name = "ViewServicesServlet", value = "/ViewServicesServlet")
public class ViewServicesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("asdasdasdasdasd");
        ViewServicesRepo viewservice = new ViewServicesRepo();
        String servicenames[] = viewservice.viewservice();
        int serviceid[] = viewservice.viewserviceid();
        double servicecost[] = viewservice.viewservicecost();
        List<String> list = Arrays.asList(servicenames);
        String responseData = new Gson().toJson(list);
        String serviceids = new Gson().toJson(serviceid);
        String servicecosts = new Gson().toJson(servicecost);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("serviceid", serviceids);
        json.addProperty("servicecost", servicecosts);
        json.addProperty("servicenames", responseData);
        writer.print(json.toString());
        writer.flush();
    }
}
