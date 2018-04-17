package com.redlongcitywork.easytourlite.handler.request;

/**
 *
 * @author redlongcity 14/12/2017 class for primary processing requests
 */
public interface RequestHandler<Request, Response> {
    
    Response handle(Request request);

    Response execute(Request request);

}
