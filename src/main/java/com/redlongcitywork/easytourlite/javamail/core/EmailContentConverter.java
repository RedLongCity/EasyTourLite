package com.redlongcitywork.easytourlite.javamail.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.extractor.TourExtractor;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.FeedBack;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.MailAddress;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Order;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.UserData;
import com.redlongcitywork.easytourlite.parsers.TourNodeParser;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.MailAddressService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity class for converting Order object to Email content
 */
@Service
public class EmailContentConverter {

    private static final Logger LOG = Logger.getLogger(EmailContentConverter.class.getName());

    @Autowired
    MailAddressService mailService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private Meal_TypeService typeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private Hotel_RatingService ratingService;

    @Autowired
    private TourExtractor extractor;

    public EmailContentConverter() {
    }

    public void getMessage(Order order, GetMessageCallBack callback) {

        if (order == null) {
            callback.onDataNotAwailable();
            return;
        }

        String tourKey = order.getKey();

        if (tourKey == null || tourKey.length() == 0) {
            UserData data = order.getData();
            if (data == null) {
                callback.onDataNotAwailable();
            } else {
                callback.onDataReceived(convertUserData(data));
            }
        } else {
            extractor.extract(tourKey, new HttpUtils.GetCallBack() {
                @Override
                public void onDataReceived(JsonNode node) {
                    TourNodeParser parser = new TourNodeParser(
                            countryService.findAll(),
                            cityService.findAll(),
                            typeService.findAll(),
                            ratingService.findAll(),
                            currencyService.findAll());

                    if (parser != null) {
                        Tour tour = parser.parseNode(node);
                        UserData data = order.getData();

                        String message = "";
                        message = message.concat(convertUserData(data));
                        message = message.concat(convertTour(tour));

                        callback.onDataReceived(message);
                    }
                }

                @Override
                public void onDataNotAwailable() {
                    callback.onDataNotAwailable();
                }
            });
        }
    }

    public String getAddresses() {
        String addresses = "";
        List<MailAddress> list = mailService.findAll();
        if (list == null) {
            return "redlongcity@gmail.com";
        }

        for (MailAddress address : list) {
            addresses = addresses.concat(address.getEmailAddress() + ", ");
        }
        return addresses;
    }

    public String getFeedBack(FeedBack feedBack) {
        String message = "";
        message = message.concat("Данные пользователя: " + "\n");
        message = message.concat("Имя: " + feedBack.getName() + "\n");
        message = message.concat("Email: " + feedBack.getEmail() + "\n");
        message = message.concat("Device: " + feedBack.getDevice() + "\n");
        message = message.concat("FeedBack: " + feedBack.getFeedBack() + "\n");
        return message;
    }

    private String convertUserData(UserData data) {
        String message = "";

        if (data == null) {
            return message;
        }

        Date date = new Date();
        message = message.concat(date.toString() + "\n\n");
        message = message.concat("Данные пользователя: " + "\n");
        message = message.concat("Имя: " + data.getName() + "\n");
        message = message.concat("Email: " + data.getEmail() + "\n");
        message = message.concat("Телефонный номер: " + data.getMobileNumber() + "\n");
        message = message.concat("Город: " + data.getMobileNumber() + "\n\n");
        return message;
    }

    private String convertTour(Tour tour) {
        String message = "";

        if (tour == null) {
            return message;
        }

        message = message.concat("Данные тура: \n");
        message = message.concat("key: " + tour.getKey() + "\n");

        Country country = tour.getCountry();
        if (country != null) {
            message = message.concat("Страна: " + country.getName() + "\n");
        }

        From_Cities city = tour.getFrom_Cities();
        if (city != null) {
            message = message.concat("Из города: " + city.getName() + "\n");
        }

        message = message.concat("Регион: " + tour.getRegion() + "\n");

        Hotel_Rating rating = tour.getHotel_Rating();
        if (rating != null) {
            message = message.concat("Рейтинг отеля: " + rating.getName() + "\n");
        }

        message = message.concat("Отель: " + tour.getHotel() + "\n");

        Meal_Type type = tour.getMeal_Type();
        if (type != null) {
            message = message.concat("Тип питания: " + type.getName_Full() + "\n");
        }

        message = message.concat("Room Type: " + tour.getRoom_Type() + "\n");
        message = message.concat("Взрослых: " + tour.getAdult_Amount() + "\n");
        message = message.concat("Детей: " + tour.getChild_Amount() + "\n");
        message = message.concat("Accomodation: " + tour.getAccomodation() + "\n");
        message = message.concat("Продолжительность: " + tour.getDuration() + "\n");
        message = message.concat("Дата с: " + tour.getDate_From() + "\n\n");
        message = message.concat("Цены: \n");

        Set<Price> set = tour.getPrices();
        if (set != null) {
            Iterator<Price> it = set.iterator();
            while (it.hasNext()) {
                Price price = it.next();
                Currency currency = price.getCurrency();
                if (currency != null) {
                    message = message.concat("Валюта: " + currency.getName()
                            + " Цена: " + price.getCost() + "\n");
                }
            }
        }

        return message;
    }

    public interface GetMessageCallBack {

        void onDataReceived(String message);

        void onDataNotAwailable();
    }
}
