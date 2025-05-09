package defpackage;

import android.content.Context;
import com.huawei.ads.adsrec.db.table.AdIECImpRecord;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class wa implements Comparable<wa> {
    private final String b;
    private final Context c;
    private final wc d;
    private final long e;
    private volatile Map<String, b> i;
    private final AtomicBoolean j = new AtomicBoolean(true);
    private final AtomicInteger f = new AtomicInteger(0);

    /* renamed from: a, reason: collision with root package name */
    private volatile long f17722a = System.currentTimeMillis();

    boolean e() {
        if (this.i == null) {
            return true;
        }
        synchronized (this) {
            b();
            if (this.f.get() > 0) {
                return false;
            }
            if (this.e + this.f17722a > System.currentTimeMillis()) {
                return false;
            }
            this.i.clear();
            this.i = null;
            return true;
        }
    }

    void a() {
        this.f.incrementAndGet();
    }

    void d() {
        this.f.decrementAndGet();
    }

    public void e(Set<String> set) {
        if (this.i == null || ListUtil.isEmpty(set)) {
            return;
        }
        synchronized (this) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                b bVar = this.i.get(it.next());
                if (bVar == null) {
                    HiAdLog.w("RTFilter", "content not in map");
                } else {
                    bVar.e();
                }
            }
        }
    }

    public void b(List<vg> list) {
        if (ListUtil.isEmpty(list)) {
            return;
        }
        a aVar = new a();
        for (vg vgVar : list) {
            long i = this.d.i(vgVar.h());
            List<vb> a2 = vgVar.a();
            if (a2 != null) {
                Iterator<vb> it = a2.iterator();
                while (it.hasNext()) {
                    aVar.a(it.next(), 60000 * i);
                }
            }
        }
        if (aVar.a()) {
            HiAdLog.w("RTFilter", "not to filter");
        } else {
            a(list, c(aVar));
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(wa waVar) {
        return this.f17722a > waVar.f17722a ? -1 : 0;
    }

    private void b() {
        if (this.j.compareAndSet(false, true)) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator<Map.Entry<String, b>> it = this.i.entrySet().iterator();
            while (it.hasNext()) {
                b value = it.next().getValue();
                String[] d2 = value.d();
                if (d2 != null) {
                    if (value.a()) {
                        arrayList2.add(d2);
                    } else {
                        arrayList.add(d2);
                    }
                    value.c();
                }
            }
            com.huawei.ads.adsrec.e eVar = new com.huawei.ads.adsrec.e(this.c);
            if (!ListUtil.isEmpty(arrayList)) {
                arrayList.size();
                eVar.b(arrayList);
            }
            if (ListUtil.isEmpty(arrayList2)) {
                return;
            }
            arrayList2.size();
            eVar.a(arrayList2);
        }
    }

    private void a(Map<String, List<vb>> map, vb vbVar) {
        String x = vbVar.x();
        List<vb> list = map.get(x);
        if (list == null) {
            list = new ArrayList<>();
            map.put(x, list);
        }
        list.add(vbVar);
    }

    private void a(List<vg> list, Map<String, List<vb>> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (vg vgVar : list) {
            String h = vgVar.h();
            List<vb> a2 = vgVar.a();
            List<vb> list2 = map.get(h);
            if (!ListUtil.isEmpty(a2) && !ListUtil.isEmpty(list2)) {
                ArrayList arrayList = new ArrayList();
                for (vb vbVar : a2) {
                    if (!list2.contains(vbVar)) {
                        arrayList.add(vbVar);
                    }
                }
                vgVar.b(arrayList);
            }
        }
    }

    private void a(long j, Map<String, List<vb>> map, vb vbVar, long j2) {
        String f = vbVar.f();
        b bVar = this.i.get(f);
        if (bVar == null || bVar.c(j, j2)) {
            a(j, f);
        } else {
            a(map, vbVar);
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private final String f17723a;
        private final String b;
        private long c;
        private boolean d;
        private long e;

        void e() {
            this.e = this.c;
        }

        void c() {
            this.c = this.e;
            this.d = true;
        }

        boolean a() {
            return this.d;
        }

        String[] d() {
            long currentTimeMillis = System.currentTimeMillis();
            if (!this.d) {
                return new String[]{this.b, this.f17723a, String.valueOf(this.e), String.valueOf(currentTimeMillis)};
            }
            long j = this.e;
            if (j == this.c) {
                return null;
            }
            return new String[]{String.valueOf(j), String.valueOf(currentTimeMillis), this.b, this.f17723a};
        }

        boolean c(long j, long j2) {
            return this.e + j2 < j;
        }

        b(String str, String str2, long j, boolean z) {
            this.b = str;
            this.f17723a = str2;
            this.c = z ? j : 0L;
            this.e = j;
            this.d = z;
        }
    }

    private void b(long j, Map<String, List<vb>> map, vb vbVar) {
        a(j, vbVar.f());
        a(map, vbVar);
    }

    static class a {
        private final List<d> b;
        private final List<d> c;

        boolean a() {
            return this.c.isEmpty() && this.b.isEmpty();
        }

        List<d> c() {
            return this.b;
        }

        void a(vb vbVar, long j) {
            (vbVar.s() == 0 ? this.c : this.b).add(new d(vbVar, j));
        }

        List<d> e() {
            return this.c;
        }

        private a() {
            this.c = new ArrayList();
            this.b = new ArrayList();
        }
    }

    private void a(long j, String str) {
        b bVar = this.i.get(str);
        if (bVar == null) {
            this.i.put(str, new b(this.b, str, j, false));
        } else {
            bVar.e = j;
        }
        d(j);
    }

    private void d(long j) {
        this.f17722a = j;
        this.j.set(false);
    }

    private void c() {
        this.i = new ConcurrentHashMap();
        List<AdIECImpRecord> a2 = new com.huawei.ads.adsrec.e(this.c).a(this.b);
        if (ListUtil.isEmpty(a2)) {
            return;
        }
        for (AdIECImpRecord adIECImpRecord : a2) {
            this.i.put(adIECImpRecord.c(), new b(this.b, adIECImpRecord.c(), adIECImpRecord.a(), true));
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private final long f17724a;
        private final vb b;

        public long c() {
            return this.f17724a;
        }

        public vb a() {
            return this.b;
        }

        d(vb vbVar, long j) {
            this.b = vbVar;
            this.f17724a = j;
        }
    }

    private Set<String> d(a aVar, long j) {
        HashSet hashSet = new HashSet();
        Iterator<d> it = aVar.e().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().a().f());
        }
        return hashSet;
    }

    private Map<String, List<vb>> b(a aVar, long j, Set<String> set) {
        List<d> c = aVar.c();
        if (ListUtil.isEmpty(c)) {
            return null;
        }
        if (this.i == null) {
            c();
        }
        HashMap hashMap = new HashMap();
        for (d dVar : c) {
            vb a2 = dVar.a();
            if (set.contains(a2.f())) {
                b(j, hashMap, a2);
            } else {
                a(j, hashMap, a2, dVar.c());
            }
        }
        return hashMap;
    }

    private Map<String, List<vb>> c(a aVar) {
        Map<String, List<vb>> b2;
        synchronized (this) {
            long currentTimeMillis = System.currentTimeMillis();
            b2 = b(aVar, currentTimeMillis, d(aVar, currentTimeMillis));
        }
        return b2;
    }

    wa(Context context, String str, long j) {
        this.c = context;
        this.b = str;
        this.e = j;
        this.d = wc.d(context);
    }
}
