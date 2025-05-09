package com.huawei.openalliance.ad;

import android.content.Context;
import android.net.Uri;

/* loaded from: classes5.dex */
public class mr {

    /* renamed from: a, reason: collision with root package name */
    private static mr f7272a;
    private static final byte[] b = new byte[0];
    private static final Uri c = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/pps/api/call").build();
    private Context d;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> com.huawei.openalliance.ad.ipc.CallResult<T> a(java.lang.String r18, java.lang.String r19, java.lang.Class<T> r20) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.mr.a(java.lang.String, java.lang.String, java.lang.Class):com.huawei.openalliance.ad.ipc.CallResult");
    }

    public static mr a(Context context) {
        mr mrVar;
        synchronized (b) {
            if (f7272a == null) {
                f7272a = new mr(context);
            }
            mrVar = f7272a;
        }
        return mrVar;
    }

    private mr(Context context) {
        this.d = context.getApplicationContext();
    }
}
