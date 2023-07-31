package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/edit")
public class EditAdServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        long adId = Long.parseLong(request.getParameter("adId"));

        Ad ad = DaoFactory.getAdsDao().find(adId);
        if (ad == null) {
            response.sendRedirect("/profile");
            return;
        }

        ad.setTitle(title);
        ad.setDescription(description);

        DaoFactory.getAdsDao().update(ad);

        response.sendRedirect("/profile");
    }
}
