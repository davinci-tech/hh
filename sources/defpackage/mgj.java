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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;

/* loaded from: classes6.dex */
public class mgj extends BaseCalculator {
    private final PluginAchieveAdapter d = getPluginAchieveAdapter();
    private final Context e = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(final int i) {
        if (this.d == null || this.e == null) {
            LogUtil.h("PLGACHIEVE_JumpRopeCalculator", "compute: mAchieveAdapter or mContext is null");
            return;
        }
        LogUtil.a("PLGACHIEVE_JumpRopeCalculator", "compute: start");
        this.d.fetchSequenceDataBySportType(this.e, mht.b(i, true), mht.b(i, false), 283, new AchieveCallback() { // from class: mgh
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public final void onResponse(int i2, Object obj) {
                mgj.this.c(i, i2, obj);
            }
        });
    }

    /* synthetic */ void c(int i, int i2, Object obj) {
        List<HiHealthData> list;
        LogUtil.a("PLGACHIEVE_JumpRopeCalculator", "compute: ret -> " + i2);
        if (koq.e(obj, HiHealthData.class)) {
            list = (List) obj;
        } else {
            LogUtil.h("PLGACHIEVE_JumpRopeCalculator", "compute: data is not list");
            list = null;
        }
        insertData(i, EnumAnnualType.REPORT_JUMP_ROPE.value(), c(list));
        LogUtil.a("PLGACHIEVE_JumpRopeCalculator", "compute: end");
    }

    private Map<Integer, String> c(List<HiHealthData> list) {
        int i;
        long j;
        HiTrackMetaData hiTrackMetaData;
        HashMap hashMap = new HashMap(2);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        long j2 = 0;
        if (!koq.b(list)) {
            HashMap<Long, Integer> hashMap2 = new HashMap<>(2);
            for (HiHealthData hiHealthData : list) {
                try {
                    hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                } catch (JsonSyntaxException e) {
                    LogUtil.b("PLGACHIEVE_JumpRopeCalculator", "computeJumpRopeData: exception -> " + e.getMessage());
                    hiTrackMetaData = null;
                }
                if (hiTrackMetaData == null || mhu.b(hiTrackMetaData)) {
                    LogUtil.h("PLGACHIEVE_JumpRopeCalculator", "computeJumpRopeData: Invalid data -> " + hiTrackMetaData);
                } else {
                    i2++;
                    int e2 = e(hiTrackMetaData);
                    j2 += e2;
                    long t = HiDateUtil.t(hiHealthData.getStartTime());
                    if (!arrayList.contains(Long.valueOf(t))) {
                        arrayList.add(Long.valueOf(t));
                    }
                    c(t, e2, hashMap2);
                }
            }
            Pair<Integer, Long> cgP_ = cgP_(hashMap2);
            i = ((Integer) cgP_.first).intValue();
            j = ((Long) cgP_.second).longValue();
        } else {
            LogUtil.h("PLGACHIEVE_JumpRopeCalculator", "computeJumpRopeData: jumpRopeDataList is null");
            i = 0;
            j = 0;
        }
        hashMap.put(10063, String.valueOf(i2));
        hashMap.put(10064, String.valueOf(j2));
        hashMap.put(10065, String.valueOf(i));
        hashMap.put(10066, String.valueOf(j));
        hashMap.put(10067, String.valueOf(mgx.c(arrayList)));
        return hashMap;
    }

    private int e(HiTrackMetaData hiTrackMetaData) {
        if (hiTrackMetaData.getTotalSteps() > 0) {
            return hiTrackMetaData.getTotalSteps();
        }
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        if (extendTrackMap == null || !extendTrackMap.containsKey("skipNum")) {
            return 0;
        }
        return nsn.e(extendTrackMap.get("skipNum"));
    }

    private void c(long j, int i, HashMap<Long, Integer> hashMap) {
        if (hashMap.containsKey(Long.valueOf(j))) {
            Integer num = hashMap.get(Long.valueOf(j));
            hashMap.put(Long.valueOf(j), Integer.valueOf((num == null ? 0 : num.intValue()) + i));
        } else {
            hashMap.put(Long.valueOf(j), Integer.valueOf(i));
        }
    }

    private Pair<Integer, Long> cgP_(HashMap<Long, Integer> hashMap) {
        Optional<Map.Entry<Long, Integer>> max = hashMap.entrySet().stream().max(Comparator.comparingInt(new ToIntFunction() { // from class: mgi
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) ((Map.Entry) obj).getValue()).intValue();
            }
        }));
        if (max.isPresent()) {
            Map.Entry<Long, Integer> entry = max.get();
            return new Pair<>(entry.getValue(), entry.getKey());
        }
        LogUtil.h("PLGACHIEVE_JumpRopeCalculator", "computeJumpRopeData: !isPresent");
        return new Pair<>(0, 0L);
    }
}
