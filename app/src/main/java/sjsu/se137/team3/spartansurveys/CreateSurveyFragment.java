package sjsu.se137.team3.spartansurveys;


import android.os.Bundle;
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

    private RadioButton mPublic = null;
    private RadioButton mPrivate = null;
    private TextView mAccessKeyTextView = null;
    private EditText mAccessKey = null;
    private Button mSendSurvey = null;

    public CreateSurveyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_create_survey, container, false);
        // Inflate the layout for this fragment
        mSendSurvey = (Button) layout.findViewById(R.id.create_survey_button);
        mSendSurvey.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Snackbar.make(view, "clicked create survey", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
            }});
           return layout;
        }
   /* @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }*/



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.create_survey_button:
                Toast.makeText(getActivity().getBaseContext(), "create a suuurvey!", Toast.LENGTH_SHORT).show();

                /** Do things you need to..
                 fragmentTwo = new FragmentTwo();

                 fragmentTransaction.replace(R.id.frameLayoutFragmentContainer, fragmentTwo);
                 fragmentTransaction.addToBackStack(null);

                 fragmentTransaction.commit();
                 */
                break;
        }
    }
}
