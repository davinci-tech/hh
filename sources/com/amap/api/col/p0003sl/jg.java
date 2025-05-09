package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class jg {

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        public static jg f1215a = new jg();
    }

    private static hz a(String str, List<hz> list) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (int i = 0; list != null && i < list.size(); i++) {
            hz hzVar = list.get(i);
            if (hzVar != null) {
                String[] f = hzVar.f();
                for (String str2 : f) {
                    if (!TextUtils.isEmpty(f[i]) && str.contains(str2)) {
                        return hzVar;
                    }
                }
            }
        }
        return null;
    }

    private static JSONArray a(String str) {
        if (str == null) {
            str = "";
        }
        String[] strArr = {"AMapPboRenderThread", "GLThread", "AMapGlRenderThread", "AMapThreadUtil", "GNaviMap", "main"};
        JSONArray jSONArray = new JSONArray();
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread != null && !str.equals(thread.getName())) {
                for (int i = 0; i < 6; i++) {
                    String str2 = strArr[i];
                    String name = thread.getName();
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(name) && ((str2.contains(name) || name.contains(str2)) && a(thread) != null)) {
                        jSONArray.put(a(thread));
                    }
                }
            }
        }
        return jSONArray;
    }

    private static JSONObject a(Thread thread) {
        if (thread == null || thread.getStackTrace() == null) {
            return null;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("threadId", thread.getId());
            jSONObject.put("threadName", thread.getName());
            jSONObject.put("threadGroup", thread.getThreadGroup());
            StringBuffer stringBuffer = new StringBuffer();
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append(stackTraceElement);
                stringBuffer.append("<br />");
            }
            jSONObject.put("stacks", stringBuffer.toString());
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|1|(11:3|(3:6|(1:47)(2:17|(6:19|(1:21)|22|23|24|(5:29|30|31|32|(1:34)(3:35|36|(2:41|42)(2:39|40)))(1:27)))|4)|51|52|24|(0)|29|30|31|32|(0)(0))|53|24|(0)|29|30|31|32|(0)(0)) */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(android.content.Context r8, java.lang.String r9, java.lang.String r10, java.util.List<com.amap.api.col.p0003sl.hz> r11, boolean r12, com.amap.api.col.p0003sl.hz r13) {
        /*
            java.lang.String r0 = "<br />"
            r1 = 0
            if (r10 != 0) goto L6
            goto L44
        L6:
            java.util.Map r2 = java.lang.Thread.getAllStackTraces()
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L12:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L65
            java.lang.Object r3 = r2.next()
            java.lang.Thread r3 = (java.lang.Thread) r3
            if (r3 == 0) goto L12
            java.lang.String r4 = r3.getName()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L12
            java.lang.String r4 = r3.getName()
            boolean r4 = r10.contains(r4)
            if (r4 != 0) goto L3e
            java.lang.String r4 = r3.getName()
            boolean r4 = r4.contains(r10)
            if (r4 == 0) goto L12
        L3e:
            java.lang.StackTraceElement[] r2 = r3.getStackTrace()
            if (r2 != 0) goto L47
        L44:
            java.lang.String r2 = ""
            goto L66
        L47:
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            int r4 = r2.length
            r5 = r1
        L4e:
            if (r5 >= r4) goto L60
            r6 = r2[r5]
            java.lang.String r7 = "at "
            r3.append(r7)
            r3.append(r6)
            r3.append(r0)
            int r5 = r5 + 1
            goto L4e
        L60:
            java.lang.String r2 = r3.toString()
            goto L66
        L65:
            r2 = 0
        L66:
            com.amap.api.col.3sl.hz r11 = a(r2, r11)
            if (r12 == 0) goto L6f
            if (r11 != 0) goto L6f
            return r1
        L6f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r9)
            r3.append(r0)
            r3.append(r2)
            java.lang.String r9 = r3.toString()
            org.json.JSONArray r10 = a(r10)
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r2 = "crashStack"
            r0.put(r2, r9)     // Catch: java.lang.Throwable -> L94
            java.lang.String r9 = "backStacks"
            r0.put(r9, r10)     // Catch: java.lang.Throwable -> L94
        L94:
            java.lang.String r9 = r0.toString()
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 == 0) goto L9f
            return r1
        L9f:
            java.lang.String r10 = "NATIVE_CRASH_MHD_NAME"
            r0 = 1
            if (r12 != 0) goto Lac
            if (r11 != 0) goto Lac
            java.lang.String r11 = "NATIVE_APP_CRASH_CLS_NAME"
            com.amap.api.col.p0003sl.iv.b(r8, r13, r9, r11, r10)     // Catch: java.lang.Throwable -> Lb1
            return r0
        Lac:
            java.lang.String r12 = "NATIVE_CRASH_CLS_NAME"
            com.amap.api.col.p0003sl.iv.a(r8, r11, r9, r12, r10)     // Catch: java.lang.Throwable -> Lb1
        Lb1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.jg.a(android.content.Context, java.lang.String, java.lang.String, java.util.List, boolean, com.amap.api.col.3sl.hz):boolean");
    }
}
