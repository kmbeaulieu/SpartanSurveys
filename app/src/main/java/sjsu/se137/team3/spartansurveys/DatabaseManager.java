package sjsu.se137.team3.spartansurveys;

/**
 * Created by smllt on 5/4/2016.
 */
import android.app.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by smllt on 5/4/2016.
 */
public class DatabaseManager{
    private static String URL = "jdbc:mysql://ec2-52-32-28-93.us-west-2.compute.amazonaws.com/spartansurvey";
    private static String password = "password137";
    private static String username = "user137";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    private int userID;
    private ResultSet resultSet = null;

    private void getConnection() {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes connection to database
     */
    private void diconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user to the database
     * @param email user's email
     * @param pass user's password
     * @return user's id
     */
    public void addUser(final String email, final String pass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                insertUser(email, pass);
                diconnect();
            }
        }).start();
    }

    private void insertUser(String email, String pass) {
        //ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO user (email, password) VALUES (?, ?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            preparedStatement.execute();
            preparedStatement.close();
//            PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT last_insert_id()");
//            resultSet = preparedStatement2.executeQuery();
//            if (resultSet.next()) {
//                userID = resultSet.getInt(1);
//            }
//            preparedStatement2.close();
        } catch (SQLException e) {
            System.out.println("UNABLE TO INSERT USER");
            e.printStackTrace();
        }
    }

    /**
     * Get a user's ID
     * @param email user email
     * @param pass user password
     * @return user's ID
     */
    public int getUser(final String email, final String pass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                selectUser(email, pass);
                diconnect();
            }
        }).start();
        return userID;
    }

    private void selectUser(String email, String pass) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM user WHERE email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userID = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Add a public survey to database
     * @param idOfUser
     * @param title
     * @param description
     * @param q1
     * @param q2
     * @param q3
     * @param q4
     * @param q5
     */
    public void addPublicSurvey(final int idOfUser, final String title, final String description, final int type, final String q1, final String q2, final String q3, final String q4, final String q5){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                insertPublicSurvey(idOfUser, title, description, type, q1, q2, q3, q4, q5);
                diconnect();
            }
        }).start();
    }

    private void insertPublicSurvey(int idOfUser, String title, String description, int type, String q1, String q2, String q3, String q4, String q5) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO survey (user_id, title, description, type, q1, q2, q3, q4, q5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, idOfUser);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, type);
            preparedStatement.setString(5, q1);
            preparedStatement.setString(6, q2);
            preparedStatement.setString(7, q3);
            preparedStatement.setString(8, q4);
            preparedStatement.setString(9, q5);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a private survey to the database
     * @param idOfUser
     * @param title
     * @param description
     * @param type (0 for private, 1 for
     * @param accessCode
     * @param q1
     * @param q2
     * @param q3
     * @param q4
     * @param q5
     */
    public void addPrivateSurvey(final int idOfUser, final String title, final String description, final int type, final String accessCode, final String q1, final String q2, final String q3, final String q4, final String q5){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                insertPrivateSurvey(idOfUser, title, description, type, accessCode, q1, q2, q3, q4, q5);
                diconnect();
            }
        }).start();
    }

    private void insertPrivateSurvey(int idOfUser, String title, String description, int type, String accessCode, String q1, String q2, String q3, String q4, String q5) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO survey (user_id, title, description, type, access_code, q1, q2, q3, q4, q5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, idOfUser);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, type);
            preparedStatement.setString(5, accessCode);
            preparedStatement.setString(6, q1);
            preparedStatement.setString(7, q2);
            preparedStatement.setString(8, q3);
            preparedStatement.setString(9, q4);
            preparedStatement.setString(10, q5);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a response to a survey to the database
     * @param idOfSurvey
     * @param r1
     * @param r2
     * @param r3
     * @param r4
     * @param r5
     */
    public void addResponse(final int idOfSurvey, final String r1, final String r2, final String r3, final String r4, final String r5){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                insertResponse(idOfSurvey, r1, r2, r3, r4, r5);
                diconnect();
            }
        }).start();
    }

    private void insertResponse(int idOfSurvey, String r1, String r2, String r3, String r4, String r5) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO response (survey_id, r1, r2, r3, r4, r5) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, idOfSurvey);
            preparedStatement.setString(2, r1);
            preparedStatement.setString(3, r2);
            preparedStatement.setString(4, r3);
            preparedStatement.setString(5, r4);
            preparedStatement.setString(6, r5);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all of a user's surveys
     * @param idOfUser
     * @return
     */
    public ResultSet getAllUserSurveys(final int idOfUser){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                allUserSurveys(idOfUser);
                diconnect();
            }
        }).start();
        return resultSet;
    }

    private void allUserSurveys(int idOfUser) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM survey WHERE user_id = ?");
            preparedStatement.setInt(1, idOfUser);
            resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get's all surveys
     * @return
     */
    public ResultSet getSurveys(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                selectSurveys();
                diconnect();
            }
        }).start();
        return resultSet;
    }

    private void selectSurveys() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM survey");
            resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a private survey
     * @param title
     * @param access_code
     * @return
     */
    public ResultSet getPrivateSurvey(final String title, final String access_code){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                selectPrivateSurvey(title, access_code);
                diconnect();
            }
        }).start();
        return resultSet;
    }

    private void selectPrivateSurvey(String title, String access_code) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM survey WHERE title = ? AND access_code = ?");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, access_code);
            resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a survey and all responses to that survey
     * @param idOfSurvey
     */
    public void deleteSurvey(final int idOfSurvey){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                deleteTargetSurvey(idOfSurvey);
                diconnect();
            }
        }).start();
    }

    private void deleteTargetSurvey(int idOfSurvey) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM survey WHERE id = ?");
            preparedStatement.setInt(1, idOfSurvey);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates a survey
     * @param idOfSurvey
     * @param title
     * @param description
     * @param type
     * @param q1
     * @param q2
     * @param q3
     * @param q4
     * @param q5
     */
    public void updateSurvey(final int idOfSurvey, final String title, final String description, final int type, final String q1, final String q2, final String q3, final String q4, final String q5){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                updatesSurvey(idOfSurvey, title, description, type, q1, q2, q3, q4, q5);
                diconnect();
            }
        }).start();
    }

    private void updatesSurvey(int idOfSurvey, String title, String description, int type, String q1, String q2, String q3, String q4, String q5) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE survey SET title = ?, description = ?, type = ?, q1 = ?, q2 = ?, q3 = ?, q4 = ?, q5 = ? WHERE id = ?");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, type);
            preparedStatement.setString(4, q1);
            preparedStatement.setString(5, q2);
            preparedStatement.setString(6, q3);
            preparedStatement.setString(7, q4);
            preparedStatement.setString(8, q5);
            preparedStatement.setInt(9, idOfSurvey);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

