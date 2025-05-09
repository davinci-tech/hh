package com.huawei.maps.offlinedata.service.cloud;

import android.text.TextUtils;
import com.huawei.maps.offlinedata.handler.dto.device.DownloadMapInfo;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.health.init.OnCheckUpdatesListListener;
import com.huawei.maps.offlinedata.utils.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public class b {
    private static volatile b e;
    private OnCheckUpdatesListListener d;
    private CountDownLatch g;

    /* renamed from: a, reason: collision with root package name */
    private final List<MapOfflineDataItemEntity> f6465a = new ArrayList();
    private final List<MapOfflineDataItemEntity> b = new ArrayList();
    private List<DownloadMapInfo> c = new ArrayList();
    private boolean f = false;

    private b() {
    }

    public static b a() {
        if (e == null) {
            synchronized (b.class) {
                if (e == null) {
                    e = new b();
                }
            }
        }
        return e;
    }

    public void a(MapOfflineDataItemEntity mapOfflineDataItemEntity) {
        if (mapOfflineDataItemEntity != null) {
            this.f6465a.add(mapOfflineDataItemEntity);
        } else {
            this.f = true;
        }
        g.a("CloudDeviceDataContrast", "watchMapGlobalData load finish.");
        CountDownLatch countDownLatch = this.g;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void a(List<MapOfflineDataItemEntity> list) {
        if (list.isEmpty()) {
            this.f = true;
        }
        g.a("CloudDeviceDataContrast", "watchMapRegionData load finish.");
        this.f6465a.addAll(list);
        CountDownLatch countDownLatch = this.g;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void b(List<MapOfflineDataItemEntity> list) {
        if (list.isEmpty()) {
            this.f = true;
        }
        g.a("CloudDeviceDataContrast", "watchContourMapRegionData load finish.");
        this.b.addAll(list);
        CountDownLatch countDownLatch = this.g;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void a(List<DownloadMapInfo> list, final OnCheckUpdatesListListener onCheckUpdatesListListener) {
        this.c = list;
        this.d = onCheckUpdatesListListener;
        this.f6465a.clear();
        this.b.clear();
        new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.service.cloud.b$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                b.this.a(onCheckUpdatesListListener);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(OnCheckUpdatesListListener onCheckUpdatesListListener) {
        this.f = false;
        Set<String> h = com.huawei.maps.offlinedata.service.config.a.a().h();
        this.g = new CountDownLatch(h.size() + 1);
        c.a().c();
        Iterator<String> it = h.iterator();
        while (it.hasNext()) {
            c.a().a(it.next());
        }
        try {
            this.g.await();
            b();
        } catch (InterruptedException e2) {
            g.d("CloudDeviceDataContrast", "check auto update failed." + e2.getMessage());
            onCheckUpdatesListListener.onOfflineMapsInfoResult(new ArrayList());
        }
    }

    private void b() {
        if (this.f) {
            g.d("CloudDeviceDataContrast", "device or cloud data not ready.");
            this.d.onOfflineMapsInfoResult(new ArrayList());
            return;
        }
        g.a("CloudDeviceDataContrast", "device and cloud data all ready.");
        ArrayList arrayList = new ArrayList();
        for (DownloadMapInfo downloadMapInfo : this.c) {
            int mspType = downloadMapInfo.getMspType();
            if (mspType == 1) {
                Iterator<MapOfflineDataItemEntity> it = this.b.iterator();
                while (true) {
                    if (it.hasNext()) {
                        MapOfflineDataItemEntity next = it.next();
                        if (TextUtils.equals(next.getCityId(), downloadMapInfo.getCityId()) && !TextUtils.equals(next.getVersion(), String.valueOf(downloadMapInfo.getVersion()))) {
                            arrayList.add(next);
                            break;
                        }
                    }
                }
            } else if (mspType == 2 || mspType == 0) {
                Iterator<MapOfflineDataItemEntity> it2 = this.f6465a.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        MapOfflineDataItemEntity next2 = it2.next();
                        if (TextUtils.equals(next2.getCityId(), downloadMapInfo.getCityId()) || mspType == 2) {
                            if (!TextUtils.equals(next2.getVersion(), String.valueOf(downloadMapInfo.getVersion()))) {
                                arrayList.add(next2);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (arrayList.size() != this.c.size()) {
            g.c("CloudDeviceDataContrast", "some item in device are not find in cloud data.");
        }
        g.a("CloudDeviceDataContrast", "the map list needed updated size is " + arrayList.size());
        this.d.onOfflineMapsInfoResult(arrayList);
    }
}
