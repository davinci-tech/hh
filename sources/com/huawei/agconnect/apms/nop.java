package com.huawei.agconnect.apms;

import android.os.Looper;
import com.huawei.agconnect.apms.anr.NativeHandler;
import com.huawei.agconnect.apms.collect.model.CollectData;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.collect.model.event.Events;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.stu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class nop {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static nop bcd = new nop();
    public static final Collection<qrs> cde = new ArrayList();
    public uvw def;
    public stu efg;
    public CollectData fgh;
    public pqr ghi = pqr.abc();

    public static void abc(boolean z) {
        if (bcd()) {
            stu stuVar = bcd.efg;
            stuVar.getClass();
            try {
                ScheduledFuture<?> schedule = stuVar.bcd.schedule(new stu.abc(stuVar, z), 0L, TimeUnit.SECONDS);
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    return;
                }
                schedule.get();
            } catch (Throwable th) {
                stu.abc.error("exception occurred while executing tick: " + th.getMessage());
            }
        }
    }

    public static boolean bcd() {
        nop nopVar = bcd;
        return (nopVar == null || nopVar.def == null) ? false : true;
    }

    public static void cde() {
        nop nopVar = bcd;
        if (nopVar == null) {
            return;
        }
        stu stuVar = nopVar.efg;
        if (stuVar == null) {
            abc.warn("the collect timer is null, can not stop timer.");
            return;
        }
        stuVar.getClass();
        if (n.abc().bcd() && stuVar.bcd()) {
            stu.abc.debug("collector will start when app in foreground.");
            return;
        }
        if (stuVar.cde != null) {
            return;
        }
        if (stuVar.def <= 0) {
            stu.abc.error("refusing to start with period 0 ms.");
            return;
        }
        stu.abc.debug("starting collector with period " + stuVar.def + "ms.");
        System.currentTimeMillis();
        try {
            stuVar.cde = stuVar.bcd.scheduleAtFixedRate(stuVar, 0L, stuVar.def, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            stu.abc.error("reporter timer started failed. message: " + th);
        }
        uvw uvwVar = stuVar.efg;
        uvwVar.getClass();
        try {
            Iterator it = ((ArrayList) uvwVar.cde()).iterator();
            while (it.hasNext()) {
                ((qrs) it.next()).fgh();
            }
        } catch (Throwable th2) {
            uvw.abc.error("exception occurred while notifying onCollectStart: " + th2.getMessage());
        }
        if (uvwVar.hij == null) {
            uvwVar.hij = new tuv(uvwVar);
            NativeHandler bcd2 = NativeHandler.bcd();
            jkl jklVar = uvwVar.hij;
            synchronized (bcd2.bcd) {
                Set<jkl> set = bcd2.cde;
                if (set != null) {
                    set.add(jklVar);
                }
            }
        }
    }

    public static void def() {
        nop nopVar = bcd;
        if (nopVar == null) {
            return;
        }
        stu stuVar = nopVar.efg;
        if (stuVar == null) {
            abc.warn("the collect timer is null, can not stop timer.");
        } else {
            stuVar.cde();
        }
    }

    public final void abc(cde cdeVar) {
        if (this.fgh == null) {
            this.fgh = new CollectData();
        }
        if (this.def == null) {
            uvw uvwVar = new uvw();
            this.def = uvwVar;
            uvwVar.fgh = this.fgh;
        }
        if (this.efg == null) {
            this.efg = new stu(this.def);
        }
        uvw uvwVar2 = this.def;
        uvwVar2.def = cdeVar;
        uvwVar2.efg = bcd.ghi;
    }

    public static void abc(qrs qrsVar) {
        if (qrsVar == null) {
            return;
        }
        if (!bcd()) {
            Collection<qrs> collection = cde;
            if (collection.contains(qrsVar)) {
                return;
            }
            synchronized (collection) {
                collection.add(qrsVar);
            }
            return;
        }
        bcd.def.abc(qrsVar);
    }

    public static void abc(pqr pqrVar) {
        if (!bcd()) {
            abc.error("Collector: cannot configure Collector before initialization.");
            return;
        }
        nop nopVar = bcd;
        pqr pqrVar2 = nopVar.ghi;
        pqrVar2.getClass();
        pqrVar2.bcd = pqrVar.bcd;
        pqrVar2.cde = pqrVar.cde;
        pqrVar2.def = pqrVar.def;
        pqrVar2.efg = pqrVar.efg;
        pqrVar2.fgh = pqrVar.fgh;
        pqrVar2.ghi = pqrVar.ghi;
        nopVar.efg.def = TimeUnit.MILLISECONDS.convert(nopVar.ghi.bcd, TimeUnit.SECONDS);
        nopVar.def.efg = nopVar.ghi;
    }

    public static pqr abc() {
        if (!bcd()) {
            return pqr.abc();
        }
        return bcd.ghi;
    }

    public static <T extends Event> void abc(T t, Events<T> events) {
        if (bcd() && 4 == bcd.def.bcd) {
            return;
        }
        int i = bcd.ghi.def;
        if (events.count() >= i) {
            abc.debug("Collector: events count limitation " + i + " reached, " + t.getClass().getSimpleName() + " dropped.");
            return;
        }
        events.add(t);
        abc.debug("Collector: now contains [" + events.count() + "] " + t.getClass().getSimpleName());
    }
}
