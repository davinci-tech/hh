package com.huawei.healthcloud.plugintrack.offlinemap.manager;

import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.CityListBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class OfflineMapSearchThreadManager {
    private OfflineMapSearchCallback d;
    private ExtendHandler b = null;
    private e c = null;

    /* renamed from: a, reason: collision with root package name */
    private int f3534a = 0;

    public interface OfflineMapSearchCallback {
        void onSearchResult(List<OfflineMapCity> list);
    }

    public void c(OfflineMapSearchCallback offlineMapSearchCallback) {
        this.d = offlineMapSearchCallback;
    }

    public void e() {
        this.b = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.manager.OfflineMapSearchThreadManager.4
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                return false;
            }
        }, "SearchHandlerThread");
    }

    public void d(String str, HashMap<Integer, CityListBean> hashMap) {
        LogUtil.a("OfflineMapSearchThreadManager", "updateSearch");
        ExtendHandler extendHandler = this.b;
        if (extendHandler == null) {
            LogUtil.b("OfflineMapSearchThreadManager", "updateSearch() mExtendHandler null");
            return;
        }
        e eVar = this.c;
        if (eVar == null) {
            extendHandler.removeTasks(eVar);
        }
        int i = this.f3534a + 1;
        this.f3534a = i;
        e eVar2 = new e(str, this, hashMap, i);
        this.c = eVar2;
        this.b.postTask(eVar2);
    }

    static class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f3535a;
        private WeakReference<OfflineMapSearchThreadManager> b;
        private String d;
        private HashMap<Integer, CityListBean> e;

        e(String str, OfflineMapSearchThreadManager offlineMapSearchThreadManager, HashMap<Integer, CityListBean> hashMap, int i) {
            if (str != null) {
                this.d = str;
            }
            this.b = new WeakReference<>(offlineMapSearchThreadManager);
            if (hashMap != null) {
                this.e = hashMap;
            }
            this.f3535a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            OfflineMapSearchThreadManager offlineMapSearchThreadManager = this.b.get();
            if (offlineMapSearchThreadManager == null) {
                LogUtil.b("OfflineMapSearchThreadManager", "MyRunnable run() fragment null");
                return;
            }
            ArrayList arrayList = new ArrayList(10);
            int size = this.e.size();
            if (size <= 1) {
                LogUtil.b("OfflineMapSearchThreadManager", "MyRunnable run() size <=1 ");
                return;
            }
            for (int i = 1; i < size; i++) {
                Iterator<OfflineMapCity> it = this.e.get(Integer.valueOf(i)).iterator();
                while (it.hasNext()) {
                    OfflineMapCity next = it.next();
                    String city = next.getCity();
                    String jianpin = next.getJianpin();
                    String lowerCase = this.d.toLowerCase(Locale.getDefault());
                    if (city.contains(this.d) || d(next, jianpin, lowerCase)) {
                        LogUtil.c("OfflineMapSearchThreadManager", "city:", city, "pinyin:", jianpin);
                        arrayList.add(next);
                    }
                }
            }
            LogUtil.a("OfflineMapSearchThreadManager", "MyRunnable run() end mCurrentCount:", Integer.valueOf(this.f3535a), ",mThreadCount:", Integer.valueOf(offlineMapSearchThreadManager.f3534a));
            if (this.f3535a == offlineMapSearchThreadManager.f3534a) {
                if (offlineMapSearchThreadManager.d != null) {
                    offlineMapSearchThreadManager.d.onSearchResult(arrayList);
                    return;
                }
                return;
            }
            LogUtil.a("OfflineMapSearchThreadManager", "MyRunnable run() mCurrentCount != mThreadCount:");
        }

        private boolean d(OfflineMapCity offlineMapCity, String str, String str2) {
            return str.startsWith(str2) || offlineMapCity.getPinyin().startsWith(str2);
        }
    }

    public void a() {
        LogUtil.a("OfflineMapSearchThreadManager", "qiut()");
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.quit(false);
            this.b = null;
        }
    }
}
