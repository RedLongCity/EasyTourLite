package com.redlongcitywork.easytourlite.storage;

import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.service.RegionService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 27/03/2018
 */
@Service
public class RegionStorage implements Storage<List<Region>, Region> {

    private final RegionService service;

    private List<Region> content;

    public RegionStorage(RegionService service) {
        this.service = service;
    }

    @Override
    public List<Region> getContent() {
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
    public Region findById(String id) {
        Region result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(id))
                    .findFirst()
                    .get();
        }
        return result;
    }

    @Override
    public Region findByName(String name) {
        Region result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(name))
                    .findFirst()
                    .get();
        }
        return result;
    }

}
