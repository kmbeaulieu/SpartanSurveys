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


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{
    private RadioButton mPrivate = null;
    private RadioButton mPublic = null;
    private EditText mAccessCode = null;
    private Button searchSurveys = null;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        instantiateLayout(layout);
        //listeners for things like button clicks
        setupListeners();
        return layout;
    }

    /**
     * setup the listeners for things like button or radiobuttons
     */
    private void setupListeners() {

        mPrivate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Snackbar.make(view, "clicked private", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
                mAccessCode.setVisibility(View.VISIBLE);

            }});
        mPublic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar.make(view, "clicked public", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
                mAccessCode.setVisibility(View.GONE);

            }});
        searchSurveys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar.make(view, "clicked search", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();


            }});

    }

    /**
     * instantiate all UI elements here
     *
     * @param v the fragment's view.
     */
    private void instantiateLayout(View v) {
        View layout = v;
        mPrivate = (RadioButton) layout.findViewById(R.id.search_private_radio_button);
        mPublic = (RadioButton) layout.findViewById(R.id.search_public_radio_button);
        mAccessCode = (EditText) layout.findViewById(R.id.search_access_code);
        searchSurveys = (Button) layout.findViewById(R.id.search_surveys_button);
    }

    //this is needed to extend onclick listener so we can use it in the instantiation. Idk why we need this override. the switch statements don't work.
    @Override
    public void onClick(View view) {

    }


}
