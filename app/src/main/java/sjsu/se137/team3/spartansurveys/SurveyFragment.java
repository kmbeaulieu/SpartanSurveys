package sjsu.se137.team3.spartansurveys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by Krystle on 5/1/2016.
 * TODO fix Hello world bar at the top. I have no idea where that is coming from.
 * TODO make each question be a page? so you click next as you make a new quiz?
 */
public class SurveyFragment extends Fragment {
    private Survey mSurvey;
    private EditText mTitleField;
    private CheckBox mTakenCheckBox;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mSurvey = new Survey();
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_survey, container, false);

        mTitleField = (EditText) v.findViewById(R.id.survey_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSurvey.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //intentionally left blank
            }
        });

        mTakenCheckBox = (CheckBox) v.findViewById(R.id.survey_taken);
        mTakenCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mSurvey.setmTaken(b);
            }
        });
        return v;
    }
}
