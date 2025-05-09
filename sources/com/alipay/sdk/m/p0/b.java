package com.alipay.sdk.m.p0;

import android.content.Context;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public Context f867a;

    public b(Context context) {
        this.f867a = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String b(int r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 == 0) goto L3b
            r1 = 1
            if (r8 == r1) goto L28
            r1 = 2
            if (r8 == r1) goto L15
            r9 = 4
            if (r8 == r9) goto Le
            r2 = r0
            goto L42
        Le:
            java.lang.String r8 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS"
            android.net.Uri r8 = android.net.Uri.parse(r8)
            goto L41
        L15:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_"
            r8.<init>(r1)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.net.Uri r8 = android.net.Uri.parse(r8)
            goto L41
        L28:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_"
            r8.<init>(r1)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.net.Uri r8 = android.net.Uri.parse(r8)
            goto L41
        L3b:
            java.lang.String r8 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
            android.net.Uri r8 = android.net.Uri.parse(r8)
        L41:
            r2 = r8
        L42:
            android.content.Context r8 = r7.f867a
            android.content.ContentResolver r1 = r8.getContentResolver()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            if (r8 == 0) goto L68
            boolean r9 = r8.moveToNext()
            if (r9 == 0) goto L64
            java.lang.String r9 = "value"
            int r9 = r8.getColumnIndex(r9)
            java.lang.String r9 = r8.getString(r9)
            r0 = r9
        L64:
            r8.close()
            goto L6f
        L68:
            java.lang.String r8 = "VMS_IDLG_SDK_DB"
            java.lang.String r9 = "return cursor is null,return"
            android.util.Log.d(r8, r9)
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.m.p0.b.b(int, java.lang.String):java.lang.String");
    }
}
