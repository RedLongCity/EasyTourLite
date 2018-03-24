package com.redlongcitywork.easytourlite.quartz.jobs;

import com.redlongcitywork.easytourlite.miner.HotFiltersMiner;
import com.redlongcitywork.easytourlite.quartz.services.QuartzService;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.miner.FilterParamsMiner;
import com.redlongcitywork.easytourlite.miner.SearchParamsMiner;
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
 * @author redlongcity 10.09.2017 class for refreshing filters
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class GlobalUpdatingJob extends QuartzJobBean {

    private static final Logger LOG = Logger.getLogger(GlobalUpdatingJob.class.getName());

    private final AppConstants constants;

    private final QuartzService quartzService;

    private final HotFiltersMiner hotMiner;

    private final SearchParamsMiner searchMiner;

    private final FilterParamsMiner filterParamsMiner;

    public GlobalUpdatingJob(AppConstants constants, QuartzService quartzService, HotFiltersMiner hotMiner, SearchParamsMiner searchMiner, FilterParamsMiner filterParamsMiner) {
        this.constants = constants;
        this.quartzService = quartzService;
        this.hotMiner = hotMiner;
        this.searchMiner = searchMiner;
        this.filterParamsMiner = filterParamsMiner;
    }

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        LOG.log(Level.INFO, "Global Job Doing");
        constants.setGlobalSuspended(false);

        hotMiner.mine();
        searchMiner.mine();
        filterParamsMiner.mine();
    }

    private void resumeShortUpdateJob(JobExecutionContext jec) {
        quartzService.resumeShortJob();
    }

    private void pauseItSelf(JobExecutionContext jec) {
        constants.setGlobalSuspended(true);
        quartzService.pauseGlobalJob();
    }

}
