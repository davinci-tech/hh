package defpackage;

import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.plan.model.model.fitness.FitnessHistoryModel;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import health.compact.a.DbManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class ett extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ett f12299a;
    private static h c;
    private f b;
    private static Locale e = Locale.getDefault();
    private static volatile boolean d = false;

    private ett(Context context) {
        super(context);
        if (this.b == null) {
            LogUtil.a("Suggestion_DatabaseManager", "registerLocaleChangeBroadcastReceiver");
            y();
        }
        if (c == null) {
            c = new h();
        }
        int e2 = gic.e((Object) ash.b("DATABASE_VERSION_KEY"));
        LogUtil.a("Suggestion_DatabaseManager", "oldVersion = ", Integer.valueOf(e2), "  DATABASE_VERSION = ", 30);
        if (e2 != 0 && g()) {
            asc.e().b(new Runnable() { // from class: etv
                @Override // java.lang.Runnable
                public final void run() {
                    ett.this.p();
                }
            });
        } else if (e2 < 30) {
            w();
            d(e2);
        }
    }

    /* synthetic */ void p() {
        FitnessHistoryModel.getInstance().downloadFitnessRecordFromCloud(new ResultCallback() { // from class: etw
            @Override // com.huawei.health.suggestion.ResultCallback
            public final void onResult(int i2, Object obj) {
                ett.this.a(i2, obj);
            }
        });
    }

    /* synthetic */ void a(int i2, Object obj) {
        LogUtil.a("Suggestion_DatabaseManager", "downloadFitnessRecordFromCloud resultCode = ", Integer.valueOf(i2), " object ", "", obj);
        euc.e().a(new UiCallback<List<PlanRecord>>() { // from class: ett.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i3, String str) {
                LogUtil.a("Suggestion_DatabaseManager", "downloadFitnessRecordFromCloud, getPlanRecords errcode = ", Integer.valueOf(i3), " errorInfo ", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<PlanRecord> list) {
                LogUtil.a("Suggestion_DatabaseManager", "downloadFitnessRecordFromCloud getPlanRecords success.");
            }
        });
    }

    public boolean g() {
        if (u()) {
            return false;
        }
        ReleaseLogUtil.e("Suggestion_DatabaseManager", "reset database version");
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("DatabaseManager", "rebuildDatabase");
        w();
        d(0);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean u() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r0.<init>(r1)
            java.lang.String r1 = "workout_record"
            java.lang.String r1 = r5.getTableFullName(r1)
            r0.append(r1)
            java.lang.String r1 = " LIMIT 1"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            java.lang.String[] r2 = new java.lang.String[r1]
            r3 = 1
            android.database.Cursor r0 = r5.rawQueryStorageData(r3, r0, r2)     // Catch: android.database.sqlite.SQLiteException -> L3c
            if (r0 == 0) goto L35
            int r2 = r0.getColumnCount()     // Catch: java.lang.Throwable -> L29
            goto L36
        L29:
            r2 = move-exception
            if (r0 == 0) goto L34
            r0.close()     // Catch: java.lang.Throwable -> L30
            goto L34
        L30:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch: android.database.sqlite.SQLiteException -> L3c
        L34:
            throw r2     // Catch: android.database.sqlite.SQLiteException -> L3c
        L35:
            r2 = r1
        L36:
            if (r0 == 0) goto L48
            r0.close()     // Catch: android.database.sqlite.SQLiteException -> L3d
            goto L48
        L3c:
            r2 = r1
        L3d:
            java.lang.String r0 = "getColumnNum(), SQLiteException"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r4 = "Suggestion_DatabaseManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r0)
        L48:
            if (r2 == 0) goto L4b
            r1 = r3
        L4b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ett.u():boolean");
    }

    private void d(int i2) {
        e(i2, 30);
        ash.a("DATABASE_VERSION_KEY", String.valueOf(30));
    }

    public static ett i() {
        if (f12299a == null) {
            LogUtil.a("Suggestion_DatabaseManager", "start getInstance");
            synchronized (ett.class) {
                if (f12299a == null) {
                    f12299a = new ett(arx.b());
                }
            }
            LogUtil.a("Suggestion_DatabaseManager", "end getInstance");
        }
        return f12299a;
    }

    private void e(int i2, int i3) {
        LogUtil.a("Suggestion_DatabaseManager", "onUpgrade(): oldVersion:", Integer.valueOf(i2));
        int i4 = i2 + 1;
        if (i4 <= 8) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : ", Integer.valueOf(i4));
            e(i4);
            i4 = 9;
        }
        if (i4 == 9) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : ", Integer.valueOf(i4));
            b(i4);
            i4 = 10;
        }
        if (i4 <= 16) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : ", Integer.valueOf(i4));
            ah();
            i4 = 17;
        }
        if (i4 <= 22) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : ", Integer.valueOf(i4));
            ac();
            i4 = 23;
        }
        if (i4 == 23) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : ", Integer.valueOf(i4));
            ad();
            i4 = 24;
        }
        int c2 = c(i4);
        if (c2 == 26) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : is 26");
            etx.b().a();
            ai();
            c2 = 27;
        }
        if (c2 == 27) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : is 27");
            etx.b().a();
            v();
            c2 = 28;
        }
        if (c2 == 28) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : is 28");
            ab();
            c2 = 29;
        }
        if (c2 == 29) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : is 29");
            x();
        } else if (c2 != 30) {
            return;
        }
        LogUtil.a("Suggestion_DatabaseManager", ",tempOldVersion : is 30");
        v();
    }

    private int c(int i2) {
        if (i2 == 24) {
            LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : ", Integer.valueOf(i2));
            etx.b().a();
            v();
            aa();
            i2 = 25;
        }
        if (i2 != 25) {
            return i2;
        }
        LogUtil.a("Suggestion_DatabaseManager", "tempOldVersion : ", Integer.valueOf(i2));
        z();
        return 26;
    }

    private void ab() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName("workout_record");
        f();
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD sportRecordType INTEGER DEFAULT 0");
        j();
    }

    private void x() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName(eub.e());
        String tableFullName2 = getTableFullName("workout_record");
        f();
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD isAi INTEGER DEFAULT 0");
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName2 + " ADD totalScore INTEGER");
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName2 + " ADD recordModeType INTEGER DEFAULT 0");
        j();
        LogUtil.a("Suggestion_DatabaseManager", "updateAiFitWorkoutRecord end.");
    }

    private void v() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName(eub.c());
        List<String> a2 = DbManager.a(valueOf);
        if (!koq.b(a2)) {
            for (String str : a2) {
                if (str != null && str.contains(tableFullName)) {
                    DbManager.e(valueOf, "DROP TABLE IF EXISTS " + str);
                }
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put(eub.e(), eub.e);
        a(hashMap);
    }

    private void aa() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName("workout_record");
        f();
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD intensityZone TEXT");
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD trainPoint INTEGER");
        j();
    }

    private void z() {
        LogUtil.a("Suggestion_DatabaseManager", "updatePlanRecordTable start.");
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName("plan_records");
        f();
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD actualDuration REAL");
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD planCategory INTEGER");
        j();
        LogUtil.a("Suggestion_DatabaseManager", "updatePlanRecordTable end.");
    }

    private void e(int i2) {
        String valueOf = String.valueOf(getModuleId());
        DbManager.e(valueOf, "ALTER TABLE " + getTableFullName("workout_record") + " ADD wearType INTEGER DEFAULT 0");
        String str = "ALTER TABLE " + getTableFullName("workout_record") + " ADD extend TEXT";
        DbManager.e(valueOf, str);
        LogUtil.a("Suggestion_DatabaseManager", "onUpgrade(): initVersion:", Integer.valueOf(i2), " sql:", str);
    }

    private void b(int i2) {
        String valueOf = String.valueOf(getModuleId());
        String str = "ALTER TABLE " + getTableFullName("workout_record") + " ADD recordIndex INTEGER DEFAULT 0";
        DbManager.e(valueOf, str);
        LogUtil.a("Suggestion_DatabaseManager", "onUpgrade(): initVersion:", Integer.valueOf(i2), " sql:", str);
    }

    private void ai() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName(eub.c());
        List<String> a2 = DbManager.a(valueOf);
        if (koq.b(a2)) {
            return;
        }
        for (String str : a2) {
            if (str != null && str.contains(tableFullName)) {
                DbManager.e(valueOf, "ALTER TABLE " + str + " ADD commodityFlag INTEGER");
                DbManager.e(valueOf, "ALTER TABLE " + str + " ADD iconDisplay INTEGER");
                DbManager.e(valueOf, "ALTER TABLE " + str + " ADD iconMessages TEXT");
            }
        }
    }

    private void ah() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName("workout_record");
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD heartRateList TEXT");
        DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD invalidHeartRateList TEXT");
        LogUtil.a("Suggestion_DatabaseManager", "upgradeFitWorkoutAndRecordInfoTable upgrade() finish");
    }

    private void ac() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName("workout_record");
        List<String> a2 = DbManager.a(valueOf);
        if (koq.c(a2)) {
            for (String str : a2) {
                if (str != null && str.contains(tableFullName)) {
                    DbManager.e(valueOf, "ALTER TABLE " + tableFullName + " ADD category INTEGER");
                }
            }
        }
    }

    private void ad() {
        String valueOf = String.valueOf(getModuleId());
        String tableFullName = getTableFullName("workout_record");
        List<String> a2 = DbManager.a(valueOf);
        String[] strArr = {"'Y001'", "'Y002'", "'Y003'", "'Y004'", "'Y005'", "'Y006'", "'Y007'", "'Y008'", "'Y009'", "'Y010'", "'Y015'", "'Y016'", "'Y017'", "'Y018'"};
        if (koq.c(a2)) {
            for (String str : a2) {
                if (str != null && str.contains(tableFullName)) {
                    for (int i2 = 0; i2 < 14; i2++) {
                        DbManager.e(valueOf, "UPDATE " + tableFullName + " SET category = 137 WHERE workoutId = " + strArr[i2]);
                    }
                }
            }
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 101010;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        LogUtil.a("Suggestion_DatabaseManager", "initDb");
        Map<String, String> q = q();
        if (a(q)) {
            return;
        }
        LogUtil.h("Suggestion_DatabaseManager", "create all table failed.");
        if (deleteDatabase()) {
            a(q);
        } else {
            LogUtil.b("Suggestion_DatabaseManager", "data base error.");
        }
    }

    private boolean a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (createStorageDataTable(entry.getKey(), 1, entry.getValue()) != 0) {
                arrayList.add(entry.getKey());
            }
        }
        LogUtil.a("Suggestion_DatabaseManager", "create failed table list: ", Arrays.toString(arrayList.toArray()));
        return arrayList.isEmpty();
    }

    private Map<String, String> q() {
        HashMap hashMap = new HashMap(18);
        hashMap.put("plan_records", "planId TEXT,userId TEXT,planType INTEGER,difficulty INTEGER,workoutDays INTEGER,status INTEGER DEFAULT 0,planName TEXT,calorie REAL,distance REAL,actualCalorie REAL,actualDistance REAL,actualDuration REAL,finishRate REAL,goal INTEGER, INTEGER,weekCount INTEGER,workoutCount INTEGER,workoutTimes INTEGER,startDate TEXT,endDate TEXT,version TEXT,finishDate TEXT,excludedDates TEXT,weekTimes INTEGER,workoutRecords INTEGER,planCategory INTEGER,PRIMARY KEY(userId,planId)");
        hashMap.put("workout_record", euj.c);
        hashMap.put("plans", "recordId INTEGER PRIMARY KEY AUTOINCREMENT,planId TEXT,lan TEXT,version TEXT,content TEXT");
        hashMap.put("best_record", "recordId INTEGER PRIMARY KEY AUTOINCREMENT,planId TEXT,userId TEXT,type INTEGER,value TEXT,status INTEGER");
        hashMap.put("data_sync", "recordId INTEGER PRIMARY KEY AUTOINCREMENT,planId TEXT,userId TEXT,type INTEGER,value TEXT,syncTimes INTEGER");
        hashMap.put(eub.e(), eub.e);
        hashMap.put("fitness_total_record", "recordId INTEGER PRIMARY KEY AUTOINCREMENT,userId TEXT,recordDate INTEGER,recordTime INTEGER,recordCalorie REAL,recordDuration INTEGER,recordStatus INTEGER,recordTotalCalorie REAL,recordTotalDuration INTEGER,dayLastRecord INTEGER");
        hashMap.put("best_record_fit", "recordId INTEGER PRIMARY KEY AUTOINCREMENT,actionId TEXT,userId TEXT,type INTEGER,value TEXT,completeTime INTEGER,status INTEGER");
        hashMap.put("train_count", "recordId INTEGER PRIMARY KEY AUTOINCREMENT,userId TEXT,id TEXT,count INTEGER");
        return hashMap;
    }

    public void f() {
        DbManager.e(String.valueOf(getModuleId()));
    }

    public void j() {
        try {
            DbManager.c(String.valueOf(getModuleId()));
        } catch (IllegalStateException unused) {
            ReleaseLogUtil.c("Suggestion_DatabaseManager", "endTransaction IllegalStateException");
        }
    }

    public etb h() {
        return a.e;
    }

    public eui t() {
        return j.d;
    }

    public eue n() {
        return i.d;
    }

    public eto o() {
        return e.c;
    }

    public eub l() {
        return b.c;
    }

    public etu k() {
        return d.e;
    }

    public ete m() {
        return c.f12300a;
    }

    public euk s() {
        return g.f12301a;
    }

    static final class a {
        private static final etb e = new etb();
    }

    static final class j {
        private static final eui d = new eui();
    }

    static final class i {
        private static final eue d = new eue();
    }

    static final class e {
        private static final eto c = new eto();
    }

    static final class b {
        private static final eub c = new eub();
    }

    static final class d {
        private static final etu e = new etu();
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        private static final ete f12300a = new ete();
    }

    static final class g {

        /* renamed from: a, reason: collision with root package name */
        private static final euk f12301a = new euk();
    }

    private void y() {
        this.b = new f();
        arx.b().registerReceiver(this.b, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
    }

    static class f extends BroadcastReceiver {
        private f() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("Suggestion_DatabaseManager", "LocaleChangeBroadcastReceiver#onReceive()");
            if (intent == null) {
                ReleaseLogUtil.c("Suggestion_DatabaseManager", "LocaleChangeReceiver, intent is null");
                return;
            }
            if (!"android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                ReleaseLogUtil.c("Suggestion_DatabaseManager", "Receiver not ACTION_LOCALE_CHANGED");
                return;
            }
            Locale locale = BaseApplication.e().getResources().getConfiguration().locale;
            if (locale == null || locale.equals(ett.e)) {
                if (ett.c == null || ett.d) {
                    return;
                }
                ReleaseLogUtil.e("Suggestion_DatabaseManager", "registermLocaleChangeComponentCallbacks");
                BaseApplication.vZ_().registerComponentCallbacks(ett.c);
                boolean unused = ett.d = true;
                return;
            }
            ReleaseLogUtil.e("Suggestion_DatabaseManager", "LocaleChangeReceiver, Language change");
            Locale unused2 = ett.e = locale;
            ett.r();
        }
    }

    static class h implements ComponentCallbacks {
        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }

        private h() {
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
            LogUtil.a("Suggestion_DatabaseManager", "sCurrentLocale=", ett.e, ",newLocale=", configuration.locale);
            if (configuration.locale != null && !configuration.locale.equals(ett.e)) {
                Locale unused = ett.e = configuration.locale;
                ett.r();
            }
            BaseApplication.vZ_().unregisterComponentCallbacks(this);
            boolean unused2 = ett.d = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void r() {
        ThreadPoolManager.d().c("Suggestion_DatabaseManager", new Runnable() { // from class: ett.5
            @Override // java.lang.Runnable
            public void run() {
                squ.r(squ.a());
                ReleaseLogUtil.e("Suggestion_DatabaseManager", "initDatabase thread executed");
                if (ett.f12299a != null) {
                    ReleaseLogUtil.e("Suggestion_DatabaseManager", "initDatabase start");
                    ett.f12299a.w();
                }
            }
        });
    }
}
