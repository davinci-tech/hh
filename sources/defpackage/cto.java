package defpackage;

import com.huawei.operation.utils.Constants;

/* loaded from: classes3.dex */
public class cto {

    /* renamed from: a, reason: collision with root package name */
    private Integer f11469a;
    private Integer b;
    private Integer c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private Long j;
    private String m;

    public String j() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public String h() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public String e() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String f() {
        return this.m;
    }

    public void g(String str) {
        this.m = str;
    }

    public String a() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public String g() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public Long i() {
        return this.j;
    }

    public void c(Long l) {
        this.j = l;
    }

    public int d() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public Integer c() {
        return this.b;
    }

    public void a(Integer num) {
        this.b = num;
    }

    public Integer b() {
        return this.f11469a;
    }

    public void c(Integer num) {
        this.f11469a = num;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("VerifyCode2CoapSessionInfo{snAppRandom ='");
        String str = this.f;
        Object obj = Constants.NULL;
        if (str == null) {
            str = Constants.NULL;
        }
        stringBuffer.append(str).append("', snDeviceRandom ='");
        String str2 = this.g;
        if (str2 == null) {
            str2 = Constants.NULL;
        }
        stringBuffer.append(str2).append("', deviceId ='");
        String str3 = this.e;
        if (str3 == null) {
            str3 = Constants.NULL;
        }
        stringBuffer.append(str3).append("', verifyCode ='");
        String str4 = this.m;
        if (str4 == null) {
            str4 = Constants.NULL;
        }
        stringBuffer.append(str4).append("', psk ='");
        String str5 = this.i;
        if (str5 == null) {
            str5 = Constants.NULL;
        }
        stringBuffer.append(str5).append("', modeSupport ='");
        Object obj2 = this.c;
        if (obj2 == null) {
            obj2 = Constants.NULL;
        }
        stringBuffer.append(obj2).append("', sessId ='");
        String str6 = this.h;
        if (str6 == null) {
            str6 = Constants.NULL;
        }
        stringBuffer.append(str6).append("', seq ='");
        Long l = this.j;
        if (l != null) {
            obj = l;
        }
        stringBuffer.append(obj).append("', optSeq =");
        stringBuffer.append(this.d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
