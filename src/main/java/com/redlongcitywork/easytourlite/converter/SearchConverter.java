package com.redlongcitywork.easytourlite.converter;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.sql.Date;
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
public class SearchConverter implements ItToursUrls {

    private static final Logger LOG = Logger.getLogger(SearchConverter.class.getName());

    private Calendar calendar = Calendar.getInstance();

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

        Region region = request.getRegion();
        if (region != null) {
            result.add(Restrictions.eq("region", region));
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
        return result;
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
