package com.huawei.operation.h5pro.jsmodules;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.cbi;
import defpackage.cuk;
import defpackage.cun;
import defpackage.cvn;
import defpackage.cvq;
import defpackage.cvr;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwg;
import defpackage.cwi;
import defpackage.cwl;
import defpackage.drl;
import defpackage.jdx;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Device extends JsBaseModule {
    private static final int ACTION_MODIFY = 1;
    private static final int ACTION_QUERY = 2;
    private static final String CLOUD_CONFIG_ID = "com.huawei.health_deviceFeature_config";
    private static final int CONVERT_RADIX_10 = 10;
    private static final int DEVICE_STATUS_ACTIVE = 0;
    private static final int DEVICE_STATUS_CONNECTED = 1;
    private static final String JSON_KEY_ANALYSIS_TYPE = "analysisType";
    private static final String KEY_SWITCH_KEYS = "switchKeys";
    private static final Object ON_WAIT_LOCK = new Object();
    private static final int RETURN_ANALYSIS_TYPE_MAP = 1;
    private static final int RETURN_ANALYSIS_TYPE_SWITCH = 0;
    private cuk mCommandProxy;
    private final BroadcastReceiver mConnectListener = new BroadcastReceiver() { // from class: com.huawei.operation.h5pro.jsmodules.Device.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a(Device.this.TAG, "ConnectListener Intent input is null, receive content ignored");
                return;
            }
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            if (!(parcelableExtra instanceof DeviceInfo)) {
                LogUtil.a(Device.this.TAG, "ConnectListener DeviceInfo input is null, receive content ignored");
                return;
            }
            DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            if (TextUtils.isEmpty(deviceIdentify)) {
                LogUtil.h(Device.this.TAG, "ConnectListener Empty deviceIdentify");
                return;
            }
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            LogUtil.a(Device.this.TAG, "ConnectListener Connecting state just changed to: ", Integer.valueOf(deviceConnectState));
            Device.this.callbackConnectListener(deviceInfo);
            synchronized (Device.ON_WAIT_LOCK) {
                Set set = (Set) Device.this.mOnWaitCallbacks.get(deviceIdentify);
                if (set != null && !set.isEmpty()) {
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        Device.this.onSuccessCallback(((Long) it.next()).longValue(), Integer.valueOf(deviceConnectState));
                    }
                    Device.this.mOnWaitCallbacks.remove(deviceIdentify);
                }
            }
        }
    };
    private long mConnectListenerId;
    private Map<String, Set<Long>> mOnWaitCallbacks;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.mCommandProxy = cuk.b();
        this.mOnWaitCallbacks = new HashMap();
        BroadcastManagerUtil.bFC_(this.mContext, this.mConnectListener, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        this.mContext.unregisterReceiver(this.mConnectListener);
        super.onDestroy();
    }

    @JavascriptInterface
    public void isSupportDeviceCapability(final long j, String str) {
        try {
            LogUtil.a(this.TAG, "isSupportDeviceCapability start: " + str);
            JSONObject jSONObject = new JSONObject(str);
            final int optInt = jSONObject.optInt("capability", -1);
            final int optInt2 = jSONObject.optInt("deviceStatus", 0);
            if (optInt == -1) {
                LogUtil.h(this.TAG, "isSupportDeviceCapability param capability is null");
                onFailureCallback(j, "param capability is null");
            }
            jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.Device.2
                @Override // java.lang.Runnable
                public void run() {
                    GetDeviceInfoMode getDeviceInfoMode;
                    cun c = cun.c();
                    if (optInt2 == 1) {
                        getDeviceInfoMode = GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70;
                    } else {
                        getDeviceInfoMode = GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70;
                    }
                    DeviceInfo deviceInfo = c.getDeviceInfo(getDeviceInfoMode, null, Device.this.TAG);
                    if (deviceInfo == null) {
                        LogUtil.h(Device.this.TAG, "isSupportDeviceCapability currentDeviceInfo is null");
                        Device.this.onFailureCallback(j, "currentDeviceInfo is null");
                        return;
                    }
                    boolean c2 = cwi.c(deviceInfo, optInt);
                    LogUtil.a(Device.this.TAG, "isSupportDeviceCapability end isSupport: " + c2);
                    Device.this.onSuccessCallback(j, Boolean.valueOf(c2));
                }
            });
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "isSupportDeviceCapability JSONException", e.getMessage());
            onFailureCallback(j, "parse param err");
        }
    }

    @JavascriptInterface
    public void sendSampleConfigCommand(long j, String str) {
        LogUtil.a(this.TAG, "start sendSampleConfigCommand");
        cvq structSampleConfig = structSampleConfig(str);
        if (structSampleConfig == null) {
            LogUtil.a(this.TAG, "sendSampleConfigCommand: Input SampleConfig with invalid params");
            onFailureCallback(j, "SampleConfig with invalid params");
            return;
        }
        String srcPkgName = structSampleConfig.getSrcPkgName();
        cvn cvnVar = structSampleConfig.getConfigInfoList().get(0);
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
        if (cvnVar.e() == 2) {
            int analysisType = getAnalysisType(str);
            LogUtil.a(this.TAG, "sendSampleConfigCommand: Prepare to listen the receive of query, analysisType=", Integer.valueOf(analysisType));
            if (analysisType == -1) {
                onFailureCallback(j, "Incorrect analysisType");
                return;
            }
            this.mCommandProxy.registerDeviceSampleListener(srcPkgName, receiveConfigCallback(j, srcPkgName, analysisType));
            if (this.mCommandProxy.sendSampleConfigCommand(deviceInfo, structSampleConfig, this.TAG)) {
                return;
            }
            this.mCommandProxy.unRegisterDeviceSampleListener(srcPkgName);
            onFailureCallback(j, "Command send failed");
            return;
        }
        if (this.mCommandProxy.sendSampleConfigCommand(deviceInfo, structSampleConfig, this.TAG)) {
            onSuccessCallback(j, "Command send successful");
        } else {
            onFailureCallback(j, "Command send failed");
        }
    }

    private DataReceiveCallback receiveConfigCallback(final long j, final String str, final int i) {
        return new DataReceiveCallback() { // from class: com.huawei.operation.h5pro.jsmodules.Device.3
            private static final String POSITION = "sendSampleConfigCommand: onDataReceived: ";

            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public void onDataReceived(int i2, DeviceInfo deviceInfo, cvr cvrVar) {
                Object tlvValues;
                if (!(cvrVar instanceof cvq)) {
                    Device.this.receiveFailedAndLog(j, str, POSITION, "Received a message with wrong format");
                    return;
                }
                List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
                if (configInfoList == null || configInfoList.size() == 0) {
                    Device.this.receiveFailedAndLog(j, str, POSITION, "Received a message are missing config info list");
                    return;
                }
                cvn cvnVar = configInfoList.get(0);
                if (cvnVar == null) {
                    Device.this.receiveFailedAndLog(j, str, POSITION, "Received a message are missing config info");
                    return;
                }
                byte[] b = cvnVar.b();
                if (b == null) {
                    Device.this.receiveFailedAndLog(j, str, POSITION, "Received a message are missing byte data");
                    return;
                }
                Device device = Device.this;
                long j2 = j;
                if (i != 0) {
                    tlvValues = device.getTlvValues(b);
                } else {
                    tlvValues = Byte.valueOf(b[b.length - 1]);
                }
                device.onSuccessCallback(j2, tlvValues);
                Device.this.mCommandProxy.unRegisterDeviceSampleListener(str);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTlvValues(byte[] bArr) {
        try {
            List<cwd> e = new cwl().a(cvx.d(bArr)).e();
            LogUtil.c(this.TAG, "getTlvValues origin tlv list: ", HiJsonUtil.e(e));
            TreeMap treeMap = new TreeMap();
            for (cwd cwdVar : e) {
                treeMap.put(cwdVar.e(), cvx.e(cutHead0(cwdVar.c())));
            }
            String e2 = HiJsonUtil.e(treeMap);
            LogUtil.c(this.TAG, "getTlvValues handled tlv list: ", e2);
            return e2;
        } catch (cwg unused) {
            LogUtil.b(this.TAG, "getTlvValues TlvException");
            return "getTlvValues TlvException";
        }
    }

    private String cutHead0(String str) {
        while (str.startsWith("0")) {
            str = str.substring(1);
        }
        return str;
    }

    private int getAnalysisType(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(JSON_KEY_ANALYSIS_TYPE)) {
                return 0;
            }
            int i = jSONObject.getInt(JSON_KEY_ANALYSIS_TYPE);
            if (i == 0 || i == 1) {
                return i;
            }
            return -1;
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "JSONException during getAnalysisType");
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveFailedAndLog(long j, String str, String str2, String str3) {
        LogUtil.b(this.TAG, str2, str3);
        onFailureCallback(j, str3);
        this.mCommandProxy.unRegisterDeviceSampleListener(str);
    }

    public cvq structSampleConfig(String str) {
        try {
            cvq cvqVar = new cvq();
            JSONObject jSONObject = new JSONObject(str);
            cvqVar.setSrcPkgName(jSONObject.getString("srcPkgName"));
            cvqVar.setWearPkgName(jSONObject.getString("wearPkgName"));
            cvqVar.setCommandId(jSONObject.optInt("commandId"));
            JSONArray jSONArray = jSONObject.getJSONArray("configInfoList");
            if (jSONArray.length() == 0) {
                throw new JSONException("");
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                cvn cvnVar = new cvn();
                cvnVar.e(jSONObject2.getLong("configId"));
                cvnVar.d(jSONObject2.getInt("configAction"));
                JSONArray optJSONArray = jSONObject2.optJSONArray("byteConfigData");
                byte[] bArr = new byte[0];
                if (optJSONArray != null) {
                    bArr = structByteConfigData(optJSONArray);
                }
                cvnVar.c(bArr);
                arrayList.add(cvnVar);
            }
            cvqVar.setConfigInfoList(arrayList);
            return cvqVar;
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "structSampleConfig JSONException");
            return null;
        }
    }

    private byte[] structByteConfigData(JSONArray jSONArray) throws JSONException {
        String c;
        byte[] bArr = new byte[0];
        int i = 0;
        while (i < jSONArray.length()) {
            int i2 = i + 1;
            String e = cvx.e(i2);
            String string = jSONArray.getString(i);
            if (TextUtils.isDigitsOnly(string)) {
                c = cvx.e(CommonUtil.a(string, 10));
            } else {
                c = cvx.c(string);
            }
            int length = c.length() / 2;
            if (c.length() % 2 != 0) {
                length++;
            }
            byte[] a2 = cvx.a(e + cvx.e(length) + c);
            byte[] bArr2 = new byte[bArr.length + a2.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(a2, 0, bArr2, bArr.length, a2.length);
            i = i2;
            bArr = bArr2;
        }
        return bArr;
    }

    @JavascriptInterface
    public void getConfig(long j, String str) {
        try {
            JSONObject b = drl.b(CLOUD_CONFIG_ID, new JSONObject(str).getString("packageName"));
            int i = b.getInt("resultCode");
            if (i == -1) {
                LogUtil.a(this.TAG, "getConfig failed: ", HiJsonUtil.e(b));
                onFailureCallback(j, b.getString("resultDesc"), i);
            } else if (i == 0) {
                LogUtil.a(this.TAG, "getConfig success");
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("data", b);
                onSuccessCallback(j, jSONObject);
            } else {
                LogUtil.b(this.TAG, "getConfig: unknown error");
                onFailureCallback(j, "Unknown error", -1);
            }
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "JSONException in getConfig: ", e.getMessage());
            onFailureCallback(j, "JSONException in getConfig", -1);
        }
    }

    @JavascriptInterface
    public void registerConnectListener(long j, String str) {
        String str2;
        try {
            str2 = new JSONObject(str).optString("deviceIdentify");
        } catch (JSONException unused) {
            LogUtil.h(this.TAG, "listenConnectState Input empty deviceIdentify, getting from main device");
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
            if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                LogUtil.b(this.TAG, "listenConnectState Get deviceIdentify failed");
                onFailureCallback(j, "get deviceIdentify failed");
                return;
            }
            str2 = deviceInfo.getDeviceIdentify();
        }
        synchronized (ON_WAIT_LOCK) {
            Map<String, Set<Long>> map = this.mOnWaitCallbacks;
            if (map == null) {
                onFailureCallback(j, "callback list generate failed");
                return;
            }
            Set<Long> set = map.get(str2);
            if (set == null) {
                set = new HashSet<>(10);
            }
            set.add(Long.valueOf(j));
            this.mOnWaitCallbacks.put(str2, set);
        }
    }

    @JavascriptInterface
    public void registerConnectListener(long j) {
        this.mConnectListenerId = j;
        pushCurrentDeviceToH5();
    }

    private void pushCurrentDeviceToH5() {
        callbackConnectListener(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackConnectListener(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h(this.TAG, "callbackConnectListener, deviceInfo is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", deviceInfo.getDeviceIdentify());
            jSONObject.put("name", deviceInfo.getDeviceName());
            jSONObject.put("state", deviceInfo.getDeviceConnectState());
            onSuccessCallback(this.mConnectListenerId, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "callbackConnectListener err: ", e.getMessage());
        }
    }

    @JavascriptInterface
    public void isBindHuaweiDevice(long j) {
        LogUtil.a(this.TAG, "isBindHuaweiDevice");
        PluginBaseAdapter adapter = PluginOperation.getInstance(BaseApplication.getContext()).getAdapter();
        PluginOperationAdapter initAdapter = adapter instanceof PluginOperationAdapter ? (PluginOperationAdapter) adapter : WebViewHelp.initAdapter();
        if (initAdapter == null) {
            LogUtil.a(this.TAG, "isBindHuaweiDevice adapter is null");
            onSuccessCallback(j, false);
        } else {
            onSuccessCallback(j, Boolean.valueOf(initAdapter.isBindDevice()));
        }
    }

    @JavascriptInterface
    public void queryBloodPressureDeviceStatus(final long j) {
        LogUtil.a(this.TAG, "queryBloodPressureDeviceStatus");
        cbi.b(new ResponseCallback<JSONObject>() { // from class: com.huawei.operation.h5pro.jsmodules.Device.4
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i, JSONObject jSONObject) {
                if (i == 0) {
                    Device.this.onSuccessCallback(j, jSONObject);
                } else {
                    Device.this.onFailureCallback(j, "");
                }
            }
        });
    }

    @JavascriptInterface
    public void queryHealthSwitchStatus(final long j, String str) {
        JSONArray jSONArray;
        LogUtil.a(this.TAG, "openHealthSwitch keyJson:", str);
        try {
            jSONArray = new JSONObject(str).optJSONArray(KEY_SWITCH_KEYS);
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "openHealthSwitch JSONException");
            jSONArray = null;
        }
        if (jSONArray == null || jSONArray.length() == 0) {
            onFailureCallback(j, "H5 param is error", -1);
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (TextUtils.isEmpty(optString)) {
                onFailureCallback(j, "H5 param key is error", -1);
                return;
            }
            arrayList.add(optString);
        }
        cun.c().getHealthSwitch(arrayList, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.Device.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a(Device.this.TAG, "getHealthSwitch errorCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    Device.this.onSuccessCallback(j, obj);
                } else if (i2 == -2) {
                    Device.this.onFailureCallback(j, "get health switch no connect device", i2);
                } else {
                    Device.this.onFailureCallback(j, "get health switch other failed", i2);
                }
            }
        });
    }

    @JavascriptInterface
    public void setHealthSwitch(final long j, String str) {
        JSONObject jSONObject;
        LogUtil.a(this.TAG, "setHealthSwitch keyJson:", str);
        try {
            jSONObject = new JSONObject(str).optJSONObject(KEY_SWITCH_KEYS);
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "setHealthSwitch JSONException");
            jSONObject = null;
        }
        if (jSONObject == null || jSONObject.length() == 0) {
            onFailureCallback(j, "H5 param is error", -1);
        } else {
            cun.c().setHealthSwitch(jSONObject, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.Device.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a(Device.this.TAG, "setHealthSwitch errorCode:", Integer.valueOf(i));
                    if (i == 0) {
                        Device.this.onSuccessCallback(j, obj);
                    } else if (i == -2) {
                        Device.this.onFailureCallback(j, "set health switch no connect device", i);
                    } else {
                        Device.this.onFailureCallback(j, "set health switch other failed", i);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void getConnectDeviceStatus(long j) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
        if (deviceInfo == null) {
            LogUtil.h(this.TAG, "getConnectDeviceStatus deviceInfo is null");
            onSuccessCallback(j, 0);
        } else {
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            LogUtil.a(this.TAG, "getConnectDeviceStatus connectStatus:", Integer.valueOf(deviceConnectState));
            onSuccessCallback(j, Integer.valueOf(deviceConnectState));
        }
    }

    @JavascriptInterface
    public void getBondedDevice(long j, String str) {
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.a("PluginDevice", HealthDeviceEntryApi.class);
        if (healthDeviceEntryApi == null) {
            ReleaseLogUtil.a(this.TAG, "getBondedDevice api is null productId ", str);
            onFailureCallback(j, "");
            return;
        }
        HealthDevice bondedDevice = healthDeviceEntryApi.getBondedDevice(str);
        LogUtil.a(this.TAG, "getBondedDevice device ", bondedDevice, " productId ", str, " callbackId ", Long.valueOf(j));
        if (bondedDevice == null) {
            onSuccessCallback(j, "");
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("name", bondedDevice.getDeviceName());
        onSuccessCallback(j, hashMap);
    }

    @JavascriptInterface
    public void getBondedDeviceList(long j, String str) {
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.a("PluginDevice", HealthDeviceEntryApi.class);
        if (healthDeviceEntryApi == null || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a(this.TAG, "getBondedDeviceList api is null kindName ", str);
            onFailureCallback(j, "");
            return;
        }
        HealthDevice.HealthDeviceKind healthDeviceKind = null;
        for (HealthDevice.HealthDeviceKind healthDeviceKind2 : HealthDevice.HealthDeviceKind.values()) {
            if (healthDeviceKind2 != null && str.equals(healthDeviceKind2.name())) {
                healthDeviceKind = healthDeviceKind2;
            }
        }
        ArrayList<ContentValues> bondedDeviceByDeviceKind = healthDeviceEntryApi.getBondedDeviceByDeviceKind(healthDeviceKind);
        LogUtil.a(this.TAG, "getBondedDeviceList deviceList ", bondedDeviceByDeviceKind, " kindName ", str);
        if (CollectionUtils.d(bondedDeviceByDeviceKind)) {
            onSuccessCallback(j, "");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ContentValues> it = bondedDeviceByDeviceKind.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            if (next != null) {
                HashMap hashMap = new HashMap(6);
                hashMap.put(CommonConstant.KEY_DISPLAY_NAME, next.getAsString(CommonConstant.KEY_DISPLAY_NAME));
                hashMap.put("name", next.getAsString("name"));
                hashMap.put(Wpt.MODE, next.getAsString(Wpt.MODE));
                hashMap.put("kind", next.getAsString("kind"));
                hashMap.put("productId", next.getAsString("productId"));
                hashMap.put("auto", next.getAsString("auto"));
                arrayList.add(hashMap);
            }
        }
        LogUtil.a(this.TAG, "getBondedDeviceList list ", arrayList, " callbackId ", Long.valueOf(j));
        onSuccessCallback(j, arrayList);
    }

    @JavascriptInterface
    public void connectLatestScale() {
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.a("PluginDevice", HealthDeviceEntryApi.class);
        ReleaseLogUtil.b(this.TAG, "connectLatestScale api ", healthDeviceEntryApi);
        if (healthDeviceEntryApi == null) {
            return;
        }
        healthDeviceEntryApi.startConnectLatestScale();
    }

    @JavascriptInterface
    public void getProductInfoList(long j, String str) {
        LogUtil.a(this.TAG, "getProductInfoList param ", str, " callbackId ", Long.valueOf(j));
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(MedalConstants.EVENT_KEY);
            String optString2 = jSONObject.optString("value");
            ProductMapParseUtil.b(BaseApplication.getContext());
            List<ProductMapInfo> b = ProductMap.b(optString, optString2);
            ArrayList arrayList = new ArrayList();
            for (ProductMapInfo productMapInfo : b) {
                if (productMapInfo != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("deviceId", Integer.valueOf(productMapInfo.c()));
                    arrayList.add(hashMap);
                }
            }
            LogUtil.a(this.TAG, "getProductInfoList key ", optString, " value ", optString2, " productInfoList ", b);
            onSuccessCallback(j, arrayList);
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c(this.TAG, "getProductInfoList exception ", d);
            onFailureCallback(j, d);
        }
    }

    @JavascriptInterface
    public void getWearDeviceInfo(long j) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
        if (deviceInfo == null) {
            LogUtil.h(this.TAG, "device info null");
            onSuccessCallback(j, "");
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("name", deviceInfo.getDeviceName());
        hashMap.put("deviceId", deviceInfo.getUuid());
        hashMap.put(BleConstants.KEY_CONNECTSTATE, String.valueOf(deviceInfo.getDeviceConnectState()));
        onSuccessCallback(j, hashMap);
    }

    @JavascriptInterface
    public void startMoreDeviceActivity(long j) {
        LogUtil.a(this.TAG, "startMoreDeviceActivity");
        ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).startMoreDeviceActivity(this.mContext);
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void startAddHeartRateDevice(long j) {
        LogUtil.a(this.TAG, "startAddHeartRateDevice");
        ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).startAddHeartRateDevice(this.mContext);
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void getDeviceSportStatus(final long j) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.Device$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Device.this.m716xdf602cf2(j);
            }
        });
    }

    /* renamed from: lambda$getDeviceSportStatus$1$com-huawei-operation-h5pro-jsmodules-Device, reason: not valid java name */
    /* synthetic */ void m716xdf602cf2(final long j) {
        ReleaseLogUtil.b(this.TAG, "getDeviceSportStatus callbackId:", Long.valueOf(j));
        ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).getDeviceSportStatus(new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.Device$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                Device.this.m715x44bf6a71(j, i, obj);
            }
        });
    }

    /* renamed from: lambda$getDeviceSportStatus$0$com-huawei-operation-h5pro-jsmodules-Device, reason: not valid java name */
    /* synthetic */ void m715x44bf6a71(long j, int i, Object obj) {
        ReleaseLogUtil.b(this.TAG, "getDeviceSportStatus errorCode:", Integer.valueOf(i), "objData:", obj);
        onSuccessCallback(j, obj);
    }
}
