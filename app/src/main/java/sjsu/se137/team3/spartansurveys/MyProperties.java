package sjsu.se137.team3.spartansurveys;

/** Allows activities and fragments to call upon the current user's id
 * Created by Krystle on 5/5/2016.
 */

public class MyProperties {
    private static MyProperties mInstance= null;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int userId;

    protected MyProperties(){}

    public static synchronized MyProperties getInstance(){
        if(null == mInstance){
            mInstance = new MyProperties();
        }
        return mInstance;
    }

}