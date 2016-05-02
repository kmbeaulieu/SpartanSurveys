package sjsu.se137.team3.spartansurveys;

import java.util.UUID;
import java.util.Date;

/**
 * Created by SHEETHAL on 4/30/2016.
 */
public class Survey {
    private UUID mId;
    private String mTitle;
    private Date mdate;
    private boolean mTaken;//is the survey taken?

    public Survey() {
        mId = UUID.randomUUID();
        mdate = new Date();
        mTaken = false;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public Date getDate() {
        return mdate;
    }

    public void setDate(Date date) {
        mdate = date;
    }

    public boolean ismTaken() {
        return mTaken;
    }

    public void setmTaken(boolean taken) {
        mTaken = taken;
    }
}
