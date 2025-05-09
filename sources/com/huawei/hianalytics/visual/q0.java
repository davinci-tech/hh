package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class q0 {
    public static volatile q0 c;

    /* renamed from: a, reason: collision with root package name */
    public Map<String, u0> f3946a = new HashMap();
    public SparseArray<u0> b = new SparseArray<>();

    public static q0 a() {
        if (c == null) {
            synchronized (q0.class) {
                if (c == null) {
                    c = new q0();
                }
            }
        }
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public u0 a(View view, String str, String str2, String str3) {
        View view2;
        View view3 = null;
        try {
            u0 u0Var = this.f3946a.get(a(str, str2, str3));
            if (u0Var != 0) {
                return u0Var;
            }
            if (view != null) {
                try {
                    view3 = view.getRootView();
                } catch (Exception e) {
                    e = e;
                    view3 = u0Var;
                    view2 = view3;
                    HiLog.w("HAViewNodeService", "fail to get view node from visual, ex: " + e.getMessage());
                    return view2;
                }
            }
            if (view3 == null) {
                u.a().getClass();
                try {
                    Activity b = v.c.b();
                    if (b != null && b.getWindow() != null && b.getWindow().isActive()) {
                        view3 = b.getWindow().getDecorView();
                    }
                } catch (Exception e2) {
                    e = e2;
                    view2 = u0Var;
                    HiLog.w("HAViewNodeService", "fail to get view node from visual, ex: " + e.getMessage());
                    return view2;
                }
            }
            if (view3 != null) {
                a(view3);
            }
            return this.f3946a.get(a(str, str2, str3));
        } catch (Exception e3) {
            e = e3;
        }
    }

    public final void a(View view) {
        this.f3946a.clear();
        this.b.clear();
        SparseArray<u0> sparseArray = new SparseArray<>();
        HashMap hashMap = new HashMap();
        try {
            if (view != null) {
                a(view, sparseArray, hashMap);
            } else {
                for (View view2 : p0.b()) {
                    a(view2, sparseArray, hashMap);
                }
            }
            this.f3946a = hashMap;
            this.b = sparseArray;
        } catch (Exception unused) {
            HiLog.w("HAViewNodeService", "fail to traverse view node");
        }
    }

    public final void a(View view, SparseArray<u0> sparseArray, Map<String, u0> map) {
        if (view == null) {
            return;
        }
        u0 a2 = a(view, true);
        if (a2 != null) {
            sparseArray.put(view.hashCode(), a2);
            if (!TextUtils.isEmpty(a2.e)) {
                JSONObject jSONObject = new JSONObject();
                Activity a3 = h0.a(view.getContext(), view);
                if (a3 == null) {
                    u.a().getClass();
                    a3 = v.c.b();
                }
                if (a3 != null && a3.getWindow() != null && a3.getWindow().isActive()) {
                    Object a4 = f0.a(view, a3);
                    jSONObject = a4 == null ? h0.b(a3) : f0.a(a4, a3);
                }
                String optString = jSONObject.optString("$page_name");
                if (!TextUtils.isEmpty(optString)) {
                    map.put(a(a2.e, a2.g, optString), a2);
                }
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    a(childAt, sparseArray, map);
                }
            }
        }
    }

    public final String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(str2);
        }
        sb.append(str3);
        return sb.toString();
    }

    public final u0 a(View view, boolean z) {
        u0 f;
        String str;
        u0 a2;
        int lastIndexOf;
        u0 u0Var;
        if (z && (u0Var = this.b.get(view.hashCode())) != null) {
            return u0Var;
        }
        Object parent = view.getParent();
        View view2 = parent instanceof ViewGroup ? (View) parent : null;
        if (view2 == null) {
            a2 = o0.f(view);
        } else {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            if (z) {
                f = this.b.get(view2.hashCode());
                if (f == null) {
                    f = o0.f(view2);
                    this.b.put(view2.hashCode(), f);
                }
            } else {
                f = o0.f(view2);
            }
            if (f != null) {
                sb.append(f.f);
                sb2.append(f.e);
                str = f.g;
            } else {
                str = "";
            }
            a2 = o0.a(view, ((ViewGroup) view2).indexOfChild(view));
            if (a2 != null) {
                if (!TextUtils.isEmpty(a2.e) && a2.e.contains(Constants.LINK) && !TextUtils.isEmpty(str) && (lastIndexOf = sb2.lastIndexOf(Constants.LINK)) != -1) {
                    sb2.replace(lastIndexOf, lastIndexOf + 1, str);
                }
                sb.append(a2.f);
                sb2.append(a2.e);
                a2 = new u0(view, a2.b, a2.c, o0.a(a2.d), sb2.toString(), sb.toString(), a2.g, a2.h, a2.i);
            }
        }
        if (z) {
            this.b.put(view.hashCode(), a2);
        }
        return a2;
    }
}
