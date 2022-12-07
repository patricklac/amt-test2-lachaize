package ch.heig.lachaize.amttest2.integration;

import ch.heig.lachaize.amttest2.model.Livre;
import ch.heig.lachaize.amttest2.model.Theme;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LivreDAO {

    @Resource(lookup = "jdbc/AMTtest2DS")
    private DataSource dataSource;

    public List<Livre> getLivres() {
        List<Livre> livres = new ArrayList<>();
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT" +
                            " livres.codLiv," +
                            " livres.titre," +
                            "    livres.note," +
                            "    livres.selection," +
                            "    themes.codTh," +
                            "    themes.nom " +
                            "   FROM " +
                            "   livres " +
                            "   LEFT JOIN " +
                            "   themes ON themes.codTh = livres.codTh " +
                            "   ORDER BY " +
                            "   themes.codTh"
            );){
            ResultSet rs = statement.executeQuery();
            Livre livre = null;
            Theme theme = null;
            while(rs.next()) {
                int codLiv = rs.getInt(1);
                String titre = rs.getString(2);
                int note = rs.getInt(3);
                boolean selection = rs.getBoolean(4);
                String codTh = rs.getString(5);
                String nomTh = rs.getString(6);
                livre = new Livre(codLiv,titre,note,selection);
                theme = new Theme(codTh,nomTh);
                livre.setTheme(theme);
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }


    public List<Livre> getLivresFromTheme(Theme theme) {
        List<Livre> livres = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT" +
                            " livres.codLiv," +
                            " livres.titre," +
                            "    livres.note," +
                            "    livres.selection " +
                            "   FROM " +
                            "   livres " +
                            "   WHERE codTh LIKE ? "
            );
            statement.setString(1,theme.getCodTh());
            ResultSet rs = statement.executeQuery();
            Livre livre = null;
            while(rs.next()) {
                int codLiv = rs.getInt(1);
                String titre = rs.getString(2);
                int note = rs.getInt(3);
                boolean selection = rs.getBoolean(4);
                livre = new Livre(codLiv,titre,note,selection);
                livre.setTheme(theme);
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    public void updateNoteSelection(Livre livre) {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `livres` SET `note` = ? , `selection` = ? WHERE `livres`.`titre` = ?;"
            );){
            statement.setInt(1,livre.getNote());
            statement.setBoolean(2,livre.isSelection());
            statement.setString(3,livre.getTitre());
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(LivreDAO.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}