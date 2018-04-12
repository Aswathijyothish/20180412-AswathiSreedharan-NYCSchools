package com.jpmchase.nycschool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * JUnit for ranking
 * Created by aswathijyothish on 4/12/18.
 */

public class NYCSchoolTester {

    @Test
    public void testSchoolRankOrder() {
        List<SchoolDetail> schoolDetailList = new ArrayList<SchoolDetail>();

        SchoolDetail s1 = new SchoolDetail();
        s1.setSchoolID("234234");
        s1.setSchoolName("ABCD");
        s1.setRankMath(320);

        SchoolDetail s2 = new SchoolDetail();
        s2.setSchoolID("435324");
        s2.setSchoolName("tryey");
        s2.setRankMath(200);


        SchoolDetail s3 = new SchoolDetail();
        s3.setSchoolID("43535");
        s3.setSchoolName("asaf");
        s3.setRankMath(100);


        schoolDetailList.add(s1);
        schoolDetailList.add(s2);
        schoolDetailList.add(s3);
        //schoolDetailList.add(s4);


        MainActivity.sortBySAT(schoolDetailList, "math");
        System.out.println(schoolDetailList);
        assertEquals(schoolDetailList.get(0).getRankMath(), new Integer(100));
    }


}
