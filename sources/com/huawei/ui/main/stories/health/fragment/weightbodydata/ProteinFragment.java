package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.util.Pair;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import com.huawei.ui.main.stories.health.util.TrendFragmentCallback;
import defpackage.cfe;
import defpackage.doj;
import defpackage.dph;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class ProteinFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10200a;
    private int c;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 8;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.f10200a = this.mWeightBean.e();
        this.mGender = this.mWeightBean.an();
        this.c = (int) this.mWeightBean.getDoubleOrIntLevelByType(8);
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
        if (((int) this.mWeightBean.getDoubleOrIntLevelByType(800)) == -1) {
            LogUtil.h("HealthWeight_ProteinFragment", "initSpecification, proteinRateProgressNew is invalid.");
            return;
        }
        double[] e = doj.e(this.mGender, this.f10200a);
        if (!qsj.e(e, 1)) {
            LogUtil.h("HealthWeight_ProteinFragment", "initSpecification, proteinRatesNew is out of bound.");
            return;
        }
        if (this.c == -1) {
            LogUtil.h("HealthWeight_ProteinFragment", "initSpecification, mGradeNew is invalid.");
            return;
        }
        setIndicatorCardViewVisible();
        float f = (float) e[0];
        float f2 = (float) e[1];
        float calculateRangValue = getCalculateRangValue(f2, 0.1f, true);
        String j = qqy.j(0, 1);
        String j2 = qqy.j(0, 2);
        String j3 = qqy.j(0, 3);
        int color = getColor(qrf.a(1));
        int color2 = getColor(qrf.a(2));
        int color3 = getColor(qrf.a(3));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(0.0f, f, color, j));
        arrayList.add(new nmm(f, calculateRangValue, color2, j2));
        arrayList.add(new nmm(calculateRangValue, 100.0f, color3, j3));
        setAllLevelsData(arrayList, e, new double[]{0.0d, 100.0d});
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WeightBodyDataFragment.b(j, getMinRangContent(getCalculateRangValue(f, 0.1f, false)), color));
        arrayList2.add(new WeightBodyDataFragment.b(j2, getComRangContent(f, f2), color2));
        arrayList2.add(new WeightBodyDataFragment.b(j3, getMaxRangContent(calculateRangValue), color3));
        setLevelInfo(arrayList2);
        c(false);
    }

    private void c(boolean z) {
        double a2 = a(this.mWeightBean);
        String e = UnitUtil.e(a2, (LanguageUtil.j(this.mContext) || LanguageUtil.p(this.mContext)) ? 2 : 1, 1);
        if (z) {
            setDataInfo(getString(R$string.IDS_hw_show_protein), UnitUtil.bCR_(this.mContext, "[\\.\\d]", e, R.style.health_weight_body_data_value, R.style.health_weight_body_data_unit));
            return;
        }
        String e2 = UnitUtil.e(a2, 1, 1);
        double a3 = UnitUtil.a(a2, 1);
        setLevelSpannableString(a3, e);
        setLevelUnitString(a3, e2, getString(R$string.IDS_hw_health_show_healthdata_weight_percent));
    }

    private double a(cfe cfeVar) {
        if (!cfeVar.isVisible(8, false)) {
            return 0.0d;
        }
        double ap = cfeVar.ap();
        if (!dph.t(ap)) {
            ap = cfeVar.al();
        }
        return doj.d(cfeVar.ax(), ap, cfeVar.a(), cfeVar.i(), cfeVar.getFractionDigitByType(0));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        if (this.c != -1) {
            e();
        }
        initAbout(qrd.n(0), qrd.n(1));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public HiAggregateOption getHiAggregateOption() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(getTrendStartTime(), getTrendEndTime());
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        LogUtil.a("HealthWeight_ProteinFragment", "getChartData() aggregateOption = ", hiAggregateOption.toString());
        return hiAggregateOption;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void getChartData() {
        qsj.c(MultiUsersManager.INSTANCE.getCurrentUser(), getHiAggregateOption(), new TrendFragmentCallback() { // from class: com.huawei.ui.main.stories.health.fragment.weightbodydata.ProteinFragment.5
            @Override // com.huawei.ui.main.stories.health.util.TrendFragmentCallback
            public void getWeight(ArrayList<cfe> arrayList, boolean z) {
                Collections.reverse(arrayList);
                ProteinFragment.this.updateTrendData(ProteinFragment.this.a(arrayList));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Pair<Long, Float>> a(ArrayList<cfe> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(arrayList)) {
            return arrayList2;
        }
        Iterator<cfe> it = arrayList.iterator();
        long j = 0;
        int i = 0;
        double d = 0.0d;
        while (it.hasNext()) {
            cfe next = it.next();
            if (!jdl.d(j, next.au())) {
                b(j, d, i, arrayList2);
                j = jdl.t(next.au());
                i = 0;
                d = 0.0d;
            }
            double a2 = a(next);
            if (a2 > 0.0d) {
                d += a2;
                i++;
                j = jdl.t(next.au());
            }
        }
        b(j, d, i, arrayList2);
        return arrayList2;
    }

    private void b(long j, double d, int i, List<Pair<Long, Float>> list) {
        if (i > 0) {
            list.add(new Pair<>(Long.valueOf(j), Float.valueOf((float) (d / i))));
        }
    }

    private void e() {
        initResult(qqy.g(1, this.c, this.f10200a), qqy.g(2, this.c, this.f10200a));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return "protein";
    }
}
