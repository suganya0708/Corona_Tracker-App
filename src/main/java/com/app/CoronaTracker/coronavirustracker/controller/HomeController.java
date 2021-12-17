package com.app.CoronaTracker.coronavirustracker.controller;

import com.app.CoronaTracker.coronavirustracker.model.LocationStats;
import com.app.CoronaTracker.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;


    @GetMapping("/")
    public String home(Model model) {
        List < LocationStats > allstats = coronavirusDataService.getAllstats();
        int totalReportedCases = allstats.stream ().mapToInt (stat-> stat.getLatestTestCasesReport ()).sum ();
        int totalNewReportedCases =  allstats.stream ().mapToInt (stat -> stat.getDiffFromPrevDay ()).sum ();
        model.addAttribute ("locationStats", allstats);
        model.addAttribute ("totalReportedCases",totalReportedCases);
        model.addAttribute ("totalNewReportedCases",totalNewReportedCases);



          return "home";
      }


}
