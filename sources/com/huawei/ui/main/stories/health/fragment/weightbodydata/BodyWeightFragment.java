package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.doj;
import defpackage.dph;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes6.dex */
public class BodyWeightFragment extends WeightBodyDataFragment {
    private int b;
    private int c;
    private double e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 0;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.mGender = this.mWeightBean.an();
        this.c = this.mWeightBean.t();
        this.b = this.mWeightBean.e();
        this.e = this.mWeightBean.j();
        double ax = this.mWeightBean.ax();
        if (dph.e(this.e, this.mIsOversea)) {
            return;
        }
        this.e = doj.a(ax, this.c);
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
        double[] e = doj.e(this.mIsOversea, this.mGender, this.b);
        if (!qsj.e(e, 2)) {
            LogUtil.h("HealthWeight_BodyWeightFragment", "initSpecification, bodyMassIndexValue is out of bound.");
            return;
        }
        setIndicatorCardViewVisible();
        float f = (float) e[0];
        float f2 = (float) e[1];
        float f3 = (float) e[2];
        float calculateRangValue = getCalculateRangValue(f2, 0.1f, true);
        String d = d(1);
        String d2 = d(2);
        String d3 = d(3);
        String d4 = d(4);
        int color = getColor(qrf.a(1));
        int color2 = getColor(qrf.a(2));
        int color3 = getColor(qrf.a(3));
        int color4 = getColor(qrf.a(4));
        double[] d5 = doj.d(Utils.o());
        float f4 = (float) d5[0];
        float f5 = (float) d5[1];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(f4, f, color, d));
        arrayList.add(new nmm(f, calculateRangValue, color2, d2));
        arrayList.add(new nmm(calculateRangValue, f3, color3, d3));
        arrayList.add(new nmm(f3, f5, color4, d4));
        setAllLevelsData(arrayList, e, d5);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(d, "", color));
        arrayList2.add(new WeightBodyDataFragment.b(d2, "", color2));
        arrayList2.add(new WeightBodyDataFragment.b(d3, "", color3));
        arrayList2.add(new WeightBodyDataFragment.b(d4, "", color4));
        setLevelInfo(arrayList2);
        a();
    }

    private String d(int i) {
        return qqy.a(0, i);
    }

    private void a() {
        String e = UnitUtil.e(UnitUtil.a(this.mWeightBean.ax()), 1, this.mWeightBean.getFractionDigitByType(0));
        double a2 = UnitUtil.a(this.e, 1);
        this.e = a2;
        setLevelUnitString(a2, e, qsj.e());
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        String format;
        int fractionDigitByType = this.mWeightBean.getFractionDigitByType(0);
        double a2 = doj.a(this.mGender, this.c, this.b);
        double a3 = UnitUtil.a(this.mWeightBean.ax(), fractionDigitByType) - UnitUtil.a(a2, fractionDigitByType);
        double abs = Math.abs(a3);
        double a4 = UnitUtil.a(a2);
        double a5 = UnitUtil.a(abs);
        int a6 = UnitUtil.a();
        int i = a6 == 1 ? R.plurals._2130903105_res_0x7f030041 : a6 == 3 ? R.plurals._2130903216_res_0x7f0300b0 : R.plurals._2130903215_res_0x7f0300af;
        String quantityString = this.mResources.getQuantityString(i, qrp.a(a4), UnitUtil.e(a4, 1, 1));
        String quantityString2 = this.mResources.getQuantityString(i, qrp.a(a5), UnitUtil.e(a5, 1, fractionDigitByType));
        if (a3 > 0.0d) {
            format = String.format(this.mResources.getString(R$string.IDS_hw_weight_suggest_description_more_than_ideal), quantityString, quantityString2);
        } else if (a3 < 0.0d) {
            format = String.format(this.mResources.getString(R$string.IDS_hw_weight_suggest_description_less_than_ideal), quantityString, quantityString2);
        } else {
            format = String.format(this.mResources.getString(R$string.IDS_hw_weight_suggest_description_equal_ideal), quantityString);
        }
        initIdealForGlobal(this.mResources.getString(R$string.IDS_weight_ideal), String.format(Locale.ENGLISH, this.mResources.getString(R$string.IDS_hwh_home_ideal_weight), qsj.e(a4, 1)), format);
        initAbout(qrd.i(0), qrd.i(1));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return "bodyWeight";
    }
}
