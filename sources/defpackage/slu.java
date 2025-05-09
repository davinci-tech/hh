package defpackage;

import java.util.GregorianCalendar;
import java.util.TimeZone;

/* loaded from: classes7.dex */
public class slu {

    /* renamed from: a, reason: collision with root package name */
    GregorianCalendar f17113a;

    public slu() {
        this(TimeZone.getDefault());
    }

    public static long c() {
        return new GregorianCalendar().getTimeInMillis();
    }

    private int d() {
        return this.f17113a.get(11);
    }

    private int h() {
        return this.f17113a.get(13);
    }

    private int j() {
        return this.f17113a.get(12);
    }

    public int a() {
        if (this.f17113a.get(0) == 0) {
            return 0;
        }
        return this.f17113a.get(1);
    }

    public int b() {
        return this.f17113a.get(5);
    }

    public void d(long j) {
        if (j == -1) {
            return;
        }
        this.f17113a.setTimeInMillis(j);
    }

    public int e() {
        return this.f17113a.get(2);
    }

    public String toString() {
        return " year:" + a() + " month:" + e() + " monthDay:" + b() + " hour:" + d() + " minute:" + j() + " second:" + h();
    }

    public slu(TimeZone timeZone) {
        this.f17113a = null;
        this.f17113a = new GregorianCalendar(timeZone);
    }
}
