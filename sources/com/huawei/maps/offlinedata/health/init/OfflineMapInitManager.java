package com.huawei.maps.offlinedata.health.init;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.health.log.ILogHealth;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.logpush.dto.a;
import com.huawei.maps.offlinedata.service.device.d;
import com.huawei.maps.offlinedata.service.device.f;
import com.huawei.maps.offlinedata.service.persist.b;
import com.huawei.maps.offlinedata.utils.a;
import com.huawei.maps.offlinedata.utils.c;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.maps.offlinedata.utils.j;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class OfflineMapInitManager {
    private static final String TAG = "OfflineMapInitManager";
    private static volatile OfflineMapInitManager mInstance;

    private OfflineMapInitManager() {
    }

    public static OfflineMapInitManager getInstance() {
        if (mInstance == null) {
            synchronized (OfflineMapInitManager.class) {
                if (mInstance == null) {
                    mInstance = new OfflineMapInitManager();
                }
            }
        }
        return mInstance;
    }

    private static void clearCacheOnDeviceChanged(String str) {
        String a2 = j.a("unique_device", "", a.a());
        j.b("unique_device", str, a.a());
        if (TextUtils.isEmpty(a2)) {
            g.c(TAG, "clearCacheOnDeviceChanged lastUniqueDevice is empty.");
        } else {
            if (a2.equals(str)) {
                return;
            }
            g.c(TAG, "clearCacheOnDeviceChanged device changed.");
            b.a().b();
            c.a(new File(com.huawei.maps.offlinedata.service.storage.a.a().c()));
        }
    }

    public static void setLogHealth(ILogHealth iLogHealth) {
        g.a(iLogHealth);
    }

    public boolean createOfflineDataMap(final OfflineMapInitOptions offlineMapInitOptions, final OnCreateResultListener onCreateResultListener) {
        String[] recommendCityIds = offlineMapInitOptions.getRecommendCityIds();
        a.a(offlineMapInitOptions.getContext());
        com.huawei.maps.offlinedata.logpush.dto.a c = a.C0167a.a().a((recommendCityIds == null || recommendCityIds.length <= 0) ? "createOfflineDataMap" : "createOfflineDataMapFromRoutes").c();
        if (TextUtils.isEmpty(offlineMapInitOptions.getH5proUrl()) || TextUtils.isEmpty(offlineMapInitOptions.getUniqueDevice())) {
            g.d(TAG, "createOfflineDataMap uniqueDevice or h5proUrl is empty.");
            c.b("060013");
            com.huawei.maps.offlinedata.logpush.b.a(c);
            return false;
        }
        g.a(TAG, "h5proUrl():" + offlineMapInitOptions.getH5proUrl());
        clearCacheOnDeviceChanged(offlineMapInitOptions.getUniqueDevice());
        com.huawei.maps.offlinedata.service.config.a.a().a(offlineMapInitOptions.getDeviceName());
        com.huawei.maps.offlinedata.service.config.a.a().a(offlineMapInitOptions.getRecommendCityIds());
        if (!com.huawei.maps.offlinedata.service.init.a.a().b()) {
            c.b("060014");
            com.huawei.maps.offlinedata.logpush.b.a(c);
            return false;
        }
        final Handler handler = new Handler(offlineMapInitOptions.getContext().getMainLooper());
        new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.health.init.OfflineMapInitManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                OfflineMapInitManager.lambda$createOfflineDataMap$2(handler, offlineMapInitOptions, onCreateResultListener);
            }
        }).start();
        c.b("0");
        com.huawei.maps.offlinedata.logpush.b.a(c);
        return true;
    }

    static /* synthetic */ void lambda$createOfflineDataMap$2(Handler handler, final OfflineMapInitOptions offlineMapInitOptions, OnCreateResultListener onCreateResultListener) {
        final boolean z;
        try {
            z = com.huawei.maps.offlinedata.service.cloud.a.a().h();
            g.a(TAG, "auth request result is:" + z);
            if (com.huawei.maps.offlinedata.service.download.b.a().c().isEmpty() && f.a().c().get() == null) {
                b.a().d(new com.huawei.maps.offlinedata.utils.b() { // from class: com.huawei.maps.offlinedata.health.init.OfflineMapInitManager$$ExternalSyntheticLambda0
                    @Override // com.huawei.maps.offlinedata.utils.b
                    public final void accept(Object obj) {
                        OfflineMapInitManager.lambda$createOfflineDataMap$0((List) obj);
                    }
                });
            }
            handler.post(new Runnable() { // from class: com.huawei.maps.offlinedata.health.init.OfflineMapInitManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    com.huawei.maps.offlinedata.service.init.a.a().a(r0.getActivity(), OfflineMapInitOptions.this.getH5proUrl(), z);
                }
            });
            g.a(TAG, "save last watch map version.");
            String e = com.huawei.maps.offlinedata.service.cloud.a.a().e();
            String f = com.huawei.maps.offlinedata.service.cloud.a.a().f();
            j.b("lastOfflineMapDataVersion" + offlineMapInitOptions.getUniqueDevice(), e, offlineMapInitOptions.getContext());
            j.b("lastOfflineContourMapDataVersion" + offlineMapInitOptions.getUniqueDevice(), f, offlineMapInitOptions.getContext());
        } catch (Exception e2) {
            g.d(TAG, "createOfflineDataMap exception:" + e2.getMessage());
            z = false;
        }
        onCreateResultListener.onCreateResult(z);
    }

    static /* synthetic */ void lambda$createOfflineDataMap$0(List list) {
        g.a(TAG, "no running task.will pause all task. size is " + list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OfflineDataTaskEntity offlineDataTaskEntity = (OfflineDataTaskEntity) it.next();
            if (offlineDataTaskEntity.getTaskState().contains("transmit")) {
                offlineDataTaskEntity.setTransmitProgress(0);
                offlineDataTaskEntity.setTaskState("{\"transmit\":\"pause\"}");
            } else {
                offlineDataTaskEntity.setTaskState("{\"download\":\"pause\"}");
            }
            b.a().b(offlineDataTaskEntity.getId(), offlineDataTaskEntity.getItemState(), offlineDataTaskEntity.getTaskState());
            b.a().a(offlineDataTaskEntity.getId(), offlineDataTaskEntity.getDownloadProgress().intValue(), offlineDataTaskEntity.getTransmitProgress().intValue());
        }
    }

    public void checkForUpdates(final Context context, final String str, final OnCheckUpdatesListener onCheckUpdatesListener) {
        g.a(TAG, "call queryDevicePing from checkForUpdates.");
        d.a().a(new IOfflineMapPingCallback() { // from class: com.huawei.maps.offlinedata.health.init.OfflineMapInitManager$$ExternalSyntheticLambda2
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback
            public final void onPingResult(int i) {
                OfflineMapInitManager.lambda$checkForUpdates$4(context, onCheckUpdatesListener, str, i);
            }
        });
    }

    static /* synthetic */ void lambda$checkForUpdates$4(final Context context, final OnCheckUpdatesListener onCheckUpdatesListener, final String str, int i) {
        g.a(TAG, "checkForUpdates onPingResult code:" + i);
        if (i == 202) {
            new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.health.init.OfflineMapInitManager$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineMapInitManager.lambda$checkForUpdates$3(context, onCheckUpdatesListener, str);
                }
            }).start();
        } else {
            g.a(TAG, "checkForUpdates ping failed.");
            onCheckUpdatesListener.onResult(false);
        }
    }

    static /* synthetic */ void lambda$checkForUpdates$3(Context context, OnCheckUpdatesListener onCheckUpdatesListener, String str) {
        try {
            com.huawei.maps.offlinedata.utils.a.a(context);
            g.a(TAG, "Check offline map updates for red...");
            if (com.huawei.maps.offlinedata.service.a.a().b()) {
                g.a(TAG, "Auto update are enable. Skip check.");
                onCheckUpdatesListener.onResult(false);
                return;
            }
            g.a(TAG, "Auto update are disable.");
            com.huawei.maps.offlinedata.service.cloud.a.a().h();
            String e = com.huawei.maps.offlinedata.service.cloud.a.a().e();
            String f = com.huawei.maps.offlinedata.service.cloud.a.a().f();
            if (TextUtils.isEmpty(e) && TextUtils.isEmpty(f)) {
                g.d(TAG, "Failed to obtain the data version. Can't check for updates.");
                onCheckUpdatesListener.onResult(false);
                return;
            }
            String a2 = j.a("lastOfflineMapDataVersion" + str, (String) null, context);
            String a3 = j.a("lastOfflineContourMapDataVersion" + str, (String) null, context);
            if (TextUtils.equals(e, a2) && TextUtils.equals(f, a3)) {
                g.a(TAG, "The version of the offline map data on the cloud isn't updated. Skip check.");
                onCheckUpdatesListener.onResult(false);
            } else {
                com.huawei.maps.offlinedata.service.device.b.a().a(onCheckUpdatesListener);
            }
        } catch (Exception e2) {
            g.d(TAG, "check4Updates exception:" + e2.getMessage());
            onCheckUpdatesListener.onResult(false);
        }
    }

    public void getRegionByLatLngs(final Context context, final List<OfflineMapPoint> list, final OnCheckRegionListListener onCheckRegionListListener) {
        g.a(TAG, "call queryDevicePing from getRegionByLatLngs.");
        d.a().a(new IOfflineMapPingCallback() { // from class: com.huawei.maps.offlinedata.health.init.OfflineMapInitManager$$ExternalSyntheticLambda4
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback
            public final void onPingResult(int i) {
                OfflineMapInitManager.lambda$getRegionByLatLngs$6(context, list, onCheckRegionListListener, i);
            }
        });
    }

    static /* synthetic */ void lambda$getRegionByLatLngs$6(final Context context, final List list, final OnCheckRegionListListener onCheckRegionListListener, int i) {
        g.a(TAG, "getRegionByLatLngs onPingResult code:" + i);
        if (i == 202) {
            new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.health.init.OfflineMapInitManager$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineMapInitManager.lambda$getRegionByLatLngs$5(context, list, onCheckRegionListListener);
                }
            }).start();
        } else {
            g.a(TAG, "getRegionByLatLngs ping failed.");
            onCheckRegionListListener.onResult(new ArrayList());
        }
    }

    static /* synthetic */ void lambda$getRegionByLatLngs$5(Context context, List list, OnCheckRegionListListener onCheckRegionListListener) {
        try {
            g.a(TAG, "Check offline map getRegionByLatLngs...");
            com.huawei.maps.offlinedata.utils.a.a(context);
            com.huawei.maps.offlinedata.service.cloud.a.a().h();
            com.huawei.maps.offlinedata.service.cloud.c.a().a((List<OfflineMapPoint>) list, onCheckRegionListListener);
        } catch (Exception e) {
            g.d(TAG, "getRegionByLatLngs exception:" + e.getMessage());
        }
    }
}
