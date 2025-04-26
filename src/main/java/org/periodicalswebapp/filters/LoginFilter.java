package org.periodicalswebapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("Preprocessing for filter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String URI = String.valueOf(req.getServletPath());
        boolean isLoggedIn = req.getSession().getAttribute("user") != null;

        if(isLoggedIn && (URI.equals("/login") || URI.equals("/register"))) {
            res.sendRedirect(req.getContextPath() + "/catalog");
        }
        else if(isLoggedIn || URI.equals("/login") || URI.equals("/register") || URI.equals("/")) {
            chain.doFilter(request, response);
        }
        else {
            res.sendRedirect(req.getContextPath() + "/login");
        }


        System.out.println("PostProcessing for filter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
