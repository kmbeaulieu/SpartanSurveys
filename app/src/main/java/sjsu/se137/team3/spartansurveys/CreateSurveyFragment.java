package sjsu.se137.team3.spartansurveys;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateSurveyFragment extends Fragment implements View.OnClickListener{

    private RadioButton mPublic, mPrivate = null;
    private EditText mAccessKey = null;
    private EditText mSurveyTitle = null;
    private EditText mQ1, mQ2, mQ3, mQ4, mQ5 = null;
    private Button mSendSurvey = null;

    public CreateSurveyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_create_survey, container, false);
        // Inflate the layout for this fragment
        instantiateUI(layout);
        //if it is a private survey, show the access key.
        mPrivate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                mAccessKey.setVisibility(View.VISIBLE);
            }});
        mPublic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mAccessKey.setVisibility(View.GONE);
            }});
        mSendSurvey.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Snackbar.make(view, "clicked create survey", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();

            }});
           return layout;
        }

    private void instantiateUI(View v){
        View layout = v;
        mSurveyTitle = (EditText) layout.findViewById(R.id.survey_title);
        mPublic = (RadioButton) layout.findViewById(R.id.public_radio_button);
        mPrivate = (RadioButton) layout.findViewById(R.id.private_radio_button);
        mQ1 = (EditText) layout.findViewById(R.id.create_question_1);
        mQ2 = (EditText) layout.findViewById(R.id.create_question_2);
        mQ3 = (EditText) layout.findViewById(R.id.create_question_3);
        mQ4 = (EditText) layout.findViewById(R.id.create_question_4);
        mQ5 = (EditText) layout.findViewById(R.id.create_question_5);
        mAccessKey = (EditText) layout.findViewById(R.id.create_access_code);
        mSendSurvey = (Button) layout.findViewById(R.id.create_survey_button);
    }

   /* @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }*/


//need this to use onclick in the onCreateView function.
    @Override
    public void onClick(View v) {

    }
}
