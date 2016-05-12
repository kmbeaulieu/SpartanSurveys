package sjsu.se137.team3.spartansurveys;

/**
 * Created by smllt on 5/4/2016.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private boolean userExists;
    private ArrayList<Survey> msurveylist = null;
    private Survey mPrivateSurvey = null;

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
    private void disconnect(){
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
       Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                try {
                    insertUser(email, pass);
                } catch(Exception e){

                }

                disconnect();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insertUser(String email, String pass) {
        //ResultSet resultSet = null;
        try {
            String query = "INSERT INTO user (email, password) VALUES ("+email+", SHA("+pass+"))";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            //preparedStatement.setString(1, email);
            //preparedStatement.setString(2, pass);
            preparedStatement.execute();
            preparedStatement.close();
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
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                try {
                    selectUser(email, pass);
                } catch(Exception e){

                }

                disconnect();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userID;
    }

    private void selectUser(String email, String pass) {
        try {
            String query = "SELECT id FROM user WHERE email = "+email+" AND password = SHA("+pass+")";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            //preparedStatement.setString(1, email);
            //preparedStatement.setString(2, pass);
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
     * Checks for a users existence
     * @param email a users email
     * @return true if email already in database, false if email is not in the database.
     */
    public boolean checkUserExistence(final String email){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                try {
                    checkForUser(email);
                } catch(Exception e){
                    e.printStackTrace();
                }
                disconnect();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    private void checkForUser(String email) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userExists = true;
            } else {
                userExists = false;
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
                disconnect();
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
                disconnect();
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
       Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                insertResponse(idOfSurvey, r1, r2, r3, r4, r5);
                disconnect();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public ArrayList<Survey> getAllUserSurveys(final int idOfUser){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                allUserSurveys(idOfUser);
                disconnect();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msurveylist;
    }

    private void allUserSurveys(int idOfUser) {
        msurveylist = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM survey WHERE user_id = ?");
            preparedStatement.setInt(1, idOfUser);
            resultSet = preparedStatement.executeQuery();
            generateMSurveyList();
//            msurveylist = new ArrayList<>();
//            while(resultSet.next()){
//                Integer surveyId = resultSet.getInt(1);
//                Integer userId = resultSet.getInt(2);
//                String surveyName = resultSet.getString(3);
//                String description = resultSet.getString(4);
//                Integer type = resultSet.getInt(5);
//                String accessCode = resultSet.getString(6);
//                String q1 = resultSet.getString(7);
//                String q2 = resultSet.getString(8);
//                String q3 = resultSet.getString(9);
//                String q4 = resultSet.getString(10);
//                String q5 = resultSet.getString(11);
//                Survey s = new Survey(surveyId,userId,surveyName,type,description,accessCode,q1,q2,q3,q4,q5);
//                msurveylist.add(s);
//            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all public surveys
     * @return
     */
    public ArrayList<Survey> getPublicSurveys(){

        Thread j = new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                selectSurveys();
                disconnect();
            }
        });
                j.start();
        try {
            j.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return msurveylist;
    }

    private void selectSurveys() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM survey WHERE type = 1");
            resultSet = preparedStatement.executeQuery();
            msurveylist = new ArrayList<>();
                while (resultSet.next()) {
                    Integer surveyId = resultSet.getInt(1);
                    Integer userId = resultSet.getInt(2);
                    String surveyName = resultSet.getString(3);
                    String description = resultSet.getString(4);
                    Integer type = resultSet.getInt(5);
                    String accessCode = resultSet.getString(6);
                    String q1 = resultSet.getString(7);
                    String q2 = resultSet.getString(8);
                    String q3 = resultSet.getString(9);
                    String q4 = resultSet.getString(10);
                    String q5 = resultSet.getString(11);
                    Survey s = new Survey(surveyId,userId,surveyName,type,description,accessCode,q1,q2,q3,q4,q5);
                    msurveylist.add(s);
                }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateMSurveyList(){
        msurveylist = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Integer surveyId = resultSet.getInt(1);
                Integer userId = resultSet.getInt(2);
                String surveyName = resultSet.getString(3);
                String description = resultSet.getString(4);
                Integer type = resultSet.getInt(5);
                String accessCode = resultSet.getString(6);
                String q1 = resultSet.getString(7);
                String q2 = resultSet.getString(8);
                String q3 = resultSet.getString(9);
                String q4 = resultSet.getString(10);
                String q5 = resultSet.getString(11);
                Survey s = new Survey(surveyId,userId,surveyName,type,description,accessCode,q1,q2,q3,q4,q5);
                msurveylist.add(s);
            }
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
    public Survey getPrivateSurvey(final String title, final String access_code){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                selectPrivateSurvey(title, access_code);
                disconnect();
            }
        }).start();
        return mPrivateSurvey;
    }

    private void selectPrivateSurvey(String title, String access_code) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM survey WHERE title = ? AND access_code = ?");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, access_code);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Integer surveyId = resultSet.getInt(1);
                Integer userId = resultSet.getInt(2);
                String surveyName = resultSet.getString(3);
                String description = resultSet.getString(4);
                Integer type = resultSet.getInt(5);
                String accessCode = resultSet.getString(6);
                String q1 = resultSet.getString(7);
                String q2 = resultSet.getString(8);
                String q3 = resultSet.getString(9);
                String q4 = resultSet.getString(10);
                String q5 = resultSet.getString(11);
                mPrivateSurvey = new Survey(surveyId,userId,surveyName,type,description,accessCode,q1,q2,q3,q4,q5);
            }
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
                disconnect();
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
                disconnect();
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

    public ArrayList<Survey> getSurveysByKeyword(final String keyword){
        Thread k = new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
                selectSurveysLike(keyword);
                disconnect();
            }
        });
        k.start();
        try {
            k.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msurveylist;
    }

    private void selectSurveysLike(String keyword) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM survey WHERE description rlike '[[:<:]] ? [[:>:]]' AND type = 1");
            preparedStatement.setString(1, keyword);
            resultSet = preparedStatement.executeQuery();
            msurveylist = new ArrayList<>();
            while (resultSet.next()) {
                Integer surveyId = resultSet.getInt(1);
                Integer userId = resultSet.getInt(2);
                String surveyName = resultSet.getString(3);
                String description = resultSet.getString(4);
                Integer type = resultSet.getInt(5);
                String accessCode = resultSet.getString(6);
                String q1 = resultSet.getString(7);
                String q2 = resultSet.getString(8);
                String q3 = resultSet.getString(9);
                String q4 = resultSet.getString(10);
                String q5 = resultSet.getString(11);
                Survey s = new Survey(surveyId,userId,surveyName,type,description,accessCode,q1,q2,q3,q4,q5);
                msurveylist.add(s);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

