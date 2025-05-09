package com.huawei.health.device.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes3.dex */
public class EventBus {
    private ArrayList<a> b = new ArrayList<>(16);
    private static final HashMap<String, EventBus> c = new HashMap<>(16);
    private static final ReentrantLock d = new ReentrantLock();

    /* renamed from: a, reason: collision with root package name */
    private static ThreadPoolExecutor f2268a = null;

    public interface ICallback {
        void onEvent(b bVar);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private Bundle f2270a;
        private Intent c;
        private String e;

        public b(String str) {
            this(str, new Bundle());
        }

        public b(String str, Bundle bundle) {
            this.e = str;
            this.f2270a = bundle;
        }

        public b(String str, Intent intent) {
            this.e = str;
            this.c = intent;
        }

        public String e() {
            return this.e;
        }

        public Bundle Kl_() {
            return this.f2270a;
        }

        public Intent Km_() {
            return this.c;
        }
    }

    private EventBus() {
    }

    public static void d(b bVar) {
        String str;
        if (bVar == null || (str = bVar.e) == null) {
            return;
        }
        d.lock();
        try {
            EventBus eventBus = c.get(str);
            if (eventBus != null) {
                ReleaseLogUtil.e("PluginDevice_EventBus", "EventBus publish ", bVar.e());
                Iterator<a> it = eventBus.b.iterator();
                while (it.hasNext()) {
                    it.next().a(bVar);
                }
            }
        } finally {
            d.unlock();
        }
    }

    public static void d(ICallback iCallback, int i, String... strArr) {
        if (iCallback == null || strArr == null || strArr.length == 0) {
            return;
        }
        d.lock();
        try {
            ReleaseLogUtil.e("PluginDevice_EventBus", "EventBus subscribe ", Arrays.toString(strArr));
            a aVar = null;
            for (String str : strArr) {
                if (str != null) {
                    HashMap<String, EventBus> hashMap = c;
                    EventBus eventBus = hashMap.get(str);
                    if (eventBus == null) {
                        eventBus = new EventBus();
                        hashMap.put(str, eventBus);
                    }
                    ArrayList<a> arrayList = eventBus.b;
                    Iterator<a> it = arrayList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().e == iCallback) {
                                break;
                            }
                        } else {
                            if (aVar == null) {
                                aVar = new a(iCallback, i);
                            }
                            arrayList.add(aVar);
                        }
                    }
                }
            }
        } finally {
            d.unlock();
        }
    }

    public static void a(ICallback iCallback, String... strArr) {
        EventBus eventBus;
        if (iCallback == null || strArr == null || strArr.length == 0) {
            return;
        }
        d.lock();
        try {
            ReleaseLogUtil.e("PluginDevice_EventBus", "EventBus unsubscribe ", Arrays.toString(strArr));
            for (String str : strArr) {
                if (str != null && (eventBus = c.get(str)) != null) {
                    ArrayList<a> arrayList = eventBus.b;
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size && arrayList.get(i).e != iCallback) {
                        i++;
                    }
                    if (i < size) {
                        arrayList.remove(i);
                        if (arrayList.isEmpty()) {
                            HashMap<String, EventBus> hashMap = c;
                            hashMap.remove(str);
                            if (hashMap.isEmpty() && f2268a != null) {
                                f2268a = null;
                            }
                        }
                    }
                }
            }
        } finally {
            d.unlock();
        }
    }

    static class a {
        Handler d;
        ICallback e;

        a(ICallback iCallback, int i) {
            this.e = iCallback;
            if (i != 1) {
                if (i == 2) {
                    this.d = new Handler(Looper.getMainLooper());
                    return;
                } else {
                    this.d = null;
                    return;
                }
            }
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                LogUtil.c("PluginDevice_EventBus", "No Looper; Looper.prepare() wasn't called on this thread.");
            } else {
                this.d = new Handler(myLooper);
            }
        }

        void a(final b bVar) {
            Runnable runnable = new Runnable() { // from class: com.huawei.health.device.util.EventBus.a.1
                @Override // java.lang.Runnable
                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    try {
                        if (a.this.e != null) {
                            ReleaseLogUtil.e("PluginDevice_EventBus", "EventBus onEvent ", bVar.e());
                            a.this.e.onEvent(bVar);
                        }
                    } finally {
                        if (a.this.d == null) {
                            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                            if (currentTimeMillis2 > 5000 && a.this.e != null) {
                                LogUtil.c("PluginDevice_EventBus", a.this.e.getClass().getName(), " takes too long (", Long.valueOf(currentTimeMillis2), "ms)!");
                            }
                        }
                    }
                }
            };
            Handler handler = this.d;
            if (handler == null) {
                if (EventBus.f2268a == null) {
                    ThreadPoolExecutor unused = EventBus.f2268a = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
                    EventBus.f2268a.allowCoreThreadTimeOut(true);
                }
                EventBus.f2268a.execute(runnable);
                return;
            }
            handler.post(runnable);
        }
    }
}
