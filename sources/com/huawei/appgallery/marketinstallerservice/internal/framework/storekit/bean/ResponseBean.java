package com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean;

/* loaded from: classes3.dex */
public class ResponseBean extends JsonBean {
    public static final int ERROR = 1;
    public static final int NETWORK_ERROR = 3;
    public static final int OK = 0;
    public static final int PARSE_ERROR = 4;
    public static final int READ_CACHE_ERROR = 6;
    public static final int REQUEST_FAILED = 1;
    public static final int REQUEST_SUCCESS = 0;
    public static final int REQ_PARAM_ERROR = 5;
    public static final int RESPONSE_ERROR = 7;
    public static final int TIMEOUT = 2;

    @InstallerNetTransmission
    private int rtnCode;

    @InstallerNetTransmission
    private String rtnDesc;
    private int e = 1;
    private a c = a.NORMAL;

    public enum a {
        NORMAL,
        NO_NETWORK,
        JSON_ERROR,
        PARAM_ERROR,
        RESPONSE_EXCEPTION,
        IO_EXCEPTION,
        CONNECT_EXCEPTION,
        UNKNOWN_EXCEPTION
    }

    public String toString() {
        return "ResponseBean{responseCode=" + this.e + ", rtnCode=" + this.rtnCode + ", rtnDesc='" + this.rtnDesc + "', errCause=" + this.c + '}';
    }

    public void setRtnDesc(String str) {
        this.rtnDesc = str;
    }

    public void setRtnCode(int i) {
        this.rtnCode = i;
    }

    public void setResponseCode(int i) {
        this.e = i;
    }

    public void setErrCause(a aVar) {
        this.c = aVar;
    }

    public String getRtnDesc() {
        return this.rtnDesc;
    }

    public int getRtnCode() {
        return this.rtnCode;
    }

    public int getResponseCode() {
        return this.e;
    }

    public a getErrCause() {
        return this.c;
    }
}
