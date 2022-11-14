package com.redlongcitywork.easytourlite.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.HotToursRequestView;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
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
 * @author redlongcity 24/12/2017
 */
@RestController
@RequestMapping("/json")
public class HotToursRequestController {

    @Autowired
    private HotToursRequestService service;

    @JsonView(HotToursRequestView.class)
    @RequestMapping(value = "/hotrequest", method = RequestMethod.GET)
    public ResponseEntity<List<HotToursRequest>> getCountries() {
        List<HotToursRequest> hotrequestList = service.findAllHotRequests();
        if (hotrequestList == null) {
            return new ResponseEntity<List<HotToursRequest>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<HotToursRequest>>(hotrequestList, HttpStatus.OK);
    }

    @JsonView(HotToursRequestView.class)
    @RequestMapping(value = "/hotrequest/{id}", method = RequestMethod.GET)
    public ResponseEntity<HotToursRequest> getHotToursRequest(@PathVariable("id") Integer id) {
        HotToursRequest hotrequest = service.findById(id);
        if (hotrequest == null) {
            return new ResponseEntity<HotToursRequest>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<HotToursRequest>(hotrequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotrequest", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllHotToursRequest() {
        service.deleteAllHotToursRequests();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/hotrequest/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteHotToursRequestById(
            @PathVariable("id") Integer id) {
        HotToursRequest hotrequest = service.findById(id);
        if (hotrequest == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteHotToursRequest(hotrequest);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
