package controller;

import Repository.ComOrderRepo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CompleatOrderServlet", value = "/CompleatOrderServlet")
public class CompleatOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComOrderRepo comorder = new ComOrderRepo();
        String result = comorder.comorder(request.getParameter("orderid"));
        String resultst = new Gson().toJson(result);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("result", resultst);
        writer.print(json.toString());
        writer.flush();
    }
}
