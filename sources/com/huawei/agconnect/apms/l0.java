package com.huawei.agconnect.apms;

import android.app.Activity;
import com.huawei.agconnect.apms.collect.model.event.interaction.ActivityLoadEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class l0 {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static volatile l0 bcd;
    public long hij;
    public long ijk;
    public long jkl;
    public long klm;
    public long lmn;
    public int efg = 0;
    public int fgh = 0;
    public int ghi = 0;
    public e0 cde = new e0();
    public WeakHashMap<Activity, Long> def = new WeakHashMap<>();
    public ExecutorService mno = Executors.newSingleThreadExecutor(new j0("AppRenderReporter"));

    public static class abc implements Runnable {
        public long abc;
        public String bcd;
        public String cde;
        public long def;
        public long efg;
        public long fgh;

        public abc(long j, String str, String str2, long j2, long j3, long j4) {
            this.abc = j;
            this.bcd = str;
            this.cde = str2;
            this.def = j2;
            this.efg = j3;
            this.fgh = j4;
        }

        @Override // java.lang.Runnable
        public void run() {
            rst.cde.add(new ActivityLoadEvent(this.abc, this.bcd, this.cde, this.def, this.efg, this.fgh));
        }
    }

    public static l0 abc() {
        if (bcd == null) {
            synchronized (l0.class) {
                if (bcd == null) {
                    bcd = new l0();
                }
            }
        }
        return bcd;
    }

    public final boolean bcd(Activity activity) {
        return (activity.getWindow() == null || (activity.getWindow().getAttributes().flags & 16777216) == 0) ? false : true;
    }

    public final String abc(Activity activity) {
        return activity.getClass().getSimpleName().length() != 0 ? activity.getClass().getSimpleName() : "";
    }
}
