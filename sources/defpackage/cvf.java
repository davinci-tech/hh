package defpackage;

/* loaded from: classes3.dex */
public class cvf {
    private String d;
    private String e;

    public String b() {
        return (String) jdy.d(this.d);
    }

    public void b(String str) {
        this.d = (String) jdy.d(str);
    }

    public String a() {
        return (String) jdy.d(this.e);
    }

    public void c(String str) {
        this.e = (String) jdy.d(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("EzPluginFileApplyRule{minAppVersion='");
        stringBuffer.append(this.d);
        stringBuffer.append("', minIndexVersion='").append(this.e).append("'}");
        return stringBuffer.toString();
    }
}
