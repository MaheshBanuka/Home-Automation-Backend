package controller;

import Repository.DeleteCartRepo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartDeleteServlet", value = "/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeleteCartRepo delcart = new DeleteCartRepo();
        delcart.cartdelete(request.getParameter("name"),request.getParameter("sname"), request.getParameter("qty"));
        System.out.println("kjhkjh");
    }
}
