package com.jpmchase.nycschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by aswathijyothish on 4/11/18.
 */

public class SchoolDetailActivity extends AppCompatActivity {
    public static final String SCHOOL_DETAIL = "SCHOOL_DETAIL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
        Intent intent = getIntent();
        SchoolDetail schoolDetails = (SchoolDetail) intent.getSerializableExtra(SCHOOL_DETAIL);
        TextView txt_name = (TextView) findViewById(R.id.school_name_vw);
        txt_name.setText(schoolDetails.getSchoolName());
        TextView txt_address = (TextView) findViewById(R.id.school_address_vw);
        txt_address.setText(schoolDetails.getAddress());
        TextView txt_phone = (TextView) findViewById(R.id.school_phone_vw);
        txt_phone.setText(schoolDetails.getPhone());
        TextView txt_email = (TextView) findViewById(R.id.school_email_vw);
        txt_email.setText(schoolDetails.getEmail());
        TextView txt_website = (TextView) findViewById(R.id.school_website_vw);
        txt_website.setText(schoolDetails.getWebsite());
        TextView txt_math = (TextView) findViewById(R.id.sat_score_math);
        txt_math.setText(schoolDetails.getMathAvgScore());
        TextView txt_reading = (TextView) findViewById(R.id.sat_score_reading);
        txt_reading.setText(schoolDetails.getCriticalReadingAvgScore());
        TextView txt_writing = (TextView) findViewById(R.id.sat_score_writing);
        txt_writing.setText(schoolDetails.getWritingAvgScore());
        TextView txt_writing_rank = (TextView) findViewById(R.id.sat_rank_writing);
        if (null != schoolDetails.getRankWriting()) {
            txt_writing_rank.setText(schoolDetails.getRankWriting().toString());
        }
        TextView txt_reading_rank = (TextView) findViewById(R.id.sat_rank_reading);
        if (null != schoolDetails.getRankReading()) {
            txt_reading_rank.setText(schoolDetails.getRankReading().toString());
        }
        TextView txt_math_rank = (TextView) findViewById(R.id.sat_rank_math);
        if (null != schoolDetails.getRankMath()) {
            txt_math_rank.setText(schoolDetails.getRankMath().toString());
        }
    }
}
