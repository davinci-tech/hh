package com.huawei.openalliance.ad.utils;

/* loaded from: classes5.dex */
public class dm {
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
    
        if (r2 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0046, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
    
        if (0 == 0) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r10) {
        /*
            java.lang.String r0 = "VIdentifierManager"
            java.lang.String r1 = ""
            r2 = 0
            java.lang.String r3 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
            android.net.Uri r5 = android.net.Uri.parse(r3)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            boolean r3 = com.huawei.openalliance.ad.utils.ao.b(r10, r5)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            if (r3 != 0) goto L17
            java.lang.String r10 = "invalid provider"
            android.util.Log.w(r0, r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            return r1
        L17:
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            if (r2 == 0) goto L37
            boolean r10 = r2.moveToNext()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            if (r10 == 0) goto L37
            java.lang.String r10 = "value"
            int r10 = r2.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            java.lang.String r10 = r2.getString(r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            r1 = r10
        L37:
            if (r2 == 0) goto L46
            goto L43
        L3a:
            r10 = move-exception
            goto L47
        L3c:
            java.lang.String r10 = "contentResolver query exception"
            com.huawei.openalliance.ad.ho.c(r0, r10)     // Catch: java.lang.Throwable -> L3a
            if (r2 == 0) goto L46
        L43:
            r2.close()
        L46:
            return r1
        L47:
            if (r2 == 0) goto L4c
            r2.close()
        L4c:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.dm.a(android.content.Context):java.lang.String");
    }
}
