package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import com.codeup.adlister.util.Validation;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Required in order to not have "null" pre-filled in text box
        if (request.getSession().getAttribute("prevUsername") == null) {
            request.getSession().setAttribute("prevUsername", "");
            request.getSession().setAttribute("prevEmail", "");
            request.getSession().setAttribute("prevPassword", "");
        }
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        request.getSession().setAttribute("prevUsername", username);
        String email = request.getParameter("email");
        request.getSession().setAttribute("prevEmail", email);
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // validate input
        boolean userHasErrors = username.isEmpty() || (!Validation.isValidUsername(username));
        boolean userExists = DaoFactory.getUsersDao().findByUsername(username) != null;

        boolean passHasErrors = password.isEmpty()
                || (!password.equals(passwordConfirmation));

        boolean emailHasErrors = email.isEmpty() || !Validation.isValidEmail(email) || DaoFactory.getUsersDao().findByEmail(email) != null;

        if (userHasErrors) {
            request.setAttribute("userError", "Username invalid, please try again.");
        } else if (userExists) {
            request.setAttribute("userError", "Username already exists.");
        }

        if (userHasErrors || userExists) {
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } else if (emailHasErrors) {
            request.setAttribute("emailError", "Email invalid, please enter a new email.");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } else if (passHasErrors) {
            request.setAttribute("passError", "Passwords do not match, please try again.");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
