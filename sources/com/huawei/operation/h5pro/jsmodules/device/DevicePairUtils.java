package com.huawei.operation.h5pro.jsmodules.device;

import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class DevicePairUtils {
    private static final String APP_PKG_NAME_KEY = "pkgName";
    private static final String APP_PKG_SWITCH_KEY = "switchMode";
    private static final String COMMAND_LIST_KEY = "list";
    private static final String DEVICE_BLUETOOTH_NAME = "bluetoothName";
    public static final String DEVICE_CONNECT_STATE = "deviceConnectState";
    public static final String DEVICE_EMUI_VERSION = "deviceEmuiVersion";
    public static final String DEVICE_FACTORY_RESET = "deviceFactoryReset";
    public static final String DEVICE_ID = "deviceId";
    private static final String DEVICE_MAC_KEY = "deviceMac";
    public static final String DEVICE_NAME = "deviceName";
    public static final String DEVICE_SMART_METER_VERSION = "smartMeterVersion";
    public static final String DEVICE_SPORT_VERSION = "sportVersion";
    private static final String ENABLE_KEY = "enable";
    public static final int ERROR_CODE = 0;
    private static final String IS_DELETE_KEY = "isDeleteDevice";
    private static final String IS_RECONNECT_KEY = "isReconnect";
    private static final String PAIR_INFO_KEY = "pairInfo";
    private static final String REMIND_MODE_KEY = "mode";
    private static final String REMIND_STATUS_KEY = "status";
    private static final String STATUS_KEY = "status";
    public static final int SUCCESS_CODE = 1;
    private static final String TAG = "DevicePairUtils";

    public static String getDeviceMac(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(DEVICE_MAC_KEY)) {
                return jSONObject.optString(DEVICE_MAC_KEY);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDeviceId error");
        }
        return "";
    }

    public static boolean isReconnect(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(IS_RECONNECT_KEY)) {
                return jSONObject.optBoolean(IS_RECONNECT_KEY);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "isReconnect error");
        }
        return false;
    }

    public static String getDevicePairInfo(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(PAIR_INFO_KEY)) {
                return jSONObject.optString(PAIR_INFO_KEY);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDevicePairInfo error");
        }
        return "";
    }

    public static boolean isDeleteDevice(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(IS_DELETE_KEY)) {
                return jSONObject.optBoolean(IS_DELETE_KEY);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "isDeleteDevice error");
        }
        return false;
    }

    public static String getCommandList(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(COMMAND_LIST_KEY)) {
                return jSONObject.optString(COMMAND_LIST_KEY);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getCommandList error");
        }
        return "";
    }

    public static String getHwSynergyStatus(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status")) {
                return jSONObject.optString("status");
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getHwSynergy error");
        }
        return "";
    }

    public static String getNotificationEnable(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("enable")) {
                return jSONObject.optString("enable");
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getNotificationEnable error");
        }
        return "";
    }

    public static String getRemindMode(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("mode")) {
                return jSONObject.optString("mode");
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getRemindMode error");
        }
        return "";
    }

    public static String getRemindStatus(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status")) {
                return jSONObject.optString("status");
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getRemindStatus error");
        }
        return "";
    }

    public static String getPkgName(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("pkgName")) {
                return jSONObject.optString("pkgName");
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getPkgName error");
        }
        return "";
    }

    public static String getSwitchMode(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(APP_PKG_SWITCH_KEY)) {
                return jSONObject.optString(APP_PKG_SWITCH_KEY);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getSwitchMode error");
        }
        return "";
    }

    public static String getBluetoothName(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("bluetoothName")) {
                return jSONObject.optString("bluetoothName");
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "isReconnect error");
        }
        return "";
    }
}
