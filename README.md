# 20180412-AswathiSreedharan-NYCSchools
Project for NYC HighSchools

NYC HIGHSCHOOL DIRECTORY APP
-----------------------------
GUIDE TO KNOW MORE ABOUT NYC HIGH SCHOOLS

ABOUT NYC HIGHSCHOOL DIRECTORY APP
------------------------------------
NYC HIGHSCHOOL DIRECTORY APP helps to know more about the high schools located in NewYork City.
School seekers can select schools based on different SAT scores ranking.

WHAT WILL YOU FIND IN NYC HIGHSCHOOL DIRECTORY APP
----------------------------------------------------
1) List of NYC Highschools listed alphabetically.
2) Serach option to find the Highschool based on school name and zipcode.
3) Details of NYC HighSchool( Address,Phone,Email,Website)
4) Access to Phone,Email,Website from the App.
5) Displays the SAT scores (Reading,Writing,Math).
6) Displays the NY Rank based on SAT Scores. Implemented data analytics to evaluate ranks and
created ranking system based on SAT scores.

ABOUT THE PROJECT
------------------
1)Data fetched from the URL: https://data.cityofnewyork.us/Education/DOE-High- School-
Directory-2017/s3k6- pzi2 and https://data.cityofnewyork.us/Education/SAT-
Results/f9bf-2cp4

2)Data exported as csv files. Imported to sqlite. Database NYSCHOOLDB created.

3) The DBhelper class handles the sqlite db functionalities.
    - Database copied.
    - Different queries written to fetch the NYC Highschool details.
    - The data retrieved after running the queries stored in the SchoolDetail Object.
    - Implemented ranking system by analyzing the different SAT Scores.

5) The Mainactivity contains the following functionalities: 
   - List the HighSchools order by SchoolNames
   - Provide SearchView to search the Schools either by SchoolNames or by Zipcode
   - CLicking the school name in the list, it will be directed to the School Detail Page.
   - Code for sorting the Highschools based on the SAT Ranking (Just the java code is implemented, 
   the ui doesn't show this functionality)
   
6) The SchoolDetailActivity displays the various details of the HighSchool
    - School Name
    - Address 
    - Phone Number
    - Email
    - Website
    - SAT scores (Reading, Writing, Math)
    - NY RANK based on the SAT scores (Reading,Writing,Math)
    
 7) For displaying the list of HighSchools, RecyclerView is used. 
    Classes used - RecyclerItemClickListener, SchoolRecyclerViewAdapter ,SchoolViewHolder
    
 8) NYCSchoolTester - A Junit to test the sorting based on ranks. 
  
FUTURE ENHANCEMENTS
-------------------
1) ADD MENU FOR SORTING BASED ON RANKS.
2) MAP DISPLAYING THE LOCATION OF HIGHSCHOOL.
3) PRINT DETAILS.
4) EMAIL DETAILS.   


    <tr>
        <td>
 <img src = "https://github.com/Aswathijyothish/20180412-AswathiSreedharan-NYCSchools/blob/master/Screens/Screen_Icon.png" height= "300" width = "150"> </td>
        <td>
 <img src = "https://github.com/Aswathijyothish/20180412-AswathiSreedharan-NYCSchools/blob/master/Screens/Screen_HomeScreen.png" height= "300" width = "150"></td>
        <td>
 <img src = "https://github.com/Aswathijyothish/20180412-AswathiSreedharan-NYCSchools/blob/master/Screens/Search_SchoolName.png" height= "300" width = "150">
        </td>
     </tr>
    <tr>
        <td>
 <img src = "https://github.com/Aswathijyothish/20180412-AswathiSreedharan-NYCSchools/blob/master/Screens/Search_ZipCode.png" height= "300" width = "150"> </td>
        <td>
 <img src =  "https://github.com/Aswathijyothish/20180412-AswathiSreedharan-NYCSchools/blob/master/Screens/Screen_SchoolDetail.png" height= "300" width = "150"></td>
        
     </tr>
    <tr>
        <td>
 <img src = "https://github.com/Aswathijyothish/20180412-AswathiSreedharan-NYCSchools/blob/master/Screens/Screen_CalltoPhone.png" height= "300" width = "150">
        </td>
        <td>
 <img src = https://github.com/Aswathijyothish/20180412-AswathiSreedharan-NYCSchools/blob/master/Screens/Screen_SendtoWebsite.png" height= "300" width = "150"></td>
     </tr>
 
 

