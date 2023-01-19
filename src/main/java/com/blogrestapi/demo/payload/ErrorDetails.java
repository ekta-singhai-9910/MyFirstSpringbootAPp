package com.blogrestapi.demo.payload;

import java.util.Date;

public class ErrorDetails {

    private Date timeStamp ;
    private String details ;
    private String message ;

    public ErrorDetails(Date timeStamp, String details, String message) {
        this.timeStamp = timeStamp ;
        this.details = details ;
        this.message = message ;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
