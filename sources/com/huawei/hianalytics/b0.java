package com.huawei.hianalytics;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
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
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b0 extends j0 {

    /* renamed from: a, reason: collision with root package name */
    public final List<a> f3835a;
    public String b;
    public String c;
    public String d;
    public String e;

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Bundle bundle) {
        ComponentName componentName;
        try {
            componentName = (ComponentName) bundle.getParcelable("comp");
        } catch (Throwable unused) {
            componentName = null;
        }
        if (componentName == null) {
            return;
        }
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        String shortClassName = componentName.getShortClassName();
        if (TextUtils.isEmpty(shortClassName)) {
            shortClassName = null;
        } else {
            int lastIndexOf = shortClassName.lastIndexOf(".");
            if (lastIndexOf > -1) {
                shortClassName = shortClassName.substring(lastIndexOf + 1);
            }
        }
        if (!TextUtils.isEmpty(className)) {
            Iterator<String> it = s.a().f79c.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(className, it.next())) {
                    return;
                }
            }
        }
        if (!TextUtils.isEmpty(this.b) && !TextUtils.isEmpty(className)) {
            Iterator<String> it2 = s.a().f76b.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (className.startsWith(it2.next())) {
                    this.c = packageName;
                    a aVar = new a();
                    aVar.f20a = this.b;
                    aVar.b = this.c;
                    aVar.c = this.d;
                    aVar.d = shortClassName;
                    aVar.f3836a = System.currentTimeMillis();
                    String str = this.e;
                    if (!TextUtils.isEmpty(str)) {
                        str = SafeBase64.encodeToString(str.getBytes(StandardCharsets.UTF_8), 0);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        str = new StringBuilder(str).reverse().toString();
                    }
                    aVar.e = str;
                    synchronized (this) {
                        ((j0) this).f3878a.removeMessages(6);
                        this.f3835a.add(aVar);
                        if (this.f3835a.size() >= s.a().f70a) {
                            b();
                        } else {
                            ((j0) this).f3878a.sendEmptyMessageDelayed(6, s.a().f81d);
                        }
                    }
                }
            }
        }
        this.b = packageName;
        this.d = shortClassName;
        if (!j.m563a(className)) {
            this.e = null;
        } else {
            ((j0) this).f3878a.removeMessages(7);
            ((j0) this).f3878a.sendEmptyMessageDelayed(7, s.a().c);
        }
    }

    @Override // com.huawei.hianalytics.j0
    public void a() {
    }

    @Override // com.huawei.hianalytics.j0
    public void a(final Bundle bundle) {
        if (s.a().e) {
            String str = s.a().f72a;
            if ((TextUtils.equals(str, "all") || TextUtils.equals(str, ((j0) this).f42a)) && "onResume".equalsIgnoreCase(bundle.getString("state")) && (!bundle.getBoolean("isHomeActivity"))) {
                TaskThread.getRecordThread().addToQueue(new Runnable() { // from class: com.huawei.hianalytics.b0$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        b0.this.b(bundle);
                    }
                });
            }
        }
    }

    public void b() {
        synchronized (this) {
            if (this.f3835a.isEmpty()) {
                return;
            }
            try {
                HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
                if (instanceByTag != null) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<a> it = this.f3835a.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next().a());
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("pageOpen", jSONArray);
                    instanceByTag.onEventSync(0, "106", jSONObject);
                }
            } catch (Throwable th) {
                HiLog.i("CC3", "flush fail: " + th.getMessage());
            }
            this.f3835a.clear();
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public long f3836a;

        /* renamed from: a, reason: collision with other field name */
        public String f20a;
        public String b;
        public String c;
        public String d;
        public String e;

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("package_name", this.b);
                jSONObject.put("dst_package_name", this.b);
                jSONObject.put("dst_page_name", this.d);
                jSONObject.put("src_package_name", this.f20a);
                jSONObject.put("src_page_name", this.c);
                jSONObject.put("triggerTime", this.f3836a);
                jSONObject.put("version", "v2");
                if (!TextUtils.isEmpty(this.e)) {
                    jSONObject.put("lite_name", this.e);
                }
            } catch (JSONException unused) {
                HiLog.w("CC3", "toJson error");
            }
            return jSONObject;
        }
    }

    public b0(Handler handler, String str) {
        super(handler, str);
        this.f3835a = new ArrayList();
    }
}
