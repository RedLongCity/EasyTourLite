package com.redlongcitywork.easytourlite.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.handler.request.HotToursRequestHandler;
import com.redlongcitywork.easytourlite.json.view.TourView;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.TourResponse;
import com.redlongcitywork.easytourlite.service.TourService;
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
 * @author redlongcity 21/11/2017
 */
@RestController
@RequestMapping("/json")
public class TourController {

    @Autowired
    private HotToursRequestHandler handler;

    @Autowired
    private TourService tourService;

    @JsonView(TourView.class)
    @RequestMapping(value = "/tour", method = RequestMethod.GET)
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tourList = tourService.findAll();
        if (tourList == null) {
            return new ResponseEntity<List<Tour>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Tour>>(tourList, HttpStatus.OK);
    }

    @JsonView(TourView.class)
    @RequestMapping(value = "/gettoursbyrequest", method = RequestMethod.POST)
    public ResponseEntity<TourResponse> getToursByRequest(
            @RequestBody HotToursRequest request) {
        if (request == null) {
            return new ResponseEntity<TourResponse>(HttpStatus.BAD_REQUEST);
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

        TourResponse answer = handler.execute(request);
        if (answer == null) {
            return new ResponseEntity<TourResponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<TourResponse>(answer, HttpStatus.OK);
    }
}
