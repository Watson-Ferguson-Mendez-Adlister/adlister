package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/////Servlet for Creating New Ads/////

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //if the user is not logged in, this saves the createAd url to be used as a redirect
        if(request.getSession().getAttribute("user") == null) {
           Object dispatcher = request.getRequestURL();
           request.getSession().setAttribute("previousUrl", dispatcher);
            response.sendRedirect("/login");
            // add a return statement to exit out of the entire method.
            return;
        } else {
            Object dispatcher = request.getRequestURL();
            request.getSession().setAttribute("url", true);
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    /////Creates uses the user-input when creating an Ad to push the created Ad into the database/////
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loggedInUser = (User) request.getSession().getAttribute("user");
        Ad ad = new Ad(
                loggedInUser.getId(),
                request.getParameter("title"),
                request.getParameter("description"),
                request.getParameter("category")
        );
        request.getSession().setAttribute("url", false);
        DaoFactory.getAdsDao().insert(ad);
        response.sendRedirect("/index");
    }
}
