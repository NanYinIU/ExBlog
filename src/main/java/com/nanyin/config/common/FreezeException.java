package com.nanyin.config.common;

/**
 * 账户冻结异常
 */
public class FreezeException extends Exception {
    public FreezeException(){
        super();
    }

    public FreezeException(String msg){
        super(msg);
    }

}
