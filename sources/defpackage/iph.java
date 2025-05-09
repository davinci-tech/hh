package defpackage;

import android.os.SystemClock;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iph {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f13530a;
    private String b;
    private String c;
    private long d;
    private long e;

    public String a() {
        return this.b;
    }

    public long b() {
        return this.d;
    }

    public String e() {
        return this.c;
    }

    public List<String> d() {
        return this.f13530a;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(long j) {
        this.d = j;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        LogUtil.c("AtAuthInfo", "Current time = ", Long.valueOf(elapsedRealtime), "in AuthInfo");
        this.e = (j * 1000) + elapsedRealtime;
    }

    public void a(List<String> list) {
        this.f13530a = list;
    }

    public void d(String str) {
        this.c = str;
    }

    public boolean c() {
        long elapsedRealtime = this.e - SystemClock.elapsedRealtime();
        ReleaseLogUtil.e("HiH_AtAuthInfo", "Time left: ", Long.valueOf(elapsedRealtime), "ms");
        return elapsedRealtime < 0;
    }
}
