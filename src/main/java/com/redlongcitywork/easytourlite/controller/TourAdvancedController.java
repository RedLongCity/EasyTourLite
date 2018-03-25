package com.redlongcitywork.easytourlite.controller;

import com.redlongcitywork.easytourlite.handler.request.SearchRequestHandler;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedResponse;
import com.redlongcitywork.easytourlite.service.TourAdvancedService;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity 24/03/2018
 */
@RestController
@RequestMapping("/json/advanced")
public class TourAdvancedController {

    @Autowired
    private SearchRequestHandler handler;

    @Autowired
    private TourAdvancedService service;

    @RequestMapping(value = "/tour", method = RequestMethod.GET)
    public ResponseEntity<List<TourAdvanced>> getAllTourAdvanceds() {
        List<TourAdvanced> tourList = service.findAll();
        if (tourList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    @RequestMapping(value = "/gettoursbyrequest", method = RequestMethod.POST)
    public ResponseEntity<TourAdvancedResponse> getTourAdvancedsByRequest(
            @RequestBody SearchingRequest request) {
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (request.getCountry() == null) {
            request.setCountry(new Country("338"));
        }

        if (request.getCity() == null) {
            request.setCity(new From_Cities("2014"));
        }

        if (request.getRatingSet() == null) {
            request.getRatingSet().add(new Hotel_Rating("78", "5"));
        }

        if (request.getAdultAmount() == null) {
            request.setAdultAmount(2);
        }

        if (request.getNightFrom() == null) {
            request.setNightFrom(2);
        }

        if (request.getNightTill() == null) {
            request.setNightTill(7);
        }

        if (request.getDateFrom() == null) {
            request.setDateFrom(new Date(System.currentTimeMillis()));
        }

        if (request.getDateTill() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            request.setDateTill((Date) calendar.getTime());
        }

        TourAdvancedResponse answer = handler.execute(request);
        if (answer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}
