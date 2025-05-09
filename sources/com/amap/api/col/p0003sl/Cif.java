package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.hr;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.col.3sl.if, reason: invalid class name */
/* loaded from: classes2.dex */
public final class Cif {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1176a = ia.c("SRFZHZUVZT3BOa0ZiemZRQQ");
    private static final String b = ia.c("FbGJzX3Nkaw");
    private static final String c = ia.c("SWjJuYVh2eEMwSzVmNklFSmh0UXpVb2xtOVM4eU9Ua3E");
    private static final String d = ia.c("FQU5EU0RLMTA");
    private static final String e = ia.c("FMTAw");
    private static boolean f = false;
    private String g = "";

    public static hr.a a() {
        return new hr.a() { // from class: com.amap.api.col.3sl.if.1

            /* renamed from: a, reason: collision with root package name */
            private Cif f1177a = new Cif();

            @Override // com.amap.api.col.3sl.hr.a
            public final ka a(byte[] bArr, Map<String, String> map) {
                return new js(bArr, map);
            }

            @Override // com.amap.api.col.3sl.hr.a
            public final String a() {
                return Cif.c();
            }

            @Override // com.amap.api.col.3sl.hr.a
            public final String a(Context context, String str) {
                return Cif.a(context, str);
            }

            @Override // com.amap.api.col.3sl.hr.a
            public final Map<String, String> b() {
                return this.f1177a.b();
            }

            @Override // com.amap.api.col.3sl.hr.a
            public final String a(String str, String str2, String str3, String str4) {
                return this.f1177a.a(str, str2, str3, str4);
            }
        };
    }

    public static String a(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(ia.c("UY29kZQ")) != 1) {
                return "";
            }
            String optString = new JSONObject(jSONObject.optString(ia.c("FZGF0YQ"))).optString(ia.c("FYWRpdQ"));
            if (TextUtils.isEmpty(optString)) {
                return "";
            }
            ig.a(optString);
            ib.a(context).a(optString);
            return optString;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public final Map<String, String> b() {
        synchronized (this) {
            if (f) {
                return null;
            }
            f = true;
            HashMap hashMap = new HashMap();
            hashMap.put(ia.c("FZW50"), ia.c("FMg"));
            StringBuilder sb = new StringBuilder();
            sb.append(ia.c("SY2hhbm5lbD0"));
            String str = b;
            sb.append(str);
            sb.append(ia.c("SJmRpdj0"));
            String str2 = d;
            sb.append(str2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str).append(str2).append(ia.c("FQA")).append(c);
            String a2 = ii.a(stringBuffer.toString());
            sb.append(ia.c("FJnNpZ249"));
            sb.append(a2.toUpperCase(Locale.US));
            sb.append(ia.c("SJm91dHB1dD1qc29u") + "\u0000");
            hashMap.put(ia.c("FaW4"), id.a(jp.a(sb.toString().getBytes(), f1176a.getBytes())));
            hashMap.put(ia.c("Sa2V5dA"), e);
            return hashMap;
        }
    }

    private String d() {
        if (!TextUtils.isEmpty(this.g)) {
            return this.g;
        }
        String a2 = hs.a("TUpJaVFGNk5LXHtSX1ZwQlRiV1VVZmtYWU1haV1hYWHCiXJtZcKLdmp8wpFewo1/wphwwoFzZmR8aWp6X2k6XsKDwoF+WGbChGdAScKLwoVXfmNxYEvCjcKLSG7CjGNvwoZtVFZ7WMKXYMKfwo5dZcKHfzZXUG85X0hNOVJrb2U8ZlJGW8KCe8KOV8KQWllrcGrCjcKIT25lUHPCicKGVsKKeG5fwp56XsKbc8KJbUVYR0pqU09gfE5/WT5YeHNAwoDCh1Z4V8KQT3JQYmxQbcKYwpFxdG/Ci3rCmMKQwop+YVbCmWFxwpxBdW07Zjp/ODlAbcKEY1pQwoJowohbV1VmV1laWmtcYGbClXfCk2NvesKdwohdWFnCol/CjWTCmMKicG1ENnAvPFtpcXtfclhfXsKAwolgRWNbS29OwpFafV3CkMKLTcKCwolrU3DCmGnCmX9wdsKPcXDCg3LCnFpGcDVTeTxNWW07bXJePVRfQn3ChGNraFhbwpNcwpXChMKNaFVjeVF8wojChm9YbmvChGDCmHvChGVQWjo0Z3o9djleOztWcVxSfWE9woLChkZdcGTCgVzCjMKUVE12wpV5bcKVwprCnntZworCgsKfwpHCksKnwpHClURURW9YaDtwXU1bck5YX3hSVFZUYlxKWFlua1xeYm9jU8KDa3ZrwpZ5am9Za3jCknR3fA");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < a2.length(); i++) {
            stringBuffer.append((char) (a2.charAt(i) - (i % 48)));
        }
        String stringBuffer2 = stringBuffer.toString();
        StringBuffer stringBuffer3 = new StringBuffer();
        for (int i2 = 0; i2 < stringBuffer2.length() / 2; i2++) {
            stringBuffer3.append((char) ((stringBuffer2.charAt(i2) + stringBuffer2.charAt((stringBuffer2.length() - 1) - i2)) / 2));
        }
        String stringBuffer4 = stringBuffer3.toString();
        this.g = stringBuffer4;
        return stringBuffer4;
    }

    public final String a(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ia.c("LdGlk"), str);
            jSONObject.put(ia.c("FZGl1"), str2);
            jSONObject.put(ia.c("AZGl1Mg"), str3);
            jSONObject.put(ia.c("EZGl1Mw"), str4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        if (TextUtils.isEmpty(jSONObject2)) {
            return null;
        }
        String a2 = ii.a();
        if (!TextUtils.isEmpty(a2)) {
            String a3 = id.a(jp.a((jSONObject2 + "\u0000").getBytes(), a2.getBytes()));
            if (!TextUtils.isEmpty(a3)) {
                try {
                    return ia.c("Fa2V5PQ") + URLEncoder.encode(id.a(ih.a(a2.getBytes("utf-8"), ih.a(d())))) + ia.c("SJmRhdGE9") + URLEncoder.encode(a3);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String c() {
        return ig.a();
    }
}
