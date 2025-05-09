package com.huawei.maps.offlinedata.handler;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.maps.offlinedata.handler.dto.device.CancelTransmitRequest;
import com.huawei.maps.offlinedata.handler.dto.device.DeleteMapInfoRequest;
import com.huawei.maps.offlinedata.handler.dto.device.TransmitRequest;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.jsbridge.a;
import com.huawei.maps.offlinedata.service.device.d;
import com.huawei.maps.offlinedata.service.device.f;
import com.huawei.maps.offlinedata.service.device.tlvtools.b;
import com.huawei.maps.offlinedata.utils.g;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class DeviceClient extends JsBaseModule {
    @JavascriptInterface
    public void queryDeviceAvailableStorage() {
        g.a("DeviceClient", "call queryDeviceAvailableStorage start");
        d.a().b();
    }

    @JavascriptInterface
    public void queryDeviceDownloadedMapInfo(long j) {
        g.a("DeviceClient", "call queryDeviceDownloadedMapInfo from h5.");
        d.a().a(j, (Integer) null);
    }

    @JavascriptInterface
    public void queryDevicePing(final long j) {
        g.a("DeviceClient", "call queryDevicePing from h5.");
        d.a().a(new IOfflineMapPingCallback() { // from class: com.huawei.maps.offlinedata.handler.DeviceClient$$ExternalSyntheticLambda0
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback
            public final void onPingResult(int i) {
                DeviceClient.a(j, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(long j, int i) {
        g.a("DeviceClient", "onPingResult code:" + i);
        if (i == 202) {
            a.a().a(true, j);
        } else {
            a.a().a(false, j);
        }
    }

    @JavascriptInterface
    public void deleteDeviceMap(long j, String str) {
        try {
            g.a("DeviceClient", "call deleteDeviceMap start, params:" + str);
            if (TextUtils.isEmpty(str)) {
                g.c("DeviceClient", "deleteMap receive invalid params");
                a.a().a(-1, "invalid params.", j);
                return;
            }
            try {
                ArrayList arrayList = (ArrayList) com.huawei.maps.offlinedata.utils.d.a(str, new TypeToken<ArrayList<DeleteMapInfoRequest>>() { // from class: com.huawei.maps.offlinedata.handler.DeviceClient.1
                }.getType());
                if (arrayList != null && !arrayList.isEmpty()) {
                    g.c("DeviceClient", "deleteMapInfos size is " + arrayList.size() + ", deleteMapInfos: " + new Gson().toJson(arrayList));
                    int size = arrayList.size();
                    int i = size % 40 == 0 ? size / 40 : (size / 40) + 1;
                    int a2 = b.a();
                    if (arrayList.size() <= 40) {
                        d.a().a(arrayList, i, 0, a2, j, arrayList.size());
                        return;
                    }
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < size) {
                        int i4 = i2 + 40;
                        d.a().a(arrayList.subList(i2, Math.min(size, i4)), i, i3, a2, j, arrayList.size());
                        i3++;
                        i2 = i4;
                    }
                    return;
                }
                g.d("DeviceClient", "parse deleteMapInfos is null or empty.");
                a.a().a(-1, "invalid params.", j);
            } catch (Exception e) {
                e = e;
                g.d("DeviceClient", "deleteDeviceMap exception:" + e.getMessage());
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @JavascriptInterface
    public void transmitMap(long j, String str) {
        g.a("DeviceClient", "call transmitFile start");
        if (TextUtils.isEmpty(str)) {
            g.c("DeviceClient", "transmitMap receive invalid params");
            a.a().a(-1, "invalid param", j);
            return;
        }
        TransmitRequest transmitRequest = (TransmitRequest) com.huawei.maps.offlinedata.utils.d.a(str, TransmitRequest.class);
        if (transmitRequest == null) {
            g.d("DeviceClient", "transmitMap: params is null");
            a.a().a(-1, "invalid param", j);
            return;
        }
        OfflineDataTaskEntity b = com.huawei.maps.offlinedata.service.persist.b.a().b(String.valueOf(transmitRequest.getRequestId()));
        if (b == null) {
            g.d("DeviceClient", "transmitMap: task is null");
            a.a().a(-1, "invalid task", j);
        } else {
            f.a().a(b);
            f.a().a(transmitRequest, j);
        }
    }

    @JavascriptInterface
    public void cancelTransmitMap(long j, String str) {
        g.a("DeviceClient", "call cancelTransmitMap start");
        if (TextUtils.isEmpty(str)) {
            g.c("DeviceClient", "cancelTransmitMap receive invalid params");
            a.a().a(-1, "invalid param", j);
            return;
        }
        CancelTransmitRequest cancelTransmitRequest = (CancelTransmitRequest) com.huawei.maps.offlinedata.utils.d.a(str, CancelTransmitRequest.class);
        if (cancelTransmitRequest == null) {
            g.d("DeviceClient", "cancelTransmitRequest: params is null");
            a.a().a(-1, "invalid param", j);
        } else {
            d.a().a(cancelTransmitRequest.getRequestId().longValue(), j, cancelTransmitRequest.getContinueTransmit().booleanValue());
        }
    }
}
