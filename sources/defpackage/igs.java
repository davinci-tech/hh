package defpackage;

import android.content.ContentValues;

/* loaded from: classes7.dex */
public class igs {

    /* renamed from: a, reason: collision with root package name */
    private String f13374a;
    private int b;
    private long c;
    private int d;
    private long e;
    private String g;
    private String i;

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public String e() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public String a() {
        return this.f13374a;
    }

    public void a(String str) {
        this.f13374a = str;
    }

    public long d() {
        return this.c;
    }

    public String j() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public long c() {
        return this.e;
    }

    public String toString() {
        return "PermissionTable{id=" + this.d + '}';
    }

    public static ContentValues bxj_(igs igsVar) {
        if (igsVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("cloud_id", Integer.valueOf(igsVar.b()));
        contentValues.put("scope_name", igsVar.e());
        contentValues.put("permission", igsVar.a());
        contentValues.put("uri", igsVar.j());
        contentValues.put("create_time", Long.valueOf(igsVar.c() <= 0 ? System.currentTimeMillis() : igsVar.c()));
        contentValues.put("modified_time", Long.valueOf(igsVar.d() <= 0 ? System.currentTimeMillis() : igsVar.d()));
        return contentValues;
    }
}
