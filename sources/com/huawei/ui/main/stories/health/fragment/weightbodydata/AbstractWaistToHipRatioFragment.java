package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import com.huawei.health.R;
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
public class AbstractWaistToHipRatioFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    protected String f10191a;
    protected double b;
    protected int c;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        if (!qrp.d()) {
            b(true);
            return;
        }
        double[] d = doj.d(this.mGender);
        setIndicatorCardViewVisible();
        float f = (float) d[0];
        float calculateRangValue = getCalculateRangValue(f, 0.01f, false);
        String a2 = a(1);
        String a3 = a(2);
        int b = b(1);
        int b2 = b(2);
        double[] z = doj.z();
        float f2 = (float) z[0];
        float f3 = (float) z[1];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(f2, f, b, a2));
        arrayList.add(new nmm(f, f3, b2, a3));
        setAllLevelsData(arrayList, d, z);
        ArrayList arrayList2 = new ArrayList();
        String string = this.mContext.getString(R$string.IDS_bp_min_scope_value, UnitUtil.e(calculateRangValue, 1, 2));
        String string2 = this.mContext.getString(R$string.IDS_bp_max_explain, UnitUtil.e(f, 1, 2));
        arrayList2.add(new WeightBodyDataFragment.b(a2, string, b));
        arrayList2.add(new WeightBodyDataFragment.b(a3, string2, b2));
        setLevelInfo(arrayList2);
        b(false);
    }

    private void b(boolean z) {
        double a2 = UnitUtil.a(this.b, 2);
        this.b = a2;
        String e = UnitUtil.e(a2, 1, 2);
        if (z) {
            setDataInfo(getString(R$string.IDS_weight_spectral_waist_to_hip_ratio), UnitUtil.bCR_(this.mContext, "[\\.\\d]", e, R.style.health_weight_body_data_value, R.style.health_weight_body_data_unit));
        } else {
            setLevel(this.b, e);
        }
    }

    private int b(int i) {
        return getColor(qrf.j(i));
    }

    private String a(int i) {
        return qqy.o(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initResult(qqy.o(1, this.c), qqy.o(2, this.c));
        initAbout(this.f10191a, qrd.r(1));
    }
}
