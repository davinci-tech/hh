package com.huawei.agconnect.apms;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Printer;
import com.huawei.agconnect.apms.collect.model.event.fps.FpsDropEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class h0 implements Printer {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public boolean bcd;
    public final HandlerThread cde;
    public long def;
    public final Handler efg;
    public final bcd fgh;

    public class abc extends Handler {
        public abc(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            long j = data.getLong("frame_start");
            long j2 = data.getLong("frame_end");
            long j3 = j2 - j;
            h0.abc.debug("Fps drop cost: " + j3);
            bcd bcdVar = h0.this.fgh;
            bcdVar.getClass();
            ArrayList arrayList = new ArrayList();
            synchronized (bcdVar.cde) {
                for (Long l : bcdVar.cde.keySet()) {
                    if (l.longValue() >= j && l.longValue() <= j2) {
                        arrayList.add(l + System.lineSeparator() + bcdVar.cde.get(l));
                    }
                }
            }
            rst.cde.add(new FpsDropEvent(arrayList, j3, j2));
        }
    }

    public static class bcd implements Runnable {
        public final Handler abc;
        public final AtomicBoolean bcd = new AtomicBoolean(false);
        public final LinkedHashMap<Long, String> cde = new LinkedHashMap<>();
        public String def;

        public bcd(Handler handler) {
            this.abc = handler;
        }

        public void abc() {
            if (this.abc == null) {
                h0.abc.warn("stopTrace, stackHandler == null");
            } else if (this.bcd.get()) {
                this.bcd.set(false);
                this.abc.removeCallbacks(this);
                this.def = null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString());
                sb.append(System.lineSeparator());
            }
            synchronized (this.cde) {
                if (this.cde.size() == 20) {
                    LinkedHashMap<Long, String> linkedHashMap = this.cde;
                    linkedHashMap.remove(linkedHashMap.keySet().iterator().next());
                }
                if (!TextUtils.equals(this.def, sb.toString())) {
                    this.cde.put(Long.valueOf(System.currentTimeMillis()), sb.toString());
                    this.def = sb.toString();
                }
            }
            if (this.bcd.get()) {
                this.abc.postDelayed(this, 300L);
            }
        }
    }

    public h0() {
        HandlerThread handlerThread = new HandlerThread("FrameTrace");
        this.cde = handlerThread;
        handlerThread.start();
        abc abcVar = new abc(handlerThread.getLooper());
        this.efg = abcVar;
        this.fgh = new bcd(abcVar);
    }

    @Override // android.util.Printer
    public void println(String str) {
        if (!this.bcd) {
            this.bcd = true;
            this.def = System.currentTimeMillis();
            bcd bcdVar = this.fgh;
            if (bcdVar.abc == null) {
                abc.warn("startTrace, stackHandler == null");
                return;
            } else {
                if (bcdVar.bcd.get()) {
                    return;
                }
                bcdVar.bcd.set(true);
                bcdVar.abc.postDelayed(bcdVar, 32L);
                return;
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.def;
        if (j != 0 && currentTimeMillis - j > Agent.getAgentConfiguration().ijk) {
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putLong("frame_start", this.def);
            bundle.putLong("frame_end", currentTimeMillis);
            obtain.setData(bundle);
            this.efg.sendMessage(obtain);
        }
        this.bcd = false;
        this.fgh.abc();
    }
}
