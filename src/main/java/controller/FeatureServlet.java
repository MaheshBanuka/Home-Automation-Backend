package controller;

import Repository.FeatureRepo;
import Repository.Myser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "FeatureServlet", value = "/FeatureServlet")
public class FeatureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FeatureRepo feature = new FeatureRepo();
        String servicenames[] = feature.fename(request.getParameter("name"),request.getParameter("serid"),request.getParameter("qty"));
        List<String> listnames = Arrays.asList(servicenames);
        String servicenamesres = new Gson().toJson(listnames);
        System.out.println(servicenamesres);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("servicenames", servicenamesres);
        writer.print(json.toString());
        writer.flush();
    }
}
