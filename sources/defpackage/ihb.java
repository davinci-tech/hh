package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ihb extends DBCommon {
    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int delete(String str, String[] strArr) {
        return super.delete(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ void execSQL(String str, Object[] objArr) {
        super.execSQL(str, objArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ long insert(ContentValues contentValues) {
        return super.insert(contentValues);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor query(String str, String[] strArr, String str2, String str3, String str4) {
        return super.query(str, strArr, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor queryEX(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return super.queryEX(strArr, str, strArr2, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor rawQuery(String str, String[] strArr) {
        return super.rawQuery(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int update(ContentValues contentValues, String str, String[] strArr) {
        return super.update(contentValues, str, strArr);
    }

    private ihb() {
    }

    public static ihb c() {
        return e.b;
    }

    /* loaded from: classes7.dex */
    static class e {
        private static final ihb b = new ihb();
    }

    public static String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS goal_value(_id integer primary key not null,goal_type integer,goal_value double,goal_unit integer,user_id integer,target_type integer,deadline integer,sync_status integer not null,modified_time integer)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "goal_type", "goal_value", "goal_unit", "user_id", "target_type", "deadline", "sync_status", "modified_time"};
    }

    public static ContentValues bxH_(HiGoalInfo hiGoalInfo, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("goal_type", Integer.valueOf(hiGoalInfo.getGoalType()));
        contentValues.put("goal_value", Double.valueOf(hiGoalInfo.getGoalValue()));
        contentValues.put("goal_unit", Integer.valueOf(hiGoalInfo.getGoalUnit()));
        contentValues.put("user_id", Integer.valueOf(hiGoalInfo.getOwnerId()));
        contentValues.put("target_type", Integer.valueOf(hiGoalInfo.getTargetType()));
        contentValues.put("deadline", Integer.valueOf(hiGoalInfo.getDealLine()));
        contentValues.put("sync_status", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static List<HiGoalInfo> bxI_(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBGoalInfo", "parseGoalInfoCursor query is null!");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"goal_type", "goal_value", "goal_unit", "user_id", "target_type", "deadline"};
                    int[] iArr2 = new int[6];
                    for (int i = 0; i < 6; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr = iArr2;
                }
                HiGoalInfo hiGoalInfo = new HiGoalInfo();
                hiGoalInfo.setGoalType(cursor.getInt(iArr[0]));
                hiGoalInfo.setGoalValue(cursor.getDouble(iArr[1]));
                hiGoalInfo.setGoalUnit(cursor.getInt(iArr[2]));
                hiGoalInfo.setOwnerId(cursor.getInt(iArr[3]));
                hiGoalInfo.setTargetType(cursor.getInt(iArr[4]));
                hiGoalInfo.setDealLine(cursor.getInt(iArr[5]));
                arrayList.add(hiGoalInfo);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "goal_value";
    }
}
