package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.plan.model.model.WorkoutRecommendRule;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class eum {
    public static void b(List<FitWorkout> list) {
        c(list, 0);
    }

    public static void c(List<FitWorkout> list, int i) {
        long i2 = jec.i();
        if (i == 0) {
            ash.a("RECOMMEND_WORKOUT_KEY_COURSE", new Gson().toJson(list));
            ash.a("RECOMMEND_WORKOUT_CREATE_TIME_COURSE", Long.toString(i2));
            return;
        }
        ash.a("RECOMMEND_WORKOUT_KEY_COURSE" + i, new Gson().toJson(list));
        ash.a("RECOMMEND_WORKOUT_CREATE_TIME_COURSE" + i, Long.toString(i2));
    }

    public static List<FitWorkout> b() {
        return d(0);
    }

    public static List<FitWorkout> d(int i) {
        String b;
        String str;
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            str = ash.b("RECOMMEND_WORKOUT_CREATE_TIME_COURSE");
            b = ash.b("RECOMMEND_WORKOUT_KEY_COURSE");
        } else {
            String b2 = ash.b("RECOMMEND_WORKOUT_CREATE_TIME_COURSE" + i);
            b = ash.b("RECOMMEND_WORKOUT_KEY_COURSE" + i);
            str = b2;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(b)) {
            return arrayList;
        }
        long i2 = jec.i() - CommonUtil.g(str);
        if (i2 <= 0 || i2 >= 86400000) {
            return arrayList;
        }
        try {
            return (List) new Gson().fromJson(b, new TypeToken<List<FitWorkout>>() { // from class: eum.3
            }.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.h("RecommendWorkoutInterators", "parse RecommendData error ", e.getMessage());
            return arrayList;
        }
    }

    public static String c() {
        String b = ash.b("RECOMMEND_WORKOUT_RULE_KEY_COURSE");
        long i = jec.i();
        String valueOf = String.valueOf(i);
        if (TextUtils.isEmpty(b)) {
            return d(valueOf, "");
        }
        try {
            WorkoutRecommendRule workoutRecommendRule = (WorkoutRecommendRule) new Gson().fromJson(b, WorkoutRecommendRule.class);
            String ruleDate = workoutRecommendRule.getRuleDate();
            String ruleName = workoutRecommendRule.getRuleName();
            if (TextUtils.isEmpty(ruleDate) || TextUtils.isEmpty(ruleName)) {
                return d(valueOf, ruleName);
            }
            long g = i - CommonUtil.g(ruleDate);
            return (g < 0 || g > 604800000) ? d(valueOf, ruleName) : ruleName;
        } catch (JsonSyntaxException e) {
            LogUtil.h("RecommendWorkoutInterators", "parse RecommendRule error ", e.getMessage());
            return d(valueOf, "");
        }
    }

    public static String d(String str, String str2) {
        String a2 = a(str2);
        ash.a("RECOMMEND_WORKOUT_RULE_KEY_COURSE", new Gson().toJson(new WorkoutRecommendRule(a2, str)));
        return a2;
    }

    private static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer);
        List<WorkoutRecord> e = e();
        if (e != null && e.size() > 0) {
            a(stringBuffer, e);
            return stringBuffer.toString();
        }
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(",");
            if (split.length == 4) {
                stringBuffer.append(split[2] + ",");
                stringBuffer.append(split[3]);
                return stringBuffer.toString();
            }
        }
        stringBuffer.append("-1,-1");
        return stringBuffer.toString();
    }

    private static void a(StringBuffer stringBuffer, List<WorkoutRecord> list) {
        Iterator<WorkoutRecord> it = list.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            FitWorkout d2 = d(it.next());
            if (d2 != null) {
                int acquireDifficulty = d2.acquireDifficulty();
                if (acquireDifficulty == 6) {
                    i++;
                }
                if (acquireDifficulty == 0) {
                    i2++;
                }
                if (acquireDifficulty == 1) {
                    i3++;
                }
                if (acquireDifficulty == 2) {
                    i4++;
                }
                String fitnessGoal = d2.getFitnessGoal();
                if (!TextUtils.isEmpty(fitnessGoal)) {
                    d c = new d(i5, i6, i7, i8, fitnessGoal).c();
                    i5 = c.b();
                    i6 = c.d();
                    i7 = c.e();
                    i8 = c.a();
                }
            }
        }
        stringBuffer.append(d(i, i2, i3, i4) + ",");
        stringBuffer.append(e(i5, i6, i7, i8));
    }

    private static FitWorkout d(WorkoutRecord workoutRecord) {
        FitWorkout a2;
        if (workoutRecord.acquireFinishRate() <= 50.0f || (a2 = ett.i().l().a(workoutRecord.acquireWorkoutId())) == null || a2.isRunModelCourse()) {
            return null;
        }
        return a2;
    }

    private static List<WorkoutRecord> e() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(5, -14);
        return etr.b().getWorkoutRecords(calendar.getTimeInMillis(), currentTimeMillis);
    }

    private static String d(int i, int i2, int i3, int i4) {
        String str;
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return "-1";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("l1Count", Integer.valueOf(i));
        linkedHashMap.put("l2Count", Integer.valueOf(i2));
        linkedHashMap.put("l3Count", Integer.valueOf(i3));
        linkedHashMap.put("l4Count", Integer.valueOf(i4));
        int intValue = ((Integer) Collections.max(linkedHashMap.values())).intValue();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (((Integer) entry.getValue()).intValue() == intValue) {
                str = (String) entry.getKey();
                break;
            }
        }
        return ("l1Count".equals(str) || "l2Count".equals(str)) ? "0" : "l3Count".equals(str) ? "1" : "l4Count".equals(str) ? "2" : "-1";
    }

    private static String e(int i, int i2, int i3, int i4) {
        String str;
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return "-1";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("fatLossCount", Integer.valueOf(i));
        linkedHashMap.put("shapingCount", Integer.valueOf(i2));
        linkedHashMap.put("muscleCount", Integer.valueOf(i3));
        linkedHashMap.put("keepHealthCount", Integer.valueOf(i4));
        int intValue = ((Integer) Collections.max(linkedHashMap.values())).intValue();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (((Integer) entry.getValue()).intValue() == intValue) {
                str = (String) entry.getKey();
                break;
            }
        }
        return "fatLossCount".equals(str) ? "0" : "shapingCount".equals(str) ? "1" : "muscleCount".equals(str) ? "2" : "keepHealthCount".equals(str) ? "3" : "-1";
    }

    private static void a(StringBuffer stringBuffer) {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("RecommendWorkoutInterators", "getFitnessDatas : userProfileMgrApi is null.");
            return;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo != null) {
            int heightOrDefaultValue = userInfo.getHeightOrDefaultValue();
            float weightOrDefaultValue = userInfo.getWeightOrDefaultValue();
            int genderOrDefaultValue = userInfo.getGenderOrDefaultValue();
            if (genderOrDefaultValue != 1 && genderOrDefaultValue != 0) {
                genderOrDefaultValue = -1;
            }
            stringBuffer.append(genderOrDefaultValue + ",");
            double a2 = arw.a(heightOrDefaultValue, weightOrDefaultValue);
            if (a2 > 0.0d && a2 <= 18.5d) {
                stringBuffer.append("0");
            } else if (a2 > 18.5d && a2 < 25.0d) {
                stringBuffer.append("1");
            } else if (a2 >= 25.0d) {
                stringBuffer.append("2");
            } else {
                stringBuffer.append("-1");
            }
            stringBuffer.append(",");
            return;
        }
        stringBuffer.append("-1,-1,");
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private int f12332a;
        private int b;
        private int c;
        private String d;
        private int e;

        d(int i, int i2, int i3, int i4, String str) {
            this.e = i;
            this.f12332a = i2;
            this.b = i3;
            this.c = i4;
            this.d = str;
        }

        public int b() {
            return this.e;
        }

        public int d() {
            return this.f12332a;
        }

        public int e() {
            return this.b;
        }

        public int a() {
            return this.c;
        }

        public d c() {
            if (this.d.indexOf("0") >= 0) {
                this.e++;
            }
            if (this.d.indexOf("1") >= 0) {
                this.f12332a++;
            }
            if (this.d.indexOf("2") >= 0) {
                this.b++;
            }
            if (this.d.indexOf("3") >= 0) {
                this.c++;
            }
            return this;
        }
    }
}
