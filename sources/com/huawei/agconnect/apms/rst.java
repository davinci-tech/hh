package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.fps.FpsDropEvent;
import com.huawei.agconnect.apms.collect.model.event.interaction.ActivityLoadEvent;
import com.huawei.agconnect.apms.collect.model.event.interaction.ActivityRenderEvent;
import com.huawei.agconnect.apms.collect.model.event.interaction.AppStartEvent;
import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import com.huawei.agconnect.apms.collect.model.event.webview.WebViewErrorEvent;
import com.huawei.agconnect.apms.collect.model.event.webview.WebViewLoadEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes2.dex */
public class rst extends opq {
    public static Future def;
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static final ScheduledExecutorService bcd = Executors.newSingleThreadScheduledExecutor(new j0("CollectQueue"));
    public static final ConcurrentLinkedQueue<Object> cde = new ConcurrentLinkedQueue<>();
    public static final Runnable efg = new abc();

    public class abc implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            if (rst.cde.size() == 0) {
                return;
            }
            while (true) {
                ConcurrentLinkedQueue<Object> concurrentLinkedQueue = rst.cde;
                if (concurrentLinkedQueue.isEmpty()) {
                    return;
                }
                try {
                    Object remove = concurrentLinkedQueue.remove();
                    if (remove instanceof AppStartEvent) {
                        nop.abc((AppStartEvent) remove, nop.bcd.fgh.getAppStartEvents());
                    } else if (remove instanceof ActivityLoadEvent) {
                        nop.abc((ActivityLoadEvent) remove, nop.bcd.fgh.getActivityLoadEvents());
                    } else if (remove instanceof ActivityRenderEvent) {
                        nop.abc((ActivityRenderEvent) remove, nop.bcd.fgh.getActivityRenderEvents());
                    } else if (remove instanceof HttpEvent) {
                        nop.abc((HttpEvent) remove, nop.bcd.fgh.getHttpEvents());
                    } else if (remove instanceof WebViewLoadEvent) {
                        nop.abc((WebViewLoadEvent) remove, nop.bcd.fgh.getWebViewLoadEvents());
                    } else if (remove instanceof WebViewErrorEvent) {
                        nop.abc((WebViewErrorEvent) remove, nop.bcd.fgh.getWebViewErrorEvents());
                    } else if (remove instanceof FpsDropEvent) {
                        nop.abc((FpsDropEvent) remove, nop.bcd.fgh.getFpsDropEvents());
                    }
                } catch (Throwable th) {
                    rst.abc.error("exception occurred when dequeue events: " + th.toString());
                }
            }
        }
    }

    public static void jkl() {
        try {
            bcd.submit(efg).get();
        } catch (InterruptedException e) {
            e = e;
            abc.error("exception occurred when synchronous events: " + e.toString());
        } catch (ExecutionException e2) {
            e = e2;
            abc.error("exception occurred when synchronous events: " + e.toString());
        } catch (Throwable th) {
            abc.error("exception occurred when synchronous events: " + th.toString());
        }
    }
}
