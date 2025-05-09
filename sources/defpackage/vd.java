package defpackage;

import com.huawei.ads.adsrec.IUtilCallback;
import com.huawei.ads.adsrec.l;
import com.huawei.ads.adsrec.o0;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class vd extends l {
    private Map<String, d> b;
    private int c;
    private int d;

    @Override // com.huawei.ads.adsrec.h0
    public void a() {
        e eVar = new e(null);
        for (vb vbVar : this.f1678a) {
            if (vbVar.y().booleanValue()) {
                double o = vbVar.o();
                double h = vbVar.h();
                eVar.f17675a = Double.valueOf(Math.min(eVar.f17675a.doubleValue(), o));
                eVar.b = Double.valueOf(Math.max(eVar.b.doubleValue(), o));
                eVar.d = Double.valueOf(Math.min(eVar.d.doubleValue(), h));
                eVar.c = Double.valueOf(Math.max(eVar.c.doubleValue(), h));
            }
        }
        double doubleValue = eVar.b.doubleValue();
        double doubleValue2 = eVar.f17675a.doubleValue();
        double doubleValue3 = eVar.c.doubleValue();
        double doubleValue4 = eVar.d.doubleValue();
        Iterator<vb> it = this.f1678a.iterator();
        while (it.hasNext()) {
            vb next = it.next();
            if (next.y().booleanValue()) {
                double o2 = next.o();
                double h2 = next.h();
                Iterator<vb> it2 = it;
                double d2 = doubleValue;
                int a2 = a(((o2 - eVar.f17675a.doubleValue()) / (doubleValue - doubleValue2)) * this.d);
                int i = this.d;
                if (a2 == i) {
                    a2 = i - 1;
                }
                int a3 = a(((h2 - eVar.d.doubleValue()) / (doubleValue3 - doubleValue4)) * this.d);
                int i2 = this.d;
                if (a3 == i2) {
                    a3 = i2 - 1;
                }
                d dVar = new d(a2, a3);
                if (!StringUtils.isBlank(next.f())) {
                    this.b.put(next.f(), dVar);
                }
                next.f();
                it = it2;
                doubleValue = d2;
            }
        }
        Collections.sort(this.f1678a, new a());
        if (HiAdLog.isDebugEnable()) {
            Iterator<vb> it3 = this.f1678a.iterator();
            while (it3.hasNext()) {
                it3.next().f();
            }
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private int f17674a;
        private int d;

        public int c() {
            return this.d;
        }

        public int b() {
            return this.f17674a;
        }

        public d(int i, int i2) {
            this.f17674a = i;
            this.d = i2;
        }
    }

    class a implements Comparator<vb> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(vb vbVar, vb vbVar2) {
            return o0.a(vbVar, vbVar2, (vbVar.y().booleanValue() && vbVar2.y().booleanValue()) ? vd.this.d(vbVar, vbVar2) : o0.a(vbVar, vbVar2));
        }

        a() {
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        Double f17675a;
        Double b;
        Double c;
        Double d;

        /* synthetic */ e(a aVar) {
            this();
        }

        private e() {
            Double valueOf = Double.valueOf(Double.MAX_VALUE);
            this.f17675a = valueOf;
            Double valueOf2 = Double.valueOf(Double.MIN_VALUE);
            this.b = valueOf2;
            this.d = valueOf;
            this.c = valueOf2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(vb vbVar, vb vbVar2) {
        double o = vbVar.o();
        double h = vbVar.h();
        double o2 = vbVar2.o();
        double h2 = vbVar2.h();
        d dVar = this.b.get(vbVar.f());
        d dVar2 = this.b.get(vbVar2.f());
        if (dVar == null || dVar2 == null) {
            HiAdLog.e("StrategySortation", "content can't find in sortingDataMap");
            return 0;
        }
        int b = dVar.b();
        int c = dVar.c();
        int b2 = dVar2.b();
        int c2 = dVar2.c();
        int compare = Integer.compare(b2 + c2, b + c);
        if (compare != 0) {
            return compare;
        }
        if (this.c == 1) {
            int compare2 = Integer.compare(c2, c);
            if (compare2 == 0) {
                compare2 = o0.a(h2, h);
            }
            return compare2 == 0 ? o0.a(o2, o) : compare2;
        }
        int compare3 = Integer.compare(b2, b);
        if (compare3 == 0) {
            compare3 = o0.a(o2, o);
        }
        return compare3 == 0 ? o0.a(h2, h) : compare3;
    }

    private static int a(double d2) {
        int i = (int) d2;
        return Math.abs(d2 - ((double) i)) > 9.99999993922529E-9d ? i : (int) Math.round(d2);
    }

    public vd(List<vb> list) {
        super(list);
        this.c = 0;
        this.d = 10;
        IUtilCallback d2 = uw.d();
        if (d2 != null) {
            this.c = StringUtils.parseIntOrDefault(d2.getConfig("rankSide"), 0);
            int parseIntOrDefault = StringUtils.parseIntOrDefault(d2.getConfig("bucketCnt"), 10);
            this.d = parseIntOrDefault;
            this.d = parseIntOrDefault >= 1 ? parseIntOrDefault : 10;
        }
        if (ListUtil.isEmpty(list)) {
            return;
        }
        this.b = new HashMap();
    }
}
