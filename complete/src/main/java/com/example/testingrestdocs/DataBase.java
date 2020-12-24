package com.example.testingrestdocs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//mport org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DataBase {
    public static void main(String[] args) {
        DataBase m = new DataBase();
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM user");
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
