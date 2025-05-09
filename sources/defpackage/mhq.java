package defpackage;

import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.report.constant.EnumAnnual;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mhq {
    public static int b(int i, int i2) {
        int i3 = i < 10 ? 100 - (i * 2) : 90 - i;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i == 0 && i2 == 0) {
            return 0;
        }
        return i3;
    }

    public static int b(SleepTotalData sleepTotalData, List<Integer> list, int i) {
        if (sleepTotalData == null || list == null) {
            return -1;
        }
        return d(sleepTotalData, list, i);
    }

    private static int d(SleepTotalData sleepTotalData, List<Integer> list, int i) {
        ArrayList arrayList = new ArrayList(13);
        double d = i * 1.0d;
        if (sleepTotalData.getScore() / d < 70.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.HORSE.getValue()));
        }
        if (sleepTotalData.getScore() / d > 85.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.LION.getValue()));
        }
        if (sleepTotalData.getFallTime() / d > 1500.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.OWL.getValue()));
        }
        double d2 = d(list);
        if (e(sleepTotalData, d, d2)) {
            arrayList.add(Integer.valueOf(EnumAnnual.CHICKEN.getValue()));
        }
        if (sleepTotalData.getWakeUpTime() / d < 360.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.LARK.getValue()));
        }
        double deepSleepTime = ((sleepTotalData.getDeepSleepTime() + sleepTotalData.getSlumberSleepTime()) + sleepTotalData.getShallowSleepTime()) / i;
        double d3 = deepSleepTime / 60.0d;
        if (d3 < 6.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.HORSE.getValue()));
        }
        if (d3 > 9.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.KOALA.getValue()));
        }
        if (sleepTotalData.getWakeupTimes() / d > 2.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.DOG.getValue()));
        }
        if ((sleepTotalData.getDeepSleepTime() / d) / deepSleepTime < 0.2d) {
            arrayList.add(Integer.valueOf(EnumAnnual.DOLPHIN.getValue()));
        }
        if ((sleepTotalData.getSlumberSleepTime() / d) / deepSleepTime < 0.1d) {
            arrayList.add(Integer.valueOf(EnumAnnual.RABBIT.getValue()));
        }
        if ((sleepTotalData.getSlumberSleepTime() / d) / deepSleepTime > 0.3d) {
            arrayList.add(Integer.valueOf(EnumAnnual.CAT.getValue()));
        }
        if (d2 < 60.0d) {
            arrayList.add(Integer.valueOf(EnumAnnual.ZEBRAFISH.getValue()));
        }
        if (arrayList.size() == 0) {
            arrayList.add(Integer.valueOf(EnumAnnual.LION.getValue()));
        }
        return e(arrayList);
    }

    private static boolean e(SleepTotalData sleepTotalData, double d, double d2) {
        double fallTime = sleepTotalData.getFallTime() / d;
        return ((fallTime > 1260.0d ? 1 : (fallTime == 1260.0d ? 0 : -1)) > 0) && ((fallTime > 1380.0d ? 1 : (fallTime == 1380.0d ? 0 : -1)) < 0) && ((d2 > 90.0d ? 1 : (d2 == 90.0d ? 0 : -1)) > 0);
    }

    private static double d(List<Integer> list) {
        int size = list.size();
        Iterator<Integer> it = list.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().intValue();
        }
        double d = size;
        double d2 = (i2 * 1.0d) / d;
        for (Integer num : list) {
            i = (int) (i + ((num.intValue() - d2) * (num.intValue() - d2)));
        }
        double sqrt = Math.sqrt((i * 1.0d) / d);
        double compare = Double.compare(sqrt, 30.0d);
        double d3 = 100.0d - ((sqrt * 2.0d) / 3.0d);
        double d4 = sqrt / 2.0d;
        if (compare > 0.0d) {
            d3 = 95.0d - d4;
        }
        if (d3 >= 0.0d) {
            return d3;
        }
        return 0.0d;
    }

    private static int e(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.get(list.size() - 1).intValue();
    }

    public static Map<Integer, Integer> e(SleepTotalData sleepTotalData, Map<Integer, Integer> map, Map<Integer, Integer> map2, Map<Integer, Double> map3) {
        if (sleepTotalData == null) {
            return map;
        }
        long sleepMonitorStartTime = sleepTotalData.getSleepMonitorStartTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sleepMonitorStartTime);
        int i = calendar.get(2);
        int i2 = i == 0 ? 4 : (i + 2) / 3;
        int score = sleepTotalData.getScore();
        if (map.containsKey(Integer.valueOf(i2))) {
            score += map.get(Integer.valueOf(i2)).intValue();
        }
        int intValue = map2.containsKey(Integer.valueOf(i2)) ? 1 + map2.get(Integer.valueOf(i2)).intValue() : 1;
        map.put(Integer.valueOf(i2), Integer.valueOf(score));
        map2.put(Integer.valueOf(i2), Integer.valueOf(intValue));
        if (intValue > 0) {
            map3.put(Integer.valueOf(i2), Double.valueOf(score / intValue));
        }
        return map;
    }

    public static int d(Map<Integer, Double> map) {
        int i = 0;
        if (map != null && map.size() != 0) {
            double d = 0.0d;
            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                double doubleValue = entry.getValue().doubleValue();
                if (mlg.e(d, doubleValue) < 0) {
                    i = entry.getKey().intValue();
                    d = doubleValue;
                }
            }
            LogUtil.a("PLGACHIEVE_SleepCalRule", "getMaxSeasonScore maxSeasonIndex ", Integer.valueOf(i), " maxSeasonScore ", Double.valueOf(d));
        }
        return i;
    }
}
