package com.huawei.haf.application;

import android.app.Activity;
import com.huawei.haf.common.dfx.DfxMonitorTask;
import com.huawei.haf.common.dfx.memory.MemoryMonitor;
import com.huawei.haf.handler.HandlerExecutor;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
final class ActivityLeakCheck implements DfxMonitorTask, Runnable {
    private Class b;
    private int d;
    private final String i;
    private final Map<Activity, Long> e = new WeakHashMap();

    /* renamed from: a, reason: collision with root package name */
    private final Map<Activity, Long> f2058a = new WeakHashMap();
    private boolean c = true;

    ActivityLeakCheck(String str) {
        this.i = str;
    }

    void vQ_(Activity activity, long j, boolean z) {
        if (MemoryMonitor.a()) {
            this.e.put(activity, Long.valueOf(j));
        }
        this.b = z ? activity.getClass() : null;
    }

    void b(boolean z) {
        boolean z2 = !z;
        this.c = z2;
        if (z2) {
            DfxMonitorCenter.a();
            if (MemoryMonitor.a()) {
                this.d = 0;
                DfxMonitorCenter.d(this);
            }
        }
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public long monitorDelayTime() {
        return this.d <= 0 ? 60000L : 300000L;
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public void onMonitor() {
        HandlerExecutor.a(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.d <= 0) {
                a(currentTimeMillis);
            }
            c(currentTimeMillis);
            if (this.d > 0) {
                DfxMonitorCenter.d(this);
            }
        }
    }

    private void a(long j) {
        if (this.e.isEmpty()) {
            return;
        }
        StringBuilder sb = null;
        for (Map.Entry<Activity, Long> entry : this.e.entrySet()) {
            Activity key = entry.getKey();
            if (key != null) {
                this.f2058a.put(key, entry.getValue());
                sb = vP_(sb, key, j - entry.getValue().longValue());
                LogUtil.a(this.i, sb.toString());
            }
        }
        this.e.clear();
    }

    private void c(long j) {
        int i = 0;
        this.d = 0;
        if (this.f2058a.isEmpty()) {
            return;
        }
        HashMap hashMap = new HashMap(this.f2058a.size());
        StringBuilder sb = null;
        for (Map.Entry<Activity, Long> entry : this.f2058a.entrySet()) {
            Activity key = entry.getKey();
            if (key != null) {
                String name = key.getClass().getName();
                Integer num = (Integer) hashMap.get(name);
                hashMap.put(name, num == null ? 1 : Integer.valueOf(num.intValue() + 1));
                if (key.getClass() == this.b) {
                    i++;
                } else {
                    sb = b(entry, j, sb);
                }
            }
        }
        if (i > 1) {
            a(j, sb);
        }
        if (hashMap.isEmpty()) {
            return;
        }
        if (hashMap.size() == 1 && i == 1) {
            return;
        }
        LogUtil.a(this.i, "Memory leak info: ", hashMap);
    }

    private void a(long j, StringBuilder sb) {
        for (Map.Entry<Activity, Long> entry : this.f2058a.entrySet()) {
            Activity key = entry.getKey();
            if (key != null && key.getClass() == this.b) {
                sb = b(entry, j, sb);
            }
        }
    }

    private StringBuilder b(Map.Entry<Activity, Long> entry, long j, StringBuilder sb) {
        long longValue = j - entry.getValue().longValue();
        if (longValue == j) {
            return sb;
        }
        if (longValue > 300000) {
            Activity key = entry.getKey();
            StringBuilder vP_ = vP_(sb, key, longValue);
            MemoryMonitor.b(key.getClass().getName(), null, vP_.toString(), j);
            entry.setValue(0L);
            return vP_;
        }
        this.d++;
        return sb;
    }

    private StringBuilder vP_(StringBuilder sb, Activity activity, long j) {
        if (sb == null) {
            sb = new StringBuilder(128);
        }
        sb.delete(0, sb.length());
        sb.append("Memory leak risk: ");
        sb.append(activity);
        sb.append(", time(ms)=");
        sb.append(j);
        return sb;
    }
}
