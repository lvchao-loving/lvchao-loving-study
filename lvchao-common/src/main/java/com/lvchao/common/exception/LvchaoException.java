package com.lvchao.common.exception;

/**
 * Description: 系统异常
 *
 * @author lvchao
 * @date Create on 2020/12/18
 */
public class LvchaoException extends Exception{
    private static final long serialVersionUID = 8647498220615751874L;

    public LvchaoException(String message){
        super(message);
    }
}
