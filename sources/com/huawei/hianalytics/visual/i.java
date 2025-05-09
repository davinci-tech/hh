package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.autocollect.exposure.ViewMark;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class i implements s, x {
    public static volatile i f;

    /* renamed from: a, reason: collision with root package name */
    public Set<String> f3922a;
    public WeakHashMap<Activity, a> b;
    public c c;
    public Rect d;
    public final Handler e;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public d f3923a;
        public Map<String, b> b;
        public WeakHashMap<View, d> c;
        public WeakHashMap<b, String> d;

        public a(Activity activity, Handler handler) {
            new WeakReference(activity);
            this.f3923a = new d(handler);
            this.b = new HashMap();
            this.c = new WeakHashMap<>();
            this.d = new WeakHashMap<>();
        }

        public b a(View view) {
            WeakHashMap<View, b> weakHashMap;
            d dVar = this.c.get(view);
            if (dVar == null || (weakHashMap = dVar.b) == null) {
                return null;
            }
            return weakHashMap.get(view);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public WeakReference<Activity> f3924a;
        public ViewMark b;
        public boolean c;
    }

    public static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final Handler f3925a;
        public final Runnable b;
        public long c = -1;

        public c(Runnable runnable, Handler handler) {
            this.f3925a = handler;
            this.b = runnable;
        }

        public void a() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long j = this.c;
            if (j > 0 && uptimeMillis - j >= 5000) {
                this.f3925a.removeCallbacks(this);
                this.c = 0L;
                this.b.run();
            } else {
                if (j <= 0) {
                    this.c = uptimeMillis;
                }
                this.f3925a.removeCallbacks(this);
                this.f3925a.postAtTime(this, Math.min(this.c + 5000, uptimeMillis + 500));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3925a.removeCallbacks(this);
            this.c = 0L;
            this.b.run();
        }
    }

    public static class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public c f3926a;
        public WeakHashMap<View, b> b = new WeakHashMap<>();
        public i c = i.a();

        public d(Handler handler) {
            this.f3926a = new c(this, handler);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakHashMap<Activity, a> weakHashMap;
            a aVar;
            i iVar = this.c;
            WeakHashMap<View, b> weakHashMap2 = this.b;
            iVar.getClass();
            u.a().getClass();
            Activity c = v.c.c();
            if (c == null || (weakHashMap = iVar.b) == null || !weakHashMap.containsKey(c) || (aVar = iVar.b.get(c)) == null) {
                return;
            }
            if (iVar.f3922a == null) {
                iVar.f3922a = new HashSet();
            }
            iVar.f3922a.clear();
            try {
                for (String str : aVar.b.keySet()) {
                    b bVar = aVar.b.get(str);
                    if (bVar != null) {
                        View view = bVar.b.getView();
                        if (view == null) {
                            iVar.f3922a.add(str);
                        } else if (aVar.a(view) != bVar) {
                            iVar.f3922a.add(str);
                        }
                    }
                }
            } catch (ConcurrentModificationException unused) {
                HiLog.d("HAEO", "concurrent modification");
            }
            Iterator<String> it = iVar.f3922a.iterator();
            while (it.hasNext()) {
                aVar.b.remove(it.next());
            }
            c cVar = iVar.c;
            if (cVar == null) {
                return;
            }
            cVar.c = -1L;
            cVar.f3925a.removeCallbacks(cVar);
            ArrayList arrayList = new ArrayList();
            try {
                for (View view2 : weakHashMap2.keySet()) {
                    b bVar2 = weakHashMap2.get(view2);
                    if (bVar2 != null) {
                        boolean z = bVar2.c;
                        boolean a2 = iVar.a(bVar2.b);
                        if (bVar2.b.getView() != view2) {
                            arrayList.add(view2);
                            HiLog.d("HAEO", "maybe global id is changed");
                        } else {
                            if (a2 && !z) {
                                iVar.a(bVar2);
                            }
                            bVar2.c = a2;
                        }
                    }
                }
                for (b bVar3 : aVar.d.keySet()) {
                    if (!aVar.c.containsKey(bVar3.b.getView())) {
                        bVar3.c = false;
                    }
                }
            } catch (Exception unused2) {
                HiLog.w("HAEO", "fail to check view exposure");
            }
            aVar.d.clear();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                iVar.a(aVar, (View) it2.next());
            }
        }
    }

    public i() {
        HandlerThread handlerThread = new HandlerThread("HA-VISUAL-IMP");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper());
    }

    @Override // com.huawei.hianalytics.visual.x
    public void a(a0 a0Var) {
        c cVar;
        if (!a0Var.f3904a.equals("window_focus_changed") && !a0Var.f3904a.equals("view_layout_changed")) {
            if (!a0Var.f3904a.equals("view_draw_changed") || (cVar = this.c) == null) {
                return;
            }
            cVar.a();
            return;
        }
        u.a().getClass();
        Activity c2 = v.c.c();
        if (c2 == null) {
            return;
        }
        a(c2);
    }

    public final void b() {
        if (this.b != null) {
            return;
        }
        this.b = new WeakHashMap<>();
        this.c = new c(new Runnable() { // from class: com.huawei.hianalytics.visual.i$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                i.this.c();
            }
        }, this.e);
        h a2 = h.a();
        synchronized (a2.f3920a) {
            a2.f3920a.add(this);
        }
        y a3 = y.a();
        synchronized (a3.f3956a) {
            a3.f3956a.add(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        u.a().getClass();
        Activity c2 = v.c.c();
        if (c2 == null) {
            return;
        }
        a(c2);
    }

    @Override // com.huawei.hianalytics.visual.s
    public void a(z zVar) {
        if (zVar.b.equals("activity_on_resume")) {
            a aVar = this.b.get(zVar.a());
            if (aVar == null) {
                return;
            }
            try {
                Iterator<View> it = aVar.c.keySet().iterator();
                while (it.hasNext()) {
                    b a2 = aVar.a(it.next());
                    if (a2 != null) {
                        a2.c = false;
                    }
                }
            } catch (ConcurrentModificationException unused) {
                HiLog.d("HAEO", "concurrent modification view timer");
            }
            a(zVar.a());
            return;
        }
        if (zVar.b.equals("activity_on_destroyed")) {
            this.b.remove(zVar.a());
            if (this.b.isEmpty()) {
                c cVar = this.c;
                if (cVar != null) {
                    cVar.c = -1L;
                    cVar.f3925a.removeCallbacks(cVar);
                }
                h a3 = h.a();
                synchronized (a3.f3920a) {
                    a3.f3920a.remove(this);
                }
                y a4 = y.a();
                synchronized (a4.f3956a) {
                    a4.f3956a.remove(this);
                }
            }
        }
    }

    public static i a() {
        if (f == null) {
            synchronized (i.class) {
                if (f == null) {
                    f = new i();
                }
            }
        }
        return f;
    }

    public final void a(a aVar, View view) {
        WeakHashMap<View, b> weakHashMap;
        view.setTag(R.id.hianalytics_exposure_view_tag, Boolean.FALSE);
        d dVar = aVar.c.get(view);
        if (dVar == null || (weakHashMap = dVar.b) == null) {
            return;
        }
        weakHashMap.remove(view);
        aVar.c.remove(view);
    }

    public final void a(Activity activity) {
        a aVar;
        c cVar;
        WeakHashMap<Activity, a> weakHashMap = this.b;
        if (weakHashMap == null || (aVar = weakHashMap.get(activity)) == null || (cVar = aVar.f3923a.f3926a) == null) {
            return;
        }
        cVar.a();
    }

    public final boolean a(ViewMark viewMark) {
        View view = viewMark.getView();
        if (o0.i(view)) {
            if (viewMark.getVisibleScale() == 0.0f) {
                return true;
            }
            if (this.d == null) {
                this.d = new Rect();
            }
            view.getLocalVisibleRect(this.d);
            Rect rect = this.d;
            int abs = Math.abs(rect.right - rect.left);
            Rect rect2 = this.d;
            if (abs * Math.abs(rect2.top - rect2.bottom) >= view.getMeasuredHeight() * view.getMeasuredWidth() * viewMark.getVisibleScale()) {
                return true;
            }
        }
        return false;
    }

    public final void a(b bVar) {
        Context context;
        View view = bVar.b.getView();
        JSONObject eventAttributes = bVar.b.getEventAttributes();
        String eventId = bVar.b.getEventId();
        if (com.huawei.hianalytics.visual.b.a().b()) {
            return;
        }
        if (com.huawei.hianalytics.visual.b.a().a(EventType.VIEW_EXPOSURE) || (context = view.getContext()) == null) {
            return;
        }
        Activity a2 = h0.a(context, view);
        if (a2 != null) {
            if (com.huawei.hianalytics.visual.b.a().i(a2.getClass())) {
                return;
            }
        }
        Object a3 = f0.a(view, a2);
        if (a3 != null) {
            if (com.huawei.hianalytics.visual.b.a().h(a3.getClass())) {
                return;
            }
        }
        if (o0.g(view)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$view_id", o0.e(view));
            jSONObject.put("$event_name", eventId);
            u0 d2 = o0.d(view);
            jSONObject.put("$view_type", d2.c);
            jSONObject.put("$view_content", o0.a(d2.d));
            n0.a(h0.b(a2), jSONObject);
            n0.a(f0.a(a3, a2), jSONObject);
            n0.a(eventAttributes, jSONObject);
            JSONObject jSONObject2 = (JSONObject) view.getTag(R.id.hianalytics_view_custom_property_tag);
            if (jSONObject2 != null) {
                jSONObject.put("$custom_property", jSONObject2);
            }
            com.huawei.hianalytics.visual.b.a().a("$ViewExposure", jSONObject);
        } catch (Exception unused) {
            HiLog.w("HAEO", "fail to report view exposure event");
        }
    }
}
