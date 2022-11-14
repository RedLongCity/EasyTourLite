package com.redlongcitywork.easytourlite.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.command.response.ResponseCommand;
import com.redlongcitywork.easytourlite.handler.request.HotToursRequestHandler;
import com.redlongcitywork.easytourlite.json.view.TourView;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.TourResponse;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
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
 * @author redlongcity
 * 21/11/2017
 */
@RestController
@RequestMapping("/json")
public class TourController {

    @Autowired
    HotToursRequestHandler handler;

    @Autowired
    private CountryService countryService;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private Hotel_RatingService ratingService;

    @Autowired
    private Meal_TypeService typeService;
    
    @Autowired
    private TourService tourService;

//    @Autowired
//    private ItToursHotSearchResponseHandler searchResponseHandler;
//    @JsonView(TourView.class)
//    @RequestMapping(value = "/gettours", method = RequestMethod.GET)
//    public ResponseEntity<Response> getTours(
//            @RequestParam(value = "country", required = false) String country_Id,
//            @RequestParam(value = "from_city", required = false) String from_Cities_Id,
//            @RequestParam("hotel_rating") String hotel_Rating,
//            @RequestParam("night_from") Integer nightFrom,
//            @RequestParam("night_till") Integer nightTill,
//            @RequestParam(value = "meal_type", required = false) String meal_Type_Id
//    ) {
//        Request request = new Request();
//
//        if (country_Id != null) {
//            Country country = countryService.findById(country_Id);
//            request.setCountry(country);
//        }
//
//        if (from_Cities_Id != null) {
//            From_Cities from_Citites = cityService.findById(from_Cities_Id);
//            request.setFrom_Cities(from_Citites);
//        }
//
//        if (hotel_Rating == null) {
//            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
//        }
//        request.setHotel_Rating(hotel_Rating);
//
//        if (nightFrom == null) {
//            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
//        }
//        request.setNight_From(nightFrom);
//
//        if (nightTill == null) {
//            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
//        }
//        request.setNight_Till(nightTill);
//
//        if (meal_Type_Id != null) {
//            Meal_Type meal_Type = meal_TypeService.findById(meal_Type_Id);
//            request.setMeal_Type(meal_Type);
//        }
//
//        ResponseCommand command = searchRequestHandler.handleRequest(request);
//        Response response = searchResponseHandler.executeResponseCommand(command);
//        if (response == null) {
//            return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//
    @JsonView(TourView.class)
    @RequestMapping(value = "/tour", method = RequestMethod.GET)
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tourList = tourService.findAll();
        if (tourList == null) {
            return new ResponseEntity<List<Tour>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Tour>>(tourList, HttpStatus.OK);
    }
//
//    @JsonView(TourView.class)
//    @RequestMapping(value = "/tour/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Tour> getTour(@PathVariable("id") Integer id) {
//        Tour tour = tourService.findById(id);
//        if (tour == null) {
//            return new ResponseEntity<Tour>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<Tour>(tour, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/tour", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteAllTours() {
//        tourService.deleteAllTours();
//        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/tour/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteTourById(@PathVariable("id") Integer id) {
//        Tour tour = tourService.findById(id);
//        if (tour == null) {
//            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        }
//        tourService.deleteTour(tour);
//        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/deletetoursbeforedate/{date}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteToursBerforeDate(@PathVariable("date") Integer date) {
//        tourService.deleteToursBeforeDate(date);
//        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/deletetoursbetweendates", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteToursBetweenDates(
//            @RequestParam("datefrom") Integer dateFrom,
//            @RequestParam("datetill") Integer dateTill) {
//        tourService.deleteToursBetweenDats(dateFrom, dateTill);
//        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//    }
    @JsonView(TourView.class)
    @RequestMapping(value = "/gettoursbyrequest", method = RequestMethod.POST)
    public ResponseEntity<TourResponse> getToursByRequest(
            @RequestBody HotToursRequest request) {
        if (request == null) {
            return new ResponseEntity<TourResponse>(HttpStatus.BAD_REQUEST);
        }
        if (request.getHotel_Rating().isEmpty() || request.getHotel_Rating().equals("null")) {
            request.setHotel_Rating("3:78");
        }
        if (request.getNight_From() == null) {
            request.setNight_From(2);
        }
        if (request.getNight_Till() == null) {
            request.setNight_Till(7);
        }
        ResponseCommand command = (ResponseCommand) handler.handle(request);
        TourResponse answer = (TourResponse) command.execute();

        if (answer == null) {
            return new ResponseEntity<TourResponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<TourResponse>(answer, HttpStatus.OK);
    }
}
