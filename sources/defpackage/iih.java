package defpackage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiHealthUserPermission;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.WearKitPermission;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.KeyManager;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class iih {
    public static List<HiHealthData> bAN_(Cursor cursor, String str) {
        return DBPointCommon.parsePointListWithDeviceUUID(cursor, str);
    }

    public static List<HiHealthData> bAz_(Cursor cursor) {
        return DBPointCommon.parseMergePointListWithClientIdAndMerged(cursor);
    }

    public static List<HiHealthData> bAD_(Cursor cursor) {
        return DBPointCommon.parseNoSyncPointList(cursor);
    }

    public static List<HiHealthData> bAO_(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseRawSportCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setStartTime(cursor.getLong(cursor.getColumnIndex("start_time")));
                hiHealthData.setEndTime(cursor.getLong(cursor.getColumnIndex("end_time")));
                hiHealthData.setType(cursor.getInt(cursor.getColumnIndex("session_type")));
                for (String str : strArr) {
                    hiHealthData.putDouble(str, cursor.getDouble(cursor.getColumnIndex(str)));
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> bAE_(Cursor cursor, String[] strArr) {
        return DBSessionCommon.parseNoSyncRawSportCursor(cursor, strArr);
    }

    public static List<HiHealthData> bAC_(Cursor cursor, String[] strArr) {
        return DBPointCommon.parseNoSyncDeviceStatCursor(cursor, strArr);
    }

    public static List<HiHealthData> bAa_(Cursor cursor, String[] strArr) {
        return DBPointCommon.parseAggregatePointCursor(cursor, strArr);
    }

    public static List<HiHealthData> bAi_(Cursor cursor, String[] strArr) {
        return DBPointCommon.parseAggregateWeightPointCursor(cursor, strArr);
    }

    public static List<HiHealthData> bzY_(Cursor cursor, String[] strArr) {
        return DBPointCommon.parseAggregateHealthPointCursor(cursor, strArr);
    }

    public static List<HiHealthData> bAc_(Cursor cursor, String[] strArr) {
        return DBSessionCommon.parseAggregateRawMixCursor(cursor, strArr);
    }

    public static List<HiHealthData> bzZ_(Cursor cursor, String[] strArr) {
        return DBSessionCommon.parseAggregateHealthSessionCursor(cursor, strArr);
    }

    public static List<HiHealthData> bAh_(Cursor cursor, String[] strArr, boolean z) {
        return iht.byx_(cursor, strArr, z);
    }

    public static List<HiHealthData> bAg_(int i, Cursor cursor, String[] strArr) {
        return iht.byw_(i, cursor, strArr);
    }

    public static List<HiHealthData> bAF_(Cursor cursor, String[] strArr, boolean z) {
        return iht.byy_(cursor, strArr, z);
    }

    public static List<HiHealthData> bAe_(Cursor cursor, String str) {
        return DBSessionCommon.parseAggregateRawSessionCursor(cursor, str);
    }

    public static List<HiHealthData> bAb_(Cursor cursor, String str) {
        return DBSessionCommon.parseAggregateRawHealthSessionCursor(cursor, str);
    }

    public static List<HiHealthData> bAf_(Cursor cursor, String str) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseAggregateSessionChangeCountCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.putInt(str, cursor.getInt(cursor.getColumnIndex(str)));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> bAd_(Cursor cursor, String[] strArr) {
        return ihq.byh_(cursor, strArr);
    }

    public static List<HiHealthData> bAT_(Cursor cursor) {
        return DBSessionCommon.parseSessionCursor(cursor);
    }

    public static List<HiHealthData> bAw_(Cursor cursor) {
        return DBSessionCommon.parseHealthSessionCursor(cursor);
    }

    public static List<HiHealthData> bAS_(Cursor cursor, String str) {
        return ihq.bym_(cursor, str);
    }

    public static List<String> bAR_(Cursor cursor) {
        return ihq.byl_(cursor);
    }

    public static List<HiHealthData> bAB_(Cursor cursor) {
        return ihq.byj_(cursor);
    }

    public static List<HiHealthData> bAQ_(Cursor cursor, boolean z) {
        return ihq.byk_(cursor, z);
    }

    public static List<HiHealthData> bAA_(Cursor cursor) {
        return ihq.byi_(cursor);
    }

    public static List<Integer> bAV_(Cursor cursor) {
        return ihq.byn_(cursor);
    }

    public static List<HiHealthData> bAP_(Cursor cursor, String str) {
        return ihe.bxM_(cursor, str);
    }

    public static Double bAG_(Cursor cursor, String str) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseOneDataValueCursor() Cursor query == null");
            return null;
        }
        try {
            return cursor.moveToNext() ? Double.valueOf(cursor.getDouble(cursor.getColumnIndex(str))) : null;
        } finally {
            cursor.close();
        }
    }

    public static List<HiHealthData> bAW_(Cursor cursor) {
        return iht.byA_(cursor);
    }

    public static List<igo> bAY_(Cursor cursor) {
        return iht.byC_(cursor);
    }

    public static igo bAL_(Cursor cursor) {
        return iht.byz_(cursor);
    }

    public static List<Integer> bAX_(Cursor cursor) {
        return iht.byB_(cursor);
    }

    public static HiAppInfo bAk_(Cursor cursor) {
        return igu.bxq_(cursor);
    }

    public static List<HiAppInfo> bAj_(Cursor cursor) {
        return igu.bxp_(cursor);
    }

    public static List<HiHealthUserPermission> bBd_(Cursor cursor) {
        LogUtil.a("Debug_ParseCursorUtil", "enter parseUserPermissionCursor", "Cursor =", cursor);
        return ihz.bze_(cursor);
    }

    public static HiDeviceInfo bAs_(Cursor cursor) {
        return igy.bxF_(cursor);
    }

    public static List<HiDeviceInfo> bAt_(Cursor cursor) {
        return igy.bxG_(cursor);
    }

    public static List<Integer> bAp_(Cursor cursor) {
        return ihc.bxB_(cursor);
    }

    public static List<ikv> bAq_(Cursor cursor) {
        return ihc.bxC_(cursor);
    }

    public static ikv bAo_(Cursor cursor) {
        return ihc.bxA_(cursor);
    }

    public static int bAI_(Cursor cursor, String str, int i) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseOneIntCursor is null");
            return i;
        }
        try {
            try {
                try {
                    if (cursor.moveToNext()) {
                        i = cursor.getInt(cursor.getColumnIndex(str));
                    }
                } catch (SQLiteException e) {
                    ReleaseLogUtil.c("HiH_ParseCursorUtil", "parseOneIntCursor sqLiteException message:" + e.getMessage());
                    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
                    linkedHashMap.put("errorCode", Integer.toString(-1));
                    ivz.d(BaseApplication.e()).c(OperationKey.HEALTH_APP_SQL_ERROR_2129011.value(), linkedHashMap, false);
                }
            } catch (SQLiteDatabaseCorruptException e2) {
                ReleaseLogUtil.c("HiH_ParseCursorUtil", "parseOneIntCursor SQLiteDatabaseCorruptException", LogAnonymous.b((Throwable) e2));
            }
            return i;
        } finally {
            cursor.close();
        }
    }

    public static int bAH_(Cursor cursor, String str) {
        return bAI_(cursor, str, 0);
    }

    public static String bAM_(Cursor cursor, String str) {
        String str2 = null;
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseOneStringCursor is null");
            return null;
        }
        try {
            try {
                if (cursor.moveToNext()) {
                    str2 = cursor.getString(cursor.getColumnIndex(str));
                }
            } catch (Exception e) {
                ReleaseLogUtil.c("HiH_ParseCursorUtil", "parseOneStringCursor ", e.getClass().getSimpleName());
            }
            return str2;
        } finally {
            cursor.close();
        }
    }

    public static long bAK_(Cursor cursor, String str) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseOneStringCursor is null");
            return 0L;
        }
        try {
            return cursor.moveToNext() ? cursor.getLong(cursor.getColumnIndex(str)) : 0L;
        } finally {
            cursor.close();
        }
    }

    public static List<Integer> bAJ_(Cursor cursor, String str) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseOneIntListCursor is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                arrayList.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(str))));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<Integer> bAr_(Cursor cursor, String str) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseDataSourceClientCursor is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                arrayList.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(str))));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<Integer> bAU_(Cursor cursor, String str) {
        if (cursor == null) {
            LogUtil.h("Debug_ParseCursorUtil", "parseSleepLastSevenDayTime cursor is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                arrayList.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(str))));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiUserInfo> bBc_(Cursor cursor) {
        return iia.byP_(cursor);
    }

    public static HiUserInfo bBb_(Cursor cursor) {
        return iia.byO_(cursor);
    }

    public static HiAccountInfo bzX_(Cursor cursor) {
        return igv.bxn_(cursor);
    }

    public static List<HiGoalInfo> bAu_(Cursor cursor) {
        return ihb.bxI_(cursor);
    }

    public static igq bAZ_(Cursor cursor) {
        return ihu.byE_(cursor);
    }

    public static List<igq> bAx_(Cursor cursor) {
        return ihu.byD_(cursor);
    }

    public static igq bBa_(Cursor cursor) {
        return ihu.byF_(cursor);
    }

    public static boolean bAv_(Cursor cursor) {
        boolean z = false;
        try {
            if (cursor == null) {
                return false;
            }
            try {
                z = cursor.moveToNext();
            } catch (SQLiteException e) {
                String message = e.getMessage();
                if (!TextUtils.isEmpty(message) && message.contains("file is not a database")) {
                    ReleaseLogUtil.c("HiH_ParseCursorUtil", "parseHasDataCursor need to clean clone data", message);
                    KeyManager.a(false);
                }
            }
            return z;
        } finally {
            cursor.close();
        }
    }

    public static List<HiUserPreference> bBf_(Cursor cursor) {
        return ihx.byU_(cursor);
    }

    public static HiUserPreference bBe_(Cursor cursor) {
        return ihx.byT_(cursor);
    }

    public static List<WearKitPermission> bBg_(Cursor cursor) {
        return iig.bzg_(cursor);
    }

    public static List<HiHealthData> bAm_(Cursor cursor, String str) {
        return iib.byY_(cursor, str);
    }

    public static List<HiHealthData> bAy_(Cursor cursor) {
        return iib.byZ_(cursor);
    }

    public static long bAl_(Cursor cursor) {
        return iib.byX_(cursor);
    }

    public static List<HiHealthData> bAn_(Cursor cursor) {
        return ihy.bzc_(cursor);
    }
}
