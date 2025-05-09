package com.huawei.appgallery.agd.api;

/* loaded from: classes2.dex */
public class BaseServiceResult {
    protected String b;
    protected String c;
    protected int e;

    /* loaded from: classes8.dex */
    public interface LoadResultCode {
        public static final int CARD_LOADING = 107;
        public static final int DOWNLOAD_EXCEPTION = 103;
        public static final int DOWNLOAD_FAILED = 104;
        public static final int DOWNLOAD_PAUSE = 105;
        public static final int DOWNLOAD_PREPARATION_FAILED = 102;
        public static final int FAIL_CONTENT_RESTRICT = 4;
        public static final int FAIL_RESPONSE_ERROR = 2;
        public static final int FAIL_RESPONSE_UNKNOWN = 7;
        public static final int FAIL_SERVER_OFF_SHELVE = 3;
        public static final int FAIL_SERVICE_EMPTY = 6;
        public static final int FAIL_VERIFY = 5;
        public static final int GET_FORMS_ERROR = 101;
        public static final int HIGHER_VERSION_LOADED = 202;
        public static final int INCOMPATIBLE = 201;
        public static final int INSTALLED = 203;
        public static final int INSTALL_FAILED = 106;
        public static final int INSTALL_SUCCESS = 100;
        public static final int INVALID = -1;
        public static final int NO_NETWORK = 108;
        public static final int PAGE_LOADING = 1;
        public static final int VERIFY_SUCCESS = 0;
    }

    public String getLoadResultDesc() {
        int i = this.e;
        switch (i) {
            case -1:
                return "INVALID";
            case 0:
                return "VERIFY_SUCCESS";
            case 1:
                return "PAGE_LOADING";
            case 2:
                return "FAIL_RESPONSE_ERROR";
            case 3:
                return "FAIL_SERVER_OFF_SHELVE";
            case 4:
                return "FAIL_CONTENT_RESTRICT";
            case 5:
                return "FAIL_VERIFY";
            case 6:
                return "FAIL_SERVICE_EMPTY";
            case 7:
                return "FAIL_RESPONSE_UNKNOWN";
            default:
                switch (i) {
                    case 100:
                        return "INSTALL_SUCCESS";
                    case 101:
                        return "GET_FORMS_ERROR";
                    case 102:
                        return "DOWNLOAD_PREPARATION_FAILED";
                    case 103:
                        return "DOWNLOAD_EXCEPTION";
                    case 104:
                        return "DOWNLOAD_FAILED";
                    case 105:
                        return "DOWNLOAD_PAUSE";
                    case 106:
                        return "INSTALL_FAILED";
                    case 107:
                        return "CARD_LOADING";
                    case 108:
                        return "NO_NETWORK";
                    default:
                        switch (i) {
                            case 201:
                                return "INCOMPATIBLE";
                            case 202:
                                return "HIGHER_VERSION_LOADED";
                            case 203:
                                return "INSTALLED";
                            default:
                                return "unknown result " + this.e;
                        }
                }
        }
    }

    public int getLoadResult() {
        return this.e;
    }

    public String getCallerContext() {
        return this.b;
    }

    public String getBundleName() {
        return this.c;
    }
}
