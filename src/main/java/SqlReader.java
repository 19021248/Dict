import java.net.ConnectException;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

/**
 *
 * @author sqlitetutorial.net
 */
public class SqlReader {
    /**
     * Connect to a sample database
     */
    Connection connection = null;

    public void connect() {
        try {

            String url = "jdbc:sqlite:C:/sqlite/dict_hh.db";
            //Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void executeCommand(String command) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void statement(Dictionary d) {

        try {
            String url = "jdbc:sqlite:C:/sqlite/dict_hh.db";
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select word, description from  av order by word asc");
            while (rs.next()) {
                d.addWord(rs.getString("word"), rs.getString("description"));
                //   System.out.println(rs.getString("word") + "\t" +
                //rs.getString("description"));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public static void deleteDb(Dictionary d, String s) {
            String sql = "DELETE FROM av WHERE word = ?";

            try {
                String url = "jdbc:sqlite:C:/sqlite/dict_hh.db";
                  Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 {

                    // set the corresponding param
                    pstmt.setString(1, s);
                    // execute the delete statement
                    pstmt.executeUpdate();
                     d.removeWord(s);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            // delete in wordlist
           // d.removeWord(s);

    }
    public static void add(String wT, String wE,Dictionary d){
        String sql = " INSERT INTO av (word, description) VALUES ('"+wT+"','"+wE+"')";
        try {
            String url = "jdbc:sqlite:C:/sqlite/dict_hh.db";
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            d.addWord(wT,wE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        // add in wordlist


    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
}




