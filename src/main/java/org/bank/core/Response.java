package org.bank.core;

public class Response {
    static int code;
    static String message;
    static Object data=null;

    public void check(int code, String message, Object data) {

        Response.code = code;
        Response.message = message;
        Response.data = data;

    }

    public void check(int code, String message) {
        Response.code = code;
        Response.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", obb=" + data +
                "}";
    }
}
