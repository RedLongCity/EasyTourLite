package com.redlongcitywork.easytourlite.update;

import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.miner.FilterParamsMiner;
import com.redlongcitywork.easytourlite.miner.HotFiltersMiner;
import com.redlongcitywork.easytourlite.miner.SearchParamsMiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 30/03/2018
 */
@Service
public class GlobalUpdateBean implements UpdateBean {

    private static final Logger LOG = Logger.getLogger(GlobalUpdateBean.class.getName());

    private final AppConstants constants;

    private final HotFiltersMiner hotMiner;

    private final SearchParamsMiner searchMiner;

    private final FilterParamsMiner filterParamsMiner;

    public GlobalUpdateBean(AppConstants constants, HotFiltersMiner hotMiner, SearchParamsMiner searchMiner, FilterParamsMiner filterParamsMiner) {
        this.constants = constants;
        this.hotMiner = hotMiner;
        this.searchMiner = searchMiner;
        this.filterParamsMiner = filterParamsMiner;
    }

    @Override
    @Scheduled(cron = "0/5 * * * * ?")
    public void update() {
        LOG.log(Level.INFO, "New Global Job Doing");
        constants.setGlobalSuspended(false);

        hotMiner.mine();
        searchMiner.mine();
        filterParamsMiner.mine();
    }

}
