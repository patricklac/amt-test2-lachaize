package ch.heig.lachaize.amttest2.presentation;

import ch.heig.lachaize.amttest2.integration.LivreDAO;
import ch.heig.lachaize.amttest2.model.Livre;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class DisplayTitres extends HttpServlet {


    @Inject
    LivreDAO livreDAO;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livre> livres = livreDAO.getLivres();
        request.setAttribute("livres",livres);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
