package defpackage;

/* loaded from: classes.dex */
public class msk {

    /* renamed from: a, reason: collision with root package name */
    private String f15149a;
    private String d;

    public String a() {
        return (String) jdy.d(this.d);
    }

    public void d(String str) {
        this.d = (String) jdy.d(str);
    }

    public String b() {
        return (String) jdy.d(this.f15149a);
    }

    public void b(String str) {
        this.f15149a = (String) jdy.d(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("EzPluginFileApplyRule{minAppVersion='");
        stringBuffer.append(this.d);
        stringBuffer.append("', minIndexVersion='").append(this.f15149a).append("'}");
        return stringBuffer.toString();
    }
}
