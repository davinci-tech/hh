package com.huawei.hianalytics.visual;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* loaded from: classes4.dex */
public class d0 {

    /* renamed from: a, reason: collision with root package name */
    public static Future<SharedPreferences> f3915a;

    public static class a implements Callable<SharedPreferences> {

        /* renamed from: a, reason: collision with root package name */
        public final Context f3916a;

        public a(Context context) {
            this.f3916a = context;
        }

        @Override // java.util.concurrent.Callable
        public SharedPreferences call() throws Exception {
            Context context = this.f3916a;
            if (context == null) {
                return null;
            }
            return context.getSharedPreferences("com.huawei.hianalytics.v1_" + context.getPackageName(), 0);
        }
    }

    public static void a(Context context) {
        if (f3915a != null) {
            return;
        }
        FutureTask futureTask = new FutureTask(new a(context));
        m0.a().execute(futureTask);
        f3915a = futureTask;
    }

    public static c0 a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        if (str.equals("app_install")) {
            return new b0(f3915a);
        }
        if (str.equals("visual_config")) {
            return new e0(f3915a);
        }
        return null;
    }
}
