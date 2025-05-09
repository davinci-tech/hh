package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.DbManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes3.dex */
public class ckc extends HwBaseManager {
    private static volatile ckc c = null;
    private static volatile boolean d = false;
    private static final String[] e = {IndoorEquipManagerApi.KEY_HEART_RATE, "resistanceLeftArmRightArm", "resistanceLeftArmLeftLeg", "resistanceLeftArmRightLeg", "resistanceRightArmLeftLeg", "resistanceRightArmRightLeg", "resistanceLeftLegRightLeg", "resistanceFreq", "resistanceLeftArmRightArmHf", "resistanceLeftArmLeftLegHf", "resistanceLeftArmRightLegHf", "resistanceRightArmLeftLegHf", "resistanceRightArmRightLegHf", "resistanceLeftLegRightLegHf"};

    /* renamed from: a, reason: collision with root package name */
    private static final String f761a = "WeightDataDBManager";

    private ckc(Context context) {
        super(context);
        b();
    }

    public static ckc a(Context context) {
        LogUtil.a("WeightDataDBManager", "WeightDataDBManager getInstance!");
        if (c == null) {
            LogUtil.h("WeightDataDBManager", "WeightDataDBManager getInstance mDBProvider is null!");
            synchronized (ckc.class) {
                if (c == null) {
                    c = new ckc(context);
                }
            }
        }
        return c;
    }

    private void b() {
        if (a()) {
            LogUtil.c("WeightDataDBManager", "WeightDataDBManager data base has been initialized");
        } else {
            LogUtil.a("WeightDataDBManager", "init dataBase");
            d(new Runnable() { // from class: ckc.2
                @Override // java.lang.Runnable
                public void run() {
                    ckc.this.e();
                }
            });
        }
    }

    public void a(final cgo cgoVar) {
        d(new Runnable() { // from class: ckc.5
            @Override // java.lang.Runnable
            public void run() {
                ckc.this.b(cgoVar);
            }
        });
    }

    public void a(final ckm ckmVar, final IBaseResponseCallback iBaseResponseCallback) {
        d(new Runnable() { // from class: ckc.4
            @Override // java.lang.Runnable
            public void run() {
                ckc.this.d(ckmVar);
                iBaseResponseCallback.d(0, null);
            }
        });
    }

