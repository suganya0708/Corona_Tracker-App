package com.app.CoronaTracker.coronavirustracker.services;

import com.app.CoronaTracker.coronavirustracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";



    List < LocationStats > allstats = new ArrayList <> ();

    public List <LocationStats > getAllstats() {
        return allstats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List < LocationStats > newstats = new ArrayList <> ();
        HttpClient client = HttpClient.newHttpClient ();
        HttpRequest request = HttpRequest.newBuilder ().uri (URI.create (VIRUS_DATA_URL)).build ();
        HttpResponse < String > response = client.send (request, HttpResponse.BodyHandlers.ofString ());

        StringReader csvBodyReader = new StringReader (response.body ());
        Iterable < CSVRecord > records = CSVFormat.EXCEL.withFirstRecordAsHeader ().parse (csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats ();
            locationStat.setState (record.get ("Province/State"));
            locationStat.setCountry (record.get ("Country/Region"));
            int latestCases =  (Integer.parseInt (record.get (record.size () - 1)));
            int prevDayCases = (Integer.parseInt (record.get(record.size ()- 2 )));
            locationStat.setLatestTestCasesReport (latestCases);
            locationStat.setDiffFromPrevDay (latestCases - prevDayCases);

            newstats.add (locationStat);
        }

        this.allstats = newstats;


    }
}
