package com.sahce.ufcg.exceptions;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(String msg){
        super(msg);
    }
}
