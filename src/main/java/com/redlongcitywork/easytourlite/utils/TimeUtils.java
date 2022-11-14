package com.redlongcitywork.easytourlite.utils;

import com.redlongcitywork.easytourlite.constants.AppConstants;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for getting data about current time
 */
@Service
public class TimeUtils {

    @Autowired
    private AppConstants constants;

    public Timestamp getCurrentTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        return time;
    }

    public Timestamp getRevelanceTime() {
        return new Timestamp(System.currentTimeMillis()
                - constants.getRevelance() * 3600000);
    }

}