    public void d(String str, long j) {
        final String[] strArr = {str, j + ""};
        d(new Runnable() { // from class: ckc.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("WeightDataDBManager", "WeightDataDBManager weight offline data delete resultCode ", Integer.valueOf(ckc.this.deleteStorageData("hihealth_weightData", 1, "uid=? and measure_time=?", strArr)));
            }
        });
    }

    public ArrayList<ckm> b(String str) {
        LogUtil.a("WeightDataDBManager", "WeightDataDBManager start query weightData uid==" + str);
        ArrayList<ckm> arrayList = new ArrayList<>(16);
        if (str == null) {
            LogUtil.b("WeightDataDBManager", "WeightDataDBManager query data uid is null");
            return arrayList;
        }
        String str2 = "select * from " + getTableFullName("hihealth_weightData") + " where uid = ?";
        String[] strArr = {str};
        if (a() && e(e)) {
            Cursor rawQueryStorageData = rawQueryStorageData(1, str2, strArr);
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    arrayList.add(Gw_(rawQueryStorageData));
                }
                rawQueryStorageData.close();
                LogUtil.a("WeightDataDBManager", "WeightDataDBManager query weightData done; resultBeansList.size==" + arrayList.size());
            }
        } else {
            LogUtil.b("WeightDataDBManager", "WeightDataDBManager query weightData table not exist");
        }
        return arrayList;
    }

    private ckm Gw_(Cursor cursor) {
        ckm ckmVar = new ckm();
        ckmVar.setBodyFatRat(cursor.getFloat(cursor.getColumnIndex("body_fat")));
        ckmVar.setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
        ckmVar.g(cursor.getDouble(cursor.getColumnIndex(IndoorEquipManagerApi.KEY_HEART_RATE)));
        ckmVar.setStartTime(cursor.getLong(cursor.getColumnIndex("measure_time")));
        ckmVar.setEndTime(cursor.getLong(cursor.getColumnIndex("measure_time")));
        ckmVar.f(cursor.getDouble(cursor.getColumnIndex("resistance")));
        ckmVar.b(1, cursor.getDouble(cursor.getColumnIndex("resistanceLeftArmRightArm")));
        ckmVar.b(2, cursor.getDouble(cursor.getColumnIndex("resistanceLeftArmLeftLeg")));
        ckmVar.b(3, cursor.getDouble(cursor.getColumnIndex("resistanceLeftArmRightLeg")));
        ckmVar.b(4, cursor.getDouble(cursor.getColumnIndex("resistanceRightArmLeftLeg")));
        ckmVar.b(5, cursor.getDouble(cursor.getColumnIndex("resistanceRightArmRightLeg")));
        ckmVar.b(0, cursor.getDouble(cursor.getColumnIndex("resistanceLeftLegRightLeg")));
        ckmVar.d(cursor.getInt(cursor.getColumnIndex("resistanceFreq")));
        ckmVar.c(1, cursor.getDouble(cursor.getColumnIndex("resistanceLeftArmRightArmHf")));
        ckmVar.c(2, cursor.getDouble(cursor.getColumnIndex("resistanceLeftArmLeftLegHf")));
        ckmVar.c(3, cursor.getDouble(cursor.getColumnIndex("resistanceLeftArmRightLegHf")));
        ckmVar.c(4, cursor.getDouble(cursor.getColumnIndex("resistanceRightArmLeftLegHf")));
        ckmVar.c(5, cursor.getDouble(cursor.getColumnIndex("resistanceRightArmRightLegHf")));
        ckmVar.c(0, cursor.getDouble(cursor.getColumnIndex("resistanceLeftLegRightLegHf")));
        if (cursor.getInt(cursor.getColumnIndex("isSuspectedData")) == 1) {
            ckmVar.b(true);
        } else {
            ckmVar.b(false);
        }
        return ckmVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (a()) {
            return;
        }
        LogUtil.a("WeightDataDBManager", "createTable | create new table: ", getTableFullName("hihealth_weightData"));
        StringBuilder sb = new StringBuilder(16);
        e(sb, "uid");
        e(sb, "weight");
        b(sb, "body_fat");
        b(sb, IndoorEquipManagerApi.KEY_HEART_RATE);
        a(sb, "measure_time");
        a(sb, "resistance");
        b(sb, "resistanceLeftArmRightArm");
        b(sb, "resistanceLeftArmLeftLeg");
        b(sb, "resistanceLeftArmRightLeg");
        b(sb, "resistanceRightArmLeftLeg");
        b(sb, "resistanceRightArmRightLeg");
        b(sb, "resistanceLeftLegRightLeg");
        b(sb, "resistanceFreq");
        b(sb, "resistanceLeftArmRightArmHf");
        b(sb, "resistanceLeftArmLeftLegHf");
        b(sb, "resistanceLeftArmRightLegHf");
        b(sb, "resistanceRightArmLeftLegHf");
        b(sb, "resistanceRightArmRightLegHf");
        b(sb, "resistanceLeftLegRightLegHf");
        a(sb, "isSuspectedData");
        e(sb, "productId");
        e(sb, "device_uuid");
        e(sb, "device_id");
        a(sb, "_status");
        a(sb, "_type");
        e(sb, "_remarks");
        sb.append("primary key(");
        sb.append("uid");
        sb.append(",");
        sb.append("measure_time");
        sb.append(Constants.RIGHT_BRACKET_ONLY);
        LogUtil.c("WeightDataDBManager", "createTable | create new table sql = ", sb.toString());
        if (createStorageDataTable("hihealth_weightData", 1, sb.toString()) != 0) {
            LogUtil.h("WeightDataDBManager", "database is bad.");
            if (!deleteDatabase()) {
                return;
            } else {
                createStorageDataTable("hihealth_weightData", 1, sb.toString());
            }
        }
        if (a() && !e(e)) {
            c();
        }
        LogUtil.a("WeightDataDBManager", "createTable | create table end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(cgo cgoVar) {
        if (cgoVar == null) {
            LogUtil.c("WeightDataDBManager", "WeightDataDBManager inster resultBean is null");
            return;
        }
        LogUtil.a("WeightDataDBManager", "WeightDataDBManager start inster weightData into weightDB...");
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", cgoVar.j());
        contentValues.put("body_fat", Float.valueOf(cgoVar.c()));
        contentValues.put("weight", Float.valueOf(cgoVar.i()));
        contentValues.put("measure_time", Long.valueOf(d(cgoVar)));
        contentValues.put("resistance", Integer.valueOf(cgoVar.g()));
        contentValues.put("isSuspectedData", Boolean.valueOf(cgoVar.n()));
        LogUtil.a("WeightDataDBManager", "WeightDataDBManager inster weightData result ", Long.valueOf(insertStorageData("hihealth_weightData", 1, contentValues)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ckm ckmVar) {
        if (ckmVar == null) {
            LogUtil.h("WeightDataDBManager", "WeightDataDBManager insert() resultBean is null");
            return;
        }
        if (!e(e)) {
            LogUtil.h("WeightDataDBManager", "WeightDataDBManager hygride new columns not exist");
            return;
        }
        LogUtil.a("WeightDataDBManager", "WeightDataDBManager start insert hagrid data : ", Long.valueOf(ckmVar.getStartTime()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", ckmVar.n());
        contentValues.put("body_fat", Float.valueOf(ckmVar.getBodyFatRat()));
        contentValues.put("weight", Float.valueOf(ckmVar.getWeight()));
        contentValues.put("measure_time", Long.valueOf(ckmVar.getStartTime()));
        contentValues.put("resistanceLeftArmRightArm", Double.valueOf(ckmVar.c(1)));
        contentValues.put("resistanceLeftArmLeftLeg", Double.valueOf(ckmVar.c(2)));
        contentValues.put("resistanceLeftArmRightLeg", Double.valueOf(ckmVar.c(3)));
        contentValues.put("resistanceRightArmLeftLeg", Double.valueOf(ckmVar.c(4)));
        contentValues.put("resistanceRightArmRightLeg", Double.valueOf(ckmVar.c(5)));
        contentValues.put("resistanceLeftLegRightLeg", Double.valueOf(ckmVar.c(0)));
        contentValues.put("resistanceFreq", Integer.valueOf(ckmVar.g()));
        contentValues.put("resistanceLeftArmRightArmHf", Double.valueOf(ckmVar.b(1)));
        contentValues.put("resistanceLeftArmLeftLegHf", Double.valueOf(ckmVar.b(2)));
        contentValues.put("resistanceLeftArmRightLegHf", Double.valueOf(ckmVar.b(3)));
        contentValues.put("resistanceRightArmLeftLegHf", Double.valueOf(ckmVar.b(4)));
        contentValues.put("resistanceRightArmRightLegHf", Double.valueOf(ckmVar.b(5)));
        contentValues.put("resistanceLeftLegRightLegHf", Double.valueOf(ckmVar.b(0)));
        contentValues.put("isSuspectedData", Boolean.valueOf(ckmVar.q()));
        contentValues.put(IndoorEquipManagerApi.KEY_HEART_RATE, Double.valueOf(ckmVar.f()));
        LogUtil.a("WeightDataDBManager", "WeightDataDBManager insert weightData result = ", Long.valueOf(insertStorageData("hihealth_weightData", 1, contentValues)));
    }

    private static void a(StringBuilder sb, String str) {
        sb.append(str);
        sb.append(" integer,");
    }

    private static void e(StringBuilder sb, String str) {
        sb.append(str);
        sb.append(" text,");
    }

    private static void b(StringBuilder sb, String str) {
        sb.append(str);
        sb.append(" real,");
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return Integer.valueOf(PrebakedEffectId.RT_HEARTBEAT);
    }

    private long d(cgo cgoVar) {
        try {
            Date parse = new SimpleDateFormat("yyyy-M-d HH:mm:ss").parse(cgoVar.h() + com.huawei.openalliance.ad.constant.Constants.LINK + cgoVar.b() + com.huawei.openalliance.ad.constant.Constants.LINK + cgoVar.a() + " " + cgoVar.d() + ":" + cgoVar.e() + ":" + cgoVar.f());
            if (parse != null) {
                return parse.getTime();
            }
            return 0L;
        } catch (ParseException unused) {
            LogUtil.b("WeightDataDBManager", "dateToTimeStamp() ParseException");
            return 0L;
        }
    }

    private boolean e(String[] strArr) {
        Cursor queryStorageData = queryStorageData("hihealth_weightData", 1, null);
        int length = strArr.length;
        int i = 0;
        boolean z = false;
        while (true) {
            if (i >= length) {
                break;
            }
            z = (queryStorageData == null || queryStorageData.getColumnIndex(strArr[i]) == -1) ? false : true;
            if (!z) {
                LogUtil.h("WeightDataDBManager", "WeightDataDBManager hygride columns not exist");
                break;
            }
            i++;
        }
        if (queryStorageData != null) {
            queryStorageData.close();
        }
        LogUtil.h("WeightDataDBManager", "WeightDataDBManager hygride new columns exist");
        return z;
    }

    private void c() {
        LogUtil.a("WeightDataDBManager", "WeightDataDBManager begin to add new columns");
        for (String str : e) {
            LogUtil.a("WeightDataDBManager", "WeightDataDBManager add column ", str);
            alterStorageDataTable("hihealth_weightData", 1, "ADD COLUMN " + str + " real default null");
        }
    }

    private void d(Runnable runnable) {
        if (runnable == null) {
            LogUtil.h("WeightDataDBManager", "serialExcute command is null");
        } else {
            ThreadPoolManager.d().c(f761a, runnable);
        }
    }

    private boolean a() {
        if (d) {
            return true;
        }
        List<String> a2 = DbManager.a(String.valueOf(getModuleId()));
        if (a2 == null || !a2.contains(getTableFullName("hihealth_weightData"))) {
            return false;
        }
        d = true;
        return true;
    }
}
