package com.redlongcitywork.easytourlite.handler.save;

import com.redlongcitywork.easytourlite.constants.AppConstants;
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

    @Autowired
    private AppConstants constants;

    public void saveData() {
        if (constants.isSaving() || !constants.isShortSuspended()) {
            return;
        }
        ResponseItem item = pull.getItemForSave();

        if (item == null) {
            return;
        }

        constants.setSaving(true);
        Saver saver = factory.getSaver(item);
        saver.save(item, new Saver.saveCallback() {

            @Override
            public void onSaved() {
                pull.deleteResponseItem(item);
                constants.setSaving(false);
                saveData();
            }

            @Override
            public void onNotSaved() {
                constants.setSaving(false);
                LOG.log(Level.WARNING, "Saving was failed!");
            }
        });
    }

}
