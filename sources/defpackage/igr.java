package defpackage;

import android.content.ContentValues;

/* loaded from: classes7.dex */
public class igr {

    /* renamed from: a, reason: collision with root package name */
    private long f13373a;
    private int b;
    private long c;
    private int d;
    private int e;
    private int g;
    private igs i;
    private int j;

    public int a() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int f() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public int e() {
        return this.j;
    }

    public void c(int i) {
        this.j = i;
    }

    public igs d() {
        return this.i;
    }

    public void d(igs igsVar) {
        this.i = igsVar;
    }

    public int b() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public void d(long j) {
        this.f13373a = j;
    }

    public long c() {
        return this.c;
    }

    public void b(long j) {
        this.c = j;
    }

    public String toString() {
        return "AuthorizationTable{id=" + this.e + '}';
    }

    public static ContentValues bxg_(igr igrVar) {
        if (igrVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", Integer.valueOf(igrVar.a()));
        contentValues.put("user_id", Integer.valueOf(igrVar.f()));
        contentValues.put("permission_id", Integer.valueOf(igrVar.e()));
        contentValues.put("granted", Integer.valueOf(igrVar.b()));
        contentValues.put("modified_time", Long.valueOf(igrVar.c()));
        return contentValues;
    }
}
