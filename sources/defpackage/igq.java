package defpackage;

import android.content.ContentValues;

/* loaded from: classes7.dex */
public class igq {

    /* renamed from: a, reason: collision with root package name */
    private int f13372a;
    private long b;
    private int c;
    private int d;
    private long e;

    public int e() {
        return this.f13372a;
    }

    public void a(int i) {
        this.f13372a = i;
    }

    public long b() {
        return this.b;
    }

    public void d(long j) {
        this.b = j;
    }

    public long a() {
        return this.e;
    }

    public void c(long j) {
        this.e = j;
    }

    public int d() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int c() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public String toString() {
        return "SyncAnchorTable{syncType=" + this.f13372a + ", cloudCode=" + this.b + ", syncTypeVersion=" + this.e + ", syncTime=" + this.c + '}';
    }

    public static ContentValues bxk_(igq igqVar) {
        if (igqVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("main_user_id", Integer.valueOf(igqVar.c()));
        contentValues.put("cloud_code", Long.valueOf(igqVar.b()));
        contentValues.put("sync_data_type", Integer.valueOf(igqVar.e()));
        contentValues.put("sync_type_version", Long.valueOf(igqVar.a()));
        contentValues.put("sync_type_time", Integer.valueOf(igqVar.d()));
        contentValues.put("modify_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }
}
