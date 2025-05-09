package defpackage;

import java.util.List;

/* loaded from: classes3.dex */
public class cgv {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f712a;
    private long b;
    private long d;

    public long e() {
        return ((Long) jdy.d(Long.valueOf(this.d))).longValue();
    }

    public void a(long j) {
        this.d = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public long c() {
        return ((Long) jdy.d(Long.valueOf(this.b))).longValue();
    }

    public void b(long j) {
        this.b = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public List<Integer> a() {
        return (List) jdy.d(this.f712a);
    }

    public void e(List<Integer> list) {
        this.f712a = (List) jdy.d(list);
    }
}
