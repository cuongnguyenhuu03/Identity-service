package com.nhc.Identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized exception"),
    USER_EXISTED(1001, "User existed"),
    USERNAME_INVALID(1002, "Username can not be blank"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    KEY_INVALID(1004, "key invalid"),
    USER_NOT_EXISTED(1005, "User not existed"),
    UNAUTHENTICATED(1006, "bad credential")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
