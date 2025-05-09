package defpackage;

import android.content.ContentValues;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;

/* loaded from: classes7.dex */
public class igt {

    /* renamed from: a, reason: collision with root package name */
    private int f13375a;
    private long b;
    private int c;
    private int d;
    private String e;
    private long f;
    private int j;

    public int a() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    public int b() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String d() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public long c() {
        return this.b;
    }

    public void d(long j) {
        this.b = j;
    }

    public int e() {
        return this.f13375a;
    }

    public String toString() {
        return "SyncCacheTable{id=" + this.d + ", dataType=" + this.c + ", data='" + this.e + "', dataTime='" + this.b + ", isDone=" + this.f13375a + ", modifiedTime=" + this.f + '}';
    }

    public static ContentValues bxl_(igt igtVar) {
        if (igtVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsbMapKeyNames.H5_USER_ID, Integer.valueOf(igtVar.a()));
        contentValues.put("dataType", Integer.valueOf(igtVar.b()));
        contentValues.put("data", igtVar.d());
        contentValues.put("dataTime", Long.valueOf(igtVar.c()));
        contentValues.put("isDone", Integer.valueOf(igtVar.e()));
        contentValues.put("modifiedTime", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }
}
