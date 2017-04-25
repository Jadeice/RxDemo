package com.cyxoder.httplib.exception;

/**
 * Created by Jade on 2017/4/25.
 */

public class ForceTimeoutException extends Exception {
    private final String msg;
    private long timeoutLength;

    public ForceTimeoutException(String message) {
        super(message);
        this.msg = message;
    }

    public ForceTimeoutException(long mills, String message) {
        super(message);
        this.msg = message;
        this.timeoutLength = mills;
    }

    public String getMsg() {
        return msg;
    }

    public long getTimeoutLength() {
        return timeoutLength;
    }
}
