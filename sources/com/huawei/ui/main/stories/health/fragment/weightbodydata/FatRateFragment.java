package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.app.Activity;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.FatRateFragment;
import com.huawei.ui.main.stories.health.views.HealthBodyDetailData;
import defpackage.cfi;
import defpackage.cpa;
import defpackage.cps;
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
public class FatRateFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private double f10198a;
    private int b;
    private int c;
    private int d;
    private double e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 1;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initView(View view) {
        super.initView(view);
        if (this.mIsOversea || !this.mWeightBean.isVisible(32, this.mIsOversea)) {
            this.mIdealDescription.setVisibility(8);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.d = this.mWeightBean.e();
        this.mGender = this.mWeightBean.an();
        this.c = this.mWeightBean.t();
        this.f10198a = this.mWeightBean.ax();
        this.e = this.mWeightBean.a();
        this.b = (int) this.mWeightBean.getDoubleOrIntLevelByType(1);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            a(true);
            return;
        }
        double[] doubleArrayLevelByType = this.mWeightBean.getDoubleArrayLevelByType(1);
        if (this.b == -1 || !qsj.e(doubleArrayLevelByType, 2)) {
            LogUtil.h("HealthWeight_FatRateFragment", "initSpecification mGradeNew is invalid", "or fat rates standard range out of bound");
            return;
        }
        setIndicatorCardViewVisible();
        float f = (float) doubleArrayLevelByType[0];
        float f2 = (float) doubleArrayLevelByType[1];
        float f3 = (float) doubleArrayLevelByType[2];
        float calculateRangValue = getCalculateRangValue(f2, 0.1f, true);
        float calculateRangValue2 = getCalculateRangValue(f3, 0.1f, true);
        String d = d(1);
        String d2 = d(2);
        String d3 = d(3);
        String d4 = d(4);
        int b = b(1);
        int b2 = b(2);
        int b3 = b(3);
        int b4 = b(4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(0.0f, f, b, d));
        arrayList.add(new nmm(f, calculateRangValue, b2, d2));
        arrayList.add(new nmm(calculateRangValue, calculateRangValue2, b3, d3));
        arrayList.add(new nmm(calculateRangValue2, 100.0f, b4, d4));
        setAllLevelsData(arrayList, doubleArrayLevelByType, new double[]{0.0d, 100.0d});
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(d, getMinRangContent(getCalculateRangValue(f, 0.1f, false)), b));
        arrayList2.add(new WeightBodyDataFragment.b(d2, getComRangContent(f, f2), b2));
        arrayList2.add(new WeightBodyDataFragment.b(d3, getComRangContent(calculateRangValue, f3), b3));
        arrayList2.add(new WeightBodyDataFragment.b(d4, getMaxRangContent(calculateRangValue2), b4));
        setLevelInfo(arrayList2);
        a(false);
    }

    private void a(boolean z) {
        String e = UnitUtil.e(this.e, 2, 1);
        if (z) {
            setDataInfo(getString(R$string.IDS_hw_show_body_fat), UnitUtil.bCR_(this.mContext, "[\\.\\d]", e, R.style.health_weight_body_data_value, R.style.health_weight_body_data_unit));
        } else if (((int) this.mWeightBean.getDoubleOrIntLevelByType(101)) != -1) {
            setLevelUnitString(UnitUtil.a(this.e, 1), UnitUtil.e(this.e, 1, 1), getString(R$string.IDS_hw_health_show_healthdata_weight_percent));
        }
    }

    private int b(int i) {
        return getColor(qrf.a(i));
    }

    private String d(int i) {
        return qqy.f(0, i);
    }

    private void b() {
        if (qsj.a(this.mWeightBean)) {
            setSegmentalCardViewState(0);
            if (this.mSegmentalSubHeader != null) {
                this.mSegmentalSubHeader.setHeadTitleText(this.mResources.getString(R$string.IDS_weight_segmental_fat));
            }
            String string = this.mResources.getString(R$string.IDS_weight_unit);
            if (!LanguageUtil.j(this.mContext)) {
                string = string + " ";
            }
            String str = string + qsj.e(0.0d, false);
            HealthTextView healthTextView = (HealthTextView) this.mSegmental.findViewById(R.id.base_weight_segmental_unit);
            healthTextView.setText(str);
            healthTextView.setVisibility(0);
            c();
            return;
        }
        setSegmentalCardViewState(8);
    }

    private void c() {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser.p()) {
            c(currentUser.g());
        } else {
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qjq
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    FatRateFragment.this.b(i, (cfi) obj);
                }
            });
        }
    }

    public /* synthetic */ void b(final int i, final cfi cfiVar) {
        if (!(this.mContext instanceof Activity)) {
            LogUtil.h("HealthWeight_FatRateFragment", "mContext is null or mContext is not Activity");
        } else {
            ((Activity) this.mContext).runOnUiThread(new Runnable() { // from class: qjp
                @Override // java.lang.Runnable
                public final void run() {
                    FatRateFragment.this.c(cfiVar, i);
                }
            });
        }
    }

    public /* synthetic */ void c(cfi cfiVar, int i) {
        if (cfiVar == null || i != 0) {
            LogUtil.h("HealthWeight_FatRateFragment", "loadDataSuccess getCurrentUser: currentUser is null return");
            cfiVar = MultiUsersManager.INSTANCE.getCurrentUser();
        }
        c(cfiVar.g());
    }

    private void c(int i) {
        double c = this.mWeightBean.c();
        if (c <= 0.0d) {
            c = doj.a(this.e, this.f10198a, 1);
        }
        double c2 = UnitUtil.c(this.mWeightBean.v(), 1);
        double c3 = UnitUtil.c(this.mWeightBean.u(), 1);
        double c4 = UnitUtil.c(this.mWeightBean.am(), 1);
        double c5 = UnitUtil.c(this.mWeightBean.ai(), 1);
        double e = doj.e(UnitUtil.a(c), c2, c3, c4, c5);
        HealthBodyDetailData healthBodyDetailData = (HealthBodyDetailData) this.mSegmental.findViewById(R.id.base_weight_segmental_body_detail_data);
        healthBodyDetailData.setBodyDetailType(0);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Double.valueOf(e));
        arrayList.add(Double.valueOf(c2));
        arrayList.add(Double.valueOf(c4));
        arrayList.add(Double.valueOf(c3));
        arrayList.add(Double.valueOf(c5));
        qsj.a(healthBodyDetailData, (ArrayList<Double>) arrayList, qsj.d(e(i), qsj.b(doj.i(this.mGender, r1.l(), this.d), doj.i(this.mGender, r1.ad(), this.d), c2, c4), qsj.b(doj.c(this.mGender, r1.o(), this.d), doj.c(this.mGender, r1.z(), this.d), c3, c5), this.mWeightBean));
        healthBodyDetailData.setVisibility(0);
        if (Utils.o()) {
            qsj.dIh_(this.mSegmental, R.id.base_weight_segmental_include_body_standard_horizontal);
        }
    }

    private cps e(int i) {
        if (cpa.c(this.mWeightBean)) {
            return new cps(this.c, (float) this.f10198a, this.mGender, this.d, i, 8, this.mWeightBean.ag(), 2, this.mWeightBean.q());
        }
        return new cps(this.c, (float) this.f10198a, this.mGender, this.d, i, this.mWeightBean.ag());
    }

    private void a() {
        int o = (int) this.mWeightBean.o();
        if (dph.h(o)) {
            setBalanceInfo(false, this.mResources.getString(R$string.IDS_weight_fat_balance_results), this.mResources.getString(R$string.IDS_weight_fat_balance_results_content, qqy.j(o)), qqy.g(o), "");
        } else {
            setBalanceResultViewState(8);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        String e;
        if (this.mWeightBean.aa() == 2) {
            b();
            a();
        }
        double doubleOrIntLevelByType = this.mWeightBean.getDoubleOrIntLevelByType(102);
        if (!doj.b(doubleOrIntLevelByType, -1.0d)) {
            if (LanguageUtil.bp(this.mContext)) {
                e = this.mResources.getString(R$string.IDS_hw_health_show_healthdata_weight_percent) + doubleOrIntLevelByType;
            } else {
                e = UnitUtil.e(doubleOrIntLevelByType, 2, 1);
            }
            if (!LanguageUtil.j(this.mContext)) {
                e = " " + e;
            }
            String string = this.mResources.getString(R$string.IDS_weight_ideal_body_fat_rate, e);
            String string2 = this.mResources.getString(R$string.IDS_hw_weight_ideal_body_fat_ratiodes_des);
            String format = String.format(this.mResources.getString(R$string.IDS_hw_weight_suggest_description_ideal_fat), e);
            if (!LanguageUtil.j(this.mContext)) {
                string2 = string2 + " ";
            }
            initIdeal(this.mResources.getString(R$string.IDS_weight_ideal_body_fat_rate_name), string, string2 + format);
        }
        if (this.b != -1) {
            d();
        }
        initAbout(qrd.j(0), qrd.j(1));
    }

    private void d() {
        initResult(qqy.c(1, this.b, this.d), qqy.c(2, this.b, this.d));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return BleConstants.BODY_FAT_RATE;
    }
}
