package com.redlongcitywork.easytourlite.storage;

import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 27/03/2018
 */
@Service
public class CityStorage implements Storage<List<From_Cities>, From_Cities> {

    private final From_CitiesService service;

    private List<From_Cities> content;

    public CityStorage(From_CitiesService service) {
        this.service = service;
    }

    @Override
    public List<From_Cities> getContent() {
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
    public From_Cities findById(String id) {
        From_Cities result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getId()
                    .equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(result);
        }
        return result;
    }

    @Override
    public From_Cities findByName(String name) {
        From_Cities result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(result);
        }
        return result;
    }
}
