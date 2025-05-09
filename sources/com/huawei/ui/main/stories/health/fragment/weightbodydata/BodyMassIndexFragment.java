package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.util.Pair;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.dks;
import defpackage.doj;
import defpackage.dph;
import defpackage.koq;
import defpackage.nmm;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class BodyMassIndexFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10193a;
    private double b;
    private int d;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 2;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.d = this.mWeightBean.e();
        this.mGender = this.mWeightBean.an();
        double j = this.mWeightBean.j();
        this.b = j;
        if (!dph.e(j, this.mIsOversea)) {
            this.b = doj.a(this.mWeightBean.ax(), this.mWeightBean.t());
        }
        this.f10193a = doj.b(this.b, this.mIsOversea, this.mGender, this.d);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public List<Pair<Long, Float>> buildTrendData(List<HiHealthData> list) {
        double d;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            if (isSetUnit().booleanValue()) {
                d = UnitUtil.a(hiHealthData.getDouble(BleConstants.BMI));
            } else {
                d = hiHealthData.getDouble(BleConstants.BMI);
            }
            float f = (float) d;
            if (f <= 0.0f) {
                double d2 = hiHealthData.getDouble("bodyWeight");
                double d3 = hiHealthData.getDouble("height");
                f = (float) dks.a(d2, d3 <= 0.0d ? MultiUsersManager.INSTANCE.getCurrentUser().d() : (int) d3);
            }
            arrayList.add(new Pair(Long.valueOf(hiHealthData.getStartTime()), Float.valueOf(f)));
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        double[] e = doj.e(this.mIsOversea, this.mGender, this.d);
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
        arrayList2.add(new WeightBodyDataFragment.b(d, getMinRangContent(getCalculateRangValue(f, 0.1f, false)), color));
        arrayList2.add(new WeightBodyDataFragment.b(d2, getComRangContent(f, f2), color2));
        arrayList2.add(new WeightBodyDataFragment.b(d3, getComRangContent(calculateRangValue, getCalculateRangValue(f3, 0.1f, false)), color3));
        arrayList2.add(new WeightBodyDataFragment.b(d4, getMaxRangContent(f3), color4));
        setLevelInfo(arrayList2);
        String e2 = UnitUtil.e(this.b, 1, 1);
        double a2 = UnitUtil.a(this.b, 1);
        this.b = a2;
        setLevel(a2, e2);
    }

    private String d(int i) {
        return qqy.a(0, i);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initResultForGlobal(qqy.a(1, this.f10193a), qqy.a(2, this.f10193a));
        initAbout(qrd.b(0), qrd.b(1));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return BleConstants.BMI;
    }
}
