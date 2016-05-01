package com.example.sheethal.spartansurveymaster_sm;

import java.util.UUID;
import java.util.Date;

/**
 * Created by SHEETHAL on 4/30/2016.
 */
public class Survey {
    private UUID mId;
    private String mTitle;
    private Date mdate;
    private boolean mSurveyStatus;//whether u took the survey or not.

    public Survey() {
        mId = UUID.randomUUID();
        mdate = new Date();
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

    public boolean ismSurveyStatus() {
        return mSurveyStatus;
    }

    public void setmSurveyStatus(boolean SurveyStatus) {
        mSurveyStatus = SurveyStatus;
    }
}
