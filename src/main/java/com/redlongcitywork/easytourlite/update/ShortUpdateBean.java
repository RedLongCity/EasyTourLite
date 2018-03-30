package com.redlongcitywork.easytourlite.update;

import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.handler.request.CommonRequestHandler;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.saver.CommonSaver;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 30/03/2018
 */
@Service
public class ShortUpdateBean implements UpdateBean {

    private static final Logger LOG = Logger.getLogger(ShortUpdateBean.class.getName());

    private final CommonRequestHandler handler;

    private final AppConstants constants;

    private final RequestPull requestPull;

    private final ResponsePull responsePull;

    private final CommonSaver saver;

    public ShortUpdateBean(CommonRequestHandler handler, AppConstants constants, RequestPull requestPull, ResponsePull responsePull, CommonSaver saver) {
        this.handler = handler;
        this.constants = constants;
        this.requestPull = requestPull;
        this.responsePull = responsePull;
        this.saver = saver;
    }

    @Override
    @Scheduled(cron = "0/1 * * * * ?")
    public void update() {
        if (constants.isShortFire()) {
            LOG.log(Level.INFO, "New ShortJob Doing");
            constants.setShortFire(false);
            RequestCommand command = requestPull.getNextCommand();
            if (command != null) {
                command.setProcessed(true);
                ResponseItem item = responsePull.getEmptyResponseItem(
                        command.getRequest());
                if (item != null) {
                    ResponseItem newItem = handler.execute(command);
                    if (newItem != null) {
                        item.setAnswer(newItem.getAnswer());
                        item.setFreezeTime(newItem.getFreezeTime());
                        item.setImmune(newItem.isImmune());
                        item.setPriority(newItem.getPriority());
                        item.setRequest(newItem.getRequest());
                        item.setRevelance(newItem.getRevelance());
                        item.setTime(newItem.getTime());

                        requestPull.deleteCommand(command);
                    } else {
                        item.setImmune(false);
                        command.setProcessed(false);
                        LOG.log(Level.WARNING, "Executing response command failed!");
                    }
                    constants.setShortFire(true);
                }
            } else {
                constants.setShortFire(true);
                saveData();
            }
        }
    }

    private void saveData() {
        List<ResponseItem> list = responsePull.getItemsForSave();
        if (list != null) {
            for (ResponseItem item : list) {
                if (constants.isSaving() || !constants.isShortFire()) {
                    return;
                }
                saver.save(item);
            }
            constants.setSaving(false);
        }
    }

}
