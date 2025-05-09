package com.huawei.health.ecologydevice.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.callback.DownloadCallback;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.ProductCreateFactory;
import com.huawei.hihealth.util.HiFileUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import defpackage.ceu;
import defpackage.cfa;
import defpackage.cwv;
import defpackage.cxh;
import defpackage.dds;
import defpackage.dij;
import defpackage.djo;
import defpackage.dkc;
import defpackage.dkd;
import defpackage.dko;
import defpackage.dks;
import defpackage.koq;
import defpackage.mst;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.Services;
import health.compact.a.WhiteBoxManager;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class FAUtil {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicInteger f2347a = null;
    private static volatile boolean c = false;
    private static volatile Set<ResultCallback> d = new CopyOnWriteArraySet();

    public interface ResultCallback {
        void onResult(boolean z);
    }

    public static void c(ResultCallback resultCallback) {
        if (resultCallback == null) {
            LogUtil.c("FAUtil", "callBack is null");
            return;
        }
        d.add(resultCallback);
        if (c) {
            LogUtil.c("FAUtil", "Last check not finish");
            return;
        }
        LogUtil.a("FAUtil", "checkCachedDataFromFA: start");
        c = true;
        Context e = BaseApplication.e();
        if (!new File(e.getFilesDir(), "cache_data_from_fa").exists()) {
            LogUtil.a("FAUtil", "checkCachedDataFromFA: file not exist, no device need bind");
            d(false);
            return;
        }
        String d2 = HiFileUtil.d(e, "cache_data_from_fa");
        if (TextUtils.isEmpty(d2)) {
            LogUtil.a("FAUtil", "checkCachedDataFromFA: cached data is empty");
            d(false);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(d2);
            int length = jSONObject.length();
            if (length <= 0) {
                LogUtil.a("FAUtil", "checkCachedDataFromFA: objects.length <= 0");
                d(false);
            } else {
                LogUtil.a("FAUtil", "checkCachedDataFromFA: objects.length = ", Integer.valueOf(length));
                e(jSONObject);
            }
        } catch (JSONException e2) {
            LogUtil.b("FAUtil", "checkCachedDataFromFA: failed because: ", e2.getMessage());
            d(false);
        }
    }

    private static void e(JSONObject jSONObject) {
        f2347a = new AtomicInteger(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            LogUtil.c("FAUtil", "handleCachedData: hashCode = ", next);
            JSONObject optJSONObject = jSONObject.optJSONObject(next);
            if (optJSONObject == null) {
                LogUtil.c("FAUtil", "handleCachedData: object is null");
                d(next);
            } else {
                String str = new String(WhiteBoxManager.d().a(HEXUtils.c(optJSONObject.optString("uniqueId"))), StandardCharsets.UTF_8);
                if (!TextUtils.isEmpty(str)) {
                    LogUtil.c("FAUtil", "handleCachedData: uniqueId = ", CommonUtil.l(str));
                    LogUtil.c("FAUtil", "handleCachedData: object = ", optJSONObject);
                    String optString = optJSONObject.optString("productId");
                    LogUtil.a("FAUtil", "handleCachedData: productId = ", optString);
                    if (((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).getBondedDeviceByUniqueId(str) == null) {
                        LogUtil.a("FAUtil", "handleCachedData: device is null");
                        a(optJSONObject, optString, str, next);
                    } else {
                        d(next);
                    }
                } else {
                    LogUtil.a("FAUtil", "handleCachedData: uniqueId is null");
                    d(next);
                }
            }
        }
    }

    private static void a(final JSONObject jSONObject, final String str, final String str2, final String str3) {
        LogUtil.a("FAUtil", "downloadAndBindDevice: productId = ", str);
        Context e = BaseApplication.e();
        final String optString = jSONObject.optString("deviceType");
        dkd dkdVar = new dkd(e, optString, 2, Collections.singletonList(str), new dkc() { // from class: com.huawei.health.ecologydevice.util.FAUtil.2
            @Override // defpackage.dkb
            public void onSuccess() {
                LogUtil.a("FAUtil", "download Success");
                FAUtil.b(dks.c(optString).getType(), jSONObject, str, str2, str3);
            }

            @Override // defpackage.dkb
            public void onFailure(int i) {
                LogUtil.a("FAUtil", "download Failure: ", Integer.valueOf(i));
                if (FAUtil.f2347a.decrementAndGet() == 0) {
                    FAUtil.d(true);
                }
            }
        });
        d(dkdVar, jSONObject.optString(AdShowExtras.DOWNLOAD_SOURCE));
        dkdVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final String str, JSONObject jSONObject, final String str2, final String str3, final String str4) {
        LogUtil.a("FAUtil", "bindDevice: productId = ", str2);
        ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).bindDevice(str2, c(str2, str3, jSONObject.optString("deviceBtName"), jSONObject.optInt("bluetoothType")), new IDeviceEventHandler() { // from class: com.huawei.health.ecologydevice.util.FAUtil.1
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
                LogUtil.a("FAUtil", "onDeviceFound");
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
                LogUtil.a("FAUtil", "onScanFailed code: ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
                LogUtil.a("FAUtil", "onStateChanged code: ", Integer.valueOf(i));
                if (i != 7) {
                    if (FAUtil.f2347a.decrementAndGet() == 0) {
                        FAUtil.d(true);
                        return;
                    }
                    return;
                }
                String str5 = str2;
                String str6 = str3;
                dko.b(str5, str6, str6);
                FAUtil.d(str4);
                if (koq.b(mst.a().c())) {
                    FAUtil.b(str, str2, str3);
                } else {
                    FAUtil.c(str, str2, str3);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final String str, final String str2, final String str3) {
        new cwv().c(new DownloadCallback() { // from class: com.huawei.health.ecologydevice.util.FAUtil.4
            @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
            public void onSuccess() {
                FAUtil.c(str, str2, str3);
            }

            @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
            public void onFailure(int i) {
                LogUtil.a("FAUtil", "downloadIndexAll onFailure errorCode ", Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, String str2, String str3) {
        if (dij.h(str2)) {
            LogUtil.a("FAUtil", "registerDataClient deviceType is ", str);
            djo djoVar = new djo();
            if (ProductCreateFactory.getProductKind(HealthDevice.HealthDeviceKind.getHealthDeviceKind(str)) == 2 && dds.c().f()) {
                LogUtil.a("FAUtil", "isRopeDeviceBtConnected");
                djoVar.a(djoVar.e(new djo.c(str2, str3).c(dds.c().a())), null);
            } else if (ProductCreateFactory.getProductKind(HealthDevice.HealthDeviceKind.getHealthDeviceKind(str)) == 1) {
                IndoorEquipManagerApi indoorEquipManagerApi = (IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class);
                if (indoorEquipManagerApi.isDeviceBtConnected()) {
                    LogUtil.a("FAUtil", "isDeviceBtConnected");
                    djoVar.a(djoVar.e(new djo.c(str2, str3).d(indoorEquipManagerApi.getDeviceInformation())), null);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str) {
        LogUtil.a("FAUtil", "removeInfoByUniqueId: ", str);
        if (f2347a.decrementAndGet() == 0) {
            d(true);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String d2 = HiFileUtil.d(BaseApplication.e(), "cache_data_from_fa");
        if (TextUtils.isEmpty(d2)) {
            LogUtil.a("FAUtil", "removeInfoByUniqueId: cachedData is already null, return");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(d2);
            if (jSONObject.isNull(str)) {
                LogUtil.a("FAUtil", "removeInfoByUniqueId: no cache info for this device, return");
                return;
            }
            jSONObject.remove(str);
            int length = jSONObject.length();
            LogUtil.a("FAUtil", "removeInfoByUniqueId: remaining length = ", Integer.valueOf(length));
            if (length <= 0) {
                a();
            } else {
                HiFileUtil.c(BaseApplication.e(), jSONObject.toString(), "cache_data_from_fa");
            }
        } catch (JSONException e) {
            LogUtil.b("FAUtil", "removeInfoByUniqueId: exception: ", e.getMessage());
        }
    }

    private static MeasurableDevice c(String str, String str2, String str3, int i) {
        if ("9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(str)) {
            return new cfa();
        }
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str2);
        if (i == 1) {
            cxh Ra_ = cxh.Ra_(remoteDevice);
            Ra_.setDeviceName(str3);
            return Ra_;
        }
        if (i != 2) {
            return null;
        }
        ceu Ej_ = ceu.Ej_(remoteDevice);
        Ej_.setDeviceName(str3);
        return Ej_;
    }

    private static void d(dkd dkdVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DeviceDownloadSourceInfo deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
        try {
            JSONObject jSONObject = new JSONObject(str);
            deviceDownloadSourceInfo.setSite(jSONObject.optInt("site"));
            deviceDownloadSourceInfo.setIndexName(jSONObject.optString("indexName"));
            deviceDownloadSourceInfo.setConfigurationPoint(jSONObject.optString("configurationPoint"));
        } catch (JSONException e) {
            LogUtil.b("FAUtil", "setDownloadSource： exception = ", e.getMessage());
        }
        dkdVar.a(deviceDownloadSourceInfo);
    }

    private static void a() {
        LogUtil.a("FAUtil", "deleteFile: deleted = ", Boolean.valueOf(FileUtils.d(new File(BaseApplication.e().getFilesDir(), "cache_data_from_fa"))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(boolean z) {
        c = false;
        if (d.isEmpty()) {
            return;
        }
        Iterator<ResultCallback> it = d.iterator();
        while (it.hasNext()) {
            it.next().onResult(z);
        }
        d.clear();
    }
}
