package com.huawei.operation.share;

import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class HiHealthError {
    public static final int ERR_API_EXCEPTION = 4;
    public static final int ERR_BLUETOOTH_NOT_ENABLED = 1005;
    public static final int ERR_DATA_VALIDATOR = 3;
    public static final int ERR_DEVICE_NOT_CONNECTED = 150;
    public static final int ERR_LOCATION_PERMISSION_DENIED = 1006;
    public static final int ERR_PARAMETER_ERROR = 7;
    public static final int ERR_PERMISSION_EXCEPTION = 1001;
    public static final int ERR_PRIVACY_USER_DENIED = 1003;
    public static final int ERR_REPEAT_EXCEPTION = 101;
    public static final int ERR_SCOPE_EXCEPTION = 1002;
    public static final int ERR_STORAGE_PERMISSION_DENIED = 1007;
    public static final int ERR_WRONG_DEVICE = 151;
    public static final int FAILED = 1;
    public static final int PARAM_INVALID = 2;
    public static final int SUCCESS = 0;
    private static Map<Integer, String> sErrorMessage;

    static {
        HashMap hashMap = new HashMap();
        sErrorMessage = hashMap;
        hashMap.put(0, "success");
        sErrorMessage.put(1, "failed");
        sErrorMessage.put(4, "api state exception");
        sErrorMessage.put(3, "data validator error");
        sErrorMessage.put(7, AuthInternalPickerConstant.PARAM_ERROR);
        sErrorMessage.put(1001, "permission exception");
        sErrorMessage.put(101, "repeat error");
        sErrorMessage.put(150, "device not connected");
        sErrorMessage.put(1002, "scope exception");
        sErrorMessage.put(1003, "user privacy denied error");
        sErrorMessage.put(1005, "bluetooth not enabled");
        sErrorMessage.put(1006, "location permission denied");
        sErrorMessage.put(1007, "storage permission denied");
    }

    private HiHealthError() {
    }

    public static String getErrorMessage(int i) {
        String str = sErrorMessage.get(Integer.valueOf(i));
        return str == null ? "UNKNOWN_ERROR" : str;
    }
}
