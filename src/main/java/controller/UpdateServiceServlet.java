package controller;

import Repository.ViewServicesRepo;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateServiceServlet", value = "/UpdateServiceServlet")
public class UpdateServiceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ViewServicesRepo viewservice = new ViewServicesRepo();
        String result = viewservice.updateservice(req.getParameter("id"), req.getParameter("name"), req.getParameter("cost"));
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("response", ""+result);
        writer.print(json.toString());
        writer.flush();
    }
}
