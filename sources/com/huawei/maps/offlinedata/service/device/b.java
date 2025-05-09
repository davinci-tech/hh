package com.huawei.maps.offlinedata.service.device;

import android.text.TextUtils;
import com.huawei.maps.offlinedata.handler.dto.device.DownloadMapInfo;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.health.init.OnCheckRegionListListener;
import com.huawei.maps.offlinedata.health.init.OnCheckUpdatesListListener;
import com.huawei.maps.offlinedata.health.init.OnCheckUpdatesListener;
import com.huawei.maps.offlinedata.health.init.OnObtainMapDataListener;
import com.huawei.maps.offlinedata.utils.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class b implements OnObtainMapDataListener {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f6474a;
    private static OnCheckUpdatesListener b;
    private static OnCheckUpdatesListListener c;
    private static OnCheckRegionListListener d;
    private List<MapOfflineDataItemEntity> e;

    private b() {
    }

    public static b a() {
        if (f6474a == null) {
            synchronized (b.class) {
                if (f6474a == null) {
                    f6474a = new b();
                }
            }
        }
        return f6474a;
    }

    public String b() {
        return com.huawei.maps.offlinedata.service.cloud.a.a().f();
    }

    public String c() {
        return com.huawei.maps.offlinedata.service.cloud.a.a().e();
    }

    public void a(OnCheckUpdatesListener onCheckUpdatesListener) {
        b = onCheckUpdatesListener;
        g.a("DeviceReceiverService", "queryDeviceDownloadedMapInfo for check update red.");
        d.a().a(0L, (Integer) 10001);
    }

    public void a(OnCheckUpdatesListListener onCheckUpdatesListListener) {
        c = onCheckUpdatesListListener;
        g.a("DeviceReceiverService", "queryDeviceDownloadedMapInfo for auto update.");
        d.a().a(0L, (Integer) 10000);
    }

    public void a(List<MapOfflineDataItemEntity> list, OnCheckRegionListListener onCheckRegionListListener) {
        this.e = list;
        d = onCheckRegionListListener;
        g.a("DeviceReceiverService", "queryDeviceDownloadedMapInfo for region.");
        d.a().a(0L, (Integer) 10003);
    }

    @Override // com.huawei.maps.offlinedata.health.init.OnObtainMapDataListener
    public void downLoadInfo(List<DownloadMapInfo> list, int i) {
        g.a("DeviceReceiverService", "Received device response for check updates.");
        if (i == 10003) {
            c(list);
            return;
        }
        if (!list.isEmpty()) {
            if (i == 10001) {
                a(list);
                return;
            } else {
                b(list);
                return;
            }
        }
        g.a("DeviceReceiverService", "The device map list is empty.");
        if (i == 10001) {
            b.onResult(false);
        } else {
            c.onOfflineMapsInfoResult(new ArrayList());
        }
    }

    private void a(List<DownloadMapInfo> list) {
        g.a("DeviceReceiverService", "Contrast map data version for check updates.");
        for (DownloadMapInfo downloadMapInfo : list) {
            String valueOf = String.valueOf(downloadMapInfo.getVersion());
            int mspType = downloadMapInfo.getMspType();
            if (mspType == 1 && !TextUtils.equals(valueOf, b())) {
                g.a("DeviceReceiverService", "The offline contour map has update.");
                b.onResult(true);
                return;
            } else if (mspType == 0 && !TextUtils.equals(valueOf, c())) {
                g.a("DeviceReceiverService", "The offline map has update.");
                b.onResult(true);
                return;
            }
        }
        b.onResult(false);
    }

    private void b(List<DownloadMapInfo> list) {
        g.a("DeviceReceiverService", "Contrast map data version for auto updates.");
        ArrayList arrayList = new ArrayList();
        for (DownloadMapInfo downloadMapInfo : list) {
            String valueOf = String.valueOf(downloadMapInfo.getVersion());
            if (downloadMapInfo.getMspType() == 1 && !TextUtils.equals(valueOf, b())) {
                arrayList.add(downloadMapInfo);
            } else if (!TextUtils.equals(valueOf, c())) {
                arrayList.add(downloadMapInfo);
            }
        }
        g.a("DeviceReceiverService", "the device map data needed updated size is " + arrayList.size());
        if (arrayList.isEmpty()) {
            g.a("DeviceReceiverService", "the device map data no need update.");
            c.onOfflineMapsInfoResult(new ArrayList());
        } else {
            com.huawei.maps.offlinedata.service.cloud.b.a().a(arrayList, c);
        }
    }

    private void c(List<DownloadMapInfo> list) {
        List<MapOfflineDataItemEntity> list2 = this.e;
        if (list2 == null || list2.isEmpty()) {
            g.a("DeviceReceiverService", "getRegionByLatLngs getRegions is null or empty");
            d.onResult(new ArrayList());
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (MapOfflineDataItemEntity mapOfflineDataItemEntity : this.e) {
            if (!list.isEmpty()) {
                g.a("DeviceReceiverService", "The device map list is not empty.");
                Iterator<DownloadMapInfo> it = list.iterator();
                while (it.hasNext()) {
                    if (TextUtils.equals(mapOfflineDataItemEntity.getCityId(), it.next().getCityId())) {
                        break;
                    }
                }
            }
            arrayList.add(mapOfflineDataItemEntity.getCityId());
        }
        d.onResult(arrayList);
    }
}
