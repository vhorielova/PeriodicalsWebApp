package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.PeriodicalDaoImpl;
import org.periodicalswebapp.models.Periodical;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatalogHandler implements Handler {
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
        List<Periodical> periodicals = periodicalDao.getAllPeriodicals();
        System.out.println(periodicals);
        request.setAttribute("periodicals", periodicals);

        HttpSession session = request.getSession();
        List <Periodical> periodicalsCart = session.getAttribute("periodicalsCart") == null ? new ArrayList<>() : (List<Periodical>) session.getAttribute("periodicalsCart");
        request.setAttribute("periodicalsCart", periodicalsCart);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalog.jsp");
        dispatcher.forward(request, response);
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        List <Periodical> periodicalsCart = session.getAttribute("periodicalsCart") == null ? new ArrayList<>() : (List<Periodical>) session.getAttribute("periodicalsCart");

        PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
        int periodicalId = Integer.parseInt(request.getParameter("periodicalId"));
        Periodical periodical = periodicalDao.getPeriodicalById(periodicalId);

        periodicalsCart.add(periodical);

        session.setAttribute("periodicalsCart", periodicalsCart);
        handleGet(request, response);
    }
}
