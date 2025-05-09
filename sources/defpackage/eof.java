package defpackage;

import com.huawei.health.musicservice.api.IAudioPlayer;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class eof {

    /* renamed from: a, reason: collision with root package name */
    private int f12119a;
    private int c;
    private volatile enq d;
    private final List<enq> j = new ArrayList();
    private final List<enq> h = new ArrayList();
    private boolean b = false;
    private boolean e = false;
    private boolean f = false;

    public enq e() {
        return this.d;
    }

    public enq a(int i) {
        if (i < 0 || i > this.j.size() - 1) {
            LogUtil.e("R_TrackProvider", "getItemByIndex failed, index out of range, index: " + i + ", size: " + this.j.size());
            return null;
        }
        return this.j.get(i);
    }

    public enq c(boolean z) {
        if (this.j.isEmpty()) {
            LogUtil.a("R_TrackProvider", "getNextTrack failed, list is empty, please check if you have called setPlayList(), or track has all been removed...");
            return null;
        }
        if (this.d == null) {
            this.d = (this.b ? this.h : this.j).get(0);
            return this.d;
        }
        if (this.b) {
            if (this.c < this.h.size() - 1) {
                return this.h.get(this.c + 1);
            }
            return this.h.get(0);
        }
        if (this.f) {
            if (this.f12119a < this.j.size() - 1) {
                return this.j.get(this.f12119a + 1);
            }
            return this.j.get(0);
        }
        if (this.e) {
            if (z) {
                return null;
            }
            if (this.f12119a < this.j.size() - 1) {
                return this.j.get(this.f12119a + 1);
            }
            return this.j.get(0);
        }
        if (this.f12119a < this.j.size() - 1) {
            return this.j.get(this.f12119a + 1);
        }
        if (this.f12119a == this.j.size() - 1 && z) {
            return null;
        }
        return this.j.get(0);
    }

    public enq d() {
        if (this.j.isEmpty()) {
            LogUtil.a("R_TrackProvider", "getPreviousTrack failed, list is empty, please check if you have called setPlayList(), or track has all been removed...");
            return null;
        }
        if (this.d == null) {
            this.d = (this.b ? this.h : this.j).get(0);
            return this.d;
        }
        if (this.j.size() == 1) {
            return this.d;
        }
        if (this.b) {
            int i = this.c;
            if (i > 0) {
                return this.h.get(i - 1);
            }
            List<enq> list = this.h;
            return list.get(list.size() - 1);
        }
        int i2 = this.f12119a;
        if (i2 > 0 && i2 <= this.j.size()) {
            return this.j.get(this.f12119a - 1);
        }
        List<enq> list2 = this.j;
        return list2.get(list2.size() - 1);
    }

    public void b(enq enqVar) {
        if (enqVar == null) {
            return;
        }
        this.d = enqVar;
        int i = 0;
        if (this.b) {
            while (i < this.h.size()) {
                if (enqVar.equals(this.h.get(i))) {
                    this.c = i;
                    this.f12119a = this.j.indexOf(enqVar);
                    return;
                }
                i++;
            }
            return;
        }
        while (i < this.j.size()) {
            if (enqVar.equals(this.j.get(i))) {
                LogUtil.c("R_TrackProvider", "currentSequenceIndex updated, " + this.f12119a + " -> " + i);
                this.f12119a = i;
                return;
            }
            i++;
        }
    }

    public void b(List<enq> list) {
        this.j.addAll(list);
        a();
    }

    public boolean c(enq enqVar) {
        this.j.remove(enqVar);
        this.h.remove(enqVar);
        if (this.j.size() == 0) {
            this.d = null;
            return true;
        }
        if (enqVar != null && enqVar.equals(this.d)) {
            if (this.b) {
                this.d = this.c >= this.h.size() ? this.h.get(0) : this.h.get(this.c);
            } else {
                this.d = this.f12119a >= this.j.size() ? this.j.get(0) : this.j.get(this.f12119a);
            }
        } else {
            b(this.d);
        }
        return false;
    }

    public void b() {
        this.j.clear();
        this.h.clear();
    }

    public void d(IAudioPlayer.PlayMode playMode) {
        this.e = false;
        this.b = false;
        this.f = false;
        ReleaseLogUtil.b("R_TrackProvider", "parse play mode, current: " + playMode);
        int i = AnonymousClass1.b[playMode.ordinal()];
        if (i == 1) {
            this.b = true;
            a();
        } else if (i == 2) {
            this.e = true;
        } else {
            if (i != 3) {
                return;
            }
            this.f = true;
        }
    }

    /* renamed from: eof$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[IAudioPlayer.PlayMode.values().length];
            b = iArr;
            try {
                iArr[IAudioPlayer.PlayMode.RANDOM_CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[IAudioPlayer.PlayMode.PLAY_ONCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[IAudioPlayer.PlayMode.SEQUENCE_CIRCLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[IAudioPlayer.PlayMode.SEQUENCE_PLAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public List<enq> c() {
        return this.j;
    }

    private void a() {
        if (this.j.isEmpty()) {
            ReleaseLogUtil.a("R_TrackProvider", "generate random list failed, source list is empty");
            return;
        }
        this.h.clear();
        this.h.addAll(this.j);
        Collections.shuffle(this.h);
        this.c = 0;
        if (this.d != null) {
            this.h.remove(this.d);
            this.h.add(0, this.d);
        }
    }
}
