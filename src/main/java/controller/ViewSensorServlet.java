package controller;

import Repository.ViewSensorRepo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ViewSensorServlet", value = "/ViewSensorServlet")
public class ViewSensorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewSensorRepo viewsens = new ViewSensorRepo();
        String senstime[] = viewsens.sensortime(request.getParameter("feid"));
        String[] date = new String[senstime.length];
        String[] time = new String[senstime.length];
        for (int i=0;i<senstime.length;i++){
            String temp[] = senstime[i].split(" ");
            date[i] = temp[0];
            time[i] = temp[1];
        }
        List<String> listdate = Arrays.asList(date);
        String sensdatest = new Gson().toJson(listdate);
        List<String> listtime = Arrays.asList(time);
        String senstimest = new Gson().toJson(listtime);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("sensdates", sensdatest);
        json.addProperty("senstimes", senstimest);
        writer.print(json.toString());
        writer.flush();
    }
}
