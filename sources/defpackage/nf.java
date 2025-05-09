package defpackage;

import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.apprichtap.haptic.b.a.c;
import com.apprichtap.haptic.player.PlayerEventCallback;
import com.apprichtap.haptic.sync.SyncCallback;

/* loaded from: classes8.dex */
public class nf {

    /* renamed from: a, reason: collision with root package name */
    public int f15281a;
    public long b;
    public String c;
    public int d;
    public int e;
    public na f;
    public int g;
    public int h;
    public SyncCallback i;
    public int j;
    public String k;
    public String l;
    public boolean m;
    public float n = 1.0f;
    public int o;
    public String p;
    public int q;
    public int r;
    public int s;
    public String t;
    public PlayerEventCallback u;
    private int y;

    private boolean e(int i) {
        return i >= 0 && i <= 9;
    }

    public String toString() {
        return "CurrentPlayingHeInfo{mHeString='" + this.c + "', mStartTime=" + this.b + ", mLoop=" + this.e + ", mAmplitude=" + this.f15281a + ", mFreq=" + this.d + ", mHeRoot=" + this.f + ", mSyncCallback=" + this.i + ", mStartPosition=" + this.j + ", mStatus:" + this.y + ", mSpeedMultiple:" + this.n + '}';
    }

    public void c() {
        Log.d("CurrentPlayingInfo", "reset!");
        this.c = null;
        this.b = 0L;
        this.e = 0;
        this.f15281a = 255;
        this.d = 0;
        this.g = 0;
        this.f = null;
        this.i = null;
        this.j = 0;
        this.y = 0;
        this.n = 1.0f;
        this.l = null;
        this.o = 0;
        this.p = null;
        this.q = 0;
        this.r = 0;
        this.t = null;
        String str = this.k;
        if (str != null && str.length() != 0) {
            mx.d(this.k);
        }
        this.k = null;
    }

    public void b() {
        String str = this.k;
        if (str != null && str.length() != 0) {
            mx.d(this.k);
        }
        String str2 = Process.myTid() + "," + SystemClock.elapsedRealtime();
        this.k = str2;
        mx.a(str2);
    }

    public int d() {
        return this.y;
    }

    public void e(int i, int i2) {
        String str;
        Log.d("CurrentPlayingInfo", "changePlayerStatus, from:" + this.y + ",to:" + i + ", code:" + i2);
        if (!c(this.y, i)) {
            str = "changePlayerStatus, invalid transition, from " + this.y + " to " + i;
        } else {
            if (i != this.y || i2 == 0) {
                this.y = i;
                PlayerEventCallback playerEventCallback = this.u;
                if (playerEventCallback != null) {
                    playerEventCallback.onPlayerStateChanged(i);
                    if (2 == this.y) {
                        this.u.onError(i2);
                        return;
                    }
                    return;
                }
                return;
            }
            str = "changePlayerStatus, needn't update";
        }
        Log.w("CurrentPlayingInfo", str);
    }

    public int a() {
        na naVar = this.f;
        if (naVar == null) {
            return -1;
        }
        if (2 == naVar.a()) {
            return this.f.b.size();
        }
        if (1 == this.f.a()) {
            return 1;
        }
        Log.w("CurrentPlayingInfo", "getPatternCount(), invalid HE version!");
        return -1;
    }

    private boolean c(int i, int i2) {
        if (!e(i) || !e(i2)) {
            return false;
        }
        switch (i) {
            case 0:
                if (i2 == 0 || 2 == i2 || 1 == i2 || 3 == i2) {
                }
                break;
            case 1:
                if (1 == i2) {
                }
                break;
            case 2:
                if (2 == i2) {
                }
                break;
            case 3:
                if (i2 == 0 || 2 == i2 || 1 == i2 || 3 == i2 || 4 == i2 || 5 == i2) {
                }
                break;
            case 4:
                if (2 == i2 || i2 == 0 || 1 == i2 || 4 == i2 || 5 == i2) {
                }
                break;
            case 5:
                if (2 == i2 || i2 == 0 || 1 == i2 || 5 == i2 || 6 == i2 || 8 == i2) {
                }
                break;
            case 6:
            case 7:
                if (2 == i2 || i2 == 0 || 1 == i2 || 6 == i2 || 8 == i2 || 7 == i2 || 9 == i2) {
                }
                break;
            case 8:
                if (2 == i2 || i2 == 0 || 1 == i2 || 4 == i2 || 5 == i2 || 8 == i2) {
                }
                break;
            case 9:
                if (2 == i2 || i2 == 0 || 1 == i2 || 6 == i2 || 7 == i2 || 9 == i2) {
                }
                break;
        }
        return false;
    }

    public static boolean e(c cVar) {
        return c.a(cVar);
    }
}
