package com.redlongcitywork.easytourlite.converter;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
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

    public static List<Criterion> getCriterionsByRequest(SearchingRequest request) {
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

        Set<Region> regions = request.getRegions();
        if (regions != null) {
            result.add(Restrictions.in("region", regions));
        }

        String ids = request.getHotel();
        if (ids != null) {
            result.add(Restrictions.in("hotelId", ids.split(",")));
        }

        Set<Hotel_Rating> ratings = request.getRatingSet();
        if (ratings != null && !ratings.isEmpty()) {
            result.add(Restrictions.in("rating", ratings));
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

    public static void addPriceCriteria(
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

    public static String getURLByRequest(SearchingRequest request) {
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
                        addPattern("currency", request.getCurrency().getId().toString(), flag)
                );
                flag = false;
            }

            if (request.getOnlyStandart() != null) {
                result = result.concat(
                        addPattern("only_standard_price", request.getOnlyStandart().toString(), flag)
                );
                flag = false;
            }

            if (request.getRegions() != null && !request.getRegions().isEmpty()) {
                result = result.concat(
                        addBeginning("region", flag)
                );
                flag = false;
                boolean eachFlag = true;
                for (Region region : request.getRegions()) {
                    result = result.concat(
                            addEndElement(region.getId(), eachFlag)
                    );
                    eachFlag = false;
                }
            }

            if (request.getRatingSet() != null && !request.getRatingSet().isEmpty()) {
                result = result.concat(
                        addBeginning("hotel_rating", flag)
                );
                flag = false;
                boolean eachFlag = true;
                for (Hotel_Rating rating : request.getRatingSet()) {
                    result = result.concat(
                            addEndElement(rating.getId(), eachFlag)
                    );
                    eachFlag = false;
                }
            }

            if (request.getMealTypes() != null && !request.getMealTypes().isEmpty()) {
                result = result.concat(
                        addBeginning("meal_type", flag)
                );
                flag = false;
                boolean eachFlag = true;
                for (Meal_Type type : request.getMealTypes()) {
                    result = result.concat(
                            addEndElement(type.getId(), eachFlag)
                    );
                    eachFlag = false;
                }
            }

        }
        return result;
    }

    private static String addPattern(String key, String value, boolean first) {
        if (first) {
            return key + "=" + value;
        } else {
            return "&" + key + "=" + value;
        }
    }

    private static String addBeginning(String key, boolean first) {
        if (first) {
            return key + "=";
        } else {
            return "&" + key + "=";
        }
    }

    private static String addEndElement(String key, boolean first) {
        if (first) {
            return key;
        } else {
            return ":" + key;
        }
    }

    private static Criterion[] getCriterionsForDateTill(Date dateTill) {
        List<Criterion> list = new ArrayList<>();
        for (int i = 1; i < 22; i++) {
            list.add(getDateTill(dateTill, i));
        }
        return list.toArray(new Criterion[list.size()]);
    }

    private static Criterion getDateTill(Date dateTill, int daysBefore) {
        calendar.setTime(dateTill);
        calendar.add(Calendar.DAY_OF_YEAR, -daysBefore);
        return Restrictions.and(Restrictions.eq("dateFrom", calendar.getTime()),
                Restrictions.eq("duration", daysBefore)
        );
    }
}
