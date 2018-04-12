package com.jpmchase.nycschool;

import java.io.Serializable;

/**
 * Created by aswathijyothish on 4/11/18.
 */

public class SchoolDetail implements Serializable {

    private static final long serialVersionUID = 123523L;
    private String schoolID;
    private String schoolName;
    private String schoolDescription;
    private String phone;
    private String email;
    private String website;
    private String location;
    private String primaryAddress;
    private String city;
    private String zipCode;
    private String stateCode;
    private double latitude;
    private double longitude;
    private String testTakers;
    private String criticalReadingAvgScore;
    private String mathAvgScore;
    private String writingAvgScore;
    private Integer rankMath;
    private Integer rankReading;
    private Integer rankWriting;


    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolDescription() {
        return schoolDescription;
    }

    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTestTakers() {
        return testTakers;
    }

    public void setTestTakers(String testTakers) {
        this.testTakers = testTakers;
    }

    public String getCriticalReadingAvgScore() {
        return criticalReadingAvgScore;
    }

    public void setCriticalReadingAvgScore(String criticalReadingAvgScore) {
        this.criticalReadingAvgScore = criticalReadingAvgScore;
    }

    public String getMathAvgScore() {
        return mathAvgScore;
    }

    public void setMathAvgScore(String mathAvgScore) {
        this.mathAvgScore = mathAvgScore;
    }

    public String getWritingAvgScore() {
        return writingAvgScore;
    }

    public void setWritingAvgScore(String writingAvgScore) {
        this.writingAvgScore = writingAvgScore;
    }


    public void setRankMath(Integer rankMath) {
        this.rankMath = rankMath;
    }

    public Integer getRankReading() {
        return rankReading;
    }

    public void setRankReading(Integer rankReading) {
        this.rankReading = rankReading;
    }

    public Integer getRankWriting() {
        return rankWriting;
    }

    public void setRankWriting(Integer rankWriting) {
        this.rankWriting = rankWriting;
    }

    public Integer getRankMath() {
        return rankMath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolDetail)) return false;

        SchoolDetail that = (SchoolDetail) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (schoolID != null ? !schoolID.equals(that.schoolID) : that.schoolID != null)
            return false;
        if (schoolName != null ? !schoolName.equals(that.schoolName) : that.schoolName != null)
            return false;
        if (schoolDescription != null ? !schoolDescription.equals(that.schoolDescription) : that.schoolDescription != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null)
            return false;
        if (primaryAddress != null ? !primaryAddress.equals(that.primaryAddress) : that.primaryAddress != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null) return false;
        if (stateCode != null ? !stateCode.equals(that.stateCode) : that.stateCode != null)
            return false;
        if (testTakers != null ? !testTakers.equals(that.testTakers) : that.testTakers != null)
            return false;
        if (criticalReadingAvgScore != null ? !criticalReadingAvgScore.equals(that.criticalReadingAvgScore) : that.criticalReadingAvgScore != null)
            return false;
        if (mathAvgScore != null ? !mathAvgScore.equals(that.mathAvgScore) : that.mathAvgScore != null)
            return false;
        if (writingAvgScore != null ? !writingAvgScore.equals(that.writingAvgScore) : that.writingAvgScore != null)
            return false;
        if (rankMath != null ? !rankMath.equals(that.rankMath) : that.rankMath != null)
            return false;
        if (rankReading != null ? !rankReading.equals(that.rankReading) : that.rankReading != null)
            return false;
        return rankWriting != null ? rankWriting.equals(that.rankWriting) : that.rankWriting == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = schoolID != null ? schoolID.hashCode() : 0;
        result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
        result = 31 * result + (schoolDescription != null ? schoolDescription.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (primaryAddress != null ? primaryAddress.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (stateCode != null ? stateCode.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (testTakers != null ? testTakers.hashCode() : 0);
        result = 31 * result + (criticalReadingAvgScore != null ? criticalReadingAvgScore.hashCode() : 0);
        result = 31 * result + (mathAvgScore != null ? mathAvgScore.hashCode() : 0);
        result = 31 * result + (writingAvgScore != null ? writingAvgScore.hashCode() : 0);
        result = 31 * result + (rankMath != null ? rankMath.hashCode() : 0);
        result = 31 * result + (rankReading != null ? rankReading.hashCode() : 0);
        result = 31 * result + (rankWriting != null ? rankWriting.hashCode() : 0);
        return result;
    }
    public String getAddress(){
        return primaryAddress +", "+stateCode+", "+zipCode;
    }

    @Override
    public String toString() {
        return "SchoolDetail{" +
                "schoolID='" + schoolID + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolDescription='" + schoolDescription + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", location='" + location + '\'' +
                ", primaryAddress='" + primaryAddress + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", testTakers='" + testTakers + '\'' +
                ", criticalReadingAvgScore='" + criticalReadingAvgScore + '\'' +
                ", mathAvgScore='" + mathAvgScore + '\'' +
                ", writingAvgScore='" + writingAvgScore + '\'' +
                ", rankMath=" + rankMath +
                ", rankReading=" + rankReading +
                ", rankWriting=" + rankWriting +
                '}';
    }
}

