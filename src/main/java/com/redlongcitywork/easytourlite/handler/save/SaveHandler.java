package com.redlongcitywork.easytourlite.handler.save;

import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import com.redlongcitywork.easytourlite.saver.Saver;
import com.redlongcitywork.easytourlite.saver.factory.SaverFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 24/12/2017
 * class for saving data to database
 */
@Service
public class SaveHandler {

    private static final Logger LOG = Logger.getLogger(SaveHandler.class.getName());

    @Autowired
    private ResponsePull pull;

    @Autowired
    private SaverFactory factory;

    public void saveData() {
        ResponseItem item = pull.getItemForSave();
        Saver saver = factory.getSaver(item);
        saver.save(item, new Saver.saveCallback() {
            @Override
            public void onSaved() {
                pull.deleteResponseItem(item);
            }

            @Override
            public void onNotSaved() {
                LOG.log(Level.WARNING, "Saving was failed!");
            }
        });
    }

}
