package defpackage;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class kd {
    public static Method d;

    public static String a(Context context) {
        ke b = ke.b();
        return b.b(context.getApplicationContext(), b.f14312a);
    }

    public static final boolean a() {
        Context context = null;
        try {
            if (d == null) {
                Method method = Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]);
                d = method;
                method.setAccessible(true);
            }
            context = (Context) d.invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.e("OpenIdHelper", "ActivityThread:currentApplication --> " + e.toString());
        }
        if (context == null) {
            return false;
        }
        return ke.b().a(context, false);
    }
}
