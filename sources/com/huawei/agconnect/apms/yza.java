package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes2.dex */
public class yza {
    public static Future def;
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static final ScheduledExecutorService bcd = Executors.newSingleThreadScheduledExecutor(new j0("HttpEventWaitReportQueue"));
    public static final ConcurrentLinkedQueue<Object> cde = new ConcurrentLinkedQueue<>();
    public static final Runnable efg = new abc();

    public class abc implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            vwx e;
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            Iterator<Object> it = yza.cde.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof wxy) {
                    wxy wxyVar = (wxy) next;
                    if (currentTimeMillis - wxyVar.lmn > PreConnectManager.CONNECT_INTERNAL) {
                        arrayList.add(next);
                        if (!wxyVar.gfe() && (e = wxyVar.e()) != null) {
                            wxyVar.abc(true);
                            rst.cde.add(new HttpEvent(e, ""));
                        }
                    }
                } else {
                    yza.abc.warn("object is not a state of Http.");
                    arrayList.add(next);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            yza.cde.removeAll(arrayList);
        }
    }
}
