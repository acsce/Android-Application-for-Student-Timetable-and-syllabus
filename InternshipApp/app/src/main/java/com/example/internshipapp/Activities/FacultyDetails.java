package com.example.internshipapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.internshipapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyDetails extends AppCompatActivity {

    private CircleImageView facultyImage;
    private Toolbar toolbar;
    private TextView facultyName;
    private TextView phoneNumber;
    private TextView email;
    private TextView place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);

        setupUIViews();
        initToolbar();

        setupDetails();
    }

    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarFacultyDetails);
        facultyImage = (CircleImageView)findViewById(R.id.ivFaculty);
        facultyName = (TextView)findViewById(R.id.tvFacultySelName);
        phoneNumber = (TextView)findViewById(R.id.tvPhoneNumber);
        email = (TextView)findViewById(R.id.tvEmail);
        place = (TextView)findViewById(R.id.tvPlace);

    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetails(){

        int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY, 0);
        String[] facultyNames = getResources().getStringArray(R.array.faculty_name);
        int[] facultyImages = new int[]{R.drawable.img_mareeswari,R.drawable.img_sunita,R.drawable.img_kavya,R.drawable.img_jyoti,R.drawable.img_krishna,R.drawable.img_senthil};
        int[] facultyArray = new int[]{R.array.Faculty1,R.array.Faculty2,R.array.Faculty3,R.array.Faculty4,R.array.Faculty5,R.array.Faculty6};
        String[] facultyDetails = getResources().getStringArray(facultyArray[faculty_pos]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        place.setText(facultyDetails[2]);
        facultyImage.setImageResource(facultyImages[faculty_pos]);
        facultyName.setText(facultyNames[faculty_pos]);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
