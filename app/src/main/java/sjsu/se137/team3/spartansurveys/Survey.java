package sjsu.se137.team3.spartansurveys;

import android.widget.ArrayAdapter;

import java.util.List;
import java.util.UUID;
import java.util.Date;

/**
 * Created by SHEETHAL on 4/30/2016.
 */
public class Survey  {
    private Integer mId;
    private String mTitle,mDescription, mAccessCode, mq1, mq2, mq3, mq4, mq5 = "";
    private Integer mType;

    public Survey(Integer id, String title, Integer type, String description, String accessCode, String q1, String q2, String q3, String q4, String q5) {
        mId = id;
        mTitle = title;
        mDescription= description;
        mType = type;
        mAccessCode = accessCode;
        mq5= q5;
        mq4 = q4;
        mq3 = q3;
        mq2 = q2;
        mq1= q1;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmAccessCode() {
        return mAccessCode;
    }

    public void setmAccessCode(String mAccessCode) {
        this.mAccessCode = mAccessCode;
    }

    public String getMq1() {
        return mq1;
    }

    public void setMq1(String mq1) {
        this.mq1 = mq1;
    }

    public String getMq2() {
        return mq2;
    }

    public void setMq2(String mq2) {
        this.mq2 = mq2;
    }

    public String getMq3() {
        return mq3;
    }

    public void setMq3(String mq3) {
        this.mq3 = mq3;
    }

    public String getMq4() {
        return mq4;
    }

    public void setMq4(String mq4) {
        this.mq4 = mq4;
    }

    public String getMq5() {
        return mq5;
    }

    public void setMq5(String mq5) {
        this.mq5 = mq5;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }
}
