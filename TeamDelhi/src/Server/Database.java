package Server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Database class to manage interactions between a client and the database
 *
 * @author Team Delhi
 */
public class Database {

    // database URL
    private static final String db = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/delhi";
    
    // Database credentials
    private static final String USER = "delhi";
    private static final String PASS = "q6kbcggva7";
    
    // Login data
    private String username;
    private String password;
    private Connection connection;


    /**
     * @param username
     * @param password
     */
    Database(String username, String password) {
        this.username = username;

        try {

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {

                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

            }

            // Get complete hashed password in hex format
            this.password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(db, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        Database user1 = new Database("ishmael", "password");

        //user1.updateWinsAndLosses("test_user", "ishmael");

        user1.viewTopFive();

    }


    /**
     *
     */
    public void register() {
        try {
            // Insert query
            String loginQuery = "INSERT INTO login_information (username, password) VALUES (?,?) ;";
            String populateGameDataTable = "INSERT INTO usernames_gamedata VALUES(?,0,0) ;";

            PreparedStatement ps1 = connection.prepareStatement(loginQuery);
            ps1.setString(1, username);
            ps1.setString(2, password);
            ps1.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement(populateGameDataTable);
            ps2.setString(1, username);
            ps2.executeUpdate();

            connection.close();
        } catch (SQLException se) {
            // handle errors for JDBC
            se.printStackTrace();
        } catch (Exception a) // catch block
        {
            a.printStackTrace();
        }
    }


    /**
     * @return
     */
    public boolean checkUsername() {

        boolean check = false;

        try {

            String query = "SELECT username FROM login_information WHERE username = ?;";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getString(1).equals(username)) {
                    check = true;
                    System.out.println("User exists.");
                }
            } else {
                System.out.println("User does not exist.");
            }
        } catch (SQLException err) {
            System.out.println("ERROR: " + err);
        }

        return check;
    }


    /**
     * @return
     */
    public boolean checkPassword() {

        boolean login = false;

        try {
            String query = "SELECT * FROM login_information WHERE username = ? AND password = ?;";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
                    login = true;
                    System.out.println("Password correct.");
                } else {
                    System.out.println("Password incorrect.");
                }
            } else {
                System.out.println("Incorrect information.");
            }

        } catch (SQLException err) {
            System.out.println("ERROR: " + err);
        }

        return login;
    }


    /**
     * @throws SQLException
     */
    public void viewTopFive() throws SQLException {
        try {
            String query = "SELECT username, wins, losses FROM usernames_gamedata ORDER BY wins DESC LIMIT 5 ;";

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String usernameColumn = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                System.out.println(usernameColumn + "\t\t" + wins + "\t" + losses + "\t");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param winner
     * @param loser
     * @throws SQLException
     */
    public void updateWinsAndLosses(String winner) {

        String wins = "SELECT wins FROM usernames_gamedata WHERE username = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(wins);
            ps.setString(1, winner);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int winnerWins = rs.getInt("wins");

            String query1 = "UPDATE usernames_gamedata SET wins = " + (winnerWins + 1) + " WHERE username = ? ;";

            ps = connection.prepareStatement(query1);
            ps.setString(1, winner);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateLosses(String loser) {
    	String losses = "SELECT losses FROM usernames_gamedata WHERE username = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(losses);
            ps.setString(1, loser);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int loserLosses = rs.getInt("losses");

          
            String query2 = "UPDATE usernames_gamedata SET losses = " + (loserLosses + 1) + " WHERE username = ? ;";

            ps = connection.prepareStatement(query2);
            ps.setString(1, loser);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
