package defpackage;

import android.content.Context;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mfv extends BaseCalculator {
    private final PluginAchieveAdapter d = getPluginAchieveAdapter();

    /* renamed from: a, reason: collision with root package name */
    private final Context f14959a = BaseApplication.getContext();

    private float c(float f) {
        if (f == Float.MIN_VALUE || f == Float.MAX_VALUE) {
            return 0.0f;
        }
        return f;
    }

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(final int i) {
        if (this.d == null || this.f14959a == null) {
            LogUtil.h("PLGACHIEVE_ClimbHillCalculator", "compute: mAchieveAdapter or mContext is null");
            return;
        }
        LogUtil.a("PLGACHIEVE_ClimbHillCalculator", "compute: start");
        this.d.fetchSequenceDataBySportType(this.f14959a, mht.b(i, true), mht.b(i, false), 260, new AchieveCallback() { // from class: mfz
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public final void onResponse(int i2, Object obj) {
                mfv.this.d(i, i2, obj);
            }
        });
    }

    /* synthetic */ void d(int i, int i2, Object obj) {
        List<HiHealthData> list;
        LogUtil.a("PLGACHIEVE_ClimbHillCalculator", "compute: ret -> " + i2);
        if (koq.e(obj, HiHealthData.class)) {
            list = (List) obj;
        } else {
            LogUtil.h("PLGACHIEVE_ClimbHillCalculator", "compute: data is not list");
            list = null;
        }
        insertData(i, EnumAnnualType.REPORT_CLIMB_HILL.value(), d(list));
        LogUtil.a("PLGACHIEVE_ClimbHillCalculator", "compute: end");
    }

    public Map<Integer, String> d(List<HiHealthData> list) {
        int i;
        String valueOf;
        String valueOf2;
        HiTrackMetaData hiTrackMetaData;
        HashMap hashMap = new HashMap(2);
        HiTrackMetaData hiTrackMetaData2 = null;
        int i2 = 0;
        float f = 0.0f;
        long j = 0;
        if (!koq.b(list)) {
            HiTrackMetaData hiTrackMetaData3 = null;
            int i3 = 0;
            long j2 = 0;
            float f2 = 0.0f;
            for (HiHealthData hiHealthData : list) {
                try {
                    hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                } catch (JsonSyntaxException e) {
                    LogUtil.b("PLGACHIEVE_ClimbHillCalculator", "computeClimbHillData: exception -> " + e.getMessage());
                    hiTrackMetaData = null;
                }
                if (hiTrackMetaData != null && hiTrackMetaData.getSportType() == 260) {
                    if (mhu.b(hiTrackMetaData)) {
                        LogUtil.h("PLGACHIEVE_ClimbHillCalculator", "computeClimbHillData: Invalid data -> " + hiTrackMetaData);
                    } else {
                        i3++;
                        i2 += hiTrackMetaData.getTotalDistance();
                        float c = c(hiTrackMetaData.getMaxAlti());
                        f += c;
                        if (c >= f2) {
                            j2 = hiHealthData.getStartTime();
                            hiTrackMetaData3 = hiTrackMetaData;
                            f2 = c;
                        }
                    }
                }
            }
            i = i2;
            j = j2;
            hiTrackMetaData2 = hiTrackMetaData3;
            i2 = i3;
        } else {
            LogUtil.h("PLGACHIEVE_ClimbHillCalculator", "computeClimbHillData: climbHillDataList is null");
            i = 0;
        }
        hashMap.put(10051, String.valueOf(i2));
        hashMap.put(10052, String.valueOf(i));
        hashMap.put(10053, String.valueOf(f));
        hashMap.put(10055, String.valueOf(j));
        String str = "0";
        if (hiTrackMetaData2 == null) {
            valueOf = "0";
        } else {
            valueOf = String.valueOf(hiTrackMetaData2.getTotalDistance());
        }
        hashMap.put(10056, valueOf);
        if (hiTrackMetaData2 != null) {
            str = String.valueOf(mht.e(hiTrackMetaData2.getTotalTime()));
        }
        hashMap.put(10057, str);
        String str2 = "0.0";
        if (hiTrackMetaData2 == null) {
            valueOf2 = "0.0";
        } else {
            valueOf2 = String.valueOf(c(hiTrackMetaData2.getMaxAlti()));
        }
        hashMap.put(10054, valueOf2);
        if (hiTrackMetaData2 != null) {
            str2 = String.valueOf(c(hiTrackMetaData2.getCreepingWave()) / 10.0f);
        }
        hashMap.put(10058, str2);
        return hashMap;
    }
}
