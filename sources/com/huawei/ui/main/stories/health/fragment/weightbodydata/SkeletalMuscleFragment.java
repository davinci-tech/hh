package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import com.huawei.ui.main.stories.health.views.HealthBodyDetailData;
import defpackage.doj;
import defpackage.dph;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class SkeletalMuscleFragment extends WeightBodyDataFragment {
    private int b;
    private int e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 10;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        LogUtil.a("HealthWeight_SkeletalMuscleFragment", "initViewTahiti()");
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
        this.e = (int) this.mWeightBean.getDoubleOrIntLevelByType(10);
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
            c(true);
            return;
        }
        int doubleOrIntLevelByType = (int) this.mWeightBean.getDoubleOrIntLevelByType(1000);
        double[] doubleArrayLevelByType = this.mWeightBean.getDoubleArrayLevelByType(10);
        if (doubleOrIntLevelByType == -1 || !qsj.e(doubleArrayLevelByType, 1)) {
            LogUtil.a("HealthWeight_SkeletalMuscleFragment", "initSpecification skeletalMuscleProgressNew is invalid or skeletalMuscleValuesNew is out of bound.");
            return;
        }
        setIndicatorCardViewVisible();
        float a2 = (float) UnitUtil.a(doubleArrayLevelByType[0]);
        float a3 = (float) UnitUtil.a(doubleArrayLevelByType[1]);
        float calculateRangValue = getCalculateRangValue(a3, 0.1f, true);
        String a4 = a(1);
        String a5 = a(2);
        String a6 = a(3);
        int d = d(1);
        int d2 = d(2);
        int d3 = d(3);
        double[] r = doj.r();
        float a7 = (float) UnitUtil.a(r[0]);
        float a8 = (float) UnitUtil.a(r[1]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(a7, a2, d, a4));
        arrayList.add(new nmm(a2, calculateRangValue, d2, a5));
        arrayList.add(new nmm(calculateRangValue, a8, d3, a6));
        setAllLevelsData(arrayList, new double[]{a2, a3}, new double[]{a7, a8});
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(a4, getMinRangContent(getCalculateRangValue(a2, 0.1f, false)), d));
        arrayList2.add(new WeightBodyDataFragment.b(a5, getComRangContent(a2, a3), d2));
        arrayList2.add(new WeightBodyDataFragment.b(a6, getMaxRangContent(calculateRangValue), d3));
        setLevelInfo(arrayList2);
        c(false);
    }

    private int d(int i) {
        return getColor(qrf.a(i));
    }

    private String a(int i) {
        return qqy.l(0, i);
    }

    private void c(boolean z) {
        double c = UnitUtil.c(this.mWeightBean.aj(), 1);
        String e = UnitUtil.e(c, 1, 1);
        double a2 = UnitUtil.a(c, 1);
        if (z) {
            setDataInfo(getString(R$string.IDS_hw_show_skeletal_muscle_mass), e, qsj.e());
        } else {
            setLevelUnitString(a2, e, qsj.e());
        }
    }

    private void e() {
        if (qsj.b(this.mWeightBean)) {
            setSegmentalCardViewState(0);
            if (this.mSegmentalSubHeader != null) {
                this.mSegmentalSubHeader.setHeadTitleText(this.mResources.getString(R$string.IDS_weight_segmental_skeletal_muscle));
            }
            String string = this.mResources.getString(R$string.IDS_weight_unit);
            if (!LanguageUtil.j(this.mContext)) {
                string = string + " ";
            }
            String str = string + qsj.e();
            HealthTextView healthTextView = (HealthTextView) this.mSegmental.findViewById(R.id.base_weight_segmental_unit);
            healthTextView.setText(str);
            healthTextView.setVisibility(0);
            d();
            return;
        }
        setSegmentalCardViewState(8);
    }

    private void d() {
        double c = UnitUtil.c(this.mWeightBean.y(), 1);
        double c2 = UnitUtil.c(this.mWeightBean.x(), 1);
        double c3 = UnitUtil.c(this.mWeightBean.ak(), 1);
        double c4 = UnitUtil.c(this.mWeightBean.ah(), 1);
        double c5 = doj.c(UnitUtil.a(this.mWeightBean.aj()), c, c2, c3, c4);
        HealthBodyDetailData healthBodyDetailData = (HealthBodyDetailData) this.mSegmental.findViewById(R.id.base_weight_segmental_body_detail_data);
        healthBodyDetailData.setBodyDetailType(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Double.valueOf(c5));
        arrayList.add(Double.valueOf(c));
        arrayList.add(Double.valueOf(c3));
        arrayList.add(Double.valueOf(c2));
        arrayList.add(Double.valueOf(c4));
        qsj.a(healthBodyDetailData, (ArrayList<Double>) arrayList, qsj.c(this.mWeightBean));
        if (Utils.o()) {
            qsj.dIh_(this.mSegmental, R.id.base_weight_segmental_include_body_standard_horizontal);
        }
    }

    private void c() {
        int ac = (int) this.mWeightBean.ac();
        if (dph.s(ac)) {
            setBalanceInfo(false, this.mResources.getString(R$string.IDS_weight_skeletal_muscle_balance_results), this.mResources.getString(R$string.IDS_weight_skeletal_muscle_balance_results_content, qqy.h(ac)), qqy.f(ac), qqy.k(ac));
            return;
        }
        setBalanceResultViewState(8);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        if (this.mWeightBean.aa() == 2) {
            e();
            c();
        }
        if (this.e != -1) {
            b();
        }
        initAbout(qrd.l(0), qrd.l(1));
        initAboutImage(R.drawable._2131430696_res_0x7f0b0d28);
    }

    private void b() {
        initResult(qqy.f(1, this.e, this.b), qqy.f(2, this.e, this.b));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return "skeletalMusclelMass";
    }
}
