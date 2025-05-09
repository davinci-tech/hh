package defpackage;

import android.content.ContentValues;
import java.util.List;

/* loaded from: classes3.dex */
public class cjv implements Comparable<cjv> {

    /* renamed from: a, reason: collision with root package name */
    private String f754a;
    private ContentValues b;
    private long d;
    private int e;
    private Object f;
    private List<cjv> h;
    private int j;
    private boolean c = false;
    private boolean g = true;

    public boolean j() {
        return this.g;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public boolean g() {
        return this.c;
    }

    public void e(boolean z) {
        this.c = z;
    }

    public Object i() {
        return cpt.d(this.f);
    }

    public void c(Object obj) {
        this.f = cpt.d(obj);
    }

    public ContentValues FT_() {
        return this.b;
    }

    public void FU_(ContentValues contentValues) {
        this.b = contentValues;
    }

    public int a() {
        return ((Integer) cpt.d(Integer.valueOf(this.e))).intValue();
    }

    public void a(int i) {
        this.e = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public long d() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public int b() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    public String e() {
        return this.f754a;
    }

    public void c(String str) {
        this.f754a = str;
    }

    public List<cjv> h() {
        return this.h;
    }

    public void e(List<cjv> list) {
        this.h = list;
    }

    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(cjv cjvVar) {
        if (cjvVar == null) {
            return 1;
        }
        return Long.compare(cjvVar.d(), this.d);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof cjv) && this.d == ((cjv) obj).d();
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "DeviceGroupInfo{object=" + this.f + ", mContentValues=" + this.b + ", mDeviceType=" + this.e + ", line=" + this.c + ", mCompareTime=" + this.d + ", mDisplayType=" + this.j + ", mCommonDeviceName='" + this.f754a + "', mSportDeviceList=" + this.h + '}';
    }
}
