package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.autocollect.exposure.ViewMark;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class e implements c {
    @Override // com.huawei.hianalytics.visual.c
    public List<Class<?>> a() {
        return new ArrayList();
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(Dialog dialog, String str) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view, Activity activity) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view, String str) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(View view, JSONObject jSONObject) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(Class<?> cls) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(Object obj, String str) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str, List<String> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str, JSONObject jSONObject) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(String str, JSONObject jSONObject, u0 u0Var) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(List<Class<?>> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(JSONObject jSONObject) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(boolean z) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean a(EventType eventType) {
        return true;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(View view) {
        Activity a2;
        if (view == null || (a2 = h0.a(view.getContext(), view)) == null) {
            return;
        }
        j jVar = j.b;
        synchronized (jVar) {
            List<ViewMark> list = jVar.f3929a.get(Integer.valueOf(a2.hashCode()));
            if (list != null) {
                for (ViewMark viewMark : list) {
                    if (viewMark != null && view == viewMark.getView()) {
                        list.remove(viewMark);
                        return;
                    }
                }
            }
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(View view, String str) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(Class<?> cls) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(String str) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void b(List<Class<?>> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean b() {
        return true;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void c(Class<?> cls) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void c(List<EventType> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean c() {
        return false;
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean c(String str) {
        return false;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void d() {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void d(Class<?> cls) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void d(List<Class<?>> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void e(Class<?> cls) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void e(List<String> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void f() {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void f(Class<?> cls) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void f(List<EventType> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public Context g() {
        return null;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void g(List<Class<?>> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean g(Class<?> cls) {
        return false;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void h() {
    }

    @Override // com.huawei.hianalytics.visual.c
    public void h(List<Class<?>> list) {
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean h(Class<?> cls) {
        return true;
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean i() {
        return false;
    }

    @Override // com.huawei.hianalytics.visual.c
    public boolean i(Class<?> cls) {
        return true;
    }

    @Override // com.huawei.hianalytics.visual.c
    public void a(ViewMark viewMark) {
        View view;
        Activity a2;
        if (viewMark == null || (view = viewMark.getView()) == null || (a2 = h0.a(view.getContext(), view)) == null) {
            return;
        }
        j jVar = j.b;
        synchronized (jVar) {
            int hashCode = a2.hashCode();
            List<ViewMark> list = jVar.f3929a.get(Integer.valueOf(hashCode));
            if (list == null) {
                list = new ArrayList<>();
                jVar.f3929a.put(Integer.valueOf(hashCode), list);
            }
            list.add(viewMark);
        }
    }

    @Override // com.huawei.hianalytics.visual.c
    public String e() {
        return "";
    }
}
