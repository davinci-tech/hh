package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.view.View;
import com.huawei.health.R;
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
public class MuscleMassFragment extends WeightBodyDataFragment {
    private int b;
    private int e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 6;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        LogUtil.a("HealthWeight_MuscleMassFragment", "initViewTahiti()");
        qsj.a().dIk_(this.mContext, this.mAboutImage);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initView(View view) {
        super.initView(view);
        qsj.a().dIk_(this.mContext, this.mAboutImage);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.b = this.mWeightBean.e();
        this.mGender = this.mWeightBean.an();
        this.e = (int) this.mWeightBean.getDoubleOrIntLevelByType(6);
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
            d(true);
            return;
        }
        int doubleOrIntLevelByType = (int) this.mWeightBean.getDoubleOrIntLevelByType(600);
        double[] doubleArrayLevelByType = this.mWeightBean.getDoubleArrayLevelByType(6);
        if (doubleOrIntLevelByType == -1 || !qsj.e(doubleArrayLevelByType, 1)) {
            LogUtil.h("HealthWeight_MuscleMassFragment", "initSpecification, muscleMassProgressNew is invalid or muscleMassValuesNew is out of bound.");
            return;
        }
        setIndicatorCardViewVisible();
        float a2 = (float) UnitUtil.a(doubleArrayLevelByType[0]);
        float a3 = (float) UnitUtil.a(doubleArrayLevelByType[1]);
        float calculateRangValue = getCalculateRangValue(a3, 0.1f, true);
        String h = qqy.h(0, 1);
        String h2 = qqy.h(0, 2);
        String h3 = qqy.h(0, 3);
        int color = getColor(qrf.a(1));
        int color2 = getColor(qrf.a(2));
        int color3 = getColor(qrf.a(3));
        double[] m = doj.m();
        float a4 = (float) UnitUtil.a(m[0]);
        float a5 = (float) UnitUtil.a(m[1]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(a4, a2, color, h));
        arrayList.add(new nmm(a2, calculateRangValue, color2, h2));
        arrayList.add(new nmm(calculateRangValue, a5, color3, h3));
        setAllLevelsData(arrayList, new double[]{a2, a3}, new double[]{a4, a5});
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(h, getMinRangContent(getCalculateRangValue(a2, 0.1f, false)), color));
        arrayList2.add(new WeightBodyDataFragment.b(h2, getComRangContent(a2, a3), color2));
        arrayList2.add(new WeightBodyDataFragment.b(h3, getMaxRangContent(calculateRangValue), color3));
        setLevelInfo(arrayList2);
        d(false);
    }

    private void d(boolean z) {
        double a2 = UnitUtil.a(this.mWeightBean.z());
        String e = UnitUtil.e(a2, 1, 1);
        double a3 = UnitUtil.a(a2, 1);
        if (z) {
            setDataInfo(getString(R$string.IDS_hw_show_muscle), e, qsj.e());
        } else {
            setLevelUnitString(a3, e, qsj.e());
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        int c;
        if (this.e != -1) {
            initResult("", (!LanguageUtil.m(this.mContext) || (c = qre.c(this.b)) == -1) ? "" : qre.d(qre.b(6, this.mGender, c, this.e), this.mWeightBean.au()));
        }
        initAbout(qrd.k(0), qrd.k(1));
        initAboutImage(R.drawable._2131430678_res_0x7f0b0d16);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return BleConstants.MUSCLE_MASS;
    }
}
