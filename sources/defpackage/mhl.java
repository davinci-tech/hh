package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.report.bean.AnnualReportFitness;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import com.huawei.pluginfitnessadvice.Classify;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mhl {
    private static int b(int i) {
        if (i < 400) {
            return 1;
        }
        return (i < 400 || i > 1200) ? 3 : 2;
    }

    public static String a(List<AnnualReportFitnessRaw> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_FitnessCalRule", "computeFavoriteCoursesData records isEmpty.");
            return "";
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (AnnualReportFitnessRaw annualReportFitnessRaw : list) {
            List<Classify> primaryClassify = annualReportFitnessRaw.getPrimaryClassify();
            if (!koq.b(primaryClassify)) {
                switch (c(primaryClassify)) {
                    case 10018:
                    case 10583:
                    case 13423:
                    case 13546:
                    case 14262:
                        i++;
                        break;
                    case 10706:
                    case 10738:
                    case 13484:
                    case 14605:
                        i4++;
                        break;
                    case 10719:
                    case 13425:
                        i2++;
                        break;
                    case 10726:
                        i3++;
                        break;
                    case 11087:
                        i5++;
                        break;
                }
            } else {
                LogUtil.a("PLGACHIEVE_FitnessCalRule", "computeFavoriteCoursesData record.acquireWorkoutId() outdate.", annualReportFitnessRaw.acquireWorkoutName());
            }
        }
        String b = b(i, i2, i3, i4, i5);
        LogUtil.a("PLGACHIEVE_FitnessCalRule", "computeFavoriteCoursesData acquireFavoriteCourse favoriteCourse ", b);
        return b;
    }

    private static int c(List<Classify> list) {
        int classifyId = list.get(0).getClassifyId();
        String classifyName = list.get(0).getClassifyName();
        if (classifyId == 14617 && list.size() > 1) {
            classifyId = list.get(1).getClassifyId();
            classifyName = list.get(1).getClassifyName();
        }
        LogUtil.a("PLGACHIEVE_FitnessCalRule", "computeFavoriteCoursesData Id ", Integer.valueOf(classifyId), " Name ", classifyName);
        return classifyId;
    }

    private static List<Integer> d(List<Classify> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_FitnessCalRule", "getPrimaryClassifyIdList primaryClassifyList is null.");
            return arrayList;
        }
        Iterator<Classify> it = list.iterator();
        while (it.hasNext()) {
            int classifyId = it.next().getClassifyId();
            if (classifyId != 14617 && classifyId != 13516 && !arrayList.contains(Integer.valueOf(classifyId))) {
                arrayList.add(Integer.valueOf(classifyId));
            }
        }
        return arrayList;
    }

    private static List<Integer> b(List<Classify> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_FitnessCalRule", "getSecondClassifyId secondClassify is null.");
            return arrayList;
        }
        Iterator<Classify> it = list.iterator();
        while (it.hasNext()) {
            int classifyId = it.next().getClassifyId();
            if (!arrayList.contains(Integer.valueOf(classifyId))) {
                arrayList.add(Integer.valueOf(classifyId));
            }
        }
        return arrayList;
    }

    private static String b(int i, int i2, int i3, int i4, int i5) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("1", Integer.valueOf(i));
        hashMap.put("2", Integer.valueOf(i2));
        hashMap.put("3", Integer.valueOf(i3));
        hashMap.put("4", Integer.valueOf(i4));
        hashMap.put("5", Integer.valueOf(i5));
        ArrayList<Map.Entry> arrayList = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: mhl.5
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().intValue() - entry.getValue().intValue();
            }
        });
        int i6 = 0;
        for (Map.Entry entry : arrayList) {
            i6 += ((Integer) entry.getValue()).intValue();
            LogUtil.a("PLGACHIEVE_FitnessCalRule", "acquireFavoriteCourse mapSet.getKey() ", entry.getKey(), " mapSet.getValue() ", entry.getValue());
        }
        return i6 <= 5 ? "0" : (String) ((Map.Entry) arrayList.get(0)).getKey();
    }

    public static AnnualReportFitness b(List<AnnualReportFitnessRaw> list, int i) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_FitnessCalRule", "computeFitnessData fitnessRecords is empty");
            return null;
        }
        LogUtil.a("PLGACHIEVE_FitnessCalRule", "computeFitnessData() year with =", Integer.valueOf(i));
        HashMap hashMap = new HashMap(16);
        int i2 = 0;
        for (AnnualReportFitnessRaw annualReportFitnessRaw : list) {
            int acquireDuring = annualReportFitnessRaw.acquireDuring() / 1000;
            i2 += acquireDuring;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(annualReportFitnessRaw.acquireExerciseTime());
            String str = i + Constants.LINK + (calendar.get(2) + 1) + Constants.LINK + calendar.get(5);
            if (hashMap.containsKey(str)) {
                hashMap.put(str, Integer.valueOf(((Integer) hashMap.get(str)).intValue() + acquireDuring));
            } else {
                hashMap.put(str, Integer.valueOf(acquireDuring));
            }
        }
        int round = (int) Math.round(i2 / 60.0d);
        if (round < 3) {
            LogUtil.a("PLGACHIEVE_FitnessCalRule", "getAnnualFitnessReport less than 3 min with duration ");
            return null;
        }
        AnnualReportFitness e = e(hashMap);
        e.saveNumberOfTimes(list.size());
        e.saveDescription(b(round));
        e.saveTotalDuration(round);
        return e;
    }

    private static AnnualReportFitness e(Map<String, Integer> map) {
        String str = "";
        int i = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() > i) {
                i = entry.getValue().intValue();
                str = entry.getKey();
            }
        }
        Calendar calendar = Calendar.getInstance();
        String[] split = str.split(Constants.LINK);
        if (split.length > 0) {
            calendar.set(1, mht.b(split[0]));
        }
        if (split.length > 1) {
            calendar.set(2, mht.b(split[1]) - 1);
        }
        if (split.length > 2) {
            calendar.set(5, mht.b(split[2]));
        }
        AnnualReportFitness annualReportFitness = new AnnualReportFitness();
        annualReportFitness.saveMaxDurationDay(calendar.getTimeInMillis());
        annualReportFitness.saveMaxDuration((int) Math.round(i / 60.0d));
        return annualReportFitness;
    }

    public static String e(List<AnnualReportFitnessRaw> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_FitnessCalRule", "computeFavoriteCoursesData records isEmpty.");
            return "";
        }
        HashMap hashMap = new HashMap(16);
        for (AnnualReportFitnessRaw annualReportFitnessRaw : list) {
            List<Classify> primaryClassify = annualReportFitnessRaw.getPrimaryClassify();
            List<Classify> secondClassify = annualReportFitnessRaw.getSecondClassify();
            if (koq.b(primaryClassify)) {
                LogUtil.a("PLGACHIEVE_FitnessCalRule", "computeFavoriteCoursesDataNew record.acquireWorkoutId() outdate.", annualReportFitnessRaw.acquireWorkoutName());
            } else {
                LogUtil.a("PLGACHIEVE_FitnessCalRule", "priList ", primaryClassify.toString(), "secondList ", secondClassify.toString());
                c(hashMap, primaryClassify, secondClassify);
            }
        }
        String a2 = a((HashMap<String, Integer>) hashMap);
        LogUtil.a("PLGACHIEVE_FitnessCalRule", "computeFavoriteCoursesDataNew favoriteCourse ", a2, " favoriteCourseMap ", hashMap.toString());
        return a2;
    }

    private static void c(HashMap<String, Integer> hashMap, List<Classify> list, List<Classify> list2) {
        for (Integer num : d(list)) {
            if (num.intValue() == 11010) {
                Iterator<Integer> it = b(list2).iterator();
                while (it.hasNext()) {
                    d(hashMap, a(it.next().intValue()));
                }
            } else {
                d(hashMap, a(num.intValue()));
            }
        }
    }

    private static String a(HashMap<String, Integer> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            return "0";
        }
        ArrayList<Map.Entry> arrayList = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: mhl.3
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().intValue() - entry.getValue().intValue();
            }
        });
        int i = 0;
        for (Map.Entry entry : arrayList) {
            i += ((Integer) entry.getValue()).intValue();
            LogUtil.a("PLGACHIEVE_FitnessCalRule", "acquireFavoriteCourse mapSet.getKey() ", entry.getKey(), " mapSet.getValue() ", entry.getValue());
        }
        return i <= 5 ? "0" : (String) ((Map.Entry) arrayList.get(0)).getKey();
    }

    private static void d(HashMap<String, Integer> hashMap, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (hashMap.containsKey(str)) {
            hashMap.put(str, Integer.valueOf(hashMap.get(str).intValue() + 1));
        } else {
            hashMap.put(str, 1);
        }
    }

    private static String a(int i) {
        switch (i) {
            case 10018:
            case 14263:
                return "5";
            case 10583:
            case 13423:
            case 14262:
            case 16000:
            case 16001:
                return "1";
            case 10706:
            case 10738:
            case 11002:
            case 14605:
            case 15101:
                return "9";
            case 10719:
            case 11004:
                return "2";
            case 10726:
            case 11001:
                return "3";
            case 11087:
                return "4";
            case 13425:
                return "6";
            case 13484:
                return "8";
            case 13546:
                return "7";
            default:
                return "";
        }
    }
}
