package defpackage;

import com.github.mikephil.charting.data.BarEntry;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.RecentMonthRecordFromDB;
import com.huawei.pluginachievement.manager.model.RecentWeekRecordFromDB;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class mkz {
    public static List<BarEntry> b(List<BarEntry> list, RecentWeekRecordFromDB recentWeekRecordFromDB, boolean z) {
        if (list == null || recentWeekRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportFragmentSupport", "getBarStepList: context/recentWeekRecordFromDB is null");
            return Collections.emptyList();
        }
        long acquireMonday = recentWeekRecordFromDB.acquireMonday();
        for (int i = 0; i < 7; i++) {
            list.add(e(recentWeekRecordFromDB.acquireSevenDaySteps(), (i * 86400000) + acquireMonday, i));
        }
        if (z) {
            e(list);
        }
        return list;
    }

    public static List<BarEntry> a(List<BarEntry> list, RecentMonthRecordFromDB recentMonthRecordFromDB, boolean z) {
        if (list == null || recentMonthRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportFragmentSupport", "getMonthBarStepList: stepList/recentMonthRecordFromDB is null");
            return Collections.emptyList();
        }
        long acquireFirstday = recentMonthRecordFromDB.acquireFirstday();
        int e = mkx.e(acquireFirstday);
        for (int i = 0; i < e; i++) {
            list.add(e(recentMonthRecordFromDB.acquireOneMonthSteps(), (i * 86400000) + acquireFirstday, i));
        }
        if (z) {
            e(list);
        }
        return list;
    }

    private static BarEntry e(Map<Long, Integer> map, long j, int i) {
        if (map.containsKey(Long.valueOf(j))) {
            return new BarEntry(i, map.get(Long.valueOf(j)).intValue());
        }
        if (d(map, j).longValue() == 0) {
            return new BarEntry(i, 0.0f);
        }
        return new BarEntry(i, map.get(r5).intValue());
    }

    private static Long d(Map<Long, Integer> map, long j) {
        Iterator<Map.Entry<Long, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Long key = it.next().getKey();
            if (HiDateUtil.c(key.longValue()) == HiDateUtil.c(j)) {
                return key;
            }
        }
        return 0L;
    }

    private static void e(List<BarEntry> list) {
        if (list == null) {
            return;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new BarEntry(i, list.get((list.size() - i) - 1).getY()));
        }
        list.clear();
        list.addAll(arrayList);
    }

    public static List<BarEntry> c(List<BarEntry> list, RecentMonthRecordFromDB recentMonthRecordFromDB, boolean z) {
        if (list == null || recentMonthRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportFragmentSupport", "getBarCaloriesList: context/recentWeekRecordFromDB is null");
            return Collections.emptyList();
        }
        Map<Long, Float> acquireWeekCalorie = recentMonthRecordFromDB.acquireWeekCalorie();
        if (acquireWeekCalorie == null || acquireWeekCalorie.size() <= 0) {
            LogUtil.h("PLGACHIEVE_ReportFragmentSupport", "setWeeklyCaloriesBarChart weekCaloriesMap is null.");
            return Collections.emptyList();
        }
        Map<Long, Number> b = mkx.b();
        b.putAll(acquireWeekCalorie);
        Iterator<Map.Entry<Long, Number>> it = b.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            list.add(new BarEntry(i, it.next().getValue().floatValue() / 1000.0f));
            i++;
        }
        if (z) {
            e(list);
        }
        return list;
    }

    public static List<BarEntry> d(List<BarEntry> list, RecentWeekRecordFromDB recentWeekRecordFromDB, boolean z) {
        if (list == null || recentWeekRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportFragmentSupport", "getBarExerciseTimeList: context/recentWeekRecordFromDB is null");
            return Collections.emptyList();
        }
        Map<Long, Long> acquireSevenDayTime = recentWeekRecordFromDB.acquireSevenDayTime();
        if (acquireSevenDayTime == null || acquireSevenDayTime.size() <= 0) {
            LogUtil.h("PLGACHIEVE_ReportFragmentSupport", "getBarExerciseTimeList weekExerciseTimeMap is null.");
            return Collections.emptyList();
        }
        Map<Long, Number> b = mkx.b();
        b.putAll(acquireSevenDayTime);
        Iterator<Map.Entry<Long, Number>> it = b.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            list.add(new BarEntry(i, (float) Math.ceil(it.next().getValue().longValue() / 60.0f)));
            i++;
        }
        if (z) {
            e(list);
        }
        return list;
    }
}
