package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    public static void main(String[] args) {
        Database m = new Database();
        m.testDatabase();
    }
    private void testDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/social_network";
            String login = "postgres";
            String password = "metyab20";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM post");
                while (rs.next()) {
                    String str = rs.getString("id") + ":" + rs.getString(2);
                    System.out.println("post:" + str);
                }
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
