package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.SubscriptionDaoImpl;
import org.periodicalswebapp.models.Periodical;
import org.periodicalswebapp.models.Subscription;
import org.periodicalswebapp.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CartHandler implements Handler {
    Logger logger = LoggerFactory.getLogger(CartHandler.class);

    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Handling GET request for cart page.");
        request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Handling POST request for cart page.");
        SubscriptionDaoImpl subscriptionDao = new SubscriptionDaoImpl();
        if(request.getParameter("delete") != null && request.getParameter("delete").equals("true")) {
            HttpSession session = request.getSession();
            List<Periodical> periodicalsCart = session.getAttribute("periodicalsCart") == null ? new ArrayList<>() : (List<Periodical>) session.getAttribute("periodicalsCart");

            int periodicalId = Integer.parseInt(request.getParameter("periodicalId"));

            logger.info("Periodical with ID {} removed from cart.", periodicalId);

            periodicalsCart = periodicalsCart.stream().filter(x -> x.getId() != periodicalId).collect(Collectors.toList());

            session.setAttribute("periodicalsCart", periodicalsCart);
        }
        else if(request.getParameter("order") != null && request.getParameter("order").equals("true")) {
            HttpSession session = request.getSession();
            HashMap<Periodical, Integer> order = (HashMap<Periodical, Integer>) session.getAttribute("periodicalsCart");
            User user = (User) session.getAttribute("user");
            for (Periodical periodical : order.keySet()) {
                logger.debug("Ordering periodical ID: {}, for period: {}", periodical.getId(), order.get(periodical));
                Subscription subscription = new Subscription();
                subscription.setUserId(user.getId());
                subscription.setPeriodicalId(periodical.getId());
                subscription.setPeriod(order.get(periodical));
                subscriptionDao.saveSubscription(subscription);
                logger.info("Subscription saved for user ID {} and periodical ID {}", user.getId(), periodical.getId());
            }
            session.setAttribute("periodicalsCart", new HashMap<>());
            logger.info("Cart has been cleared after order submission.");
        }
        handleGet(request, response);
    }
}
