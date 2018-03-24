package com.redlongcitywork.easytourlite.controller;

import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.service.RegionService;
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
public class RegionController {

    @Autowired
    private RegionService service;

    @RequestMapping(value = "/region", method = RequestMethod.GET)
    public ResponseEntity<List<Region>> getRegions() {
        List<Region> regionList = service.findAll();
        if (regionList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(regionList, HttpStatus.OK);
    }

    @RequestMapping(value = "/region/{id}", method = RequestMethod.GET)
    public ResponseEntity<Region> getRegion(@PathVariable("id") String id) {
        Region region = service.findById(id);
        if (region == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @RequestMapping(value = "/region", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllRegion() {
        service.deleteAllCountries();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/region/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRegionById(
            @PathVariable("id") String id) {
        Region region = service.findById(id);
        if (region == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteRegion(region);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
