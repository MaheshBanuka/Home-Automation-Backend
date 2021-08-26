package controller;

import Repository.FeatureRenameRepo;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FeatureRenameServlet", value = "/FeatureRenameServlet")
public class FeatureRenameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FeatureRenameRepo ferename = new FeatureRenameRepo();
        String result = ferename.ferename(request.getParameter("fid"),request.getParameter("fname"));
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("response", ""+result);
        writer.print(json.toString());
        writer.flush();
    }
}
