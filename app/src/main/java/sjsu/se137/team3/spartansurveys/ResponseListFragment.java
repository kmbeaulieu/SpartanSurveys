package sjsu.se137.team3.spartansurveys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Krystle on 5/12/2016.
 */

public class ResponseListFragment extends Fragment {
    private RecyclerView responseRecyclerView;
    private ResponseAdapter surveyResponseAdapter;
    private Response r;
    private Survey s;
    private ArrayList<Survey> mSurveyList = new ArrayList<>();
    private ArrayList<Response> mResponseList = new ArrayList<>();

    //blank constructor
    public ResponseListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate view and make the recycler
        View layout = inflater.inflate(R.layout.fragment_response_list, container, false);
        responseRecyclerView = (RecyclerView) layout.findViewById(R.id.response_recycler_view);
        responseRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()) );
        Bundle bundle = getArguments();
        s = bundle.getParcelable("Survey");
        //get info from the database, fill in the response list to the adapter so the recycler view can be filled
        DatabaseManager dbm = new DatabaseManager();
        mResponseList = dbm.getResponsesToSurvey(s.getmId());

        //change the title!
        getActivity().setTitle("Survey Response List");

        surveyResponseAdapter = new ResponseAdapter(mResponseList);

        responseRecyclerView.setAdapter(surveyResponseAdapter);

        return layout;
    }



    private class ResponseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mq1,mr1,mq2,mr2,mq3,mr3,mq4,mr4,mq5,mr5;
        public ResponseHolder(View itemView) {
            super(itemView);

            // Initialize field views
            mq1 = (TextView) itemView.findViewById(R.id.my_survey_q1);
            mr1 = (TextView) itemView.findViewById(R.id.my_survey_response_q1);
            mq2 = (TextView) itemView.findViewById(R.id.my_survey_q2);
            mr2 = (TextView) itemView.findViewById(R.id.my_survey_response_q2);
            mq3 = (TextView) itemView.findViewById(R.id.my_survey_q3);
            mr3 = (TextView) itemView.findViewById(R.id.my_survey_response_q3);
            mq4 = (TextView) itemView.findViewById(R.id.my_survey_q4);
            mr4 = (TextView) itemView.findViewById(R.id.my_survey_response_q4);
            mq5 = (TextView) itemView.findViewById(R.id.my_survey_q5);
            mr5 = (TextView) itemView.findViewById(R.id.my_survey_response_q5);
        }

        // Bind response to the holder and set name accordingly
        public void bindResponse(Response response) {
            //pss the object to the main activity so the individual response can be pulled
            r = response;
            Bundle bundle = getArguments();

            //fill in the question of the survey and the response to it
            mq1.setText(s.getMq1());
            mr1.setText(r.getR1());
            mq2.setText(s.getMq2());
            mr2.setText(r.getR2());
            mq3.setText(s.getMq3());
            mr3.setText(r.getR3());
            mq4.setText(s.getMq4());
            mr4.setText(r.getR4());
            mq5.setText(s.getMq5());
            mr5.setText(r.getR5());


        }


        @Override
        public void onClick(View v) {
//            switch (v.getId()) {
//                /*case R.id.response_list_button:
//                    //what to put here
//                    Fragment frag = new ResponseFragment();
//                    FragmentManager fm = getFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("Response",s);
//                    frag.setArguments(bundle);
//                    ft.replace(R.id.fragment_container, frag, "Response Response");
//                    ft.commit();
//                    break;*/
//            }
        }
    }


    private class ResponseAdapter extends RecyclerView.Adapter<ResponseHolder> {
        private ArrayList<Response> mList;
        //fill in the list
        public ResponseAdapter(ArrayList<Response> list) {
            mList = list;
        }

        @Override
        public ResponseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create the LayoutInflater that is used to inflate the ResponseList
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            View view = layoutInflater.inflate(R.layout.response_list_item, parent, false);
            return new ResponseHolder(view);
        }

        @Override
        public void onBindViewHolder(ResponseHolder holder, int position) {
            // Get each response in list and bind to holder
            Response response = mList.get(position);
            holder.bindResponse(response);
        }

        @Override
        public int getItemCount() {
            return mResponseList.size();
        }
    }
    //keeps our menu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


}
