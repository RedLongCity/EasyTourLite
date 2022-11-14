package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 09.09.2017
 * class for parsing Currency.class from JsonNode
 */
@Service
public class CurrencyNodeParser implements NodeParser<Currency> {

    private static final Logger LOG = Logger.getLogger(CurrencyNodeParser.class.getName());

    @Override
    public Currency parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "CurrencyNode: currencyNode is missing");
            return null;
        }
        Currency currency = new Currency();

        if (jsonNode.has("id")) {
            currency.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            currency.setName(jsonNode.path("name").asText());
        }

        return currency;
    }

}
