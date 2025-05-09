package com.huawei.hihealth.data.constant;

import android.util.SparseArray;

/* loaded from: classes.dex */
public final class HiErrorCode {
    private static SparseArray<Object> c;

    static {
        SparseArray<Object> sparseArray = new SparseArray<>();
        c = sparseArray;
        sparseArray.append(0, true);
        c.append(1, "ERR_API_EXCEPTION ");
        c.append(2, "ERR_BINDER_EXCEPTION ");
        c.append(3, "ERR_DATA_VALIDATOR ");
        c.append(4, "ERR_DATA_INSERT ");
        c.append(5, "ERR_DATA_READ ");
        c.append(6, "ERR_DATA_STAT ");
        c.append(8, "ERR_AUTHORIZATION ");
        c.append(9, "ERR_DATA_DELETE ");
        c.append(10, "ERR_NO_FIND_CLIENT ");
        c.append(11, "ERR_SQLITE_EXCEPTION ");
        c.append(7, "ERR_PARAMETER_ERROR ");
        c.append(12, "ERR_LOGIN ");
        c.append(13, "ERR_LOGOUT ");
        c.append(14, "ERR_USER_INFO ");
        c.append(15, "ERR_ACCOUNT_INFO ");
        c.append(16, "ERR_GOAL_INFO ");
        c.append(17, "ERR_VALID_APP");
        c.append(18, "ERR_VALID_UPDATE_OPTION");
        c.append(19, "ERR_IS_NO_CLOUD_VERSION");
        c.append(20, "ERR_HEALTH_USER_HAS_BEEN_CHANGED");
        c.append(21, "ERR_TIMEOUT");
        c.append(22, "ERR_THREAD_DISPOSE_EXCEPTION");
        c.append(23, "ERR_AT_INVALID");
        c.append(24, "ERR_HEALTH_NO_RUN");
        c.append(25, "ERR_SCREEN_LOCKED");
        c.append(1001, "ERR_PERMISSION_EXCEPTION");
        c.append(1002, "ERR_SCOPE_EXCEPTION");
        c.append(1003, "ERR_PRIVACY_USER_DENIED");
        c.append(26, "ERR_SENSITIVE_DATABASE");
        c.append(27, "ERR_PARTIAL_DATA_INVALID");
    }

    private HiErrorCode() {
    }

    public static Object d(int i) {
        Object obj = c.get(i);
        return obj == null ? "unknown error" : obj;
    }
}
