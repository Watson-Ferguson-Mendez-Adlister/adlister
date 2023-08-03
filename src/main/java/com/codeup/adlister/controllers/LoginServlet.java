package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/////Servlet for Logging in/////

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirects to Home page if already Logged in
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/index");
            return;
        }
        //If-Statement for Sticky Forms so that the user-input box will be empty instead of saying Null
        if(request.getSession().getAttribute("prevUser") == null){
            request.getSession().setAttribute("prevUser", "");
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Saves input for Sticky Forms
        String username = request.getParameter("username");
        request.getSession().setAttribute("prevUser", username);
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);
        boolean validAttempt = false;

        //Checks the inputted password to see if it matches the hashed version in the database

        if(user != null){
            validAttempt = Password.check(password, user.getPassword());
        }

        //Error Messages

        if (user == null) {
            request.setAttribute("loginError", "Invalid username or password.");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else if(!validAttempt){
            request.setAttribute("loginError", "Invalid username or password.");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        //Intended Redirects

        if (request.getSession().getAttribute("previousUrl") != null) {
            request.getSession().setAttribute("user", user);
            String previous = request.getSession().getAttribute("previousUrl").toString();
            response.sendRedirect(previous);
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/index");
        }
    }
}
