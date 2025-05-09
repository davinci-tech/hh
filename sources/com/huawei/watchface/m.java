package com.huawei.watchface;

import android.content.Context;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.ea;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.utils.HwLog;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11057a = "m";
    private static final Object b = new Object();
    private static m c;

    interface a {

        /* renamed from: a, reason: collision with root package name */
        public static final byte[] f11058a = {1};
    }

    public static m a() {
        m mVar;
        synchronized (b) {
            if (c == null) {
                c = new m();
            }
            mVar = c;
        }
        return mVar;
    }

    public byte[] a(byte[] bArr) {
        try {
            ea.c cVar = new ea.c(FlavorConfig.SERVICE_WATCH_FACE);
            ea.a(bArr, cVar);
            return cVar.a().get(FlavorConfig.SERVICE_WATCH_FACE);
        } catch (Exception e) {
            FlavorConfig.safeHwLog(f11057a, e.getMessage());
            return null;
        }
    }

    public boolean a(String str) {
        return cx.a().a(str, "is_sync") != null;
    }

    private void a(Map<String, byte[]> map) {
        HwLog.e(f11057a, "unzipSyncModel, 39.3 need sync flag, .sync: true");
        map.put("is_sync", a.f11058a);
    }

    public Map<String, byte[]> a(byte[] bArr, String str, String str2) {
        HashMap hashMap = new HashMap();
        try {
        } catch (IOException unused) {
            HwLog.e(f11057a, "unzip catch IOException");
        } catch (Exception unused2) {
            HwLog.e(f11057a, "unzip catch Exception");
        }
        if (!HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).isSportGTModel()) {
            HwLog.i(f11057a, "unzipSyncModel, AP mode");
            ea.c cVar = new ea.c("watchface.bin");
            ea.a(bArr, cVar);
            if (cVar.a().size() > 0) {
                a(hashMap);
            }
            hashMap.put(FlavorConfig.SERVICE_WATCH_FACE, bArr);
            return hashMap;
        }
        String str3 = f11057a;
        HwLog.e(str3, "unzipSyncModel, GT mode");
        ea.c cVar2 = new ea.c("watchface.bin");
        ea.a aVar = new ea.a("watchface.bin");
        boolean a2 = ea.a(bArr, cVar2, aVar);
        if (a2 && cVar2.a().size() > 0) {
            byte[] bArr2 = cVar2.a().get("watchface.bin");
            byte[] a3 = aVar.a();
            boolean vipFreeFromCache = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getVipFreeFromCache(str);
            HwLog.i(str3, "unzipSyncModel, vip free: " + vipFreeFromCache);
            if (vipFreeFromCache) {
                str = WatchResourcesInfo.markUpHiTopId(str);
            }
            byte[] a4 = cy.a(str, str2, new cw("watchface.bin", bArr2), new cw(FlavorConfig.SERVICE_WATCH_FACE, a3));
            HwLog.e(str3, "unzipSyncModel, merge file: " + a4.length);
            hashMap.put(FlavorConfig.SERVICE_WATCH_FACE, a4);
            a(hashMap);
            return hashMap;
        }
        HwLog.e(str3, "unzipSyncModel, isZip: " + a2 + ", size: " + cVar2.a().size());
        hashMap.put(FlavorConfig.SERVICE_WATCH_FACE, bArr);
        return hashMap;
    }

    public Map<String, byte[]> a(Context context, byte[] bArr, boolean z) {
        boolean isSupportDialUnite = HwWatchFaceBtManager.getInstance(context).isSupportDialUnite();
        String str = f11057a;
        HwLog.e(str, String.format("unzipWatchFace, isSupportDialUnite: %s, isZip: %s", Boolean.valueOf(isSupportDialUnite), Boolean.valueOf(z)));
        HashMap hashMap = new HashMap();
        if (!isSupportDialUnite && !z) {
            HwLog.e(str, "unzipWatchFace, isSupportDialUnite && !isZip");
            hashMap.put(FlavorConfig.SERVICE_WATCH_FACE, bArr);
            return hashMap;
        }
        try {
            ea.c cVar = new ea.c("watchface.bin");
            ea.a(bArr, cVar);
            if (cVar.a().size() >= 1) {
                bArr = cVar.a().get("watchface.bin");
            }
            hashMap.put(FlavorConfig.SERVICE_WATCH_FACE, bArr);
            return hashMap;
        } catch (IOException unused) {
            return Collections.emptyMap();
        }
    }
}
