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
public class PressureIndexFragment extends WeightBodyDataFragment {
    private double c;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 12;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.c = this.mWeightBean.ad();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            setDataInfo(getString(R$string.IDS_hw_show_pressure_index), UnitUtil.e(this.c, 1, 1), "");
            return;
        }
        double[] k = doj.k();
        setIndicatorCardViewVisible();
        float f = (float) k[0];
        float f2 = (float) k[1];
        float f3 = (float) k[2];
        float calculateRangValue = getCalculateRangValue(f2, 0.1f, true);
        float calculateRangValue2 = getCalculateRangValue(f3, 0.1f, true);
        String b = b(1);
        String b2 = b(2);
        String b3 = b(3);
        String b4 = b(4);
        int color = getColor(qrf.c(1));
        int color2 = getColor(qrf.c(2));
        int color3 = getColor(qrf.c(3));
        int color4 = getColor(qrf.c(4));
        double[] n = doj.n();
        float f4 = (float) n[0];
        float f5 = (float) n[1];
        float calculateRangValue3 = getCalculateRangValue(f, 0.1f, true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(f4, calculateRangValue3, color, b));
        arrayList.add(new nmm(calculateRangValue3, calculateRangValue, color2, b2));
        arrayList.add(new nmm(calculateRangValue, calculateRangValue2, color3, b3));
        arrayList.add(new nmm(calculateRangValue2, f5, color4, b4));
        setAllLevelsData(arrayList, k, n);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(b, getMinRangContent(f), color));
        arrayList2.add(new WeightBodyDataFragment.b(b2, getComRangContent(calculateRangValue3, f2), color2));
        arrayList2.add(new WeightBodyDataFragment.b(b3, getComRangContent(calculateRangValue, f3), color3));
        arrayList2.add(new WeightBodyDataFragment.b(b4, getMaxRangContent(calculateRangValue2), color4));
        setLevelInfo(arrayList2);
        String e = UnitUtil.e(this.c, 1, 1);
        double a2 = UnitUtil.a(this.c, 1);
        this.c = a2;
        setLevel(a2, e);
    }

    private String b(int i) {
        return qqy.g(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initAbout(qrd.m(0), qrd.m(1));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return "pressure";
    }
}
