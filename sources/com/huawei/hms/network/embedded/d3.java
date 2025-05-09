package com.huawei.hms.network.embedded;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class d3 {

    /* renamed from: a, reason: collision with root package name */
    public String f5212a;
    public URL b;
    public MalformedURLException c;
    public List<String> d = new ArrayList();

    public String toString() {
        return super.toString();
    }

    public d3 setIps(List<String> list) {
        this.d = list;
        return this;
    }

    public String getUrl() {
        return this.f5212a;
    }

    public URL getURL() throws MalformedURLException {
        URL url = this.b;
        if (url != null) {
            return url;
        }
        throw this.c;
    }

    public int getPort() {
        URL url = this.b;
        if (url == null) {
            return -1;
        }
        return url.getPort();
    }

    public List<String> getIps() {
        return this.d;
    }

    public String getHost() {
        URL url = this.b;
        return url == null ? "" : url.getHost();
    }

    public d3 addIp(String str) {
        this.d.add(str);
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.network.embedded.d3 a(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "url == null"
            com.huawei.hms.framework.common.CheckParamUtils.checkNotNull(r9, r0)
            r2 = 1
            r3 = 0
            java.lang.String r4 = "ws:"
            r5 = 0
            r6 = 3
            r1 = r9
            boolean r0 = r1.regionMatches(r2, r3, r4, r5, r6)
            if (r0 == 0) goto L1b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "http:"
            r0.<init>(r1)
            r1 = 3
            goto L30
        L1b:
            r3 = 1
            r4 = 0
            java.lang.String r5 = "wss:"
            r6 = 0
            r7 = 4
            r2 = r9
            boolean r0 = r2.regionMatches(r3, r4, r5, r6, r7)
            if (r0 == 0) goto L3b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "https:"
            r0.<init>(r1)
            r1 = 4
        L30:
            java.lang.String r9 = r9.substring(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
        L3b:
            boolean r0 = com.huawei.hms.network.base.util.HttpUtils.isHttpOrGrsUrl(r9)
            if (r0 == 0) goto L55
            r8.f5212a = r9
            boolean r0 = com.huawei.hms.network.base.util.HttpUtils.isHttpUrl(r9)
            if (r0 == 0) goto L54
            java.net.URL r0 = new java.net.URL     // Catch: java.net.MalformedURLException -> L51
            r0.<init>(r9)     // Catch: java.net.MalformedURLException -> L51
            r8.b = r0     // Catch: java.net.MalformedURLException -> L51
            goto L54
        L51:
            r9 = move-exception
            r8.c = r9
        L54:
            return r8
        L55:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "url not valid must be http://;https://;grs://"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.d3.a(java.lang.String):com.huawei.hms.network.embedded.d3");
    }

    public d3(String str) {
        a(str);
    }
}
