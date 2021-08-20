package controller;

import Lightonoff.OnOff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OnOffServlet", value = "/OnOffServlet")
public class OnOffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OnOff onoff = new OnOff();
        onoff.action(request.getParameter("id"), request.getParameter("value"));
        System.out.println("servlet on");
    }
}
