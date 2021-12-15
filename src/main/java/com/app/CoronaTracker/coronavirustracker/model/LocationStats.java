package com.app.CoronaTracker.coronavirustracker.model;

public class LocationStats {

    private String state;
    private String country;
    private int latestTestCasesReport;


    public LocationStats(String state, String country, int latestTestCasesReport1) {
        this.state = state;
        this.country = country;
        this.latestTestCasesReport = latestTestCasesReport1;

    }

    public LocationStats() {

    }

    public void setLatestTestCasesReport(int latestTestCasesReport) {
        this.latestTestCasesReport = latestTestCasesReport;
    }
    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
    public int getLatestTestCasesReport() {
        return latestTestCasesReport;
    }


    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", LatestTestCasesReport=" + latestTestCasesReport +
                '}';
    }
}
