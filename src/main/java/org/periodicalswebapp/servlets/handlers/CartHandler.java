package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.PeriodicalDaoImpl;
import org.periodicalswebapp.models.Periodical;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartHandler implements Handler {
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("delete") != null && request.getParameter("delete").equals("true")) {
            HttpSession session = request.getSession();
            List<Periodical> periodicalsCart = session.getAttribute("periodicalsCart") == null ? new ArrayList<>() : (List<Periodical>) session.getAttribute("periodicalsCart");

            PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
            int periodicalId = Integer.parseInt(request.getParameter("periodicalId"));

//            periodicalsCart.remove(periodical);
            periodicalsCart = periodicalsCart.stream().filter(x -> x.getId() != periodicalId).collect(Collectors.toList());

            session.setAttribute("periodicalsCart", periodicalsCart);
            handleGet(request, response);
        }
        else{
        }
    }
}
