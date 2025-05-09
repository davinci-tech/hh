package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.fz;
import com.amap.api.col.p0003sl.ka;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.ServiceSettings;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class ev<T, V> extends hu {
    protected T b;
    protected Context i;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f1031a = true;
    protected int g = 1;
    protected String h = "";
    private int k = 1;
    protected String j = "";

    protected abstract V a(String str) throws AMapException;

    protected abstract String c();

    protected fz.b e() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public Map<String, String> getParams() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public Map<String, String> getRequestHead() {
        return null;
    }

    public ev(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.i = context;
        this.b = t;
        this.g = 1;
        setSoTimeout(ServiceSettings.getInstance().getSoTimeOut());
        setConnectionTimeout(ServiceSettings.getInstance().getConnectionTimeOut());
    }

    private String f() {
        return this.j;
    }

    private String g() {
        String ipv6url = getIPV6URL();
        if (ipv6url == null) {
            return null;
        }
        try {
            int indexOf = ipv6url.indexOf(".com/");
            int indexOf2 = ipv6url.indexOf("?");
            if (indexOf2 == -1) {
                return ipv6url.substring(indexOf + 5);
            }
            return ipv6url.substring(indexOf + 5, indexOf2);
        } catch (Throwable unused) {
            return null;
        }
    }

    protected V a(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            fd.a(e, "ProtocalHandler", "loadData");
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        fd.b(str);
        return a(str);
    }

    public final V d() throws AMapException {
        if (this.b == null) {
            return null;
        }
        try {
            return h();
        } catch (AMapException e) {
            gi.a(g(), f(), e);
            throw e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
    
        r0 = a(r0, r12, r16);
        r12 = java.lang.System.currentTimeMillis();
        r7 = b(r0);
        com.amap.api.col.p0003sl.gi.a(r16.i, g(), r12 - r9, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private V h() throws com.amap.api.services.core.AMapException {
        /*
            Method dump skipped, instructions count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.ev.h():java.lang.Object");
    }

    private byte[] a(int i, jz jzVar, hu huVar) throws hm {
        kb e;
        setHttpProtocol(i == 1 ? ka.c.HTTP : ka.c.HTTPS);
        if (this.f1031a) {
            e = jz.a(huVar);
        } else {
            e = jz.e(huVar);
        }
        if (e == null) {
            return null;
        }
        byte[] bArr = e.f1250a;
        this.j = e.d;
        return bArr;
    }

    private V b(byte[] bArr) throws AMapException {
        return a(bArr);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public String getSDKName() {
        return "sea";
    }
}
