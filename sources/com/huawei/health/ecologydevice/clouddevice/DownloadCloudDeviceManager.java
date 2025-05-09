package com.huawei.health.ecologydevice.clouddevice;

import android.text.TextUtils;
import com.huawei.health.ecologydevice.callback.DownloadCallback;
import com.huawei.health.ecologydevice.clouddevice.DownloadCloudDeviceManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.profile.DeviceProfile;
import defpackage.cun;
import defpackage.cwu;
import defpackage.cwv;
import defpackage.cww;
import defpackage.koq;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class DownloadCloudDeviceManager {
    private final cwv b = new cwv();
    private List<CloudSwatchDeviceInfo> c;
    private DeviceDataCallback e;

    public interface DeviceDataCallback {
        void deviceResourceCallbackResult(boolean z);
    }

    public void c(DeviceDataCallback deviceDataCallback) {
        LogUtil.a("DownloadCloudDeviceManager", "syncDeviceInfoFromProfileCloud");
        if (deviceDataCallback == null) {
            LogUtil.h("DownloadCloudDeviceManager", "syncDeviceInfoFromProfileCloud: deviceId or deviceStateCallback is null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() || Utils.o()) {
            b(false);
            return;
        }
        this.e = deviceDataCallback;
        this.c = new ArrayList(16);
        c();
    }

    private void c() {
        LogUtil.a("DownloadCloudDeviceManager", "getCloudDeviceList");
        cun.c().getDevicesFromCloud("DownloadCloudDeviceManager", new IBaseResponseCallback() { // from class: cwr
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                DownloadCloudDeviceManager.this.b(i, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Object obj) {
        LogUtil.a("DownloadCloudDeviceManager", "getDevicesFromCloud: errorCode = ", Integer.valueOf(i));
        if (i == 0) {
            if (koq.e(obj, DeviceProfile.class) && (obj instanceof List)) {
                final List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.a("DownloadCloudDeviceManager", "getDevicesFromCloud: empty list");
                    b(true);
                    return;
                } else {
                    this.b.d(new DownloadCallback() { // from class: com.huawei.health.ecologydevice.clouddevice.DownloadCloudDeviceManager.1
                        @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
                        public void onSuccess() {
                            LogUtil.a("DownloadCloudDeviceManager", "mapCallback onSuccess");
                            DownloadCloudDeviceManager.this.d(list);
                        }

                        @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
                        public void onFailure(int i2) {
                            LogUtil.h("DownloadCloudDeviceManager", "mapCallback onFailure, errorCode is:", Integer.valueOf(i2));
                            DownloadCloudDeviceManager.this.b(false);
                        }
                    });
                    return;
                }
            }
            LogUtil.h("DownloadCloudDeviceManager", "getDevicesFromCloud: type not match");
            b(false);
            return;
        }
        LogUtil.h("DownloadCloudDeviceManager", "getDevicesFromCloud: get data failed");
        b(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            cww.b(this.c);
        }
        cww.a();
        DeviceDataCallback deviceDataCallback = this.e;
        if (deviceDataCallback != null) {
            deviceDataCallback.deviceResourceCallbackResult(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<DeviceProfile> list) {
        LogUtil.a("DownloadCloudDeviceManager", "configDeviceProfiles: list.size = ", Integer.valueOf(list.size()));
        if (e("ZAA6", list)) {
            this.b.c(a(list));
        }
        if (e("H001", list)) {
            Iterator<DeviceProfile> it = list.iterator();
            while (it.hasNext()) {
                Map<String, Object> profile = it.next().getProfile();
                if (profile != null && "H001".equals(d("prodId", profile))) {
                    b(profile, "H001");
                    c(profile, "HDK_SMART_WATCH", false);
                }
            }
        }
    }

    private DownloadCallback a(final List<DeviceProfile> list) {
        return new DownloadCallback() { // from class: com.huawei.health.ecologydevice.clouddevice.DownloadCloudDeviceManager.3
            @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
            public void onSuccess() {
                LogUtil.a("DownloadCloudDeviceManager", "There is no local resource package.");
                DownloadCloudDeviceManager.this.b.c("SMART_HEADPHONES", "ZAA6", true, new DownloadCallback() { // from class: com.huawei.health.ecologydevice.clouddevice.DownloadCloudDeviceManager.3.1
                    @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
                    public void onSuccess() {
                        LogUtil.a("DownloadCloudDeviceManager", "save cloud device to db.");
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            Map<String, Object> profile = ((DeviceProfile) it.next()).getProfile();
                            if (profile != null && "ZAA6".equals(DownloadCloudDeviceManager.this.d("prodId", profile))) {
                                new cwu().e(DownloadCloudDeviceManager.this.d("mac", profile), DownloadCloudDeviceManager.this.d("deviceName", profile), DownloadCloudDeviceManager.this.d("sn", profile), "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c", DownloadCloudDeviceManager.this.d("wiseDeviceId", profile), "0");
                            }
                        }
                        DownloadCloudDeviceManager.this.b(true);
                    }

                    @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
                    public void onFailure(int i) {
                        LogUtil.h("DownloadCloudDeviceManager", "device resource is err, errorCode is ", Integer.valueOf(i));
                        DownloadCloudDeviceManager.this.b(false);
                    }
                });
            }

            @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
            public void onFailure(int i) {
                LogUtil.h("DownloadCloudDeviceManager", "index_all is err, errorCode is ", Integer.valueOf(i));
                DownloadCloudDeviceManager.this.b(false);
            }
        };
    }

    private boolean e(String str, List<DeviceProfile> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<DeviceProfile> it = list.iterator();
        while (it.hasNext()) {
            Map<String, Object> profile = it.next().getProfile();
            if (profile != null && str.equals(d("prodId", profile))) {
                return true;
            }
        }
        return false;
    }

    private void c(Map<String, Object> map, String str, boolean z) {
        String d = d("prodId", map);
        d("mac", map);
        d("deviceName", map);
        this.b.c(str, d, z, new DownloadCallback() { // from class: com.huawei.health.ecologydevice.clouddevice.DownloadCloudDeviceManager.2
            @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
            public void onSuccess() {
                LogUtil.a("DownloadCloudDeviceManager", "downloadSingleDeviceResource onSuccess");
                DownloadCloudDeviceManager.this.b(true);
            }

            @Override // com.huawei.health.ecologydevice.callback.DownloadCallback
            public void onFailure(int i) {
                LogUtil.h("DownloadCloudDeviceManager", "downloadSingleDeviceResource onFailure : errorCode is ", Integer.valueOf(i));
                DownloadCloudDeviceManager.this.b(false);
            }
        });
    }

    private void b(Map<String, Object> map, String str) {
        ProductMapInfo d = ProductMap.d(str);
        if (d == null) {
            LogUtil.h("DownloadCloudDeviceManager", "productInfo is null");
            return;
        }
        String h = d.h();
        if (TextUtils.isEmpty(h)) {
            LogUtil.h("DownloadCloudDeviceManager", "productId is error");
            return;
        }
        CloudSwatchDeviceInfo cloudSwatchDeviceInfo = new CloudSwatchDeviceInfo();
        cloudSwatchDeviceInfo.setHiLinkDeviceId(str);
        String d2 = d("sn", map);
        String d3 = d(ProfileRequestConstants.FWV, map);
        String d4 = d("model", map);
        String d5 = d("deviceName", map);
        cloudSwatchDeviceInfo.setDeviceSn(d2);
        cloudSwatchDeviceInfo.setSoftVersion(d3);
        cloudSwatchDeviceInfo.setDeviceModel(d4);
        cloudSwatchDeviceInfo.setDeviceName(d5);
        cloudSwatchDeviceInfo.setProductId(h);
        List<CloudSwatchDeviceInfo> list = this.c;
        if (list != null) {
            list.add(cloudSwatchDeviceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str, Map<String, Object> map) {
        Object obj = map.get(str);
        return obj instanceof String ? (String) obj : "";
    }

    public void b() {
        this.e = null;
        List<CloudSwatchDeviceInfo> list = this.c;
        if (list != null) {
            list.clear();
            this.c = null;
        }
    }
}
