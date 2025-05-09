package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.doj;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qre;
import defpackage.qrf;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class BasalMetabolicRateFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10192a;
    private int b;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 4;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.b = this.mWeightBean.e();
        this.mGender = this.mWeightBean.an();
        int doubleOrIntLevelByType = (int) this.mWeightBean.getDoubleOrIntLevelByType(4);
        this.f10192a = doubleOrIntLevelByType;
        LogUtil.a("HealthWeight_BasalMetabolicRateFragment", "initData mGradNew = ", Integer.valueOf(doubleOrIntLevelByType));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            setDataInfo(getString(R$string.IDS_hw_show_metabolism), UnitUtil.e(this.mWeightBean.d(), 1, 1), getString(R$string.IDS_hw_show_sport_cal_unit_new));
            return;
        }
        int doubleOrIntLevelByType = (int) this.mWeightBean.getDoubleOrIntLevelByType(400);
        double[] doubleArrayLevelByType = this.mWeightBean.getDoubleArrayLevelByType(4);
        if (doubleOrIntLevelByType == -1 || !qsj.e(doubleArrayLevelByType, 1)) {
            LogUtil.h("HealthWeight_BasalMetabolicRateFragment", "initSpecification, basalMetabolicRateProgressNew is null or basalMetabolicRatesNew is out of bound.");
            return;
        }
        setIndicatorCardViewVisible();
        float f = (float) doubleArrayLevelByType[0];
        float f2 = (float) doubleArrayLevelByType[1];
        float calculateRangValue = getCalculateRangValue(f2, 0.1f, true);
        String b = b(1);
        String b2 = b(2);
        String b3 = b(3);
        int e = e(1);
        int e2 = e(2);
        int e3 = e(3);
        double[] a2 = doj.a();
        float f3 = (float) a2[0];
        float f4 = (float) a2[1];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(f3, f, e, b));
        arrayList.add(new nmm(f, calculateRangValue, e2, b2));
        arrayList.add(new nmm(calculateRangValue, f4, e3, b3));
        setAllLevelsData(arrayList, doubleArrayLevelByType, a2);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(b, getMinRangContent(getCalculateRangValue(f, 0.1f, false)), e));
        arrayList2.add(new WeightBodyDataFragment.b(b2, getComRangContent(f, f2), e2));
        arrayList2.add(new WeightBodyDataFragment.b(b3, getMaxRangContent(calculateRangValue), e3));
        setLevelInfo(arrayList2);
        setLevelUnitString(UnitUtil.a(this.mWeightBean.d(), 1), UnitUtil.e(this.mWeightBean.d(), 1, 1), getString(R$string.IDS_hw_show_sport_cal_unit_new));
    }

    private int e(int i) {
        return getColor(qrf.a(i));
    }

    private String b(int i) {
        return qqy.b(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        if (this.f10192a != -1) {
            d();
        }
        initAbout(qrd.a(0), qrd.a(1));
    }

    private void d() {
        String e = qqy.e(1, this.f10192a, this.b);
        String e2 = qqy.e(2, this.f10192a, this.b);
        if (!this.mWeightBean.isNewScaleType()) {
            if (LanguageUtil.m(this.mContext)) {
                int c = qre.c(this.b);
                if (c != -1) {
                    e2 = qre.d(qre.b(4, this.mGender, c, this.f10192a), this.mWeightBean.au());
                }
                e = "";
            } else {
                e = "";
                e2 = e;
            }
        }
        initResult(e, e2);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return BleConstants.BASAL_METABOLISM;
    }
}
