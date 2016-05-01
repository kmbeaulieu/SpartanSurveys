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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic setup of page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //now do button screen changes
        mSignIn = (Button) findViewById(R.id.signIn);
        mSignUp = (Button) findViewById(R.id.signUp);

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
            }
        });
    }

    public void connect(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                insert();
            }
        }).start();
    }

    protected void insert(){
        try{
            //please fix these values to what you want to do
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.250.98.21/school";
            Connection c = DriverManager.getConnection(url,"krystle","123456");
            PreparedStatement st = c.prepareStatement("insert into student value (?,?,?)");
            st.setString(1,"A001");
            st.setString(2,"krystle");
            st.setInt(3,90);
            st.execute();
            st.close();
            c.close();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }


}
