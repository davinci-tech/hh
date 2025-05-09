package com.amap.api.col.p0003sl;

import android.content.Context;

/* loaded from: classes2.dex */
public final class kg {

    /* renamed from: a, reason: collision with root package name */
    private Context f1254a;
    private hz b;
    private String c;

    public kg(Context context, hz hzVar, String str) {
        this.f1254a = context.getApplicationContext();
        this.b = hzVar;
        this.c = str;
    }

    final byte[] a() {
        return ia.a(a(this.f1254a, this.b, this.c));
    }

    private static String a(Context context, hz hzVar, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(hzVar.c());
            sb.append("\",\"product\":\"");
            sb.append(hzVar.a());
            sb.append("\",\"nt\":\"");
            sb.append(hr.d(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }
}
