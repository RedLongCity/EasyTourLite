package com.redlongcitywork.easytourlite.convertor;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 02/03/2018
 */
@Service
public class SearchConvertor implements ItToursUrls {

    private static final Logger LOG = Logger.getLogger(SearchConvertor.class.getName());

    private static Calendar calendar = Calendar.getInstance();

//    private final MealTypeStorage mealTypeStorage;
//
//    private final RegionStorage regionStorage;
//
//    private final HotelRatingStorage ratingStorage;
//
//    public SearchConvertor(
//            MealTypeStorage mealTypeStorage,
//            RegionStorage regionStorage,
//            HotelRatingStorage ratingStorage) {
//        this.mealTypeStorage = mealTypeStorage;
//        this.regionStorage = regionStorage;
//        this.ratingStorage = ratingStorage;
//    }

    public List<Criterion> getCriterionsByRequest(SearchingRequest request) {
        List<Criterion> result = new ArrayList<>();

        Country country = request.getCountry();
        if (country != null) {
            result.add(Restrictions.eq("country", country));
        }

        Type type = request.getType();
        if (type != null) {
            result.add(Restrictions.eq("type", type));
        }

        Integer kind = request.getKind();
        if (kind != null) {
            switch (kind) {
                case 1:
                    result.add(Restrictions.eq("transportType", "flight"));
                    break;
                case 2:
                    result.add(Restrictions.eq("transportType", "bus"));
                    break;
                default:
                    result.add(Restrictions.eq("transportType", "flight"));
            }
        }

        From_Cities city = request.getCity();
        if (city != null) {
            result.add(Restrictions.eq("city", city));
        }

        String ids = request.getHotel();
        if (ids != null) {
            result.add(Restrictions.in("hotelId", ids.split(",")));
        }

//        String regions = request.getRegions();
//        if (regions != null) {
//            List<String> list = Arrays.asList(regions.split(":"));
//            result.add(Restrictions.in(
//                    "region",
//                    list.stream()
//                            .map((p) -> regionStorage.findById(p))
//                            .collect(Collectors.toList())));
//        }
        String regions = request.getRegions();
        if (regions != null) {
            List<String> list = Arrays.asList(regions.split(":"));
            result.add(Restrictions.in(
                    "region",
                    list.stream()
                            .map((p) -> new Region(p))
                            .collect(Collectors.toList())));
        }

//        String ratings = request.getRatings();
//        if (ratings != null) {
//            List<String> list = Arrays.asList(ratings.split(":"));
//            result.add(Restrictions.in(
//                    "rating",
//                    list.stream()
//                            .map((p) -> ratingStorage.findById(p))
//                            .collect(Collectors.toList())));
//        }
        String ratings = request.getRatings();
        if (ratings != null) {
            List<String> list = Arrays.asList(ratings.split(":"));
            result.add(Restrictions.in(
                    "rating",
                    list.stream()
                            .map((p) -> new Hotel_Rating(p))
                            .collect(Collectors.toList())));
        }

//        String mealTypes = request.getMealTypes();
//        if (mealTypes != null) {
//            List<String> list = Arrays.asList(ratings.split(":"));
//            result.add(Restrictions.in(
//                    "mealType",
//                    list.stream()
//                            .map((p) -> mealTypeStorage.findById(p))
//                            .collect(Collectors.toList())));
//        }
        String mealTypes = request.getMealTypes();
        if (mealTypes != null) {
            List<String> list = Arrays.asList(ratings.split(":"));
            result.add(Restrictions.in(
                    "mealType",
                    list.stream()
                            .map((p) -> new Meal_Type(p))
                            .collect(Collectors.toList())));
        }

        Integer adultAmount = request.getAdultAmount();
        if (adultAmount != null) {
            result.add(Restrictions.eq("adultAmount", adultAmount));
        }

        Integer childAmount = request.getChildAmount();
        if (childAmount != null) {
            result.add(Restrictions.eq("childAmount", childAmount));
        }

        Integer nightFrom = request.getNightFrom();
        if (nightFrom != null) {
            result.add(Restrictions.ge("duration", nightFrom));
        }

        Integer nightTill = request.getNightTill();
        if (nightFrom != null) {
            result.add(Restrictions.le("duration", nightTill));
        }

        Date dateFrom = request.getDateFrom();
        if (dateFrom != null) {
            result.add(Restrictions.ge("dateFrom", dateFrom));
        }

        Date dateTill = request.getDateTill();
        if (dateTill != null) {
            result.add(Restrictions.disjunction(getCriterionsForDateTill(dateTill)));
        }

        return result;
    }

