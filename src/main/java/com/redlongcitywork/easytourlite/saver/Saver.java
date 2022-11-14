package com.redlongcitywork.easytourlite.saver;

/**
 *
 * @author redlongcity
 * 24/12/2017
 * interface like fundament for next savers of tours
 */
public interface Saver<Answer> {
    
    void save(Answer answer, saveCallback callback);
    
    interface saveCallback{
        
        void onSaved();
        
        void onNotSaved();
    }
}
