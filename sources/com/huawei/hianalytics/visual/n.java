package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import java.util.WeakHashMap;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class n implements p {

    /* renamed from: a, reason: collision with root package name */
    public final WeakHashMap<Integer, Object> f3939a = new WeakHashMap<>();
    public String b;

    @Override // com.huawei.hianalytics.visual.p
    public void a(Object obj, View view, Bundle bundle) {
        if (obj == null) {
            return;
        }
        try {
            String name = obj.getClass().getName();
            view.setTag(R.id.hianalytics_fragment_view_sdk_tag, name);
            if (view instanceof ViewGroup) {
                a(name, (ViewGroup) view);
            }
            Activity a2 = h0.a(view.getContext(), view);
            if (a2 == null) {
                return;
            }
            if (a2.getWindow() != null) {
                if (a2.getWindow().getDecorView() == null) {
                    return;
                } else {
                    a2.getWindow().getDecorView().getRootView().setTag(R.id.hianalytics_fragment_view_sdk_tag, name);
                }
            }
            f0.a(name, obj);
        } catch (Exception unused) {
            HiLog.w("HAFPEnter", "fail to set fragment name for view");
        }
    }

    @Override // com.huawei.hianalytics.visual.p
    public void b(Object obj) {
        if (obj == null) {
            return;
        }
        this.f3939a.remove(Integer.valueOf(obj.hashCode()));
    }

    @Override // com.huawei.hianalytics.visual.p
    public void b(Object obj, boolean z) {
        if (obj == null) {
            return;
        }
        if (z) {
            this.f3939a.remove(Integer.valueOf(obj.hashCode()));
        } else {
            a(obj, "onHiddenChanged");
        }
    }

    @Override // com.huawei.hianalytics.visual.p
    public void a(Object obj) {
        a(obj, "onResume");
    }

    @Override // com.huawei.hianalytics.visual.p
    public void a(Object obj, boolean z) {
        if (obj == null) {
            return;
        }
        if (!z) {
            this.f3939a.remove(Integer.valueOf(obj.hashCode()));
        } else {
            a(obj, "setUserVisibleHint");
        }
    }

    public final void a(String str, ViewGroup viewGroup) {
        if (str.isEmpty()) {
            return;
        }
        try {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                childAt.setTag(R.id.hianalytics_fragment_view_sdk_tag, str);
                if ((childAt instanceof ViewGroup) && !(childAt instanceof ListView) && !(childAt instanceof GridView) && !(childAt instanceof Spinner) && !(childAt instanceof RadioGroup)) {
                    a(str, (ViewGroup) childAt);
                }
            }
        } catch (Exception unused) {
            HiLog.w("HAFPEnter", "fail to add name to each view");
        }
    }

    public final void a(Object obj, String str) {
        if (obj == null) {
            return;
        }
        if (b.a().a(EventType.PAGE_ENTER)) {
            return;
        }
        if (b.a().h(obj.getClass()) || this.f3939a.containsKey(Integer.valueOf(obj.hashCode())) || !f0.f(obj)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            n0.a(f0.a(obj, (Activity) null), jSONObject);
            u a2 = u.a();
            jSONObject.getString("$page_name");
            a2.getClass();
            try {
                obj.getClass().getMethod("getParentFragment", new Class[0]).invoke(obj, new Object[0]);
            } catch (Exception unused) {
                HiLog.w("ApplicationStateMonitor", "fail to find getParentFragment method");
            }
            String canonicalName = obj.getClass().getCanonicalName();
            jSONObject.put("$last_page", TextUtils.isEmpty(this.b) ? "" : this.b);
            jSONObject.put("$current_page", canonicalName);
            this.b = canonicalName;
            n0.a(obj, jSONObject);
            b.a().a("$AppViewScreen", jSONObject);
        } catch (Exception e) {
            HiLog.w("HAFPEnter", "fail to report enter fragment event " + str + ", ex: " + e.getMessage());
        }
        this.f3939a.put(Integer.valueOf(obj.hashCode()), obj);
    }
}
