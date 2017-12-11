package com.redlongcitywork.easytourlite.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.command.response.ResponseCommand;
import com.redlongcitywork.easytourlite.handler.request.ItToursHotFiltersRequestHandler;
import com.redlongcitywork.easytourlite.handler.response.ItToursHotFiltersResponseHandler;
import com.redlongcitywork.easytourlite.json.view.CountryView;
import com.redlongcitywork.easytourlite.model.FiltersResponse;
import com.redlongcitywork.easytourlite.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class FiltersController {

    @Autowired
    ItToursHotFiltersRequestHandler filtersRequestHandler;

    @Autowired
    ItToursHotFiltersResponseHandler filtersResponseHandler;

    @JsonView(CountryView.class)
    @RequestMapping(value = "/getfilters", method = RequestMethod.GET)
    public ResponseEntity<FiltersResponse> getFilters() {
        ResponseCommand command = filtersRequestHandler.handleRequest(new Request());
        FiltersResponse response = filtersResponseHandler.executeResponseCommand(command);
        if (response == null) {
            return new ResponseEntity<FiltersResponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<FiltersResponse>(response, HttpStatus.OK);
    }

}
