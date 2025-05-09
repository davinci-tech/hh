package com.huawei.hwdevice.phoneprocess.hihealthkit;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.Gson;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.IDetectCommonCallback;
import com.huawei.health.IKitWearAIDL;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.HeartDeviceInfo;
import com.huawei.hwdevice.mainprocess.service.SyncMusicService;
import com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinderUtil;
import com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.HwMusicMgrCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.blt;
import defpackage.ceu;
import defpackage.cfa;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.cwi;
import defpackage.jcg;
import defpackage.jqi;
import defpackage.jqj;
import defpackage.jsd;
import defpackage.jst;
import defpackage.jsz;
import defpackage.jtp;
import defpackage.jtq;
import defpackage.jtr;
import defpackage.juw;
import defpackage.jyp;
import defpackage.kau;
import defpackage.kbi;
import defpackage.kbl;
import defpackage.kbm;
import defpackage.kbr;
import defpackage.kcu;
import defpackage.kcy;
import defpackage.kdh;
import defpackage.ket;
import defpackage.knl;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class KitWearBinder extends IKitWearAIDL.Stub {
    private static IBaseCommonCallback e;
    private volatile HwMusicMgrCallback c = null;

    @Override // com.huawei.health.IKitWearAIDL
    public void getRealTimeData(String str, int i, int i2, final IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "IWearAIDL getRealTimeData callback is null");
            return;
        }
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "no device connected");
            return;
        }
        HwHeartRateManager.getInstance().remindSwitchHeartRateForKit(i, i2, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                try {
                    iBaseCommonCallback.onResponse(i3, "OpenOrClose state");
                } catch (RemoteException e2) {
                    LogUtil.b("KitWearBinder", "IWearAIDL getRealTimeData state e: ", e2.getMessage());
                }
            }
        }, str);
        if (i2 == 1) {
            c(str, iBaseCommonCallback);
            return;
        }
        if (i2 == 2) {
            HwHeartRateManager.getInstance().unregisterKitHeartRateCallback(str);
            return;
        }
        if (i2 == 3) {
            d(str, iBaseCommonCallback);
        } else if (i2 == 4) {
            HwHeartRateManager.getInstance().unregisterKitRriCallback(str);
        } else {
            LogUtil.h("KitWearBinder", "IWearAIDL openOrClose is else");
        }
    }

    private void d(String str, final IBaseCommonCallback iBaseCommonCallback) {
        HwHeartRateManager.getInstance().registerKitRriCallback(str, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.14
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj == null) {
                    return;
                }
                LogUtil.a("KitWearBinder", "IWearAIDL getRRI errorCode: ", Integer.valueOf(i));
                LogUtil.c("KitWearBinder", "IWearAIDL getRRI objectData: ", obj);
                try {
                    iBaseCommonCallback.onResponse(i, new JSONObject(obj.toString()).getString("value"));
                } catch (RemoteException e2) {
                    LogUtil.b("KitWearBinder", "IWearAIDL getRRI e: ", e2.getMessage());
                } catch (JSONException unused) {
                    LogUtil.b("KitWearBinder", "IWearAIDL getRRI JSONException");
                }
            }
        });
    }

    private void c(String str, final IBaseCommonCallback iBaseCommonCallback) {
        HwHeartRateManager.getInstance().registerKitHeartRateCallback(str, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.16
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj == null) {
                    return;
                }
                LogUtil.a("KitWearBinder", "IWearAIDL getHeartRate state: ", Integer.valueOf(i));
                LogUtil.c("KitWearBinder", "IWearAIDL getHeartRate objectData: ", obj);
                try {
                    iBaseCommonCallback.onResponse(i, new JSONArray(new JSONObject(obj.toString()).getString("value")).get(0).toString());
                } catch (RemoteException e2) {
                    LogUtil.b("KitWearBinder", "IWearAIDL getHeartRate e: ", e2.getMessage());
                } catch (JSONException unused) {
                    LogUtil.b("KitWearBinder", "IWearAIDL getHeartRate JSONException");
                }
            }
        });
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void getDeviceList(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        String str;
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "IWearAIDL getDeviceList callback is null");
            return;
        }
        String a2 = a();
        try {
            JSONArray jSONArray = new JSONArray(a2);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("deviceInfo")) {
                    JSONObject jSONObject2 = new JSONObject(jSONObject.getString("deviceInfo"));
                    jSONObject2.put("mDeviceIdentify", blt.b(jSONObject2.optString("mDeviceIdentify")));
                    jSONObject.put("deviceInfo", jSONObject2.toString());
                }
                jSONArray.put(i, jSONObject);
            }
            str = jSONArray.toString();
        } catch (JSONException e2) {
            LogUtil.b("KitWearBinder", "getHeartDeviceInfo json exception: ", e2.getMessage());
            str = a2;
        }
        LogUtil.a("KitWearBinder", "getDeviceList heartDeviceInfo:", str);
        iBaseCommonCallback.onResponse(0, a2);
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void sendDeviceCommand(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        JSONObject jSONObject;
        int i;
        if (str == null || iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "IWearAIDL sendDeviceCommand commandOptions or callback is null");
            return;
        }
        int i2 = 1;
        LogUtil.a("05", 1, "KitWearBinder", "sendDeviceCommand commandOptions is ", str);
        int i3 = 0;
        try {
            jSONObject = new JSONObject(str);
            try {
                i = jSONObject.optInt("openOrClose");
                try {
                    i3 = jSONObject.optInt("commandType");
                    if (jSONObject.has("operator_measure_source")) {
                        i2 = jSONObject.optInt("operator_measure_source");
                    }
                } catch (JSONException e2) {
                    e = e2;
                    ReleaseLogUtil.c("KITWEARRELEASE_KitWearBinder", "occur JSONException:", ExceptionUtils.d(e));
                    e(i3, iBaseCommonCallback, i, i2, str, jSONObject);
                }
            } catch (JSONException e3) {
                e = e3;
                i = 0;
                ReleaseLogUtil.c("KITWEARRELEASE_KitWearBinder", "occur JSONException:", ExceptionUtils.d(e));
                e(i3, iBaseCommonCallback, i, i2, str, jSONObject);
            }
        } catch (JSONException e4) {
            e = e4;
            jSONObject = null;
        }
        e(i3, iBaseCommonCallback, i, i2, str, jSONObject);
    }

    private void e(int i, IBaseCommonCallback iBaseCommonCallback, int i2, int i3, String str, JSONObject jSONObject) {
        if (i == 2) {
            if (e(iBaseCommonCallback, i2)) {
                return;
            }
            c(iBaseCommonCallback, i2);
            return;
        }
        if (i == 3) {
            if (b(iBaseCommonCallback, i2)) {
                return;
            }
            e(iBaseCommonCallback, i2, i3);
            return;
        }
        if (i == 4) {
            b(iBaseCommonCallback);
            return;
        }
        if (i == 10) {
            m(iBaseCommonCallback);
            return;
        }
        if (i == 100) {
            a(str, iBaseCommonCallback);
            return;
        }
        if (i == 300) {
            d(iBaseCommonCallback);
            return;
        }
        if (i == 800) {
            e(str, iBaseCommonCallback);
            return;
        }
        if (i == 200) {
            a(iBaseCommonCallback, i2);
            return;
        }
        if (i == 201) {
            a(iBaseCommonCallback);
            return;
        }
        if (i == 600) {
            d(str);
        } else if (i == 601) {
            b();
        } else {
            a(iBaseCommonCallback, i, jSONObject);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(java.lang.String r8, com.huawei.health.IBaseCommonCallback r9) {
        /*
            r7 = this;
            java.lang.String r0 = "KitWearBinder"
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L1c
            r2.<init>(r8)     // Catch: org.json.JSONException -> L1c
            java.lang.String r8 = "serviceId"
            int r8 = r2.optInt(r8)     // Catch: org.json.JSONException -> L1c
            java.lang.String r3 = "commandId"
            int r1 = r2.optInt(r3)     // Catch: org.json.JSONException -> L1d
            java.lang.String r3 = "deviceCommand"
            java.lang.String r2 = r2.optString(r3)     // Catch: org.json.JSONException -> L1d
            goto L29
        L1c:
            r8 = r1
        L1d:
            java.lang.String r2 = "sendCommandForTheme JSONException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
            java.lang.String r2 = ""
        L29:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            java.lang.String r4 = ", commandId:"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            java.lang.String r6 = "sendCommandForTheme, serviceId:"
            java.lang.Object[] r3 = new java.lang.Object[]{r6, r3, r4, r5}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L84
            com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.e = r9
            com.huawei.datatype.DeviceCommand r9 = new com.huawei.datatype.DeviceCommand
            r9.<init>()
            r9.setServiceID(r8)     // Catch: java.lang.NumberFormatException -> L79
            r9.setCommandID(r1)     // Catch: java.lang.NumberFormatException -> L79
            byte[] r8 = defpackage.cvx.a(r2)
            r9.setDataContent(r8)
            byte[] r8 = defpackage.cvx.a(r2)
            int r8 = r8.length
            r9.setDataLen(r8)
            java.lang.String r8 = "theme deviceCommand:"
            java.lang.String r1 = r9.toString()
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r1}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r8)
            android.content.Context r8 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface r8 = defpackage.jsz.b(r8)
            r8.sendDeviceData(r9)
            goto L8d
        L79:
            java.lang.String r8 = "sendCommandForTheme, NumberFormatException"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
            return
        L84:
            java.lang.String r8 = "IWearAIDL sendDeviceCommand deviceCommand is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r8)
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.e(java.lang.String, com.huawei.health.IBaseCommonCallback):void");
    }

    private void a(IBaseCommonCallback iBaseCommonCallback, int i, JSONObject jSONObject) {
        if (i == 5) {
            if (CommonUtil.bv()) {
                return;
            }
            i(iBaseCommonCallback);
            return;
        }
        if (i == 6) {
            e(iBaseCommonCallback);
            return;
        }
        if (i == 400) {
            n(iBaseCommonCallback);
            return;
        }
        if (i == 505) {
            f(iBaseCommonCallback);
            return;
        }
        if (i == 900) {
            c(iBaseCommonCallback, jSONObject);
            return;
        }
        if (i != 901) {
            switch (i) {
                case 500:
                    d(iBaseCommonCallback, jSONObject);
                    break;
                case 501:
                    e(iBaseCommonCallback, jSONObject);
                    break;
                case TypedValues.PositionType.TYPE_DRAWPATH /* 502 */:
                    h(iBaseCommonCallback);
                    break;
                case 503:
                    g(iBaseCommonCallback);
                    break;
                default:
                    try {
                        iBaseCommonCallback.onResponse(500000, "");
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "checkOtherCommandType RemoteException");
                    }
                    ReleaseLogUtil.c("KITWEARRELEASE_KitWearBinder", "default else:", Integer.valueOf(i));
                    break;
            }
            return;
        }
        b(iBaseCommonCallback, jSONObject);
    }

    private void e(IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("KitWearBinder", "getBatteryValue enter");
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "KitWearBinder");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        if (deviceInfo == null) {
            try {
                iBaseCommonCallback.onResponse(-1, "");
                return;
            } catch (RemoteException unused) {
                LogUtil.b("KitWearBinder", "getBatteryValue Exception");
                return;
            }
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            return;
        }
        jtq.e().a(deviceIdentify, iBaseCommonCallback);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(8);
        deviceCommand.setmIdentify(deviceIdentify);
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void c(final IBaseCommonCallback iBaseCommonCallback, JSONObject jSONObject) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "no device connected.");
            return;
        }
        LinkedList<kbm> linkedList = new LinkedList<>();
        try {
            if (jSONObject == null) {
                LogUtil.h("KitWearBinder", "startDataDictionary jsonObject valid");
                return;
            }
            LogUtil.a("KitWearBinder", "startDataDictionary jsonString", jSONObject.toString());
            JSONArray jSONArray = jSONObject.getJSONArray("digitTypeIds");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                int i2 = jSONArray.getInt(i);
                kbm kbmVar = new kbm();
                kbmVar.d(i2);
                linkedList.add(kbmVar);
            }
            if (linkedList.size() == 0) {
                try {
                    iBaseCommonCallback.onResponse(-1, "");
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "startDataDictionary has error remote exception");
                    return;
                }
            }
            kbr.d().c(linkedList, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.17
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj) {
                    ReleaseLogUtil.e("KITWEARRELEASE_KitWearBinder", "startToSyncByHealthKit result: ", Integer.valueOf(i3));
                    try {
                        iBaseCommonCallback.onResponse(i3, "");
                    } catch (RemoteException e2) {
                        ReleaseLogUtil.c("KITWEARRELEASE_KitWearBinder", "startToSyncByHealthKit RemoteException: ", e2.getMessage());
                    }
                }
            });
        } catch (JSONException unused2) {
            LogUtil.b("KitWearBinder", "checkJson startDataDictionary JSONException");
            try {
                iBaseCommonCallback.onResponse(-1, "");
            } catch (RemoteException unused3) {
                LogUtil.b("KitWearBinder", "startDataDictionary has error remote exception");
            }
        }
    }

    private void b(IBaseCommonCallback iBaseCommonCallback, JSONObject jSONObject) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "no device connected.");
            return;
        }
        int e2 = ket.a().e();
        LogUtil.a("KitWearBinder", "querySyncStatus errorCode:", Integer.valueOf(e2));
        try {
            iBaseCommonCallback.onResponse(e2, "");
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "querySyncStatus has error remote exception");
        }
    }

    private void f(IBaseCommonCallback iBaseCommonCallback) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "getDeviceSn no device connected.");
            return;
        }
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "KitWearBinder");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        JSONObject jSONObject = new JSONObject();
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
            try {
                jSONObject.put(HealthEngineRequestManager.PARAMS_DEVICE_SN, deviceInfo.getSecurityDeviceId());
                LogUtil.a("KitWearBinder", "getDeviceSn success");
            } catch (JSONException unused) {
                LogUtil.b("KitWearBinder", "getDeviceSn JSON exception");
            }
        }
        if (!TextUtils.isEmpty(jSONObject.toString())) {
            try {
                iBaseCommonCallback.onResponse(0, jSONObject.toString());
            } catch (RemoteException unused2) {
                LogUtil.b("KitWearBinder", "getDeviceSn remote exception");
            }
        } else {
            try {
                iBaseCommonCallback.onResponse(-1, "");
            } catch (RemoteException unused3) {
                LogUtil.b("KitWearBinder", "getDeviceSn has error remote exception");
            }
        }
    }

    private void i(final IBaseCommonCallback iBaseCommonCallback) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "getSwitchStatus no device connected");
        } else {
            kcy.e(BaseApplication.getContext()).e(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.20
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        iBaseCommonCallback.onResponse(i, obj instanceof String ? (String) obj : "");
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "getSwitchStatus remoteException");
                    }
                }
            });
        }
    }

    private void g(final IBaseCommonCallback iBaseCommonCallback) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "getEcgServiceIv no device connected");
        } else {
            LogUtil.a("KitWearBinder", "enter getEcgServiceIv");
            kcy.e(BaseApplication.getContext()).a(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.23
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    String str;
                    if (obj instanceof String) {
                        str = (String) obj;
                        LogUtil.a("KitWearBinder", "getEcgServiceIv errorCode: ", Integer.valueOf(i), "data: ", str);
                    } else {
                        str = "";
                    }
                    try {
                        iBaseCommonCallback.onResponse(i, str);
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "getSwitchStatus remoteException");
                    }
                }
            });
        }
    }

    private void d(final IBaseCommonCallback iBaseCommonCallback) {
        kau.b().e(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.25
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                try {
                    if (obj instanceof String) {
                        String str = (String) obj;
                        LogUtil.a("KitWearBinder", "getAppMarketList code: ", Integer.valueOf(i), "data: ", str);
                        iBaseCommonCallback.onResponse(i, str);
                    } else {
                        iBaseCommonCallback.onResponse(i, "");
                    }
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "getAppMarketList remote exception");
                }
            }
        });
    }

    private boolean b(IBaseCommonCallback iBaseCommonCallback, int i) {
        if (i == 1 || i == 2) {
            return false;
        }
        try {
            c();
            iBaseCommonCallback.onResponse(500000, "");
            return true;
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "handleTag openOrClose exception");
            return false;
        }
    }

    private boolean e(IBaseCommonCallback iBaseCommonCallback, int i) {
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            return false;
        }
        try {
            c();
            iBaseCommonCallback.onResponse(500000, "");
            return true;
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "handleTag openOrClose exception");
            return false;
        }
    }

    private void c() {
        LogUtil.h("05", 1, "KitWearBinder", "writeErrorSwitchParamLog openOrClose is invalid");
    }

    private void e(final IBaseCommonCallback iBaseCommonCallback, int i, int i2) {
        kcy.e(BaseApplication.getContext()).b(i, i2, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.22
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                try {
                    iBaseCommonCallback.onResponse(i3, "");
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "setAtrialSingleMeasure setAtrialSingleMeasure exception");
                }
            }
        });
    }

    private void c(final IBaseCommonCallback iBaseCommonCallback, int i) {
        kcy.e(BaseApplication.getContext()).d(i, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.21
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                try {
                    iBaseCommonCallback.onResponse(i2, "");
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "IWearAIDL setAtrialAutoMeasureStatus exception");
                }
            }
        });
    }

    private void a(String str, IBaseCommonCallback iBaseCommonCallback) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "no device connected.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int[] iArr = {jSONObject.optInt("startTime"), jSONObject.optInt("endTime")};
            int optInt = jSONObject.optInt("fileType");
            boolean optBoolean = jSONObject.optBoolean("isNeedVerify", false);
            String optString = jSONObject.optString(ContentResource.FILE_NAME, null);
            LogUtil.a("KitWearBinder", "isNeedVerify: ", Boolean.valueOf(optBoolean));
            jqj jqjVar = new jqj();
            jqjVar.a(optString);
            jqjVar.d(optInt);
            jqjVar.e(iArr);
            jqjVar.a(optBoolean);
            jqjVar.c((String) null);
            jyp.c().a(jqjVar, iBaseCommonCallback);
        } catch (JSONException unused) {
            LogUtil.b("KitWearBinder", "getFileInfo exception");
        }
    }

    private void d(final IBaseCommonCallback iBaseCommonCallback, JSONObject jSONObject) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "setEcgAuthSwitch no device connected.");
        } else {
            kcy.e(BaseApplication.getContext()).c(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        iBaseCommonCallback.onResponse(i, obj instanceof String ? (String) obj : "");
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "IWearAIDL setEcgAuthSwitch exception");
                    }
                }
            }, jSONObject);
        }
    }

    private void e(final IBaseCommonCallback iBaseCommonCallback, JSONObject jSONObject) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "setEcgAuthSwitch no device connected.");
        } else {
            kcy.e(BaseApplication.getContext()).e(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        iBaseCommonCallback.onResponse(i, obj instanceof String ? (String) obj : "");
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "IWearAIDL setForbiddenSwitch exception");
                    }
                }
            }, jSONObject);
        }
    }

    private void h(final IBaseCommonCallback iBaseCommonCallback) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "setEcgAuthSwitch no device connected.");
        } else {
            kcy.e(BaseApplication.getContext()).c(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        iBaseCommonCallback.onResponse(i, obj instanceof String ? (String) obj : "");
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "IWearAIDL getEcgAuth exception");
                    }
                }
            });
        }
    }

    private void n(IBaseCommonCallback iBaseCommonCallback) {
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "no device connected.");
        } else {
            LogUtil.a("KitWearBinder", "enter KitCoreSleepManager startCoreSleep");
            jtp.a().b(iBaseCommonCallback);
        }
    }

    private void d(String str) {
        kcu.d().d(str);
    }

    private void b() {
        kcu.d().e();
    }

    private boolean o(IBaseCommonCallback iBaseCommonCallback) {
        DeviceInfo deviceInfo;
        Iterator<DeviceInfo> it = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "KitWearBinder").iterator();
        while (true) {
            if (!it.hasNext()) {
                deviceInfo = null;
                break;
            }
            deviceInfo = it.next();
            if (deviceInfo.getDeviceConnectState() == 2 && !cvt.c(deviceInfo.getProductType())) {
                break;
            }
        }
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("KitWearBinder", "getFileInfo get device info error");
            try {
                iBaseCommonCallback.onResponse(300004, null);
            } catch (RemoteException unused) {
                LogUtil.b("KitWearBinder", "getFileInfo no device remote exception");
            }
            return false;
        }
        if (!jcg.d(deviceInfo.getDeviceIdentify()) && !jst.s(deviceInfo.getDeviceIdentify())) {
            return true;
        }
        LogUtil.h("KitWearBinder", "getFileInfo get device is Otaing");
        try {
            iBaseCommonCallback.onResponse(22000, null);
        } catch (RemoteException unused2) {
            LogUtil.b("KitWearBinder", "getFileInfo device is Otaing remote exception");
        }
        return false;
    }

    private void b(final IBaseCommonCallback iBaseCommonCallback) {
        kcy.e(BaseApplication.getContext()).b(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                try {
                    iBaseCommonCallback.onResponse(i, "");
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "IWearAIDL clearAtrialData exception");
                }
            }
        });
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void registerSingleAtrialCallback(final IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "IWearAIDL registerSingleAtrialCallback callback is null");
        } else {
            kcy.e(BaseApplication.getContext()).d(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (obj == null) {
                        LogUtil.h("KitWearBinder", "IWearAIDL registerSingleAtrialCallback objectData is null");
                        return;
                    }
                    try {
                        LogUtil.c("05", 1, "KitWearBinder", "registerSingleAtrialCallback error code is ", Integer.valueOf(i), "data: ", obj);
                        if (obj instanceof String) {
                            iBaseCommonCallback.onResponse(i, (String) obj);
                        } else {
                            iBaseCommonCallback.onResponse(i, "");
                        }
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "IWearAIDL registerSingleAtrialCallback exception");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void unRegisterSingleAtrialCallback(final IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "IWearAIDL unRegisterSingleAtrialCallback callback is null");
        } else {
            kcy.e(BaseApplication.getContext()).j(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        if (i == 0) {
                            LogUtil.a("05", 1, "KitWearBinder", "unRegisterSingleAtrialCallback error code is ", Integer.valueOf(i));
                            iBaseCommonCallback.onResponse(i, "");
                        } else {
                            iBaseCommonCallback.onResponse(-1, "");
                        }
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "IWearAIDL unRegisterSingleAtrialCallback exception");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void writeToWear(String str, String str2, byte[] bArr, String str3, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "writeToWear callback is null");
            return;
        }
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                d();
                if (!"huaweiOnlineMusic".equals(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        jSONObject.putOpt("package", str);
                        str2 = jSONObject.toString();
                    } catch (JSONException unused) {
                        LogUtil.b("KitWearBinder", "writeToWear, JSONException");
                        iBaseCommonCallback.onResponse(1, "writeToWear json error");
                        return;
                    }
                }
                c(str2, bArr, str3, iBaseCommonCallback);
                return;
            }
            iBaseCommonCallback.onResponse(2, "inputDescriptionString is empty");
        } catch (RemoteException unused2) {
            LogUtil.b("KitWearBinder", "writeToWear RemoteException");
        }
    }

    private void d() {
        if (this.c == null) {
            startSyncMusicService();
            try {
                Thread.sleep(300L);
            } catch (InterruptedException unused) {
                LogUtil.b("KitWearBinder", "startSyncMusicService InterruptedException");
            }
        }
    }

    private void c(String str, byte[] bArr, String str2, IBaseCommonCallback iBaseCommonCallback) {
        try {
            String optString = new JSONObject(str).optString(ParsedFieldTag.ACTION_TYPE);
            char c = 0;
            LogUtil.a("KitWearBinder", "actionType: ", optString, " sizeAndFinished: ", str2);
            switch (optString.hashCode()) {
                case -1149503253:
                    if (optString.equals("deleteAudio")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1016530986:
                    if (optString.equals("accountNotify")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -771961760:
                    if (optString.equals("sendBatchInfo")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1957230553:
                    if (optString.equals("sendSongFile")) {
                        break;
                    }
                    c = 65535;
                    break;
                case 2036285271:
                    if (optString.equals("cancleTransfer")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                b(str, bArr, str2, iBaseCommonCallback);
                return;
            }
            if (c == 1 || c == 2 || c == 3 || c == 4) {
                b(str, iBaseCommonCallback);
            } else {
                iBaseCommonCallback.onResponse(500001, "");
            }
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "endToWear RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("KitWearBinder", "hwMusicOperate JSONException");
            try {
                iBaseCommonCallback.onResponse(1, "JSON ERROR");
            } catch (RemoteException unused3) {
                LogUtil.b("KitWearBinder", "endToWear onResponse RemoteException");
            }
        }
    }

    private boolean a(byte[] bArr, IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("KitWearBinder", "isDataEmpty enter");
        if (bArr != null && bArr.length != 0) {
            return false;
        }
        try {
            iBaseCommonCallback.onResponse(2, "");
            return true;
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "isDataEmpty RemoteException");
            return true;
        }
    }

    private void b(String str, byte[] bArr, String str2, IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("KitWearBinder", "saveOneMusicInfo enter");
        if (a(bArr, iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "saveOneMusicInfo failed, data is empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            try {
                iBaseCommonCallback.onResponse(2, "sizeAndFinished is null");
                return;
            } catch (RemoteException unused) {
                LogUtil.b("KitWearBinder", "sizeAndFinished RemoteException");
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            boolean optBoolean = jSONObject.optBoolean("is_finished");
            int optInt = jSONObject.optInt("size");
            String optString = new JSONObject(str).optString("musicId");
            if (optBoolean) {
                c(bArr, optString);
                int a2 = KitWearBinderUtil.a(optString);
                LogUtil.a("KitWearBinder", "saveOneMusicInfo finished, musicSize: ", Integer.valueOf(a2));
                if (a2 == optInt) {
                    if (!j(iBaseCommonCallback)) {
                        ReleaseLogUtil.e("KITWEARRELEASE_KitWearBinder", "Interrupt send music");
                        e();
                        return;
                    } else {
                        LogUtil.a("KitWearBinder", "saveOneMusicInfo succeed");
                        b(str, iBaseCommonCallback);
                        return;
                    }
                }
                LogUtil.h("KitWearBinder", "saveOneMusicInfo failed! Delete temp file");
                c(optString);
                iBaseCommonCallback.onResponse(1, "musicSize != size");
                return;
            }
            c(bArr, optString);
        } catch (RemoteException unused2) {
            LogUtil.b("KitWearBinder", "saveOneMusicInfo RemoteException");
        } catch (JSONException unused3) {
            LogUtil.b("KitWearBinder", "saveOneMusicInfo exception");
            try {
                iBaseCommonCallback.onResponse(1, "JSON ERROR");
            } catch (RemoteException unused4) {
                LogUtil.b("KitWearBinder", "endToWear onResponse RemoteException");
            }
        }
    }

    private boolean j(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        DeviceInfo d = KitWearBinderUtil.d("saveOneMusicInfo");
        if (d == null) {
            ReleaseLogUtil.d("KITWEARRELEASE_KitWearBinder", "hasStoragePermission deviceInfo is null, continue sending");
            return true;
        }
        if (!KitWearBinderUtil.a(d)) {
            ReleaseLogUtil.e("KITWEARRELEASE_KitWearBinder", "hasStoragePermission not supported, continue sending");
            return true;
        }
        int i = AnonymousClass18.e[KitWearBinderUtil.b(d).ordinal()];
        if (i == 1) {
            iBaseCommonCallback.onResponse(1025, "device storage permission denied");
            return false;
        }
        if (i != 2) {
            return true;
        }
        iBaseCommonCallback.onResponse(1, "get device storage permission status failed");
        return false;
    }

    /* renamed from: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder$18, reason: invalid class name */
    static /* synthetic */ class AnonymousClass18 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[KitWearBinderUtil.DevicePermissionResult.values().length];
            e = iArr;
            try {
                iArr[KitWearBinderUtil.DevicePermissionResult.DENIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[KitWearBinderUtil.DevicePermissionResult.OTHER_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void e() {
        b("{\"actionType\":\"cancleTransfer\"}", new IBaseCommonCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.7
            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i, String str) throws RemoteException {
                ReleaseLogUtil.e("KITWEARRELEASE_KitWearBinder", "cancelTransfer result: ", Integer.valueOf(i), ", reason: ", str);
            }
        });
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("KitWearBinder", "deleteTempMusicFile, musicFile is empty");
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.a("KitWearBinder", "deleteTempMusicFile: ", str, "isSuccess: ", Boolean.valueOf(BaseApplication.getContext().deleteFile(str)));
            return;
        }
        try {
            if (file.delete()) {
                LogUtil.a("KitWearBinder", "deleteTempMusicFile: ", str);
            } else {
                LogUtil.h("KitWearBinder", "delete fail: ", str);
            }
        } catch (SecurityException unused) {
            LogUtil.b("KitWearBinder", "SecurityException");
        }
    }

    private void c(byte[] bArr, String str) {
        LogUtil.a("KitWearBinder", "writeDataToFile proceeding");
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = BaseApplication.getContext().openFileOutput(str, 32768);
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b("KitWearBinder", "proceeding close error IOException");
                    }
                }
            } catch (IOException unused2) {
                LogUtil.b("KitWearBinder", "saveOneMusicInfo IOException");
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b("KitWearBinder", "proceeding close error IOException");
                    }
                }
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("KitWearBinder", "proceeding close error IOException");
                }
            }
            throw th;
        }
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void readFromWear(String str, String str2, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "readFromWear callback is null");
            return;
        }
        LogUtil.a("KitWearBinder", "readFromWear enter,inputType: ", str, ",fileDescriptionString: ", str2);
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                d();
                if (!"huaweiOnlineMusic".equals(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        jSONObject.putOpt("package", str);
                        str2 = jSONObject.toString();
                    } catch (JSONException unused) {
                        LogUtil.b("KitWearBinder", "readFromWear, JSONException");
                        iBaseCommonCallback.onResponse(1, "readFromWear json error");
                        return;
                    }
                }
                b(str2, iBaseCommonCallback);
                return;
            }
            iBaseCommonCallback.onResponse(1, "inputType or fileDescriptionString is empty");
        } catch (RemoteException unused2) {
            LogUtil.b("KitWearBinder", "readFromWear RemoteException");
        }
    }

    private void b(final String str, final IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("KitWearBinder", "sendCommandsToDevice enter");
        HwMusicMgrCallback hwMusicMgrCallback = this.c;
        try {
            if (hwMusicMgrCallback == null) {
                iBaseCommonCallback.onResponse(1, "not support music");
                LogUtil.h("KitWearBinder", "sendCommandsToDevice, mMusicMgrCallback is null");
            } else {
                hwMusicMgrCallback.executeCommand(str, new IBaseCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.8
                    @Override // com.huawei.hwservicesmgr.IBaseCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        LogUtil.a("KitWearBinder", "enter errorCode: ", Integer.valueOf(i));
                        iBaseCommonCallback.onResponse(i, str2);
                        KitWearBinder.this.d(i, str);
                    }
                });
            }
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "RemoteException");
            startSyncMusicService();
            HwMusicMgrCallback hwMusicMgrCallback2 = this.c;
            if (hwMusicMgrCallback2 != null) {
                try {
                    hwMusicMgrCallback2.executeCommand(str, new IBaseCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.10
                        @Override // com.huawei.hwservicesmgr.IBaseCallback
                        public void onResponse(int i, String str2) throws RemoteException {
                            LogUtil.a("KitWearBinder", "enter errCode: ", Integer.valueOf(i), "str: ", str2);
                            iBaseCommonCallback.onResponse(i, str2);
                        }
                    });
                } catch (RemoteException unused2) {
                    LogUtil.b("KitWearBinder", "RemoteException after reStartService");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        if (i == 0) {
            Iterator<String> it = e(str).iterator();
            while (it.hasNext()) {
                c(it.next());
            }
        }
    }

    private List<String> e(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("sendBatchInfo".equals(jSONObject.getString(ParsedFieldTag.ACTION_TYPE))) {
                JSONArray jSONArray = jSONObject.getJSONArray("inputDescription");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        String optString = jSONObject2.optString("musicId");
                        if (!TextUtils.isEmpty(optString)) {
                            arrayList.add(optString);
                        } else {
                            LogUtil.h("KitWearBinder", "getMusicIdByFileDescription musicId is null");
                        }
                    } else {
                        LogUtil.h("KitWearBinder", "getMusicIdByFileDescription item is null");
                    }
                }
            }
        } catch (JSONException e2) {
            LogUtil.b("KitWearBinder", "getMusicIdByFileDescription JSONException: " + e2.getMessage());
        }
        return arrayList;
    }

    public void registerMusicMgrCallback(HwMusicMgrCallback hwMusicMgrCallback) {
        if (hwMusicMgrCallback == null) {
            LogUtil.h("KitWearBinder", "registMuiscMgrCallback callback is null");
        } else {
            LogUtil.a("KitWearBinder", "enter registMuiscMgrCallback");
            this.c = hwMusicMgrCallback;
        }
    }

    public void unRegisterMusicMgrCallback() {
        LogUtil.a("KitWearBinder", "enter unRegisterMusicMgrCallback");
        this.c = null;
    }

    public void startSyncMusicService() {
        LogUtil.a("KitWearBinder", "startSyncMusicService enter");
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getContext(), SyncMusicService.class);
        intent.setAction("StartServiceFromHealthKit");
        try {
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException unused) {
            LogUtil.b("startSyncMusicService, IllegalStateException", new Object[0]);
        }
    }

    private void a(final IBaseCommonCallback iBaseCommonCallback, int i) {
        if (d(iBaseCommonCallback, i)) {
            c();
        } else {
            kdh.d().c(i, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.15
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    try {
                        iBaseCommonCallback.onResponse(i2, "");
                    } catch (RemoteException unused) {
                        LogUtil.b("KitWearBinder", "IWearAIDL setSleepBreatheSwitch exception");
                    }
                }
            });
        }
    }

    private boolean d(IBaseCommonCallback iBaseCommonCallback, int i) {
        if (i == 1 || i == 2 || i == 0) {
            return false;
        }
        try {
            iBaseCommonCallback.onResponse(500000, "");
            return true;
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "handleBreatheErrorParam openOrClose exception");
            return false;
        }
    }

    private void a(final IBaseCommonCallback iBaseCommonCallback) {
        kdh.d().e(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.12
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                try {
                    iBaseCommonCallback.onResponse(i, "");
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "IWearAIDL clearSleepBreatheData exception");
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0064 A[Catch: RemoteException -> 0x0089, TryCatch #0 {RemoteException -> 0x0089, blocks: (B:4:0x0006, B:7:0x0011, B:9:0x0017, B:12:0x001e, B:21:0x0047, B:23:0x005c, B:25:0x0064, B:27:0x002d, B:30:0x0037, B:33:0x0081), top: B:2:0x0004 }] */
    @Override // com.huawei.health.IKitWearAIDL
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void pushMsgToWearable(com.huawei.health.HiAppInfo r6, java.lang.String r7, java.lang.String r8, com.huawei.health.IBaseCommonCallback r9) {
        /*
            r5 = this;
            java.lang.String r0 = "KitWearBinder"
            r1 = 0
            r2 = 1
            if (r9 != 0) goto L11
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch: android.os.RemoteException -> L89
            java.lang.String r7 = "pushMsgToWearable：pushCallback is null"
            r6[r1] = r7     // Catch: android.os.RemoteException -> L89
            com.huawei.hwlogsmodel.LogUtil.h(r0, r6)     // Catch: android.os.RemoteException -> L89
            return
        L11:
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch: android.os.RemoteException -> L89
            if (r3 != 0) goto L81
            boolean r3 = android.text.TextUtils.isEmpty(r8)     // Catch: android.os.RemoteException -> L89
            if (r3 == 0) goto L1e
            goto L81
        L1e:
            int r3 = r7.hashCode()     // Catch: android.os.RemoteException -> L89
            r4 = -2113461736(0xffffffff82072218, float:-9.928018E-38)
            if (r3 == r4) goto L37
            r4 = 1294020898(0x4d213122, float:1.6902198E8)
            if (r3 == r4) goto L2d
            goto L41
        L2d:
            java.lang.String r3 = "INTERACTIVE_REMIND"
            boolean r3 = r7.equals(r3)     // Catch: android.os.RemoteException -> L89
            if (r3 == 0) goto L41
            r3 = r2
            goto L42
        L37:
            java.lang.String r3 = "hicarRemind"
            boolean r3 = r7.equals(r3)     // Catch: android.os.RemoteException -> L89
            if (r3 == 0) goto L41
            r3 = r1
            goto L42
        L41:
            r3 = -1
        L42:
            r4 = 2
            if (r3 == 0) goto L64
            if (r3 == r2) goto L5c
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: android.os.RemoteException -> L89
            java.lang.String r8 = "unsupported inputType is: "
            r6[r1] = r8     // Catch: android.os.RemoteException -> L89
            r6[r2] = r7     // Catch: android.os.RemoteException -> L89
            com.huawei.hwlogsmodel.LogUtil.h(r0, r6)     // Catch: android.os.RemoteException -> L89
            java.lang.String r6 = "error"
            r7 = 100001(0x186a1, float:1.40131E-40)
            r9.onResponse(r7, r6)     // Catch: android.os.RemoteException -> L89
            goto L93
        L5c:
            jtt r7 = defpackage.jtt.c()     // Catch: android.os.RemoteException -> L89
            r7.d(r6, r8, r9)     // Catch: android.os.RemoteException -> L89
            goto L93
        L64:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: android.os.RemoteException -> L89
            java.lang.String r3 = "the inputType type is: "
            r6[r1] = r3     // Catch: android.os.RemoteException -> L89
            r6[r2] = r7     // Catch: android.os.RemoteException -> L89
            com.huawei.hwlogsmodel.LogUtil.a(r0, r6)     // Catch: android.os.RemoteException -> L89
            jtr r6 = defpackage.jtr.b()     // Catch: android.os.RemoteException -> L89
            r6.a(r8, r9)     // Catch: android.os.RemoteException -> L89
            java.lang.String r6 = "success"
            r7 = 100000(0x186a0, float:1.4013E-40)
            r9.onResponse(r7, r6)     // Catch: android.os.RemoteException -> L89
            goto L93
        L81:
            java.lang.String r6 = "inputType or message is empty"
            r7 = 255(0xff, float:3.57E-43)
            r9.onResponse(r7, r6)     // Catch: android.os.RemoteException -> L89
            return
        L89:
            java.lang.String r6 = "pushMsgToWearable RemoteException"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r6)
        L93:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.pushMsgToWearable(com.huawei.health.HiAppInfo, java.lang.String, java.lang.String, com.huawei.health.IBaseCommonCallback):void");
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void getSwitch(String str, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.b("KitWearBinder", "kit callback error, about DaemonService callback is null. please check aidl callback");
            return;
        }
        if (!o(iBaseCommonCallback)) {
            LogUtil.h("KitWearBinder", "no device connected getSwitch.");
            return;
        }
        try {
            if (str.hashCode() == -761173970 && str.equals("scientificSleep")) {
                c(iBaseCommonCallback);
            }
            iBaseCommonCallback.onResponse(2, "-1");
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "getSwitch RemoteException");
        }
    }

    private void c(final IBaseCommonCallback iBaseCommonCallback) {
        jqi.a().getSwitchSetting("core_sleep_button", new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                try {
                    if ("0".equals(obj)) {
                        iBaseCommonCallback.onResponse(0, "0");
                    } else if ("1".equals(obj)) {
                        iBaseCommonCallback.onResponse(0, "1");
                    } else {
                        iBaseCommonCallback.onResponse(0, "-1");
                    }
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "getCoreSleepSwitch remote exception");
                }
            }
        });
    }

    private void m(final IBaseCommonCallback iBaseCommonCallback) {
        jtr.b().e(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.11
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                try {
                    LogUtil.a("KitWearBinder", "queryWearStatus error code is ", Integer.valueOf(i));
                    if (obj instanceof String) {
                        iBaseCommonCallback.onResponse(i, (String) obj);
                    } else {
                        iBaseCommonCallback.onResponse(i, "");
                    }
                } catch (RemoteException unused) {
                    LogUtil.b("KitWearBinder", "IWearAIDL queryWearStatus exception");
                }
            }
        });
    }

    public static void handleDataReceive(byte[] bArr) {
        try {
            IBaseCommonCallback iBaseCommonCallback = e;
            if (iBaseCommonCallback != null) {
                iBaseCommonCallback.onResponse(800, cvx.d(bArr));
            }
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "handleDataReceive remote exception");
        }
    }

    public static void handleConnectStateReceive(String str) {
        try {
            IBaseCommonCallback iBaseCommonCallback = e;
            if (iBaseCommonCallback != null) {
                iBaseCommonCallback.onResponse(801, str);
            }
        } catch (RemoteException unused) {
            LogUtil.b("KitWearBinder", "handleDataReceive remote exception");
        }
    }

    private String a() {
        ArrayList arrayList = new ArrayList(10);
        Iterator<DeviceInfo> it = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "KitWearBinder").iterator();
        while (it.hasNext()) {
            arrayList.add(b(it.next()));
        }
        return new Gson().toJson(arrayList);
    }

    private HeartDeviceInfo b(DeviceInfo deviceInfo) {
        HeartDeviceInfo heartDeviceInfo = new HeartDeviceInfo();
        if (deviceInfo == null) {
            return heartDeviceInfo;
        }
        HeartDeviceInfo a2 = a(deviceInfo);
        if (TextUtils.isEmpty(deviceInfo.getUdidFromDevice())) {
            a2.setPhdDeviceUdid(deviceInfo.getDeviceUdid());
        } else {
            a2.setPhdDeviceUdid(deviceInfo.getUdidFromDevice());
        }
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e(deviceInfo.getSecurityUuid() + "#ANDROID21");
        if (!TextUtils.isEmpty(e2)) {
            LogUtil.a("KitWearBinder", "enter getHeartStudyDeviceList, wiseDeviceId not empty");
            a2.setmDeviceId(e2);
        } else {
            String e3 = KeyValDbManager.b(BaseApplication.getContext()).e(deviceInfo.getDeviceUdid());
            if (!TextUtils.isEmpty(e3)) {
                LogUtil.a("KitWearBinder", "enter getHeartStudyDeviceList, getDeviceUdid not empty");
                a2.setmDeviceId(e3);
            } else {
                LogUtil.h("KitWearBinder", "enter getHeartStudyDeviceList, getDeviceUdid is empty");
            }
        }
        a2.setIsSupportGetEcgServiceIv(cwi.c(deviceInfo, 45));
        a2.setDeviceInfo(new Gson().toJson(deviceInfo));
        return a2;
    }

    private HeartDeviceInfo a(DeviceInfo deviceInfo) {
        String a2;
        HeartDeviceInfo heartDeviceInfo = new HeartDeviceInfo();
        heartDeviceInfo.setDeviceName(deviceInfo.getDeviceName());
        heartDeviceInfo.setProductType(deviceInfo.getProductType());
        DeviceCapability c = c(deviceInfo);
        heartDeviceInfo.setDeviceCapability(c != null ? new Gson().toJson(c, DeviceCapability.class) : "");
        heartDeviceInfo.setDeviceConnectState(deviceInfo.getDeviceConnectState());
        heartDeviceInfo.setSoftVersion(deviceInfo.getSoftVersion());
        heartDeviceInfo.setDeviceModel(deviceInfo.getDeviceModel());
        heartDeviceInfo.setCertModel(deviceInfo.getCertModel());
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        if (!deviceIdentify.equals(securityDeviceId)) {
            a2 = knl.a(deviceIdentify + securityDeviceId);
        } else {
            a2 = knl.a(deviceIdentify);
        }
        heartDeviceInfo.setUuid(a2);
        return heartDeviceInfo;
    }

    private DeviceCapability c(DeviceInfo deviceInfo) {
        if (deviceInfo != null && deviceInfo.getDeviceIdentify() != null) {
            Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "KitWearBinder");
            if (!queryDeviceCapability.isEmpty()) {
                return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
            }
        }
        return null;
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void isSwitchOn(int i, IDetectCommonCallback iDetectCommonCallback) {
        juw.d().b(i, iDetectCommonCallback);
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void getList(int i, IDetectCommonCallback iDetectCommonCallback) {
        juw.d().e(i, iDetectCommonCallback);
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void isLatestVersion(int i, IDetectCommonCallback iDetectCommonCallback) {
        juw.d().c(i, iDetectCommonCallback);
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void goIntoPage(String str, int i, IDetectCommonCallback iDetectCommonCallback) {
        juw.d().b(str, i, iDetectCommonCallback);
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void bindDevice(String str, String str2, String str3, int i, IBaseCommonCallback iBaseCommonCallback) {
        MeasurableDevice d;
        LogUtil.a("KitWearBinder", "enter bindDevice");
        if ("9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(str)) {
            d = new cfa();
        } else {
            d = d(str2, str3, i);
        }
        if (d == null) {
            try {
                iBaseCommonCallback.onResponse(2, "param invalid");
            } catch (RemoteException unused) {
                LogUtil.h("KitWearBinder", "bindDevice RemoteException");
            }
        } else {
            try {
                if (((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).bindDevice(str, d, new IDeviceEventHandler() { // from class: com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder.19
                    @Override // com.huawei.health.device.callback.IDeviceEventHandler
                    public void onDeviceFound(HealthDevice healthDevice) {
                        LogUtil.a("KitWearBinder", "onDeviceFound");
                    }

                    @Override // com.huawei.health.device.callback.IDeviceEventHandler
                    public void onScanFailed(int i2) {
                        LogUtil.a("KitWearBinder", "onScanFailed code: ", Integer.valueOf(i2));
                    }

                    @Override // com.huawei.health.device.callback.IDeviceEventHandler
                    public void onStateChanged(int i2) {
                        LogUtil.a("KitWearBinder", "onStateChanged code: ", Integer.valueOf(i2));
                    }
                })) {
                    iBaseCommonCallback.onResponse(0, "bindDevice Success");
                } else {
                    iBaseCommonCallback.onResponse(4, "bindDevice Fail");
                }
            } catch (RemoteException unused2) {
                LogUtil.b("KitWearBinder", "bindDevice RemoteException");
            }
        }
    }

    private MeasurableDevice d(String str, String str2, int i) {
        MeasurableDevice Ej_;
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
        if (i == 1) {
            Ej_ = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).fromBluetoothDevice(remoteDevice);
        } else {
            Ej_ = i == 2 ? ceu.Ej_(remoteDevice) : null;
        }
        if (Ej_ != null) {
            Ej_.setDeviceName(str2);
        }
        return Ej_;
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void captureLog(String str, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "callback is null");
        } else if (TextUtils.isEmpty(str)) {
            kbi.d(iBaseCommonCallback, 1, "deviceUuid is empty");
        } else {
            kbl.e().e(str, iBaseCommonCallback);
        }
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void uploadLog(int i, String str, List<String> list, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "callback is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            kbi.d(iBaseCommonCallback, 1, "deviceUuid is empty");
            return;
        }
        if (list == null || list.size() == 0) {
            kbi.d(iBaseCommonCallback, 1, "list is empty");
            return;
        }
        LogUtil.a("KitWearBinder", "uploadLog uploadFileList.get(0): ", list.get(0), ";uploadType: ", Integer.valueOf(i));
        boolean c = jsd.c(com.huawei.haf.application.BaseApplication.e());
        if (i == 3 && !c) {
            String str2 = SharedPreferenceManager.e("detect_wifi_mmkv_model_id", "detect_wifi_upload_log", "") + list.get(0) + "#";
            SharedPreferenceManager.c("detect_wifi_mmkv_model_id", "detect_wifi_upload_log", str2);
            LogUtil.a("KitWearBinder", "uploadLog uploadFileList: ", str2);
            ReleaseLogUtil.d("KITWEARRELEASE_KitWearBinder", "uploadLog upload type is wifi uploadType: ", Integer.valueOf(i));
            return;
        }
        kbl.e().a(str, list.get(0), iBaseCommonCallback);
    }

    @Override // com.huawei.health.IKitWearAIDL
    public void removeLog(List<String> list, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitWearBinder", "callback is null");
        } else if (list == null || list.size() == 0) {
            kbi.d(iBaseCommonCallback, 1, "list is empty");
        } else {
            kbl.e().c(list.get(0), iBaseCommonCallback);
        }
    }
}
