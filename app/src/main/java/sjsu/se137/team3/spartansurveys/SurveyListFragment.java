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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
* Created by Krystle on 5/5/2016.
*/


public class SurveyListFragment extends Fragment {
    private RecyclerView surveyRecyclerView;
    private SurveyAdapter surveyAdapter;
    private ArrayList<Survey> mSurveyList = new ArrayList<>();

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

//convert BEFORE adapter
        getSurveys();

        surveyRecyclerView.setAdapter(surveyAdapter);

        return view;
    }

    private void getSurveys() {
//WHY ARE YOU NULL?! // TODO: fix this null plz
        DatabaseManager dbm = new DatabaseManager();

        surveyAdapter = new SurveyAdapter(dbm.getPublicSurveys());


    }

    private class SurveyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button surveyNameButton;

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


        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            //pass in survey info to the next place
            /*intent.putExtra(MainActivity.SURVEY, surveyNameButton.getText().toString());*/

            startActivity(intent);
        }
    }


    private class SurveyAdapter extends RecyclerView.Adapter<SurveyHolder> {

        private ArrayList<Survey> mList;

        //TODO Check if this is right
        public SurveyAdapter(ArrayList<Survey> list) {
            mList = list;
        }

        @Override
        public SurveyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create the LayoutInflater that is used to inflate the SurveyList
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            // Inflates each individual SurveyHolder (ViewHolder) using
            //  the generic xml template list_item_survey.xml
            View view = layoutInflater.inflate(R.layout.survey_list_item, parent, false);

            return new SurveyHolder(view);
        }

        @Override
        public void onBindViewHolder(SurveyHolder holder, int position) {
         // Get each survey in list and bind to holder
            Survey surveyObj = mList.get(position);
            holder.bindSurvey(surveyObj.getmTitle());
        }

        @Override
        public int getItemCount() {
            return mSurveyList.size();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}



