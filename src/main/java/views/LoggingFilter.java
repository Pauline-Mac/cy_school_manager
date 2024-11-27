package views;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import models.User;

import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        /*if (httpRequest.getSession(false) != null) {
            User user = (User) httpRequest.getSession().getAttribute("user");
            if (user != null) {
                System.out.println("Logged in user: " + user.getEmail());
            }
        }*/

        chain.doFilter(request, response);
    }
}