package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dyc {
    private static final List<String> c = new LinkedList<String>() { // from class: dyc.4
        {
            add("RhythmType_0");
            add("RhythmType_1");
            add("RhythmType_2");
            add("RhythmType_3");
        }
    };
    private static final List<String> d = new LinkedList<String>() { // from class: dyc.3
        {
            add("MonthlySleepProblem_0");
            add("MonthlySleepProblem_1");
            add("MonthlySleepProblem_2");
            add("MonthlySleepProblem_3");
            add("MonthlySleepProblem_4");
            add("MonthlySleepProblem_5");
        }
    };
    private static final List<String> e = new LinkedList<String>() { // from class: dyc.5
        {
            add("DailySleepProblem_0");
            add("DailySleepProblem_1");
            add("DailySleepProblem_2");
            add("DailySleepProblem_3");
            add("DailySleepProblem_4");
            add("DailySleepProblem_5");
            add("DailySleepProblem_6");
            add("DailySleepProblem_7");
            add("DailySleepProblem_8");
            add("DailySleepProblem_9");
        }
    };
    private static final List<String> b = new LinkedList<String>() { // from class: dyc.2
        {
            add("DailyTargetProblem_0");
            add("DailyTargetProblem_1");
            add("DailyTargetProblem_2");
            add("DailyTargetProblem_3");
            add("DailyTargetProblem_4");
            add("DailyTargetProblem_5");
            add("DailyTargetProblem_6");
            add("DailyTargetProblem_7");
            add("DailyTargetProblem_8");
            add("DailyTargetProblem_9");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, List<String>> f11908a = new HashMap<String, List<String>>() { // from class: dyc.1
        {
            put("health_sleep_rhythm_type", dyc.c);
            put("health_monthly_sleep_problem", dyc.d);
            put("health_daily_sleep_problem", dyc.e);
            put("health_daily_sleep_target_problem", dyc.b);
        }
    };

    public static String d(String str, int i) {
        return b(str, i, f11908a.get(str));
    }

    public static String d(String str, List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        int size = list.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(d(str, list.get(i).intValue()));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static String b(String str, int i, List<String> list) {
        if (str == null || !f11908a.containsKey(str)) {
            LogUtil.a("SleepLabelMatcher", str, " is not existing in sleepLabelsMap");
            return null;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("SleepLabelMatcher", str, " value is invalid");
            return null;
        }
        return list.get(i);
    }
}
