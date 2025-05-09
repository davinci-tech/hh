package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.net.http.f;
import com.huawei.openalliance.ad.utils.ct;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;

/* loaded from: classes5.dex */
class OkHttpCaller extends b {
    private static OkHttpClient b;
    private static OkHttpClient c;
    private static OkHttpClient d;
    private static i e;
    private static final byte[] f = new byte[0];

    @Override // com.huawei.openalliance.ad.net.http.h
    public Response b(e eVar, a aVar) {
        Response a2 = a(eVar, aVar, false);
        ho.b("OkHttpCaller", "dnserror:" + a2.e());
        if (!a2.e() || a(eVar.h, aVar.b())) {
            return a2;
        }
        String d2 = a2.d();
        Response a3 = a(eVar, aVar, true);
        a3.c(1);
        a3.a(d2);
        if (a3.e()) {
            ct.a().d(Uri.parse(aVar.a() ? aVar.b() : new f.a().a(eVar.h, aVar.c()).a(aVar.i).a().c()).getHost());
        }
        return a3;
    }

    private boolean a(okhttp3.Response response) {
        return Constants.GZIP.equalsIgnoreCase(response.header("Content-Encoding"));
    }

    private boolean a(Context context, String str) {
        return "dnkeeperServer".equalsIgnoreCase(str) || (str != null && str.equalsIgnoreCase(context.getString(R.string._2130851074_res_0x7f023502)));
    }

    private void a(Request.Builder builder, a aVar) {
        builder.addHeader(j2.v, Constants.GZIP);
        if (a(aVar)) {
            builder.addHeader("hw-request-type", b(aVar));
        }
        if (aVar.h == 1) {
            String k = com.huawei.openalliance.ad.utils.d.k(this.f7301a);
            if (!TextUtils.isEmpty(k)) {
                builder.removeHeader("User-Agent").addHeader("User-Agent", k);
            }
        }
        if (aVar.f != null) {
            for (Map.Entry<String, String> entry : aVar.f.a().entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.huawei.openalliance.ad.net.http.a r5, okhttp3.Request.Builder r6) {
        /*
            r4 = this;
            boolean r0 = r5.k
            if (r0 == 0) goto Lb
            java.lang.String r0 = "Content-Encoding"
            java.lang.String r1 = "gzip"
            r6.addHeader(r0, r1)
        Lb:
            boolean r0 = r4.a(r5)
            java.lang.String r1 = "Content-Type"
            java.lang.String r2 = "OkHttpCaller"
            if (r0 == 0) goto L20
            java.lang.String r0 = "content type stream."
            com.huawei.openalliance.ad.ho.a(r2, r0)
            r6.removeHeader(r1)
            java.lang.String r0 = "application/octet-stream"
            goto L31
        L20:
            java.lang.String r0 = r5.g
            if (r0 == 0) goto L34
            java.lang.String r0 = r5.g
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r3 = "content type: %s"
            com.huawei.openalliance.ad.ho.a(r2, r3, r0)
            java.lang.String r0 = r5.g
        L31:
            r6.addHeader(r1, r0)
        L34:
            byte[] r0 = r5.j
            if (r0 == 0) goto L44
            byte[] r5 = r5.j
            int r5 = r5.length
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r0 = "Content-Length"
            r6.addHeader(r0, r5)
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.net.http.OkHttpCaller.a(com.huawei.openalliance.ad.net.http.a, okhttp3.Request$Builder):void");
    }

    private static OkHttpClient a(e eVar, boolean z) {
        OkHttpClient okHttpClient;
        synchronized (f) {
            if (b == null || d == null || c == null || e == null) {
                OkHttpClient.Builder protocols = new OkHttpClient.Builder().connectionPool(new ConnectionPool(8, 10L, TimeUnit.MINUTES)).readTimeout(eVar.c, TimeUnit.MILLISECONDS).connectTimeout(eVar.b, TimeUnit.MILLISECONDS).protocols(Collections.unmodifiableList(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1)));
                i a2 = HttpCallerFactory.a();
                e = a2;
                a2.a(protocols);
                HttpsConfig.a(protocols, false, eVar.i);
                try {
                    protocols.dispatcher(protocols.createDispatcher(Protocol.HTTP_2));
                } catch (Throwable th) {
                    ho.c("OkHttpCaller", "create dispatcher error, " + th.getClass().getSimpleName());
                }
                b = protocols.build();
                c = protocols.dns(new l(eVar.h, true)).build();
                OkHttpClient.Builder newBuilder = b.newBuilder();
                HttpsConfig.a(newBuilder, true, false);
                d = newBuilder.build();
            }
            okHttpClient = z ? c : eVar.g ? d : b;
        }
        return okHttpClient;
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x0365, code lost:
    
        if (r3 > 0) goto L162;
     */
    /* JADX WARN: Failed to calculate best type for var: r21v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryInsertAdditionalMove(FixTypesVisitor.java:558)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r21v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r21v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryInsertAdditionalMove(FixTypesVisitor.java:555)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r21v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:70)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.applyResolvedVars(TypeSearch.java:100)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:76)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 20, insn: 0x03ef: INVOKE (r20 I:java.io.Closeable) STATIC call: com.huawei.openalliance.ad.utils.cy.a(java.io.Closeable):void A[MD:(java.io.Closeable):void (m)], block:B:203:0x03ec */
    /* JADX WARN: Not initialized variable reg: 21, insn: 0x03b5: MOVE (r1 I:??[OBJECT, ARRAY]) = (r21 I:??[OBJECT, ARRAY]), block:B:202:0x03b5 */
    /* JADX WARN: Not initialized variable reg: 24, insn: 0x03ec: INVOKE (r24 I:java.io.Closeable) STATIC call: com.huawei.openalliance.ad.utils.cy.a(java.io.Closeable):void A[MD:(java.io.Closeable):void (m)], block:B:203:0x03ec */
    /* JADX WARN: Removed duplicated region for block: B:38:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x039e A[Catch: all -> 0x03b4, TryCatch #15 {all -> 0x03b4, blocks: (B:73:0x0345, B:39:0x0367, B:40:0x03d6, B:62:0x037d, B:64:0x039e, B:65:0x03a5, B:67:0x03ab, B:36:0x03c8), top: B:15:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03ab A[Catch: all -> 0x03b4, TryCatch #15 {all -> 0x03b4, blocks: (B:73:0x0345, B:39:0x0367, B:40:0x03d6, B:62:0x037d, B:64:0x039e, B:65:0x03a5, B:67:0x03ab, B:36:0x03c8), top: B:15:0x0065 }] */
    /* JADX WARN: Type inference failed for: r5v41, types: [byte[], java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v5, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.openalliance.ad.net.http.Response a(com.huawei.openalliance.ad.net.http.e r29, com.huawei.openalliance.ad.net.http.a r30, boolean r31) {
        /*
            Method dump skipped, instructions count: 1020
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.net.http.OkHttpCaller.a(com.huawei.openalliance.ad.net.http.e, com.huawei.openalliance.ad.net.http.a, boolean):com.huawei.openalliance.ad.net.http.Response");
    }

    OkHttpCaller(Context context) {
        super(context);
    }
}
