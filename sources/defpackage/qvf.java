package defpackage;

import android.util.Pair;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class qvf {

    /* renamed from: a, reason: collision with root package name */
    private transient Pair<Integer, Integer> f16607a;
    private transient Pair<Integer, Integer> b;

    @SerializedName("currentWindow")
    private d e;

    @SerializedName("fastingLitePhases")
    private List<d> g;

    @SerializedName("endTime")
    private long h;

    @SerializedName("fastingLiteMode")
    private quv i;
    private volatile transient boolean n;
    private transient d o;
    private transient d s;

    @SerializedName("startTime")
    private long t;

    @SerializedName("note")
    private String r = "";

    @SerializedName("isRunning")
    private volatile boolean k = false;

    @SerializedName("eatingWindow")
    private long c = 0;

    @SerializedName("fastingWindow")
    private long j = 0;

    @SerializedName("baseTime")
    private long d = 0;

    @SerializedName("lastTunnedTime")
    private long m = 0;
    private volatile transient List<d> f = new ArrayList();
    private final Object l = new Object();

    qvf() {
    }

    public qvf(quv quvVar) {
        this.i = quvVar;
        p();
    }

    public boolean p() {
        if (this.n) {
            LogUtil.a("FastingLiteTask", "fasting lite task is starting");
            return false;
        }
        this.n = true;
        quv quvVar = this.i;
        if (quvVar == null) {
            LogUtil.h("FastingLiteTask", "fasting lite mode is null");
            this.n = false;
            return false;
        }
        qvc b = quvVar.b();
        if (b == null) {
            LogUtil.h("FastingLiteTask", "start task fail, illegal fastingLite mode");
            this.k = false;
            this.n = false;
            qlc.b().j();
            return false;
        }
        this.d = b.b() * 1000;
        this.c = b.c() * 1000;
        this.j = (b.a() * 1000) - this.c;
        this.b = b.dJb_();
        this.f16607a = b.dJa_();
        if (this.c == 0 || this.j == 0) {
            LogUtil.h("FastingLiteTask", "mEatingWindow or mFastingWindow = 0");
            this.k = false;
            this.n = false;
            qlc.b().j();
            return false;
        }
        z();
        this.k = true;
        LogUtil.a("FastingLiteTask", "start task complete, update task");
        v();
        a();
        LogUtil.a("FastingLiteTask", "start task at ", DateFormatUtil.b(this.t, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), ",head ", this.o, ",tail ", this.s, ",mode ", this.i);
        this.n = false;
        return true;
    }

    private void z() {
        synchronized (this.l) {
            ab();
            if (koq.c(this.f)) {
                LogUtil.h("FastingLiteTask", "init head and tail from mFastingLiteWindows");
                ad();
            } else {
                if (this.e != null) {
                    this.e = null;
                }
                LogUtil.a("FastingLiteTask", "start generateNewWindow");
                aa();
            }
        }
    }

    private void ab() {
        if (koq.b(this.f) && koq.c(this.g)) {
            LogUtil.a("FastingLiteTask", "mFastingLiteNewWindows merge mFastingLiteWindows");
            this.f.addAll(this.g);
        } else {
            LogUtil.a("FastingLiteTask", "has merge complete");
        }
    }

    public void s() {
        ag();
        v();
        a();
    }

    public void d(long j, long j2) {
        long j3 = j * 1000;
        this.c = j3;
        this.j = (1000 * j2) - j3;
        quv quvVar = this.i;
        if (quvVar == null || quvVar.b() == null) {
            return;
        }
        this.i.b().c(j);
        this.i.b().a(j2);
        this.b = this.i.b().dJb_();
        this.f16607a = this.i.b().dJa_();
    }

    public void a(long j) {
        long j2 = j / 1000;
        if (this.d == j2) {
            LogUtil.h("FastingLiteTask", "baseTime is same to currentBaseTime");
            return;
        }
        LogUtil.a("FastingLiteTask", "setBaseTime ", Long.valueOf(j));
        quv quvVar = this.i;
        if (quvVar == null || quvVar.b() == null) {
            LogUtil.h("FastingLiteTask", "illegal state, can not set base time");
        } else {
            this.i.b().b(j2);
            b(j);
        }
    }

    public void a() {
        if (!this.k && this.e == null) {
            LogUtil.h("FastingLiteTask", "setBaseTime fail illegal state");
            return;
        }
        ArrayList arrayList = new ArrayList();
        d dVar = this.e;
        if (dVar != null && !dVar.g()) {
            arrayList.add(new quy(this.e.h / 1000, this.e.c / 1000));
        }
        quz.e().d(arrayList, this.i.b());
    }

    public void t() {
        this.k = false;
        long currentTimeMillis = System.currentTimeMillis();
        long e = qve.e(this.i.b());
        if (e > 0) {
            currentTimeMillis = Math.min(e, currentTimeMillis);
        }
        d dVar = this.s;
        if (dVar != null) {
            dVar.d(currentTimeMillis, true);
        }
        ai();
        this.h = currentTimeMillis;
        LogUtil.a("FastingLiteTask", "stop task time ", Long.valueOf(currentTimeMillis));
        quz.e().b();
    }

    private void ai() {
        synchronized (this.l) {
            for (d dVar : this.f) {
                if (dVar.f16608a) {
                    LogUtil.a("FastingLiteTask", "setSaveWindowTime current window is new window");
                } else {
                    if (this.t != dVar.b()) {
                        dVar.a(this.t, true);
                    }
                    if (dVar.d() == 0 && dVar.e() == 0) {
                        dVar.d(dVar.f());
                        dVar.e(dVar.f());
                    }
                    dVar.h();
                }
            }
        }
    }

    public boolean q() {
        return this.s != null && this.k && this.s.c >= this.m;
    }

    public boolean k() {
        quv h = h();
        if (h == null) {
            return false;
        }
        long e = qve.e(h.b());
        return e > 0 && System.currentTimeMillis() >= e;
    }

    public boolean r() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public long b() {
        if (!this.k) {
            LogUtil.h("FastingLiteTask", "getCurrentPhaseStartTime task not running");
            return -1L;
        }
        d dVar = this.e;
        if (dVar != null) {
            return dVar.h;
        }
        return -1L;
    }

    public long c() {
        if (!this.k) {
            LogUtil.h("FastingLiteTask", "getCurrentPhaseEndTime task not running");
            return -1L;
        }
        d dVar = this.e;
        if (dVar == null) {
            return -1L;
        }
        return dVar.c;
    }

    public long l() {
        if (!this.k) {
            LogUtil.h("FastingLiteTask", "getPrePhaseStartTime task not running");
            return -1L;
        }
        d dVar = this.s;
        if (dVar == null || dVar.l == null) {
            d dVar2 = this.s;
            if (dVar2 != null) {
                return jdl.t(dVar2.h);
            }
            return -1L;
        }
        return this.s.l.h;
    }

    public void d(long j) {
        LogUtil.h("FastingLiteTask", "resetCurrentPhaseStartTime ", Long.valueOf(j));
        if (!this.k) {
            LogUtil.h("FastingLiteTask", "resetCurrentPhase task not running");
            return;
        }
        v();
        d dVar = this.s;
        if (dVar == null || this.o == null) {
            LogUtil.h("FastingLiteTask", "resetCurrentPhaseStartTime tail or mHead is null");
            return;
        }
        dVar.c(j, true);
        if (this.s.l == null) {
            LogUtil.h("FastingLiteTask", "reset start time");
            this.t = j;
            this.o.c(j, true);
            return;
        }
        this.s.l.d(j, true);
    }

    public quv h() {
        return this.i;
    }

    public String j() {
        return this.r;
    }

    public void e(String str) {
        this.r = str;
    }

    public long m() {
        return this.t;
    }

    public long i() {
        return this.h;
    }

    public int e() {
        return (int) ((this.c / 1000) / 3600);
    }

    public int f() {
        return (int) ((this.j / 1000) / 3600);
    }

    private void ad() {
        this.o = this.f.get(0);
        this.k = true;
        this.s = this.f.get(this.f.size() - 1);
        d dVar = this.o;
        for (int i = 1; i < this.f.size(); i++) {
            if (this.f.get(i) != null) {
                d dVar2 = this.f.get(i);
                dVar.g = dVar2;
                dVar2.l = dVar;
                dVar = dVar2;
            }
        }
        this.s = dVar;
    }

    private void aa() {
        if (this.t <= 0) {
            LogUtil.a("FastingLiteTask", "start task at current time");
            this.t = System.currentTimeMillis();
        }
        if (this.e == null) {
            long e = jdl.e(this.t, ((Integer) this.b.first).intValue(), ((Integer) this.b.second).intValue());
            long e2 = jdl.e(this.t, ((Integer) this.f16607a.first).intValue(), ((Integer) this.f16607a.second).intValue());
            long j = this.t;
            boolean z = e <= j && j < e2;
            long e3 = j > e ? jdl.e(jdl.y(e), ((Integer) this.b.first).intValue(), ((Integer) this.b.second).intValue()) : e;
            if (z) {
                e = e2;
            } else if (this.t >= e) {
                e = jdl.e(jdl.y(e), ((Integer) this.b.first).intValue(), ((Integer) this.b.second).intValue());
            }
            boolean z2 = z;
            long j2 = e3;
            this.o = new d(jdl.t(this.t), this.t, z2, j2);
            d dVar = new d(this.t, e, z2, j2);
            this.s = dVar;
            this.o.g = dVar;
            this.s.l = this.o;
        }
    }

    public boolean n() {
        d dVar = this.e;
        if (dVar == null) {
            LogUtil.h("FastingLiteTask", "isEating mTail is null");
            return false;
        }
        return dVar.e;
    }

    public void a(List<d> list) {
        synchronized (this.l) {
            if (koq.c(this.g)) {
                this.f.addAll(d(list));
            }
            if (koq.c(list)) {
                this.f.addAll(list);
            }
        }
    }

    private List<d> d(List<d> list) {
        if (koq.b(list) || koq.b(this.g)) {
            LogUtil.a("FastingLiteTask", "deleteDuplicatesWindow fastingLiteWindows or mFastingLiteWindows is empty");
            return this.g;
        }
        d dVar = list.get(0);
        int size = this.g.size();
        d dVar2 = null;
        for (int i = 0; i < this.g.size(); i++) {
            d dVar3 = this.g.get(i);
            if (dVar.f() < dVar3.c()) {
                size = i;
                dVar2 = dVar3;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (dVar2 == null) {
            arrayList.addAll(this.g);
        } else if (size > 0) {
            arrayList.addAll(this.g.subList(0, size));
        } else {
            LogUtil.a("FastingLiteTask", "deleteDuplicatesWindow duplicatesWindowPosition == 0");
        }
        return arrayList;
    }

    public List<d> g() {
        ArrayList arrayList;
        synchronized (this.l) {
            arrayList = new ArrayList(this.f);
        }
        return arrayList;
    }

    public List<d> y() {
        LogUtil.a("FastingLiteTask", "start toRecord");
        w();
        if (this.o == null) {
            LogUtil.a("FastingLiteTask", "toRecord mHead is null");
            this.s = null;
            return this.f;
        }
        u();
        long j = 0;
        for (d dVar = this.o; dVar != null; dVar = dVar.g) {
            this.f.add(dVar);
            this.s = dVar;
            if (dVar.d() > j) {
                j = this.s.d();
            }
        }
        ae();
        e(j);
        return this.f;
    }

    private void u() {
        long f = this.o.f();
        this.t = f;
        if (f != this.o.d()) {
            this.o.d(this.t);
            this.o.e(this.t);
        }
    }

    private void e(long j) {
        if (koq.b(this.f)) {
            LogUtil.a("FastingLiteTask", "setSaveWindowTime mFastingLiteNewWindows is empty");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis <= j) {
            currentTimeMillis = j + 1;
        }
        d dVar = null;
        for (d dVar2 : this.f) {
            if (dVar2.i()) {
                LogUtil.a("FastingLiteTask", "setSaveWindowTime current window is old");
            } else {
                if (this.t != dVar2.b()) {
                    dVar2.a(this.t, true);
                }
                if (dVar2.d() == 0 && dVar2.e() == 0) {
                    dVar2.d(currentTimeMillis);
                    dVar2.e(currentTimeMillis);
                }
                if (dVar != null && dVar.c() < dVar2.f() && dVar.a() != dVar2.f()) {
                    dVar.b(dVar2.f(), true);
                }
                currentTimeMillis++;
                dVar = dVar2;
            }
        }
    }

    private void ae() {
        if (this.f.size() < 1) {
            LogUtil.h("FastingLiteTask", "mFastingLiteNewWindows size is less 1");
            return;
        }
        d dVar = this.f.get(this.f.size() - 1);
        if (dVar != null && dVar.i()) {
            dVar.h();
            dVar.k();
        }
        if (this.f.size() < 2) {
            LogUtil.a("FastingLiteTask", "mFastingLiteNewWindows size is less 2");
            return;
        }
        d dVar2 = this.f.get(this.f.size() - 2);
        if (dVar2 == null || !dVar2.i()) {
            return;
        }
        dVar2.h();
        dVar2.k();
    }

    private void w() {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.clear();
    }

    public void v() {
        d dVar;
        synchronized (this.l) {
            d dVar2 = this.s;
            if (koq.b(this.f)) {
                d dVar3 = this.e;
                if (dVar3 != null) {
                    LogUtil.a("FastingLiteTask", "updateTask old mCurrentWindow ", dVar3.toString());
                    dVar2 = this.e;
                    this.o = dVar2;
                    this.s = dVar2;
                } else {
                    LogUtil.a("FastingLiteTask", "mCurrentWindow is null");
                    dVar2 = this.o;
                }
            }
            x();
            d dVar4 = this.s;
            if (dVar4 != null) {
                d dVar5 = new d(dVar4.h, this.s.c, this.s.e, this.s.d);
                this.e = dVar5;
                LogUtil.a("FastingLiteTask", "updateTask new mCurrentWindow ", dVar5.toString());
            }
            d(dVar2);
            if (koq.b(this.f) && (dVar = this.o) != null) {
                this.o = dVar.g;
            }
            y();
        }
    }

    public void b(long j) {
        if (this.e == null) {
            LogUtil.h("FastingLiteTask", "finnedTunned mCurrentWindow is null");
            return;
        }
        this.d = j;
        if (this.i.b() != null) {
            this.i.b().b(this.d / 1000);
            this.b = this.i.b().dJb_();
            this.f16607a = this.i.b().dJa_();
        }
        long ac = ac();
        LogUtil.a("FastingLiteTask", "adjust base ", Long.valueOf(j), "next eatingWindow start", Long.valueOf(ac));
        d dVar = this.s;
        if (dVar != null) {
            if (dVar.c > ac) {
                this.s.d(ac, true);
            }
            this.s.b(ac, true);
        }
        if (this.e.c > ac) {
            this.e.c = ac;
        }
        this.e.d = ac;
    }

    private long ac() {
        long currentTimeMillis = System.currentTimeMillis();
        long e = jdl.e(currentTimeMillis, ((Integer) this.b.first).intValue(), ((Integer) this.b.second).intValue());
        return currentTimeMillis > e ? jdl.e(jdl.y(currentTimeMillis), ((Integer) this.b.first).intValue(), ((Integer) this.b.second).intValue()) : e;
    }

    private void ag() {
        if (this.s == null) {
            LogUtil.a("FastingLiteTask", "finnedTunned tail is null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.s.d(currentTimeMillis, true);
        d dVar = new d(currentTimeMillis, this.s.d, !this.s.g(), this.s.d);
        this.s.g = dVar;
        dVar.l = this.s;
        this.s = dVar;
        if (this.m < dVar.d) {
            this.m = this.s.d;
        } else {
            this.m = this.s.d + 1;
        }
    }

    private d a(d dVar) {
        long e;
        if (dVar.c < dVar.d) {
            long j = dVar.c + (dVar.g() ? this.j : this.c);
            if (dVar.g()) {
                e = jdl.e(j, ((Integer) this.b.first).intValue(), ((Integer) this.b.second).intValue());
            } else {
                e = jdl.e(j, ((Integer) this.f16607a.first).intValue(), ((Integer) this.f16607a.second).intValue());
            }
            long min = Math.min(e, dVar.d);
            LogUtil.a("FastingLiteTask", "nextWindow start ", DateFormatUtil.b(dVar.c, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), ",end ", DateFormatUtil.b(min, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), ",next eating window ", DateFormatUtil.b(dVar.d, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
            return new d(dVar.c, min, !dVar.g(), dVar.d);
        }
        if (dVar.c > dVar.d) {
            dVar.d(dVar.d, true);
        }
        long j2 = dVar.d;
        return new d(j2, jdl.e(this.c + j2, ((Integer) this.f16607a.first).intValue(), ((Integer) this.f16607a.second).intValue()), true, jdl.e(jdl.y(j2), ((Integer) this.b.first).intValue(), ((Integer) this.b.second).intValue()));
    }

    private void x() {
        if (this.s == null) {
            LogUtil.h("FastingLiteTask", "mTail is null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("FastingLiteTask", "completePreviousWindows before ", DateFormatUtil.b(currentTimeMillis, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), ",", this.s);
        if (this.s.h > currentTimeMillis) {
            LogUtil.h("FastingLiteTask", "start time can not greater than current time, need look back");
            while (this.s.h > currentTimeMillis && this.s.l != null) {
                d dVar = this.s.l;
                this.s = dVar;
                dVar.g = null;
            }
            LogUtil.a("FastingLiteTask", "after lock back ", this.s);
            if (this.m > this.s.d) {
                this.m = 0L;
            }
        }
        if (this.b == null || this.f16607a == null) {
            ReleaseLogUtil.b("R_FastingLiteTask", "completePreviousWindows mEatingWindowStartTime or mEatingWindowEndTime is null, return");
            return;
        }
        while (this.s.c <= currentTimeMillis) {
            d a2 = a(this.s);
            this.s.g = a2;
            a2.l = this.s;
            this.s = a2;
        }
        LogUtil.a("FastingLiteTask", "completePreviousWindows ret ", this.s);
    }

    private void d(d dVar) {
        LogUtil.a("FastingLiteTask", "start filter");
        if (dVar == null) {
            LogUtil.h("FastingLiteTask", "preTail is null");
            return;
        }
        ArrayList<Integer> c = qve.c(this.i.b());
        boolean b = koq.b(c);
        long e = qve.e(this.i.b());
        LogUtil.a("FastingLiteTask", "settingEndTime is : ", Long.valueOf(e));
        while (true) {
            if (dVar.g == null || (e >= 0 && dVar.g.h >= e)) {
                break;
            }
            long j = dVar.g.d;
            long j2 = (j - this.j) - this.c;
            if (b || c.contains(Integer.valueOf(jdl.d(j2)))) {
                dVar = dVar.g;
            } else if (dVar.g.e(j2, j)) {
                if (dVar.g.g == null) {
                    dVar.g = null;
                    break;
                } else {
                    dVar.g = dVar.g.g;
                    dVar.g.l = dVar;
                }
            } else {
                dVar = dVar.g;
            }
        }
        if (e <= 0 || dVar.c < e) {
            return;
        }
        dVar.d(e, true);
        dVar.g = null;
    }

    public boolean o() {
        if (this.s != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.s.h <= currentTimeMillis && this.s.c > currentTimeMillis) {
                return false;
            }
        }
        return true;
    }

    public qvf d() {
        qvf qvfVar = new qvf();
        qvfVar.i = this.i;
        qvfVar.t = this.t;
        qvfVar.h = this.h;
        qvfVar.r = this.r;
        qvfVar.g = this.g;
        qvfVar.k = this.k;
        qvfVar.c = this.c;
        qvfVar.j = this.j;
        qvfVar.d = this.d;
        qvfVar.m = this.m;
        if (koq.c(this.g)) {
            qvfVar.e = this.e;
        } else {
            LogUtil.a("FastingLiteTask", "conventToSaveTask old fastingLiteWindows is empty, clear currentWindow");
        }
        return qvfVar;
    }

    public String toString() {
        return "FastingLiteTask{mFastingLiteMode=" + this.i + ", mStartTime=" + this.t + ", mEndTime=" + this.h + ", mTaskNote='" + this.r + "', mFastingLiteWindows=" + this.g + ", mIsRunning=" + this.k + ", mEatingWindow=" + this.c + ", mFastingWindow=" + this.j + ", mBaseTime=" + this.d + ", mNextTunnableTime=" + this.m + ", mCurrentWindow=" + this.e + '}';
    }

    public static class d implements Serializable {
        private static final long serialVersionUID = 10001;

        @SerializedName("endTime")
        private long c;

        @SerializedName("nextEatingWindowStart")
        private long d;

        @SerializedName("isEating")
        private boolean e;
        private transient long f;
        private transient d g;

        @SerializedName("startTime")
        private long h;
        private transient long i;
        private transient long j;
        private transient d l;
        private transient boolean b = false;

        /* renamed from: a, reason: collision with root package name */
        private transient boolean f16608a = true;

        d(long j, long j2) {
            this.f = j;
            this.j = j2;
        }

        d(long j, long j2, boolean z, long j3) {
            this.h = j;
            this.c = j2;
            this.e = z;
            this.d = j3;
        }

        public long f() {
            return this.h;
        }

        public long c() {
            return this.c;
        }

        public boolean g() {
            return this.e;
        }

        boolean j() {
            return this.b;
        }

        boolean i() {
            return !this.f16608a;
        }

        long a() {
            return this.d;
        }

        long d() {
            return this.f;
        }

        long e() {
            return this.j;
        }

        void d(boolean z) {
            this.e = z;
        }

        void c(long j, boolean z) {
            this.h = j;
            this.b = z;
        }

        void d(long j, boolean z) {
            this.c = j;
            this.b = z;
        }

        void b(long j, boolean z) {
            this.d = j;
            this.b = z;
        }

        void a(long j, boolean z) {
            this.i = j;
            this.b = z;
        }

        public long b() {
            return this.i;
        }

        void d(long j) {
            this.f = j;
        }

        void e(long j) {
            this.j = j;
        }

        void k() {
            this.f16608a = true;
        }

        void h() {
            this.b = true;
        }

        public String toString() {
            return "RecordFastingLitePhase{isEating=" + this.e + ", startTime=" + DateFormatUtil.b(this.h, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT) + ", endTime=" + DateFormatUtil.b(this.c, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT) + ",nextEatingWindowStart=" + DateFormatUtil.b(this.d, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT) + ", lasting" + (this.c - this.h) + '}';
        }

        boolean e(long j, long j2) {
            return this.c >= j && this.h <= j2;
        }
    }
}
