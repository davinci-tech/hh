package defpackage;

import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class jvh {

    /* renamed from: a, reason: collision with root package name */
    private static TimerTask f14119a = null;
    private static boolean b = false;
    private static boolean c = true;
    private static Timer d;
    private static Timer e;
    private static TimerTask g;
    private static int j;

    public static void b(int i) {
        j = i;
    }

    public static int i() {
        return j;
    }

    public static void a() {
        j++;
    }

    public static Timer e() {
        return e;
    }

    public static void b(Timer timer) {
        e = timer;
    }

    public static TimerTask c() {
        return g;
    }

    public static void b(TimerTask timerTask) {
        g = timerTask;
    }

    public static void d(boolean z) {
        c = z;
    }

    public static boolean g() {
        return c;
    }

    public static void e(boolean z) {
        b = z;
    }

    public static boolean h() {
        return b;
    }

    public static Timer d() {
        return d;
    }

    public static void e(Timer timer) {
        d = timer;
    }

    public static TimerTask b() {
        return f14119a;
    }

    public static void e(TimerTask timerTask) {
        f14119a = timerTask;
    }

    public static void c(Timer timer, TimerTask timerTask) {
        if (timer != null) {
            timer.cancel();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }
}
