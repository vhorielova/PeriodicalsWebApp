package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.PeriodicalDaoImpl;
import org.periodicalswebapp.daoimpl.SubscriptionDaoImpl;
import org.periodicalswebapp.models.Periodical;
import org.periodicalswebapp.models.Subscription;
import org.periodicalswebapp.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CatalogHandler implements Handler {
    PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
    SubscriptionDaoImpl subscriptionDao = new SubscriptionDaoImpl();
    Logger logger = LoggerFactory.getLogger(CatalogHandler.class);

    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Handling GET request for catalog page.");
        List<Periodical> periodicals = periodicalDao.getAllPeriodicals();
        logger.debug("Fetched {} periodicals from database.", periodicals.size());
        request.setAttribute("periodicals", periodicals);

        HttpSession session = request.getSession();
        HashMap <Periodical, Integer> periodicalsCart = session.getAttribute("periodicalsCart") == null ? new HashMap<>() : (HashMap<Periodical, Integer>) session.getAttribute("periodicalsCart");
        logger.debug("Cart contains {} items.", periodicalsCart.size());
        request.setAttribute("periodicalsCart", periodicalsCart);

        subscriptionDao.update();
        int userId = ((User) session.getAttribute("user")).getId();
        //List <Periodical> order = session.getAttribute("order") == null ? new ArrayList<>() : (List<Periodical>) session.getAttribute("order");
        List <Subscription> subscriptions = subscriptionDao.getAllUserSubscriptions(((User) session.getAttribute("user")).getId());
        logger.debug("Fetched {} subscriptions for user ID {}.", subscriptions.size(), userId);
        request.setAttribute("subscriptions", subscriptions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalog.jsp");
        dispatcher.forward(request, response);
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        logger.info("Handling POST request for catalog page.");
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

            logger.debug("Adding periodical ID {} with subscription period {} to cart.", periodicalId, period);

            periodicalsCart.put(periodical, period);

            session.setAttribute("periodicalsCart", periodicalsCart);
            logger.info("Periodical '{}' added to cart.", periodical.getName());
        }
        else if(delete != null) {
            PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
            periodicalDao.deletePeriodical(Integer.parseInt(delete));
            logger.info("Deleting periodical with ID {}.", Integer.parseInt(delete));
        }
        else if(add != null) {
            PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();

            String name = request.getParameter("name");

            if(name.isEmpty() || request.getParameter("halfYearPrice").isEmpty() || request.getParameter("fullYearPrice").isEmpty()){
                logger.warn("Attempted to add periodical, but not all fields were filled.");
                request.setAttribute("error", "Заповніть всі поля");
                handleGet(request, response);
            }
            double halfYearPrice = Double.parseDouble(request.getParameter("halfYearPrice"));
            double fullYearPrice = Double.parseDouble(request.getParameter("fullYearPrice"));

            logger.debug("Adding new periodical: name = '{}', halfYearPrice = {}, fullYearPrice = {}", name, halfYearPrice, fullYearPrice);

            Periodical periodical = new Periodical(name, halfYearPrice, fullYearPrice);
            periodicalDao.savePeriodical(periodical);
            logger.info("New periodical '{}' saved to database.", name);
        }


        handleGet(request, response);
    }
}
