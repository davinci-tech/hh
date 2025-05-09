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
public class BoneMineralContentFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10196a;
    private int d;
    private int e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 7;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.d = this.mWeightBean.e();
        this.e = this.mWeightBean.getFractionDigitByType(7);
        this.f10196a = (int) this.mWeightBean.getDoubleOrIntLevelByType(7);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isSetUnit() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            a(true);
            return;
        }
        int doubleOrIntLevelByType = (int) this.mWeightBean.getDoubleOrIntLevelByType(700);
        double[] doubleArrayLevelByType = this.mWeightBean.getDoubleArrayLevelByType(7);
        if (doubleOrIntLevelByType == -1 || !qsj.e(doubleArrayLevelByType, 1)) {
            LogUtil.h("HealthWeight_BoneMineralContentFragment", "initSpecification, boneMineralContentProgressNew is invalid.");
            return;
        }
        setIndicatorCardViewVisible();
        float c = (float) UnitUtil.c(doubleArrayLevelByType[0], 2);
        float c2 = (float) UnitUtil.c(doubleArrayLevelByType[1], 2);
        float calculateRangValue = getCalculateRangValue(c2, 0.01f, true);
        String a2 = a(1);
        String a3 = a(2);
        String a4 = a(3);
        int c3 = c(1);
        int c4 = c(2);
        int c5 = c(3);
        double[] d = doj.d();
        float a5 = (float) UnitUtil.a(d[0]);
        float a6 = (float) UnitUtil.a(d[1]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(a5, c, c3, a2));
        arrayList.add(new nmm(c, calculateRangValue, c4, a3));
        arrayList.add(new nmm(calculateRangValue, a6, c5, a4));
        setAllLevelsData(arrayList, new double[]{c, c2}, new double[]{a5, a6});
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(a2, this.mContext.getString(R$string.IDS_bp_min_scope_value, UnitUtil.e(getCalculateRangValue(c, 0.01f, false), 1, 2)), c3));
        arrayList2.add(new WeightBodyDataFragment.b(a3, a(c, c2), c4));
        arrayList2.add(new WeightBodyDataFragment.b(a4, this.mContext.getString(R$string.IDS_bp_max_explain, UnitUtil.e(calculateRangValue, 1, 2)), c5));
        setLevelInfo(arrayList2);
        a(false);
    }

    private String a(float f, float f2) {
        return this.mContext.getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(f, 1, 2), UnitUtil.e(f2, 1, 2));
    }

    private int c(int i) {
        return getColor(qrf.a(i));
    }

    private String a(int i) {
        return qqy.d(0, i);
    }

    private void a(boolean z) {
        double a2 = UnitUtil.a(this.mWeightBean.i());
        String e = UnitUtil.e(a2, 1, this.e);
        double a3 = UnitUtil.a(a2, this.e);
        if (z) {
            setDataInfo(getString(R$string.IDS_hw_show_bone), e, qsj.e());
        } else {
            setLevelUnitString(a3, e, qsj.e());
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        if (this.f10196a != -1) {
            e();
        }
        initAbout(qrd.h(0), qrd.h(1));
    }

    private void e() {
        initResult(qqy.a(1, this.f10196a, this.d), qqy.a(2, this.f10196a, this.d));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return BleConstants.BONE_SALT;
    }
}
