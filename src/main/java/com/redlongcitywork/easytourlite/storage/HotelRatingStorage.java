package com.redlongcitywork.easytourlite.storage;

import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 27/03/2018
 */
@Service
public class HotelRatingStorage implements Storage<List<Hotel_Rating>, Hotel_Rating> {

    private final Hotel_RatingService service;

    private List<Hotel_Rating> content;

    public HotelRatingStorage(Hotel_RatingService service) {
        this.service = service;
    }

    @Override
    public List<Hotel_Rating> getContent() {
        if (content == null) {
            updateStorage();
        }
        return content;
    }

    @Override
    public void updateStorage() {
        content = service.findAll();
    }

    public Hotel_Rating findById(String id) {
        Hotel_Rating result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(id))
                    .findFirst()
                    .get();
        }
        return result;
    }

    public Hotel_Rating findByName(String name) {
        Hotel_Rating result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(name))
                    .findFirst()
                    .get();
        }
        return result;
    }
}
