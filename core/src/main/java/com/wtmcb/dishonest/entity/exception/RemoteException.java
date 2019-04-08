package com.wtmcb.dishonest.entity.exception;

/**
 * Created by WangGang on 2019-04-08.
 * Email: wanggang1@vipkid.com.cn
 */
public class RemoteException extends Exception {

    public RemoteException() {
        super();
    }

    public RemoteException(String message) {
        super(message);
    }

    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteException(Throwable cause) {
        super(cause);
    }

    protected RemoteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
