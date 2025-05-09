package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class iit {

    /* renamed from: a, reason: collision with root package name */
    private igw f13388a;

    private iit() {
        this.f13388a = igw.c();
    }

    /* loaded from: classes7.dex */
    static class a {
        private static final iit d = new iit();
    }

    public static iit d() {
        return a.d;
    }

    public static String[] a() {
        return new String[]{"_id", "start_time", "end_time", "type_id", "value", "metadata", DBPointCommon.COLUMN_UNIT_ID, "client_id", "merged", "sync_status", "timeZone", "modified_time"};
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(" create table  IF NOT EXISTS " + str + Constants.LEFT_BRACKET_ONLY);
        sb.append("_id integer primary key  not null,start_time integer not null,end_time integer not null,type_id integer not null,value double,metadata text,unit_id integer not null,client_id integer not null,merged integer not null,sync_status integer not null,timeZone text not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String d(String str, String str2) {
        StringBuilder sb = new StringBuilder(" CREATE INDEX  IF NOT EXISTS ");
        sb.append(str + " ON " + str2 + Constants.LEFT_BRACKET_ONLY);
        sb.append("start_time,end_time,type_id,client_id)");
        return sb.toString();
    }

    public List<HiHealthData> b(String str, HiDataReadOption hiDataReadOption, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAN_(this.f13388a.bxs_(str, stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> d(String str, HiDataReadOption hiDataReadOption, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged =? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(0);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAN_(this.f13388a.bxs_(str, stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> a(String str, long j, long j2, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[size + 3];
        stringBuffer.append("start_time =? and end_time =? and type_id =? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        return iih.bAz_(this.f13388a.bxs_(str, stringBuffer.toString(), strArr, null, null, null));
    }

    public int a(String str, HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", Double.valueOf(hiHealthData.getValue()));
        contentValues.put("metadata", hiHealthData.getMetaData());
        contentValues.put(DBPointCommon.COLUMN_UNIT_ID, Integer.valueOf(hiHealthData.getPointUnit()));
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put("merged", Integer.valueOf(i2));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        if (hiHealthData.getSyncStatus() == 1) {
            contentValues.remove("sync_status");
        }
        return this.f13388a.bxt_(str, contentValues, b(), a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i));
    }

    private boolean c(String str, long j, long j2, int i, int i2) {
        return iih.bAv_(this.f13388a.bxs_(str, b(), a(j, j2, i, i2), null, null, null));
    }

    public boolean b(String str, HiHealthData hiHealthData, int i, int i2) {
        long d;
        LogUtil.c("Debug_ConfigDataManager", "insertOrUpdatePointData enter!");
        if (c(str, hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i)) {
            d = a(str, hiHealthData, i, i2);
        } else {
            d = d(str, hiHealthData, i, i2);
        }
        return iil.a(d);
    }

    public long d(String str, HiHealthData hiHealthData, int i, int i2) {
        return this.f13388a.bxr_(str, bBi_(hiHealthData, i, i2));
    }

    private String[] a(long j, long j2, int i, int i2) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2)};
    }

    private ContentValues bBi_(HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("start_time", Long.valueOf(hiHealthData.getStartTime()));
        contentValues.put("end_time", Long.valueOf(hiHealthData.getEndTime()));
        contentValues.put("type_id", Integer.valueOf(hiHealthData.getType()));
        contentValues.put("value", Double.valueOf(hiHealthData.getValue()));
        contentValues.put("metadata", hiHealthData.getMetaData());
        contentValues.put(DBPointCommon.COLUMN_UNIT_ID, Integer.valueOf(hiHealthData.getPointUnit()));
        contentValues.put("client_id", Integer.valueOf(i));
        contentValues.put("merged", Integer.valueOf(i2));
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put("timeZone", HiDateUtil.d(hiHealthData.getTimeZone()));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public List<HiHealthData> a(String str, long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        String[] strArr = new String[list.size() + length + 2];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAz_(this.f13388a.bxs_(str, stringBuffer.toString(), strArr, null, null, null));
    }

    public int b(String str, long j, long j2, int i, int i2) {
        return this.f13388a.a(str, b(), a(j, j2, i, i2));
    }

    public int d(String str, long j, int i, int i2) {
        return this.f13388a.bxt_(str, iid.bzK_(i, i2), "_id =? ", new String[]{Long.toString(j)});
    }

    public List<HiHealthData> a(String str, List<Integer> list, HiAggregateOption hiAggregateOption) {
        String[] strArr = new String[list.size() + 3];
        strArr[0] = Long.toString(hiAggregateOption.getStartTime());
        strArr[1] = Long.toString(hiAggregateOption.getEndTime());
        strArr[2] = Integer.toString(0);
        return iih.bAc_(this.f13388a.rawQuery(d(str, list, strArr, 3, hiAggregateOption), strArr), hiAggregateOption.getConstantsKey());
    }

    private String d(String str, List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption) {
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select ");
        stringBuffer.append(str).append(".start_time,");
        stringBuffer.append(str).append(".end_time,");
        stringBuffer.append(str).append(".type_id,");
        int[] type = hiAggregateOption.getType();
        for (int i2 = 0; i2 < type.length; i2++) {
            stringBuffer.append(d(str, hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < type.length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from ").append(str);
        stringBuffer.append(" where ").append(str).append(".start_time >=? and ").append(str).append(".start_time <=?  and ");
        stringBuffer.append(str).append(".merged =? ");
        iil.a(str + ".client_id", list, stringBuffer, strArr, i);
        iij.e(stringBuffer, str, "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    private String d(String str, int i, int i2, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i == 0) {
            iij.a(stringBuffer, str, "type_id", "value", i2, str2);
        } else if (i == 1) {
            iij.e(stringBuffer, str, "type_id", "value", i2, str2);
        } else if (i == 2) {
            iij.c(stringBuffer, str, "type_id", i2, str2);
        } else if (i == 3) {
            iij.b(stringBuffer, str, "type_id", "value", i2, str2);
        } else if (i == 4) {
            iij.d(stringBuffer, str, "type_id", "value", i2, str2);
        } else if (i == 5) {
            iij.c(stringBuffer, str, "type_id", "value", i2, str2);
        }
        return stringBuffer.toString();
    }

    private String b() {
        return "start_time =? and end_time =? and type_id =? and client_id =? ";
    }
}
