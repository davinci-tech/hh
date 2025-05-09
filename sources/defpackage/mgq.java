package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class mgq extends BaseCalculator {

    /* renamed from: a, reason: collision with root package name */
    private PluginAchieveAdapter f14981a = getPluginAchieveAdapter();
    private Context e = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        e(i, i);
    }

    public void e(int i) {
        e(i - 1, i);
    }

    public void e(final int i, final int i2) {
        LogUtil.a("PLGACHIEVE_WeightCalculator", "getAnnualWeighData ENTER");
        this.f14981a.getWeightData(this.e, mht.b(i, true), mht.b(i2, false), new AchieveCallback() { // from class: mgq.4
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i3, Object obj) {
                if (obj == null) {
                    LogUtil.a("PLGACHIEVE_WeightCalculator", "getWeightData is null,return");
                    return;
                }
                if (!koq.e(obj, HiHealthData.class)) {
                    LogUtil.a("PLGACHIEVE_WeightCalculator", "getWeightData obj type cast,return");
                    return;
                }
                try {
                    mgq.this.b((List<HiHealthData>) obj, i, i2);
                } catch (ClassCastException unused) {
                    LogUtil.b("PLGACHIEVE_WeightCalculator", "getWeightData data ClassCastException");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, int i, int i2) {
        List<HiHealthData> d = d(list);
        if (koq.b(d)) {
            return;
        }
        if (i == i2) {
            a(d, i2);
        } else {
            b(a(d, i2, i2), a(d, i2 - 1, i2), i2);
        }
    }

    private List<HiHealthData> d(List<HiHealthData> list) {
        if (koq.b(list)) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            String metaData = hiHealthData.getMetaData();
            if (TextUtils.isEmpty(metaData) || Constants.NULL.equalsIgnoreCase(metaData) || "0".equals(metaData)) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private void a(List<HiHealthData> list, int i) {
        LogUtil.a("PLGACHIEVE_WeightCalculator", "computeWeightData");
        if (koq.b(list)) {
            return;
        }
        int size = list.size();
        if (size < 3) {
            LogUtil.a("PLGACHIEVE_WeightCalculator", "computeWeightData size=", Integer.valueOf(size));
            return;
        }
        HiHealthData hiHealthData = list.get(size - 1);
        HiHealthData hiHealthData2 = list.get(0);
        double d = hiHealthData.getDouble("bodyWeight");
        double d2 = hiHealthData.getDouble("bodyWeight");
        double d3 = hiHealthData2.getDouble("bodyWeight");
        double d4 = hiHealthData.getDouble("bodyWeight");
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            double d5 = it.next().getDouble("bodyWeight");
            if (mlg.e(d5, d2) > 0) {
                d2 = d5;
            }
            if (mlg.e(d5, d) < 0) {
                d = d5;
            }
        }
        insertData(i, EnumAnnualType.REPORT_WEIGHT.value(), 7001, String.valueOf(d3 - d4));
        insertData(i, EnumAnnualType.REPORT_WEIGHT.value(), 7002, String.valueOf(d2));
        insertData(i, EnumAnnualType.REPORT_WEIGHT.value(), 7003, String.valueOf(d));
    }

    private double a(List<HiHealthData> list, int i, int i2) {
        int i3;
        double d;
        double d2;
        double d3 = 0.0d;
        if (koq.b(list)) {
            return 0.0d;
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        int height = userInfo != null ? userInfo.getHeight() : 0;
        double d4 = 0.0d;
        double d5 = 0.0d;
        double d6 = 0.0d;
        long j = 0;
        int i4 = 0;
        double d7 = 0.0d;
        for (HiHealthData hiHealthData : list) {
            if (String.valueOf(i).equals(mht.d(hiHealthData.getStartTime()))) {
                double d8 = hiHealthData.getDouble("bodyWeight");
                d = d5;
                d2 = 0.0d;
                if (mlg.e(d8, 0.0d) > 0) {
                    if (j < hiHealthData.getStartTime()) {
                        j = hiHealthData.getStartTime();
                        double d9 = hiHealthData.getDouble("height");
                        d = hiHealthData.getDouble(BleConstants.BMI);
                        d4 = d9;
                        d7 = d8;
                    }
                    i4++;
                    d6 += d8;
                }
            } else {
                d = d5;
                d2 = 0.0d;
            }
            d3 = d2;
            d5 = d;
        }
        double d10 = d5;
        double d11 = d3;
        if (i4 == 0) {
            i3 = i2;
        } else {
            i3 = i2;
            d11 = d6 / i4;
        }
        if (i == i3) {
            insertData(i, EnumAnnualType.REPORT_HEALTH.value(), 10013, String.valueOf(i4));
            insertData(i, EnumAnnualType.REPORT_HEALTH.value(), 10012, UnitUtil.e(d11, 1, 2));
            insertData(i, EnumAnnualType.REPORT_HEALTH.value(), PrebakedEffectId.RT_LIGHTNING, UnitUtil.e(d(d7, d4, height, d10), 1, 1));
        }
        return d11;
    }

    private double d(double d, double d2, int i, double d3) {
        if (mlg.e(d3, 0.0d) != 0) {
            LogUtil.a("PLGACHIEVE_WeightCalculator", "getBmi bmi ", Double.valueOf(d3));
            return d3;
        }
        if (mlg.e(d2, 0.0d) != 0) {
            double a2 = doj.a(d, (int) d2);
            LogUtil.a("PLGACHIEVE_WeightCalculator", "getBmi weightBmi ", Double.valueOf(a2));
            return a2;
        }
        if (i == 0) {
            return d3;
        }
        double a3 = doj.a(d, i);
        LogUtil.a("PLGACHIEVE_WeightCalculator", "getBmi userBmi ", Double.valueOf(a3));
        return a3;
    }

    private void b(double d, double d2, int i) {
        insertData(i, EnumAnnualType.REPORT_HEALTH.value(), PrebakedEffectId.RT_CALENDAR_DATE, UnitUtil.e(d - d2, 1, 2));
    }
}
