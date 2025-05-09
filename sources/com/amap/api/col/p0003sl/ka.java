package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import com.amap.api.col.p0003sl.jt;
import java.net.Proxy;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class ka {
    public static final int DEFAULT_RETRY_TIMEOUT = 5000;
    jt.a f;
    private String h;
    private boolean i;
    private boolean j;
    int c = 20000;
    int d = 20000;
    Proxy e = null;

    /* renamed from: a, reason: collision with root package name */
    private boolean f1246a = false;
    private int b = 20000;
    private boolean g = true;
    private a k = a.NORMAL;
    private b l = b.FIRST_NONDEGRADE;

    public byte[] getEntityBytes() {
        return null;
    }

    public abstract Map<String, String> getParams();

    public abstract Map<String, String> getRequestHead();

    public abstract String getURL();

    public boolean isIgnoreGZip() {
        return false;
    }

    public boolean isSupportIPV6() {
        return false;
    }

    public enum a {
        NORMAL(0),
        INTERRUPT_IO(1),
        NEVER(2),
        FIX(3),
        SINGLE(4);

        private int f;

        a(int i) {
            this.f = i;
        }
    }

    public enum b {
        FIRST_NONDEGRADE(0),
        NEVER_GRADE(1),
        DEGRADE_BYERROR(2),
        DEGRADE_ONLY(3),
        FIX_NONDEGRADE(4),
        FIX_DEGRADE_BYERROR(5),
        FIX_DEGRADE_ONLY(6);

        private int h;

        b(int i2) {
            this.h = i2;
        }

        public final int a() {
            return this.h;
        }

        public final boolean b() {
            int i2 = this.h;
            return i2 == FIRST_NONDEGRADE.h || i2 == NEVER_GRADE.h || i2 == FIX_NONDEGRADE.h;
        }

        public final boolean c() {
            int i2 = this.h;
            return i2 == DEGRADE_BYERROR.h || i2 == DEGRADE_ONLY.h || i2 == FIX_DEGRADE_BYERROR.h || i2 == FIX_DEGRADE_ONLY.h;
        }

        public final boolean d() {
            int i2 = this.h;
            return i2 == DEGRADE_BYERROR.h || i2 == FIX_DEGRADE_BYERROR.h;
        }

        public final boolean e() {
            return this.h == NEVER_GRADE.h;
        }
    }

    public enum c {
        HTTP(0),
        HTTPS(1);

        private int c;

        c(int i) {
            this.c = i;
        }
    }

    final String a() {
        return a(getURL());
    }

    private String a(String str) {
        byte[] entityBytes = getEntityBytes();
        if (entityBytes == null || entityBytes.length == 0) {
            return str;
        }
        Map<String, String> params = getParams();
        if (jt.e != null) {
            if (params != null) {
                params.putAll(jt.e);
            } else {
                params = jt.e;
            }
        }
        if (params == null) {
            return str;
        }
        String a2 = jx.a(params);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append("?").append(a2);
        return stringBuffer.toString();
    }

    final String b() {
        return a(getIPV6URL());
    }

    protected boolean isIPRequest() {
        return !TextUtils.isEmpty(getIPDNSName());
    }

    public String getIPV6URL() {
        return getURL();
    }

    public final void setConnectionTimeout(int i) {
        this.c = i;
    }

    public final void setSoTimeout(int i) {
        this.d = i;
    }

    public int getConntectionTimeout() {
        return this.c;
    }

    public int getSoTimeout() {
        return this.d;
    }

    public final void setProxy(Proxy proxy) {
        this.e = proxy;
    }

    public Proxy getProxy() {
        return this.e;
    }

    public void setDegradeAbility(a aVar) {
        this.k = aVar;
    }

    protected a getDegradeAbility() {
        return this.k;
    }

    protected boolean isBinary() {
        return this.f1246a;
    }

    public void setBinary(boolean z) {
        this.f1246a = z;
    }

    protected boolean isHttps() {
        return this.j;
    }

    public void setHttpProtocol(c cVar) {
        this.j = cVar == c.HTTPS;
    }

    public jt.a getUrlConnectionImpl() {
        return this.f;
    }

    public void setUrlConnectionImpl(jt.a aVar) {
        this.f = aVar;
    }

    protected b getDegradeType() {
        return this.l;
    }

    public void setDegradeType(b bVar) {
        this.l = bVar;
    }

    protected int getReal_max_timeout() {
        return this.b;
    }

    public void setReal_max_timeout(int i) {
        this.b = i;
    }

    protected boolean isHostToIP() {
        return this.g;
    }

    public void setHostToIP(boolean z) {
        this.g = z;
    }

    protected String getNon_degrade_final_Host() {
        return this.h;
    }

    public void setNon_degrade_final_Host(String str) {
        this.h = str;
    }

    protected boolean isIPV6Request() {
        return this.i;
    }

    public void setIPV6Request(boolean z) {
        this.i = z;
    }

    protected String parseSdkNameFromRequest() {
        String str;
        try {
            str = getSDKName();
            try {
                if (TextUtils.isEmpty(str)) {
                    if (this.f1246a) {
                        str = parseSDKNameFromPlatInfo(((ju) this).g());
                    } else {
                        str = parseSdkNameFromHeader(getRequestHead());
                    }
                }
            } catch (Throwable th) {
                th = th;
                is.a(th, "ht", "pnfr");
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
            str = "";
        }
        return str;
    }

    protected String parseSdkNameFromHeader(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            if (map.containsKey("platinfo")) {
                return parseSDKNameFromPlatInfo(map.get("platinfo"));
            }
            return null;
        } catch (Throwable th) {
            is.a(th, "ht", "pnfh");
            return null;
        }
    }

    protected String parseSDKNameFromPlatInfo(String str) {
        String str2;
        String str3 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split("&");
                if (split.length > 1) {
                    int length = split.length;
                    int i = 0;
                    String str4 = "";
                    while (true) {
                        if (i >= length) {
                            str2 = "";
                            break;
                        }
                        str2 = split[i];
                        if (str2.contains("sdkversion")) {
                            str4 = str2;
                        }
                        if (str2.contains("product")) {
                            break;
                        }
                        i++;
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        String[] split2 = str2.split("=");
                        if (split2.length > 1) {
                            str3 = split2[1].trim();
                            if (!TextUtils.isEmpty(str4) && TextUtils.isEmpty(io.a(str3))) {
                                String[] split3 = str4.split("=");
                                if (split3.length > 1) {
                                    io.a(str3, split3[1].trim());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            is.a(th, "ht", "pnfp");
        }
        return str3;
    }

    public String getSDKName() {
        return "";
    }

    protected String getIPDNSName() {
        return "";
    }
}
