package com.redlongcitywork.easytourlite.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.redlongcitywork.easytourlite.handler.request.HotToursRequestHandler;
import com.redlongcitywork.easytourlite.json.view.TourView;
import com.redlongcitywork.easytourlite.model.*;
import com.redlongcitywork.easytourlite.service.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author redlongcity 21/11/2017
 */
@RestController
@RequestMapping("/json")
public class TourController {

    private final HotToursRequestHandler handler;
    private final TourService tourService;

    public TourController(HotToursRequestHandler handler,
                          TourService tourService) {
        this.handler = handler;
        this.tourService = tourService;
    }

    private final List<String> ratings =
            Lists.newArrayList("7", "3", "4", "5");
    private final Comparator<Hotel_Rating> hotelRatingsComparator =
            Comparator.comparingInt(r -> ratings.indexOf(r.getId()));

    @JsonView(TourView.class)
    @RequestMapping(value = "/tour", method = RequestMethod.GET)
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tourList = tourService.findAll();
        if (tourList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    @JsonView(TourView.class)
    @RequestMapping(value = "/gettoursbyrequest", method = RequestMethod.POST)
    public ResponseEntity<TourResponse> getToursByRequest(
            @RequestBody HotToursRequest request) {
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

        TourResponse answer = handler.handle(request);
        Set<Tour> filtered = answer.getTours().stream()
                .filter(getCountryPredicate(request.getCountry()))
                .filter(getFromCityPredicate(request.getFrom_Cities()))
                .filter(getHotelRatingPredicate(request.getRatings()))
                .filter(getMealTypePredicate(request.getMeal_Type()))
                .filter(getNightFromPredicate(request.getNight_From()))
                .filter(getNightTillPredicate(request.getNight_Till()))
                .collect(Collectors.toSet());

        answer.setTours(filtered);

        if (answer == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    private Predicate<Tour> getCountryPredicate(Country country) {
        return (tour) -> tour == null
                || country == null
                || tour.getCountry().getId().equals(country.getId());
    }

    private Predicate<Tour> getFromCityPredicate(From_Cities from_cities) {
        return (tour) -> tour == null
                || from_cities == null
                || tour.getFrom_Cities().getId().equals(from_cities.getId());
    }

    private Predicate<Tour> getHotelRatingPredicate(Set<Hotel_Rating> set) {
        Hotel_Rating low = set.stream().min(hotelRatingsComparator)
                .orElse(new Hotel_Rating("7", "2"));
        Hotel_Rating high = set.stream().max(hotelRatingsComparator)
                .orElse(new Hotel_Rating("5", "5"));
        return (tour) -> ratings.indexOf(tour.getHotel_Rating().getId())
                >= ratings.indexOf(low.getId())
                && ratings.indexOf(tour.getHotel_Rating().getId())
                <= ratings.indexOf(high.getId());
    }

    private Predicate<Tour> getMealTypePredicate(Meal_Type meal_type) {
        return (tour) -> tour == null
                || meal_type == null
                || tour.getMeal_Type().getId().equals(meal_type.getId());
    }

    private Predicate<Tour> getNightFromPredicate(int nightFrom) {
        return (tour) -> tour != null && tour.getDuration() >= nightFrom;
    }

    private Predicate<Tour> getNightTillPredicate(int nightTill) {
        return (tour) -> tour != null && tour.getDuration() <= nightTill;
    }
}
