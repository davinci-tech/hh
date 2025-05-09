package com.huawei.hwdevice.phoneprocess.mgr.httpproxy;

/* loaded from: classes5.dex */
public enum ErrorCode {
    ERROR_SUCCESS(19900000, "Success."),
    ERROR_INNER(19900001, "Internal Error."),
    ERROR_PARAMETER(19900002, "Invalid parameters."),
    ERROR_USER_NO_LOGIN(19900003, "The user has not logged in."),
    ERROR_NO_CLOUD_NOT_SUPPORT(19900004, "Not supported in regions without cloud."),
    ERROR_GET_GRS_FAILED(19900005, "Failed to obtain the GRS."),
    ERROR_NETWORK_REQUEST_FAILED(19900006, "Network request failed."),
    ERROR_CHANNEL_BUSY(19900007, "Channel busy."),
    ERROR_TOO_MANY_REQUEST(19900008, "Request too frequent."),
    FILE_TRANSFER_FAILED(19900009, "File transfer failed"),
    APP_CREATE_FILE_FAILED(19900010, "App create file failed");

    private final int code;
    private final String msg;

    ErrorCode(int i, String str) {
        this.code = i;
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
