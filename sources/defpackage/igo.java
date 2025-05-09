package defpackage;

import android.content.ContentValues;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class igo {

    /* renamed from: a, reason: collision with root package name */
    private int f13370a;
    private int b;
    private int c;
    private int d;
    private long e;
    private int f;
    private int g;
    private int h;
    private int i;
    private String j;
    private double n;

    public int a() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int e() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public void d(long j) {
        this.c = HiDateUtil.c(j);
    }

    public int b() {
        return this.f13370a;
    }

    public void c(int i) {
        this.f13370a = i;
    }

    public int f() {
        return this.i;
    }

    public void d(int i) {
        this.i = i;
    }

    public double l() {
        return this.n;
    }

    public void a(double d) {
        this.n = d;
    }

    public int i() {
        return this.h;
    }

    public void j(int i) {
        this.h = i;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public String h() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public int j() {
        return this.g;
    }

    public void h(int i) {
        this.g = i;
    }

    public int g() {
        return this.f;
    }

    public void g(int i) {
        this.f = i;
    }

    public long c() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public String toString() {
        return "DayStatTable{id=" + this.d + ",dt=" + this.c + ",hiHlhTp=" + this.f13370a + ",statTp=" + this.i + ",val=" + HiCommonUtil.d(Double.valueOf(this.n)) + ",who=" + this.h + ",ClntId=" + this.b + ",TZ='" + this.j + "',sync=" + this.f + ",mTm=" + this.e + '}';
    }

    public static ContentValues bxh_(igo igoVar) {
        if (igoVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues(10);
        contentValues.put("date", Integer.valueOf(igoVar.e()));
        contentValues.put("hihealth_type", Integer.valueOf(igoVar.b()));
        contentValues.put("stat_type", Integer.valueOf(igoVar.f()));
        contentValues.put("value", Double.valueOf(igoVar.l()));
        contentValues.put("client_id", Integer.valueOf(igoVar.d()));
        contentValues.put(DBPointCommon.COLUMN_UNIT_ID, Integer.valueOf(igoVar.j()));
        contentValues.put("user_id", Integer.valueOf(igoVar.i()));
        contentValues.put("timeZone", HiDateUtil.d(igoVar.h()));
        contentValues.put("sync_status", Integer.valueOf(igoVar.g()));
        contentValues.put("modified_time", Long.valueOf(igoVar.c() <= 0 ? System.currentTimeMillis() : igoVar.c()));
        return contentValues;
    }

    public static ContentValues bxi_(igo igoVar) {
        ContentValues contentValues = new ContentValues(6);
        contentValues.put("value", Double.valueOf(igoVar.l()));
        contentValues.put(DBPointCommon.COLUMN_UNIT_ID, Integer.valueOf(igoVar.j()));
        contentValues.put("user_id", Integer.valueOf(igoVar.i()));
        contentValues.put("sync_status", Integer.valueOf(igoVar.g()));
        contentValues.put("modified_time", Long.valueOf(igoVar.c()));
        return contentValues;
    }

    public void e(igo igoVar, int i, int i2, boolean z) {
        igoVar.b(i2);
        igoVar.j(i);
        if (z) {
            return;
        }
        igoVar.g(0);
    }

    public List<igo> e(List<igo> list, String str, int i, int i2) {
        for (igo igoVar : list) {
            igoVar.b(i);
            igoVar.b(str);
            igoVar.e(i2);
            igoVar.g(1);
            igoVar.c(20001);
        }
        return list;
    }

    public igo a(int i, double d, int i2) {
        igo igoVar = new igo();
        igoVar.d(i);
        igoVar.a(d);
        igoVar.h(i2);
        return igoVar;
    }
}
