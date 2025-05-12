package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.UserDaoImpl;
import org.periodicalswebapp.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ProfileHandler implements Handler {
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();
    Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Handling GET request for profile page");
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Handling POST request for profile page");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String index = request.getParameter("index");

        logger.debug("Received profile update: name={}, lastname={}, address={}, index={}", name, lastname, address, index);

        if(name.isEmpty() || lastname.isEmpty() || address.isEmpty() || index.isEmpty()) {
            logger.warn("Profile update failed: one or more fields are empty");
            request.setAttribute("error", "Поля не можуть бути пустими");
            handleGet(request, response);
        }
        else{
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            logger.debug("Updating user with ID {}: name={}, lastname={}, address={}, index={}",
                    user.getId(), name, lastname, address, index);

            user.setName(name);
            user.setLastname(lastname);
            user.setAddress(address);
            user.setIndex(index);

            userDao.updateUser(user);

            logger.info("User profile updated successfully for user ID {}", user.getId());

            session.setAttribute("user", user);
            request.setAttribute("updated", true);
            handleGet(request, response);
        }

    }
}
