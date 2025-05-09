package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.util.Pair;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.doj;
import defpackage.koq;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class FatFreeFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10197a;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 14;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.f10197a = (int) this.mWeightBean.getDoubleOrIntLevelByType(14);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public List<Pair<Long, Float>> buildTrendData(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            double d = hiHealthData.getDouble(BleConstants.BODY_FAT_RATE);
            if (d > 0.0d) {
                double a2 = UnitUtil.a(UnitUtil.a(doj.e(d, hiHealthData.getDouble("bodyWeight"))), 1);
                if (a2 > 0.0d) {
                    arrayList.add(new Pair(Long.valueOf(hiHealthData.getStartTime()), Float.valueOf((float) a2)));
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            a(true);
            return;
        }
        double[] doubleArrayLevelByType = this.mWeightBean.getDoubleArrayLevelByType(14);
        if (!qsj.e(doubleArrayLevelByType, 1) || this.f10197a == -1) {
            LogUtil.h("HealthWeight_FatFreeFragment", "initSpecification fatFreeValuesNew is out of bound or mGradeNew is out of bound.");
            return;
        }
        setIndicatorCardViewVisible();
        float a2 = (float) UnitUtil.a(doubleArrayLevelByType[0]);
        float a3 = (float) UnitUtil.a(doubleArrayLevelByType[1]);
        float calculateRangValue = getCalculateRangValue(a3, 0.1f, true);
        String c = qqy.c(0, 2);
        String c2 = qqy.c(0, 3);
        int color = getColor(qrf.a(1));
        int color2 = getColor(qrf.a(2));
        int color3 = getColor(qrf.a(3));
        double[] f = doj.f();
        float a4 = (float) UnitUtil.a(f[0]);
        float a5 = (float) UnitUtil.a(f[1]);
        String c3 = qqy.c(0, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(a4, a2, color, c3));
        arrayList.add(new nmm(a2, calculateRangValue, color2, c));
        arrayList.add(new nmm(calculateRangValue, a5, color3, c2));
        setAllLevelsData(arrayList, new double[]{a2, a3}, new double[]{a4, a5});
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(c3, getMinRangContent(getCalculateRangValue(a2, 0.1f, false)), color));
        arrayList2.add(new WeightBodyDataFragment.b(c, getComRangContent(a2, a3), color2));
        arrayList2.add(new WeightBodyDataFragment.b(c2, getMaxRangContent(calculateRangValue), color3));
        setLevelInfo(arrayList2);
        a(false);
    }

    private void a(boolean z) {
        double a2 = UnitUtil.a(doj.e(this.mWeightBean.a(), this.mWeightBean.ax()));
        String e = UnitUtil.e(a2, 1, 1);
        double a3 = UnitUtil.a(a2, 1);
        if (z) {
            setDataInfo(getString(R$string.IDS_weight_fat_free), e, qsj.e());
        } else {
            setLevelUnitString(a3, e, qsj.e());
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        int i = this.f10197a;
        if (i != -1) {
            initResult(qqy.d(1, i, this.mWeightBean.e()), qqy.d(2, this.f10197a, this.mWeightBean.e()));
        }
        initAbout(qrd.g(0), qrd.g(1));
    }
}
