package sjsu.se137.team3.spartansurveys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Krystle on 5/11/2016.
 * The single response form for a survey
 */
public class ResponseFragment extends Fragment {
    private TextView mTitle, mDescription, mQ1, mQ2, mQ3, mQ4, mQ5;
    private EditText mQ1Response, mQ2Response, mQ3Response, mQ4Response, mQ5Response = null;
    private Button sendResponseButton = null;
    private Survey loadedSurvey;
    public ResponseFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_response, container, false);
        //grab the survey to populate this response form
//        loadedSurvey = ((MainActivity)getActivity()).getSurvey();
       //RETRIEVE DATA
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            loadedSurvey = bundle.getParcelable("survey");
        }

        //instantiates things like edit texts
        instantiateLayout(layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //set title
        getActivity().setTitle("Respond to Survey");
        //fills the text views with the title, desc, and questions of the survey so you can respond in the edit texts.
        fillSurveyTextViews();
        //listeners for things like button clicks
        setupListeners();
    }

    /**
     * instantiate all UI elements here
     *
     * @param v the fragment's view.
     */
    private void instantiateLayout(View v) {
        View layout = v;
        //instantiate text views and fill in from given survey
        mTitle = (TextView) layout.findViewById(R.id.response_survey_title);
        mDescription = (TextView) layout.findViewById(R.id.response_survey_description);
        mQ1 = (TextView) layout.findViewById(R.id.response_survey_q1);
        mQ2 = (TextView) layout.findViewById(R.id.response_survey_q2);
        mQ3 = (TextView) layout.findViewById(R.id.response_survey_q3);
        mQ4 = (TextView) layout.findViewById(R.id.response_survey_q4);
        mQ5 = (TextView) layout.findViewById(R.id.response_survey_q5);
        //instantiate button
        sendResponseButton = (Button) layout.findViewById(R.id.send_response_button);
        //instantiate edit texts
        mQ1Response = (EditText) layout.findViewById(R.id.response_q1);
        mQ2Response = (EditText) layout.findViewById(R.id.response_q2);
        mQ3Response = (EditText) layout.findViewById(R.id.response_q3);
        mQ4Response = (EditText) layout.findViewById(R.id.response_q4);
        mQ5Response = (EditText) layout.findViewById(R.id.response_q5);

    }

    /**
     * This fills the response page with prompts drawn from the survey you are responding to.
     */
    private void fillSurveyTextViews(){
        mTitle.setText(loadedSurvey.getmTitle());
        mDescription.setText(loadedSurvey.getmDescription());
        mQ1.setText(loadedSurvey.getMq1());
        mQ2.setText(loadedSurvey.getMq2());
        mQ3.setText(loadedSurvey.getMq3());
        mQ4.setText(loadedSurvey.getMq4());
        mQ5.setText(loadedSurvey.getMq5());
    }
    /**
     * setup the listeners for things like button or edit text
     * TODO set up warning to send without all fields filled
     * TODO send user to the main page when response is sent
     */
    private void setupListeners() {
        sendResponseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DatabaseManager dbm = new DatabaseManager();
                dbm.addResponse(loadedSurvey.getmId(),
                        mQ1Response.getText().toString(),
                        mQ2Response.getText().toString(),
                        mQ3Response.getText().toString(),
                        mQ4Response.getText().toString(),
                        mQ5Response.getText().toString() );

                //now move to home page aka public survey list
                Fragment frag = new SurveyListFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, frag, "Public Survey List");
                ft.commit();

            }});
    }


}
