package sjsu.se137.team3.spartansurveys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Krystle on 4/25/2016.
 *
 */
public class SignUpActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic setup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //set up edit texts and buttons
        mEmail = (EditText) findViewById(R.id.emailSignUp);
        mPassword = (EditText) findViewById(R.id.passwordSignUp);
        mSubmit = (Button) findViewById(R.id.signUpSubmit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "clicked sign up button", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
                //START CHECK FOR INFO IN EDIT TEXT FIELDS THEN make a new intent to start a page
                /*if(mEmail != null && mPassword != null){
                    connect();
                    if(true//TABLE HAS  TODO insert code for connect working for has user
                            ){
                        Intent intent = new Intent(SignUpActivity.this, SurveyActivity.class);
                        SignUpActivity.this.startActivity(intent);
                        finish();
                    }
                }else{
                    Snackbar.make(view, "Please put in an email and password", Snackbar.LENGTH_SHORT)
                            .setAction("Action",null)
                            .show();
                }*/
            }


        });
        //setup toolbar
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);*/
    }

    public void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                insertNewUser(mEmail.getText().toString(), mPassword.getText().toString());
            }
        }).start();
    }

    protected void insertNewUser(String email, String password) {
        try {
            System.out.println("inserting user");
            //NEED SOME UNIQUE ID SOMEHOW.

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://ec2-52-10-61-82.us-west-2.compute.amazonaws.com/spartansurvey";
            Connection c = DriverManager.getConnection(url, "user137", "password137");
            PreparedStatement st = c.prepareStatement("INSERT INTO user (id, email, password) VALUES (?, ?, ?)");
            st.setInt(1, 120);
            st.setString(2, email);
            st.setString(4, password);
            st.execute();
            st.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


   /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu, menu);
        return true;
    }*/
    }
