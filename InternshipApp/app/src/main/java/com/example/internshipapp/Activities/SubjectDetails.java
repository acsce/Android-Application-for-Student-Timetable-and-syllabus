package com.example.internshipapp.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.internshipapp.R;

public class SubjectDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarSubjectDetails);
        listView = (ListView)findViewById(R.id.lvSubjectDetails);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView() {
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF,null);

        String[] syllabus = new String[]{};
        String[] titles = getResources().getStringArray(R.array.titles);

        if(subject_selected.equalsIgnoreCase("iot")){
            syllabus = getResources().getStringArray(R.array.iot);
        }else if(subject_selected.equalsIgnoreCase("big")){
            syllabus = getResources().getStringArray(R.array.big);
        }else if(subject_selected.equalsIgnoreCase("uid")){
            syllabus = getResources().getStringArray(R.array.uid);
        }else if(subject_selected.equalsIgnoreCase("internship")){
            syllabus = getResources().getStringArray(R.array.internship);
        }else if(subject_selected.equalsIgnoreCase("project")){
            syllabus = getResources().getStringArray(R.array.project);
        }else{
            syllabus = getResources().getStringArray(R.array.seminar);
        }

        SubjectDetailsAdapter subjectDetailsAdapter = new SubjectDetailsAdapter(this,titles,syllabus);
        listView.setAdapter(subjectDetailsAdapter);


    }

    public class SubjectDetailsAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;



        public SubjectDetailsAdapter(Context context, String[] title, String[] syllabus){
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
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
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);
            }

            title = (TextView)convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = (TextView)convertView.findViewById(R.id.tvSyllabus);


            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);


            return convertView;
        }
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
