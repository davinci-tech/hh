package com.huawei.operation.h5pro.jsmodules.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.devicepair.api.BasePairManagerApi;
import com.huawei.devicepair.api.PermissionsApi;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bgb;
import defpackage.bgd;
import defpackage.cvs;
import defpackage.jeg;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReleaseLogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BasePairManagerJsApi extends JsBaseModule {
    public static final String FROM_H5_KEY = "isFromH5";
    private static final Object LOCK = new Object();
    private static final String TAG = "BasePairManagerJsApi";
    private static final String TAG_RELEASE = "DEVMGR_BasePairManagerJsApi";
    public static volatile boolean isOpen = false;
    private boolean isOobe;
    private Map<String, Set<Long>> mCallBacks;
    private final BroadcastReceiver mDeviceStateBroadcast = new BroadcastReceiver() { // from class: com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.h(BasePairManagerJsApi.TAG, "context or intent is null");
                return;
            }
            String action = intent.getAction();
            ReleaseLogUtil.b(BasePairManagerJsApi.TAG_RELEASE, "action : ", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                try {
                    DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                    String deviceIdentify = deviceInfo.getDeviceIdentify();
                    if (TextUtils.isEmpty(deviceIdentify)) {
                        LogUtil.h(BasePairManagerJsApi.TAG, "deviceId is null");
                        return;
                    }
                    int deviceConnectState = deviceInfo.getDeviceConnectState();
                    LogUtil.a(BasePairManagerJsApi.TAG, "device: ", deviceInfo.getDeviceName(), " device state: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                    synchronized (BasePairManagerJsApi.LOCK) {
                        Set<Long> set = (Set) BasePairManagerJsApi.this.mCallBacks.get(deviceIdentify);
                        if (set != null && !set.isEmpty()) {
                            for (Long l : set) {
                                LogUtil.a(BasePairManagerJsApi.TAG, "callbackid: ", l);
                                BasePairManagerJsApi.this.onSuccessCallback(l.longValue(), BasePairManagerJsApi.this.getDeviceJson(deviceInfo));
                                if (deviceIdentify.equals(BasePairManagerJsApi.this.mTempMac)) {
                                    if (deviceConnectState != 11) {
                                        BasePairManagerJsApi.this.isOobe = false;
                                    } else {
                                        bgb.d().sendOobeStatus(deviceInfo);
                                        BasePairManagerJsApi.this.isOobe = true;
                                    }
                                }
                            }
                            if (deviceConnectState == 2 && deviceIdentify.equals(BasePairManagerJsApi.this.mTempMac) && BasePairManagerJsApi.this.mCallBacks.get(deviceIdentify) != null) {
                                BasePairManagerJsApi.this.mCallBacks.remove(deviceIdentify);
                                BasePairManagerJsApi.this.mTempMac = null;
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                    LogUtil.b(BasePairManagerJsApi.TAG, "dealStateChange ClassCastException");
                } catch (Exception unused2) {
                    LogUtil.b(BasePairManagerJsApi.TAG, "dealStateChange Exception");
                }
            }
        }
    };
    private String mTempMac;

    /* JADX INFO: Access modifiers changed from: private */
    public String getDeviceJson(DeviceInfo deviceInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceName", deviceInfo.getDeviceName());
            jSONObject.put("deviceId", deviceInfo.getDeviceIdentify());
            jSONObject.put(DevicePairUtils.DEVICE_CONNECT_STATE, deviceInfo.getDeviceConnectState());
            jSONObject.put(DevicePairUtils.DEVICE_FACTORY_RESET, deviceInfo.getDeviceFactoryReset());
            jSONObject.put(DevicePairUtils.DEVICE_EMUI_VERSION, deviceInfo.getEmuiVersion());
            jSONObject.put(DevicePairUtils.DEVICE_SPORT_VERSION, deviceInfo.getSportVersion());
            DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
            jSONObject.put(DevicePairUtils.DEVICE_SMART_METER_VERSION, e != null ? e.getSmartWatchVersionTypeValue() : -1);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDeviceJson is error");
        }
        return jSONObject.toString();
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.mCallBacks = new HashMap();
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            intentFilter.setPriority(1000);
            BroadcastManagerUtil.bFC_(this.mContext, this.mDeviceStateBroadcast, intentFilter, LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b(TAG, "registerConnectStateBroadcast IllegalStateException");
        }
        ReleaseLogUtil.b(TAG_RELEASE, "enter onCreate");
        isOpen = true;
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        if (this.isOobe) {
            bgb.d().removeDevice(this.mTempMac);
        }
        try {
            this.mContext.unregisterReceiver(this.mDeviceStateBroadcast);
        } catch (IllegalArgumentException unused) {
            LogUtil.b(TAG, "unregisterReceiver IllegalStateException");
        }
        this.mTempMac = null;
        isOpen = false;
        super.onDestroy();
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        jeg.d().d(strArr, iArr);
    }

    @JavascriptInterface
    public void connectDevice(final long j, String str) {
        ReleaseLogUtil.b(TAG, "connectDevice callbackId ", Long.valueOf(j));
        BasePairManagerApi d = bgb.d();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        boolean isReconnect = DevicePairUtils.isReconnect(str);
        String devicePairInfo = DevicePairUtils.getDevicePairInfo(str);
        if (TextUtils.isEmpty(deviceMac) || TextUtils.isEmpty(devicePairInfo)) {
            onFailureCallback(j, "get deviceMac or pairInfo error");
            return;
        }
        this.mTempMac = deviceMac;
        setCallBack(deviceMac, j);
        d.connectDevice(this.mContext, deviceMac, isReconnect, devicePairInfo, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                BasePairManagerJsApi.this.onFailureCallback(j, "permission error");
            }
        });
    }

    private void setCallBack(String str, long j) {
        synchronized (LOCK) {
            Map<String, Set<Long>> map = this.mCallBacks;
            if (map == null) {
                onFailureCallback(j, "get callback list is fail");
                return;
            }
            Set<Long> set = map.get(str);
            if (set == null) {
                set = new HashSet<>(16);
            }
            set.add(Long.valueOf(j));
            this.mCallBacks.put(str, set);
        }
    }

    @JavascriptInterface
    public void disconnectDevice(long j, String str) {
        ReleaseLogUtil.b(TAG, "disconnectDevice callbackId ", Long.valueOf(j));
        BasePairManagerApi d = bgb.d();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        boolean isDeleteDevice = DevicePairUtils.isDeleteDevice(str);
        String devicePairInfo = DevicePairUtils.getDevicePairInfo(str);
        if (TextUtils.isEmpty(deviceMac)) {
            LogUtil.h(TAG, "disconnectDevice, get deviceMac is error");
            onFailureCallback(j, "disconnectDevice is error");
            return;
        }
        d.disconnectDevice(deviceMac, devicePairInfo, isDeleteDevice);
        synchronized (LOCK) {
            Set<Long> set = this.mCallBacks.get(deviceMac);
            if (set != null && !set.isEmpty()) {
                if (this.mCallBacks.get(deviceMac) != null) {
                    this.mCallBacks.remove(deviceMac);
                }
            }
        }
    }

    @JavascriptInterface
    public void listDevice(long j, String str) {
        ReleaseLogUtil.b(TAG, "listDevice callbackId ", Long.valueOf(j));
        List<bgd> listDevice = bgb.d().listDevice(this.mContext);
        if (CollectionUtils.d(listDevice)) {
            onFailureCallback(j, "get listDevice is error");
        } else {
            onSuccessCallback(j, listDevice);
        }
    }

    @JavascriptInterface
    public void getOobePath(long j, String str) {
        ReleaseLogUtil.b(TAG, "getOobePath callbackId ", Long.valueOf(j));
        String oobePath = bgb.d().getOobePath(DevicePairUtils.getBluetoothName(str));
        if (TextUtils.isEmpty(oobePath)) {
            onFailureCallback(j, "getOobePath is error");
        } else {
            onSuccessCallback(j, oobePath);
        }
    }

    @JavascriptInterface
    public void sendDeclarationInfoList(long j, String str) {
        ReleaseLogUtil.b(TAG, "sendDeclarationInfoList callbackId ", Long.valueOf(j));
        BasePairManagerApi d = bgb.d();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        String commandList = DevicePairUtils.getCommandList(str);
        if (TextUtils.isEmpty(deviceMac) || TextUtils.isEmpty(commandList)) {
            LogUtil.h(TAG, "sendDeclarationInfoList, device mac or context is empty ");
            onFailureCallback(j, "sendDeclarationInfoList error");
        } else {
            d.sendDeclarationInfoList(deviceMac, commandList);
        }
    }

    @JavascriptInterface
    public void isSupportOobe(long j, String str) {
        ReleaseLogUtil.b(TAG, "connectDevice callbackId ", Long.valueOf(j));
    }

    @JavascriptInterface
    public void isSupportSyncAccount(long j, String str) {
        ReleaseLogUtil.b(TAG, "isSupportSyncAccount callbackId ", Long.valueOf(j));
        BasePairManagerApi d = bgb.d();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        if (TextUtils.isEmpty(deviceMac)) {
            LogUtil.h(TAG, "isSupportSyncAccount, device mac is empty ");
            onFailureCallback(j, "isSupportSyncAccount error");
        } else {
            onSuccessCallback(j, Boolean.valueOf(d.isSupportSyncAccount(deviceMac)));
        }
    }

    @JavascriptInterface
    public void syncAccount(long j, String str) {
        ReleaseLogUtil.b(TAG, "syncAccount callbackId ", Long.valueOf(j));
        bgb.d().syncAccount("", 1);
    }

    @JavascriptInterface
    public void isSupportHwSynergy(long j, String str) {
        ReleaseLogUtil.b(TAG, "isSupportHwSynergy callbackId ", Long.valueOf(j));
        BasePairManagerApi d = bgb.d();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        if (TextUtils.isEmpty(deviceMac)) {
            LogUtil.h(TAG, "isSupportHwSynergy, device mac is empty ");
            onFailureCallback(j, "isSupportSyncAccount error");
        } else {
            onSuccessCallback(j, Boolean.valueOf(d.isSupportHwSynergy(deviceMac)));
        }
    }

    @JavascriptInterface
    public void hwSynergy(long j, String str) {
        ReleaseLogUtil.b(TAG, "hwSynergy callbackId ", Long.valueOf(j));
        BasePairManagerApi d = bgb.d();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        String hwSynergyStatus = DevicePairUtils.getHwSynergyStatus(str);
        if (TextUtils.isEmpty(deviceMac) || TextUtils.isEmpty(hwSynergyStatus)) {
            LogUtil.h(TAG, "hwSynergy, deviceId or status is error");
            onFailureCallback(j, "get param error");
        } else {
            d.hwSynergy(deviceMac, Integer.parseInt(hwSynergyStatus));
        }
    }

    @JavascriptInterface
    public void requestPermissionsForPair(long j, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "requestPermissionsForPair JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "requestPermissionsForPair jsonObject is null");
            onFailureCallback(j, "jsonObject is null", -1);
            return;
        }
        String optString = jSONObject.optString("deviceMac");
        PermissionsApi a2 = bgb.a();
        JSONArray jSONArray = new JSONArray();
        Map<String, Boolean> requestPermissionsForPair = a2.requestPermissionsForPair(optString);
        if (requestPermissionsForPair == null || requestPermissionsForPair.isEmpty()) {
            ReleaseLogUtil.a(TAG_RELEASE, "requestPermissionsForPair permissionMap is error");
        } else {
            for (Map.Entry<String, Boolean> entry : requestPermissionsForPair.entrySet()) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("permission", entry.getKey());
                    jSONObject2.put("status", entry.getValue());
                    jSONArray.put(jSONObject2);
                } catch (JSONException e2) {
                    ReleaseLogUtil.c(TAG_RELEASE, "requestPermissionsForPair permission JSONException ", ExceptionUtils.d(e2));
                }
            }
            if (jSONArray.length() == 0) {
                ReleaseLogUtil.a(TAG_RELEASE, "requestPermissionsForPair permissionArray is empty");
            }
        }
        LogUtil.a(TAG, "requestPermissionsForPair permissionArray:", jSONArray.toString());
        onSuccessCallback(j, jSONArray.toString());
    }

    @JavascriptInterface
    public void requestPermissionsGrantStatus(final long j, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "requestPermissionsGrantStatus JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "requestPermissionsGrantStatus jsonObject is null");
            onFailureCallback(j, "jsonObject is null", -1);
            return;
        }
        String optString = jSONObject.optString("pairPermission");
        if (TextUtils.isEmpty(optString)) {
            LogUtil.h(TAG, "requestPermissionsGrantStatus permissionParam is error");
            onFailureCallback(j, "permissionParam is error", -1);
            return;
        }
        final List<String> list = (List) HiJsonUtil.e(optString, List.class);
        if (CollectionUtils.d(list)) {
            LogUtil.h(TAG, "requestPermissionsGrantStatus permissionList is empty");
            onFailureCallback(j, "permissionList is error", -1);
        } else {
            bgb.a().requestPermissionsGrantStatus(list, jSONObject.optString("deviceMac"), this.mContext, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.h(BasePairManagerJsApi.TAG, "requestPermissionsGrantStatus errorCode:", Integer.valueOf(i), ", objectData:", obj);
                    if (i != -1) {
                        BasePairManagerJsApi.this.responseSuccess(j, obj, list);
                    } else {
                        ReleaseLogUtil.b(BasePairManagerJsApi.TAG_RELEASE, "responseSuccess errorCode -1");
                        BasePairManagerJsApi.this.onFailureCallback(j, "request error", -1);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void permissionGrantRegisterContactObserver(long j, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "permissionGrantRegisterContactObserver JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "permissionGrantRegisterContactObserver param is null");
            onFailureCallback(j, "param is null", -1);
        } else {
            bgb.a().permissionGrantRegisterContactObserver(jSONObject.optString("deviceMac"));
            onSuccessCallback(j, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void responseSuccess(long j, Object obj, List<String> list) {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            int[] iArr = new int[map.size()];
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) map.get(list.get(i));
                if (num != null) {
                    iArr[i] = num.intValue();
                }
            }
            ReleaseLogUtil.b(TAG_RELEASE, "responseSuccess resultArray:", Arrays.toString(iArr));
            onSuccessCallback(j, iArr);
            return;
        }
        ReleaseLogUtil.b(TAG_RELEASE, "responseSuccess error");
        onFailureCallback(j, "request error", -1);
    }

    @JavascriptInterface
    public void isSupportKeepAlive(long j, String str) {
        ReleaseLogUtil.b(TAG, "isSupportKeepAlive callbackId ", Long.valueOf(j));
        onSuccessCallback(j, Boolean.valueOf(bgb.d().isSupportKeepAlive()));
    }

    @JavascriptInterface
    public void onSuccessPair(long j, String str) {
        ReleaseLogUtil.b(TAG, "onSuccessPair");
        bgb.d().onSuccessPair(this.mContext, DevicePairUtils.getDeviceMac(str));
    }

    @JavascriptInterface
    public void settingComplete(long j, String str) {
        ReleaseLogUtil.b(TAG, "enter settingComplete");
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
        intent.putExtra(FROM_H5_KEY, true);
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }
}
