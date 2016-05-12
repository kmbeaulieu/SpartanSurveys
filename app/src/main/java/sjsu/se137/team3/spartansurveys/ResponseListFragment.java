/*
package sjsu.se137.team3.spartansurveys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

*/
/**
 * Created by Krystle on 5/12/2016.
 *//*

public class ResponseListFragment extends Fragment{
    private RecyclerView surveyRecyclerView;
    private SurveyAdapter surveyResponseAdapter;
    private Survey s;
    private ArrayList<Survey> mSurveyList = new ArrayList<>();
    private ArrayList<Response> mResponseList = new ArrayList<>();
    //blank constructor
    public ResponseListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate view and make the recycler
        View layout = inflater.inflate(R.layout.fragment_response_list, container, false);
        surveyRecyclerView = (RecyclerView) layout.findViewById(R.id.response_recycler_view);
        surveyRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()) );
        //get info from the database, fill in the survey list to the adapter so the recycler view can be filled
        DatabaseManager dbm = new DatabaseManager();
//        int userid = MyProperties.getInstance().userId;
        //get all responses for a survey
//        mResponseList = dbm.getAllUserSurveys(userid);

        //set title of action bar
        getActivity().setTitle("Responses");
        surveyResponseAdapter = new SurveyAdapter(mResponseList);

        surveyRecyclerView.setAdapter(surveyResponseAdapter);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private class SurveyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button surveyNameButton;

        public SurveyHolder(View itemView) {
            super(itemView);

            // Initialize name of survey inside SurveyHolder

            surveyNameButton = (Button) itemView.findViewById(R.id.survey_list_button);
            surveyNameButton.setOnClickListener(this);
        }

        // Bind survey to the holder and set name accordingly
        public void bindSurvey(Survey survey) {
            //pss the object to the main activity so the individual survey can be pulled
            s = survey;
            //this is the title of the survey in the button. click it and a response opens.
            surveyNameButton.setText(s.getmTitle());
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.survey_list_button:
                    //what to put here
                    Fragment frag = new ResponseListFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Survey",s);
                    frag.setArguments(bundle);
                    ft.replace(R.id.fragment_container, frag, "Survey Response");
                    ft.commit();
                    break;
            }
           */
/* Intent intent = new Intent();
            //pass in survey info to the next place
            intent.putExtra(MainActivity.SURVEYID,surveyNameButton.getText().toString());
            startActivity(intent);*//*

        }
    }


    private class SurveyAdapter extends RecyclerView.Adapter<SurveyHolder> {
        private ArrayList<Survey> mList;
        //fill in the list
        public SurveyAdapter(ArrayList<Survey> list) {
            mList = list;
        }

        @Override
        public SurveyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create the LayoutInflater that is used to inflate the SurveyList
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            View view = layoutInflater.inflate(R.layout.survey_list_item, parent, false);
            return new SurveyHolder(view);
        }

        @Override
        public void onBindViewHolder(SurveyHolder holder, int position) {
            // Get each survey in list and bind to holder
            Survey survey = mList.get(position);
            holder.bindSurvey(survey);
        }

        @Override
        public int getItemCount() {
            return mSurveyList.size();
        }
    }
    //keeps our menu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}
*/
