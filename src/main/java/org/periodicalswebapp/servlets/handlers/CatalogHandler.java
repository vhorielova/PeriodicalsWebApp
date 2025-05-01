package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.periodicalswebapp.daoimpl.PeriodicalDaoImpl;
import org.periodicalswebapp.models.Periodical;

import java.io.IOException;
import java.util.List;

public class CatalogHandler implements Handler {
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
        List<Periodical> periodicals = periodicalDao.getAllPeriodicals();
        System.out.println(periodicals);
        request.setAttribute("periodicals", periodicals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalog.jsp");
        dispatcher.forward(request, response);
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog");
    }
}
