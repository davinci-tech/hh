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
public class TotalBodyWaterFragment extends WeightBodyDataFragment {
    private int c;
    private int e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 3;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.e = this.mWeightBean.e();
        this.mGender = this.mWeightBean.an();
        this.c = (int) this.mWeightBean.getDoubleOrIntLevelByType(3);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            setDataInfo(getString(R$string.IDS_hw_show_water), UnitUtil.e(this.mWeightBean.ap(), 2, 1), "");
            return;
        }
        int doubleOrIntLevelByType = (int) this.mWeightBean.getDoubleOrIntLevelByType(300);
        double[] f = doj.f(this.mGender, this.e);
        if (doubleOrIntLevelByType == -1 || !qsj.e(f, 1)) {
            LogUtil.a("HealthWeight_TotalBodyWaterFragment", "initSpecification totalBodyWaterProgressNew is invalid or totalBodyWatersNew is out of bound.");
            return;
        }
        setIndicatorCardViewVisible();
        float f2 = (float) f[0];
        float f3 = (float) f[1];
        float calculateRangValue = getCalculateRangValue(f3, 0.1f, true);
        String d = d(1);
        String d2 = d(2);
        String d3 = d(3);
        int b = b(1);
        int b2 = b(2);
        int b3 = b(3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(0.0f, f2, b, d));
        arrayList.add(new nmm(f2, calculateRangValue, b2, d2));
        arrayList.add(new nmm(calculateRangValue, 100.0f, b3, d3));
        setAllLevelsData(arrayList, f, new double[]{0.0d, 100.0d});
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(d, getMinRangContent(getCalculateRangValue(f2, 0.1f, false)), b));
        arrayList2.add(new WeightBodyDataFragment.b(d2, getComRangContent(f2, f3), b2));
        arrayList2.add(new WeightBodyDataFragment.b(d3, getMaxRangContent(calculateRangValue), b3));
        setLevelInfo(arrayList2);
        setLevelUnitString(UnitUtil.a(this.mWeightBean.ap(), 1), UnitUtil.e(this.mWeightBean.ap(), 1, 1), getString(R$string.IDS_hw_health_show_healthdata_weight_percent));
    }

    private int b(int i) {
        return getColor(qrf.a(i));
    }

    private String d(int i) {
        return qqy.m(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        if (this.c != -1) {
            e();
        }
        initAbout(qrd.s(0), qrd.s(1));
    }

    private void e() {
        initResult(qqy.i(1, this.c, this.e), qqy.i(2, this.c, this.e));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return BleConstants.MOISTURE_RATE;
    }
}
