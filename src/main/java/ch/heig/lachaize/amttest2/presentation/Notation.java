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
import java.util.HashMap;
import java.util.List;

/**
 * Gère le POST des notations d'un critique
 */
@WebServlet("/notation")
public class Notation extends HttpServlet {

    @Inject
    CritiqueDAO critiqueDAO;

    @Inject
    LivreDAO livreDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Critique critique = (Critique) request.getSession().getAttribute("critique");
        List<Livre> livres = livreDAO.getLivresFromTheme(critique.getTheme());
        request.setAttribute("livres", livres);
        request.getRequestDispatcher("/WEB-INF/pages/notation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Critique critique = (Critique) req.getSession().getAttribute("critique");
        List<Livre> livres = livreDAO.getLivresFromTheme(critique.getTheme());
        // hashmap<titre,note> pour reload par jsp des valeurs saisies dans les champs note
        HashMap<String, String> notes = new HashMap<>();
        req.setAttribute("notes", notes);
        // hashmap<titre,note> des notes valides saisies
        HashMap<String, Integer> notesOK = new HashMap<>();

        boolean allOK = true; // si toutes les notes saisies sont valides
        for (Livre livre : livres) {
            // Dans la jsp, les inputs sont nommés avec le titre de chaque livre
            String param = req.getParameter(livre.getTitre());
            notes.put(livre.getTitre(), param);

            // detection d'une note (valide ou non) saisie pour ce livre
            if (param != null && param.length()!=0) {
                int paramParse = 0;
                try {
                    paramParse = Integer.parseInt(param);
                    if (paramParse >= 9 || paramParse <= 0) {
                        req.setAttribute("alertmessage", "Une note doit être comprise entre 0 - 9");
                        req.setAttribute("alerttype", "alert-danger");
                        allOK = false;
                        break;
                    } else {
                        // La note pour ce livre est valide
                        // trop tôt pour update, on attends de voir les autres
                        notesOK.put(livre.getTitre(), paramParse);
                    }

                } catch (NumberFormatException exception) {
                    req.setAttribute("alertmessage", "Une note doit être comprise entre 0 - 9");
                    req.setAttribute("alerttype", "alert-danger");
                    allOK = false;
                    break;
                }
            }
        }

        // si au moins une note est saisie et qu'elles sont toutes valides -> update des livres
        if (allOK && notesOK.size() != 0) {
            req.setAttribute("alertmessage", "Les notes ont bien été prises en compte");
            req.setAttribute("alerttype", "alert-success");
            // on parcours quand même tous les livres de ce thème puisqu'on a la collection sous la main
            for (Livre livre : livres) {
                Integer note = notesOK.get(livre.getTitre());
                // uniquement les notes qui viennent d'être saisies
                if (note != null) {
                    livre.setNote(note.intValue());
                    livreDAO.updateNoteSelection(livre);
                }
            }

            // tous les livres de ce thème sont-ils évalués ?
            boolean allEvaluated = true;
            int noteMax = 0;
            for (Livre livre : livres) {
                if (livre.getNote()== 0) {
                    allEvaluated = false;
                    break;
                } else if (livre.getNote() > noteMax) {
                    // on profite de la boucle pour enregistrer la note max du thème
                    noteMax = livre.getNote();
                }
            }

            // on selectionne tous les livres ayant la note max du thème
            if (allEvaluated) {
                for (Livre livre : livres) {
                    if (livre.getNote()== noteMax) {
                        livre.setSelection(true);
                        livreDAO.updateNoteSelection(livre);
                    }
                }
            }
        }
        req.setAttribute("critique", critique);
        req.setAttribute("livres", livres);
        req.getRequestDispatcher("/WEB-INF/pages/notation.jsp").forward(req, resp);
    }
}
