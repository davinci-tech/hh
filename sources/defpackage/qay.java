package defpackage;

import android.text.TextUtils;
import com.github.mikephil.charting.data.Entry;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.npi;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class qay extends npi {
    private static int e = 5;

    public qay(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(hwHealthBaseBarLineChart);
    }

    private long c() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10006), "heart_rate_lastTimes");
        if (TextUtils.isEmpty(b2) || "0".equals(b2)) {
            LogUtil.h("HealthHeartRate_HwHRDatasContainer", "lastTimes is empty or 0");
            return System.currentTimeMillis();
        }
        try {
            return Long.parseLong(b2);
        } catch (NumberFormatException unused) {
            LogUtil.h("HealthHeartRate_HwHRDatasContainer", "getLastTimestamp numberFormatException");
            return System.currentTimeMillis();
        }
    }

    @Override // defpackage.npi, com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public void load(HwHealthLineDataSet hwHealthLineDataSet) {
        if (!(this.c instanceof HwHealthBaseScrollBarLineChart)) {
            super.load(hwHealthLineDataSet);
            return;
        }
        this.b.b(this.c, hwHealthLineDataSet);
        this.d = hwHealthLineDataSet;
        this.f15422a.clear();
        HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = (HwHealthBaseScrollBarLineChart) this.c;
        long millis = TimeUnit.MINUTES.toMillis(nom.h((int) hwHealthBaseScrollBarLineChart.getLowestVisibleX()) + nom.h((int) hwHealthBaseScrollBarLineChart.getHighestVisibleX())) / 2;
        boolean z = nom.b(millis) == nom.b(c());
        e eVar = new e(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(nom.d(millis))), Integer.MAX_VALUE, e(hwHealthLineDataSet));
        c cVar = new c(hwHealthLineDataSet, this.b);
        b bVar = null;
        b bVar2 = null;
        int i = 0;
        b bVar3 = null;
        while (i < 10000) {
            b d = d(cVar, eVar.a(), eVar.d());
            if (d != null) {
                if (bVar3 == null || d.s() > bVar3.s()) {
                    bVar3 = d;
                }
                if (bVar == null || d.t() < bVar.t()) {
                    bVar = d;
                }
                if (z) {
                    bVar2 = d;
                }
                d.h();
            }
            if (!cVar.c() || !eVar.e()) {
                break;
            }
            i++;
            eVar.c();
        }
        b(bVar3, bVar, bVar2);
        a(this.f15422a);
    }

    private int e(HwHealthLineDataSet hwHealthLineDataSet) {
        int i = e;
        return Math.round(hwHealthLineDataSet.a()) <= i ? Math.round(hwHealthLineDataSet.a()) : i;
    }

    private void b(b bVar, b bVar2, b bVar3) {
        if (bVar2 != null) {
            bVar2.m();
        }
        if (bVar != null) {
            bVar.l();
        }
        if (bVar3 != null) {
            bVar3.n();
        }
    }

    private b d(c cVar, int i, int i2) {
        b bVar = null;
        while (true) {
            if (!cVar.c()) {
                break;
            }
            Entry d = cVar.d();
            if (d != null) {
                if (d.getX() < i || d.getX() >= i2) {
                    break;
                }
                cVar.e();
                if (bVar == null) {
                    bVar = new b();
                    this.f15422a.add(bVar);
                }
                bVar.a(d);
            } else {
                LogUtil.h("HealthHeartRate_HwHRDatasContainer", "report error,is that OOM");
                break;
            }
        }
        return bVar;
    }

    static class b extends npf {
        private List<Entry> e = new ArrayList();

        b() {
        }

        public void a(Entry entry) {
            this.e.add(entry);
        }

        public void h() {
            if (this.e.size() == 0) {
                LogUtil.h("HealthHeartRate_HwHRDatasContainer", "no data,genSamplingPoint failed");
            } else {
                Entry entry = this.e.get(0);
                b(entry.getX(), entry.getY(), entry);
            }
        }

        public Entry o() {
            if (this.e.size() == 0) {
                LogUtil.h("HealthHeartRate_HwHRDatasContainer", "no data,getLastEntry failed");
                return new Entry();
            }
            return this.e.get(r0.size() - 1);
        }

        public Entry k() {
            if (this.e.size() == 0) {
                LogUtil.h("HealthHeartRate_HwHRDatasContainer", "no data,getFirstEntry failed");
                return new Entry();
            }
            return this.e.get(0);
        }

        public float s() {
            Entry p = p();
            if (p == null) {
                return -3.4028235E38f;
            }
            return p.getY();
        }

        public Entry p() {
            List<Entry> list = this.e;
            Entry entry = null;
            if (list != null && list.size() != 0) {
                float f = -3.4028235E38f;
                for (Entry entry2 : this.e) {
                    if (entry2.getY() > f) {
                        f = entry2.getY();
                        entry = entry2;
                    }
                }
            }
            return entry;
        }

        public float t() {
            Entry q = q();
            if (q == null) {
                return Float.MAX_VALUE;
            }
            return q.getY();
        }

        public Entry q() {
            List<Entry> list = this.e;
            Entry entry = null;
            if (list != null && list.size() != 0) {
                float f = Float.MAX_VALUE;
                for (Entry entry2 : this.e) {
                    if (entry2.getY() < f) {
                        f = entry2.getY();
                        entry = entry2;
                    }
                }
            }
            return entry;
        }

        public void l() {
            Entry p = p();
            if (p == null) {
                LogUtil.h("HealthHeartRate_HwHRDatasContainer", "genSamplingPointAsMax null");
            } else {
                b(p.getX(), p.getY(), p);
            }
        }

        public void m() {
            Entry q = q();
            if (q == null) {
                LogUtil.h("HealthHeartRate_HwHRDatasContainer", "genSamplingPointAsMin null");
            } else {
                b(q.getX(), q.getY(), q);
            }
        }

        public void n() {
            Entry o = o();
            if (o == null) {
                LogUtil.h("HealthHeartRate_HwHRDatasContainer", "genSamplingPointAsLast null");
            } else {
                b(o.getX(), o.getY(), o);
            }
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private int f16372a;
        private int b;
        private int c;
        private int d;

        e(int i, int i2, int i3) {
            this.c = i;
            this.b = i2;
            this.f16372a = i3;
            this.d = i;
        }

        public boolean e() {
            int i = this.d;
            int i2 = this.f16372a;
            int i3 = this.b;
            return i + i2 <= i3 || i + (i2 * 2) <= i3;
        }

        public boolean c() {
            if (!e()) {
                return false;
            }
            this.d += this.f16372a;
            return true;
        }

        public int a() {
            return this.d;
        }

        public int d() {
            return this.d + this.f16372a;
        }
    }

    public static class c {
        private HwHealthLineDataSet c;
        private int d;
        private npi.d e;

        public c(HwHealthLineDataSet hwHealthLineDataSet, npi.d dVar) {
            this.c = hwHealthLineDataSet;
            this.e = dVar;
            a();
        }

        public boolean c() {
            return this.d + 1 < this.e.b();
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [com.github.mikephil.charting.data.Entry] */
        public Entry d() {
            if (c()) {
                return this.c.getEntryForIndex(this.d + 1);
            }
            return null;
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [com.github.mikephil.charting.data.Entry] */
        public Entry e() {
            if (!c()) {
                return null;
            }
            int i = this.d + 1;
            this.d = i;
            return this.c.getEntryForIndex(i);
        }

        private void a() {
            this.d = this.e.e() - 1;
        }
    }

    private void a(List<npf> list) {
        int i = 0;
        while (i < list.size()) {
            int i2 = i - 1;
            b bVar = null;
            b bVar2 = (i2 < 0 || i2 >= list.size()) ? null : (b) list.get(i2);
            int i3 = i + 1;
            if (i3 >= 0 && i3 < list.size()) {
                bVar = (b) list.get(i3);
            }
            b bVar3 = (b) list.get(i);
            if (bVar2 != null) {
                bVar3.d(c(bVar2, bVar3));
            }
            if (bVar != null) {
                bVar3.b(c(bVar3, bVar));
            }
            i = i3;
        }
    }

    private boolean c(b bVar, b bVar2) {
        return Math.abs(((int) bVar.o().getX()) - ((int) bVar2.k().getX())) <= 35;
    }
}
