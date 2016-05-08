package sjsu.se137.team3.spartansurveys;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MySurveyFragment extends Fragment {

private TextView textVeiew = null;

    private ArrayList<Survey> sl;
    public MySurveyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_survey, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        textVeiew = (TextView) getActivity().findViewById(R.id.testgetsurvey);
       DatabaseManager dbm = new DatabaseManager();
        sl = dbm.getPublicSurveys();

        String str = sl.get(0).toMyString();
        textVeiew.setText(str);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

}
