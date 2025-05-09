package com.huawei.agconnect.apms;

import android.os.Looper;
import android.text.TextUtils;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class xyz {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static final ConcurrentHashMap<String, String> bcd = new ConcurrentHashMap<>();

    public static void abc(wxy wxyVar, String str, String str2, long j) {
        wxyVar.def(str);
        if (!wxyVar.c()) {
            wxyVar.def = str2;
        }
        if (wxyVar.edc()) {
            return;
        }
        wxyVar.qrs = j;
        wxyVar.abc = 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
    
        if (android.text.TextUtils.isEmpty(r0) != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void bcd(com.huawei.agconnect.apms.wxy r11, java.net.HttpURLConnection r12) {
        /*
            java.lang.String r0 = "x-nuwa-sample-state"
            int r1 = r12.getContentLength()
            java.lang.String r2 = ""
            int r3 = r12.getResponseCode()     // Catch: java.lang.Throwable -> L5b
            com.huawei.agconnect.apms.cde r4 = com.huawei.agconnect.apms.Agent.getAgentConfiguration()     // Catch: java.lang.Throwable -> L59
            java.lang.String r4 = r4.def     // Catch: java.lang.Throwable -> L59
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L59
            if (r5 == 0) goto L1a
            java.lang.String r4 = "dl-from"
        L1a:
            java.lang.String r2 = abc(r12, r4)     // Catch: java.lang.Throwable -> L59
            java.lang.String r4 = "_apms_result_code"
            java.lang.String r4 = abc(r12, r4)     // Catch: java.lang.Throwable -> L59
            r11.gfe = r4     // Catch: java.lang.Throwable -> L59
            boolean r4 = com.huawei.agconnect.apms.Agent.isNuwaTracerEnable()     // Catch: java.lang.Throwable -> L59
            if (r4 == 0) goto L74
            java.lang.String r4 = "network-in"
            java.lang.String r4 = abc(r12, r4)     // Catch: java.lang.Throwable -> L59
            r11.dcb = r4     // Catch: java.lang.Throwable -> L59
            java.lang.String r4 = "network-out"
            java.lang.String r4 = abc(r12, r4)     // Catch: java.lang.Throwable -> L59
            r11.cba = r4     // Catch: java.lang.Throwable -> L59
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L59
            if (r4 != 0) goto L4c
            java.lang.String r0 = r12.getHeaderField(r0)     // Catch: java.lang.Throwable -> L59
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L59
            if (r4 == 0) goto L4e
        L4c:
            java.lang.String r0 = "0"
        L4e:
            r11.f1698a = r0     // Catch: java.lang.Throwable -> L59
            java.lang.String r0 = "x-nuwa-call-seq"
            java.lang.String r0 = abc(r12, r0)     // Catch: java.lang.Throwable -> L59
            r11.b = r0     // Catch: java.lang.Throwable -> L59
            goto L74
        L59:
            r0 = move-exception
            goto L5d
        L5b:
            r0 = move-exception
            r3 = 0
        L5d:
            com.huawei.agconnect.apms.log.AgentLog r4 = com.huawei.agconnect.apms.xyz.abc
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "failed to get response info: "
            r5.<init>(r6)
            java.lang.String r0 = r0.getMessage()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r4.warn(r0)
        L74:
            r10 = r2
            r8 = r3
            java.lang.String r9 = r12.getContentType()
            long r6 = (long) r1
            r5 = r11
            abc(r5, r6, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.xyz.bcd(com.huawei.agconnect.apms.wxy, java.net.HttpURLConnection):void");
    }

    public static void abc(wxy wxyVar, HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null || httpURLConnection.getURL() == null) {
            return;
        }
        String url = httpURLConnection.getURL().toString();
        String requestMethod = httpURLConnection.getRequestMethod();
        wxyVar.def(url);
        if (wxyVar.c()) {
            return;
        }
        wxyVar.def = requestMethod;
    }

    public static String abc(HttpURLConnection httpURLConnection, String str) {
        if (!TextUtils.isEmpty(str)) {
            String headerField = httpURLConnection.getHeaderField(str);
            if (!TextUtils.isEmpty(headerField)) {
                return headerField;
            }
        }
        return "";
    }

    public static void abc(wxy wxyVar, Exception exc) {
        String exc2 = exc.toString();
        abc.debug("catching http error: " + exc2);
        wxyVar.abc(exc2);
    }

    public static void abc(String str, wxy wxyVar) {
        if (TextUtils.isEmpty(str) || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            InetAddress[] allByName = InetAddress.getAllByName(str);
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            if (currentTimeMillis2 >= 8) {
                nml nmlVar = new nml(str, 0L);
                wxyVar.abc(nmlVar);
                wxyVar.fed();
                nmlVar.cde = currentTimeMillis2;
                nmlVar.def = Arrays.asList(allByName).toString();
                nmlVar.efg = true;
            }
        } catch (Throwable th) {
            abc.error("attempt to resolve dns failed: " + th.getMessage());
        }
    }

    public static int abc(wxy wxyVar) {
        long currentTimeMillis = System.currentTimeMillis() - wxyVar.xyz;
        if (currentTimeMillis <= 0 || currentTimeMillis >= 2147483647L) {
            return -1;
        }
        return (int) currentTimeMillis;
    }

    public static void abc(wxy wxyVar, long j, int i, String str, String str2) {
        if (j >= 0 && !wxyVar.edc() && j >= 0) {
            wxyVar.fgh = j;
        }
        if (!wxyVar.edc()) {
            wxyVar.jkl = i;
        }
        wxyVar.efg = str;
        wxyVar.cde = str2;
    }
}
