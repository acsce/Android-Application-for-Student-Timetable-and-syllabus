package com.example.internshipapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.internshipapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupUIViews();
        initToolbar();

        setupListView();
    }

    private void setupUIViews(){
        toolbar = findViewById(R.id.ToolbarMain);
        listView = findViewById(R.id.lvMain);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Internship App");
    }

    private void setupListView() {

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        Intent intent = new Intent(HomeActivity.this, WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(HomeActivity.this, SubjectActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(HomeActivity.this, FacultyActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://results.vtu.ac.in"));
                        startActivity(browserIntent);
                        break;
                    }
                    case 4: {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.acsce.edu.in"));
                        startActivity(browserIntent);
                        break;
                    }
                    case 5: {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nptel.ac.in"));
                        startActivity(browserIntent);
                        break;
                    }
                    case 6: {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swayam.gov.in"));
                        startActivity(browserIntent);
                        break;
                    }
                }
            }
        });

    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;


        public SimpleAdapter(Context context, String[] title, String[] description){
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item, null);
            }

            title = convertView.findViewById(R.id.tvMain);
            description = convertView.findViewById(R.id.tvDescription);
            imageView = convertView.findViewById(R.id.ivMain);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            if(titleArray[position].equalsIgnoreCase("Timetable")){
                imageView.setImageResource(R.drawable.logo_timetable);
            }else if(titleArray[position].equalsIgnoreCase("Subjects")){
                imageView.setImageResource(R.drawable.logo_subjects);
            }else if(titleArray[position].equalsIgnoreCase("Faculty")) {
                imageView.setImageResource(R.drawable.logo_faculty);
            }else if(titleArray[position].equalsIgnoreCase("VTU Results")){
                imageView.setImageResource(R.drawable.logo_vtu_1);
            }else if(titleArray[position].equalsIgnoreCase("ACS College")){
                imageView.setImageResource(R.drawable.logo_acsce);
            }else if(titleArray[position].equalsIgnoreCase("NPTEL")){
                imageView.setImageResource(R.drawable.logo_nptel);
            }else {
                imageView.setImageResource(R.drawable.logo_swyam);
            }
            return convertView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.id_profile){
            Intent intentprofile = new Intent(HomeActivity.this,Profile.class);
            startActivity(intentprofile);
            return true;
        }

        if(id == R.id.id_developer){
            Intent intentdeveloper = new Intent(HomeActivity.this,Developer.class);
            startActivity(intentdeveloper);
            return true;
        }
        if(id == R.id.id_about){
            Intent intentabout = new Intent(HomeActivity.this,About.class);
            startActivity(intentabout);

            return true;
        }
        if(id == R.id.id_sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(loginActivity);
            finish();
        }

        return true;
    }
}
