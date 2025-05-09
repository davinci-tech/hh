package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class auz {
    public static int d(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return auw.e("module_10031_health_task_records", "huid =? ", new String[]{str});
    }

    public static int e(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return auw.e("module_10031_health_task_records", "huid =? and id =? and recordDay =? ", new String[]{str, String.valueOf(i2), String.valueOf(i)});
    }

    public static int d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return auw.e("module_10031_health_task_records", "huid =? and recordDay<?", new String[]{str, String.valueOf(i)});
    }

    public static int b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return auw.e("module_10031_health_task_records", "huid =? and recordDay >? ", new String[]{str, String.valueOf(i)});
    }

    public static int a(int i, int i2) {
        return e("select count(*) as recordCount from module_10031_health_task_records where huid =? and status >=1 and recordDay >=? and recordDay <=? ", new String[]{azi.p(), String.valueOf(i), String.valueOf(i2)});
    }

    public static int a(int i, int i2, int i3) {
        return e("select count(*) as recordCount from module_10031_health_task_records where huid =? and id =? and status >=1 and recordDay >=? and recordDay <=? ", new String[]{azi.p(), String.valueOf(i3), String.valueOf(i), String.valueOf(i2)});
    }

    public static int e(int i, int i2, int i3) {
        return e("select count(*) as recordCount from module_10031_health_task_records where huid =? and id =? and status =4 and recordDay >=? and recordDay <=? ", new String[]{azi.p(), String.valueOf(i3), String.valueOf(i), String.valueOf(i2)});
    }

    private static int e(String str, String[] strArr) {
        Cursor jV_;
        try {
            jV_ = auw.jV_(str, strArr);
            try {
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("HealthLife_RecordDatabaseHelper", "queryRecordCount exception ", LogAnonymous.b((Throwable) e));
        }
        if (jV_ == null) {
            LogUtil.h("HealthLife_RecordDatabaseHelper", "queryRecordCount cursor is null sql ", str, " selectionArgs ", strArr);
            if (jV_ != null) {
                jV_.close();
            }
            return 0;
        }
        r1 = jV_.moveToNext() ? jV_.getInt(jV_.getColumnIndexOrThrow("recordCount")) : -1;
        if (jV_ != null) {
            jV_.close();
        }
        return r1;
    }

    public static HealthLifeBean a(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            return new HealthLifeBean();
        }
        return b("select * from module_10031_health_task_records where huid =? and id =? and recordDay =? ", new String[]{str, String.valueOf(i), String.valueOf(i2)});
    }

    private static HealthLifeBean b(String str, String[] strArr) {
        Cursor jV_;
        HealthLifeBean healthLifeBean = new HealthLifeBean();
        try {
            jV_ = auw.jV_(str, strArr);
            try {
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("HealthLife_RecordDatabaseHelper", "query exception ", LogAnonymous.b((Throwable) e));
        }
        if (jV_ == null) {
            LogUtil.h("HealthLife_RecordDatabaseHelper", "query cursor is null sql ", str, " selectionArgs ", strArr);
            if (jV_ != null) {
                jV_.close();
            }
            return healthLifeBean;
        }
        if (jV_.moveToFirst()) {
            do {
                jX_(jV_, healthLifeBean);
            } while (jV_.moveToNext());
        }
        if (jV_ != null) {
            jV_.close();
        }
        return healthLifeBean;
    }

    public static List<HealthLifeBean> b(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        return a("select * from module_10031_health_task_records where huid =? and syncStatus <=? and recordDay between " + i + " and " + i2 + " order by recordDay asc ", new String[]{str, String.valueOf(0)});
    }

    public static List<HealthLifeBean> d(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        return a("select * from module_10031_health_task_records where huid =? and recordDay >=? and recordDay <=? ", new String[]{str, String.valueOf(i), String.valueOf(i2)});
    }

    private static List<HealthLifeBean> a(String str, String[] strArr) {
        Cursor jV_;
        ArrayList arrayList = new ArrayList();
        try {
            jV_ = auw.jV_(str, strArr);
            try {
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("HealthLife_RecordDatabaseHelper", "queryList exception ", LogAnonymous.b((Throwable) e));
        }
        if (jV_ == null) {
            LogUtil.h("HealthLife_RecordDatabaseHelper", "queryList cursor is null sql ", str, " selectionArgs ", strArr);
            if (jV_ != null) {
                jV_.close();
            }
            return arrayList;
        }
        if (jV_.moveToFirst()) {
            do {
                HealthLifeBean healthLifeBean = new HealthLifeBean();
                jX_(jV_, healthLifeBean);
                arrayList.add(healthLifeBean);
            } while (jV_.moveToNext());
        }
        if (jV_ != null) {
            jV_.close();
        }
        return arrayList;
    }

    private static void jX_(Cursor cursor, HealthLifeBean healthLifeBean) {
        try {
            healthLifeBean.setRest(cursor.getInt(cursor.getColumnIndexOrThrow("restStatus")));
            healthLifeBean.setTimeZone(cursor.getString(cursor.getColumnIndexOrThrow(DBSessionCommon.COLUMN_TIME_ZONE)));
            healthLifeBean.setCondition(cursor.getString(cursor.getColumnIndexOrThrow("condition")));
            healthLifeBean.setReserve(cursor.getString(cursor.getColumnIndexOrThrow("reserve")));
            healthLifeBean.setResult(cursor.getString(cursor.getColumnIndexOrThrow("result")));
            healthLifeBean.setTarget(cursor.getString(cursor.getColumnIndexOrThrow("target")));
            healthLifeBean.setHuid(cursor.getString(cursor.getColumnIndexOrThrow("huid")));
            healthLifeBean.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow("timestamp")));
            healthLifeBean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            healthLifeBean.setType(cursor.getInt(cursor.getColumnIndexOrThrow("type")));
            healthLifeBean.setComplete(cursor.getInt(cursor.getColumnIndexOrThrow("status")));
            healthLifeBean.setRecordDay(cursor.getInt(cursor.getColumnIndexOrThrow(ParsedFieldTag.RECORD_DAY)));
            healthLifeBean.setSyncStatus(cursor.getInt(cursor.getColumnIndexOrThrow("syncStatus")));
            healthLifeBean.setChallengeId(cursor.getInt(cursor.getColumnIndexOrThrow("challengeId")));
            healthLifeBean.setDataSource(cursor.getInt(cursor.getColumnIndexOrThrow("dataSource")));
            healthLifeBean.setTaskType(cursor.getInt(cursor.getColumnIndexOrThrow(ParsedFieldTag.TASK_TYPE)));
        } catch (SQLiteException | IllegalArgumentException e) {
            LogUtil.b("HealthLife_RecordDatabaseHelper", "conversion exception ", LogAnonymous.b(e));
        }
    }

    public static long a(List<HealthLifeBean> list, String str) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            return -1L;
        }
        Iterator<HealthLifeBean> it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            if (a(it.next(), str) == -1) {
                j = -1;
            }
        }
        return j;
    }

    public static long a(HealthLifeBean healthLifeBean, String str) {
        if (!azi.j(healthLifeBean) || TextUtils.isEmpty(str)) {
            return -1L;
        }
        HealthLifeBean a2 = a(healthLifeBean.getId(), healthLifeBean.getRecordDay(), str);
        if (azi.j(a2)) {
            if (c(healthLifeBean, a2)) {
                return b(healthLifeBean, str);
            }
            return 0L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("restStatus", Integer.valueOf(healthLifeBean.getRest()));
        contentValues.put(DBSessionCommon.COLUMN_TIME_ZONE, healthLifeBean.getTimeZone());
        contentValues.put("huid", str);
        contentValues.put("id", Integer.valueOf(healthLifeBean.getId()));
        contentValues.put("type", Integer.valueOf(healthLifeBean.getType()));
        contentValues.put("status", Integer.valueOf(healthLifeBean.getComplete()));
        contentValues.put("target", StringUtils.c((Object) healthLifeBean.getTarget()));
        contentValues.put("result", StringUtils.c((Object) healthLifeBean.getResult()));
        contentValues.put("timestamp", Long.valueOf(healthLifeBean.getTimestamp()));
        contentValues.put("condition", healthLifeBean.getCondition());
        contentValues.put("reserve", healthLifeBean.getReserve());
        contentValues.put(ParsedFieldTag.RECORD_DAY, Integer.valueOf(healthLifeBean.getRecordDay()));
        contentValues.put("syncStatus", Integer.valueOf(healthLifeBean.getSyncStatus()));
        contentValues.put("challengeId", Integer.valueOf(healthLifeBean.getChallengeId()));
        contentValues.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(healthLifeBean.getTaskType()));
        contentValues.put("dataSource", Integer.valueOf(healthLifeBean.getDataSource()));
        return auw.jU_("module_10031_health_task_records", contentValues);
    }

    public static void d(List<HealthLifeBean> list, String str) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_RecordDatabaseHelper", "updateList list ", list, " userId ", str);
            return;
        }
        Iterator<HealthLifeBean> it = list.iterator();
        while (it.hasNext()) {
            b(it.next(), str);
        }
    }

    private static int b(HealthLifeBean healthLifeBean, String str) {
        if (!azi.j(healthLifeBean) || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_RecordDatabaseHelper", "update bean ", healthLifeBean, " userId ", str);
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("restStatus", Integer.valueOf(healthLifeBean.getRest()));
        contentValues.put(DBSessionCommon.COLUMN_TIME_ZONE, healthLifeBean.getTimeZone());
        contentValues.put("status", Integer.valueOf(healthLifeBean.getComplete()));
        contentValues.put("target", StringUtils.c((Object) healthLifeBean.getTarget()));
        contentValues.put("result", StringUtils.c((Object) healthLifeBean.getResult()));
        contentValues.put("timestamp", Long.valueOf(healthLifeBean.getTimestamp()));
        contentValues.put("syncStatus", Integer.valueOf(healthLifeBean.getSyncStatus()));
        contentValues.put("challengeId", Integer.valueOf(healthLifeBean.getChallengeId()));
        contentValues.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(healthLifeBean.getTaskType()));
        contentValues.put("dataSource", Integer.valueOf(healthLifeBean.getDataSource()));
        return auw.jW_("module_10031_health_task_records", contentValues, "huid =? and id =? and recordDay =? ", new String[]{str, String.valueOf(healthLifeBean.getId()), String.valueOf(healthLifeBean.getRecordDay())});
    }

    private static boolean c(HealthLifeBean healthLifeBean, HealthLifeBean healthLifeBean2) {
        int complete = healthLifeBean.getComplete();
        int complete2 = healthLifeBean2.getComplete();
        int b = b(healthLifeBean.getId(), healthLifeBean.getResult());
        int b2 = b(healthLifeBean2.getId(), healthLifeBean2.getResult());
        if (complete <= 0 && complete2 <= 0) {
            if (b > b2) {
                return true;
            }
            if (b != b2 || healthLifeBean.getChallengeId() == healthLifeBean2.getChallengeId()) {
                return false;
            }
            return healthLifeBean.getIsUpdated();
        }
        if (complete > 0 && complete2 <= 0) {
            return true;
        }
        if (complete <= 0) {
            return false;
        }
        if (TextUtils.isEmpty(healthLifeBean.getResult()) || !TextUtils.isEmpty(healthLifeBean2.getResult())) {
            return (!TextUtils.isEmpty(healthLifeBean.getResult()) || TextUtils.isEmpty(healthLifeBean2.getResult())) && b >= b2;
        }
        return true;
    }

    private static int b(int i, String str) {
        if (i == 7 || i == 6) {
            return 0;
        }
        return CommonUtil.h(str);
    }
}
