package com.huawei.health.device.api;

import com.huawei.health.device.callback.DownloadCityCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.hjd;
import java.util.List;

/* loaded from: classes3.dex */
public interface OfflineMapApi {
    void initOfflineMap(String str);

    void jumpToOfflineMap(DeviceInfo deviceInfo, List<String> list);

    void queryingDownloadCity(List<hjd> list, DownloadCityCallback downloadCityCallback);
}
