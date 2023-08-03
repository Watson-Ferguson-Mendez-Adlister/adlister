package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/////Deletes the Selected Profile from the Database

@WebServlet(name = "DeleteProfileServlet", urlPatterns = "/profile/delete")
public class DeleteProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");

        DaoFactory.getAdsDao().deleteAdsByUserId(user.getId());

        DaoFactory.getUsersDao().delete(user);

        request.getSession().invalidate();
        response.sendRedirect("/");
    }

}
