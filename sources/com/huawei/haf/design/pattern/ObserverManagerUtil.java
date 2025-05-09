package com.huawei.haf.design.pattern;

import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ObserverManagerUtil {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, Observable> f2106a = new HashMap<>();
    private static final Object d = new Object();

    public static void d(Observer observer, String str) {
        if (observer == null) {
            return;
        }
        synchronized (d) {
            if (!f2106a.containsKey(str)) {
                Observable observable = new Observable(str);
                observable.a(observer);
                f2106a.put(str, observable);
            } else {
                Observable observable2 = f2106a.get(str);
                observable2.a(observer);
                f2106a.put(str, observable2);
            }
        }
    }

    public static void c(Observer observer) {
        if (observer == null) {
            return;
        }
        synchronized (d) {
            Iterator<Observable> it = f2106a.values().iterator();
            while (it.hasNext()) {
                it.next().d(observer);
            }
        }
    }

    public static void e(Observer observer, String str) {
        if (observer == null) {
            return;
        }
        synchronized (d) {
            if (f2106a.containsKey(str)) {
                if (f2106a.get(str).d(observer) == 0) {
                    f2106a.remove(str);
                }
            } else {
                LogUtil.a("HAF_SubscribeManager", "unregister label = ", str, " not registered");
            }
        }
    }

    public static void e(String str) {
        synchronized (d) {
            if (f2106a.containsKey(str)) {
                f2106a.get(str).e();
                f2106a.remove(str);
            } else {
                LogUtil.a("HAF_SubscribeManager", "unregisterObserver action = ", str, " not registered");
            }
        }
    }

    public static boolean c(String str, Object... objArr) {
        synchronized (d) {
            if (!f2106a.containsKey(str)) {
                LogUtil.a("HAF_SubscribeManager", "sendBroadcast action = ", str, " not registered");
                return false;
            }
            Observable observable = f2106a.get(str);
            if (observable != null) {
                observable.b(objArr);
            }
            return true;
        }
    }
}
