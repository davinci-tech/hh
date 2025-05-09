package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class fpn {
    private static boolean e(String str) {
        return (TextUtils.isEmpty(str) || !str.contains("_") || str.startsWith("_")) ? false : true;
    }

    private static boolean d(String[] strArr) {
        String str;
        return strArr.length > 1 && (str = strArr[0]) != null && b(str);
    }

    private static boolean b(String str) {
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    public static void b(WorkoutRecord workoutRecord, SimpleDateFormat simpleDateFormat, String[] strArr) {
        if (workoutRecord == null) {
            LogUtil.h("Suggestion_TrainEventHelper", "getExerciseTimeTemp itemData == null");
            return;
        }
        String acquireTrajectoryId = workoutRecord.acquireTrajectoryId();
        if (e(acquireTrajectoryId)) {
            String[] split = acquireTrajectoryId.split("_");
            if (d(split)) {
                try {
                    strArr[0] = simpleDateFormat.format(new Date(Long.parseLong(split[0])));
                    strArr[1] = SimpleDateFormat.getDateInstance(3).format(new Date(Long.parseLong(split[0])));
                } catch (NumberFormatException unused) {
                    LogUtil.b("Suggestion_TrainEventHelper", "getExerciseTimeTemp error trajectoryId:", acquireTrajectoryId);
                }
            }
        }
    }

    public static final String b(double d) {
        return String.format(Locale.ROOT, "%02d'%02d\"", Long.valueOf(Math.round(d) / 60), Long.valueOf(Math.round(d) % 60));
    }

    private static fqv e(Calendar calendar, SimpleDateFormat simpleDateFormat, int i) {
        if (calendar.get(5) == 1 || calendar.get(5) == 15) {
            return new fqv(0.0f, simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis())), i);
        }
        return new fqv(0.0f, "", i);
    }

    private static fqv a(int i) {
        if (i == 0 || i == 9 || i == 19) {
            return new fqv(0.0f, ffy.d(BaseApplication.getContext(), R.string._2130848387_res_0x7f022a83, UnitUtil.e(i + 1, 1, 0)), i);
        }
        return new fqv(0.0f, "", i);
    }

    public static void c(List<fqv> list, List<fqv> list2, Plan plan) {
        if (plan == null || list == null || list2 == null) {
            LogUtil.h("Suggestion_TrainEventHelper", "getTotalExcelList joinedPlan == null || excelList == null || anotherExcelList == null");
            return;
        }
        if (plan.getWeekCount() <= 8) {
            int i = 0;
            while (i < plan.getWeekCount()) {
                int i2 = i + 1;
                fqv fqvVar = new fqv(0.0f, ffy.d(BaseApplication.getContext(), R.string._2130848387_res_0x7f022a83, UnitUtil.e(i2, 1, 0)), i);
                list.add(fqvVar);
                if (fqvVar.clone() instanceof fqv) {
                    list2.add((fqv) fqvVar.clone());
                }
                i = i2;
            }
            return;
        }
        for (int i3 = 0; i3 < plan.getWeekCount(); i3++) {
            fqv a2 = a(i3);
            list.add(a2);
            if (a2.clone() instanceof fqv) {
                list2.add((fqv) a2.clone());
            }
        }
    }

    public static void c(List<fqv> list, c cVar, DecimalFormat decimalFormat, boolean z) {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_TrainEventHelper", "diffRecordType : userProfileMgrApi is null.");
            return;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (cVar == null) {
            LogUtil.h("Suggestion_TrainEventHelper", "eventData == null");
            return;
        }
        if (decimalFormat == null) {
            LogUtil.h("Suggestion_TrainEventHelper", "decimalFormat == null");
            return;
        }
        if (koq.d(list, cVar.c)) {
            fqv fqvVar = list.get(cVar.c);
            if (cVar.f12597a == 0) {
                fqvVar.a(3);
                fqvVar.h((float) (fqvVar.f() + moe.g(cVar.d.acquireDistance())));
                fqvVar.d(fqvVar.o() + CommonUtil.j(moe.d(moe.g(cVar.d.acquireActualDistance()))));
                fqvVar.c(ffy.b(ffz.c(), (int) fqvVar.o(), decimalFormat.format(fqvVar.o())));
                return;
            }
            if (cVar.f12597a == 1) {
                fqvVar.a(1);
                fqvVar.h(fqvVar.f() + cVar.d.acquireCalorie());
                fqvVar.d(fqvVar.o() + cVar.d.acquireActualCalorie());
                fqvVar.i(fqvVar.o());
                fqvVar.c(ffy.d(BaseApplication.getContext(), R.string._2130837535_res_0x7f02001f, new DecimalFormat("#").format(Math.round(fqvVar.o() / 1000.0f))));
                return;
            }
            if (cVar.f12597a == 2) {
                a(cVar, fqvVar);
            } else if (cVar.f12597a == 3) {
                e(cVar, userInfo, fqvVar, z);
            }
        }
    }

    private static void a(c cVar, fqv fqvVar) {
        fqvVar.h(fqvVar.f() + CommonUtil.j(moe.d(moe.g(cVar.d.acquireActualDistance()))));
        fqvVar.d(fqvVar.o() + cVar.d.getDuration());
        fqvVar.g(fqvVar.p() + cVar.d.getDuration());
        fqvVar.i(fqvVar.f() == 0.0f ? 0.0f : fqvVar.p() / fqvVar.f());
        fqvVar.a(2);
        fqvVar.c(ffy.d(BaseApplication.getContext(), ffz.b(), b(cVar.d.acquireActualDistance() == 0.0f ? 0.0d : cVar.d.getDuration() / moe.g(cVar.d.acquireActualDistance()))));
    }

    private static void e(c cVar, UserInfomation userInfomation, fqv fqvVar, boolean z) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_TrainEventHelper", "setPercentExcel : planApi is null.");
            return;
        }
        fqvVar.g(fqvVar.p() + cVar.d.acquireActualCalorie());
        if (cVar.e == null) {
            LogUtil.h("Suggestion_TrainEventHelper", "diffRecordType firstDay == null");
            fqvVar.j(planApi.getFitnessPlanShouldCompleteCalorie(1, cVar.d, userInfomation));
        } else {
            fqvVar.j(planApi.getFitnessPlanShouldCompleteCalorie(0, cVar.d, userInfomation));
        }
        if (Math.abs(fqvVar.s()) > 1.0E-6d) {
            fqvVar.d(gdr.c((fqvVar.p() / fqvVar.s()) * 100.0f));
        }
        fqvVar.a(0);
        fqvVar.c(UnitUtil.e((!z || fqvVar.o() <= 99.0f) ? fqvVar.o() : 100.0d, 2, 1));
    }

    public static void e(List<WorkoutRecord> list, List<Object> list2) {
        if (list != null) {
            for (WorkoutRecord workoutRecord : list) {
                if (b(workoutRecord)) {
                    list2.add(workoutRecord);
                }
            }
        }
    }

    private static boolean b(WorkoutRecord workoutRecord) {
        return workoutRecord.acquireActualCalorie() > 0.0f || workoutRecord.acquireActualDistance() > 0.0f;
    }

    public static fqv d(Calendar calendar, SimpleDateFormat simpleDateFormat, int i, int i2) {
        fqv fqvVar;
        if (i2 == 3) {
            fqvVar = e(calendar, simpleDateFormat, i);
        } else {
            fqvVar = new fqv(0.0f, simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis())), i);
        }
        calendar.add(6, 1);
        return fqvVar;
    }

    public static int c(Calendar calendar, SimpleDateFormat simpleDateFormat, WorkoutRecord workoutRecord, Date date) throws ParseException {
        if (workoutRecord == null) {
            return -1;
        }
        if (calendar == null) {
            int a2 = ggl.a(date, simpleDateFormat.parse(workoutRecord.acquireWorkoutDate()));
            return a2 >= 0 ? a2 / 7 : a2;
        }
        return ggl.a(calendar.getTime(), simpleDateFormat.parse(workoutRecord.acquireWorkoutDate()));
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private int f12597a;
        private int c;
        private WorkoutRecord d;
        private Calendar e;

        public c(Calendar calendar, int i, WorkoutRecord workoutRecord, int i2) {
            this.e = calendar;
            this.f12597a = i;
            this.d = workoutRecord;
            this.c = i2;
        }
    }
}
