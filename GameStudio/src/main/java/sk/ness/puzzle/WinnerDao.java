package sk.ness.puzzle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WinnerDao {

    public static final String DRIVER_CLASS = "org.apache.derby.jdbc.ClientDriver";
    public static final String URL = "jdbc:derby://localhost:1527/puzzle;create=true";
    public static final String USER = "AS";
    public static final String PASSWORD = "AS";

    public WinnerDao() throws ClassNotFoundException {
        Class.forName(DRIVER_CLASS);
    }

    public void insert(Winner winner){
        try {
            insert_internal(winner);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }


    private void insert_internal(Winner winner) throws SQLException {
        // PreparedStatement stmt = con.prepareStatement(QUERY);
        String QUERY = "INSERT INTO WINNERS (id, name, seconds) VALUES (?, ?, ?)";
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(QUERY);
        stmt.setInt(1, getMaxId());
        stmt.setString(2, winner.getName());
        stmt.setDouble(3, winner.getSec());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    private int getMaxId() throws SQLException {
        String QUERY = "SELECT max(id)+1 FROM WINNERS";
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        int maxId = 1;
        if (rs.next()) {
            maxId = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        con.close();
        return maxId;
    }

    public ArrayList<Winner> getWinners() throws ClassNotFoundException, SQLException {
        String QUERY = "SELECT id,name,seconds FROM WINNERS order by seconds";
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        ArrayList<Winner> winners = new ArrayList<Winner>();
        while (rs.next()) {
            winners.add(new Winner(rs.getInt(1), rs.getString(2), rs.getLong(3)));
        }
        rs.close();
        stmt.close();
        con.close();
        return winners;
    }

    public void createTable() throws SQLException {
        String QUERY = "CREATE TABLE WINNERS (ID INT PRIMARY KEY, NAME VARCHAR(120) NOT NULL, SECONDS DOUBLE NOT NULL)";
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(QUERY);
        stmt.executeUpdate();
        stmt.close();
        con.close();

    }

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        new WinnerDao().createTable();

        /*ArrayList<Winner> winners = new WinnerDao().getWinners();
        for (Winner winner : winners) {
            System.out.println(winner);
        }*/
    }


}
