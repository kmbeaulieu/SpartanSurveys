package com.example.sheethal.spartansurveymaster_sm;

/**
 * Created by SHEETHAL on 4/30/2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;

public class SurveyListFragment extends Fragment{
    private Survey mSurvey;
    private EditText mTitleField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSurvey = new Survey();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_surveylist, container,false);
        mTitleField =(EditText)v.findViewById(R.id.survey_list_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTitleField.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

}
