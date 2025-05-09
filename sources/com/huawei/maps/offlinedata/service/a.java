package com.huawei.maps.offlinedata.service;

import android.text.TextUtils;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.health.init.OnCheckUpdatesListListener;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.service.device.d;
import com.huawei.maps.offlinedata.service.device.f;
import com.huawei.maps.offlinedata.service.persist.b;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.maps.offlinedata.utils.h;
import com.huawei.maps.offlinedata.utils.j;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6463a;
    private volatile boolean b = false;

    private a() {
    }

    public static a a() {
        if (f6463a == null) {
            synchronized (a.class) {
                if (f6463a == null) {
                    f6463a = new a();
                }
            }
        }
        return f6463a;
    }

    public boolean b() {
        return Boolean.parseBoolean(com.huawei.maps.offlinedata.utils.a.a().getSharedPreferences("com.huawei.health.h5.offline-map", 0).getString("autoUpdateIfWifi", null));
    }

    public boolean c() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void d() {
        g.a("OfflineMapManager", "resumeAllWhenNetworkChange");
        b.a().d(new com.huawei.maps.offlinedata.utils.b() { // from class: com.huawei.maps.offlinedata.service.a$$ExternalSyntheticLambda0
            @Override // com.huawei.maps.offlinedata.utils.b
            public final void accept(Object obj) {
                a.c((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(List list) {
        g.a("OfflineMapManager", "the task list needed resume size is " + list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OfflineDataTaskEntity offlineDataTaskEntity = (OfflineDataTaskEntity) it.next();
            String taskState = offlineDataTaskEntity.getTaskState();
            if (TextUtils.equals(offlineDataTaskEntity.getItemState(), "updating") && taskState.contains("download") && !com.huawei.maps.offlinedata.service.download.b.a().c().contains(offlineDataTaskEntity)) {
                com.huawei.maps.offlinedata.service.download.b.a().b(offlineDataTaskEntity);
            }
        }
    }

    public void e() {
        g.a("OfflineMapManager", "pauseAllWhenNetworkChange");
        com.huawei.maps.offlinedata.service.download.b.a().g();
    }

    public void f() {
        g.a("OfflineMapManager", "resumeWaitingTaskWhenH5change");
        b.a().d(new com.huawei.maps.offlinedata.utils.b() { // from class: com.huawei.maps.offlinedata.service.a$$ExternalSyntheticLambda1
            @Override // com.huawei.maps.offlinedata.utils.b
            public final void accept(Object obj) {
                a.b((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(List list) {
        g.a("OfflineMapManager", "the task list needed resume size is " + list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OfflineDataTaskEntity offlineDataTaskEntity = (OfflineDataTaskEntity) it.next();
            String taskState = offlineDataTaskEntity.getTaskState();
            if (!taskState.contains(VastAttribute.PAUSE) && !taskState.contains(VastAttribute.ERROR)) {
                if (offlineDataTaskEntity.getTaskState().contains("transmit")) {
                    f.a().b(offlineDataTaskEntity);
                } else {
                    com.huawei.maps.offlinedata.service.download.b.a().b(offlineDataTaskEntity);
                }
            }
        }
    }

    public void g() {
        g.a("OfflineMapManager", "pauseWaitingTaskWhenH5change");
        com.huawei.maps.offlinedata.service.download.b.a().e();
        f.a().b();
    }

    public void a(final String str) {
        g.a("OfflineMapManager", "start check auto updates.");
        if (c()) {
            g.a("OfflineMapManager", "the webview is running. skip native updates.");
            return;
        }
        if (!b()) {
            g.a("OfflineMapManager", "the auto updates is disable");
            return;
        }
        int networkType = NetworkUtil.getNetworkType(com.huawei.maps.offlinedata.utils.a.a());
        if (networkType != 1) {
            g.a("OfflineMapManager", "current network type is not wifi: " + networkType);
            return;
        }
        long a2 = j.a("autoUpdateCheckTime" + str, 0L, com.huawei.maps.offlinedata.utils.a.a());
        final long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(a2 - currentTimeMillis) < TimeUnit.HOURS.toMillis(1L)) {
            g.a("OfflineMapManager", "checked for updates recently. skip.");
            return;
        }
        g.a("OfflineMapManager", "call queryDevicePing from startAutoUpdates.");
        d.a().a(new IOfflineMapPingCallback() { // from class: com.huawei.maps.offlinedata.service.a$$ExternalSyntheticLambda3
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback
            public final void onPingResult(int i) {
                a.this.a(str, currentTimeMillis, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, long j, int i) {
        g.a("OfflineMapManager", "startAutoUpdates onPingResult code:" + i);
        if (i == 202) {
            g.a("OfflineMapManager", "the auto updates start.");
            j.b("autoUpdateCheckTime" + str, j, com.huawei.maps.offlinedata.utils.a.a());
            a(new OnCheckUpdatesListListener() { // from class: com.huawei.maps.offlinedata.service.a$$ExternalSyntheticLambda2
                @Override // com.huawei.maps.offlinedata.health.init.OnCheckUpdatesListListener
                public final void onOfflineMapsInfoResult(List list) {
                    a.a(list);
                }
            });
            return;
        }
        g.a("OfflineMapManager", "startAutoUpdates ping failed.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MapOfflineDataItemEntity mapOfflineDataItemEntity = (MapOfflineDataItemEntity) it.next();
            OfflineDataTaskEntity a2 = b.a().a(mapOfflineDataItemEntity.getCityId(), mapOfflineDataItemEntity.getDataType());
            if (a2 == null) {
                a2 = OfflineDataTaskEntity.Builder.create().fromMapInfo(mapOfflineDataItemEntity).build();
            }
            if (a2.getTaskState().contains(VastAttribute.PAUSE) || a2.getTaskState().contains(VastAttribute.ERROR)) {
                h.b("OfflineMapManager", "the task has paused or error. skip update " + a2.getPrintId());
            } else {
                a2.setItemState("updating");
                a2.setVersion(mapOfflineDataItemEntity.getVersion());
                com.huawei.maps.offlinedata.service.download.b.a().b(a2);
            }
        }
    }

    private void a(final OnCheckUpdatesListListener onCheckUpdatesListListener) {
        g.a("OfflineMapManager", "Check offline map updates for auto update...");
        if (com.huawei.maps.offlinedata.service.init.a.a().b()) {
            new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.service.a$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    a.b(OnCheckUpdatesListListener.this);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(OnCheckUpdatesListListener onCheckUpdatesListListener) {
        com.huawei.maps.offlinedata.service.cloud.a.a().h();
        if (!com.huawei.maps.offlinedata.service.cloud.a.a().g()) {
            g.c("OfflineMapManager", "the auto updates is disable by cloud.");
            onCheckUpdatesListListener.onOfflineMapsInfoResult(new ArrayList());
            return;
        }
        String e = com.huawei.maps.offlinedata.service.cloud.a.a().e();
        String f = com.huawei.maps.offlinedata.service.cloud.a.a().f();
        if (TextUtils.isEmpty(e) && TextUtils.isEmpty(f)) {
            g.d("OfflineMapManager", "Failed to obtain the data version. Can't check for auto updates.");
            onCheckUpdatesListListener.onOfflineMapsInfoResult(new ArrayList());
        } else {
            com.huawei.maps.offlinedata.service.device.b.a().a(onCheckUpdatesListListener);
        }
    }
}
