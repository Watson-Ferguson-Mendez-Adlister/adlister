package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/////Individual Ad Servlet/////

@WebServlet(name = "AdServlet", urlPatterns = "/ads/*")
public class ViewAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        Long id = Long.parseLong(pathInfo.substring(1));

        Ad ad = DaoFactory.getAdsDao().find(id);
        User user = DaoFactory.getUsersDao().findById(ad.getUserId());

        request.setAttribute("ad", ad);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/ads/viewAd.jsp").forward(request, response);
    }
}

