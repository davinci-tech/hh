package defpackage;

import android.content.Context;
import android.util.Pair;
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
import health.compact.a.HiDateUtil;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToLongFunction;

/* loaded from: classes6.dex */
public class mft extends BaseCalculator {

    /* renamed from: a, reason: collision with root package name */
    private final PluginAchieveAdapter f14958a = getPluginAchieveAdapter();
    private final Context b = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(final int i) {
        if (this.f14958a == null || this.b == null) {
            LogUtil.h("PLGACHIEVE_BadmintonCalculator", "compute: mAchieveAdapter or mContext is null");
            return;
        }
        LogUtil.a("PLGACHIEVE_BadmintonCalculator", "compute: start");
        this.f14958a.fetchSequenceDataBySportType(this.b, mht.b(i, true), mht.b(i, false), 129, new AchieveCallback() { // from class: mfq
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public final void onResponse(int i2, Object obj) {
                mft.this.c(i, i2, obj);
            }
        });
    }

    /* synthetic */ void c(int i, int i2, Object obj) {
        List<HiHealthData> list;
        LogUtil.a("PLGACHIEVE_BadmintonCalculator", "compute: ret -> " + i2);
        if (koq.e(obj, HiHealthData.class)) {
            list = (List) obj;
        } else {
            LogUtil.h("PLGACHIEVE_BadmintonCalculator", "compute: data is not list");
            list = null;
        }
        insertData(i, EnumAnnualType.REPORT_BADMINTON.value(), d(list));
        LogUtil.a("PLGACHIEVE_BadmintonCalculator", "compute: end");
    }

    private Map<Integer, String> d(List<HiHealthData> list) {
        long j;
        long j2;
        HiTrackMetaData hiTrackMetaData;
        HashMap hashMap = new HashMap(2);
        int i = 0;
        long j3 = 0;
        if (!koq.b(list)) {
            HashMap<Long, Long> hashMap2 = new HashMap<>(2);
            for (HiHealthData hiHealthData : list) {
                try {
                    hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                } catch (JsonSyntaxException e) {
                    LogUtil.b("PLGACHIEVE_BadmintonCalculator", "computeBadmintonData: exception -> " + e.getMessage());
                    hiTrackMetaData = null;
                }
                if (hiTrackMetaData == null || mhu.b(hiTrackMetaData)) {
                    LogUtil.h("PLGACHIEVE_BadmintonCalculator", "computeBadmintonData: Invalid data -> " + hiTrackMetaData);
                } else {
                    i++;
                    j3 += hiTrackMetaData.getTotalTime();
                    b(hiHealthData.getStartTime(), hiTrackMetaData.getTotalTime(), hashMap2);
                }
            }
            Pair<Long, Long> cgO_ = cgO_(hashMap2);
            j = ((Long) cgO_.first).longValue();
            j2 = ((Long) cgO_.second).longValue();
        } else {
            LogUtil.h("PLGACHIEVE_BadmintonCalculator", "computeBadmintonData: badmintonDataList is null");
            j = 0;
            j2 = 0;
        }
        hashMap.put(10059, String.valueOf(i));
        hashMap.put(10060, String.valueOf(mht.e(j3)));
        hashMap.put(10061, String.valueOf(mht.e(j)));
        hashMap.put(10062, String.valueOf(j2));
        return hashMap;
    }

    private void b(long j, long j2, HashMap<Long, Long> hashMap) {
        long t = HiDateUtil.t(j);
        if (hashMap.containsKey(Long.valueOf(t))) {
            Long l = hashMap.get(Long.valueOf(t));
            hashMap.put(Long.valueOf(t), Long.valueOf((l == null ? 0L : l.longValue()) + j2));
        } else {
            hashMap.put(Long.valueOf(t), Long.valueOf(j2));
        }
    }

    private Pair<Long, Long> cgO_(HashMap<Long, Long> hashMap) {
        Optional<Map.Entry<Long, Long>> max = hashMap.entrySet().stream().max(Comparator.comparingLong(new ToLongFunction() { // from class: mfr
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((Long) ((Map.Entry) obj).getValue()).longValue();
            }
        }));
        if (max.isPresent()) {
            Map.Entry<Long, Long> entry = max.get();
            return new Pair<>(entry.getValue(), entry.getKey());
        }
        LogUtil.h("PLGACHIEVE_BadmintonCalculator", "computeBadmintonData: !isPresent");
        return new Pair<>(0L, 0L);
    }
}
