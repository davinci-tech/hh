package defpackage;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.CalendarContract;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import defpackage.ffl;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public class fyo {
    public static int a(Context context) {
        int d = d(context);
        if (d < 0) {
            return 1;
        }
        LogUtil.d("CalendarEventUtil", "current id of CalendarAccount：" + d);
        return d;
    }

    private static int d(Context context) {
        try {
            Cursor query = context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, null, null, null, null);
            try {
                if (query == null) {
                    LogUtil.d("CalendarEventUtil", "userCursor null in checking CalendarAccount");
                    if (query != null) {
                        query.close();
                    }
                    return 1;
                }
                if (query.getCount() <= 0) {
                    if (query != null) {
                        query.close();
                    }
                    return 1;
                }
                query.moveToFirst();
                int i = query.getInt(query.getColumnIndex("_id"));
                if (query != null) {
                    query.close();
                }
                return i;
            } finally {
            }
        } catch (SQLException unused) {
            LogUtil.b("CalendarEventUtil", "checkCalendarAccount error");
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, boolean z, long j, Workout workout, String str) {
        ContentValues aIt_ = aIt_(context, workout.acquireName(), str, j);
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (z) {
                aIt_.put("hwext_service_type", "SportsExercise");
                String k = moe.k(workout.acquireDuration());
                FitWorkout a2 = mod.a(workout);
                String str2 = ggm.d(a2.acquireDifficulty()) + " " + k + BaseApplication.getContext().getString(R.string._2130851571_res_0x7f0236f3);
                aIt_.put("hwext_service_description", str2);
                String acquireId = workout.acquireId();
                String accquireVersion = a2.accquireVersion();
                LogUtil.b("CalendarEventUtil", "courseVersion = " + accquireVersion);
                aIt_.put("hwext_service_cp_bz_uri", "huaweischeme://healthapp/fitnesspage?skip_type=train_details&id=" + acquireId + Constants.VERSION + accquireVersion);
                LogUtil.d("CalendarEventUtil", "extDescription = ", str2, "cid=", acquireId, Constants.VERSION, accquireVersion);
                Uri insert = contentResolver.insert(Uri.parse("content://com.huawei.calendar/events"), aIt_);
                LogUtil.d("CalendarEventUtil", "insert event into huawei calendar, uri=", insert);
                if (insert == null) {
                    LogUtil.d("CalendarEventUtil", "insert event into android calendar, uri=", contentResolver.insert(CalendarContract.Events.CONTENT_URI, aIt_(context, workout.acquireName(), str, j)));
                }
            } else {
                LogUtil.d("CalendarEventUtil", "insert event into android calendar, uri=", contentResolver.insert(CalendarContract.Events.CONTENT_URI, aIt_));
            }
        } catch (IllegalArgumentException e) {
            LogUtil.e("CalendarEventUtil", "insertEvents exception:", LogAnonymous.b((Throwable) e));
        }
    }

    private static void c(Context context, boolean z, long j, mnt mntVar, String str) {
        if (mntVar == null) {
            return;
        }
        ContentValues aIt_ = aIt_(context, mntVar.d(), str, j);
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (z) {
                aIt_.put("hwext_service_type", "SportsExercise");
                String b = mntVar.b();
                long c = mntVar.c();
                if (b != null && !"Race".equals(b)) {
                    if (c >= 60) {
                        aIt_.put("hwext_service_description", moe.k(c) + BaseApplication.getContext().getString(R.string._2130851571_res_0x7f0236f3));
                    }
                    aIt_.put("hwext_service_cp_bz_uri", "huaweischeme://healthapp/fitnesspage?skip_type=train_details&id=" + b + "&version=2.0");
                    Uri insert = contentResolver.insert(Uri.parse("content://com.huawei.calendar/events"), aIt_);
                    LogUtil.d("CalendarEventUtil", "insertEventsForIntPlan insert event into huawei calendar, uri=" + insert);
                    if (insert == null) {
                        LogUtil.d("CalendarEventUtil", "insertEventsForIntPlan into android calendar, uri=" + contentResolver.insert(CalendarContract.Events.CONTENT_URI, aIt_(context, mntVar.d(), str, j)));
                        return;
                    }
                    return;
                }
                ReleaseLogUtil.a("CalendarEventUtil", "insertEventsForIntPlan ", b);
                return;
            }
            LogUtil.b("CalendarEventUtil", "insertEventsForIntPlan insert event into android calendar, uri=" + contentResolver.insert(CalendarContract.Events.CONTENT_URI, aIt_));
        } catch (IllegalArgumentException e) {
            LogUtil.e("CalendarEventUtil", "insertEventsForIntPlan exception:", LogAnonymous.b((Throwable) e));
        }
    }

    private static ContentValues aIt_(Context context, String str, String str2, long j) {
        ContentValues contentValues = new ContentValues();
        String id = TimeZone.getDefault().getID();
        LogUtil.d("CalendarEventUtil", "timezone:", id, " startTime:", Long.valueOf(j), " courseName:", str);
        contentValues.put("eventTimezone", id);
        contentValues.put("eventEndTimezone", id);
        contentValues.put("allDay", (Integer) 1);
        contentValues.put("title", str);
        contentValues.put("description", str2);
        contentValues.put("eventStatus", (Integer) 1);
        contentValues.put("selfAttendeeStatus", (Integer) 1);
        contentValues.put("dtstart", Long.valueOf(j));
        contentValues.put("dtend", Long.valueOf(86400000 + j));
        contentValues.put("hasAlarm", (Integer) 1);
        contentValues.put("calendar_id", Integer.valueOf(a(context)));
        contentValues.put("guestsCanModify", (Boolean) false);
        contentValues.put("isOrganizer", (Boolean) true);
        contentValues.put("accessLevel", (Integer) 0);
        contentValues.put("guestsCanInviteOthers", (Boolean) false);
        contentValues.put("guestsCanModify", (Boolean) false);
        contentValues.put("customAppUri", "huaweischeme://healthapp/fitnesspage?skip_type=main_page&fitness_type=2");
        return contentValues;
    }

    public static boolean aIu_(ContentResolver contentResolver) {
        if (contentResolver == null) {
            return false;
        }
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient("com.huawei.calendar");
        Object[] objArr = new Object[2];
        objArr[0] = "isSupportHWCalendarService:";
        objArr[1] = Boolean.valueOf(acquireUnstableContentProviderClient != null);
        LogUtil.d("CalendarEventUtil", objArr);
        return acquireUnstableContentProviderClient != null;
    }

    public static void c(Context context, String str, IntPlan.PlanType planType) {
        if (planType != null) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", 1);
            hashMap.put("type", Integer.valueOf(planType.getType()));
            ixx.d().d(context, AnalyticsValue.INT_PLAN_1120041.value(), hashMap, 0);
        }
        final ContentResolver contentResolver = context.getContentResolver();
        final String str2 = "(customAppUri = ?)";
        jdx.b(new Runnable() { // from class: fyq
            @Override // java.lang.Runnable
            public final void run() {
                fyo.aIv_(contentResolver, str2);
            }
        });
    }

    static /* synthetic */ void aIv_(ContentResolver contentResolver, String str) {
        try {
            LogUtil.d("CalendarEventUtil", "delete " + contentResolver.delete(CalendarContract.Events.CONTENT_URI, str, new String[]{"huaweischeme://healthapp/fitnesspage?skip_type=main_page&fitness_type=2"}) + " events of the invalid plan");
        } catch (IllegalArgumentException e) {
            LogUtil.e("CalendarEventUtil", "deleteCalendarEvent exception:", LogAnonymous.b((Throwable) e));
        }
    }

    public static boolean e(Context context) {
        return context != null && context.checkSelfPermission("android.permission.WRITE_CALENDAR") == 0 && context.checkSelfPermission("android.permission.READ_CALENDAR") == 0;
    }

    public static void a(final IntPlan intPlan, final Context context, int i) {
        if (intPlan == null) {
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(intPlan.getPlanType().getType()));
        hashMap.put("from", Integer.valueOf(i));
        ixx.d().d(context, AnalyticsValue.INT_PLAN_1120040.value(), hashMap, 0);
        String b = ash.b("ai_plan_sync");
        String b2 = ash.b("ai_plan_sync_name");
        if (b != null && !b.equals(intPlan.getPlanId())) {
            c(context, b2, (IntPlan.PlanType) null);
            ash.d("ai_plan_sync_name");
            ash.d("ai_plan_sync");
        }
        ash.a("ai_plan_sync", intPlan.getPlanId());
        ash.a("ai_plan_sync_name", intPlan.getMetaInfo().getName());
        final int weekCount = intPlan.getMetaInfo().getWeekCount();
        final boolean aIu_ = aIu_(context.getContentResolver());
        jdx.b(new Runnable() { // from class: fyn
            @Override // java.lang.Runnable
            public final void run() {
                fyo.a(IntPlan.this, context, weekCount, aIu_);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(IntPlan intPlan, Context context, int i, boolean z) {
        long beginDate = intPlan.getPlanTimeInfo().getBeginDate() * 1000;
        LogUtil.d("CalendarEventUtil", "planStartTime = ", Long.valueOf(beginDate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beginDate);
        int d = gib.d(calendar.get(7));
        int i2 = calendar.get(15);
        int i3 = calendar.get(16);
        calendar.add(14, i2 + i3);
        long timeInMillis = calendar.getTimeInMillis();
        LogUtil.d("CalendarEventUtil", "zoneOffset:", Integer.valueOf(i2), " dstOffset:", Integer.valueOf(i3), " planStartTimeUtc:", Long.valueOf(timeInMillis), "dayOrder:", Integer.valueOf(d));
        int i4 = 0;
        while (i4 < i) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i4);
            if (weekInfoByIdx != null) {
                for (int i5 = 0; i5 < weekInfoByIdx.getDayCount(); i5++) {
                    IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i5);
                    if (dayByIdx != null) {
                        int i6 = i4 == 0 ? i5 : (8 - d) + ((i4 - 1) * 7) + i5;
                        long j = timeInMillis + (i6 * 86400000);
                        String name = intPlan.getMetaInfo().getName();
                        LogUtil.d("CalendarEventUtil", "insertEventsForIntPlan: extraDay = ", Integer.valueOf(i6), " eventStartTime=", Long.valueOf(j), "planName = ", name);
                        if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN || intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                            e(context, z, dayByIdx, j, name);
                        } else {
                            c(context, z, dayByIdx, j, name);
                        }
                    }
                }
            }
            i4++;
        }
    }

    private static void c(final Context context, final boolean z, IntDayPlan intDayPlan, final long j, final String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null) {
                arrayList.add(new ffl.d(inPlanAction.getActionId()).b());
            }
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.e("CalendarEventUtil", "courseApi is null.");
        } else {
            courseApi.getCourseByIds(arrayList, false, new UiCallback<List<FitWorkout>>() { // from class: fyo.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str2) {
                    LogUtil.e("CalendarEventUtil", "getCourseByIds fail: ", str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    if (list == null || list.size() == 0) {
                        LogUtil.d("CalendarEventUtil", "getCourseByIds: null.");
                        return;
                    }
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        fyo.b(context, z, j, list.get(i2), str);
                    }
                }
            });
        }
    }

    private static void e(Context context, boolean z, IntDayPlan intDayPlan, long j, String str) {
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null) {
                c(context, z, j, inPlanAction.getActionInfo(), str);
            }
        }
    }

    public static long c(IntPlan intPlan, int i, int i2) {
        if (intPlan == null) {
            ReleaseLogUtil.a("CalendarEventUtil", "getPlanTimeMillis intPlan is null");
            return 0L;
        }
        PlanTimeInfo planTimeInfo = intPlan.getPlanTimeInfo();
        if (planTimeInfo == null) {
            ReleaseLogUtil.a("CalendarEventUtil", "getPlanTimeMillis planTimeInfo is null mIntPlan ", intPlan);
            return 0L;
        }
        return gib.e(planTimeInfo.getBeginDate() * 1000, i, i2);
    }
}