    public void addPriceCriteria(
            Criteria crit,
            Integer priceFrom,
            Integer priceTill,
            Currency currency) {
        if (currency == null || (priceFrom == null && priceTill == null)) {
            LOG.warning("Currency is null or both of prices are null");
        }
        if (priceFrom != null && priceTill != null) {
            Criteria c2 = crit.createCriteria("prices");
            c2.add(Restrictions.between("cost", priceFrom, priceTill));
            c2.add(Restrictions.eq("currency", currency));
        } else {
            if (priceFrom != null && priceTill == null) {
                Criteria c2 = crit.createCriteria("prices");
                c2.add(Restrictions.ge("cost", priceFrom));
                c2.add(Restrictions.eq("currency", currency));
            } else {
                Criteria c2 = crit.createCriteria("prices");
                c2.add(Restrictions.le("cost", priceTill));
                c2.add(Restrictions.eq("currency", currency));
            }
        }
    }

    public String getURLByRequest(SearchingRequest request) {
        String result = null;
        if (request != null) {
            result = new String("?");
            boolean flag = true;

            if (request.getType() != null) {
                result = result.concat(
                        addPattern("type", request.getType().getId(), flag)
                );
                flag = false;
            }

            if (request.getKind() != null) {
                result = result.concat(
                        addPattern("kind", request.getKind().toString(), flag)
                );
                flag = false;
            }

            if (request.getCountry() != null) {
                result = result.concat(
                        addPattern("country", request.getCountry().getId(), flag));
                flag = false;
            }

            if (request.getCity() != null) {
                result = result.concat(
                        addPattern("from_city", request.getCity().getId(), flag)
                );
                flag = false;
            }

            if (request.getHotel() != null) {
                result = result.concat(
                        addPattern("hotel", request.getHotel(), flag)
                );
                flag = false;
            }

            if (request.getAdultAmount() != null) {
                result = result.concat(
                        addPattern("adult_amount", request.getAdultAmount().toString(), flag)
                );
                flag = false;
            }

            if (request.getChildAmount() != null) {
                result = result.concat(
                        addPattern("child_amount", request.getChildAmount().toString(), flag)
                );
                flag = false;
            }

            if (request.getChildAge() != null) {
                result = result.concat(
                        addPattern("child_age", request.getChildAge(), flag)
                );
                flag = false;
            }

            if (request.getNightFrom() != null) {
                result = result.concat(
                        addPattern("night_from", request.getNightFrom().toString(), flag)
                );
                flag = false;
            }

            if (request.getNightTill() != null) {
                result = result.concat(
                        addPattern("night_till", request.getNightTill().toString(), flag)
                );
                flag = false;
            }
            SimpleDateFormat formatter = null;
            if (request.getDateFrom() != null || request.getDateTill() != null) {
                formatter = new SimpleDateFormat("dd.MM.yy");
            }

            if (request.getDateFrom() != null) {
                result = result.concat(
                        addPattern(
                                "date_from",
                                formatter.format(request.getDateFrom()),
                                flag)
                );
                flag = false;
            }

            if (request.getDateTill() != null) {
                result = result.concat(
                        addPattern(
                                "date_till",
                                formatter.format(request.getDateTill()),
                                flag)
                );
                flag = false;
            }

            if (request.getPriceFrom() != null) {
                result = result.concat(
                        addPattern("price_from", request.getPriceFrom().toString(), flag)
                );
                flag = false;
            }

            if (request.getPriceTill() != null) {
                result = result.concat(
                        addPattern("price_till", request.getPriceTill().toString(), flag)
                );
                flag = false;
            }

            if (request.getCurrency() != null) {
                result = result.concat(
                        addPattern("currency", request.getCurrency().getId(), flag)
                );
                flag = false;
            }

            if (request.getOnlyStandart() != null) {
                result = result.concat(
                        addPattern("only_standard_price", request.getOnlyStandart().toString(), flag)
                );
                flag = false;
            }

            if (request.getRegions() != null) {
                result = result.concat(
                        addPattern("region", request.getRegions(), flag)
                );
                flag = false;
            }

            if (request.getRatings() != null) {
                result = result.concat(
                        addPattern("hotel_rating", request.getRatings(), flag)
                );
                flag = false;
            }

            if (request.getMealTypes() != null) {
                result = result.concat(
                        addPattern("meal_type", request.getMealTypes(), flag)
                );
                flag = false;
            }

        }
        return result;
    }

