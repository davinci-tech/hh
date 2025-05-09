package defpackage;

import com.huawei.hwidauth.i.b;

/* loaded from: classes5.dex */
public final class krv extends b {
    private static krv v;

    private krv() {
    }

    public static krv o() {
        krv krvVar;
        synchronized (krv.class) {
            if (v == null) {
                v = new krv();
            }
            krvVar = v;
        }
        return krvVar;
    }

    private void q() {
        this.q = this.e + "/CAS/remoteLogin";
        this.b = this.e + "/CAS/mobile/standard/welcome.html";
        this.f6370a = this.e + "/CAS/mobile/standard/wapLogin.html";
        this.c = this.e + "/AMW/portal/userCenter/index.html";
        this.d = this.e + "/AMW/mobile/userCenter/oauth/appList.html";
        this.h = this.e + "/CAS/atRemoteLogin";
        this.i = this.e + "/CAS/weixin/codeAuthorize";
        this.k = this.e + "/CAS/mobile/qrLogin.html?";
        this.g = this.f + "/oauth2/v3/authorize?";
        this.l = this.e + "/CAS/mobile/chkUserPwd.html?";
        this.m = this.j + "/AccountServer/IUserDeviceMng/getDevAuthCode";
        this.n = this.e + "/CAS/mobile/atRemoteLogin.html?";
        this.o = this.e + "/AMW/portal/userCenter/wap_userinfo.html";
        this.p = this.e + "/AMW/mobile/userCenter/privacyCenter/index.html";
        this.r = this.e + "/RealNameW/userCenter/realNameStatus.html";
        this.s = this.e + "/AMW/mobile/children/childInfo.html";
    }

    @Override // com.huawei.hwidauth.i.b
    public void a(String str, String str2, String str3) {
        this.e = str;
        this.j = str2;
        this.f = str3;
        q();
    }

    @Override // com.huawei.hwidauth.i.b
    public String b() {
        return this.f6370a;
    }

    @Override // com.huawei.hwidauth.i.b
    public String k() {
        return this.r;
    }

    @Override // com.huawei.hwidauth.i.b
    public String l() {
        return this.s;
    }

    @Override // com.huawei.hwidauth.i.b
    public String c() {
        return this.c;
    }

    @Override // com.huawei.hwidauth.i.b
    public String d() {
        return this.d;
    }

    @Override // com.huawei.hwidauth.i.b
    public String e() {
        return this.h;
    }

    @Override // com.huawei.hwidauth.i.b
    public String f() {
        return this.g;
    }

    @Override // com.huawei.hwidauth.i.b
    public String g() {
        return this.i;
    }

    public String r() {
        return this.j;
    }

    @Override // com.huawei.hwidauth.i.b
    public String h() {
        return this.k;
    }

    @Override // com.huawei.hwidauth.i.b
    public String i() {
        return this.l;
    }

    @Override // com.huawei.hwidauth.i.b
    public String j() {
        return this.n;
    }

    @Override // com.huawei.hwidauth.i.b
    public String m() {
        return this.o;
    }

    @Override // com.huawei.hwidauth.i.b
    public String n() {
        return this.p;
    }
}
