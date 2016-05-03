package sjsu.se137.team3.spartansurveys;

/**
 * Created by Krystle on 5/3/2016.
 */
public class Account {
    private String mEmail;
    private String mPassword;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }
    //should NOT get and set password....maybe?

    public Account(String mEmail) {
        this.mEmail = mEmail;
    }
}
