package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.operation.OperationKey;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes7.dex */
public class ivu {
    private static final iwa c = new iwa();

    private static boolean c(int i) {
        if (i == 2103 || i == 2104 || i == 2106 || i == 2107 || i == 10001 || i == 47299) {
            return true;
        }
        switch (i) {
            case 2008:
            case 2009:
            case 2010:
            case 2011:
            case 2012:
            case 2013:
            case 2014:
            case 2015:
                return true;
            default:
                switch (i) {
                    case 47200:
                    case 47201:
                    case 47202:
                    case 47203:
                    case 47204:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public static ijj d(Context context, int i) {
        if (g(context, i)) {
            return iji.b();
        }
        return ijj.a(context);
    }

    public static ijt b(Context context, int i) {
        if (g(context, i)) {
            return ijs.d();
        }
        return ijt.b();
    }

    public static ijd a(Context context, int i) {
        if (g(context, i)) {
            return ijb.b();
        }
        return ijd.c(context);
    }

    public static boolean e(Context context, int i) {
        SQLiteDatabase writableDatabase;
        boolean g = g(context, i);
        if (g) {
            writableDatabase = HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase();
        } else {
            writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
        }
        if (writableDatabase == null) {
            ReleaseLogUtil.d("HiH_HiDataBaseManagerChooseUtil", "begin dataBase is empty,isSensitive:", Boolean.valueOf(g));
            return false;
        }
        try {
            c.lock();
            if (!iuz.f()) {
                ReleaseLogUtil.b("HiH_HiDataBaseManagerChooseUtil", "begin,isSensitive:", Boolean.valueOf(g));
            }
            writableDatabase.beginTransactionNonExclusive();
            return true;
        } catch (Throwable th) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
            linkedHashMap.put("beginTransactionNonLockException", LogAnonymous.b(th));
            ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SQL_CIPHER_TIME_OUT_2129019.value(), linkedHashMap, true);
            iwa iwaVar = c;
            if (iwaVar.isLocked()) {
                iwaVar.unlock();
            }
            ReleaseLogUtil.c("HiH_HiDataBaseManagerChooseUtil", "beginTransactionNonLock e ", LogAnonymous.b(th));
            return false;
        }
    }

    public static boolean i(Context context, int i) {
        SQLiteDatabase writableDatabase;
        if (g(context, i)) {
            writableDatabase = HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase();
        } else {
            writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
        }
        if (writableDatabase != null) {
            return writableDatabase.inTransaction();
        }
        return false;
    }

    public static void j(Context context, int i) {
        SQLiteDatabase writableDatabase;
        boolean g = g(context, i);
        if (g) {
            writableDatabase = HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase();
        } else {
            writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
        }
        if (writableDatabase != null) {
            if (writableDatabase.inTransaction()) {
                writableDatabase.setTransactionSuccessful();
                return;
            }
            c("setTran not inTransaction,isSensitive:" + g);
        }
    }

    public static void c(Context context, int i) {
        iwa iwaVar;
        StringBuilder sb;
        SQLiteDatabase writableDatabase;
        boolean g = g(context, i);
        try {
            if (g) {
                writableDatabase = HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase();
            } else {
                writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
            }
            if (writableDatabase != null) {
                if (!iuz.f()) {
                    ReleaseLogUtil.b("HiH_HiDataBaseManagerChooseUtil", "end,isSensitive:", Boolean.valueOf(g));
                }
                if (writableDatabase.inTransaction()) {
                    writableDatabase.endTransaction();
                } else {
                    c("end database not inTransaction,isSensitive:" + g);
                }
            }
            iwaVar = c;
        } catch (Throwable th) {
            try {
                ReleaseLogUtil.c("HiH_HiDataBaseManagerChooseUtil", "endTransactionNonLock e ", LogAnonymous.b(th));
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put("endTransactionNonLockException", LogAnonymous.b(th));
                long b = SharedPreferenceManager.b("HiHealthService", "dbMalformed", 0L);
                long currentTimeMillis = System.currentTimeMillis();
                if (th.getMessage() == null || !(th.getMessage().contains("database or disk is full") || th.getMessage().contains("disk image is malformed"))) {
                    ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SQL_CIPHER_TIME_OUT_2129019.value(), linkedHashMap, true);
                } else if (currentTimeMillis - b >= 604800000) {
                    ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SQL_CIPHER_TIME_OUT_2129019.value(), linkedHashMap, true);
                    SharedPreferenceManager.e("HiHealthService", "dbMalformed", currentTimeMillis);
                }
                iwaVar = c;
                if (iwaVar.isLocked()) {
                    ReleaseLogUtil.b("HiH_HiDataBaseManagerChooseUtil", "end,isSensitive:", Boolean.valueOf(g), " unlock");
                } else {
                    sb = new StringBuilder("end LOCK is unlocked,isSensitive:");
                }
            } catch (Throwable th2) {
                iwa iwaVar2 = c;
                if (iwaVar2.isLocked()) {
                    ReleaseLogUtil.b("HiH_HiDataBaseManagerChooseUtil", "end,isSensitive:", Boolean.valueOf(g), " unlock");
                    iwaVar2.unlock();
                } else {
                    c("end LOCK is unlocked,isSensitive:" + g);
                }
                throw th2;
            }
        }
        if (iwaVar.isLocked()) {
            ReleaseLogUtil.b("HiH_HiDataBaseManagerChooseUtil", "end,isSensitive:", Boolean.valueOf(g), " unlock");
            iwaVar.unlock();
        } else {
            sb = new StringBuilder("end LOCK is unlocked,isSensitive:");
            sb.append(g);
            c(sb.toString());
        }
    }

    private static void c(String str) {
        ReleaseLogUtil.d("HiH_HiDataBaseManagerChooseUtil", str);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("endTransactionNonLock", str);
        ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SQL_CIPHER_TIME_OUT_2129019.value(), linkedHashMap, true);
    }

    public static int[] e(Context context, int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return new int[0];
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            if (g(context, i)) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public static int[] c(Context context, int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return new int[0];
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            if (!g(context, i)) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public static List<igo> a(Context context, List<igo> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (igo igoVar : list) {
            HiHealthDictType h = HiHealthDictManager.d(context).h(igoVar.f());
            if (h != null) {
                if (h.j() >= 3) {
                    arrayList.add(igoVar);
                }
            } else if (c(igoVar.f())) {
                arrayList.add(igoVar);
            }
        }
        return arrayList;
    }

    public static List<igo> c(Context context, List<igo> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (igo igoVar : list) {
            HiHealthDictType h = HiHealthDictManager.d(context).h(igoVar.f());
            if (h != null) {
                if (h.j() < 3) {
                    arrayList.add(igoVar);
                }
            } else if (!c(igoVar.f())) {
                arrayList.add(igoVar);
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> e(Context context, List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (g(context, hiHealthData.getType())) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> d(Context context, List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (!c(hiHealthData.getType()) && !f(context, hiHealthData.getType())) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    public static boolean g(Context context, int i) {
        return c(i) || f(context, i);
    }

    private static boolean f(Context context, int i) {
        HiHealthDictType f;
        int c2 = HiHealthDictManager.d(context).c(i);
        if (c2 == 0 || c2 == 1) {
            f = HiHealthDictManager.d(context).f(i);
        } else if (c2 == 2) {
            f = HiHealthDictManager.d(context).h(i);
        } else {
            f = HiHealthDictManager.d(context).d(i);
        }
        return f != null && f.j() >= 3;
    }

    public static String c() {
        Thread owner = c.getOwner();
        return owner != null ? c(owner) : "";
    }

    private static String c(Thread thread) {
        return "deadlock thread name: " + thread.getName() + ", state: " + thread.getState() + ", id: " + thread.getId() + ": " + System.lineSeparator() + DfxUtils.d(thread, null);
    }
}
