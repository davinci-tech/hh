package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.autocollect.exposure.ViewMark;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.s0;
import com.huawei.hihealth.model.SampleEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class d implements c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3913a;
    public final HiAnalyticsInstance c;
    public final HAAutoConfigOptions d;
    public boolean k;
    public boolean l;
    public boolean b = true;
    public Set<Integer> e = new CopyOnWriteArraySet();
    public Set<Integer> f = new CopyOnWriteArraySet();
    public Set<Class<?>> h = new CopyOnWriteArraySet();
    public final Set<Integer> g = new CopyOnWriteArraySet();
    public final List<String> i = new CopyOnWriteArrayList();
    public JSONObject j = new JSONObject();
    public final Map<String, Set<String>> m = new HashMap();

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONObject f3914a;
        public final /* synthetic */ u0 b;
        public final /* synthetic */ String c;

        public a(JSONObject jSONObject, u0 u0Var, String str) {
            this.f3914a = jSONObject;
            this.b = u0Var;
            this.c = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:141:0x025c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:142:0x025d  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 764
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.d.a.run():void");
        }
    }

    public d(Context context, HiAnalyticsInstance hiAnalyticsInstance, HiAnalyticsConfig hiAnalyticsConfig, HiAnalyticsConfig hiAnalyticsConfig2, HAAutoConfigOptions hAAutoConfigOptions) {
        this.f3913a = a(context);
        this.c = hiAnalyticsInstance;
        this.d = hAAutoConfigOptions;
        if (hiAnalyticsConfig != null) {
            this.k = true;
        }
        if (hiAnalyticsConfig2 != null) {
            this.l = true;
        }
        b(context);
        d0.a(context);
        b(context, hAAutoConfigOptions);
        a(context, hAAutoConfigOptions);
    }

    public static void a(Context context, HiAnalyticsConfig hiAnalyticsConfig, HiAnalyticsConfig hiAnalyticsConfig2, HAAutoConfigOptions hAAutoConfigOptions) {
        if (context == null) {
            HiLog.sw("HAAgentImpl", "create(): instance context is null, create failed! TAG: hianalytics_default_autocollect");
            return;
        }
        if (!hAAutoConfigOptions.isAutoCollectEnabled()) {
            HiLog.sw("HAAgentImpl", "hiAnalytics auto collect instance is disabled, please enable");
            return;
        }
        d dVar = new d(context, new HiAnalyticsInstance.Builder(context).setOperConf(hiAnalyticsConfig).setMaintConf(hiAnalyticsConfig2).refresh("hianalytics_default_autocollect"), hiAnalyticsConfig, hiAnalyticsConfig2, hAAutoConfigOptions);
        synchronized (b.c) {
            b.b = dVar;
        }
        j jVar = j.b;
        synchronized (jVar) {
            if (b.a().b()) {
                jVar.f3929a.clear();
                HiLog.d("ViewMarkCache", "view mark clear: auto collect disabled");
            } else {
                Activity b = v.c.b();
                Activity c = v.c.c();
                if (b == null && c == null) {
                    jVar.f3929a.clear();
                    HiLog.d("ViewMarkCache", "view mark clear: activity null");
                } else {
                    List<ViewMark> list = c != null ? jVar.f3929a.get(Integer.valueOf(c.hashCode())) : jVar.f3929a.get(Integer.valueOf(b.hashCode()));
                    jVar.f3929a.clear();
                    if (list != null && !list.isEmpty()) {
                        Iterator<ViewMark> it = list.iterator();
                        while (it.hasNext()) {
                            b.a().a(it.next());
                        }
                    }
                }
            }
        }
        if (!b.a().b()) {
            if (!b.a().a(EventType.APP_INSTALL)) {
                com.huawei.hianalytics.visual.a.b();
            }
        }
        HiLog.i("HAAgentImpl", "HiAnalytics Visual auto collect instance init end. ========sdk V:3.2.12.500");
    }

    public final void b(Context context) {
        Application application = (Application) context.getApplicationContext();
        v vVar = v.c;
        synchronized (vVar) {
            if (vVar.b == null) {
                vVar.b = new q();
                application.registerActivityLifecycleCallbacks(vVar.b);
            }
        }
        v vVar2 = v.c;
        vVar2.getClass();
        vVar2.a(new m());
        vVar2.a(h.a());
        vVar2.a(new f());
        FragmentInstrumentation.addHAFragmentCallbacks(new n());
        FragmentInstrumentation.addHAFragmentCallbacks(new o());
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean c() {
        return this.d.isWebViewBridgeEnabled();
    }

    @Override // com.huawei.hianalytics.visual.c
    public void d(List<Class<?>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Class<?> cls : list) {
            if (cls != null) {
                this.g.add(Integer.valueOf(cls.hashCode()));
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public String e() {
        return k0.b.f3932a.f3930a;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void f() {
        Context context = this.f3913a;
        if (context == null) {
            HiLog.sw("HAAgentImpl", "syncVisualConfig context is null");
            return;
        }
        HAAutoConfigOptions hAAutoConfigOptions = this.d;
        if (hAAutoConfigOptions == null) {
            HiLog.sw("HAAgentImpl", "syncVisualConfig haAutoConfigOptions is null");
        } else {
            b(context, hAAutoConfigOptions);
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public Context g() {
        return this.f3913a;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void h(List<Class<?>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (this.f == null) {
            this.f = new CopyOnWriteArraySet();
        }
        for (Class<?> cls : list) {
            if (cls != null) {
                this.f.remove(Integer.valueOf(cls.hashCode()));
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean i() {
        return this.d.isVisualizedEnabled() || this.d.isVisualConfigEnabled();
    }

    @Override // com.huawei.hianalytics.visual.c
    public void c(List<EventType> list) {
        if (list == null || this.d.getAutoCollectEventTypes().isEmpty()) {
            return;
        }
        Iterator<EventType> it = list.iterator();
        while (it.hasNext()) {
            this.d.getAutoCollectEventTypes().remove(it.next());
        }
        if (this.d.getAutoCollectEventTypes().isEmpty()) {
            this.b = false;
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean g(Class<?> cls) {
        if (cls == null) {
            return true;
        }
        if (this.g.isEmpty()) {
            return false;
        }
        return !this.g.contains(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean i(Class<?> cls) {
        Set<Integer> set;
        return (cls == null || (set = this.e) == null || !set.contains(Integer.valueOf(cls.hashCode()))) ? false : true;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void e(Class<?> cls) {
        if (cls == null) {
            return;
        }
        this.g.add(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.huawei.hianalytics.visual.c
    public void e(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str) && !this.i.contains(str)) {
                this.i.add(str);
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public void g(List<Class<?>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (this.e == null) {
            this.e = new CopyOnWriteArraySet();
        }
        for (Class<?> cls : list) {
            if (cls != null) {
                this.e.add(Integer.valueOf(cls.hashCode()));
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public void f(List<EventType> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.b = true;
        this.d.setAutoCollectEventTypes(list);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void d(Class<?> cls) {
        if (cls == null) {
            return;
        }
        if (this.f == null) {
            this.f = new CopyOnWriteArraySet();
        }
        this.f.add(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.huawei.hianalytics.visual.c
    public void c(Class<?> cls) {
        if (cls == null) {
            return;
        }
        if (this.e == null) {
            this.e = new CopyOnWriteArraySet();
        }
        this.e.remove(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean h(Class<?> cls) {
        Set<Integer> set;
        return (cls == null || (set = this.f) == null || !set.contains(Integer.valueOf(cls.hashCode()))) ? false : true;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void d() {
        HiAnalyticsInstance hiAnalyticsInstance = this.c;
        if (hiAnalyticsInstance == null) {
            HiLog.w("HAAgentImpl", "onReport, HiAnalyticsAgentImpl auto Instance is null");
            return;
        }
        if (this.k) {
            hiAnalyticsInstance.onReport(0);
        }
        if (this.l) {
            this.c.onReport(1);
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public void f(Class<?> cls) {
        if (cls == null) {
            return;
        }
        if (this.f == null) {
            this.f = new CopyOnWriteArraySet();
        }
        this.f.remove(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.huawei.hianalytics.visual.c
    public void h() {
        if (this.j == null) {
            return;
        }
        this.j = new JSONObject();
    }

    public final void b(Context context, HAAutoConfigOptions hAAutoConfigOptions) {
        if (hAAutoConfigOptions.isAutoCollectEnabled() && hAAutoConfigOptions.isVisualConfigEnabled()) {
            s0.a.f3949a.a(context, hAAutoConfigOptions);
            s0 s0Var = s0.a.f3949a;
            List<String> list = this.i;
            List<String> list2 = s0Var.c;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            for (String str : s0Var.c) {
                if (!list.contains(str)) {
                    list.add(str);
                }
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean c(String str) {
        if (str == null || this.i.isEmpty()) {
            return false;
        }
        Iterator<String> it = this.i.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean b() {
        if (this.d.isAutoCollectEnabled()) {
            return false;
        }
        return !this.b;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(List<Class<?>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (this.e == null) {
            this.e = new CopyOnWriteArraySet();
        }
        for (Class<?> cls : list) {
            if (cls != null) {
                this.e.remove(Integer.valueOf(cls.hashCode()));
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(View view, String str) {
        if (view == null || TextUtils.isEmpty(str)) {
            return;
        }
        view.setTag(R.id.hianalytics_view_id_tag, str);
    }

    public final Context a(Context context) {
        return context instanceof Application ? context : context.getApplicationContext();
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(Class<?> cls) {
        if (cls == null) {
            return;
        }
        if (this.h == null) {
            this.h = new CopyOnWriteArraySet();
        }
        this.h.add(cls);
    }

    public final void a(Context context, HAAutoConfigOptions hAAutoConfigOptions) {
        if (hAAutoConfigOptions.isAutoCollectEnabled() && hAAutoConfigOptions.isWebViewBridgeEnabled()) {
            com.huawei.hianalytics.visual.a.a(context, hAAutoConfigOptions);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.huawei.hianalytics.visual.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(android.view.View r5) {
        /*
            r4 = this;
            com.huawei.hianalytics.visual.i r0 = com.huawei.hianalytics.visual.i.a()
            r0.getClass()
            com.huawei.hianalytics.visual.c r1 = com.huawei.hianalytics.visual.b.a()
            boolean r1 = r1.b()
            if (r1 == 0) goto L12
            goto L67
        L12:
            com.huawei.hianalytics.visual.autocollect.EventType r1 = com.huawei.hianalytics.visual.autocollect.EventType.VIEW_EXPOSURE
            com.huawei.hianalytics.visual.c r2 = com.huawei.hianalytics.visual.b.a()
            boolean r1 = r2.a(r1)
            if (r1 == 0) goto L1f
            goto L67
        L1f:
            if (r5 == 0) goto L60
            java.util.WeakHashMap<android.app.Activity, com.huawei.hianalytics.visual.i$a> r1 = r0.b
            if (r1 != 0) goto L26
            goto L60
        L26:
            android.content.Context r1 = r5.getContext()
            android.app.Activity r1 = com.huawei.hianalytics.visual.h0.a(r1, r5)
            if (r1 == 0) goto L39
            java.util.WeakHashMap<android.app.Activity, com.huawei.hianalytics.visual.i$a> r2 = r0.b
            java.lang.Object r1 = r2.get(r1)
            com.huawei.hianalytics.visual.i$a r1 = (com.huawei.hianalytics.visual.i.a) r1
            goto L61
        L39:
            java.util.WeakHashMap<android.app.Activity, com.huawei.hianalytics.visual.i$a> r1 = r0.b     // Catch: java.util.ConcurrentModificationException -> L59
            java.util.Collection r1 = r1.values()     // Catch: java.util.ConcurrentModificationException -> L59
            java.util.Iterator r1 = r1.iterator()     // Catch: java.util.ConcurrentModificationException -> L59
        L43:
            boolean r2 = r1.hasNext()     // Catch: java.util.ConcurrentModificationException -> L59
            if (r2 == 0) goto L60
            java.lang.Object r2 = r1.next()     // Catch: java.util.ConcurrentModificationException -> L59
            com.huawei.hianalytics.visual.i$a r2 = (com.huawei.hianalytics.visual.i.a) r2     // Catch: java.util.ConcurrentModificationException -> L59
            java.util.WeakHashMap<android.view.View, com.huawei.hianalytics.visual.i$d> r3 = r2.c     // Catch: java.util.ConcurrentModificationException -> L59
            boolean r3 = r3.containsKey(r5)     // Catch: java.util.ConcurrentModificationException -> L59
            if (r3 == 0) goto L43
            r1 = r2
            goto L61
        L59:
            java.lang.String r1 = "HAEO"
            java.lang.String r2 = "concurrent modification"
            com.huawei.hianalytics.core.log.HiLog.d(r1, r2)
        L60:
            r1 = 0
        L61:
            if (r1 != 0) goto L64
            goto L67
        L64:
            r0.a(r1, r5)
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.d.b(android.view.View):void");
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(boolean z) {
        this.d.setAutoCollectEnabled(z);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(Class<?> cls) {
        if (cls == null) {
            return;
        }
        if (this.e == null) {
            this.e = new CopyOnWriteArraySet();
        }
        this.e.add(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(List<Class<?>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (this.f == null) {
            this.f = new CopyOnWriteArraySet();
        }
        for (Class<?> cls : list) {
            if (cls != null) {
                this.f.add(Integer.valueOf(cls.hashCode()));
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean a(EventType eventType) {
        if (eventType == null) {
            return false;
        }
        return !this.d.getAutoCollectEventTypes().contains(eventType);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(Dialog dialog, String str) {
        if (dialog == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Window window = dialog.getWindow();
            if (window == null || window.getDecorView() == null) {
                return;
            }
            window.getDecorView().setTag(R.id.hianalytics_view_id_tag, str);
        } catch (Exception unused) {
            HiLog.w("HAAgentImpl", "fail to set view id for dialog");
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(String str) {
        a(str, (List<String>) null);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(Object obj, String str) {
        Class<?> a2 = n0.a();
        if (a2 == null || obj == null || TextUtils.isEmpty(str) || !a2.isInstance(obj)) {
            return;
        }
        try {
            Object invoke = obj.getClass().getMethod("getWindow", new Class[0]).invoke(obj, new Object[0]);
            if (invoke == null) {
                return;
            }
            Window window = (Window) invoke;
            if (window.getDecorView() != null) {
                window.getDecorView().setTag(R.id.hianalytics_view_id_tag, str);
            }
        } catch (Exception unused) {
            HiLog.sw("HAAgentImpl", "fail to set view id for alertDialog");
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view, Activity activity) {
        if (view == null || activity == null) {
            return;
        }
        view.setTag(R.id.hianalytics_activity_view_user_tag, activity);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view, String str) {
        if (view == null || TextUtils.isEmpty(str)) {
            return;
        }
        view.setTag(R.id.hianalytics_fragment_view_user_tag, str);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view) {
        if (view == null) {
            return;
        }
        view.setTag(R.id.hianalytics_view_disabled_user_tag, "1");
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view, JSONObject jSONObject) {
        if (view == null || jSONObject == null) {
            return;
        }
        view.setTag(R.id.hianalytics_view_custom_property_tag, jSONObject);
    }

    @Override // com.huawei.hianalytics.visual.c
    public List<Class<?>> a() {
        if (this.h == null) {
            this.h = new CopyOnWriteArraySet();
        }
        return new ArrayList(this.h);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(JSONObject jSONObject) {
        if (this.j == null) {
            this.j = new JSONObject();
        }
        n0.a(jSONObject, this.j);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str) {
        JSONObject jSONObject = this.j;
        if (jSONObject == null) {
            return;
        }
        jSONObject.remove(str);
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str, JSONObject jSONObject) {
        m0.a().execute(new a(jSONObject, null, str));
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str, JSONObject jSONObject, u0 u0Var) {
        m0.a().execute(new a(jSONObject, u0Var, str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00da, code lost:
    
        if (r5.equals(r8) != false) goto L40;
     */
    @Override // com.huawei.hianalytics.visual.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.huawei.hianalytics.visual.autocollect.exposure.ViewMark r12) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.d.a(com.huawei.hianalytics.visual.autocollect.exposure.ViewMark):void");
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str, List<String> list) {
        if (!TextUtils.isEmpty(str) && str.startsWith(SampleEvent.SEPARATOR)) {
            Set<String> set = this.m.get(str);
            if (set == null) {
                set = new HashSet<>();
            }
            if (list != null && list.size() > 0) {
                set.addAll(list);
            }
            this.m.put(str, set);
        }
    }
}
