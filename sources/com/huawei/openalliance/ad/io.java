package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.iu;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class io {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f7067a = Pattern.compile("(\\d+)-(\\d+)/(\\d+)");
    private static final Pattern b = Pattern.compile("(\\d+)/(\\d+)");
    private static final Pattern c = Pattern.compile("(\\d+)");
    private final ig d;
    private final iu e;
    private final ia f;
    private Map<String, Long> g;
    private ip h;
    private iq i;
    private ie j;
    private long k = 157286400;
    private ih l;
    private Context m;

    private boolean a(int i) {
        return i != -1;
    }

    private boolean c(int i) {
        return (i == 200 || i == 206) ? false : true;
    }

    public void a(final Socket socket) {
        if (!a()) {
            it itVar = new it();
            ig igVar = this.d;
            if (igVar != null && !TextUtils.isEmpty(igVar.a())) {
                itVar.a("Range", this.d.a());
            }
            this.e.a(new iv(this.d.b(), itVar, null), new iu.a() { // from class: com.huawei.openalliance.ad.io.1
                @Override // com.huawei.openalliance.ad.iu.a
                public void a(Throwable th) {
                    ho.b("ProxyRequestProcessor", "request remote server failed:%s, info:%s", com.huawei.openalliance.ad.utils.dl.a(io.this.d.b()), th.getClass().getSimpleName());
                    io.this.b(socket);
                }

                @Override // com.huawei.openalliance.ad.iu.a
                public void a(iw iwVar) {
                    ho.a("ProxyRequestProcessor", "request remote server success:%s,", com.huawei.openalliance.ad.utils.dl.a(io.this.d.b()));
                    io ioVar = io.this;
                    ioVar.a(socket, iwVar, ioVar.d.c());
                }
            });
            return;
        }
        ho.b("ProxyRequestProcessor", "max limit, skip.");
        iq iqVar = this.i;
        if (iqVar != null) {
            iqVar.a(-2);
        }
        ih ihVar = this.l;
        if (ihVar != null) {
            ihVar.a();
        }
        b(socket);
    }

    public void a(iq iqVar) {
        this.i = iqVar;
    }

    public void a(ih ihVar) {
        this.l = ihVar;
    }

    private boolean b(int i) {
        int i2 = i / 100;
        return i2 == 4 || i2 == 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Socket socket) {
        try {
            ho.a("ProxyRequestProcessor", "close socket");
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
            if (!socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
            socket.close();
        } catch (Throwable th) {
            ho.c("ProxyRequestProcessor", "close socket failed, %s", th.getClass().getSimpleName());
        }
    }

    private void b(final String str) {
        if (com.huawei.openalliance.ad.utils.cz.b(this.d.e())) {
            return;
        }
        this.h = new ip(this.m, this.d.e(), this.f, this.d.f());
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.io.2
            @Override // java.lang.Runnable
            public void run() {
                boolean a2 = io.this.h.a(io.this.d.b(), str);
                ho.b("ProxyRequestProcessor", "check file valid: %s", Boolean.valueOf(a2));
                if (a2 || io.this.i == null) {
                    return;
                }
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.io.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ie.a().b(true);
                        io.this.i.a(-3);
                    }
                });
            }
        });
    }

    private long b(iw iwVar) {
        if (iwVar == null) {
            return 0L;
        }
        String a2 = iwVar.a().a(j2.w);
        if (ho.a()) {
            ho.a("ProxyRequestProcessor", "lengthStr: %s", a2);
        }
        if (TextUtils.isEmpty(a2) || a2.length() < 3) {
            return 0L;
        }
        try {
            return Long.parseLong(a2.substring(1, a2.length() - 1));
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private boolean a(int i, long j) {
        long j2 = i;
        if (j2 == j) {
            return true;
        }
        ho.a("ProxyRequestProcessor", "isCompleteLocalResource Range bytes: %d", this.d.d());
        Long d = this.d.d();
        return d != null && d.longValue() + j2 == j;
    }

    private boolean a() {
        return this.j.a(this.d.b()) > this.k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Socket socket, iw iwVar, String str) {
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            try {
                ho.a("ProxyRequestProcessor", "write header to client");
                bufferedOutputStream.write(a(iwVar).getBytes("UTF-8"));
                a(iwVar, bufferedOutputStream, str);
            } catch (Throwable th) {
                th = th;
                try {
                    ho.d("ProxyRequestProcessor", "write header failed: %s", th.getClass().getSimpleName());
                } finally {
                    b(socket);
                    com.huawei.openalliance.ad.utils.cy.a(bufferedOutputStream);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = null;
        }
    }

    private void a(String str, int i) {
        if (this.g.get(this.d.b() + str) == null) {
            this.g.put(this.d.b() + str, Long.valueOf(System.currentTimeMillis() + 60000));
        }
        for (Map.Entry<String, Long> entry : this.g.entrySet()) {
            Long l = this.g.get(entry.getKey());
            if (l != null && l.longValue() < System.currentTimeMillis()) {
                this.g.remove(entry.getKey());
            }
        }
    }

    private void a(BufferedOutputStream bufferedOutputStream, byte[] bArr, int i) {
        bufferedOutputStream.write(bArr, 0, i);
        bufferedOutputStream.flush();
    }

    private void a(iw iwVar, BufferedOutputStream bufferedOutputStream, String str) {
        long j;
        long j2;
        long j3;
        long b2;
        int b3 = iwVar.b();
        OutputStream outputStream = null;
        try {
            if (!c(b3)) {
                ir a2 = this.f.a(this.d.b(), str);
                if (a2 == null) {
                    return;
                }
                OutputStream b4 = a2.b();
                try {
                    if (b3 == 206) {
                        ho.a("ProxyRequestProcessor", "http status code = 200, request uri:" + this.d.b());
                        b2 = a(iwVar.a().a("Content-Range"));
                        ho.a("ProxyRequestProcessor", "totalLength:" + b2);
                        j = this.d.d().longValue();
                    } else {
                        j = 0;
                        if (b3 == 200) {
                            b2 = b(iwVar);
                        } else {
                            j2 = 0;
                            j3 = 0;
                            ho.b("ProxyRequestProcessor", "totalLength: %s, cost: %s", Long.valueOf(j2), Long.valueOf(ie.a().a(this.d.b())));
                            a(iwVar, bufferedOutputStream, a2, b4, j2, j3);
                            outputStream = b4;
                        }
                    }
                    j2 = b2;
                    j3 = j;
                    ho.b("ProxyRequestProcessor", "totalLength: %s, cost: %s", Long.valueOf(j2), Long.valueOf(ie.a().a(this.d.b())));
                    a(iwVar, bufferedOutputStream, a2, b4, j2, j3);
                    outputStream = b4;
                } catch (Throwable th) {
                    th = th;
                    outputStream = b4;
                    try {
                        ho.b("ProxyRequestProcessor", "write err: %s", th.getClass().getSimpleName());
                    } finally {
                        com.huawei.openalliance.ad.utils.cy.a(outputStream);
                    }
                }
            } else if (b(b3)) {
                a(str, b3);
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(3:(3:19|20|(6:(3:23|24|25)(1:55)|26|(4:28|29|30|31)(1:51)|32|33|34)(1:56))|16|17) */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00fa, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00fb, code lost:
    
        r1 = r11;
        r11 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0100, code lost:
    
        r1 = r11;
        r11 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.huawei.openalliance.ad.iw r23, java.io.BufferedOutputStream r24, com.huawei.openalliance.ad.ir r25, java.io.OutputStream r26, long r27, long r29) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.io.a(com.huawei.openalliance.ad.iw, java.io.BufferedOutputStream, com.huawei.openalliance.ad.ir, java.io.OutputStream, long, long):void");
    }

    private void a(ir irVar, OutputStream outputStream, long j, byte[] bArr, int i) {
        int c2;
        if (outputStream != null) {
            if (j < irVar.c()) {
                if (i + j > irVar.c()) {
                    c2 = (int) (irVar.c() - j);
                    i -= c2;
                    ho.a("ProxyRequestProcessor", "start: %d, count: %d", Integer.valueOf(c2), Integer.valueOf(i));
                }
                outputStream.flush();
            }
            c2 = 0;
            outputStream.write(bArr, c2, i);
            outputStream.flush();
        }
    }

    private void a(ir irVar, OutputStream outputStream, long j, int i) {
        if (outputStream == null || !a(i, j)) {
            return;
        }
        ho.a("ProxyRequestProcessor", "Get complete resource, %s", irVar.a());
        this.f.a(j, irVar);
        b(com.huawei.openalliance.ad.utils.ae.h(this.f.a(this.d.b(), irVar)));
    }

    private void a(ir irVar, long j) {
        this.f.a(irVar);
    }

    private void a(Context context) {
        this.j = ie.a();
        long q = fh.b(context.getApplicationContext()).q();
        this.k = q;
        ho.b("ProxyRequestProcessor", "init, max data consume is: %s", Long.valueOf(q));
    }

    private String a(iw iwVar) {
        String format = String.format(Locale.ENGLISH, "HTTP/1.1 %d %s", Integer.valueOf(iwVar.b()), iwVar.d());
        StringBuilder sb = new StringBuilder();
        sb.append(format);
        it a2 = iwVar.a();
        for (String str : a2.a()) {
            sb.append(String.format(Locale.ENGLISH, "%s: %s%n", str, a2.a(str)));
        }
        sb.append("\n");
        String sb2 = sb.toString();
        ho.a("ProxyRequestProcessor", "headers: " + sb2);
        return sb2;
    }

    private long a(String str) {
        if (str != null && !str.isEmpty()) {
            Matcher matcher = f7067a.matcher(str);
            if (matcher.find()) {
                return Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1));
            }
            Matcher matcher2 = b.matcher(str);
            if (matcher2.find()) {
                return Long.parseLong(matcher2.group(2));
            }
            Matcher matcher3 = c.matcher(str);
            if (matcher3.find()) {
                return Long.parseLong(matcher3.group(1));
            }
        }
        return 0L;
    }

    public io(Context context, ig igVar, iu iuVar, ia iaVar, Map<String, Long> map) {
        this.d = igVar;
        this.e = iuVar;
        this.f = iaVar;
        this.g = map;
        Context applicationContext = context.getApplicationContext();
        this.m = applicationContext;
        a(applicationContext);
    }
}
