package com.huawei.maps.offlinedata.service.init;

import android.content.Context;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.maps.offlinedata.DeviceTypeConsts;
import com.huawei.maps.offlinedata.handler.AuthClient4H5;
import com.huawei.maps.offlinedata.handler.ConfigHolder;
import com.huawei.maps.offlinedata.handler.DecompressHandler;
import com.huawei.maps.offlinedata.handler.DeviceClient;
import com.huawei.maps.offlinedata.handler.DownloadHandler;
import com.huawei.maps.offlinedata.handler.H5StateHandler;
import com.huawei.maps.offlinedata.handler.PagesDirection;
import com.huawei.maps.offlinedata.handler.Repository;
import com.huawei.maps.offlinedata.handler.StorageHandler;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap;
import com.huawei.maps.offlinedata.service.download.b;
import com.huawei.maps.offlinedata.utils.d;
import com.huawei.maps.offlinedata.utils.g;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6504a;

    private a() {
    }

    public static a a() {
        if (f6504a == null) {
            synchronized (a.class) {
                if (f6504a == null) {
                    f6504a = new a();
                }
            }
        }
        return f6504a;
    }

    public void a(Context context, String str, boolean z) {
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("configHolder", ConfigHolder.class).addCustomizeJsModule("repository", Repository.class).addCustomizeJsModule("storageHandler", StorageHandler.class).addCustomizeJsModule("downloadHandler", DownloadHandler.class).addCustomizeJsModule("decompressHandler", DecompressHandler.class).addCustomizeJsModule("deviceClient", DeviceClient.class).addCustomizeJsModule("PagesDirection", PagesDirection.class).addCustomizeJsModule("H5StateHandler", H5StateHandler.class).addCustomizeJsModule("AuthClient4H5", AuthClient4H5.class).addPath("#/?authResult=".concat(z ? "1" : "0")).setActivityStartFlag(AppRouterExtras.COLDSTART).setForceDarkMode(1).enableOnDestroyCallback().setImmerse().showStatusBar().build();
        b.a().b().set(false);
        H5ProClient.setBaseUrl(str);
        if (context == null) {
            H5ProClient.startH5MiniProgram(com.huawei.maps.offlinedata.utils.a.a(), "com.huawei.health.h5.offline-map", build);
        } else {
            H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.offline-map", build);
        }
    }

    public boolean b() {
        com.huawei.maps.offlinedata.service.config.a.a().c(DeviceTypeConsts.WATCH);
        IWearEngine4OfflineMap wearEngine4OfflineMap = OfflineMapWearEngineManager.getInstance().getWearEngine4OfflineMap();
        if (wearEngine4OfflineMap == null) {
            g.d("InitService", "wearEngine is null.");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(com.huawei.maps.offlinedata.consts.a.IS_SUPPORT_OFFLINE_MAP.a()));
        arrayList.add(Integer.valueOf(com.huawei.maps.offlinedata.consts.a.IS_SUPPORT_CONTOUR_MAP.a()));
        Map<Integer, Boolean> queryDeviceCapability = wearEngine4OfflineMap.queryDeviceCapability(arrayList);
        g.a("InitService", "offlineMapCapability resultMap: " + d.a(queryDeviceCapability));
        com.huawei.maps.offlinedata.service.config.a.a().d();
        if (Boolean.TRUE.equals(queryDeviceCapability.get(Integer.valueOf(com.huawei.maps.offlinedata.consts.a.IS_SUPPORT_OFFLINE_MAP.a())))) {
            com.huawei.maps.offlinedata.service.config.a.a().b("watch_map");
        }
        if (Boolean.TRUE.equals(queryDeviceCapability.get(Integer.valueOf(com.huawei.maps.offlinedata.consts.a.IS_SUPPORT_CONTOUR_MAP.a())))) {
            com.huawei.maps.offlinedata.service.config.a.a().b("watch_map_contour");
        }
        g.a("InitService", "offlineMapCapability categories: " + d.a(com.huawei.maps.offlinedata.service.config.a.a().h()));
        return true;
    }
}
