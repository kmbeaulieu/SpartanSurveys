package sjsu.se137.team3.spartansurveys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Krystle on 4/25/2016.
 * This is the page where you click sign up or sign in.
 */
public class WelcomeActivity extends AppCompatActivity {
    //instantiate widgets on the page
    private Button mSignUp;
    private Button mSignIn;
    private Button mTesting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic setup of page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //connect();
        //now do button screen changes
        mSignIn = (Button) findViewById(R.id.signIn);
        mSignUp = (Button) findViewById(R.id.signUp);
        mTesting = (Button) findViewById(R.id.insert);

        //set onclicklistener for each button
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make a new intent to start a page
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                //start the page
                WelcomeActivity.this.startActivity(intent);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make a new intent to start a page
                Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                //start the page
                WelcomeActivity.this.startActivity(intent);
                //Dont put finish here so that the people can press back and choose another option
            }
        });
        mTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make a new intent to start a page
                Intent intent = new Intent(WelcomeActivity.this, SurveyActivity.class);
                //start the page
                WelcomeActivity.this.startActivity(intent);
                //Dont put finish here so that the people can press back and choose another option
            }
        });

    }

    public void connect(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                insert();
            }
        }).start();
    }

    protected void insert(){
        try{
            System.out.println("inserting user");
//please fix these values to what you want to do
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://ec2-52-10-61-82.us-west-2.compute.amazonaws.com/spartansurvey";
            Connection c = DriverManager.getConnection(url,"user137","password137");
            PreparedStatement st = c.prepareStatement("INSERT INTO user (id, name, email, password) VALUES (?, ?, ?, ?)");
            st.setInt(1,100);
            st.setString(2,"krystle");
            st.setString(3,"A001");
            st.setString(4,"pass");
            st.execute();
            st.close();
            c.close();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }


}
