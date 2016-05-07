package sjsu.se137.team3.spartansurveys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final Survey SURVEY = null;
    NavigationView navigationView = null;
    Toolbar toolbar = null;
     /*RadioButton mPublic = null;
     RadioButton mPrivate = null;
     TextView mAccessKeyTextView = null;
     EditText mAccessKey = null;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Integer userid = MyProperties.getInstance().userId;

     /*   // Set the view to be that of the layout file: fragment_survey_template.xml
        setContentView(R.layout.fragment_create_survey);*/

        // Get support Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // Create fragment and set it to fragment located in fragment_survey_template.xml
        Fragment fragment = fm.findFragmentById(R.id.survey_recycler_view);


        if (fragment == null) {
            fragment = new CreateSurveyFragment();
            fm.beginTransaction().add(R.id.survey_recycler_view, fragment).commit();
        }/*else{
           *//* CustomAdapterOptimized mAdapter = new CustomAdapterOptimized(mContext, R.layout.example_item, mExampleList);
            mListView.setAdapter(mAdapter);*//*
        }*/
        /* TRANSITION
        SearchFragment fragment = new SearchFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();*/

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_logout){
            Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
            MyProperties.getInstance().setUserId(0);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mySurveys) {

            //handle the my surveys action
            MySurveyFragment fragment = new MySurveyFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_search) {
            //handle the search action
            SearchFragment fragment = new SearchFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_createSurvey){
            //handle the create survey action
            CreateSurveyFragment fragment = new CreateSurveyFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_public_surveys){
            //handle the create survey action TODO see if the recycler view is correct
            SurveyListFragment fragment = new SurveyListFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.survey_recycler_view, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void findSurveys(){
       /* ResultSet rs = dbm.find
        List<Survey> mainScreenList = new LinkedList<Survey>();

        while (resSet.next()) {
            Example mExample = new Example ();
            mExample.ID = resSet.getInt("ExampleID");
            mExample.name = resSet.getString("ExampleName");
            [..]
            mExampleList.add(mExample);
        }*/
    }

}
