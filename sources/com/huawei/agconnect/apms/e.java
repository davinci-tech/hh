package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import java.io.IOException;
import okio.ForwardingSource;
import okio.Source;

/* loaded from: classes2.dex */
public class e extends ForwardingSource {
    public long abc;
    public boolean bcd;
    public boolean cde;
    public final /* synthetic */ f def;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(f fVar, Source source) {
        super(source);
        this.def = fVar;
        this.bcd = false;
        this.cde = false;
    }

    public final int abc(wxy wxyVar) {
        long currentTimeMillis = System.currentTimeMillis() - wxyVar.xyz;
        if (currentTimeMillis <= 0 || currentTimeMillis >= 2147483647L) {
            return -1;
        }
        return (int) currentTimeMillis;
    }

    @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        abc("");
        super.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0050, code lost:
    
        if (((okio.BufferedSource) delegate()).exhausted() == false) goto L23;
     */
    @Override // okio.ForwardingSource, okio.Source
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long read(okio.Buffer r7, long r8) throws java.io.IOException {
        /*
            r6 = this;
            boolean r0 = r6.cde
            r1 = 1
            if (r0 != 0) goto L12
            com.huawei.agconnect.apms.f r0 = r6.def
            com.huawei.agconnect.apms.wxy r0 = r0.abc
            int r2 = r6.abc(r0)
            long r2 = (long) r2
            r0.tuv = r2
            r6.cde = r1
        L12:
            long r7 = super.read(r7, r8)     // Catch: java.io.IOException -> L72
            long r2 = r6.abc
            r4 = -1
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L20
            r4 = r7
            goto L22
        L20:
            r4 = 0
        L22:
            long r2 = r2 + r4
            r6.abc = r2
            boolean r0 = r6.bcd
            if (r0 != 0) goto L36
            com.huawei.agconnect.apms.f r0 = r6.def
            com.huawei.agconnect.apms.wxy r0 = r0.abc
            if (r0 == 0) goto L36
            java.util.concurrent.ConcurrentLinkedQueue<java.lang.Object> r2 = com.huawei.agconnect.apms.yza.cde
            r2.remove(r0)
            r6.bcd = r1
        L36:
            if (r9 == 0) goto L52
            long r0 = r6.abc     // Catch: java.io.IOException -> L5e
            com.huawei.agconnect.apms.f r9 = r6.def     // Catch: java.io.IOException -> L5e
            okhttp3.ResponseBody r9 = r9.bcd     // Catch: java.io.IOException -> L5e
            long r2 = r9.getContentLength()     // Catch: java.io.IOException -> L5e
            int r9 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r9 == 0) goto L52
            okio.Source r9 = r6.delegate()     // Catch: java.io.IOException -> L5e
            okio.BufferedSource r9 = (okio.BufferedSource) r9     // Catch: java.io.IOException -> L5e
            boolean r9 = r9.exhausted()     // Catch: java.io.IOException -> L5e
            if (r9 == 0) goto L5d
        L52:
            com.huawei.agconnect.apms.f r9 = r6.def     // Catch: java.io.IOException -> L5e
            com.huawei.agconnect.apms.wxy r9 = r9.abc     // Catch: java.io.IOException -> L5e
            if (r9 == 0) goto L5d
            java.lang.String r9 = ""
            r6.abc(r9)     // Catch: java.io.IOException -> L5e
        L5d:
            return r7
        L5e:
            r7 = move-exception
            com.huawei.agconnect.apms.f r8 = r6.def
            com.huawei.agconnect.apms.wxy r8 = r8.abc
            java.lang.String r9 = r7.getMessage()
            r8.abc(r9)
            java.lang.String r8 = com.huawei.agconnect.apms.r0.abc()
            r6.abc(r8)
            throw r7
        L72:
            r7 = move-exception
            com.huawei.agconnect.apms.f r8 = r6.def
            com.huawei.agconnect.apms.wxy r8 = r8.abc
            long r0 = r6.abc
            r8.abc(r0)
            com.huawei.agconnect.apms.f r8 = r6.def
            com.huawei.agconnect.apms.wxy r8 = r8.abc
            java.lang.String r9 = r7.getMessage()
            r8.abc(r9)
            java.lang.String r8 = com.huawei.agconnect.apms.r0.abc()
            r6.abc(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.e.read(okio.Buffer, long):long");
    }

    public final void abc(String str) {
        wxy wxyVar = this.def.abc;
        if (wxyVar == null || wxyVar.gfe()) {
            return;
        }
        wxy wxyVar2 = this.def.abc;
        wxyVar2.abc(this.abc);
        wxyVar2.uvw = abc(wxyVar2);
        wxyVar2.abc(abc(wxyVar2));
        wxyVar2.d();
        vwx e = wxyVar2.e();
        if (e != null) {
            this.def.abc.abc(true);
            rst.cde.add(new HttpEvent(e, str));
        }
    }
}
