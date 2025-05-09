package com.huawei.hianalytics.visual.autocollect.instrument;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.b;
import com.huawei.hianalytics.visual.f0;
import com.huawei.hianalytics.visual.g;
import com.huawei.hianalytics.visual.h0;
import com.huawei.hianalytics.visual.n0;
import com.huawei.hianalytics.visual.o0;
import com.huawei.hianalytics.visual.u0;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ViewScrollInstrumentation {

    /* renamed from: a, reason: collision with root package name */
    public static long f3910a;

    public static boolean a(Object obj) {
        Class<?> cls;
        Class<?> cls2 = null;
        try {
            cls = Class.forName("android.widget.SearchView");
        } catch (Exception unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("androidx.appcompat.widget.SearchView");
        } catch (Exception unused2) {
        }
        if (cls != null) {
            try {
                if (cls.isInstance(obj)) {
                    return true;
                }
            } catch (Exception unused3) {
                HiLog.w("HAVSI", "fail to load class for searchView");
                return false;
            }
        }
        if (cls2 != null) {
            return cls2.isInstance(obj);
        }
        return false;
    }

    public static void focusChangeOnView(View view, boolean z) {
        g a2;
        Object a3;
        if (view == null) {
            return;
        }
        boolean z2 = view instanceof EditText;
        if (!z2 && !a(view)) {
            HiLog.d("HAVSI", "focusChangeOnView view is not EditText or SearchView");
            return;
        }
        if (o0.a() || o0.a(view, view.getClass()) || (a2 = o0.a((Object) null, view, view.getClass())) == null) {
            return;
        }
        if (z) {
            f3910a = SystemClock.elapsedRealtime();
        }
        if (z) {
            return;
        }
        if (z2 && TextUtils.isEmpty(((EditText) view).getText())) {
            return;
        }
        if (a(view) && ((a3 = n0.a(view, "getQuery", new Object[0])) == null || TextUtils.isEmpty(a3.toString()))) {
            return;
        }
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime() - f3910a;
            if (elapsedRealtime <= 0) {
                HiLog.d("HAVSI", "edit duration <= 0");
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$event_duration", elapsedRealtime);
            jSONObject.put("$view_id", o0.e(view));
            jSONObject.put("$event_name", o0.e(view));
            u0 d = o0.d(view);
            if (view instanceof EditText) {
                jSONObject.put("$view_type", "EditText");
            } else {
                jSONObject.put("$view_type", "SearchView");
            }
            jSONObject.put("$view_content", o0.a(d.d));
            n0.a(h0.b(a2.f3918a), jSONObject);
            n0.a(f0.a(a2.b, a2.f3918a), jSONObject);
            JSONObject jSONObject2 = (JSONObject) view.getTag(R.id.hianalytics_view_custom_property_tag);
            if (jSONObject2 != null) {
                jSONObject.put("$custom_property", jSONObject2);
            }
            b.a().a("$ViewClick", jSONObject, o0.a(a2.f3918a, view, jSONObject));
        } catch (Exception unused) {
            HiLog.w("HAVSI", "fail to report focus change event");
        }
    }

    public static void scrollChangeOnView(View view, int i, int i2, int i3, int i4) {
        if (view == null || o0.a() || o0.a(view, view.getClass()) || o0.a((Object) null, view, view.getClass()) == null || i2 <= i4) {
            return;
        }
        view.setTag(R.id.hianalytics_scroll_view_tag, Integer.valueOf(i2));
    }
}