    public List<Criterion> getRequestCriterions(SearchingRequest request) {
        List<Criterion> result = null;
        if (request != null) {
            result = new ArrayList<Criterion>();
            Type type = request.getType();
            if (type != null) {
                result.add(Restrictions.eq("type", type));
            } else {
                result.add(Restrictions.isNull("type"));
            }

            Integer kind = request.getKind();
            if (kind != null) {
                result.add(Restrictions.eq("kind", kind));
            } else {
                result.add(Restrictions.isNull("kind"));
            }

            Country country = request.getCountry();
            if (country != null) {
                result.add(Restrictions.eq("country", country));
            } else {
                result.add(Restrictions.isNull("country"));
            }

            From_Cities city = request.getCity();
            if (city != null) {
                result.add(Restrictions.eq("city", city));
            } else {
                result.add(Restrictions.isNull("city"));
            }

            String regions = request.getRegions();
            if (regions != null) {
                result.add(Restrictions.eq("regions", regions));
            } else {
                result.add(Restrictions.isEmpty("regions"));//Проверить
            }

            String hotel = request.getHotel();
            if (hotel != null) {
                result.add(Restrictions.eq("hotel", hotel));
            } else {
                result.add(Restrictions.isNull("hotel"));
            }

            String ratings = request.getRatings();
            if (ratings != null) {
                result.add(Restrictions.eq("ratings", ratings));
            } else {
                result.add(Restrictions.isEmpty("ratings"));
            }

            Integer adultAmount = request.getAdultAmount();
            if (adultAmount != null) {
                result.add(Restrictions.eq("adultAmount", adultAmount));
            } else {
                result.add(Restrictions.isNull("adultAmount"));
            }

            Integer childAmount = request.getChildAmount();
            if (childAmount != null) {
                result.add(Restrictions.eq("childAmount", childAmount));
            } else {
                result.add(Restrictions.isNull("childAmount"));
            }

            String childAge = request.getChildAge();
            if (childAge != null) {
                result.add(Restrictions.eq("childAge", childAge));
            } else {
                result.add(Restrictions.isNull("childAge"));
            }

            Integer nightFrom = request.getNightFrom();
            if (nightFrom != null) {
                result.add(Restrictions.eq("nightFrom", nightFrom));
            } else {
                result.add(Restrictions.isNull("nightFrom"));
            }

            Integer nightTill = request.getNightTill();
            if (nightTill != null) {
                result.add(Restrictions.eq("nightTill", nightTill));
            } else {
                result.add(Restrictions.isNull("nightTill"));
            }

            Date dateFrom = request.getDateFrom();
            if (dateFrom != null) {
                result.add(Restrictions.eq("dateFrom", dateFrom));
            } else {
                result.add(Restrictions.isNull("dateFrom"));
            }

            Date dateTill = request.getDateTill();
            if (dateTill != null) {
                result.add(Restrictions.eq("dateTill", dateTill));
            } else {
                result.add(Restrictions.isNull("dateTill"));
            }

            String mealTypes = request.getMealTypes();
            if (ratings != null) {
                result.add(Restrictions.eq("mealTypes", mealTypes));
            } else {
                result.add(Restrictions.isNull("mealTypes"));
            }

            Integer priceFrom = request.getPriceFrom();
            if (priceFrom != null) {
                result.add(Restrictions.eq("priceFrom", priceFrom));
            } else {
                result.add(Restrictions.isNull("priceFrom"));
            }

            Integer priceTill = request.getPriceTill();
            if (priceTill != null) {
                result.add(Restrictions.eq("priceTill", priceTill));
            } else {
                result.add(Restrictions.isNull("priceTill"));
            }

            Currency currency = request.getCurrency();
            if (currency != null) {
                result.add(Restrictions.eq("currency", currency));
            } else {
                result.add(Restrictions.isNull("currency"));
            }

            Integer onlyStandart = request.getOnlyStandart();
            if (onlyStandart != null) {
                result.add(Restrictions.eq("onlyStandart", onlyStandart));
            } else {
                result.add(Restrictions.isNull("onlyStandart"));
            }
        }

        return result;
    }

    private String addPattern(String key, String value, boolean first) {
        if (first) {
            return key + "=" + value;
        } else {
            return "&" + key + "=" + value;
        }
    }

    private String addBeginning(String key, boolean first) {
        if (first) {
            return key + "=";
        } else {
            return "&" + key + "=";
        }
    }

    private String addEndElement(String key, boolean first) {
        if (first) {
            return key;
        } else {
            return ":" + key;
        }
    }

    private Criterion[] getCriterionsForDateTill(Date dateTill) {
        List<Criterion> list = new ArrayList<>();
        for (int i = 1; i < 22; i++) {
            list.add(getDateTill(dateTill, i));
        }
        return list.toArray(new Criterion[list.size()]);
    }

    private Criterion getDateTill(Date dateTill, int daysBefore) {
        calendar.setTime(dateTill);
        calendar.add(Calendar.DAY_OF_YEAR, -daysBefore);
        return Restrictions.and(Restrictions.eq("dateFrom", calendar.getTime()),
                Restrictions.eq("duration", daysBefore)
        );
    }
}
