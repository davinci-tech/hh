package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.bean.TaskConfigBean;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class aus {
    public static List<HealthLifeBean> jS_(String str, int i, SparseArray<TaskConfigBean> sparseArray, boolean z) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        HealthLifeBean b = b(str, 1, i);
        if (azi.j(b)) {
            str2 = b.getTarget();
        } else {
            b = new HealthLifeBean();
            b.setId(1);
            b.setRecordDay(i);
            str2 = "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("HealthLife_ConfigDatabaseHelper", "use default task");
            str2 = bcm.nt_(sparseArray);
            b.setTarget(str2);
            b.setLastTarget(str2);
            b.setAddStatus(1);
            b.setTimestamp(DateFormatUtil.c(i));
            b.setTimeZone(jdl.q(currentTimeMillis));
        }
        List<Integer> nx_ = bcm.nx_(sparseArray);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        jT_(nx_, arrayList, arrayList2, Arrays.asList(str2.split(",")), sparseArray);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(b);
        arrayList3.addAll(jR_(arrayList, 1, i, sparseArray, str));
        if (!z && i < DateFormatUtil.b(currentTimeMillis)) {
            return arrayList3;
        }
        arrayList3.addAll(jR_(arrayList2, 0, i, sparseArray, str));
        return arrayList3;
    }

    private static void jT_(List<Integer> list, List<Integer> list2, List<Integer> list3, List<String> list4, SparseArray<TaskConfigBean> sparseArray) {
        if (koq.b(list4)) {
            LogUtil.h("HealthLife_ConfigDatabaseHelper", "setListForQueryOneDay idStringList ", list4);
            return;
        }
        if (list4.contains(String.valueOf(4))) {
            list.add(4);
        }
        for (Integer num : list) {
            if (list4.contains(String.valueOf(num))) {
                list2.add(num);
            } else if (azi.c(num.intValue()) && sparseArray != null) {
                TaskConfigBean taskConfigBean = sparseArray.get(num.intValue());
                if (taskConfigBean != null && "false".equals(taskConfigBean.getSupportTask())) {
                    LogUtil.a("HealthLife_ConfigDatabaseHelper", "queryOneDay getSupportTask false id ", num);
                } else {
                    list3.add(num);
                }
            }
        }
    }

    private static List<HealthLifeBean> jR_(List<Integer> list, int i, int i2, SparseArray<TaskConfigBean> sparseArray, String str) {
        TaskConfigBean taskConfigBean;
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_ConfigDatabaseHelper", "getSubscriptionTaskBeanList idList ", list, " userId ", str);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Integer num : list) {
            HealthLifeBean b = b(str, num.intValue(), i2);
            if (!azi.j(b)) {
                b = new HealthLifeBean();
                b.setId(num.intValue());
                b.setRecordDay(i2);
                b.setTimestamp(DateFormatUtil.c(i2));
                b.setTimeZone(jdl.q(System.currentTimeMillis()));
            }
            b.setAddStatus(i);
            if (num.intValue() == 11) {
                arrayList.add(b);
            } else {
                boolean z = num.intValue() == 12;
                if (z && CommonUtils.h(b.getTarget()) < 2) {
                    b.setTarget("");
                }
                if (z) {
                    arrayList.add(b);
                } else {
                    if (TextUtils.isEmpty(b.getTarget()) && sparseArray != null && (taskConfigBean = sparseArray.get(num.intValue())) != null) {
                        b.setTarget(taskConfigBean.getDefaultGoal());
                    }
                    arrayList.add(b);
                }
            }
        }
        LogUtil.c("HealthLife_ConfigDatabaseHelper", "getSubscriptionTaskBeanList taskList ", arrayList);
        return arrayList;
    }

    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return auw.e("module_10031_health_task_config", "huid =? ", new String[]{str});
    }

    public static int e(int i) {
        return auw.e("module_10031_health_task_config", "huid =? and recordDay >? ", new String[]{azi.p(), String.valueOf(i)});
    }

    public static HealthLifeBean b(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return new HealthLifeBean();
        }
        return d("select * from module_10031_health_task_config where huid =? and id =? and recordDay<=? order by recordDay DESC limit 1", new String[]{str, String.valueOf(i), String.valueOf(i2)}, i2);
    }

    private static HealthLifeBean d(String str, String[] strArr, int i) {
        Cursor jV_;
        HealthLifeBean healthLifeBean = new HealthLifeBean();
        try {
            jV_ = auw.jV_(str, strArr);
            try {
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("HealthLife_ConfigDatabaseHelper", "queryRecently exception ", LogAnonymous.b((Throwable) e));
        }
        if (jV_ == null) {
            LogUtil.h("HealthLife_ConfigDatabaseHelper", "queryRecently cursor is null sql ", str, " selectionArgs ", strArr, " date ", Integer.valueOf(i));
            if (jV_ != null) {
                jV_.close();
            }
            return healthLifeBean;
        }
        while (jV_.moveToNext()) {
            jQ_(jV_, healthLifeBean, i);
        }
        if (jV_ != null) {
            jV_.close();
        }
        return healthLifeBean;
    }

    public static HealthLifeBean a(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            return new HealthLifeBean();
        }
        return e("select * from module_10031_health_task_config where huid =? and id =? and recordDay =? ", new String[]{str, String.valueOf(i), String.valueOf(i2)}, i2);
    }

    private static HealthLifeBean e(String str, String[] strArr, int i) {
        Cursor jV_;
        HealthLifeBean healthLifeBean = new HealthLifeBean();
        try {
            jV_ = auw.jV_(str, strArr);
            try {
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("HealthLife_ConfigDatabaseHelper", "query exception ", LogAnonymous.b((Throwable) e));
        }
        if (jV_ == null) {
            LogUtil.h("HealthLife_ConfigDatabaseHelper", "query cursor is null sql ", str, " selectionArgs ", strArr, " date ", Integer.valueOf(i));
            if (jV_ != null) {
                jV_.close();
            }
            return healthLifeBean;
        }
        if (jV_.moveToFirst()) {
            do {
                jQ_(jV_, healthLifeBean, i);
            } while (jV_.moveToNext());
        }
        if (jV_ != null) {
            jV_.close();
        }
        return healthLifeBean;
    }

    private static void jQ_(Cursor cursor, HealthLifeBean healthLifeBean, int i) {
        try {
            healthLifeBean.setTimeZone(cursor.getString(cursor.getColumnIndexOrThrow(DBSessionCommon.COLUMN_TIME_ZONE)));
            healthLifeBean.setLastTarget(cursor.getString(cursor.getColumnIndexOrThrow("lastTarget")));
            healthLifeBean.setCondition(cursor.getString(cursor.getColumnIndexOrThrow("condition")));
            healthLifeBean.setReserve(cursor.getString(cursor.getColumnIndexOrThrow("reserve")));
            healthLifeBean.setTarget(cursor.getString(cursor.getColumnIndexOrThrow("target")));
            healthLifeBean.setHuid(cursor.getString(cursor.getColumnIndexOrThrow("huid")));
            healthLifeBean.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow("timestamp")));
            healthLifeBean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            healthLifeBean.setRecordDay(cursor.getInt(cursor.getColumnIndexOrThrow(ParsedFieldTag.RECORD_DAY)));
            healthLifeBean.setSyncStatus(cursor.getInt(cursor.getColumnIndexOrThrow("syncStatus")));
            healthLifeBean.setChallengeId(cursor.getInt(cursor.getColumnIndexOrThrow("challengeId")));
            healthLifeBean.setDataSource(cursor.getInt(cursor.getColumnIndexOrThrow("dataSource")));
            healthLifeBean.setTaskType(cursor.getInt(cursor.getColumnIndexOrThrow(ParsedFieldTag.TASK_TYPE)));
            int id = healthLifeBean.getId();
            if (i != healthLifeBean.getRecordDay()) {
                if (id == 11 || id == 12) {
                    healthLifeBean.setTarget("");
                }
            }
        } catch (SQLiteException | IllegalArgumentException e) {
            LogUtil.b("HealthLife_ConfigDatabaseHelper", "conversion exception ", LogAnonymous.b(e));
        }
    }

    public static long c(List<HealthLifeBean> list, String str) {
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
        int id = healthLifeBean.getId();
        int recordDay = healthLifeBean.getRecordDay();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSessionCommon.COLUMN_TIME_ZONE, healthLifeBean.getTimeZone());
        contentValues.put("target", StringUtils.c((Object) healthLifeBean.getTarget()));
        contentValues.put("lastTarget", StringUtils.c((Object) healthLifeBean.getLastTarget()));
        contentValues.put("condition", healthLifeBean.getCondition());
        contentValues.put("reserve", healthLifeBean.getReserve());
        contentValues.put("huid", str);
        contentValues.put("timestamp", Long.valueOf(healthLifeBean.getTimestamp()));
        contentValues.put("id", Integer.valueOf(id));
        contentValues.put(ParsedFieldTag.RECORD_DAY, Integer.valueOf(recordDay));
        contentValues.put("syncStatus", Integer.valueOf(healthLifeBean.getSyncStatus()));
        contentValues.put("challengeId", Integer.valueOf(healthLifeBean.getChallengeId()));
        contentValues.put("dataSource", Integer.valueOf(healthLifeBean.getDataSource()));
        contentValues.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(healthLifeBean.getTaskType()));
        if (azi.j(a(id, recordDay, str))) {
            return auw.jW_("module_10031_health_task_config", contentValues, "huid =? and id =? and recordDay =? ", new String[]{str, String.valueOf(id), String.valueOf(recordDay)});
        }
        return auw.jU_("module_10031_health_task_config", contentValues);
    }
}
