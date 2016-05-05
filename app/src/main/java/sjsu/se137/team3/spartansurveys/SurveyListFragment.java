/*
package sjsu.se137.team3.spartansurveys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

*/
/**
 * Created by Krystle on 5/5/2016.
 *//*

public class SurveyListFragment extends Fragment {
    private RecyclerView surveyRecyclerView;
    private SurveyAdapter surveyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate view
        View view = inflater.inflate(R.layout.fragment_survey_list, container, false);

        // Initialize surveyRecyclerView
        surveyRecyclerView = (RecyclerView) view.findViewById(R.id.survey_recycler_view);

        // IMPORTANT:
        // RecyclerView requires a LayoutManager to work. IT WILL CRASH IF YOU FORGET ONE.
        surveyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Array objects;
        try {
            objects = rs.getArray(1);
            surveyAdapter = new SurveyAdapter(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }

        surveyRecyclerView.setAdapter(surveyAdapter);

        return view;
    }

    private class SurveyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button surveyNameButton;
        public void getResults() {
            DatabaseManager dbm = new DatabaseManager();
            ResultSet rs = dbm.getSurveys();
            try {
                while (rs.next()) {
                    int surveyId = rs.getInt(1);
                    String surveyName = rs.getString(2);
                    String description = rs.getString(3);
                    int type = rs.getInt(4);
                    String accessCode = rs.getString(5);
                    String q1 = rs.getString(6);
                    String q2 = rs.getString(7);
                    String q3 = rs.getString(8);
                    String q4 = rs.getString(9);
                    String q5 = rs.getString(10);{


                        // ... do something with these variables ...
                        bindSurvey(surveyName);
                }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
            public SurveyHolder(View itemView) {
            super(itemView);

            // Initialize name of survey inside SurveyHolder
            surveyNameButton = (Button) itemView.findViewById(R.id.list_survey_name_button);

            surveyNameButton.setOnClickListener(this);
        }

        // Bind survey to the holder and set name accordingly
        public void bindSurvey(String s) {
            surveyNameButton.setText(s);
        }

        // Initializes TakeSurveyActivity when user selects a survey to take
        @Override
        public void onClick(View v) {
            Intent intent = customGetIntent();
            intent.putExtra(MainActivity.SURVEY_ID, surveyNameButton.getText().toString());
            startActivity(intent);
        }
    }

    */
/**
     * Checks for whether activitySwitch is 0 or 1 to determine whether
     * to return Intent with TakeSurveyActivity or SurveyResultActivity
     * @return Intent that varies depending on activitySwitch's value.
     *//*

    private Intent customGetIntent() {

        if (GlobalVariable.result == 0) {
            return new Intent(getActivity(), TakeSurveyActivity.class);
        } else {
            return new Intent(getActivity(), SurveyResultActivity.class);
        }
    }

    private class SurveyAdapter extends RecyclerView.Adapter<SurveyHolder> {

        private Array surveyObjects;

        public SurveyAdapter(Array sObjects) {
            surveyObjects = sObjects;
        }

        @Override
        public SurveyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create the LayoutInflater that is used to inflate the CrimeHolder (ViewHolder)
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            // Inflates each individual SurveyHolder (ViewHolder) using
            //  the generic xml template list_item_survey.xml
            View view = layoutInflater.inflate(R.layout.survey_list_item, parent, false);

            return new SurveyHolder(view);
        }

        @Override
        public void onBindViewHolder(SurveyHolder holder, int position) {
            Object obj = surveyObjects.;
            String surveyName = parseObject.getString("surveyName");
            Survey survey = new Survey(surveyName);
            holder.bindSurvey(survey);
        }

        @Override
        public int getItemCount() {
            return surveyObjects.size();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}
}
*/
