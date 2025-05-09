package com.huawei.hms.kit.awareness;

import android.util.SparseArray;

/* loaded from: classes4.dex */
public final class AwarenessStatusCodes {
    public static final int AWARENESS_AGC_FILE_ERROR = 10008;
    public static final int AWARENESS_APPLICATION_NOT_HUAWEI_PHONE = 10500;
    public static final int AWARENESS_BARRIER_PARAMETER_ERROR_CODE = 10006;
    public static final int AWARENESS_BEACON_NOT_AVAILABLE_CODE = 10300;
    public static final int AWARENESS_BEHAVIOR_PERMISSION_CODE = 10103;
    public static final int AWARENESS_BINDER_ERROR_CODE = 10001;
    public static final int AWARENESS_BLUETOOTH_NOT_AVAILABLE_CODE = 10400;
    public static final int AWARENESS_BLUETOOTH_PERMISSION_CODE = 10104;
    public static final int AWARENESS_COUNT_LIMIT_CODE = 10004;
    public static final int AWARENESS_DONATE_NOT_AVAILABLE_CODE = 10150;
    public static final int AWARENESS_DONATE_REMOTE_FAIL_CODE = 10151;
    public static final int AWARENESS_DONATE_V2_DENY_SERVICE = 10152;
    public static final int AWARENESS_DONATE_V2_INVALID_PARAMS = 10153;
    public static final int AWARENESS_DONATE_V2_UNKNOWN_CODE = 10154;
    public static final int AWARENESS_FREQUENCY_LIMIT_CODE = 10005;
    public static final int AWARENESS_GET_SID_ACCESS_CLOUD_FAIL = 10155;
    public static final int AWARENESS_INTERFACE_INVALID = 10012;
    public static final int AWARENESS_LOCATION_CORE_PERMISSION_CODE = 10102;
    public static final int AWARENESS_LOCATION_FENCE_SWITCH_NOT_OPEN_CODE = 10202;
    public static final int AWARENESS_LOCATION_NOCACHE_CODE = 10200;
    public static final int AWARENESS_LOCATION_NOT_AVAILABLE_CODE = 10201;
    public static final int AWARENESS_LOCATION_PERMISSION_CODE = 10101;
    public static final int AWARENESS_NO_ENOUGH_RESOURCE = 10013;
    public static final int AWARENESS_REGISTER_FAILED_CODE = 10002;
    public static final int AWARENESS_REMOTE_EXCEPTION_ERROR = 10010;
    public static final int AWARENESS_REQUEST_ERROR_CODE = 10007;
    public static final int AWARENESS_RESULT_INVALID_ERROR = 10009;
    public static final int AWARENESS_SDK_VERSION_ERROR = 10100;
    public static final int AWARENESS_SUCCESS_CODE = 0;
    public static final int AWARENESS_TIMEOUT_CODE = 10003;
    public static final int AWARENESS_UNKNOWN_ERROR_CODE = 10000;
    public static final int AWARENESS_WAIT_CALLBACK_CODE = 10011;
    public static final int AWARENESS_WIFI_CORE_PERMISSION_CODE = 10106;
    public static final int AWARENESS_WIFI_PERMISSION_CODE = 10105;
    private static final SparseArray<String> CODE_MAP;
    public static final int UPDATE_HMS_CODE = 907135003;
    public static final int UPDATE_KIT_CODE = 1212;

    public static String getMessage(int i) {
        return CODE_MAP.get(i, "unknown error code: " + i);
    }

    private AwarenessStatusCodes() {
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        CODE_MAP = sparseArray;
        sparseArray.put(0, "success");
        sparseArray.put(10000, "unknown error code");
        sparseArray.put(10001, "binder is null error");
        sparseArray.put(10002, "register barrier failed");
        sparseArray.put(10003, "timeout");
        sparseArray.put(10004, "access max count of barriers for single cp");
        sparseArray.put(10005, "barrier or capture frequency limit");
        sparseArray.put(10006, "barrier parameter error");
        sparseArray.put(10007, "request parameter error");
        sparseArray.put(10008, "AGC file error");
        sparseArray.put(10009, "capture result from service is invalid");
        sparseArray.put(10010, "bind method throw remote exception");
        sparseArray.put(10011, "wait service callback");
        sparseArray.put(10012, "this interface no longer provides service");
        sparseArray.put(10100, "sdk version error check if sdk version >= 24");
        sparseArray.put(AWARENESS_LOCATION_PERMISSION_CODE, "app location permission denied");
        sparseArray.put(10102, "HMS location permission denied");
        sparseArray.put(10103, "app behavior permission denied");
        sparseArray.put(AWARENESS_LOCATION_NOCACHE_CODE, "location no cache");
        sparseArray.put(AWARENESS_LOCATION_NOT_AVAILABLE_CODE, "location service not available");
        sparseArray.put(AWARENESS_LOCATION_FENCE_SWITCH_NOT_OPEN_CODE, "geofencing switch not open");
        sparseArray.put(AWARENESS_BEACON_NOT_AVAILABLE_CODE, "beacon service not available");
        sparseArray.put(AWARENESS_BLUETOOTH_NOT_AVAILABLE_CODE, "this hardware does not support bluetooth");
        sparseArray.put(1212, "awareness kit needs update");
        sparseArray.put(907135003, "HMS needs update");
        sparseArray.put(10013, "kit has no enough resource");
        sparseArray.put(AWARENESS_DONATE_NOT_AVAILABLE_CODE, "CA meta data or provider not support");
        sparseArray.put(AWARENESS_DONATE_REMOTE_FAIL_CODE, "CA provider return error");
        sparseArray.put(AWARENESS_DONATE_V2_DENY_SERVICE, "intent framework deny service");
        sparseArray.put(AWARENESS_DONATE_V2_INVALID_PARAMS, "intent framework param check fail");
        sparseArray.put(AWARENESS_DONATE_V2_UNKNOWN_CODE, "unknown intent framework error code");
        sparseArray.put(AWARENESS_GET_SID_ACCESS_CLOUD_FAIL, "Failed to access the cloud service");
    }
}
