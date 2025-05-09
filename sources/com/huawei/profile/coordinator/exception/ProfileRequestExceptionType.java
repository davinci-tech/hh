package com.huawei.profile.coordinator.exception;

/* loaded from: classes6.dex */
public enum ProfileRequestExceptionType {
    IO_EXCEPTION(0),
    INPUT_PARAM_INVALID(1),
    UNEXPECTED_NULL_POINTER(2),
    UNEXPECTED_HTTP_RESPONSE_CODE(3),
    NUMBER_FORMAT_EXCEPTION(4),
    CONTENT_LENGTH_INVALID(5),
    DATA_TRAFFIC_OVERSIZE(6),
    DEVICE_PROFILE_INVALID(7),
    EMPTY_CLOUD_DEVICE_ID(8),
    JSON_PARSE_EXCEPTION(9),
    NETWORK_ERROR(10),
    INVALID_REQUEST_TYPE(11),
    AT_EXPIRED(12),
    PROD_ID_NOT_EXIST(13),
    TLS_ERROR(14);

    private final int exceptionTypeValue;

    ProfileRequestExceptionType(int i) {
        this.exceptionTypeValue = i;
    }

    public int getExceptionTypeValue() {
        return this.exceptionTypeValue;
    }
}
