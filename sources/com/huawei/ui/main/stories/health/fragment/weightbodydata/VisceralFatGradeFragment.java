package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.doj;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class VisceralFatGradeFragment extends WeightBodyDataFragment {
    private int c;
    private int d;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 5;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.d = this.mWeightBean.e();
        this.c = (int) this.mWeightBean.getDoubleOrIntLevelByType(5);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            c(true);
            return;
        }
        int doubleOrIntLevelByType = (int) this.mWeightBean.getDoubleOrIntLevelByType(500);
        double[] aa = doj.aa();
        if (doubleOrIntLevelByType == -1 || !qsj.e(aa, 2)) {
            LogUtil.h("HealthWeight_VisceralFatGradeFragment", "visceralFatGradeProgressNew is not invalid or visceralFatGradesNew is out of bound");
            return;
        }
        setIndicatorCardViewVisible();
        float f = (float) aa[0];
        float f2 = (float) aa[1];
        float f3 = (float) aa[2];
        String c = c(1);
        String c2 = c(2);
        String c3 = c(3);
        String c4 = c(4);
        int d = d(1);
        int d2 = d(2);
        int d3 = d(3);
        int d4 = d(4);
        double[] v = doj.v();
        float f4 = (float) v[0];
        float f5 = (float) v[1];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(f4, f, d, c));
        arrayList.add(new nmm(f, f2, d2, c2));
        arrayList.add(new nmm(f2, f3, d3, c3));
        arrayList.add(new nmm(f3, f5, d4, c4));
        setAllLevelsData(arrayList, aa, v);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(c, getMinRangContent(getCalculateRangValue(f, 0.1f, false)), d));
        arrayList2.add(new WeightBodyDataFragment.b(c2, getComRangContent(f, getCalculateRangValue(f2, 0.1f, false)), d2));
        arrayList2.add(new WeightBodyDataFragment.b(c3, getComRangContent(f2, getCalculateRangValue(f3, 0.1f, false)), d3));
        arrayList2.add(new WeightBodyDataFragment.b(c4, getMaxRangContent(f3), d4));
        setLevelInfo(arrayList2);
        c(false);
    }

    private void c(boolean z) {
        String e = UnitUtil.e(this.mWeightBean.s(), 1, 1);
        String string = getString(R$string.IDS_hw_show_haslet_unit);
        if (z) {
            setDataInfo(getString(R$string.IDS_hw_show_haslet), e, string);
        } else {
            setLevelUnitString(UnitUtil.a(this.mWeightBean.s(), 1), e, string);
        }
    }

    private int d(int i) {
        return getColor(qrf.f(i));
    }

    private String c(int i) {
        return qqy.n(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        if (this.c != -1) {
            d();
        }
        initAbout(qrd.q(0), qrd.q(1));
    }

    private void d() {
        initResult(qqy.j(1, this.c, this.d), qqy.j(2, this.c, this.d));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return BleConstants.VISCERAL_FAT_LEVEL;
    }
}
