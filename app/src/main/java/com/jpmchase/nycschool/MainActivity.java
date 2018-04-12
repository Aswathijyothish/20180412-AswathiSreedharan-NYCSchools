package com.jpmchase.nycschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static final String SCHOOL_DETAIL = "SCHOOL_DETAIL";
    private List<SchoolDetail> schoolList = new ArrayList<SchoolDetail>();
    private ArrayList<SchoolDetail> filteredList = null;
    private RecyclerView recyclerView;
    private SchoolRecyclerViewAdapter schoolRecyclerViewAdapter;
    private SearchView searchView;
    private SchoolDbHelper db;


    /***
     * retrieves data from sql db
     * Render the view for school details list order by school name
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("School Name or Zipcode");
        searchView.setQueryRefinementEnabled(true);

        db = SchoolDbHelper.getInstance(this);

        /***
         * Listener to query the schools by school name or zip code
         */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                filteredList = new ArrayList<SchoolDetail>();
                for (SchoolDetail nycSchoolDetails : schoolList
                        ) {
                    if ((null != nycSchoolDetails.getSchoolName() &&
                            nycSchoolDetails.getSchoolName().toLowerCase().startsWith(newText)) ||
                            (null != nycSchoolDetails.getZipCode() &&
                                    nycSchoolDetails.getZipCode().startsWith(newText))) {
                        filteredList.add(nycSchoolDetails);
                    }
                }
                schoolRecyclerViewAdapter = new SchoolRecyclerViewAdapter(filteredList, MainActivity.this);
                recyclerView.setAdapter(schoolRecyclerViewAdapter);
                return true;
            }
        });

        try {

            db.createDataBase();
            db.openDataBase();

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }

        this.schoolList = db.getAllSchools();
       // sortBySAT(this.schoolList, "math");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        schoolRecyclerViewAdapter = new SchoolRecyclerViewAdapter(schoolList, MainActivity.this);
        recyclerView.setAdapter(schoolRecyclerViewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                clickView(view, position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                clickView(view, position);
            }


        }));

    }

    public void clickView(View view, int position) {
        Intent intent = new Intent(MainActivity.this, SchoolDetailActivity.class);
        SchoolDetail schoolDetail = null;
        if (null != filteredList && !filteredList.isEmpty()) {
            schoolDetail = filteredList.get(position);
        } else {
            schoolDetail = schoolList.get(position);
        }
        intent.putExtra(SCHOOL_DETAIL, db.getSchoolById(schoolDetail));
        startActivity(intent);

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(null==this.schoolList){
            this.schoolList=db.getAllSchools();
        }

    }
    /***
     * Need to implement the menu on the home page to list the schools based on the rank.
     * This method is to sort the school list based on following Math/Reading/Writing Scores
     *
     * @param schoolList
     * @param sortField
     */
    public static void sortBySAT(List<SchoolDetail> schoolList, final String sortField) {
        Collections.sort(schoolList, new Comparator<SchoolDetail>() {
            public int compare(SchoolDetail o1, SchoolDetail o2) {
                // Write your logic here.
                int val = 0;
                if (sortField.equals("math")) {

                    if (o1.getRankMath() != null && o2.getRankMath() != null) {
                        val = o1.getRankMath().compareTo(o2.getRankMath());
                    } else if (o1.getRankMath() != null && o2.getRankMath() == null) {
                        val = -1;
                    } else if (o1.getRankMath() == null && o2.getRankMath() != null) {
                        val = 1;
                    } else {
                        val = 0;
                    }
                }
                if (sortField.equals("read")) {
                    if (o1.getRankReading() != null && o2.getRankReading() != null) {
                        val = o1.getRankReading().compareTo(o2.getRankReading());
                    } else if (o1.getRankReading() != null && o2.getRankReading() == null) {
                        val = -1;
                    } else if (o1.getRankReading() == null && o2.getRankReading() != null) {
                        val = 1;
                    } else {
                        val = 0;
                    }
                }
                if (sortField.equals("write")) {
                    if (o1.getRankWriting() != null && o2.getRankWriting() != null) {
                        val = o1.getRankWriting().compareTo(o2.getRankWriting());
                    } else if (o1.getRankWriting() != null && o2.getRankWriting() == null) {
                        val = -1;
                    } else if (o1.getRankWriting() == null && o2.getRankWriting() != null) {
                        val = 1;
                    } else {
                        val = 0;
                    }
                }
                return val;
            }
        });

    }
}
