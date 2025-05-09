package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes6.dex */
public class mdc {
    private static long e;

    private static int a(Context context, PluginAchieveAdapter pluginAchieveAdapter, TotalRecord totalRecord) {
        return (context == null || pluginAchieveAdapter == null || totalRecord == null) ? -1 : 0;
    }

    public static void e(final Context context, PluginAchieveAdapter pluginAchieveAdapter, final TotalRecord totalRecord) {
        if (a(context, pluginAchieveAdapter, totalRecord) == -1) {
            return;
        }
        pluginAchieveAdapter.getSumYearData(context, 0L, mle.d(false, System.currentTimeMillis()), 21, new AchieveCallback() { // from class: mdc.4
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i, Object obj) {
                mdc.a(context, totalRecord, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void a(Context context, TotalRecord totalRecord, T t) {
        if (!koq.e(t, HiHealthData.class)) {
            LogUtil.h("PLGACHIEVE_TotalRecordRule", "saveAchieveSumData is null");
            return;
        }
        List<HiHealthData> list = (List) t;
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_TotalRecordRule", "saveAchieveSumData dataInfos is null,return");
            return;
        }
        int i = 0;
        int i2 = 0;
        long j = 0;
        for (HiHealthData hiHealthData : list) {
            i2 += hiHealthData.getInt("totalStep");
            j += hiHealthData.getLong("totalCalorie");
            i += hiHealthData.getInt(BleConstants.TOTAL_DISTANCE);
        }
        totalRecord.setDistance(mlg.d(i));
        totalRecord.setSteps(i2);
        totalRecord.saveCalorie(j);
        long j2 = e;
        if (j2 != 0) {
            totalRecord.setStartDate(j2);
        }
        LogUtil.a("PLGACHIEVE_TotalRecordRule", "achieveRe value ==", Integer.valueOf(i2), ",sweep ==", Long.valueOf(j), ",lang ==", Integer.valueOf(i), " mStartTime", Long.valueOf(e));
        meh.c(context).e(totalRecord);
    }

    public static void d(final Context context, PluginAchieveAdapter pluginAchieveAdapter, final TotalRecord totalRecord) {
        if (Utils.o() && a(context, pluginAchieveAdapter, totalRecord) != -1) {
            pluginAchieveAdapter.getSumYearData(context, 0L, mle.d(false, System.currentTimeMillis()), 22, new AchieveCallback() { // from class: mdc.3
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    mdc.c(context, totalRecord, obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void c(Context context, TotalRecord totalRecord, T t) {
        if (!(t instanceof SparseArray)) {
            LogUtil.h("PLGACHIEVE_TotalRecordRule", "saveAchieveSumDays: !(data instanceof SparseArray)");
            return;
        }
        try {
            SparseArray sparseArray = (SparseArray) t;
            if (sparseArray.size() <= 0) {
                LogUtil.h("PLGACHIEVE_TotalRecordRule", "totalDetailTask: complete with empty data [countDown(-3)]");
                return;
            }
            LogUtil.a("PLGACHIEVE_TotalRecordRule", "totalDetailTask: success query totalDetailData from sport health");
            Object obj = sparseArray.get(40002);
            List list = obj instanceof List ? (List) obj : null;
            Object obj2 = sparseArray.get(40003);
            List list2 = obj2 instanceof List ? (List) obj2 : null;
            Object obj3 = sparseArray.get(40004);
            List list3 = obj3 instanceof List ? (List) obj3 : null;
            HashSet hashSet = new HashSet(16);
            hashSet.addAll(e(list));
            hashSet.addAll(e(list2));
            hashSet.addAll(e(list3));
            b(context, totalRecord, (Set<Long>) hashSet);
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_TotalRecordRule", "saveAchieveSumDays data ClassCastException");
        }
    }

    private static void b(Context context, TotalRecord totalRecord, Set<Long> set) {
        if (koq.b(set)) {
            LogUtil.h("PLGACHIEVE_TotalRecordRule", "calculateTotalDays totalTimes isEmpty!");
            return;
        }
        ArrayList arrayList = new ArrayList(set);
        if (koq.b(arrayList)) {
            LogUtil.h("PLGACHIEVE_TotalRecordRule", "calculateTotalDays list isEmpty!");
            return;
        }
        Collections.sort(arrayList);
        int size = arrayList.size();
        long longValue = ((Long) arrayList.get(0)).longValue();
        totalRecord.setDays(size);
        totalRecord.setStartDate(longValue);
        e = longValue;
        LogUtil.a("PLGACHIEVE_TotalRecordRule", "achieveReport TotalDays() == ", Integer.valueOf(arrayList.size()), " FirstTimestamp() == ", Long.valueOf(longValue));
        meh.c(context).e(totalRecord);
    }

    private static Set<Long> e(List<HiHealthData> list) {
        HashSet hashSet = new HashSet(16);
        if (!koq.b(list)) {
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getFloat("point_value") > 0.0f) {
                    hashSet.add(Long.valueOf(hiHealthData.getStartTime()));
                }
            }
        }
        return hashSet;
    }
}
