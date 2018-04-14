package com.redlongcitywork.easytourlite.storage;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.service.CountryService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 27/03/2018
 */
@Service
public class CountryStorage implements Storage<List<Country>, Country> {

    private final CountryService service;

    private List<Country> content;

    public CountryStorage(CountryService service) {
        this.service = service;
    }

    @Override
    public List<Country> getContent() {
        if (content == null) {
            updateStorage();
        }
        return content;
    }

    @Override
    public void updateStorage() {
        content = service.findAll();
    }

    public Country findById(String id) {
        Country result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(id))
                    .findFirst()
                    .get();
        }
        return result;
    }

    public Country findByName(String name) {
        Country result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(name))
                    .findFirst()
                    .get();
        }
        return result;
    }

}
