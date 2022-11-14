package com.redlongcitywork.easytourlite.quartz.services;

import com.redlongcitywork.easytourlite.constants.AppConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronScheduleBuilder;
import static org.quartz.JobKey.jobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import static org.quartz.TriggerKey.triggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 12.09.2017
 * class for quartz manipulations
 */
@Service("quartzService")
public class QuartzServiceImpl implements QuartzService {

    private static final Logger LOG = Logger.getLogger(QuartzServiceImpl.class.getName());

    @Autowired
    Scheduler scheduler;
    
    @Autowired
    AppConstants constants;
    
    
    @Override
    public void updateShortTrigger(Long RepeatInterval) {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        Trigger oldTrigger = null;
        try {
             oldTrigger = scheduler.getTrigger(triggerKey("shortTrigger","quartzTriggers"));
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
        Trigger newTrigger = tb.withSchedule(SimpleScheduleBuilder.
        simpleSchedule().withIntervalInMilliseconds(RepeatInterval).
                repeatForever()).build();
        try {
            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateGlobalTrigger(String cronExpressions) {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
                Trigger oldTrigger = null;
        try {
            oldTrigger = scheduler.getTrigger(triggerKey("globalTrigger","quartzTriggers"));
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(oldTrigger==null){
           LOG.log(Level.WARNING,"oldTrigger is null");
           return;
        }
        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
        Trigger newTrigger = tb.withSchedule(CronScheduleBuilder.
                cronSchedule(cronExpressions)).build();
        try {
            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void pauseJob(String jobName,String jobGroup){
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        try {
            scheduler.pauseJob(jobKey(jobName,jobGroup));
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pauseGlobalJob() {
    pauseJob("globalJob", "quartzJobs");
    }

    @Override
    public void pauseShortJob() {
    pauseJob("shortJob", "quartzJobs");
    }
    
    

    @Override
    public void resumeJob(String jobName,String jobGroup){
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
        return;
        }
        try {
            scheduler.resumeJob(jobKey(jobName,jobGroup));
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void resumeGlobalJob() {
        if(constants.isGlobalRun()){
        resumeJob("globalJob", "quartzJobs");
        }
    }

    @Override
    public void resumeShortJob() {
        if(constants.isShortRun()){
        resumeJob("shortJob", "quartzJobs");
        }
    }
    
    
    
    @Override
    public void resumeAll() {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        try {
            scheduler.resumeAll();
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pauseAll() {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        try {
            scheduler.pauseAll();
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shutDown() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
