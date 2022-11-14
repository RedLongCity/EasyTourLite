package com.redlongcitywork.easytourlite.quartz.jobs;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.quartz.services.QuartzService;
import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.handler.save.SaveHandler;
import com.redlongcitywork.easytourlite.parsers.TourArrayNodeParser;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for updating elements in elements pull
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ShortUpdatingJob extends QuartzJobBean {

    private static final Logger LOG = Logger.getLogger(ShortUpdatingJob.class.getName());

    @Autowired
    AppConstants constants;

    @Autowired
    RequestPull requestPull;

    @Autowired
    ResponsePull responsePull;

    @Autowired
    QuartzService quartzService;
    
    @Autowired
    TourArrayNodeParser parser;

    @Autowired
    TimeUtils timeUtils;
    
    @Autowired
    SaveHandler handler;
    
    private long time;

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        LOG.log(Level.INFO, "ShortJob Doing");
        constants.setShortSuspended(false);
        constants.setShortRun(true);

        RequestCommand command = requestPull.getNextCommand();
        if (command != null) {
            command.setProcessed(true);
            ResponseItem item = responsePull.getEmptyResponseItem(
                    command.getRequest());
            if (item != null) {
                time = System.currentTimeMillis();
                command.execute(new HttpUtils.GetCallBack() {
                    @Override
                    public void onDataReceived(JsonNode node) {
                        requestPull.deleteCommand(command);
                        item.setAnswer(parser.parseNode(node));
                        item.setFreezeeTime(new Timestamp(
                                timeUtils.getCurrentTime().getTime()
                                + constants.getFreezzeeTimeDelay()));
                        item.setRevelance(new Timestamp(timeUtils.getCurrentTime().getTime()
                                + constants.getRevelance()));
                        item.setImmune(false);
                        LOG.log(Level.INFO, "Operation of extracting from server: "+(System.currentTimeMillis() - time));
                    }

                    @Override
                    public void onDataNotAwailable() {
                        item.setImmune(false);
                        command.setProcessed(false);
                        LOG.log(Level.WARNING, "Executing response command failed!");
                    }
                });

                constants.setGlobalDelay(true);
            }
        } else {
            constants.setGlobalDelay(false);
            constants.setShortSuspended(true);
            pauseItSelf(jec);
        }
    }

    private void pauseItSelf(JobExecutionContext jec) {
        constants.setShortSuspended(true);
        handler.saveData();
        quartzService.pauseShortJob();
    }
}
