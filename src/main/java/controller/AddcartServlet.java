package controller;

import Dto.Cart;
import Dto.User;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddcartServlet", value = "/AddcartServlet")
public class AddcartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("fun").equals("add")){
            Cart cart = new Cart(req.getParameter("name"),
                    req.getParameter("servicename"),
                    req.getParameter("qty"));
            UserService userService=new UserServiceImpl();
            String result = userService.addcart(cart);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JsonObject json = new JsonObject();
            json.addProperty("Response", "test"+result);
            writer.print(json.toString());
            writer.flush();
        }

    }
}
