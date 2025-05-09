package defpackage;

import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class rdo {
    private List<rdr> b;
    private boolean c;
    private boolean e;
    private int g;
    private rdj j;
    private static final int[] d = {1, 2, 3, 4, 5};

    /* renamed from: a, reason: collision with root package name */
    private static final Object f16720a = new Object();

    public rdo() {
        this.g = 0;
        this.c = false;
        this.e = false;
        this.j = new rdj();
        l();
    }

    public rdo(rdj rdjVar) {
        this.g = 0;
        this.c = false;
        this.e = false;
        this.j = rdjVar;
        l();
    }

    private void l() {
        this.b = new ArrayList(10);
    }

    public void a(double d2, long j, double d3, int i, long j2) {
        int d4 = rdu.d(i);
        rdj rdjVar = this.j;
        if (rdjVar == null || rdjVar.d() == null) {
            this.j = new rdj(new HashMap(), 1, j);
        }
        Map<String, Double> d5 = this.j.d();
        e(d3, "Track_" + d4 + 3, d5);
        e(d2, "Track_" + d4 + 2, d5);
        double d6 = (double) j2;
        e(d6, "Track_" + d4 + 4, d5);
        e(d3, "Track_Calorie_Sum", d5);
        e(d2, "Track_Distance_Sum", d5);
        e(d6, "Track_Duration_Sum", d5);
        this.j.e(j);
        if (i == 289 || i == 288) {
            return;
        }
        rdj rdjVar2 = this.j;
        rdjVar2.e(rdjVar2.b() + 1);
    }

    private void e(double d2, String str, Map<String, Double> map) {
        if (map.get(str) != null) {
            map.put(str, Double.valueOf(map.get(str).doubleValue() + d2));
        } else {
            map.put(str, Double.valueOf(d2));
        }
    }

    public void d(rdr rdrVar) {
        if (this.b == null) {
            this.b = new ArrayList(10);
        }
        if (rdrVar == null) {
            LogUtil.a("Track_SportHistoryExpandableGroupData", "addSingleTrackData trackData is null");
            return;
        }
        synchronized (f16720a) {
            if (!this.b.contains(rdrVar)) {
                this.b.add(rdrVar);
            }
        }
    }

    public void d(int i, rdr rdrVar) {
        if (this.b == null) {
            this.b = new ArrayList(10);
        }
        if (rdrVar == null) {
            LogUtil.a("Track_SportHistoryExpandableGroupData", "addSingleTrackData trackData is null");
            return;
        }
        if (i >= d() || i < 0) {
            LogUtil.a("Track_SportHistoryExpandableGroupData", "addSingleTrackData index of");
            return;
        }
        synchronized (f16720a) {
            this.b.add(i, rdrVar);
        }
    }

    public int d() {
        List<rdr> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public rdr a(int i) {
        synchronized (f16720a) {
            if (koq.b(this.b, i)) {
                return null;
            }
            return this.b.get(i);
        }
    }

    public int j() {
        return this.j.b();
    }

    public String i() {
        return this.j.a();
    }

    public long h() {
        return this.j.c();
    }

    public double n() {
        return d(262, 0);
    }

    public long o() {
        return (long) d(262, 3);
    }

    public void a(rdj rdjVar) {
        if (this.j == null) {
            return;
        }
        this.j = rdjVar;
    }

    public rdj c() {
        return this.j;
    }

    public int f() {
        return this.g;
    }

    public void e(int i) {
        this.g = rdu.d(i);
    }

    public boolean m() {
        return this.g != 0;
    }

    public double b(int i) {
        if (i == 0) {
            return c("Track_Duration_Sum") + 0.0d + a(10001, 4);
        }
        if (i == 10001) {
            return a(10001, 4);
        }
        return a(i, 4);
    }

    public double d(int i) {
        if (i == 0) {
            return c("Track_Calorie_Sum") + 0.0d + a(10001, 3);
        }
        if (i == 10001) {
            return a(10001, 3);
        }
        return a(i, 3);
    }

    public String e(boolean z) {
        String quantityString;
        double a2 = a(287, 22);
        double d2 = a2 / 10;
        int i = d2 == 0.0d ? 1 : 0;
        if (UnitUtil.h()) {
            quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903217_res_0x7f0300b1, ((int) a2) / 10, UnitUtil.e(UnitUtil.e(d2, 1), 1, i ^ 1));
        } else {
            quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903307_res_0x7f03010b, ((int) a2) / 10, UnitUtil.e(d2, 1, i ^ 1));
        }
        double a3 = a(287, 21);
        double d3 = a3 / 60.0d;
        boolean z2 = d3 == 0.0d;
        Resources resources = BaseApplication.getContext().getResources();
        int i2 = (int) a3;
        Object[] objArr = new Object[1];
        objArr[0] = UnitUtil.e(d3, 1, z2 ? 0 : 2);
        String quantityString2 = resources.getQuantityString(R.plurals._2130903305_res_0x7f030109, i2, objArr);
        double a4 = a(287, 5);
        String quantityString3 = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, (int) a4, UnitUtil.e(a4, 1, 0));
        if (z) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_history_desc_three, quantityString, quantityString2, quantityString3);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_history_desc_three, quantityString2, quantityString3, "");
    }

    private double c(String str) {
        if (e().containsKey(str)) {
            return e().get(str).doubleValue();
        }
        return 0.0d;
    }

    public double a(int i, int i2) {
        double d2;
        if (e().containsKey("Track_" + i + i2)) {
            d2 = e().get("Track_" + i + i2).doubleValue();
        } else {
            d2 = 0.0d;
        }
        if (i == 286) {
            if (e().containsKey("Track_220" + i2)) {
                d2 += e().get("Track_220" + i2).doubleValue();
            }
        }
        if (i != 287) {
            return d2;
        }
        if (!e().containsKey("Track_291" + i2)) {
            return d2;
        }
        return d2 + e().get("Track_291" + i2).doubleValue();
    }

    public double c(int i) {
        if (i == 0) {
            return c("Track_Distance_Sum");
        }
        return a(i, 2);
    }

    public List<String> b() {
        ArrayList arrayList = new ArrayList();
        rdj rdjVar = this.j;
        if (rdjVar == null || rdjVar.e()) {
            return null;
        }
        for (Map.Entry<String, Double> entry : this.j.d().entrySet()) {
            if (entry.getValue() != null && entry.getValue().doubleValue() > 0.0d && !arrayList.contains(entry.getKey())) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public int g() {
        rdj rdjVar;
        HashSet hashSet = new HashSet();
        List<String> c = hln.c(BaseApplication.getContext()).c();
        if (koq.b(c) || (rdjVar = this.j) == null || rdjVar.d() == null) {
            return 0;
        }
        for (String str : c) {
            int a2 = rdu.a(str);
            if (this.j.d().containsKey(str) && this.j.d().get(str).doubleValue() > 0.0d && a2 != 0 && a2 != 288 && a2 != 289) {
                hashSet.add(Integer.valueOf(rdu.d(a2)));
            }
        }
        return hashSet.size();
    }

    public Map<String, Double> e() {
        return this.j.d();
    }

    public double d(int i, int i2) {
        rdj rdjVar;
        List<String> e = rdu.e(i);
        if (!koq.d(e, i2) || (rdjVar = this.j) == null || rdjVar.d() == null || !this.j.d().containsKey(e.get(i2)) || this.j.d().get(e.get(i2)) == null) {
            return 0.0d;
        }
        return this.j.d().get(e.get(i2)).doubleValue();
    }

    public int c(int i, int i2) {
        LogUtil.a("Track_SportHistoryExpandableGroupData", "deleteChildAt i = ", Integer.valueOf(i));
        if (i >= d() || i < 0) {
            LogUtil.h("Track_SportHistoryExpandableGroupData", "OutOfIndex delete failed childCount = ", Integer.valueOf(d()));
            return d();
        }
        rdr rdrVar = this.b.get(i);
        int x = rdrVar.x();
        if (x == 289 || x == 288) {
            LogUtil.a("Track_SportHistoryExpandableGroupData", "delete BREATH_HOLDING_TRAINING or SPORT_TYPE_BREATH_HOLDING_TRAINING");
            synchronized (f16720a) {
                this.b.remove(i);
            }
            return d();
        }
        this.j.e(r2.b() - 1);
        int d2 = rdu.d(x);
        if (d2 == 287 && i2 != 0) {
            rdu.c(rdrVar, this.j.d(), this.j.c(), jdl.a(this.j.c()), 5, new IBaseResponseCallback() { // from class: rdk
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj) {
                    rdo.this.d(i3, obj);
                }
            });
        } else if (m() && i2 == 0 && d2 == 287) {
            float k = rdrVar.k() / 1000.0f;
            if (this.j.d().get("Track_28721") != null) {
                this.j.d().put("Track_28721", Double.valueOf(this.j.d().get("Track_28721").doubleValue() - k));
            }
        } else {
            rdu.b(rdrVar, d2, this.j.d());
        }
        h(d2);
        synchronized (f16720a) {
            this.b.remove(i);
        }
        return d();
    }

    /* synthetic */ void d(int i, Object obj) {
        this.j.d((Map) obj);
    }

    private void h(int i) {
        List<String> e = rdu.e(i);
        if (e == null || e.size() < 3) {
            LogUtil.b("Track_SportHistoryExpandableGroupData", "delete times", Integer.valueOf(i));
            return;
        }
        String str = e.get(2);
        if (!this.j.d().containsKey(str) || this.j.d().get(str) == null) {
            return;
        }
        this.j.d().put(str, Double.valueOf(this.j.d().get(str).doubleValue() - 1.0d));
    }

    public boolean a() {
        return this.c;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean k() {
        if (d() == 0 || !a()) {
            return false;
        }
        return !this.e;
    }
}
