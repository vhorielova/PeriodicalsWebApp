package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.AbstractMultivaluedMap;
import org.periodicalswebapp.daoimpl.PeriodicalDaoImpl;
import org.periodicalswebapp.daoimpl.SubscriptionDaoImpl;
import org.periodicalswebapp.models.Periodical;
import org.periodicalswebapp.models.Subscription;
import org.periodicalswebapp.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogHandler implements Handler {
    PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
    SubscriptionDaoImpl subscriptionDao = new SubscriptionDaoImpl();
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Periodical> periodicals = periodicalDao.getAllPeriodicals();
        request.setAttribute("periodicals", periodicals);

        HttpSession session = request.getSession();
        HashMap <Periodical, Integer> periodicalsCart = session.getAttribute("periodicalsCart") == null ? new HashMap<>() : (HashMap<Periodical, Integer>) session.getAttribute("periodicalsCart");
        request.setAttribute("periodicalsCart", periodicalsCart);

        subscriptionDao.update();
        //List <Periodical> order = session.getAttribute("order") == null ? new ArrayList<>() : (List<Periodical>) session.getAttribute("order");
        List <Subscription> subscriptions = subscriptionDao.getAllUserSubscriptions(((User) session.getAttribute("user")).getId());
        request.setAttribute("subscriptions", subscriptions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalog.jsp");
        dispatcher.forward(request, response);
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String addToCart = request.getParameter("periodicalId");
        String delete = request.getParameter("deletePeriodical");
        String add = request.getParameter("addPeriodical");

        if(addToCart != null) {
            HttpSession session = request.getSession();
            HashMap <Periodical, Integer> periodicalsCart = session.getAttribute("periodicalsCart") == null ? new HashMap<>() : (HashMap<Periodical, Integer>) session.getAttribute("periodicalsCart");

            PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
            int periodicalId = Integer.parseInt(request.getParameter("periodicalId"));
            Periodical periodical = periodicalDao.getPeriodicalById(periodicalId);

            int period = Integer.parseInt(request.getParameter("subscriptionPeriod"));

            periodicalsCart.put(periodical, period);

            session.setAttribute("periodicalsCart", periodicalsCart);
        }
        else if(delete != null) {
            PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
            periodicalDao.deletePeriodical(Integer.parseInt(delete));
        }
        else if(add != null) {
            PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();

            String name = request.getParameter("name");

            if(name.isEmpty() || request.getParameter("halfYearPrice").isEmpty() || request.getParameter("fullYearPrice").isEmpty()){
                request.setAttribute("error", "Заповніть всі поля");
                handleGet(request, response);
            }
            double halfYearPrice = Double.parseDouble(request.getParameter("halfYearPrice"));
            double fullYearPrice = Double.parseDouble(request.getParameter("fullYearPrice"));

            Periodical periodical = new Periodical(name, halfYearPrice, fullYearPrice);
            periodicalDao.savePeriodical(periodical);
        }


        handleGet(request, response);
    }
}
