package com.jpmchase.nycschool;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by aswathijyothish on 4/11/18.
 */

public class SchoolDbHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NYSCHOOLDB.sqlite";
    private static final String TAG = "DbActivity";
    private SQLiteDatabase nySchoolDatabase;
    private final Context myContext;
    private static final String NO_DATA = "No Data Found";
    private static SchoolDbHelper nycSchoolDbHelper;

    public static SchoolDbHelper getInstance(Context context) {
        if (null == nycSchoolDbHelper) {
            nycSchoolDbHelper = new SchoolDbHelper(context);
        }
        return nycSchoolDbHelper;
    }

    public SchoolDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DATABASE_NAME).toString();
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getWritableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }

        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        //  this.getReadableDatabase();

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            Log.d(TAG, "checked");
        } catch (SQLiteException e) {
            //database does't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        Log.d(TAG, "copied");
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH;
        nySchoolDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (nySchoolDatabase != null)
            nySchoolDatabase.close();
        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return nySchoolDatabase.query("NY_SCHOOLS", null, null, null, null, null, null);
    }

    /***
     *  This method retrieves list of schools ordered by School Name
     * @return List of Schools
     */
    public List<SchoolDetail> getAllSchools() {
        List<SchoolDetail> schoolDetails = new LinkedList<SchoolDetail>();

        // 1. build the query
        String query = "SELECT dbn,school_name,postcode FROM NY_SCHOOLS order by school_name";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  Cursor cursor;
        // db.rawQuery(query,new String []{})  ;
        Cursor cursor = db.rawQuery(query, null);
        //cursor = db.query("NY_SCHOOLS_VIEW", null, null, null, null, null, "SCHOOL_NAME");
        // 3. go over each row, build book and add it to list
        SchoolDetail schoolDetail = null;
        if (cursor.moveToFirst()) {
            do {
                schoolDetail = new SchoolDetail();
                schoolDetail.setSchoolID(cursor.getString(cursor.getColumnIndex("dbn")));
                schoolDetail.setSchoolName(cursor.getString(cursor.getColumnIndex("school_name")));
                schoolDetail.setZipCode(cursor.getString(cursor.getColumnIndex("postcode")));


                // Add book to books
                schoolDetails.add(schoolDetail);
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (null != db) {
            db.close();
        }


        consolidateSchoolRankings(schoolDetails);
        return schoolDetails;
    }

    /***
     * This method retrieves school details based on the school ID.
     * @param schoolDetail
     * @return  School Details
     */
    public SchoolDetail getSchoolById(SchoolDetail schoolDetail) {


        String query = "SELECT school.dbn,school.school_name,school.overview_paragraph,school.location,school.phone_number," +
                "school.school_email,school.website,school.primary_address_line_1,school.postcode,school.state_code,school.latitude,school.longitude," +
                "sat.num_of_sat_test_takers,sat.sat_critical_reading_avg_Score,sat.sat_math_avg_score,sat.sat_writing_avg_score " +
                " FROM NY_SCHOOLS school  LEFT JOIN  NY_SCHOOLS_SAT_RESULTS sat  ON school.dbn=sat.dbn WHERE school.dbn=?";

        Log.d("HELPER_ACTIVITY", "Query:" + query);
        Log.d("HELPER_ACTIVITY", "ID :" + schoolDetail.getSchoolID());
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;

        cursor = db.rawQuery(query, new String[]{schoolDetail.getSchoolID()});


        if (cursor.moveToFirst()) {

            do {

                schoolDetail.setSchoolID(cursor.getString(0));
                schoolDetail.setSchoolName(cursor.getString(1));
                schoolDetail.setSchoolDescription(cursor.getString(2));
                schoolDetail.setLocation(cursor.getString(3));
                schoolDetail.setPhone(cursor.getString(4));
                schoolDetail.setEmail(cursor.getString(5));
                schoolDetail.setWebsite(cursor.getString(6));
                schoolDetail.setPrimaryAddress(cursor.getString(7));
                schoolDetail.setZipCode(cursor.getString(8));
                schoolDetail.setStateCode(cursor.getString(9));
                try {
                    schoolDetail.setLatitude(cursor.getDouble(10));
                    schoolDetail.setLongitude(cursor.getDouble(11));
                } catch (Exception e) {
                    Log.d("HELPER_ACTIVITY", "Data issue with latitude/longitude");
                }
                if (null != cursor.getString(12) && !cursor.getString(12).trim().equals("")) {
                    schoolDetail.setTestTakers(cursor.getString(12));
                } else {
                    schoolDetail.setTestTakers(NO_DATA);
                }
                if (null != cursor.getString(13) && !cursor.getString(13).trim().equals("")) {
                    schoolDetail.setCriticalReadingAvgScore(cursor.getString(13));
                } else {
                    schoolDetail.setCriticalReadingAvgScore(NO_DATA);
                }
                if (null != cursor.getString(14) && !cursor.getString(14).trim().equals("")) {
                    schoolDetail.setMathAvgScore(cursor.getString(14));
                } else {
                    schoolDetail.setMathAvgScore(NO_DATA);
                }
                if (null != cursor.getString(15) && !cursor.getString(15).trim().equals("")) {
                    schoolDetail.setWritingAvgScore(cursor.getString(15));
                } else {
                    schoolDetail.setWritingAvgScore(NO_DATA);
                }


            } while (cursor.moveToNext());
        }
        if (null != db) {
            db.close();
        }


        return schoolDetail;
    }

    /**
     * This method evaluate the ranks of the SAT scores based on the field requested
     * @param field
     * @return   List of school with ranks
     */
    public Map<String, SchoolDetail> getSchoolByRank(String field) {

        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("SELECT s1.dbn,s1.fieldplaceholder ,COUNT(DISTINCT s2.fieldplaceholder) AS rank FROM ").append(
                " NY_SCHOOLS_SAT_RESULTS s1 JOIN NY_SCHOOLS_SAT_RESULTS s2 ON (s1.fieldplaceholder <= s2.fieldplaceholder and s1.fieldplaceholder !=\"s\" and s2.fieldplaceholder !=\"s\") ").append(
                " GROUP BY s1.SCHOOL_NAME order by rank");

        String query = queryBuilder.toString().replace("fieldplaceholder", field);
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery(query, null);

        SchoolDetail schoolDetail = null;
        Map<String, SchoolDetail> schoolMap = null;
        if (cursor.moveToFirst()) {
            schoolMap = new HashMap<String, SchoolDetail>();
            do {
                schoolDetail = new SchoolDetail();
                schoolDetail.setSchoolID(cursor.getString(cursor.getColumnIndex("dbn")));

                if (field.equals("sat_critical_reading_avg_score")) {
                    schoolDetail.setRankReading(cursor.getInt(cursor.getColumnIndex("rank")));

                } else if (field.equals("sat_math_avg_score")) {
                    schoolDetail.setRankMath(cursor.getInt(cursor.getColumnIndex("rank")));

                } else if (field.equals("sat_writing_avg_score")) {
                    schoolDetail.setRankWriting(cursor.getInt(cursor.getColumnIndex("rank")));

                }
                schoolMap.put(schoolDetail.getSchoolID(), schoolDetail);


            } while (cursor.moveToNext());
        }
        if (null != db) {
            db.close();
        }
        return schoolMap;
    }


    /***
     * This method consolidates ranks for all the SAT subjects.
     * @param schoolDetailList
     */
    public void consolidateSchoolRankings(List<SchoolDetail> schoolDetailList) {
        Map<String, SchoolDetail> readRankMap = getSchoolByRank("sat_critical_reading_avg_score");
        Map<String, SchoolDetail> mathRankMap = getSchoolByRank("sat_math_avg_score");
        Map<String, SchoolDetail> writeRankMap = getSchoolByRank("sat_writing_avg_score");

        for (SchoolDetail schoolDetail : schoolDetailList
                ) {
            if (null != writeRankMap && null != writeRankMap.get(schoolDetail.getSchoolID())) {
                schoolDetail.setRankWriting(writeRankMap.get(schoolDetail.getSchoolID()).getRankWriting());
            }
            if (null != readRankMap && null != readRankMap.get(schoolDetail.getSchoolID())) {
                schoolDetail.setRankReading(readRankMap.get(schoolDetail.getSchoolID()).getRankReading());
            }
            if (null != mathRankMap && null != mathRankMap.get(schoolDetail.getSchoolID())) {
                schoolDetail.setRankMath(mathRankMap.get(schoolDetail.getSchoolID()).getRankMath());
            }

        }


    }

}
