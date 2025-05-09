package com.huawei.featureuserprofile.todo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.huawei.featureuserprofile.todo.TodoJsApi;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryCbk;
import com.huawei.wearengine.sensor.DataResult;
import defpackage.bvw;
import defpackage.nrk;
import health.compact.a.CommonUtil;
import java.util.TimeZone;

@H5ProService(name = TodoJsApi.TAG, users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class TodoJsApi {
    private static final String TAG = "TodoJsApi";

    @H5ProMethod(name = "todoSyncToCalendar")
    public static void todoSyncToCalendar(String str, String str2, String str3, DietDiaryCbk<Boolean> dietDiaryCbk) {
        if (dietDiaryCbk == null) {
            LogUtil.h(TAG, "todoTaskSyncToCalendar callback is null, return");
            return;
        }
        if (!CommonUtil.bh()) {
            LogUtil.h(TAG, "todoTaskSyncToCalendar is not huawei system. todoName = ", str);
            dietDiaryCbk.onFailure(-1, "not huawei system");
        } else if (!nrk.d(BaseApplication.getContext())) {
            LogUtil.h(TAG, "todoTaskSyncToCalendar no calender permission todoName = ", str);
            dietDiaryCbk.onFailure(-1, "no calender permission");
        } else {
            calenderSync(str, str2, str3, dietDiaryCbk);
        }
    }

    private static void calenderSync(final String str, final String str2, final String str3, final DietDiaryCbk<Boolean> dietDiaryCbk) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: buz
            @Override // java.lang.Runnable
            public final void run() {
                TodoJsApi.lambda$calenderSync$0(str, str2, str3, dietDiaryCbk);
            }
        });
    }

    public static /* synthetic */ void lambda$calenderSync$0(String str, String str2, String str3, DietDiaryCbk dietDiaryCbk) {
        ContentValues contentValues = getContentValues(str, str2, str3);
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        if (nrk.cJX_(contentResolver)) {
            contentValues.put("hwext_service_type", "SportsExercise");
            contentValues.put("hwext_service_description", str2);
            contentValues.put("hwext_service_cp_bz_uri", str3);
            Uri insert = contentResolver.insert(Uri.parse("content://com.huawei.calendar/events"), contentValues);
            LogUtil.a(TAG, "insert event into huawei calendar, uri=", insert);
            if (insert == null) {
                LogUtil.a(TAG, "insert event into android calendar, uri=", contentResolver.insert(CalendarContract.Events.CONTENT_URI, getContentValues(str, str2, str3)));
            }
        } else {
            LogUtil.a(TAG, "insert event into android calendar, uri=", contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues));
        }
        dietDiaryCbk.onSuccess(true);
    }

    private static ContentValues getContentValues(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventTimezone", String.valueOf(TimeZone.getTimeZone(DataResult.UTC)));
        contentValues.put("allDay", (Integer) 1);
        contentValues.put("title", str);
        contentValues.put("description", str2);
        contentValues.put("eventStatus", (Integer) 1);
        contentValues.put("selfAttendeeStatus", (Integer) 1);
        contentValues.put("dtstart", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("dtend", Long.valueOf(System.currentTimeMillis() + 86400000));
        contentValues.put("hasAlarm", (Integer) 1);
        contentValues.put("calendar_id", Integer.valueOf(nrk.b(BaseApplication.getContext())));
        contentValues.put("guestsCanModify", (Boolean) false);
        contentValues.put("isOrganizer", (Boolean) true);
        contentValues.put("accessLevel", (Integer) 0);
        contentValues.put("guestsCanInviteOthers", (Boolean) false);
        contentValues.put("guestsCanModify", (Boolean) false);
        contentValues.put("customAppUri", str3);
        LogUtil.a(TAG, "todoTaskSyncToCalendar todoName = ", str);
        return contentValues;
    }

    @H5ProMethod(name = "getTodoSwitchStatus")
    public static void getTodoSwitchStatus(final String str, final DietDiaryCbk<String> dietDiaryCbk) {
        if (dietDiaryCbk == null) {
            LogUtil.h(TAG, "getTodoSwitchStatus callback is null, return");
        } else if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "getTodoSwitchStatus switchType is empty, return");
            dietDiaryCbk.onFailure(-1, "switch type is empty");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: buw
                @Override // java.lang.Runnable
                public final void run() {
                    TodoJsApi.lambda$getTodoSwitchStatus$1(str, dietDiaryCbk);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getTodoSwitchStatus$1(String str, DietDiaryCbk dietDiaryCbk) {
        String d = bvw.d(str);
        if (TextUtils.isEmpty(d)) {
            dietDiaryCbk.onFailure(-1, "switch type error");
        } else {
            dietDiaryCbk.onSuccess(d);
        }
    }

    @H5ProMethod(name = "changeTodoSwitch")
    public static void changeTodoSwitch(final String str, final boolean z, final int i, final DietDiaryCbk<Boolean> dietDiaryCbk) {
        if (dietDiaryCbk == null) {
            LogUtil.h(TAG, "getTodoSwitchStatus callback is null, return");
        } else if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "getTodoSwitchStatus switchType is empty, return");
            dietDiaryCbk.onFailure(-1, "switch type is empty");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: buv
                @Override // java.lang.Runnable
                public final void run() {
                    dietDiaryCbk.onSuccess(Boolean.valueOf(bvw.b(str, z, i)));
                }
            });
        }
    }
}
