package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kfg {

    /* renamed from: a, reason: collision with root package name */
    private String f14339a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private int g;
    private String h;
    private String i;
    private String j;
    private String k;
    private long l;
    private String m;
    private String n;
    private byte[] o;
    private String r;

    public kfg() {
        q();
    }

    private void q() {
        this.g = 0;
        this.f14339a = "";
        this.k = "SyncPairToken";
        this.n = "syncPairToken";
    }

    public String n() {
        return this.m;
    }

    public boolean m(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TrustPairDevice", "udid is empty");
            return false;
        }
        this.m = str;
        return true;
    }

    public String d() {
        return this.e;
    }

    public boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TrustPairDevice", "deviceType is empty");
            return false;
        }
        this.e = str;
        return true;
    }

    public String j() {
        return this.f;
    }

    public boolean j(String str) {
        if (!TextUtils.isEmpty(str) && str.length() <= 32) {
            this.f = str;
            return true;
        }
        LogUtil.h("TrustPairDevice", "model has wrong value");
        return false;
    }

    public String f() {
        return this.i;
    }

    public boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TrustPairDevice", "manufactory is empty");
            return false;
        }
        this.i = str;
        return true;
    }

    public String i() {
        return this.h;
    }

    public boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TrustPairDevice", "productId is empty");
            return false;
        }
        this.h = str;
        return true;
    }

    public String b() {
        return this.f14339a;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TrustPairDevice", "hiLinkVersion is empty");
            return false;
        }
        this.f14339a = str;
        return true;
    }

    public String m() {
        return this.k;
    }

    public boolean g(String str) {
        if (!TextUtils.isEmpty(str)) {
            if ("SyncPairToken".equals(str)) {
                this.k = str;
                return true;
            }
            LogUtil.h("TrustPairDevice", "service id is does not match");
            return false;
        }
        LogUtil.h("TrustPairDevice", "serviceId has wrong format");
        return false;
    }

    public String k() {
        return this.n;
    }

    public boolean h(String str) {
        if (!TextUtils.isEmpty(str)) {
            if ("syncPairToken".equals(str)) {
                this.n = str;
                return true;
            }
            LogUtil.h("TrustPairDevice", "service type does not match");
            return false;
        }
        LogUtil.h("TrustPairDevice", "serviceType has wrong format");
        return false;
    }

    public byte[] l() {
        LogUtil.a("TrustPairDevice", "getToken:", Integer.valueOf(this.o.length));
        return (byte[]) this.o.clone();
    }

    public boolean e(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("TrustPairDevice", "setToken: token is null");
            this.o = new byte[0];
            return false;
        }
        LogUtil.a("TrustPairDevice", "setToken: token length is ", Integer.valueOf(bArr.length));
        this.o = (byte[]) bArr.clone();
        return true;
    }

    public String h() {
        return this.j;
    }

    public boolean d(String str) {
        this.j = str;
        return true;
    }

    public int c() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public String a() {
        return this.d;
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TrustPairDevice", "deviceName is empty");
            return false;
        }
        this.d = str;
        return true;
    }

    public boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TrustPairDevice", "uuid is empty");
            return false;
        }
        this.r = str;
        return true;
    }

    public String p() {
        return this.r;
    }

    public int g() {
        return this.g;
    }

    public void e(int i) {
        this.g = i;
    }

    public long o() {
        return this.l;
    }

    public boolean a(Long l) {
        if (l == null) {
            LogUtil.h("TrustPairDevice", "setPairState: pairState is null");
            return false;
        }
        this.l = l.longValue();
        return true;
    }

    public String e() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }
}
