package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.doj;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class HeartRateFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10199a;
    private double b;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 11;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        double p = this.mWeightBean.p();
        this.b = p;
        this.f10199a = doj.b(p);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        double[] j = doj.j();
        setIndicatorCardViewVisible();
        int i = (int) j[0];
        int i2 = (int) j[1];
        int i3 = (int) j[2];
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        String c = c(1);
        String c2 = c(2);
        String c3 = c(3);
        String c4 = c(4);
        int a2 = a(1);
        int a3 = a(2);
        int a4 = a(3);
        int a5 = a(4);
        double[] g = doj.g();
        float f = (float) g[0];
        float f2 = (float) g[1];
        ArrayList arrayList = new ArrayList();
        float f3 = i;
        arrayList.add(new nmm(f, f3, a2, c));
        float f4 = i4;
        arrayList.add(new nmm(f3, f4, a3, c2));
        float f5 = i5;
        arrayList.add(new nmm(f4, f5, a4, c3));
        arrayList.add(new nmm(f5, f2, a5, c4));
        setAllLevelsData(arrayList, j, g);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(c, this.mContext.getString(R$string.IDS_bp_min_scope_value, UnitUtil.e(i - 1, 1, 0)), a2));
        arrayList2.add(new WeightBodyDataFragment.b(c2, this.mContext.getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0)), a3));
        arrayList2.add(new WeightBodyDataFragment.b(c3, this.mContext.getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(i4, 1, 0), UnitUtil.e(i3, 1, 0)), a4));
        arrayList2.add(new WeightBodyDataFragment.b(c4, this.mContext.getString(R$string.IDS_bp_max_explain, UnitUtil.e(i5, 1, 0)), a5));
        setLevelInfo(arrayList2);
        String e = UnitUtil.e(this.b, 1, 0);
        double a6 = UnitUtil.a(this.b, 0);
        this.b = a6;
        setLevelUnitString(a6, e, getString(R$string.ie_main_watch_heart_rate_unit_string));
    }

    private int a(int i) {
        return getColor(qrf.a(i));
    }

    private String c(int i) {
        return qqy.i(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initResultForGlobal(qqy.i(1, this.f10199a), qqy.i(2, this.f10199a));
        initAbout(qrd.f(0), qrd.f(1));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return IndoorEquipManagerApi.KEY_HEART_RATE;
    }
}
