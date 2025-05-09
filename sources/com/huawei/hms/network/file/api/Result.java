package com.huawei.hms.network.file.api;

import com.huawei.hms.network.file.core.Constants;

/* loaded from: classes4.dex */
public class Result {
    private int code;
    private String message;
    public static final Result RESULT_SUCCESS = new Result(Constants.ErrorCode.SUCCESS);
    public static final Result RESULT_STATUS_FAILED = new Result(Constants.ErrorCode.REQUEST_STATUS_ERROR);
    public static final int SUCCESS = Constants.ErrorCode.SUCCESS.getErrorCode();
    public static final int PAUSE = Constants.ErrorCode.TASK_PAUSE_EXCEPTION.getErrorCode();
    public static final int CANCEL = Constants.ErrorCode.TASK_CANCEL_EXCEPTION.getErrorCode();

    /* loaded from: classes9.dex */
    public enum STATUS {
        INIT,
        PROCESS,
        PAUSE,
        INVALID
    }

    public String toString() {
        return "Result{code=" + this.code + ", message='" + this.message + "'}";
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    public Result(Constants.ErrorCode errorCode) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMessage();
    }

    public Result(int i, String str) {
        this.code = i;
        this.message = str;
    }

    public Result(int i) {
        this.code = i;
    }

    public Result() {
    }
}
