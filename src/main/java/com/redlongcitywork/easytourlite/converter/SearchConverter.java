package com.redlongcitywork.easytourlite.converter;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 02/03/2018
 */
@Service
public class SearchConverter implements RequestConverter<SearchingRequest>, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(SearchConverter.class.getName());

    @Override
    public List<Criterion> getCriterionsByRequest(SearchingRequest request) {
        List<Criterion> result = new ArrayList<>();
        return result;
    }

    @Override
    public String getURLByRequest(SearchingRequest request) {
        String result = null;
        return result;
    }

}
