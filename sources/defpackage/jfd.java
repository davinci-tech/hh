package defpackage;

/* loaded from: classes5.dex */
public class jfd {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13781a;
    private boolean b;
    private boolean c;
    private int d;
    private String e;
    private String f;
    private String h;
    private String j;

    public String d() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String b() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public String c() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public String e() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    public boolean f() {
        return this.c;
    }

    public boolean a() {
        return this.c;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public boolean g() {
        return this.b;
    }

    public void d(boolean z) {
        this.b = z;
    }

    public boolean h() {
        return this.f13781a;
    }

    public void a(boolean z) {
        this.f13781a = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("MigrateTable{mId=");
        stringBuffer.append(this.d).append(", mOriginalHuid='").append(this.e).append("', mToHuid='").append(this.h).append("', mOriginalSt='").append(this.j).append("', mTime='").append(this.f).append("', mIsSendCommandFinished=").append(this.c).append(", mIsCloudFinished=").append(this.b).append(", mIsLocalFinished=").append(this.f13781a).append('}');
        return stringBuffer.toString();
    }
}
