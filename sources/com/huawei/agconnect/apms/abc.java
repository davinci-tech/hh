package com.huawei.agconnect.apms;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.agconnect.apms.anr.NativeHandler;
import com.huawei.agconnect.apms.hij;
import com.huawei.agconnect.apms.log.AgentLog;

/* loaded from: classes.dex */
public class abc implements Runnable {
    public final /* synthetic */ Context abc;
    public final /* synthetic */ cde bcd;

    /* renamed from: com.huawei.agconnect.apms.abc$abc, reason: collision with other inner class name */
    public class RunnableC0032abc implements Runnable {
        public RunnableC0032abc() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NativeHandler.bcd().abc(abc.this.bcd.efg);
        }
    }

    public abc(Context context, cde cdeVar) {
        this.abc = context;
        this.bcd = cdeVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        klm klmVar;
        try {
            Agent.setImpl(new bcd(this.abc, this.bcd));
            if (Agent.isDisabled()) {
                bcd.abc.info("APMS has been disabled.");
                return;
            }
            AgentLog agentLog = hij.abc;
            hij.bcd.abc.getClass();
            synchronized (klm.class) {
                if (klm.efg == null) {
                    klm.efg = new klm();
                }
                klmVar = klm.efg;
            }
            klmVar.abc(this.bcd.efg);
            new Handler(Looper.getMainLooper()).post(new RunnableC0032abc());
            o0.abc.lmn();
            Agent.start();
        } catch (Throwable th) {
            bcd.abc.error("failed to initialize APMS: " + th.toString());
        }
    }
}
