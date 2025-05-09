package defpackage;

/* loaded from: classes7.dex */
public class rkc {

    /* renamed from: a, reason: collision with root package name */
    private int f16795a;
    private int b;
    private String c;
    private boolean d;
    private boolean e;
    private int f;
    private boolean g;
    private String j;

    public int e() {
        return this.f;
    }

    public void d(int i) {
        this.f = i;
    }

    public int a() {
        return this.f16795a;
    }

    public void e(int i) {
        this.f16795a = i;
    }

    public String c() {
        String str = this.c;
        return str == null ? "" : str;
    }

    public void a(String str) {
        this.c = str;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public String b() {
        String str = this.j;
        return str == null ? "" : str;
    }

    public void e(String str) {
        this.j = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeviceRecordBean{ itemType=");
        stringBuffer.append(this.f);
        stringBuffer.append(", dataType=").append(this.f16795a);
        stringBuffer.append(", content=").append(this.c);
        stringBuffer.append(", imageId=").append(this.b);
        stringBuffer.append(", title=").append(this.j);
        stringBuffer.append(", isActivityStatistics=").append(this.d);
        stringBuffer.append(", isHealthState=").append(this.g);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public boolean i() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public boolean h() {
        return this.g;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public boolean g() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }
}
