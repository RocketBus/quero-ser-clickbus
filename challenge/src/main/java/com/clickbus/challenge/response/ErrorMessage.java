package com.clickbus.challenge.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ErrorMessage {

    public ErrorMessage(Date date, String localizedMessage) {
        this.message = localizedMessage;
        this.timeStamp = date;
    }
    private Date timeStamp;
    private String message;
}
