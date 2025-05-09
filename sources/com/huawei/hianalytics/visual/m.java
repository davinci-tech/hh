package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.ViewGroup;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.autocollect.instrument.ParamCollectorInstrumentation;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class m implements w {

    /* renamed from: a, reason: collision with root package name */
    public Handler f3936a;

    public class a extends Handler {
        public a(m mVar, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
        }
    }

    public static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final Activity f3937a;
        public final JSONObject b;

        public b(Activity activity, JSONObject jSONObject) {
            this.f3937a = activity;
            this.b = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.f3937a.getWindow() == null) {
                    return;
                }
                this.b.put("$browse_depth", o0.a(o0.a((ViewGroup) this.f3937a.getWindow().getDecorView()), this.f3937a));
                com.huawei.hianalytics.visual.b.a().a("$AppViewExit", this.b);
            } catch (Exception unused) {
                HiLog.w("ActivityPageStateMonitor", "fail to report page exit event");
            }
        }
    }

    public m() {
        a();
    }

    @Override // com.huawei.hianalytics.visual.w
    public void onActivityPaused(Activity activity) {
        JSONObject jSONObject;
        if (com.huawei.hianalytics.visual.b.a().c(activity.getClass().getCanonicalName())) {
            return;
        }
        if (com.huawei.hianalytics.visual.b.a().i(activity.getClass())) {
            return;
        }
        if (com.huawei.hianalytics.visual.b.a().a(EventType.PAGE_EXIT)) {
            return;
        }
        v vVar = v.c;
        if (vVar.b == null) {
            jSONObject = null;
        } else {
            vVar.b.getClass();
            jSONObject = q.e.get(Integer.valueOf(activity.hashCode()));
        }
        if (jSONObject == null) {
            return;
        }
        try {
            long a2 = n0.a(jSONObject.optLong("activity_onResume_timestamp"), SystemClock.elapsedRealtime());
            if (a2 < 50) {
                return;
            }
            jSONObject.put("$event_duration", a2);
            jSONObject.remove("activity_onResume_timestamp");
            n0.a(activity, jSONObject);
            m0.a().execute(new b(activity, jSONObject));
        } catch (Exception unused) {
            HiLog.w("ActivityPageStateMonitor", "fail to report page exit event");
        }
    }

    @Override // com.huawei.hianalytics.visual.w
    public void onActivityPostCreated(final Activity activity, Bundle bundle) {
        this.f3936a.post(new Runnable() { // from class: com.huawei.hianalytics.visual.m$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ParamCollectorInstrumentation.setActivityTag(activity);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a4, code lost:
    
        if (r0.getBooleanExtra("ha_deep_link_parsed", false) != false) goto L43;
     */
    @Override // com.huawei.hianalytics.visual.w
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityResumed(android.app.Activity r7) {
        /*
            r6 = this;
            java.lang.Class r0 = r7.getClass()
            java.lang.String r0 = r0.getCanonicalName()
            com.huawei.hianalytics.visual.c r1 = com.huawei.hianalytics.visual.b.a()
            boolean r0 = r1.c(r0)
            if (r0 == 0) goto L13
            return
        L13:
            java.lang.Class r0 = r7.getClass()
            com.huawei.hianalytics.visual.c r1 = com.huawei.hianalytics.visual.b.a()
            boolean r0 = r1.i(r0)
            if (r0 == 0) goto L22
            return
        L22:
            com.huawei.hianalytics.visual.autocollect.EventType r0 = com.huawei.hianalytics.visual.autocollect.EventType.PAGE_ENTER
            com.huawei.hianalytics.visual.c r1 = com.huawei.hianalytics.visual.b.a()
            boolean r0 = r1.a(r0)
            java.lang.String r1 = "PageEventFactory"
            r2 = 0
            if (r0 == 0) goto L32
            goto L74
        L32:
            com.huawei.hianalytics.visual.v r0 = com.huawei.hianalytics.visual.v.c
            com.huawei.hianalytics.visual.q r3 = r0.b
            if (r3 != 0) goto L39
            goto L3f
        L39:
            com.huawei.hianalytics.visual.q r0 = r0.b
            java.lang.String r0 = r0.d
            if (r0 != 0) goto L41
        L3f:
            java.lang.String r0 = ""
        L41:
            org.json.JSONObject r3 = com.huawei.hianalytics.visual.h0.b(r7)     // Catch: org.json.JSONException -> L5b
            java.lang.Class r4 = r7.getClass()     // Catch: org.json.JSONException -> L5b
            java.lang.String r4 = r4.getCanonicalName()     // Catch: org.json.JSONException -> L5b
            java.lang.String r5 = "$current_page"
            r3.put(r5, r4)     // Catch: org.json.JSONException -> L5b
            java.lang.String r4 = "$last_page"
            r3.put(r4, r0)     // Catch: org.json.JSONException -> L5b
            com.huawei.hianalytics.visual.n0.a(r7, r3)     // Catch: org.json.JSONException -> L5b
            goto L61
        L5b:
            java.lang.String r0 = "build page enter data failed, JSONException"
            com.huawei.hianalytics.core.log.HiLog.w(r1, r0)
            r3 = r2
        L61:
            if (r3 != 0) goto L6b
            java.lang.String r0 = "ActivityPageStateMonitor"
            java.lang.String r3 = "report activity enter failed, JSONException"
            com.huawei.hianalytics.core.log.HiLog.d(r0, r3)
            goto L74
        L6b:
            com.huawei.hianalytics.visual.c r0 = com.huawei.hianalytics.visual.b.a()
            java.lang.String r4 = "$AppViewScreen"
            r0.a(r4, r3)
        L74:
            com.huawei.hianalytics.visual.autocollect.EventType r0 = com.huawei.hianalytics.visual.autocollect.EventType.DEEP_LINK
            com.huawei.hianalytics.visual.c r3 = com.huawei.hianalytics.visual.b.a()
            boolean r0 = r3.a(r0)
            if (r0 == 0) goto L81
            goto Ld1
        L81:
            android.content.Intent r0 = r7.getIntent()
            if (r0 == 0) goto Lc5
            java.lang.String r3 = "android.intent.action.VIEW"
            java.lang.String r4 = r0.getAction()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto Lc5
            android.net.Uri r3 = r0.getData()
            if (r3 != 0) goto L9a
            goto Lc5
        L9a:
            java.lang.String r3 = "ha_deep_link_parsed"
            if (r0 != 0) goto L9f
            goto Lbd
        L9f:
            r4 = 0
            boolean r1 = r0.getBooleanExtra(r3, r4)     // Catch: java.lang.Throwable -> La7
            if (r1 == 0) goto Lbd
            goto Lc5
        La7:
            r2 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "isDeepLinkParsed error: "
            r4.<init>(r5)
            java.lang.String r2 = r2.getMessage()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.huawei.hianalytics.core.log.HiLog.e(r1, r2)
        Lbd:
            r1 = 1
            r0.putExtra(r3, r1)
            org.json.JSONObject r2 = com.huawei.hianalytics.visual.h0.b(r7)
        Lc5:
            if (r2 != 0) goto Lc8
            goto Ld1
        Lc8:
            com.huawei.hianalytics.visual.c r7 = com.huawei.hianalytics.visual.b.a()
            java.lang.String r0 = "$DeepLink"
            r7.a(r0, r2)
        Ld1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.m.onActivityResumed(android.app.Activity):void");
    }

    public final void a() {
        HandlerThread handlerThread = new HandlerThread("COLLECT_APP_EVENT");
        handlerThread.start();
        this.f3936a = new a(this, handlerThread.getLooper());
    }
}
