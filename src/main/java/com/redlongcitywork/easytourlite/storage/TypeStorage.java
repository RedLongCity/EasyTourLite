package com.redlongcitywork.easytourlite.storage;

import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.service.TypeService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 27/03/2018
 */
@Service
public class TypeStorage implements Storage<List<Type>, Type> {

    private final TypeService service;

    private List<Type> content;

    public TypeStorage(TypeService service) {
        this.service = service;
    }

    @Override
    public List<Type> getContent() {
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
    public Type findById(String id) {
        Type result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getId()
                    .equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(result);
        }
        return result;
    }

    @Override
    public Type findByName(String name) {
        Type result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(result);
        }
        return result;
    }
}
