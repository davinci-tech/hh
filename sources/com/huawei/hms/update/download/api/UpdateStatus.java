package com.huawei.hms.update.download.api;

/* loaded from: classes9.dex */
public final class UpdateStatus {
    public static final int CHECK_FAILURE = 1201;
    public static final int CHECK_NO_SUPPORTED = 1203;
    public static final int CHECK_NO_UPDATE = 1202;
    public static final int CHECK_OK = 1000;
    public static final int DOWNLOADING = 2100;
    public static final int DOWNLOAD_CANCELED = 2101;
    public static final int DOWNLOAD_FAILURE = 2201;
    public static final int DOWNLOAD_HASH_ERROR = 2202;
    public static final int DOWNLOAD_NO_SPACE = 2203;
    public static final int DOWNLOAD_NO_STORAGE = 2204;
    public static final int DOWNLOAD_SUCCESS = 2000;

    private UpdateStatus() {
    }

    public static String statusToString(int i) {
        if (i == 1000) {
            return "CHECK_OK";
        }
        if (i == 2000) {
            return "DOWNLOAD_SUCCESS";
        }
        if (i == 2100) {
            return "DOWNLOADING";
        }
        if (i == 2101) {
            return "DOWNLOAD_CANCELED";
        }
        switch (i) {
            case 1201:
                return "CHECK_FAILURE";
            case 1202:
                return "CHECK_NO_UPDATE";
            case 1203:
                return "CHECK_NO_SUPPORTED";
            default:
                switch (i) {
                    case 2201:
                        return "DOWNLOAD_FAILURE";
                    case 2202:
                        return "DOWNLOAD_HASH_ERROR";
                    case 2203:
                        return "DOWNLOAD_NO_SPACE";
                    case 2204:
                        return "DOWNLOAD_NO_STORAGE";
                    default:
                        return "UNKNOWN - " + i;
                }
        }
    }
}
