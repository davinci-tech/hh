package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionPieChartBasicAdapter;
import com.huawei.health.knit.section.view.SectionPieChartTrendAdapter;
import com.huawei.hihealth.data.model.HiBloodPressureMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.chart.HealthTrendBarChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class qhm {
    private SectionPieChartBasicAdapter.b f;
    private final String i;
    private final String k;
    private qhm l;
    private long m;
    private final String s;
    private final String t;
    private List<qkg> o = new ArrayList();
    private Map<Integer, Integer> e = new HashMap();
    private final List<qkg> b = new ArrayList();
    private double g = 0.0d;
    private double d = 0.0d;
    private double c = 0.0d;
    private long p = 0;
    private long n = 0;
    private int x = 0;

    /* renamed from: a, reason: collision with root package name */
    private String f16426a = "--";
    private DataInfos h = DataInfos.NoDataPlaceHolder;
    private List<SectionPieChartBasicAdapter.b> j = new ArrayList();
    private Map<String, Map<Integer, Integer>> r = new HashMap();
    private Map<String, List<SectionPieChartBasicAdapter.b>> q = new HashMap();
    private List<SectionPieChartTrendAdapter.b> u = new ArrayList();

    public void c(long j) {
        this.m = j;
    }

    public long i() {
        return this.m;
    }

    public qhm() {
        Context e = BaseApplication.e();
        this.f = new SectionPieChartBasicAdapter.b(e.getString(R$string.IDS_weight_index), e.getString(R$string.IDS_blood_pressure_max), e.getString(R$string.IDS_blood_pressure_min), e.getString(R$string.IDS_blood_pressure_average));
        this.t = e.getString(R$string.IDS_blood_pressure_systolic);
        this.i = e.getString(R$string.IDS_blood_pressure_diastolic);
        this.s = e.getString(R$string.IDS_pulse_rate);
        this.k = e.getString(R$string.IDS_pulse_pressure_difference);
    }

    private void o() {
        this.o.clear();
        this.e.clear();
        this.b.clear();
        this.g = 0.0d;
        this.d = 0.0d;
        this.c = 0.0d;
        this.p = 0L;
        this.n = 0L;
        this.x = 0;
        this.j.clear();
        this.u.clear();
        this.f16426a = "--";
    }

    public void b(DataInfos dataInfos) {
        this.h = dataInfos;
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        int f16427a;
        int b;
        qkg c;
        int d;
        int e;
        int f;
        qkg g;
        qkg h;
        qkg i;
        int j;
        qkg k;
        qkg l;
        int m;

        private c() {
            this.f = Integer.MAX_VALUE;
            this.j = Integer.MIN_VALUE;
            this.e = 0;
            this.f16427a = 0;
            this.d = 0;
            this.g = null;
            this.l = null;
            this.c = null;
            this.h = null;
            this.i = null;
            this.k = null;
            this.m = 0;
            this.b = 0;
        }
    }

    public void b(List<qkg> list, long j, long j2) {
        if (this.l == null) {
            this.l = new qhm();
        }
        this.l.e(list, j, j2);
    }

    public void e(List<qkg> list, long j, long j2) {
        c(list, j, j2);
        if (this.h == DataInfos.BloodPressureMonthDetail || this.h == DataInfos.BloodPressureWeekDetail) {
            d(list, j, j2);
        }
    }

    void c(List<qkg> list, long j, long j2) {
        o();
        if (koq.b(list)) {
            return;
        }
        this.o.addAll(list);
        this.p = j;
        this.n = j2;
        this.b.clear();
        c cVar = new c();
        d(list, cVar);
        d(list, this.e, cVar);
        b(list);
        b(cVar);
    }

    private void d(List<qkg> list, c cVar) {
        int size = list.size();
        int i = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (qkg qkgVar : list) {
            double o = qkgVar.o();
            double m = qkgVar.m();
            double n = qkgVar.n();
            d += o;
            d2 += m;
            if (n > 0.0d) {
                d3 += n;
                i++;
            }
        }
        this.c = i > 0 ? Math.round(d3 / i) : 0.0d;
        double d4 = size;
        this.g = Math.round(d / d4);
        this.d = Math.round(d2 / d4);
        cVar.e = (int) this.g;
        cVar.f16427a = (int) this.d;
        cVar.d = (int) this.c;
    }

    void d(List<qkg> list, long j, long j2) {
        LogUtil.a("BloodPressureData", "updateTimeSharingData");
        this.r.clear();
        this.q.clear();
        if (koq.b(list)) {
            LogUtil.a("BloodPressureData", "dataList is empty");
            return;
        }
        Iterator<qkg> it = list.iterator();
        while (it.hasNext()) {
            LogUtil.a("BloodPressureData", "timeSharingData: " + it.next().toString());
        }
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (qkg qkgVar : list) {
            int k = (int) qkgVar.k();
            a(qkgVar, hashMap);
            if (!hashMap2.containsKey(Integer.valueOf(k))) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(qkgVar);
                hashMap2.put(Integer.valueOf((int) qkgVar.k()), arrayList);
            } else {
                List<qkg> list2 = hashMap2.get(Integer.valueOf(k));
                if (list2 != null) {
                    list2.add(qkgVar);
                }
            }
        }
        for (Map.Entry<String, List<qkg>> entry : hashMap.entrySet()) {
            e(entry.getKey(), entry.getValue(), j, j2);
        }
        c(hashMap2, j, j2);
    }

    private void c(Map<Integer, List<qkg>> map, long j, long j2) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Integer, List<qkg>> entry : map.entrySet()) {
            List<qkv> b = BloodPressureUtil.b();
            BloodPressureUtil.d(BloodPressureUtil.a(entry.getKey().intValue()), b);
            for (qkv qkvVar : b) {
                if (qkvVar.i()) {
                    if (hashMap.containsKey(qkvVar.d()) && hashMap.get(qkvVar.d()) != null) {
                        ((List) hashMap.get(qkvVar.d())).addAll(entry.getValue());
                    } else {
                        hashMap.put(qkvVar.d(), new ArrayList(entry.getValue()));
                    }
                }
            }
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            e((String) entry2.getKey(), (List) entry2.getValue(), j, j2);
        }
    }

    private void a(qkg qkgVar, Map<String, List<qkg>> map) {
        HiBloodPressureMetaData hiBloodPressureMetaData;
        if (TextUtils.isEmpty(qkgVar.f()) || (hiBloodPressureMetaData = (HiBloodPressureMetaData) nrv.b(qkgVar.f(), HiBloodPressureMetaData.class)) == null) {
            return;
        }
        List<String> activityActions = hiBloodPressureMetaData.getActivityActions();
        if (koq.b(activityActions)) {
            LogUtil.a("BloodPressureData", "activity actions is empty");
            return;
        }
        for (String str : activityActions) {
            if (!map.containsKey(str)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(qkgVar);
                map.put(str, arrayList);
            } else {
                List<qkg> list = map.get(str);
                if (list != null) {
                    list.add(qkgVar);
                }
            }
        }
    }

    private void b(c cVar) {
        Context e = BaseApplication.e();
        if (this.h == DataInfos.BloodPressureMonthDetail || this.h == DataInfos.BloodPressureWeekDetail) {
            d(e, cVar, this.j);
            d(e, cVar);
        }
    }

    private void d(Context context, c cVar, List<SectionPieChartBasicAdapter.b> list) {
        list.clear();
        int c2 = eeu.c();
        list.add(this.f);
        list.add(e(cVar, context, c2));
        list.add(a(cVar, context, c2));
        list.add(a(cVar));
        list.add(c(cVar, context, c2));
    }

    private void d(Context context, c cVar) {
        this.u.clear();
        String string = context.getString(R$string.IDS_hw_mean_blood_pressure);
        String str = Constants.LEFT_BRACKET_ONLY + context.getString(R$string.IDS_hw_health_show_healthdata_mmhg_str) + Constants.RIGHT_BRACKET_ONLY;
        HealthTrendBarChart.b bVar = new HealthTrendBarChart.b(cVar.e, cVar.f16427a, e(cVar.e, cVar.f16427a) + " " + e());
        qhm qhmVar = this.l;
        int a2 = qhmVar != null ? (int) qhmVar.a() : 0;
        qhm qhmVar2 = this.l;
        int b = qhmVar2 != null ? (int) qhmVar2.b() : 0;
        String e = e(a2, b);
        qhm qhmVar3 = this.l;
        this.u.add(new SectionPieChartTrendAdapter.b(string, str, bVar, new HealthTrendBarChart.b(a2, b, e + " " + (qhmVar3 != null ? qhmVar3.e() : "--"))));
        String string2 = context.getString(R$string.IDS_hw_ave_pulse_value);
        String str2 = Constants.LEFT_BRACKET_ONLY + context.getString(R$string.IDS_main_watch_heart_rate_unit_string) + Constants.RIGHT_BRACKET_ONLY;
        HealthTrendBarChart.b bVar2 = new HealthTrendBarChart.b(cVar.d, e(cVar.d));
        qhm qhmVar4 = this.l;
        int d = qhmVar4 != null ? (int) qhmVar4.d() : 0;
        this.u.add(new SectionPieChartTrendAdapter.b(string2, str2, bVar2, new HealthTrendBarChart.b(d, e(d))));
        String string3 = context.getString(R$string.IDS_low_blood_pressure_times);
        String str3 = Constants.LEFT_BRACKET_ONLY + context.getString(R$string.IDS_awake_times) + Constants.RIGHT_BRACKET_ONLY;
        int e2 = e(this.e, 0);
        HealthTrendBarChart.b bVar3 = new HealthTrendBarChart.b(e2, d(e2));
        qhm qhmVar5 = this.l;
        int e3 = qhmVar5 != null ? qhmVar5.e(qhmVar5.e, 0) : 0;
        this.u.add(new SectionPieChartTrendAdapter.b(string3, str3, bVar3, new HealthTrendBarChart.b(e3, d(e3))));
    }

    void e(String str, List<qkg> list, long j, long j2) {
        LogUtil.a("BloodPressureData", "updatePieBasicData, key: " + str);
        if (koq.b(list)) {
            return;
        }
        Iterator<qkg> it = list.iterator();
        while (it.hasNext()) {
            LogUtil.a("BloodPressureData", "healthData: " + it.next().toString());
        }
        this.p = j;
        this.n = j2;
        c cVar = new c();
        HashMap hashMap = new HashMap();
        this.x = 0;
        d(list, hashMap, cVar);
        a(cVar, hashMap, str);
    }

    private void a(c cVar, Map<Integer, Integer> map, String str) {
        Context e = BaseApplication.e();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BloodPressureData", "key is not exist");
            return;
        }
        this.r.put(str, map);
        ArrayList arrayList = new ArrayList();
        d(e, cVar, arrayList);
        this.q.put(str, arrayList);
    }

    private void d(List<qkg> list, Map<Integer, Integer> map, c cVar) {
        int size = list.size();
        int i = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (qkg qkgVar : list) {
            double o = qkgVar.o();
            int i2 = size;
            double m = qkgVar.m();
            double d4 = d2;
            double n = qkgVar.n();
            if (n > 0.0d) {
                d += n;
                i++;
            }
            int i3 = i;
            double d5 = d;
            int i4 = (int) o;
            int i5 = (int) m;
            int c2 = eeu.c(i4, i5);
            map.put(Integer.valueOf(c2), Integer.valueOf(e(map, c2) + 1));
            if (this.h == DataInfos.BloodPressureMonthDetail || this.h == DataInfos.BloodPressureWeekDetail) {
                d(cVar, i4, i5, qkgVar, (int) n);
            }
            this.x++;
            d2 = d4 + o;
            d3 += m;
            size = i2;
            i = i3;
            d = d5;
        }
        int i6 = size;
        double d6 = d2;
        double round = i > 0 ? Math.round(d / i) : 0.0d;
        double d7 = i6;
        int round2 = (int) Math.round(d6 / d7);
        int round3 = (int) Math.round(d3 / d7);
        int c3 = eeu.c(round2, round3);
        cVar.e = round2;
        cVar.f16427a = round3;
        cVar.d = (int) round;
        cVar.b = c3;
        this.f16426a = eeu.c(c3);
    }

    private void b(List<qkg> list) {
        synchronized (this.b) {
            this.b.clear();
            for (qkg qkgVar : list) {
                if (eeu.c((int) qkgVar.o(), (int) qkgVar.m()) != 1) {
                    this.b.add(qkgVar);
                }
            }
        }
    }

    private void d(c cVar, int i, int i2, qkg qkgVar, int i3) {
        if (cVar.g == null || i >= cVar.g.o()) {
            cVar.g = qkgVar;
        }
        if (cVar.l == null || i <= cVar.l.o()) {
            cVar.l = qkgVar;
        }
        if (cVar.c == null || i2 >= cVar.c.m()) {
            cVar.c = qkgVar;
        }
        if (cVar.h == null || i2 <= cVar.h.m()) {
            cVar.h = qkgVar;
        }
        if (i3 >= cVar.j) {
            cVar.j = i3;
        }
        if ((i3 <= cVar.f || cVar.f == 0) && (cVar.f == Integer.MAX_VALUE || i3 != 0)) {
            cVar.f = i3;
        }
        int i4 = i - i2;
        cVar.m += i4;
        if (cVar.i == null || i4 >= a(cVar.i)) {
            cVar.i = qkgVar;
        }
        if (cVar.k == null || i4 <= a(cVar.k)) {
            cVar.k = qkgVar;
        }
    }

    private SectionPieChartBasicAdapter.b e(c cVar, Context context, int i) {
        int o = (int) cVar.g.o();
        int m = (int) cVar.g.m();
        int c2 = eeu.c(o, m);
        SpannableString dDG_ = dDG_(eeu.c(c2), c2);
        int o2 = (int) cVar.l.o();
        int m2 = (int) cVar.l.m();
        int c3 = eeu.c(o2, m2);
        SpannableString dDG_2 = dDG_(eeu.c(c3), c3);
        String e = e(o, m);
        String e2 = e(o2, m2);
        return new SectionPieChartBasicAdapter.b(this.t, dDH_(e, c(e), context, false), dDG_, BloodPressureUtil.dHx_(context, i, c2), dDH_(e2, c(e2), context, false), dDG_2, BloodPressureUtil.dHx_(context, i, c3), e(cVar.e), dDG_(this.f16426a, cVar.b), BloodPressureUtil.dHx_(context, i, cVar.b));
    }

    private SectionPieChartBasicAdapter.b a(c cVar, Context context, int i) {
        int o = (int) cVar.c.o();
        int m = (int) cVar.c.m();
        int c2 = eeu.c(o, m);
        SpannableString dDG_ = dDG_(eeu.c(c2), c2);
        int o2 = (int) cVar.h.o();
        int m2 = (int) cVar.h.m();
        int c3 = eeu.c(o2, m2);
        SpannableString dDG_2 = dDG_(eeu.c(c3), c3);
        String e = e(m, o);
        String e2 = e(m2, o2);
        return new SectionPieChartBasicAdapter.b(this.i, dDH_(e, c(e), context, false), dDG_, BloodPressureUtil.dHx_(context, i, c2), dDH_(e2, c(e2), context, false), dDG_2, BloodPressureUtil.dHx_(context, i, c3), e(cVar.f16427a), dDG_(this.f16426a, cVar.b), BloodPressureUtil.dHx_(context, i, cVar.b));
    }

    private SectionPieChartBasicAdapter.b a(c cVar) {
        return new SectionPieChartBasicAdapter.b(this.s, e(cVar.j), e(cVar.f), e(cVar.d));
    }

    private SectionPieChartBasicAdapter.b c(c cVar, Context context, int i) {
        String e = e((int) cVar.i.o(), (int) cVar.i.m());
        String d = d(a(cVar.i));
        String str = d + Constants.LEFT_BRACKET_ONLY + e + Constants.RIGHT_BRACKET_ONLY;
        if (LanguageUtil.b(context)) {
            str = d + " (" + e + Constants.RIGHT_BRACKET_ONLY;
        }
        if (LanguageUtil.bp(BaseApplication.e())) {
            str = Constants.LEFT_BRACKET_ONLY + e + Constants.RIGHT_BRACKET_ONLY + d;
        }
        String e2 = e((int) cVar.k.o(), (int) cVar.k.m());
        String d2 = d(a(cVar.k));
        String str2 = d2 + Constants.LEFT_BRACKET_ONLY + e2 + Constants.RIGHT_BRACKET_ONLY;
        if (LanguageUtil.b(context)) {
            str2 = d2 + " (" + e2 + Constants.RIGHT_BRACKET_ONLY;
        }
        if (LanguageUtil.bp(BaseApplication.e())) {
            str2 = Constants.LEFT_BRACKET_ONLY + e2 + Constants.RIGHT_BRACKET_ONLY + d2;
        }
        int b = BloodPressureUtil.b(a(cVar.i));
        String b2 = b(context, b);
        int b3 = BloodPressureUtil.b(a(cVar.k));
        String b4 = b(context, b3);
        int round = this.x != 0 ? (int) Math.round(cVar.m / this.x) : 0;
        int b5 = BloodPressureUtil.b(round);
        return new SectionPieChartBasicAdapter.b(this.k, dDH_(str, d.length(), context, true), dDG_(b2, b), BloodPressureUtil.dHx_(context, i, b), dDH_(str2, d2.length(), context, true), dDG_(b4, b3), BloodPressureUtil.dHx_(context, i, b3), d(round), dDG_(b(context, b5), b5), BloodPressureUtil.dHx_(context, i, b5));
    }

    public List<SectionPieChartTrendAdapter.b> l() {
        return this.u;
    }

    private String e(double d, double d2) {
        StringBuilder sb;
        String e = d > 0.0d ? UnitUtil.e((int) d, 1, 0) : "--";
        String e2 = d2 > 0.0d ? UnitUtil.e((int) d2, 1, 0) : "--";
        if (!LanguageUtil.bc(BaseApplication.e()) || LanguageUtil.bp(BaseApplication.e())) {
            sb = new StringBuilder();
            sb.append(e);
            sb.append("/");
            sb.append(e2);
        } else {
            sb = new StringBuilder();
            sb.append(e2);
            sb.append("/");
            sb.append(e);
        }
        return sb.toString();
    }

    private String e(double d) {
        return d > 0.0d ? UnitUtil.e((int) d, 1, 0) : "--";
    }

    private String d(double d) {
        return UnitUtil.e((int) d, 1, 0);
    }

    private SpannableString dDG_(String str, int i) {
        int d = eeu.d(i);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(d), 0, str.length(), 33);
        return spannableString;
    }

    private SpannableString dDH_(String str, int i, Context context, boolean z) {
        int i2;
        int length = str.length();
        int i3 = 0;
        if (z && LanguageUtil.bp(BaseApplication.e())) {
            i2 = str.length() - i;
            int length2 = str.length();
            length = str.length() - i;
            i = length2;
        } else {
            i2 = 0;
            i3 = i;
        }
        TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(context, R.style.health_basic_situation_value_primary);
        TextAppearanceSpan textAppearanceSpan2 = new TextAppearanceSpan(context, R.style.health_basic_situation_value_secondary);
        SpannableString spannableString = new SpannableString(str);
        if (nsn.t()) {
            spannableString.setSpan(new TextAppearanceSpan(context, R.style.health_basic_situation_value_primary_less_one_three_Scale), i2, i, 33);
            spannableString.setSpan(new TextAppearanceSpan(context, R.style.health_basic_situation_value_secondary_less_one_three_Scale), i3, length, 33);
        } else if (nsn.c() > 1.55f) {
            spannableString.setSpan(new TextAppearanceSpan(context, R.style.health_basic_situation_value_primary_more_one_five_Scale), i2, i, 33);
            spannableString.setSpan(new TextAppearanceSpan(context, R.style.health_basic_situation_value_secondary_less_one_three_Scale), i3, length, 33);
        } else {
            spannableString.setSpan(textAppearanceSpan, i2, i, 33);
            spannableString.setSpan(textAppearanceSpan2, i3, length, 33);
        }
        return spannableString;
    }

    private int c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BloodPressureData", "sysDiaStr is empty");
            return 0;
        }
        String[] split = str.split("/");
        if (split.length > 0) {
            return split[0].length();
        }
        return 0;
    }

    public String e() {
        return this.f16426a;
    }

    public List<SectionPieChartBasicAdapter.b> h() {
        return new ArrayList(this.j);
    }

    public Map<String, List<SectionPieChartBasicAdapter.b>> n() {
        return this.q;
    }

    private String b(Context context, int i) {
        if (i == 0) {
            return context.getString(R$string.IDS_device_measureactivity_result_low);
        }
        if (i != 1) {
            return i != 5 ? "--" : context.getString(R$string.IDS_device_measureactivity_result_high);
        }
        return context.getString(R$string.IDS_device_measureactivity_result_normal);
    }

    private int a(qkg qkgVar) {
        return (int) (qkgVar.o() - qkgVar.m());
    }

    private int e(Map<Integer, Integer> map, int i) {
        if (map.containsKey(Integer.valueOf(i))) {
            return map.get(Integer.valueOf(i)).intValue();
        }
        return 0;
    }

    public int e(int i) {
        return e(this.e, i);
    }

    public int c(Map<Integer, Integer> map, int i) {
        return e(map, i);
    }

    public List<qkg> j() {
        return this.o;
    }

    public Map<String, Map<Integer, Integer>> k() {
        return this.r;
    }

    public List<qkg> c() {
        return this.b;
    }

    public double a() {
        return this.g;
    }

    public double b() {
        return this.d;
    }

    public double d() {
        return this.c;
    }

    public long g() {
        return this.p;
    }

    public long f() {
        return this.n;
    }

    public String toString() {
        return "BloodPressureData{mHealthDataList=" + this.o + ", mDistMap=" + this.e + ", mJumpTime=" + this.m + '}';
    }
}
