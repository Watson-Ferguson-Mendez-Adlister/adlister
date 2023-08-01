package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdServlet", urlPatterns = "/ads/*")
public class ViewAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        Long id = Long.parseLong(pathInfo.substring(1));

        Ad ad = DaoFactory.getAdsDao().find(id);

        request.setAttribute("ad", ad);

        request.getRequestDispatcher("/WEB-INF/ads/viewAd.jsp").forward(request, response);
    }
}

