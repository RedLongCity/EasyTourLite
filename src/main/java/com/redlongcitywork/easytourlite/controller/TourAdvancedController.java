package com.redlongcitywork.easytourlite.controller;

import com.redlongcitywork.easytourlite.handler.request.SearchRequestHandler;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedResponse;
import com.redlongcitywork.easytourlite.service.TourAdvancedService;
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
 * @author redlongcity
 * 24/03/2018
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
        
        if (request.getRatings() == null || request.getRatings().isEmpty()) {
            Hotel_Rating first = new Hotel_Rating();
            first.setId("3");
            Hotel_Rating second = new Hotel_Rating();
            second.setId("78");
            request.getRatings().add(first);
            request.getRatings().add(second);
        }
        if (request.getNight_From() == null) {
            request.setNight_From(2);
        }
        if (request.getNight_Till() == null) {
            request.setNight_Till(7);
        }

        TourAdvancedResponse answer = handler.execute(request);
        if (answer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
    
}
