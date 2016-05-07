package sjsu.se137.team3.spartansurveys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//This is just the splash screen will forward you to the welcome activity.
// It is based off of Big Nerd Ranch's guide on following Google's recommended guidelines.
//This is the link: www.bignerdranch.com/blogs/splash-screens-the-right-way
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();//finish is here so that you cannot go back into the splash screen.
    }
}