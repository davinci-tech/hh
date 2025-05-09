package com.huawei.hianalytics;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.secure.android.common.util.SafeBase64;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class v extends j0 {

    /* renamed from: a, reason: collision with root package name */
    public long f3902a;

    /* renamed from: a, reason: collision with other field name */
    public final List<a> f86a;
    public String b;
    public String c;

    @Override // com.huawei.hianalytics.j0
    public void a(final Bundle bundle) {
        if (g.f3871a[0].equals(((j0) this).f42a) && s.a().f80c && "onResume".equalsIgnoreCase(bundle.getString("state"))) {
            TaskThread.getRecordThread().addToQueue(new Runnable() { // from class: com.huawei.hianalytics.v$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    v.this.b(bundle);
                }
            });
        }
    }

    public void b() {
        synchronized (this) {
            if (TextUtils.isEmpty(this.b)) {
                return;
            }
            ((j0) this).f3878a.removeMessages(2);
            this.f86a.add(new a(this.b, 1, this.f3902a, System.currentTimeMillis(), this.c));
            if (this.f86a.size() >= s.a().f70a) {
                c();
            } else {
                ((j0) this).f3878a.sendEmptyMessageDelayed(2, s.a().f81d);
            }
            this.b = "";
            this.f3902a = 0L;
            this.c = "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Bundle bundle) {
        try {
            ComponentName componentName = (ComponentName) bundle.getParcelable("comp");
            if (componentName == null) {
                return;
            }
            ((j0) this).f3878a.removeMessages(1);
            if (j.m563a(componentName.getClassName())) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = componentName.getPackageName();
                ((j0) this).f3878a.sendMessageDelayed(obtain, s.a().c);
            } else {
                b();
            }
        } catch (Throwable th) {
            HiLog.i("CC2", "cache " + th.getMessage());
        }
    }

    public void c() {
        synchronized (this) {
            if (this.f86a.isEmpty()) {
                return;
            }
            try {
                HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
                if (instanceByTag != null) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<a> it = this.f86a.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next().a());
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("liteApps", jSONArray);
                    jSONObject.put("version", "2.0");
                    if (HiLog.isDebugEnable()) {
                        HiLog.d("CC2", "onEvent:" + jSONObject);
                    }
                    instanceByTag.onEventSync(0, "101", jSONObject);
                }
            } catch (Throwable th) {
                HiLog.i("CC2", "flush fail: " + th.getMessage());
            }
            this.f86a.clear();
        }
    }

    @Override // com.huawei.hianalytics.j0
    public void a() {
        b();
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f3903a;

        /* renamed from: a, reason: collision with other field name */
        public long f87a;

        /* renamed from: a, reason: collision with other field name */
        public String f88a;
        public long b;

        /* renamed from: b, reason: collision with other field name */
        public String f89b;

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            String str = this.f88a;
            if (!TextUtils.isEmpty(str)) {
                str = SafeBase64.encodeToString(str.getBytes(StandardCharsets.UTF_8), 0);
            }
            if (!TextUtils.isEmpty(str)) {
                str = new StringBuilder(str).reverse().toString();
            }
            jSONObject.put("label", str);
            jSONObject.put("cnt", this.f3903a);
            jSONObject.put("firstTimeStamp", this.f87a);
            jSONObject.put("lastTimeStamp", this.b);
            jSONObject.put("version", "v2");
            jSONObject.put("packageName", this.f89b);
            return jSONObject;
        }

        public a(String str, int i, long j, long j2, String str2) {
            this.f88a = str;
            this.f3903a = i;
            this.f87a = j;
            this.b = j2;
            this.f89b = str2;
        }
    }

    public v(Handler handler, String str) {
        super(handler, str);
        this.f86a = new ArrayList();
    }
}
