package com.redlongcitywork.easytourlite.quartz.jobs;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.extractor.HotFiltersExtractor;
import com.redlongcitywork.easytourlite.quartz.services.QuartzService;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
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
 * class for refreshing filters
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class GlobalUpdatingJob extends QuartzJobBean {
    
    private static final Logger LOG = Logger.getLogger(GlobalUpdatingJob.class.getName());
    
    @Autowired
    AppConstants constants;
    
    @Autowired
    QuartzService quartzService;
    
    @Autowired
    HotFiltersExtractor extractor;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        LOG.log(Level.INFO, "Global Job Doing");
        constants.setGlobalSuspended(false);
        
        extractor.extract(new HttpUtils.GetCallBack() {
            @Override
            public void onDataReceived(JsonNode node) {
                LOG.log(Level.INFO, "Hot Tours Filters were extracting successfully");
            }
            
            @Override
            public void onDataNotAwailable() {
                LOG.log(Level.WARNING, "Hot Tours extracting was failed!");
            }
        });
        //pauseItSelf(jec);
    }
    
    private void resumeShortUpdateJob(JobExecutionContext jec) {
        quartzService.resumeShortJob();
    }
    
    private void pauseItSelf(JobExecutionContext jec) {
        constants.setGlobalSuspended(true);
        quartzService.pauseGlobalJob();
    }
    
}
