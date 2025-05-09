package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateUtils;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.R$string;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class scg {
    public static final HashMap<Integer, Integer> e = new HashMap<Integer, Integer>() { // from class: scg.4
        private static final long serialVersionUID = 1914034327014654167L;

        {
            put(0, 0);
            put(1, 0);
            put(2, 0);
            put(3, 1);
            put(4, 1);
            put(5, 1);
            put(6, 2);
            put(7, 2);
            put(8, 2);
            put(9, 2);
            put(10, 3);
            put(11, 3);
            put(12, 3);
        }
    };

    public static int a(int i) {
        if (i < 70) {
            return 2;
        }
        return i > 89 ? 0 : 1;
    }

    public static String h(int i) {
        if (i < 0 || i > 12) {
            return "ERROR_SCORE";
        }
        Context context = BaseApplication.getContext();
        int intValue = e.get(Integer.valueOf(i)).intValue();
        if (intValue == 1) {
            return context.getString(R$string.IDS_hw_health_blood_oxygen_mild);
        }
        if (intValue == 2) {
            return context.getString(R$string.IDS_hw_health_blood_oxygen_moderate);
        }
        if (intValue == 3) {
            return context.getString(R$string.IDS_hw_health_blood_oxygen_severe);
        }
        return context.getString(R$string.IDS_hw_pressure_normal);
    }

    public static String d(Context context, long j) {
        return DateUtils.formatDateTime(context, j, 16);
    }

    public static String e(Context context, long j) {
        return DateUtils.formatDateTime(context, j, 129);
    }

    public static String a(Context context, long j) {
        return DateUtils.formatDateTime(context, j, 145);
    }

    public static int b(int i) {
        if (i < 0 || i > 12) {
            return -1;
        }
        return d(e.get(Integer.valueOf(i)).intValue());
    }

    public static int c(int i) {
        return e(a(i));
    }

    public static int d(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        if (i == 1) {
            return resources.getColor(R.color._2131296515_res_0x7f090103);
        }
        if (i == 2) {
            return resources.getColor(R.color._2131296514_res_0x7f090102);
        }
        if (i == 3) {
            return resources.getColor(R.color._2131296504_res_0x7f0900f8);
        }
        if (i == 0) {
            return resources.getColor(R.color._2131296516_res_0x7f090104);
        }
        return -1;
    }

    public static int e(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        if (i == 2) {
            return resources.getColor(R.color._2131296504_res_0x7f0900f8);
        }
        if (i == 1) {
            return resources.getColor(R.color._2131296514_res_0x7f090102);
        }
        return resources.getColor(R.color._2131296516_res_0x7f090104);
    }

    public static List<HiHealthData> d(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3, boolean z) {
        if (koq.b(list) || koq.b(list2)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            if (!z || hiHealthData.getIntValue() > 2) {
                long c = c(hiHealthData.getStartTime());
                HiHealthData hiHealthData2 = new HiHealthData();
                Iterator<HiHealthData> it = list2.iterator();
                int i = 120001;
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (next.getStartTime() - c == 0) {
                            e(hiHealthData2, next);
                            break;
                        }
                        int startTime = (int) (next.getStartTime() - hiHealthData.getStartTime());
                        if (startTime >= 0 && startTime < i) {
                            e(hiHealthData2, next);
                            i = startTime;
                        }
                    } else if (i <= 60000) {
                    }
                }
                hiHealthData2.putInt("lakeLouiseScoreKey", hiHealthData.getIntValue());
                hiHealthData2.putInt("bloodOxygenCardKey", 1001);
                arrayList.add(hiHealthData2);
            }
        }
        b(arrayList, list3);
        return arrayList;
    }

    private static void e(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        hiHealthData.setStartTime(hiHealthData2.getStartTime());
        hiHealthData.setEndTime(hiHealthData2.getEndTime());
        hiHealthData.setModifiedTime(hiHealthData2.getModifiedTime());
        hiHealthData.setClientId(hiHealthData2.getClientId());
        hiHealthData.putInt("bloodOxygenKey", hiHealthData2.getInt("bloodOxygenKey"));
        hiHealthData.putInt("point_value", hiHealthData2.getIntValue());
    }

    public static void b(List<HiHealthData> list, List<HiHealthData> list2) {
        if (koq.b(list)) {
            return;
        }
        if (koq.b(list2)) {
            Iterator<HiHealthData> it = list.iterator();
            while (it.hasNext()) {
                it.next().putInt("altitudeKey", Integer.MIN_VALUE);
            }
            return;
        }
        for (HiHealthData hiHealthData : list) {
            hiHealthData.putInt("altitudeKey", Integer.MIN_VALUE);
            long j = 60001;
            for (HiHealthData hiHealthData2 : list2) {
                long abs = Math.abs(hiHealthData.getStartTime() - hiHealthData2.getStartTime());
                if (abs < j) {
                    hiHealthData.putInt("altitudeKey", hiHealthData2.getInt("altitudeKey"));
                    j = abs;
                }
            }
        }
    }

    public static long c(long j) {
        Date date = new Date(j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }
}
