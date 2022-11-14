package com.redlongcitywork.easytourlite.controller;

import org.springframework.http.ResponseEntity;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.utils.GlobalUpdateDelayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class SupportController {

    @Autowired
    AppConstants constants;

    @Autowired
    GlobalUpdateDelayUtils delayUtils;

    @RequestMapping(value = "/getshortstatus", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getShortStatus() {
        Boolean status = constants.isShortRun();
        return new ResponseEntity<Boolean>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/getglobalstatus", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getGlobalStatus() {
        Boolean status = constants.isGlobalRun();
        return new ResponseEntity<Boolean>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/getshortsuspended", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getShortSuspended() {
        return new ResponseEntity<Boolean>(constants.isShortSuspended(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getglobalsuspended", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getGlobalSuspended() {
        return new ResponseEntity<Boolean>(constants.isGlobalSuspended(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getshortdelay", method = RequestMethod.GET)
    public ResponseEntity<Long> getShortDelay() {
        Long delay = constants.getShortUpdatingDelay();
        return new ResponseEntity<Long>(delay, HttpStatus.OK);
    }

    @RequestMapping(value = "/getglobaldelay", method = RequestMethod.GET)
    public ResponseEntity<Integer> getGlobalDelay() {
        Integer delay = delayUtils.getHumanData(constants.getGlobalUpdatingDelay());
        return new ResponseEntity<Integer>(delay, HttpStatus.OK);
    }

    @RequestMapping(value = "/getrequestpullsize", method = RequestMethod.GET)
    public ResponseEntity<Integer> getRequestPullSize() {
        Integer size = constants.getRequestPullSize();
        return new ResponseEntity<Integer>(size, HttpStatus.OK);
    }

    @RequestMapping(value = "/getresponsepullsize", method = RequestMethod.GET)
    public ResponseEntity<Integer> getResponsePullSize() {
        Integer size = constants.getResponsePullSize();
        return new ResponseEntity<Integer>(size, HttpStatus.OK);
    }

    @RequestMapping(value = "/getfreezzeetime", method = RequestMethod.GET)
    public ResponseEntity<Long> getFreezzeeTimeDelay() {
        Long delay = constants.getFreezzeeTimeDelay();
        return new ResponseEntity<Long>(delay, HttpStatus.OK);
    }

    @RequestMapping(value = "/getrevelance", method = RequestMethod.GET)
    public ResponseEntity<Integer> getRevelance() {
        Integer revelance = constants.getRevelance();
        return new ResponseEntity<Integer>(revelance, HttpStatus.OK);
    }

}
