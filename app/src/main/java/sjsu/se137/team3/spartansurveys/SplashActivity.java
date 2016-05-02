package sjsu.se137.team3.spartansurveys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//This is just the splash screen will forward you to the welcome activity
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();//finish is here so that you cannot go back into the splash screen.
    }
}