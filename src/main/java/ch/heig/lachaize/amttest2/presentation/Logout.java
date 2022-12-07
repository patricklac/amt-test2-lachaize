package ch.heig.lachaize.amttest2.presentation;

import ch.heig.lachaize.amttest2.integration.CritiqueDAO;
import ch.heig.lachaize.amttest2.integration.LivreDAO;
import ch.heig.lachaize.amttest2.model.Critique;
import ch.heig.lachaize.amttest2.model.Livre;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("critique",null);
        req.getRequestDispatcher("/").forward(req, resp);
    }

}
