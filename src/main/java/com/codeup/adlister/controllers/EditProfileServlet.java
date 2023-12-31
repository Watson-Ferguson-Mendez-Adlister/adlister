package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/////Gives the ability to Edit an already created Profile/////

@WebServlet(name = "EditProfileServlet", urlPatterns = "/profile/edit")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String editedUsername = request.getParameter("username");
        String editedEmail = request.getParameter("email");
        String editedPassword = request.getParameter("password");

        User user = (User) request.getSession().getAttribute("user");
        user.setUsername(editedUsername);
        user.setEmail(editedEmail);

        if(editedPassword != null && !editedPassword.isEmpty()) {
            String hashedPassword = Password.hash(editedPassword);
            user.setPassword(hashedPassword);
        }

        DaoFactory.getUsersDao().update(user);

        response.sendRedirect("/profile");
    }
}

