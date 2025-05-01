package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.UserDaoImpl;
import org.periodicalswebapp.models.User;

import java.io.IOException;

public class ProfileHandler implements Handler {
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String index = request.getParameter("index");

        if(name.isEmpty() || lastname.isEmpty() || address.isEmpty() || index.isEmpty()) {
            request.setAttribute("error", "Поля не можуть бути пустими");
            handleGet(request, response);
        }
        else{
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            user.setName(name);
            user.setLastname(lastname);
            user.setAddress(address);
            user.setIndex(index);

            userDao.updateUser(user);

            session.setAttribute("user", user);
            request.setAttribute("updated", true);
            handleGet(request, response);
        }

    }
}
