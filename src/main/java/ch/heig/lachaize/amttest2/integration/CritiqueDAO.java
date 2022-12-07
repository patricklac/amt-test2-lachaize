package ch.heig.lachaize.amttest2.integration;
import ch.heig.lachaize.amttest2.model.Critique;
import ch.heig.lachaize.amttest2.model.Theme;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class CritiqueDAO {

    @Resource(lookup = "jdbc/AMTtest2DS")
    private DataSource dataSource;

    public Critique getCritique(String nom, String prenom) {
        Critique critique = null;
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT  " +
                            " critiques.codcri,  " +
                            "    critiques.prenom,  " +
                            "    critiques.nom,  " +
                            "    critiques.codTh, " +
                            "    themes.nom " +
                            "FROM " +
                            " critiques " +
                            "LEFT JOIN  " +
                            " themes ON critiques.codTh = themes.codTh " +
                            "WHERE " +
                            " critiques.nom = ? " +
                            "    AND critiques.prenom =?"
            );){
            statement.setString(1,nom);
            statement.setString(2,prenom);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int codCri = rs.getInt(1);
                String codTh = rs.getString(4);
                String thNom = rs.getString(5);
                critique = new Critique(codCri,nom,prenom);
                Theme theme = new Theme(codTh,thNom);
                critique.setTheme(theme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return critique;
    }
}