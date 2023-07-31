package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("prevUsername") == null) {
            request.getSession().setAttribute("prevUsername", "");
            request.getSession().setAttribute("prevEmail", "");
            request.getSession().setAttribute("prevPassword", "");
        }
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        request.getSession().setAttribute("prevUsername", username);
        String email = request.getParameter("email");
        request.getSession().setAttribute("prevEmail", email);
        String password = request.getParameter("password");
        request.getSession().setAttribute("prevPassword", password);
        String passwordConfirmation = request.getParameter("confirm_password");

        // validate input
        boolean inputHasErrors = username.isEmpty() || DaoFactory.getUsersDao().findByUsername(username) != null
                || email.isEmpty() || DaoFactory.getUsersDao().findByEmail(email) != null
                || password.isEmpty()
                || (!password.equals(passwordConfirmation));

        if (inputHasErrors) {
            response.sendRedirect("/register");
            return;
        }
        // create and save a new user
        User user = new User(username, email, password);

        // hash the password

        String hash = Password.hash(user.getPassword());

        user.setPassword(hash);
        DaoFactory.getUsersDao().insert(user);

        response.sendRedirect("/login");

    }
}
