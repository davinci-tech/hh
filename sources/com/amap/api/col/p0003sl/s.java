package com.amap.api.col.p0003sl;

import android.content.Context;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.Hashtable;
import java.util.Map;

/* loaded from: classes2.dex */
public final class s extends hf<String, a> {
    private boolean j;
    private int[] k;

    public static final class a {
        public String b;
        public String c;

        /* renamed from: a, reason: collision with root package name */
        public int f1359a = -1;
        public boolean d = false;
    }

    @Override // com.amap.api.col.p0003sl.hf
    protected final String c() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final boolean isSupportIPV6() {
        return true;
    }

    public s(Context context, String str) {
        super(context, str);
        this.j = true;
        this.k = new int[]{10000, 0, 10018, 10019, 10020, 10021, 10022, 10023};
        this.h = "/feedback";
        this.isPostFlag = false;
        this.j = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0039, code lost:
    
        r2.d = true;
     */
    @Override // com.amap.api.col.p0003sl.hf
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amap.api.col.3sl.s.a a(java.lang.String r6) throws com.amap.api.col.p0003sl.he {
        /*
            r5 = this;
            java.lang.String r0 = "errcode"
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L41
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L41
            boolean r6 = r1.has(r0)     // Catch: java.lang.Throwable -> L41
            if (r6 == 0) goto L1e
            int r6 = r1.optInt(r0)     // Catch: java.lang.Throwable -> L41
            java.lang.String r0 = "errmsg"
            java.lang.String r0 = r1.optString(r0)     // Catch: java.lang.Throwable -> L41
            java.lang.String r2 = "errdetail"
            java.lang.String r1 = r1.optString(r2)     // Catch: java.lang.Throwable -> L41
            goto L22
        L1e:
            java.lang.String r0 = ""
            r6 = -1
            r1 = r0
        L22:
            com.amap.api.col.3sl.s$a r2 = new com.amap.api.col.3sl.s$a     // Catch: java.lang.Throwable -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L41
            r2.f1359a = r6     // Catch: java.lang.Throwable -> L41
            r2.b = r0     // Catch: java.lang.Throwable -> L41
            r2.c = r1     // Catch: java.lang.Throwable -> L41
            r0 = 0
            r2.d = r0     // Catch: java.lang.Throwable -> L41
            int[] r1 = r5.k     // Catch: java.lang.Throwable -> L41
            int r3 = r1.length     // Catch: java.lang.Throwable -> L41
        L33:
            if (r0 >= r3) goto L40
            r4 = r1[r0]     // Catch: java.lang.Throwable -> L41
            if (r4 != r6) goto L3d
            r6 = 1
            r2.d = r6     // Catch: java.lang.Throwable -> L41
            goto L40
        L3d:
            int r0 = r0 + 1
            goto L33
        L40:
            return r2
        L41:
            r6 = move-exception
            r6.printStackTrace()
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.s.a(java.lang.String):com.amap.api.col.3sl.s$a");
    }

    @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put(MedalConstants.EVENT_KEY, hn.f(this.g));
        if (this.j) {
            hashtable.put("pname", "3dmap");
        }
        String a2 = hq.a();
        String a3 = hq.a(this.g, a2, ia.b(hashtable));
        hashtable.put("ts", a2);
        hashtable.put("scode", a3);
        return hashtable;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return "http://restsdk.amap.com/v4" + this.h;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getIPV6URL() {
        return dv.a(getURL());
    }
}
