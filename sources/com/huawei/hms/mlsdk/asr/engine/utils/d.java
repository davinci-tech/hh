package com.huawei.hms.mlsdk.asr.engine.utils;

/* loaded from: classes4.dex */
public class d {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00cd, code lost:
    
        if (r4.isNetworkRoaming() == false) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a() {
        /*
            java.lang.String r0 = "NetworkHelper"
            com.huawei.hms.mlsdk.common.MLApplication r1 = com.huawei.hms.mlsdk.common.MLApplication.getInstance()
            android.content.Context r1 = r1.getAppContext()
            java.lang.String r2 = "connectivity"
            java.lang.Object r3 = r1.getSystemService(r2)
            android.net.ConnectivityManager r3 = (android.net.ConnectivityManager) r3
            java.lang.String r4 = "phone"
            java.lang.Object r4 = r1.getSystemService(r4)
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4
            r5 = 4
            android.net.NetworkInfo r3 = r3.getActiveNetworkInfo()     // Catch: java.lang.Exception -> Ld4
            r6 = 1
            if (r3 == 0) goto Ld3
            boolean r7 = r3.isAvailable()     // Catch: java.lang.Exception -> Ld4
            if (r7 == 0) goto Ld3
            boolean r7 = r3.isConnected()     // Catch: java.lang.Exception -> Ld4
            if (r7 != 0) goto L30
            goto Ld3
        L30:
            int r7 = r3.getType()     // Catch: java.lang.Exception -> Ld4
            r8 = 3
            r9 = 2
            if (r7 != r6) goto L9c
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> Ld4
            r4 = 31
            if (r3 >= r4) goto L3f
            return r5
        L3f:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> Ld4
            if (r3 < r4) goto L73
            android.content.Context r1 = r1.getApplicationContext()     // Catch: java.lang.Exception -> Ld4
            java.lang.Object r1 = r1.getSystemService(r2)     // Catch: java.lang.Exception -> Ld4
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1     // Catch: java.lang.Exception -> Ld4
            android.net.Network[] r2 = r1.getAllNetworks()     // Catch: java.lang.Exception -> Ld4
            int r3 = r2.length     // Catch: java.lang.Exception -> Ld4
            r4 = 0
        L53:
            if (r4 >= r3) goto L73
            r7 = r2[r4]     // Catch: java.lang.Exception -> Ld4
            android.net.NetworkCapabilities r7 = r1.getNetworkCapabilities(r7)     // Catch: java.lang.Exception -> Ld4
            if (r7 == 0) goto L70
            boolean r10 = r7.hasTransport(r6)     // Catch: java.lang.Exception -> Ld4
            if (r10 == 0) goto L70
            android.net.TransportInfo r7 = r7.getTransportInfo()     // Catch: java.lang.Exception -> Ld4
            if (r7 == 0) goto L70
            boolean r10 = r7 instanceof android.net.wifi.WifiInfo     // Catch: java.lang.Exception -> Ld4
            if (r10 == 0) goto L70
            android.net.wifi.WifiInfo r7 = (android.net.wifi.WifiInfo) r7     // Catch: java.lang.Exception -> Ld4
            goto L74
        L70:
            int r4 = r4 + 1
            goto L53
        L73:
            r7 = 0
        L74:
            if (r7 != 0) goto L77
            return r6
        L77:
            int r1 = r7.getRssi()     // Catch: java.lang.Exception -> Ld4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld4
            r2.<init>()     // Catch: java.lang.Exception -> Ld4
            java.lang.String r3 = "wifi rssi ==="
            r2.append(r3)     // Catch: java.lang.Exception -> Ld4
            r2.append(r1)     // Catch: java.lang.Exception -> Ld4
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Ld4
            com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger.i(r0, r2)     // Catch: java.lang.Exception -> Ld4
            r0 = -75
            r2 = -89
            if (r1 >= r0) goto L98
            if (r1 < r2) goto L98
            return r8
        L98:
            if (r1 >= r2) goto L9b
            return r9
        L9b:
            return r5
        L9c:
            if (r7 != 0) goto Ld2
            int r1 = r3.getSubtype()     // Catch: java.lang.Exception -> Ld4
            switch(r1) {
                case 1: goto Ld1;
                case 2: goto Ld1;
                case 3: goto Laa;
                case 4: goto Ld1;
                case 5: goto Laa;
                case 6: goto Laa;
                case 7: goto Ld1;
                case 8: goto Laa;
                case 9: goto Laa;
                case 10: goto Laa;
                case 11: goto Ld1;
                case 12: goto Laa;
                case 13: goto La5;
                case 14: goto Laa;
                case 15: goto Laa;
                case 16: goto Ld1;
                case 17: goto Laa;
                default: goto La5;
            }     // Catch: java.lang.Exception -> Ld4
        La5:
            java.lang.String r1 = r3.getSubtypeName()     // Catch: java.lang.Exception -> Ld4
            goto Lb1
        Laa:
            boolean r0 = r4.isNetworkRoaming()     // Catch: java.lang.Exception -> Ld4
            if (r0 != 0) goto Ld1
            goto Lcf
        Lb1:
            java.lang.String r2 = "WCDMA"
            boolean r2 = r2.equalsIgnoreCase(r1)     // Catch: java.lang.Exception -> Ld4
            if (r2 != 0) goto Lc9
            java.lang.String r2 = "CDMA2000"
            boolean r2 = r2.equalsIgnoreCase(r1)     // Catch: java.lang.Exception -> Ld4
            if (r2 != 0) goto Lc9
            java.lang.String r2 = "TD-SCDMA"
            boolean r1 = r2.equalsIgnoreCase(r1)     // Catch: java.lang.Exception -> Ld4
            if (r1 == 0) goto Ld2
        Lc9:
            boolean r0 = r4.isNetworkRoaming()     // Catch: java.lang.Exception -> Ld4
            if (r0 != 0) goto Ld1
        Lcf:
            r7 = r8
            goto Ld2
        Ld1:
            r7 = r9
        Ld2:
            return r7
        Ld3:
            return r6
        Ld4:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger.e(r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.mlsdk.asr.engine.utils.d.a():int");
    }
}
