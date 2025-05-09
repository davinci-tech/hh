package com.huawei.agconnect.credential.obs;

import android.content.Context;
import com.huawei.agconnect.AGConnectInstance;

/* loaded from: classes2.dex */
public class al {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1751a = "4D5480E9";
    private static final al b = new al();
    private Context c;

    public Context b() {
        if (this.c == null) {
            this.c = AGConnectInstance.getInstance().getContext();
        }
        return this.c;
    }

    public static String d() {
        return "80fedfd8941a368fafdae46750a4d367";
    }

    public static String c() {
        return "1C4DE4EC";
    }

    public static void a(Context context) {
        synchronized (al.class) {
            Context applicationContext = context.getApplicationContext();
            al alVar = b;
            if (applicationContext != null) {
                context = applicationContext;
            }
            alVar.c = context;
        }
    }

    public static al a() {
        return b;
    }

    private al() {
    }
}
