package controller;

import Repository.compayRepo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CompletePaymentServlet", value = "/CompletePaymentServlet")
public class CompletePaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        compayRepo cmpr = new compayRepo();
        cmpr.compay(req.getParameter("name"));
    }
}
