package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.DbManager;
import health.compact.a.KeyValDbManager;

/* loaded from: classes5.dex */
public class DeviceDbForHomeCards {
    public static final String EXERCISE_INSERT_TIME = "exerciseInsertTime";
    public static final String EXERCISE_INSERT_TIME_TABLE_NAME = "exercise_insert_time_table";
    public static final String HUID = "huid";
    private static final String TAG = "DeviceDBForHomeCards";
    public static final String USER_ID = "user_id";

    private DeviceDbForHomeCards() {
    }

    public static void updateExerciseInsertTime(long j) {
        LogUtil.a(TAG, "enter updateExerciseInsertTime with currentTime = " + j);
        DbManager.c(BaseApplication.getContext(), String.valueOf(22), EXERCISE_INSERT_TIME_TABLE_NAME, 2, "huid varchar primary key ,exerciseInsertTime exerciseInsertTime varchar");
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_INSERT_TIME, Long.toString(j));
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        contentValues.put("huid", e);
        if (isTableHaveCurrentUser()) {
            DbManager.b bVar = new DbManager.b();
            bVar.b(BaseApplication.getContext());
            bVar.e(String.valueOf(22));
            bVar.c(EXERCISE_INSERT_TIME_TABLE_NAME);
            bVar.a(2);
            DbManager.bGH_(bVar, contentValues, "huid='" + e + "'");
            return;
        }
        DbManager.bGC_(BaseApplication.getContext(), String.valueOf(22), EXERCISE_INSERT_TIME_TABLE_NAME, 2, contentValues);
    }

    private static boolean isTableHaveCurrentUser() {
        LogUtil.a(TAG, "enter isHaveCurrentUser");
        Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), String.valueOf(String.valueOf(22)), EXERCISE_INSERT_TIME_TABLE_NAME, 2, "huid='" + KeyValDbManager.b(BaseApplication.getContext()).e("user_id") + "'");
        if (bGE_ != null) {
            r2 = bGE_.getCount() > 0;
            bGE_.close();
        }
        LogUtil.c(TAG, "isHaveKeyInfo:", Boolean.valueOf(r2));
        return r2;
    }
}
