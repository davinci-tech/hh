package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class o implements p {

    /* renamed from: a, reason: collision with root package name */
    public final Map<Integer, JSONObject> f3941a = new HashMap();

    public static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final Object f3942a;
        public final JSONObject b;

        public a(Object obj, JSONObject jSONObject) {
            this.f3942a = obj;
            this.b = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Activity a2 = f0.a(this.f3942a);
                if (a2.getWindow() != null) {
                    this.b.put("$browse_depth", o0.a(o0.a((ViewGroup) a2.getWindow().getDecorView()), a2));
                    b.a().a("$AppViewExit", this.b);
                }
            } catch (Exception unused) {
                HiLog.w("HAFPExit", "fail to report page exit event");
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.p
    public void a(Object obj) {
        if (f0.f(obj)) {
            d(obj);
        }
    }

    @Override // com.huawei.hianalytics.visual.p
    public void a(Object obj, View view, Bundle bundle) {
    }

    @Override // com.huawei.hianalytics.visual.p
    public void b(Object obj) {
        if (obj == null) {
            return;
        }
        e(obj);
    }

    public final boolean c(Object obj) {
        if (b.a().a(EventType.PAGE_EXIT)) {
            return false;
        }
        return !b.a().h(obj.getClass());
    }

    public final void d(Object obj) {
        if (obj != null && c(obj)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("enter_fragment_timestamp", SystemClock.elapsedRealtime());
                String canonicalName = obj.getClass().getCanonicalName();
                jSONObject.put("$current_page", canonicalName);
                jSONObject.put("$last_page", h0.f3921a);
                n0.a(f0.a(obj, (Activity) null), jSONObject);
                this.f3941a.put(Integer.valueOf(obj.hashCode()), jSONObject);
                h0.f3921a = canonicalName;
            } catch (Exception unused) {
                HiLog.w("HAFPExit", "fail to acquire fragment property");
            }
        }
    }

    public final void e(Object obj) {
        if (obj != null && c(obj)) {
            int hashCode = obj.hashCode();
            if (this.f3941a.containsKey(Integer.valueOf(hashCode))) {
                try {
                    JSONObject jSONObject = this.f3941a.get(Integer.valueOf(hashCode));
                    if (jSONObject == null) {
                        return;
                    }
                    long a2 = n0.a(jSONObject.optLong("enter_fragment_timestamp"), SystemClock.elapsedRealtime());
                    if (a2 < 50) {
                        return;
                    }
                    jSONObject.put("$event_duration", a2);
                    jSONObject.remove("enter_fragment_timestamp");
                    n0.a(obj, jSONObject);
                    m0.a().execute(new a(obj, jSONObject));
                    this.f3941a.remove(Integer.valueOf(hashCode));
                } catch (Exception unused) {
                    HiLog.w("HAFPExit", "fail to report fragment exit");
                }
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.p
    public void b(Object obj, boolean z) {
        if (f0.f(obj)) {
            d(obj);
        } else {
            e(obj);
        }
    }

    @Override // com.huawei.hianalytics.visual.p
    public void a(Object obj, boolean z) {
        if (f0.f(obj)) {
            d(obj);
        } else {
            e(obj);
        }
    }
}
