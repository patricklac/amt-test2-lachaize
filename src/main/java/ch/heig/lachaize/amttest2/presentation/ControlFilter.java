package ch.heig.lachaize.amttest2.presentation;

import ch.heig.lachaize.amttest2.model.Critique;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ControlFilter", urlPatterns = "/notation")
public class ControlFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Critique critique = (Critique) request.getSession().getAttribute("critique");
        if (critique == null) {
            req.getRequestDispatcher("/login").forward(request, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
