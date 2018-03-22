package com.redlongcitywork.easytourlite.quartz.jobs;

import com.redlongcitywork.easytourlite.quartz.services.QuartzService;
import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.handler.request.CommonRequestHandler;
import com.redlongcitywork.easytourlite.handler.save.SaveHandler;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity 10.09.2017 class for updating elements in elements pull
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ShortUpdatingJob extends QuartzJobBean {

    private static final Logger LOG = Logger.getLogger(ShortUpdatingJob.class.getName());

    private final CommonRequestHandler handler;

    private final AppConstants constants;

    private final RequestPull requestPull;

    private final ResponsePull responsePull;

    private final QuartzService quartzService;

    private final SaveHandler saveHandler;

    public ShortUpdatingJob(
            CommonRequestHandler handler,
            AppConstants constants,
            RequestPull requestPull,
            ResponsePull responsePull,
            QuartzService quartzService,
            SaveHandler saveHandler) {
        this.handler = handler;
        this.constants = constants;
        this.requestPull = requestPull;
        this.responsePull = responsePull;
        this.quartzService = quartzService;
        this.saveHandler = saveHandler;
    }

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
        saveHandler.saveData();
        quartzService.pauseShortJob();
    }
}
