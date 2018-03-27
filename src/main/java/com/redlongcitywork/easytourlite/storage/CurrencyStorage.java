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
public class CurrencyStorage implements Storage<List<Currency>> {

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

}
