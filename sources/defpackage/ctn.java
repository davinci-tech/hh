package defpackage;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class ctn implements Serializable {
    private static final long serialVersionUID = -6977434421058883312L;

    /* renamed from: a, reason: collision with root package name */
    private String f11468a;
    private String b;
    private String c;
    private String d;
    private int e;
    private String g = "unknown";
    private String h;
    private String i;
    private String j;

    public ctn() {
    }

    public ctn(String str, String str2, int i) {
        this.j = str;
        this.c = str2;
        this.e = i;
    }

    public String g() {
        return (String) cpt.d(this.h);
    }

    public void c(String str) {
        this.h = (String) cpt.d(str);
    }

    public String f() {
        return (String) cpt.d(this.j);
    }

    public void f(String str) {
        this.j = (String) cpt.d(str);
    }

    public String h() {
        return (String) cpt.d(this.i);
    }

    public void i(String str) {
        this.i = (String) cpt.d(str);
    }

    public String c() {
        return (String) cpt.d(this.c);
    }

    public void a(String str) {
        this.c = (String) cpt.d(str);
    }

    public String e() {
        return (String) cpt.d(this.f11468a);
    }

    public void e(String str) {
        this.f11468a = (String) cpt.d(str);
    }

    public String b() {
        return (String) cpt.d(this.d);
    }

    public void d(String str) {
        this.d = (String) cpt.d(str);
    }

    public String d() {
        return (String) cpt.d(this.b);
    }

    public void b(String str) {
        this.b = (String) cpt.d(str);
    }

    public String i() {
        return (String) cpt.d(this.g);
    }

    public void j(String str) {
        this.g = (String) cpt.d(str);
    }

    public int a() {
        return ((Integer) cpt.d(Integer.valueOf(this.e))).intValue();
    }

    public void a(int i) {
        this.e = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ctn)) {
            return false;
        }
        ctn ctnVar = (ctn) obj;
        return (d(ctnVar) || b(ctnVar) || c(ctnVar) || a(ctnVar) || e(ctnVar)) ? false : true;
    }

    private boolean d(ctn ctnVar) {
        return f() == null ? ctnVar.f() != null : !f().equals(ctnVar.f());
    }

    private boolean b(ctn ctnVar) {
        return h() == null ? ctnVar.h() != null : !h().equals(ctnVar.h());
    }

    private boolean c(ctn ctnVar) {
        return e() == null ? ctnVar.e() != null : !e().equals(ctnVar.e());
    }

    private boolean a(ctn ctnVar) {
        return c() == null ? ctnVar.c() != null : !c().equals(ctnVar.c());
    }

    private boolean e(ctn ctnVar) {
        return i() == null ? ctnVar.i() != null : !i().equals(ctnVar.i());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddDeviceInfo{ssid ='");
        stringBuffer.append(cpw.d(this.j)).append("', productId ='");
        stringBuffer.append(cpw.d(this.i)).append("', deviceSn ='");
        stringBuffer.append(cpw.d(this.c)).append("', deviceId ='");
        stringBuffer.append(cpw.d(this.f11468a)).append("', mac ='");
        stringBuffer.append(cpw.d(this.d)).append("', baseUrl ='");
        stringBuffer.append(this.b).append("', sourceType ='");
        stringBuffer.append(this.g).append("', encryptMode =");
        stringBuffer.append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public int hashCode() {
        int hashCode = f() != null ? f().hashCode() : 0;
        int hashCode2 = h() != null ? h().hashCode() : 0;
        int hashCode3 = e() != null ? e().hashCode() : 0;
        return (((((((hashCode * 31) + hashCode2) * 31) + hashCode3) * 31) + (c() != null ? c().hashCode() : 0)) * 31) + (i() != null ? i().hashCode() : 0);
    }
}
