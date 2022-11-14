package com.redlongcitywork.easytourlite.controller;

import com.redlongcitywork.easytourlite.quartz.services.QuartzService;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.utils.GlobalUpdateDelayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author redlongcity
 * 17/09/2017
 * controller for configurating and analyse server 
 */
@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    QuartzService quartzService;

    @Autowired
    AppConstants constatns;

    @Autowired
    GlobalUpdateDelayUtils delayUtils;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getSearchPage() {
        return "search";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String getStatisticsPage() {
        return "statistics";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String getSettingsPage() {
        return "settings";
    }

    @RequestMapping(value = "/stopshort", method = RequestMethod.GET)
    public @ResponseBody
    void stopShort() {
        constatns.setShortRun(false);
        quartzService.pauseShortJob();
    }

    @RequestMapping(value = "/resumeshort", method = RequestMethod.GET)
    public @ResponseBody
    void resumeShort() {
        constatns.setShortRun(true);
        quartzService.resumeShortJob();
    }

    @RequestMapping(value = "/setshortdelay", method = RequestMethod.GET)
    public @ResponseBody
    void setShortDelay(@RequestParam("delay") Long delay) {
        constatns.setShortUpdatingDelay(delay);
        quartzService.updateShortTrigger(delay);
    }

    @RequestMapping(value = "/stopglobal", method = RequestMethod.GET)
    public @ResponseBody
    void stopGlobal() {
        constatns.setGlobalRun(false);
        quartzService.pauseGlobalJob();
    }

    @RequestMapping(value = "/resumeglobal", method = RequestMethod.GET)
    public @ResponseBody
    void resumeGlobal() {
        constatns.setGlobalRun(true);
        quartzService.resumeGlobalJob();
    }

    @RequestMapping(value = "/setglobaldelay", method = RequestMethod.GET)
    public @ResponseBody
    void setGlobalDelay(@RequestParam("delay") Integer delay) {
        constatns.setGlobalUpdatingDelay(delayUtils.getMachineData(delay));
        quartzService.updateGlobalTrigger(delayUtils.getMachineData(delay));
    }

    @RequestMapping(value = "/setrequestpullsize", method = RequestMethod.GET)
    public @ResponseBody
    void setRequestPullSize(@RequestParam("size") int size) {
        constatns.setRequestPullSize(size);
    }

    @RequestMapping(value = "/setresponsepullsize", method = RequestMethod.GET)
    public @ResponseBody
    void setResponsePullSize(@RequestParam("size") int size) {
        constatns.setResponsePullSize(size);
    }

    @RequestMapping(value = "/setfreezzeetime", method = RequestMethod.GET)
    public @ResponseBody
    void setFreezzeeTime(@RequestParam("time") long size) {
        constatns.setFreezzeeTimeDelay(size);
    }

    @RequestMapping(value = "/setrevelance", method = RequestMethod.GET)
    public @ResponseBody
    void setRevelance(@RequestParam("revelance") int revelance) {
        constatns.setRevelance(revelance);
    }

}
