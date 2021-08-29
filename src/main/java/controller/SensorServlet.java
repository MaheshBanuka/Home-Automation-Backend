package controller;

import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SensorServlet")
public class SensorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(req.getParameter("sensor"));
        UserService userService = new UserServiceImpl();
        boolean result = userService.saveSensorData(req.getParameter("sensor"), req.getParameter("featureid"));
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();

        json.addProperty("Response", "mytest" + result);
        writer.print(json.toString());
        writer.flush();
    }
}
