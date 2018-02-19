package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.RegionDao;
import com.redlongcitywork.easytourlite.model.Region;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 14/02/2018
 */
@Service("regionService")
@Transactional
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao dao;

    @Override
    public Region findById(String id) {
        return dao.findById(id);
    }

    @Override
    public void saveRegion(Region region) {
        dao.save(region);
    }

    @Override
    public void updateRegion(Region region) {
        Region entity = dao.findById(region.getId());
        if (entity != null) {
            entity.setName(region.getName());
            dao.mergeRegion(region);
        }
    }

    @Override
    public void deleteRegion(Region region) {
        dao.deleteRegion(region);
    }

    @Override
    public List<Region> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllCountries() {
        List<Region> list = dao.findAll();
        if(list!=null){
            for(Region region:list){
                dao.deleteRegion(region);
            }
        }
    }

    @Override
    public void saveOrUpdateRegion(Region region) {
        dao.saveOrUpdateRegion(region);
    }

}
