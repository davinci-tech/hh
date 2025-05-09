package defpackage;

import android.util.Patterns;
import com.huawei.health.device.wifi.entity.builder.BaseBuilder;
import com.huawei.health.device.wifi.entity.model.Entity;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.vcd;
import health.compact.a.IoUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.regex.Pattern;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.elements.config.BasicListDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes3.dex */
public class ctl {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11464a;
    private static uxj b;
    private static String c;
    public static final StringBuilder d;
    private static CoapEndpoint e;
    private static ctr i;

    static {
        StringBuilder sb = new StringBuilder("coap://");
        sb.append(Patterns.IP_ADDRESS);
        sb.append(":[0-9]{1,4}");
        d = sb;
        f11464a = new Object();
        c = "";
    }

    public static void b(String str) {
        synchronized (f11464a) {
            c = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(BaseBuilder baseBuilder) {
        synchronized (f11464a) {
            String str = "coap://" + c + ":5683" + baseBuilder.getUri();
            cpw.c(true, "CoapService", "createClient: uri is ", str);
            if (i == null) {
                i = new ctr();
            }
            i.b(str);
            i.a(Long.valueOf(baseBuilder.getDefaultTime()));
            CoapEndpoint.e eVar = new CoapEndpoint.e();
            eVar.e(new uyr(), new ctf());
            i.d(eVar.e());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x007c A[Catch: all -> 0x008f, TryCatch #0 {, blocks: (B:20:0x0005, B:22:0x000b, B:5:0x0047, B:7:0x005b, B:8:0x0062, B:10:0x007c, B:11:0x008d, B:18:0x0082, B:4:0x002b), top: B:19:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0082 A[Catch: all -> 0x008f, TryCatch #0 {, blocks: (B:20:0x0005, B:22:0x000b, B:5:0x0047, B:7:0x005b, B:8:0x0062, B:10:0x007c, B:11:0x008d, B:18:0x0082, B:4:0x002b), top: B:19:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x005b A[Catch: all -> 0x008f, TryCatch #0 {, blocks: (B:20:0x0005, B:22:0x000b, B:5:0x0047, B:7:0x005b, B:8:0x0062, B:10:0x007c, B:11:0x008d, B:18:0x0082, B:4:0x002b), top: B:19:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void e(com.huawei.health.device.wifi.entity.builder.BaseBuilder r6, java.lang.Integer r7, defpackage.csz r8) {
        /*
            java.lang.Object r0 = defpackage.ctl.f11464a
            monitor-enter(r0)
            if (r7 == 0) goto L2b
            int r1 = r7.intValue()     // Catch: java.lang.Throwable -> L8f
            if (r1 <= 0) goto L2b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
            java.lang.String r2 = "coap://"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r2 = defpackage.ctl.c     // Catch: java.lang.Throwable -> L8f
            r1.append(r2)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r2 = ":"
            r1.append(r2)     // Catch: java.lang.Throwable -> L8f
            r1.append(r7)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = r6.getUri()     // Catch: java.lang.Throwable -> L8f
            r1.append(r7)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Throwable -> L8f
            goto L47
        L2b:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
            java.lang.String r1 = "coap://"
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r1 = defpackage.ctl.c     // Catch: java.lang.Throwable -> L8f
            r7.append(r1)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r1 = ":5684"
            r7.append(r1)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r1 = r6.getUri()     // Catch: java.lang.Throwable -> L8f
            r7.append(r1)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L8f
        L47:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L8f
            java.lang.String r2 = "createClient: uri is "
            r3 = 0
            r1[r3] = r2     // Catch: java.lang.Throwable -> L8f
            r2 = 1
            r1[r2] = r7     // Catch: java.lang.Throwable -> L8f
            java.lang.String r4 = "CoapService"
            defpackage.cpw.c(r2, r4, r1)     // Catch: java.lang.Throwable -> L8f
            uxj r1 = defpackage.ctl.b     // Catch: java.lang.Throwable -> L8f
            if (r1 != 0) goto L62
            uxj r1 = new uxj     // Catch: java.lang.Throwable -> L8f
            r1.<init>()     // Catch: java.lang.Throwable -> L8f
            defpackage.ctl.b = r1     // Catch: java.lang.Throwable -> L8f
        L62:
            uxj r1 = defpackage.ctl.b     // Catch: java.lang.Throwable -> L8f
            r1.b(r7)     // Catch: java.lang.Throwable -> L8f
            uxj r7 = defpackage.ctl.b     // Catch: java.lang.Throwable -> L8f
            long r4 = r6.getDefaultTime()     // Catch: java.lang.Throwable -> L8f
            java.lang.Long r6 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> L8f
            r7.a(r6)     // Catch: java.lang.Throwable -> L8f
            org.eclipse.californium.core.network.CoapEndpoint r6 = b(r8)     // Catch: java.lang.Throwable -> L8f
            defpackage.ctl.e = r6     // Catch: java.lang.Throwable -> L8f
            if (r6 == 0) goto L82
            uxj r7 = defpackage.ctl.b     // Catch: java.lang.Throwable -> L8f
            r7.d(r6)     // Catch: java.lang.Throwable -> L8f
            goto L8d
        L82:
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = "createClient: sCoapEndpoint is null"
            r6[r3] = r7     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = "CoapService"
            defpackage.cpw.d(r2, r7, r6)     // Catch: java.lang.Throwable -> L8f
        L8d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8f
            return
        L8f:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8f
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ctl.e(com.huawei.health.device.wifi.entity.builder.BaseBuilder, java.lang.Integer, csz):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v6, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Exception, java.security.cert.CertificateException] */
    private static CoapEndpoint b(csz cszVar) {
        ?? r1 = 0;
        InputStream inputStream = null;
        if (cszVar == null) {
            return null;
        }
        Certificate[] certificateArr = new Certificate[1];
        try {
            try {
                inputStream = cpp.a().getResources().getAssets().open("root.pem");
                certificateArr[0] = CertificateFactory.getInstance("X.509").generateCertificate(inputStream);
            } catch (Throwable th) {
                IoUtils.e((Closeable) r1);
                throw th;
            }
        } catch (IOException | CertificateException e2) {
            cpw.e(true, "CoapService", e2.getMessage());
        }
        IoUtils.e(inputStream);
        vcd.e eVar = new vcd.e(Configuration.d());
        eVar.e(new InetSocketAddress(0));
        r1 = cszVar.e();
        if (r1 != 0 && cszVar.c() != null) {
            eVar.a(new vfa(vbk.a(cszVar.e()), cszVar.c()));
        } else {
            cpw.d(true, "CoapService", "createDtlsCoapEndpoint: getPskIv or getPskKey is null");
        }
        eVar.d(vez.a().b(certificateArr).c().b());
        eVar.a(DtlsConfig.ao, (vaw<DtlsConfig.DtlsRole>) DtlsConfig.DtlsRole.CLIENT_ONLY);
        eVar.a((BasicListDefinition) DtlsConfig.b, (Object[]) new CipherSuite[]{CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256});
        DTLSConnector dTLSConnector = new DTLSConnector(eVar.e());
        CoapEndpoint.e eVar2 = new CoapEndpoint.e();
        eVar2.e(new uyr(), new ctf());
        eVar2.d(dTLSConnector);
        return eVar2.e();
    }

    public static void e(final BaseBuilder baseBuilder, final Entity.EntityResponseCallback entityResponseCallback) {
        if (baseBuilder == null || entityResponseCallback == null) {
            cpw.c(true, "CoapService", "obj or callback is null");
            return;
        }
        synchronized (f11464a) {
            jdx.b(new Runnable() { // from class: ctl.2
                /* JADX WARN: Removed duplicated region for block: B:14:0x00b0 A[Catch: Exception -> 0x00b4, RuntimeException -> 0x00be, IOException -> 0x00c8, vbe -> 0x00d2, TRY_LEAVE, TryCatch #2 {IOException -> 0x00c8, RuntimeException -> 0x00be, vbe -> 0x00d2, Exception -> 0x00b4, blocks: (B:3:0x0008, B:5:0x0012, B:7:0x001e, B:9:0x0039, B:12:0x0048, B:14:0x00b0, B:16:0x0053), top: B:2:0x0008 }] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() {
                    /*
                        r9 = this;
                        java.lang.String r0 = "CoapService"
                        com.huawei.health.device.wifi.entity.builder.BaseBuilder r1 = com.huawei.health.device.wifi.entity.builder.BaseBuilder.this
                        defpackage.ctl.c(r1)
                        r1 = 0
                        ctr r2 = defpackage.ctl.c()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        uxi r2 = r2.e()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        if (r2 == 0) goto Ldb
                        org.eclipse.californium.core.coap.CoAP$ResponseCode r3 = org.eclipse.californium.core.coap.CoAP.ResponseCode.CONTENT     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        org.eclipse.californium.core.coap.CoAP$ResponseCode r4 = r2.c()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        if (r3 == 0) goto Ldb
                        r3 = 1
                        java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r5 = defpackage.uxl.e(r2)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        r6 = 0
                        r4[r6] = r5     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        defpackage.cpw.c(r3, r0, r4)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r4 = "/.well-known/core?st=cloudSetup"
                        com.huawei.health.device.wifi.entity.builder.BaseBuilder r5 = com.huawei.health.device.wifi.entity.builder.BaseBuilder.this     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r5 = r5.getUri()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        if (r4 != 0) goto L53
                        com.huawei.health.device.wifi.entity.builder.BaseBuilder r4 = com.huawei.health.device.wifi.entity.builder.BaseBuilder.this     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r5 = "/.well-known/core?st=monitordev"
                        java.lang.String r4 = r4.getUri()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        boolean r4 = r5.equals(r4)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        if (r4 == 0) goto L48
                        goto L53
                    L48:
                        com.huawei.health.device.wifi.entity.builder.BaseBuilder r3 = com.huawei.health.device.wifi.entity.builder.BaseBuilder.this     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r2 = r2.a()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        ctc r1 = r3.makeResponseEntity(r2)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        goto Lae
                    L53:
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r5 = "coap:/"
                        r4.<init>(r5)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        uxr r5 = r2.b()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        org.eclipse.californium.elements.EndpointContext r5 = r5.getSourceContext()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.net.InetSocketAddress r5 = r5.getPeerAddress()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.net.InetAddress r5 = r5.getAddress()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        r4.append(r5)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r5 = ":"
                        r4.append(r5)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        uxr r5 = r2.b()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        org.eclipse.californium.elements.EndpointContext r5 = r5.getSourceContext()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.net.InetSocketAddress r5 = r5.getPeerAddress()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        int r5 = r5.getPort()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        r4.append(r5)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        r5 = 2
                        java.lang.Object[] r7 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r8 = "address "
                        r7[r6] = r8     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        r7[r3] = r4     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        defpackage.cpw.c(r3, r0, r7)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        boolean r7 = defpackage.ctl.d(r4)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.String r8 = "get flag "
                        r5[r6] = r8     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        java.lang.Boolean r8 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        r5[r3] = r8     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        defpackage.cpw.c(r3, r0, r5)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        com.huawei.health.device.wifi.entity.builder.BaseBuilder r3 = com.huawei.health.device.wifi.entity.builder.BaseBuilder.this     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        ctc r1 = defpackage.ctl.c(r3, r7, r2, r4)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                    Lae:
                        if (r1 == 0) goto Ldb
                        r1.c(r6)     // Catch: java.lang.Exception -> Lb4 java.lang.RuntimeException -> Lbe java.io.IOException -> Lc8 defpackage.vbe -> Ld2
                        goto Ldb
                    Lb4:
                        java.lang.String r2 = "get Exception"
                        java.lang.Object[] r2 = new java.lang.Object[]{r2}
                        com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
                        goto Ldb
                    Lbe:
                        java.lang.String r2 = "get RuntimeException"
                        java.lang.Object[] r2 = new java.lang.Object[]{r2}
                        com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
                        goto Ldb
                    Lc8:
                        java.lang.String r2 = "get IOException"
                        java.lang.Object[] r2 = new java.lang.Object[]{r2}
                        com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
                        goto Ldb
                    Ld2:
                        java.lang.String r2 = "get ConnectorException"
                        java.lang.Object[] r2 = new java.lang.Object[]{r2}
                        com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
                    Ldb:
                        com.huawei.health.device.wifi.entity.model.Entity$EntityResponseCallback r0 = r2
                        r0.onResponse(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: defpackage.ctl.AnonymousClass2.run():void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ctc a(BaseBuilder baseBuilder, boolean z, uxi uxiVar, String str) {
        if (z) {
            return baseBuilder.makeResponseEntity(uxiVar.a(), str);
        }
        return baseBuilder.makeResponseEntity(uxiVar.a());
    }

    public static void c(final BaseBuilder baseBuilder, final Entity.EntityResponseCallback entityResponseCallback) {
        if (baseBuilder == null || entityResponseCallback == null) {
            cpw.c(true, "CoapService", "post obj or callback is null");
            return;
        }
        synchronized (f11464a) {
            jdx.b(new Runnable() { // from class: ctl.4
                @Override // java.lang.Runnable
                public void run() {
                    uxi uxiVar;
                    ctl.b(BaseBuilder.this);
                    ctc ctcVar = null;
                    try {
                        uxiVar = ctl.i.a(BaseBuilder.this.makeRequestStream(), 50);
                    } catch (Exception unused) {
                        LogUtil.b("CoapService", "post sendto failed: ENETUNREACH");
                        uxiVar = null;
                    }
                    if (uxiVar != null) {
                        cpw.c(true, "CoapService", uxl.e(uxiVar));
                        ctcVar = BaseBuilder.this.makeResponseEntity(uxiVar.a());
                    } else {
                        cpw.e(true, "CoapService", "post: response is null");
                    }
                    entityResponseCallback.onResponse(ctcVar);
                }
            });
        }
    }

    public static void a(final BaseBuilder baseBuilder, final Entity.EntityResponseCallback entityResponseCallback) {
        if (baseBuilder == null || entityResponseCallback == null) {
            cpw.c(true, "CoapService", "postByte obj or callback is null");
            return;
        }
        synchronized (f11464a) {
            jdx.b(new Runnable() { // from class: ctl.1
                @Override // java.lang.Runnable
                public void run() {
                    ctc ctcVar;
                    ctl.b(BaseBuilder.this);
                    ctp b2 = ctl.i.b(BaseBuilder.this, 50);
                    if (b2 != null) {
                        cpw.c(true, "CoapService", "post: getResponseText ", b2.b());
                        ctcVar = BaseBuilder.this.makeResponseEntity(b2.d());
                    } else {
                        cpw.e(true, "CoapService", "post: response is null");
                        ctcVar = null;
                    }
                    entityResponseCallback.onResponse(ctcVar);
                }
            });
        }
    }

    public static boolean d(String str) {
        return Pattern.compile(d.toString()).matcher(str).matches();
    }

    public static void c(final BaseBuilder baseBuilder, final Integer num, final csz cszVar, final Entity.EntityResponseCallback entityResponseCallback) {
        if (baseBuilder == null || entityResponseCallback == null) {
            cpw.c(true, "CoapService", "postByDtls obj or callback is null");
        } else {
            jdx.b(new Runnable() { // from class: ctl.3
                @Override // java.lang.Runnable
                public void run() {
                    ctl.e(BaseBuilder.this, num, cszVar, entityResponseCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0046 A[Catch: all -> 0x0056, TryCatch #0 {, blocks: (B:4:0x0003, B:7:0x0009, B:9:0x0030, B:10:0x0051, B:11:0x0054, B:15:0x0046, B:17:0x0016, B:19:0x0022), top: B:3:0x0003, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030 A[Catch: all -> 0x0056, TryCatch #0 {, blocks: (B:4:0x0003, B:7:0x0009, B:9:0x0030, B:10:0x0051, B:11:0x0054, B:15:0x0046, B:17:0x0016, B:19:0x0022), top: B:3:0x0003, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(com.huawei.health.device.wifi.entity.builder.BaseBuilder r5, java.lang.Integer r6, defpackage.csz r7, com.huawei.health.device.wifi.entity.model.Entity.EntityResponseCallback r8) {
        /*
            java.lang.Object r0 = defpackage.ctl.f11464a
            monitor-enter(r0)
            e(r5, r6, r7)     // Catch: java.lang.Throwable -> L56
            r6 = 0
            r7 = 0
            r1 = 1
            uxj r2 = defpackage.ctl.b     // Catch: defpackage.vbe -> L16 java.io.IOException -> L22 java.lang.Throwable -> L56
            java.lang.String r3 = r5.makeRequestStream()     // Catch: defpackage.vbe -> L16 java.io.IOException -> L22 java.lang.Throwable -> L56
            r4 = 50
            uxi r2 = r2.a(r3, r4)     // Catch: defpackage.vbe -> L16 java.io.IOException -> L22 java.lang.Throwable -> L56
            goto L2e
        L16:
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "getResponse ConnectorException"
            r2[r7] = r3     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "CoapService"
            com.huawei.hwlogsmodel.LogUtil.b(r3, r2)     // Catch: java.lang.Throwable -> L56
            goto L2d
        L22:
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "getResponse IOException"
            r2[r7] = r3     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "CoapService"
            com.huawei.hwlogsmodel.LogUtil.b(r3, r2)     // Catch: java.lang.Throwable -> L56
        L2d:
            r2 = r6
        L2e:
            if (r2 == 0) goto L46
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = defpackage.uxl.e(r2)     // Catch: java.lang.Throwable -> L56
            r6[r7] = r3     // Catch: java.lang.Throwable -> L56
            java.lang.String r7 = "CoapService"
            defpackage.cpw.c(r1, r7, r6)     // Catch: java.lang.Throwable -> L56
            java.lang.String r6 = r2.a()     // Catch: java.lang.Throwable -> L56
            ctc r6 = r5.makeResponseEntity(r6)     // Catch: java.lang.Throwable -> L56
            goto L51
        L46:
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L56
            java.lang.String r2 = "post: response is null"
            r5[r7] = r2     // Catch: java.lang.Throwable -> L56
            java.lang.String r7 = "CoapService"
            defpackage.cpw.e(r1, r7, r5)     // Catch: java.lang.Throwable -> L56
        L51:
            r8.onResponse(r6)     // Catch: java.lang.Throwable -> L56
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
            return
        L56:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ctl.e(com.huawei.health.device.wifi.entity.builder.BaseBuilder, java.lang.Integer, csz, com.huawei.health.device.wifi.entity.model.Entity$EntityResponseCallback):void");
    }
}
