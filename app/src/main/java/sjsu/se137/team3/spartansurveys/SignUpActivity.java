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

    /*TODO 1) check if the email has @ sign. This is our "validation".
    * TODO 2) hash the passwords and send that into the password user INSERT. Just the basic java hash
     * TODO 3) connect to the db and insert the information
     * TODO 4) if it inserts, then send the user to the login page OR to the MainActivity. You can store the email locally in an Account if you want.
     * These are what I thiiiink needs to be done. Feel free to ask me(Krystle) or Sam for help on what needs to be done.
     * */



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

    }

    }
