package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.os.Handler;
import com.amap.api.col.p0003sl.av;
import com.amap.api.col.p0003sl.aw;
import com.amap.api.col.p0003sl.ba;
import com.amap.api.col.p0003sl.ds;
import com.amap.api.col.p0003sl.dv;
import com.amap.api.col.p0003sl.ht;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.col.p0003sl.hx;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class OfflineMapManager {

    /* renamed from: a, reason: collision with root package name */
    ba f1447a;
    aw b;
    private Context c;
    private OfflineMapDownloadListener d;
    private OfflineLoadedListener e;
    private Handler f;
    private Handler g;

    public interface OfflineLoadedListener {
        void onVerifyComplete();
    }

    public interface OfflineMapDownloadListener {
        void onCheckUpdate(boolean z, String str);

        void onDownload(int i, int i2, String str);

        void onRemove(boolean z, String str, String str2);
    }

    public final void restart() {
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener) throws Exception {
        hx a2 = hw.a(context, dv.a());
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new Exception(a2.b);
        }
        this.d = offlineMapDownloadListener;
        this.c = context.getApplicationContext();
        this.f = new Handler(this.c.getMainLooper());
        this.g = new Handler(this.c.getMainLooper());
        a(context);
        ht.a().a(this.c);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener, AMap aMap) {
        this.d = offlineMapDownloadListener;
        this.c = context.getApplicationContext();
        this.f = new Handler(this.c.getMainLooper());
        this.g = new Handler(this.c.getMainLooper());
        try {
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(Context context) {
        this.c = context.getApplicationContext();
        aw.b = false;
        aw a2 = aw.a(this.c);
        this.b = a2;
        a2.a(new aw.a() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1
            @Override // com.amap.api.col.3sl.aw.a
            public final void a(final av avVar) {
                if (OfflineMapManager.this.d == null || avVar == null) {
                    return;
                }
                OfflineMapManager.this.f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            OfflineMapManager.this.d.onDownload(avVar.c().b(), avVar.getcompleteCode(), avVar.getCity());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }

            @Override // com.amap.api.col.3sl.aw.a
            public final void b(final av avVar) {
                if (OfflineMapManager.this.d == null || avVar == null) {
                    return;
                }
                OfflineMapManager.this.f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (!avVar.c().equals(avVar.g) && !avVar.c().equals(avVar.f904a)) {
                                OfflineMapManager.this.d.onCheckUpdate(false, avVar.getCity());
                                return;
                            }
                            OfflineMapManager.this.d.onCheckUpdate(true, avVar.getCity());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }

            @Override // com.amap.api.col.3sl.aw.a
            public final void c(final av avVar) {
                if (OfflineMapManager.this.d == null || avVar == null) {
                    return;
                }
                OfflineMapManager.this.f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (avVar.c().equals(avVar.f904a)) {
                                OfflineMapManager.this.d.onRemove(true, avVar.getCity(), "");
                            } else {
                                OfflineMapManager.this.d.onRemove(false, avVar.getCity(), "");
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }

            @Override // com.amap.api.col.3sl.aw.a
            public final void a() {
                if (OfflineMapManager.this.e != null) {
                    OfflineMapManager.this.f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.4
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                OfflineMapManager.this.e.onVerifyComplete();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        try {
            this.b.a();
            this.f1447a = this.b.f;
            ds.b(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void downloadByCityCode(String str) throws AMapException {
        try {
            this.b.f(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void downloadByCityName(String str) throws AMapException {
        try {
            this.b.e(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void downloadByProvinceName(String str) throws AMapException {
        try {
            a();
            OfflineMapProvince itemByProvinceName = getItemByProvinceName(str);
            if (itemByProvinceName == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            Iterator<OfflineMapCity> it = itemByProvinceName.getCityList().iterator();
            while (it.hasNext()) {
                final String city = it.next().getCity();
                this.g.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            OfflineMapManager.this.b.e(city);
                        } catch (AMapException e) {
                            iv.c(e, "OfflineMapManager", "downloadByProvinceName");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            if (th instanceof AMapException) {
                throw th;
            }
            iv.c(th, "OfflineMapManager", "downloadByProvinceName");
        }
    }

    public final void remove(String str) {
        try {
            if (this.b.b(str)) {
                this.b.c(str);
                return;
            }
            OfflineMapProvince c = this.f1447a.c(str);
            if (c != null && c.getCityList() != null) {
                Iterator<OfflineMapCity> it = c.getCityList().iterator();
                while (it.hasNext()) {
                    final String city = it.next().getCity();
                    this.g.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.3
                        @Override // java.lang.Runnable
                        public final void run() {
                            OfflineMapManager.this.b.c(city);
                        }
                    });
                }
                return;
            }
            OfflineMapDownloadListener offlineMapDownloadListener = this.d;
            if (offlineMapDownloadListener != null) {
                offlineMapDownloadListener.onRemove(false, str, "没有该城市");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final ArrayList<OfflineMapProvince> getOfflineMapProvinceList() {
        return this.f1447a.a();
    }

    public final OfflineMapCity getItemByCityCode(String str) {
        return this.f1447a.a(str);
    }

    public final OfflineMapCity getItemByCityName(String str) {
        return this.f1447a.b(str);
    }

    public final OfflineMapProvince getItemByProvinceName(String str) {
        return this.f1447a.c(str);
    }

    public final ArrayList<OfflineMapCity> getOfflineMapCityList() {
        return this.f1447a.b();
    }

    public final ArrayList<OfflineMapCity> getDownloadingCityList() {
        return this.f1447a.e();
    }

    public final ArrayList<OfflineMapProvince> getDownloadingProvinceList() {
        return this.f1447a.f();
    }

    public final ArrayList<OfflineMapCity> getDownloadOfflineMapCityList() {
        return this.f1447a.c();
    }

    public final ArrayList<OfflineMapProvince> getDownloadOfflineMapProvinceList() {
        return this.f1447a.d();
    }

    private void a(String str) throws AMapException {
        this.b.a(str);
    }

    public final void updateOfflineCityByCode(String str) throws AMapException {
        OfflineMapCity itemByCityCode = getItemByCityCode(str);
        if (itemByCityCode == null || itemByCityCode.getCity() == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        a(itemByCityCode.getCity());
    }

    public final void updateOfflineCityByName(String str) throws AMapException {
        a(str);
    }

    public final void updateOfflineMapProvinceByName(String str) throws AMapException {
        a(str);
    }

    private void a() throws AMapException {
        if (!dv.d(this.c)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    public final void stop() {
        this.b.d();
    }

    public final void pause() {
        this.b.e();
    }

    public final void pauseByName(String str) {
        this.b.d(str);
    }

    public final void destroy() {
        try {
            aw awVar = this.b;
            if (awVar != null) {
                awVar.f();
            }
            b();
            Handler handler = this.f;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            this.f = null;
            Handler handler2 = this.g;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
            }
            this.g = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b() {
        this.d = null;
    }

    public final void setOnOfflineLoadedListener(OfflineLoadedListener offlineLoadedListener) {
        this.e = offlineLoadedListener;
    }
}
