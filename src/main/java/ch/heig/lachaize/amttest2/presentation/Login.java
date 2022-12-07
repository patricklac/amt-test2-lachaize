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

@WebServlet("/login")
public class Login extends HttpServlet {
    @Inject
    private CritiqueDAO critiqueDAO;

    @Inject
    private LivreDAO livreDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prenom = req.getParameter("prenom");
        String nom = req.getParameter("nom");
        Critique critique = critiqueDAO.getCritique(nom, prenom);
        if(critique == null) {
            req.setAttribute("alertmessage", "Critique inconnu");
            req.setAttribute("alerttype", "alert-danger");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        } else {
            List<Livre> livres = livreDAO.getLivresFromTheme(critique.getTheme());
            req.setAttribute("critique",critique);
            req.setAttribute("livres",livres);
            req.getSession().setAttribute("critique",critique);
            req.getRequestDispatcher("/WEB-INF/pages/notation.jsp").forward(req, resp);

        }
    }
}
