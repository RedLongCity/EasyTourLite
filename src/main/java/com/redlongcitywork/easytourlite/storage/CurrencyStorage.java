package com.redlongcitywork.easytourlite.storage;

import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 27/03/2018
 */
@Service
public class CurrencyStorage implements Storage<List<Currency>, Currency> {

    private final CurrencyService service;

    private List<Currency> content;

    public CurrencyStorage(CurrencyService service) {
        this.service = service;
    }

    @Override
    public List<Currency> getContent() {
        if (content == null) {
            updateStorage();
        }
        return content;
    }

    @Override
    public void updateStorage() {
        content = service.findAll();
    }

    @Override
    public Currency findById(String id) {
        Currency result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getId()
                    .equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(result);
        }
        return result;
    }

    @Override
    public Currency findByName(String name) {
        Currency result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(result);
        }
        return result;
    }

}
