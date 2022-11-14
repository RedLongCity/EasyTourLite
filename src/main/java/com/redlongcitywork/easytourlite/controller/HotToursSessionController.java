package com.redlongcitywork.easytourlite.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.HotToursSessionView;
import com.redlongcitywork.easytourlite.json.view.TourView;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import com.redlongcitywork.easytourlite.service.HotToursSessionService;
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
 * @author redlongcity 
 * 24/12/2017
 */
@RestController
@RequestMapping("/json")
public class HotToursSessionController {

    @Autowired
    private HotToursSessionService service;

    @JsonView(HotToursSessionView.class)
    @RequestMapping(value = "/hotsession", method = RequestMethod.GET)
    public ResponseEntity<List<HotToursSession>> getAll() {
        List<HotToursSession> hotsessionList = service.findAll();
        if (hotsessionList == null) {
            return new ResponseEntity<List<HotToursSession>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<HotToursSession>>(hotsessionList, HttpStatus.OK);
    }

    @JsonView(HotToursSessionView.class)
    @RequestMapping(value = "/hotsession/{id}", method = RequestMethod.GET)
    public ResponseEntity<HotToursSession> getById(@PathVariable("id") Integer id) {
        HotToursSession hotsession = service.findById(id);
        if (hotsession == null) {
            return new ResponseEntity<HotToursSession>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<HotToursSession>(hotsession, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotsession", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll() {
        service.deleteAllHotToursSessions();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/hotsession/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") Integer id) {
        HotToursSession hotsession = service.findById(id);
        if (hotsession == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteHotToursSession(hotsession);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
