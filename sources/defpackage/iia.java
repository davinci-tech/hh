package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class iia extends DBCommon {
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

    private iia() {
    }

    static class c {
        private static final iia d = new iia();
    }

    public static iia b() {
        return c.d;
    }

    public static String e() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS hihealth_user(_id integer primary key not null,huid text not null,nick_name text,head_url text,relate_type integer not null,height double,weight double,email text,mobile text,unit_category integer,sex integer,birthday integer,age integer,sync_status integer not null,create_time integer not null)");
        return sb.toString();
    }

    public static String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX UserInfoIndex ON hihealth_user(_id,huid)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "huid", "nick_name", "head_url", "relate_type", "height", "weight", "email", JsNetwork.NetworkType.MOBILE, "unit_category", "sex", "birthday", "age", "sync_status", "create_time"};
    }

    public static ContentValues byH_(HiUserInfo hiUserInfo, int i) {
        return byI_(hiUserInfo, i, true);
    }

    public static ContentValues byG_(HiUserInfo hiUserInfo) {
        return byI_(hiUserInfo, 0, false);
    }

    private static ContentValues byI_(HiUserInfo hiUserInfo, int i, boolean z) {
        ContentValues contentValues = new ContentValues();
        int modifiedIntent = hiUserInfo.getModifiedIntent();
        LogUtil.c("Debug_DBUserInfo", "begin intent:", Integer.valueOf(modifiedIntent));
        contentValues.put("huid", hiUserInfo.getHuid());
        contentValues.put("relate_type", Integer.valueOf(hiUserInfo.getRelateType()));
        if (z) {
            contentValues.put("sync_status", Integer.valueOf(i));
        }
        if (modifiedIntent == 0) {
            LogUtil.h("Debug_DBUserInfo", "DATA_ALL not permitted");
            byJ_(hiUserInfo, contentValues);
            return contentValues;
        }
        contentValues.put("email", hiUserInfo.getEmail());
        contentValues.put(JsNetwork.NetworkType.MOBILE, hiUserInfo.getMobile());
        if (268435456 == modifiedIntent) {
            LogUtil.c("Debug_DBUserInfo", "UP_DATA_ONLY birth:", Integer.valueOf(hiUserInfo.getBirthday()), " gender", Integer.valueOf(hiUserInfo.getGender()));
            byN_(hiUserInfo, contentValues);
        } else {
            LogUtil.c("Debug_DBUserInfo", "no use birth:", Integer.valueOf(hiUserInfo.getBirthday()), " gender", Integer.valueOf(hiUserInfo.getGender()));
        }
        if (536870912 == modifiedIntent) {
            LogUtil.c("Debug_DBUserInfo", "DP_DATA_ONLY height:", Integer.valueOf(hiUserInfo.getHeight()), " weight:", Float.valueOf(hiUserInfo.getWeight()), " unit", Integer.valueOf(hiUserInfo.getUnitType()), " create time:", Long.valueOf(hiUserInfo.getCreateTime()), "birth:", Integer.valueOf(hiUserInfo.getBirthday()), " gender", Integer.valueOf(hiUserInfo.getGender()));
            byL_(hiUserInfo, contentValues);
        } else {
            LogUtil.c("Debug_DBUserInfo", "no use height:", Integer.valueOf(hiUserInfo.getHeight()), " weight:", Float.valueOf(hiUserInfo.getWeight()), " unit", Integer.valueOf(hiUserInfo.getUnitType()), " create time:", Long.valueOf(hiUserInfo.getCreateTime()));
        }
        if (805306368 == modifiedIntent) {
            byK_(hiUserInfo, contentValues);
        } else {
            LogUtil.c("Debug_DBUserInfo", "no use height:", Integer.valueOf(hiUserInfo.getHeight()), " weight:", Float.valueOf(hiUserInfo.getWeight()), " unit", Integer.valueOf(hiUserInfo.getUnitType()), " create time:", Long.valueOf(hiUserInfo.getCreateTime()));
        }
        LogUtil.c("Debug_DBUserInfo", "end");
        return contentValues;
    }

    private static void byK_(HiUserInfo hiUserInfo, ContentValues contentValues) {
        LogUtil.c("Debug_DBUserInfo", "DATA_CLOUD height:", Integer.valueOf(hiUserInfo.getHeight()), " weight:", Float.valueOf(hiUserInfo.getWeight()), " unit", Integer.valueOf(hiUserInfo.getUnitType()), " create time:", Long.valueOf(hiUserInfo.getCreateTime()));
        if (hiUserInfo.isHeightValid()) {
            contentValues.put("height", Integer.valueOf(hiUserInfo.getHeight()));
        }
        if (hiUserInfo.isWeightValid()) {
            contentValues.put("weight", Double.valueOf(String.valueOf(hiUserInfo.getWeight())));
        }
        contentValues.put("unit_category", Integer.valueOf(hiUserInfo.getUnitType()));
        contentValues.put("create_time", Long.valueOf(hiUserInfo.getCreateTime()));
    }

    private static void byL_(HiUserInfo hiUserInfo, ContentValues contentValues) {
        if (hiUserInfo.isGenderValid()) {
            contentValues.put("sex", Integer.valueOf(hiUserInfo.getGender()));
        }
        if (hiUserInfo.isBirthdayValid()) {
            contentValues.put("birthday", Integer.valueOf(hiUserInfo.getBirthday()));
        }
        if (hiUserInfo.getAge() > 0) {
            contentValues.put("age", Integer.valueOf(hiUserInfo.getAge()));
        }
        if (hiUserInfo.isHeightValid()) {
            contentValues.put("height", Integer.valueOf(hiUserInfo.getHeight()));
        }
        if (hiUserInfo.isWeightValid()) {
            contentValues.put("weight", Double.valueOf(String.valueOf(hiUserInfo.getWeight())));
        }
        contentValues.put("unit_category", Integer.valueOf(hiUserInfo.getUnitType()));
        contentValues.put("create_time", Long.valueOf(hiUserInfo.getCreateTime()));
    }

    private static void byN_(HiUserInfo hiUserInfo, ContentValues contentValues) {
        if (!TextUtils.isEmpty(hiUserInfo.getName())) {
            contentValues.put("nick_name", hiUserInfo.getName());
        }
        contentValues.put("head_url", hiUserInfo.getHeadImgUrl());
        if (hiUserInfo.isGenderValid()) {
            contentValues.put("sex", Integer.valueOf(hiUserInfo.getGender()));
        }
        if (hiUserInfo.isBirthdayValid()) {
            contentValues.put("birthday", Integer.valueOf(hiUserInfo.getBirthday()));
        }
        if (hiUserInfo.getAge() > 0) {
            contentValues.put("age", Integer.valueOf(hiUserInfo.getAge()));
        }
    }

    private static void byJ_(HiUserInfo hiUserInfo, ContentValues contentValues) {
        if (hiUserInfo.getCreateTime() == 1) {
            contentValues.put("create_time", (Integer) 1);
            contentValues.put("email", hiUserInfo.getEmail());
            contentValues.put(JsNetwork.NetworkType.MOBILE, hiUserInfo.getMobile());
            if (!TextUtils.isEmpty(hiUserInfo.getName())) {
                contentValues.put("nick_name", hiUserInfo.getName());
            }
            contentValues.put("head_url", hiUserInfo.getHeadImgUrl());
            if (hiUserInfo.isGenderValid()) {
                contentValues.put("sex", Integer.valueOf(hiUserInfo.getGender()));
            }
            if (hiUserInfo.isBirthdayValid()) {
                contentValues.put("birthday", Integer.valueOf(hiUserInfo.getBirthday()));
            }
            if (hiUserInfo.getAge() > 0) {
                contentValues.put("age", Integer.valueOf(hiUserInfo.getAge()));
            }
            if (hiUserInfo.isHeightValid()) {
                contentValues.put("height", Integer.valueOf(hiUserInfo.getHeight()));
            }
            if (hiUserInfo.isWeightValid()) {
                contentValues.put("weight", Double.valueOf(String.valueOf(hiUserInfo.getWeight())));
            }
            contentValues.put("unit_category", Integer.valueOf(hiUserInfo.getUnitType()));
            contentValues.put("sync_status", (Integer) 1);
        }
    }

    public static HiUserInfo byO_(Cursor cursor) {
        HiUserInfo hiUserInfo = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBUserInfo", "parseUserInfoCursor() cursor is null ");
            return null;
        }
        try {
            if (cursor.moveToNext()) {
                hiUserInfo = byM_(cursor);
                hiUserInfo.setOwnerId(cursor.getInt(cursor.getColumnIndex("_id")));
            }
            cursor.close();
            LogUtil.c("Debug_DBUserInfo", "parseUserInfoCursor() userInfo  = ", hiUserInfo);
            return hiUserInfo;
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }

    public static List<HiUserInfo> byP_(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("Debug_DBUserInfo", "parseUserInfoListCursor() cursor is null ");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                HiUserInfo byM_ = byM_(cursor);
                byM_.setOwnerId(cursor.getInt(cursor.getColumnIndex("_id")));
                arrayList.add(byM_);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private static HiUserInfo byM_(Cursor cursor) {
        HiUserInfo hiUserInfo = new HiUserInfo();
        hiUserInfo.setHuid(cursor.getString(cursor.getColumnIndex("huid")));
        hiUserInfo.setName(cursor.getString(cursor.getColumnIndex("nick_name")));
        hiUserInfo.setHeadImgUrl(cursor.getString(cursor.getColumnIndex("head_url")));
        hiUserInfo.setRelateType(cursor.getInt(cursor.getColumnIndex("relate_type")));
        if (!cursor.isNull(cursor.getColumnIndex("height"))) {
            hiUserInfo.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
        }
        if (!cursor.isNull(cursor.getColumnIndex("weight"))) {
            hiUserInfo.setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
        }
        hiUserInfo.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        hiUserInfo.setMobile(cursor.getString(cursor.getColumnIndex(JsNetwork.NetworkType.MOBILE)));
        hiUserInfo.setUnitType(cursor.getInt(cursor.getColumnIndex("unit_category")));
        if (!cursor.isNull(cursor.getColumnIndex("sex"))) {
            hiUserInfo.setGender(cursor.getInt(cursor.getColumnIndex("sex")));
        }
        if (!cursor.isNull(cursor.getColumnIndex("birthday"))) {
            hiUserInfo.setBirthday(cursor.getInt(cursor.getColumnIndex("birthday")));
        }
        hiUserInfo.setAge(cursor.getInt(cursor.getColumnIndex("age")));
        hiUserInfo.setCreateTime(cursor.getLong(cursor.getColumnIndex("create_time")));
        return hiUserInfo;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "hihealth_user";
    }
}
