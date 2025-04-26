package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;


public interface Handler {
    void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
    void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
