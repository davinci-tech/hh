package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.autocollect.exposure.ViewMark;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public interface c {
    List<Class<?>> a();

    void a(Dialog dialog, String str);

    void a(View view);

    void a(View view, Activity activity);

    void a(View view, String str);

    void a(View view, JSONObject jSONObject);

    void a(ViewMark viewMark);

    void a(Class<?> cls);

    void a(Object obj, String str);

    void a(String str);

    void a(String str, List<String> list);

    void a(String str, JSONObject jSONObject);

    void a(String str, JSONObject jSONObject, u0 u0Var);

    void a(List<Class<?>> list);

    void a(JSONObject jSONObject);

    void a(boolean z);

    boolean a(EventType eventType);

    void b(View view);

    void b(View view, String str);

    void b(Class<?> cls);

    void b(String str);

    void b(List<Class<?>> list);

    boolean b();

    void c(Class<?> cls);

    void c(List<EventType> list);

    boolean c();

    boolean c(String str);

    void d();

    void d(Class<?> cls);

    void d(List<Class<?>> list);

    String e();

    void e(Class<?> cls);

    void e(List<String> list);

    void f();

    void f(Class<?> cls);

    void f(List<EventType> list);

    Context g();

    void g(List<Class<?>> list);

    boolean g(Class<?> cls);

    void h();

    void h(List<Class<?>> list);

    boolean h(Class<?> cls);

    boolean i();

    boolean i(Class<?> cls);
}
