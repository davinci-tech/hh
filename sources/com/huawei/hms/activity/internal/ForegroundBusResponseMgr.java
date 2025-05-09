package com.huawei.hms.activity.internal;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ForegroundBusResponseMgr {
    private static final ForegroundBusResponseMgr b = new ForegroundBusResponseMgr();

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, BusResponseCallback> f4260a = new HashMap();

    public static ForegroundBusResponseMgr getInstance() {
        return b;
    }

    public BusResponseCallback get(String str) {
        BusResponseCallback busResponseCallback;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.f4260a) {
            busResponseCallback = this.f4260a.get(str);
        }
        return busResponseCallback;
    }

    public void registerObserver(String str, BusResponseCallback busResponseCallback) {
        if (TextUtils.isEmpty(str) || busResponseCallback == null) {
            return;
        }
        synchronized (this.f4260a) {
            if (!this.f4260a.containsKey(str)) {
                this.f4260a.put(str, busResponseCallback);
            }
        }
    }

    public void unRegisterObserver(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f4260a) {
            this.f4260a.remove(str);
        }
    }
}
