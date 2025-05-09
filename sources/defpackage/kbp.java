package defpackage;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class kbp {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<Integer, String> f14260a;
    private long b;
    private long c;
    private long d;
    private HashMap<Integer, Double> e;

    public long e() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long a() {
        return this.c;
    }

    public void d(long j) {
        this.c = j;
    }

    public long b() {
        return this.d;
    }

    public void e(long j) {
        this.d = j;
    }

    public HashMap<Integer, String> d() {
        return this.f14260a;
    }

    public void a(HashMap<Integer, String> hashMap) {
        this.f14260a = hashMap;
    }

    public HashMap<Integer, Double> c() {
        return this.e;
    }

    public void c(HashMap<Integer, Double> hashMap) {
        this.e = hashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{mStartTime:");
        sb.append(this.b);
        sb.append(",mEndTime:");
        sb.append(this.c);
        sb.append(",mModifyTime:");
        sb.append(this.d);
        sb.append(",mFieldsValueData:");
        HashMap<Integer, Double> hashMap = this.e;
        sb.append(hashMap == null ? null : hashMap.toString());
        sb.append(",mFieldsMetaData:");
        HashMap<Integer, String> hashMap2 = this.f14260a;
        sb.append(hashMap2 != null ? hashMap2.toString() : null);
        sb.append("}");
        return sb.toString();
    }
}
