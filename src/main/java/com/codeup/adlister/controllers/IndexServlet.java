package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all ads
        List<Ad> ads = DaoFactory.getAdsDao().all();

        request.setAttribute("ads", ads);

        // Forward the request to the index.jsp page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
