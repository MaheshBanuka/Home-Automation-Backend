package controller;

import Dto.Cart;
import Repository.CartRepo;
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

@WebServlet(name = "ViewcartServlet", value = "/ViewcartServlet")
public class ViewcartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = new Cart(req.getParameter("name"));
        UserService userService=new UserServiceImpl();
        int serviceqty[] = userService.getqty(cart);
        String servicenames[] = userService.findcart(cart);
        List<String> listnames = Arrays.asList(servicenames);
        String servicenamesres = new Gson().toJson(listnames);
        String serviceqtyres = new Gson().toJson(serviceqty);
//        System.out.println(servicenamesres);
//        System.out.println(serviceqtyres);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("servicenames", servicenamesres);
        json.addProperty("serviceqty", serviceqtyres);
        writer.print(json.toString());
        writer.flush();
    }
}
