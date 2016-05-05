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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Krystle on 4/25/2016.
 *
 */
public class SignUpActivity extends AppCompatActivity {
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mSubmit;
    private View mProgressView;
    private View mSignUpFormView;
    private Integer user = 0;
    private UserLoginTask mAuthTask = null;
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
        mEmailEditText = (EditText) findViewById(R.id.emailSignUp);
        mPasswordEditText = (EditText) findViewById(R.id.passwordSignUp);
        mSubmit = (Button) findViewById(R.id.signUpSubmit);


        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "clicked sign up button", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
                attemptSignUp();
                //START CHECK FOR INFO IN EDIT TEXT FIELDS THEN make a new intent to start a page
                /*if(mEmailEditText != null && mPasswordEditText != null){
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
        mSignUpFormView = findViewById(R.id.sign_up_form);
        mProgressView = findViewById(R.id.signup_progress);
    }
    private void attemptSignUp() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailEditText.setError(null);
        mPasswordEditText.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if ( !isPasswordValid(password)) {
            mPasswordEditText.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }else if(TextUtils.isEmpty(password)){
            mPasswordEditText.setError(getString(R.string.error_field_required));
            focusView = mPasswordEditText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailEditText.setError(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailEditText.setError(getString(R.string.error_invalid_email));
            focusView = mEmailEditText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }
    private boolean isEmailValid(String email) {
        //we will just check with this
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignUpFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: make passwords a hash!

            DatabaseManager dbm = new DatabaseManager();

            dbm.addUser(mEmail,mPassword);

            MyProperties.getInstance().setUserId(dbm.getUser(mEmail,mPassword));

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                String usr = Integer.toString( MyProperties.getInstance().userId);

                Snackbar.make(findViewById(R.id.sign_up_form), "Thank you for creating an account! Enjoy!" + usr, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
                Intent i = new Intent(SignUpActivity.this, MainActivity.class);

                startActivity(i);
                finish();
            } else {
                mSubmit.setError("There was an error creating user");
                mSubmit.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }

    }
}
