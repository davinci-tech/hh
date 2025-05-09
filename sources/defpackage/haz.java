package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class haz extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13058a;
    private int b;
    private boolean c;
    private ArrayList<LenLatLong> d;
    private int e;
    private MotionPath f;
    private Map<Integer, Float> g;
    private Handler h;
    private Map<Integer, Float> i;
    private MotionPathSimplify j;
    private Set<Integer> k;
    private Set<Integer> l;
    private ArrayList<LatLong> n;
    private ReTrackSimplify o;

    public haz() {
        super("Track_ReTrackProcess");
        this.f = null;
        this.j = null;
        this.g = null;
        this.i = null;
        this.l = new HashSet();
        this.k = new HashSet();
        this.d = null;
        this.n = null;
        this.o = new ReTrackSimplify();
        this.f13058a = false;
        this.c = false;
        this.h = null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        LogUtil.a("Track_ReTrackProcess", "go into ReTrackProcess Thread");
        if (!f() || this.f == null || this.j == null) {
            this.c = true;
            return;
        }
        if (this.o == null) {
            this.o = new ReTrackSimplify();
        }
        this.o.setHiHealthMarkerList(gwe.a((List<MarkPoint>) gwe.a(this.f.requestMarkPointList(), this.b), true));
        this.c = false;
        hav a2 = new hav(this.f, this.j, this.o).c(this.g).a(this.i).e(this.e).a(this.b);
        ArrayList<LatLong> c = a2.c();
        hba e = new hba().c(a2.d()).e(a2.b()).e(a2.a());
        haw a3 = new haw().d(c).a(e.f()).a(e.a());
        a3.c();
        this.n = a3.e();
        Message obtain = Message.obtain();
        obtain.what = 6;
        obtain.obj = Integer.valueOf(this.n.size());
        this.h.sendMessage(obtain);
        this.o.setTrackTotalDistance(a3.b()).setCycles(e.c()).setMinMultiplexField(a3.aYm_()).setMaxMultiplexField(a3.aYl_());
        haq d = new haq().d(this.n);
        d.a();
        this.n = d.e();
        this.o.setAreaNumber(d.b());
        hay d2 = new hay().e(e.d()).b(e.b()).d(e.e());
        d2.c(this.n);
        this.o.setLensTotalDistance(d2.c());
        this.d = d2.e();
        a2.b(this.n.size());
        this.o.setHiHealthMarkerMap(new has().b(this.o.getHiHealthMarkerList(), this.n));
        this.o.syncMaxValue();
        this.c = true;
        LogUtil.a("Track_ReTrackProcess", "go out ReTrackProcess Thread");
    }

    public haz a(MotionPath motionPath) {
        if (motionPath == null) {
            this.f13058a = true;
            return this;
        }
        this.f = motionPath;
        return this;
    }

    public haz b(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            this.f13058a = true;
            return this;
        }
        this.j = motionPathSimplify;
        return this;
    }

    public haz e(Map<Integer, Float> map) {
        if (map == null) {
            return this;
        }
        this.g = map;
        return this;
    }

    public haz b(Map<Integer, Float> map) {
        if (map == null) {
            return this;
        }
        this.i = map;
        return this;
    }

    public haz d(int i) {
        this.e = i;
        return this;
    }

    public haz c(int i) {
        this.k.add(Integer.valueOf(i));
        return this;
    }

    public haz aYn_(Handler handler) {
        this.h = handler;
        return this;
    }

    public ReTrackSimplify a() {
        return this.o;
    }

    public ArrayList<LatLong> c() {
        return this.n;
    }

    public ArrayList<LenLatLong> e() {
        return this.d;
    }

    public boolean d() {
        return this.c;
    }

    public boolean b() {
        ArrayList<LenLatLong> arrayList;
        return (this.o == null || (arrayList = this.d) == null || this.n == null || arrayList.size() < 2 || this.n.size() < 2) ? false : true;
    }

    public boolean f() {
        MotionPathSimplify motionPathSimplify;
        if (!this.f13058a && (motionPathSimplify = this.j) != null && this.f != null) {
            int requestSportType = motionPathSimplify.requestSportType();
            Iterator<Integer> it = this.l.iterator();
            while (it.hasNext()) {
                if (it.next().intValue() == requestSportType) {
                    return false;
                }
            }
            Iterator<Integer> it2 = this.k.iterator();
            while (it2.hasNext()) {
                if (it2.next().intValue() == requestSportType) {
                    return true;
                }
            }
        }
        return false;
    }

    public haz b(int i) {
        this.b = i;
        return this;
    }
}
