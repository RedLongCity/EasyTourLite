package com.redlongcitywork.easytourlite.converter;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 09.09.2017
 * class for handling operations with ItTours Hot Search Request
 */
@Service
public class HotSearchConverter implements RequestConverter<HotToursRequest>, 
        ItToursUrls {

    private static final Logger LOG = Logger.getLogger(HotSearchConverter.class.getName());

    public HotSearchConverter() {
    }

    @Autowired
    Hotel_RatingService service;

    @Override
    public List<Criterion> getCriterionsByRequest(HotToursRequest request) {
        List<Criterion> criterionsList = new ArrayList<Criterion>();

        Country country = request.getCountry();
        if (country != null) {
            criterionsList.add(Restrictions.eq("country", country));
        }

        From_Cities from_Cities = request.getFrom_Cities();
        if (from_Cities != null) {
            criterionsList.add(Restrictions.eq("from_Cities", from_Cities));
        }

        String hotel_Rating = request.getHotel_Rating();
        if (hotel_Rating == null) {
            LOG.log(Level.WARNING, "RequestHandlerService: Hotel rating is null");
            return criterionsList;
        }
        String[] hotel_RatingsArray = hotel_Rating.split(":", -1);
        if (hotel_RatingsArray.length == 0) {
            LOG.log(Level.WARNING, "RequestHandlerService: hotel_RatingsArray too short");
            return criterionsList;
        } else {
            if (hotel_RatingsArray.length == 1) {
                criterionsList.add(Restrictions.eq("hotel_Rating",
                        service.findById(hotel_RatingsArray[0])));
            } else {
                criterionsList.add(Restrictions.between("hotel_Rating",
                        service.findById(hotel_RatingsArray[0]),
                        service.findById(hotel_RatingsArray[1])));
            }
        }
        Integer night_From = request.getNight_From();
        if (night_From == null) {
            LOG.log(Level.WARNING, "RequestHandlerService: night_From is null");
            return criterionsList;
        }

        Integer night_Till = request.getNight_Till();
        if (night_Till == null) {
            LOG.log(Level.WARNING, "RequestHandlerService: night_Till is null");
            return criterionsList;
        }

        criterionsList.add(Restrictions.between("duration", night_From, night_Till));

        Meal_Type meal_Type = request.getMeal_Type();
        if (meal_Type != null) {
            criterionsList.add(Restrictions.eq("meal_Type", meal_Type));
        }
        
        Timestamp requestTime = request.getRequestTime();
        if(requestTime != null){
            criterionsList.add(Restrictions.ge("date_From", requestTime));
        }else{
            LOG.log(Level.WARNING,"RequestHandlerService: requestTime is null");
        }

        return criterionsList;
    }

    @Override
    public String getURLByRequest(HotToursRequest request) {
        String URL = api_base_url//http://api.ittour.com.ua/
                + api_showcases//showcase/hot-offers
                + api_showcases_search;// /search?

        Country country = request.getCountry();
        if (country != null) {
            String country_Id = country.getId();
            URL = URL.concat("country=" + country_Id + "&");
        }

        From_Cities from_Cities = request.getFrom_Cities();
        if (from_Cities != null) {
            String from_Cities_Id = from_Cities.getId();
            URL = URL.concat("from_city=" + from_Cities_Id + "&");
        }

        String hotel_Rating = request.getHotel_Rating();
        if (hotel_Rating == null) {
            LOG.log(Level.WARNING, "RequestHandlerService_URL: Hotel rating is null");
            return URL;
        }
        URL = URL.concat("hotel_rating=" + hotel_Rating + "&");

        Integer night_From = request.getNight_From();
        if (night_From == null) {
            LOG.log(Level.WARNING, "RequestHandlerService: night_From is null");
            return URL;
        }
        URL = URL.concat("night_from=" + night_From + "&");

        Integer night_Till = request.getNight_Till();
        if (night_Till == null) {
            LOG.log(Level.WARNING, "RequestHandlerService: night_Till is null");
            return URL;
        }
        URL = URL.concat("night_till=" + night_Till + "&");

        Meal_Type meal_Type = request.getMeal_Type();
        if (meal_Type != null) {
            String meal_Type_Id = meal_Type.getId();
            URL = URL.concat("meal_type=" + meal_Type_Id + "&");
        }
        URL = URL.concat("items_per_page=100&hotel_image=1");
        return URL;
    }

}
