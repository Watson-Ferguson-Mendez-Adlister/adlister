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

/////Servlet for the Home Page/////

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("url", false);

        // Fetch all ads
        List<Ad> ads = DaoFactory.getAdsDao().all();

        Map<String, List<Ad>> categories = new HashMap<>();
        for (Ad ad : ads) {
            String category = ad.getCategory();
            if (!categories.containsKey(category)) {
                categories.put(category, new ArrayList<>());
            }
            categories.get(category).add(ad);
        }

        Map<String, String> categoryDisplayNames = new HashMap<>();
        categoryDisplayNames.put("community", "Community");
        categoryDisplayNames.put("events", "Events");
        categoryDisplayNames.put("forSale", "For Sale");
        categoryDisplayNames.put("jobs", "Jobs");
        categoryDisplayNames.put("housing", "Housing");
        categoryDisplayNames.put("services", "Services");
        categoryDisplayNames.put("gigs", "Gigs");

        request.setAttribute("categories", categories);
        request.setAttribute("categoryDisplayNames", categoryDisplayNames);

        // Forward the request to the index.jsp page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
