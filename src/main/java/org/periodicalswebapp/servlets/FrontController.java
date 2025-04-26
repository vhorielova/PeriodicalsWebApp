package org.periodicalswebapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.periodicalswebapp.servlets.handlers.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FrontController extends HttpServlet {
    private String message;
    Map<String, Handler> routes = new HashMap<>();

    public void init() {
        routes.put("/", new IndexHandler());
        routes.put("/login", new LoginHandler());
        routes.put("/register", new RegisterHandler());
        routes.put("/catalog", new HelloHandler());
        routes.put("/profile", new HelloHandler());
        routes.put("/cart", new HelloHandler());
        message = "FrontController";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String URI = String.valueOf(request.getServletPath());

        Handler handler = routes.get(URI);
        if(handler != null) {
            handler.handlePost(request, response);
        }
        else {
            throw new ServletException("No handler found for " + URI);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String URI = String.valueOf(request.getServletPath());
        //System.out.println(URL);
        Handler handler = routes.get(URI);

        if(handler != null) {
            handler.handleGet(request, response);
        }
        else {
            throw new ServletException("No handler found for " + URI);
        }


        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
