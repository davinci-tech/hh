package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.doj;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import defpackage.qrp;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RelativeAppendicularSkeletalMuscleFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private double f10201a;
    private byte b;
    private int c;
    private int e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 26;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.b = this.mWeightBean.an();
        this.f10201a = this.mWeightBean.af();
        int e = this.mWeightBean.e();
        this.c = e;
        this.e = doj.e(this.b, this.f10201a, e);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            setDataInfo(getString(R$string.IDS_weight_limb_skeletal_muscle_index), UnitUtil.e(this.f10201a, 1, 1), getString(R$string.IDS_weight_kg_square));
            return;
        }
        double[] a2 = doj.a(this.b, this.c);
        setIndicatorCardViewVisible();
        float f = (float) a2[0];
        float f2 = (float) a2[1];
        float a3 = (float) UnitUtil.a(getCalculateRangValue(f2, 0.1f, true), 1);
        String d = d(1);
        String d2 = d(2);
        String d3 = d(3);
        int c = c(1);
        int c2 = c(2);
        int c3 = c(3);
        doj.b(this.b, this.f10201a, this.c);
        double[] s = doj.s();
        float f3 = (float) s[0];
        float f4 = (float) s[1];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(f3, f, c, d));
        arrayList.add(new nmm(f, a3, c2, d2));
        arrayList.add(new nmm(a3, f4, c3, d3));
        setAllLevelsData(arrayList, a2, s);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(d, getMinRangContent((float) UnitUtil.a(getCalculateRangValue(f, 0.1f, false), 1)), c));
        arrayList2.add(new WeightBodyDataFragment.b(d2, getComRangContent(f, f2), c2));
        arrayList2.add(new WeightBodyDataFragment.b(d3, getMaxRangContent(a3), c3));
        setLevelInfo(arrayList2);
        String e = UnitUtil.e(this.f10201a, 1, 1);
        double a4 = UnitUtil.a(this.f10201a, 1);
        this.f10201a = a4;
        setLevelUnitString(a4, e, getString(R$string.IDS_weight_kg_square));
    }

    private int c(int i) {
        return getColor(qrf.a(i));
    }

    private String d(int i) {
        return qqy.k(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initResult(qqy.k(1, this.e), qqy.k(2, this.e));
        initAbout(qrd.o(0), qrd.o(1));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return "rasm";
    }
}
