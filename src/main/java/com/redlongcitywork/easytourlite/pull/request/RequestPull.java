package com.redlongcitywork.easytourlite.pull.request;

import com.redlongcitywork.easytourlite.requestcommand.RequestCommand;
import com.redlongcitywork.easytourlite.singletons.AppConstants;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author redlongcity
 * 13/12/2017
 * class for keeping and handling request elements
 */
@Service
public class RequestPull {

    private static final Logger LOG = Logger.getLogger(RequestPull.class.getName());

    private CopyOnWriteArrayList<RequestCommand> pull;
    
    @Autowired
    private AppConstants constants;


    public void handleCommand(RequestCommand command) {
        if (pull == null) {
            pull = new CopyOnWriteArrayList<RequestCommand>();
        }

        if (command == null) {
            LOG.log(Level.WARNING, "Null command!");
            return;
        }

        if (pull.contains(command)) {
            increasePriority(command);
            return;
        }

        if (pull.size() < constants.getRequestPullSize()) {
            pull.add(command);
            return;
        } 
    }

    public RequestCommand getNextCommand() {
        if (pull == null) {
            return null;
        }
        
        RequestCommand command = null;
        
        Iterator<RequestCommand> it = pull.iterator();
        while(it.hasNext()){
            RequestCommand insideCommand = it.next();
            
            if(command == null){
                command = insideCommand;
                continue;
            }
            
            if(command.getPriority() <= insideCommand.getPriority() &&
                    command.getCreationTime().after(insideCommand.getCreationTime())){
                command = insideCommand;
                continue;
            }
        }
        
        return command;
    }
    
    private void increasePriority(RequestCommand command){
        if(pull == null){
            return;
        }
        Iterator<RequestCommand> it = pull.iterator();
        while(it.hasNext()){
            RequestCommand inside = it.next();
            if(inside.equals(command)){
                inside.setPriority(inside.getPriority()+1);
                return;
            }
        }
    }

}
