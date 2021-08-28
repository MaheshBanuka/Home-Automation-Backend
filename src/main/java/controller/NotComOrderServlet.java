package controller;

import Repository.OrderDetailsRepo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "NotComOrderServlet", value = "/NotComOrderServlet")
public class NotComOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDetailsRepo orderdet = new OrderDetailsRepo();
        int orderid[] = orderdet.viewnotcomorderid();
        int userid[] = orderdet.viewnotcomuserid();
        String orderdate[] = orderdet.viewnotcomorderdates();
//        String orderstatus[] = orderdet.viewnotcomorderstatus();
        List<String> listnames = Arrays.asList(orderdate);
        String orderdates = new Gson().toJson(listnames);
//        List<String> listorderstatus = Arrays.asList(orderstatus);
//        String orderstatusst = new Gson().toJson(listorderstatus);
        String useridst = new Gson().toJson(userid);
        String orderidst = new Gson().toJson(orderid);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("userid", useridst);
        json.addProperty("orderid", orderidst);
        json.addProperty("orderdates", orderdates);
//        json.addProperty("orderstatus", orderstatusst);
        writer.print(json.toString());
        writer.flush();
    }
}
