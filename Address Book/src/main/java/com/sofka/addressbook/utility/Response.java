package com.sofka.addressbook.utility;

/**
 * API response handler
 *
 * @version 1.0.0 2022-11-03
 * @author Larry Mateo Ramirez C.
 */
public class Response {

    /**
     * Error on API response
     */
    public Boolean error;

    /**
     * API in use message
     */
    public String message;

    /**
     * API data information
     */
    public Object data;

    /**
     * Constructor method
     */
    public Response() {
        error = false;
        message = "";
        data = null;
    }

    /**
     * API response restart
     */
    public void restart() {
        error = false;
        message = "";
        data = null;
    }
}
