package com.huawei.maps.offlinedata.health.p2p;

import android.content.Context;
import com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap;
import com.huawei.maps.offlinedata.utils.a;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class OfflineMapWearEngineManager {
    public static final String OFFLINE_MAP_MODULE = "mapkit";
    public static final String WEAR_FINGERPRINT = "SystemApp";
    public static final String WEAR_PACKAGE_NAME = "in.huawei.mapkit";
    private static volatile OfflineMapWearEngineManager mInstance;
    private volatile long lastCheckTime = 0;
    private IWearEngine4OfflineMap wearEngine4OfflineMap = null;

    private OfflineMapWearEngineManager() {
    }

    public static OfflineMapWearEngineManager getInstance() {
        if (mInstance == null) {
            synchronized (OfflineMapWearEngineManager.class) {
                if (mInstance == null) {
                    mInstance = new OfflineMapWearEngineManager();
                }
            }
        }
        return mInstance;
    }

    public IWearEngine4OfflineMap getWearEngine4OfflineMap() {
        return this.wearEngine4OfflineMap;
    }

    public void setWearEngine4OfflineMap(Context context, String str, IWearEngine4OfflineMap iWearEngine4OfflineMap) {
        this.wearEngine4OfflineMap = iWearEngine4OfflineMap;
        a.a(context);
        if (System.currentTimeMillis() - this.lastCheckTime >= TimeUnit.SECONDS.toMillis(1L)) {
            this.lastCheckTime = System.currentTimeMillis();
            com.huawei.maps.offlinedata.service.a.a().a(str);
        }
    }
}
