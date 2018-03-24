package com.redlongcitywork.easytourlite.controller;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity 24/03/2018
 */
@RestController
@RequestMapping("/json")
public class SearchingRequestController {

    @Autowired
    private SearchingRequestService service;

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public ResponseEntity<List<SearchingRequest>> getCountries() {
        List<SearchingRequest> requestList = service.findAll();
        if (requestList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

    @RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
    public ResponseEntity<SearchingRequest> getSearchingRequest(@PathVariable("id") Integer id) {
        SearchingRequest request = service.findById(id);
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @RequestMapping(value = "/request", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllSearchingRequest() {
        service.deleteAllSearchingRequests();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSearchingRequestById(
            @PathVariable("id") Integer id) {
        SearchingRequest request = service.findById(id);
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteSearchingRequest(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
