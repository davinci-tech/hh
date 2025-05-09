package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ijc {
    private ihn e;

    private ijc() {
        this.e = ihn.a();
    }

    static class e {
        private static final ijc c = new ijc();
    }

    public static ijc d(Context context) {
        return e.c;
    }

    public long b(HiHealthData hiHealthData, int i, int i2) {
        return this.e.insert(iid.bzx_(hiHealthData, i, i2));
    }

    public int b(long j, long j2, int i) {
        return this.e.delete(b(), c(j, j2, i));
    }

    public int c(long j, long j2, int i, int i2, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and end_time <=? and type_id >=? and type_id <=? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Long.toString(i);
        strArr[3] = Long.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        LogUtil.c("Debug_DataSessionManager", "deleteSessionDatas() sbSelector = ", stringBuffer, ",selectAgs = ", HiJsonUtil.e(strArr));
        return this.e.delete(stringBuffer.toString(), strArr);
    }

    public List<HiHealthData> c(long j, long j2, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        String[] strArr = new String[size + 3];
        stringBuffer.append("start_time =? and end_time =? and sync_status !=? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        stringBuffer.append(" ORDER BY ").append("start_time").append(" ASC ");
        return iih.bAT_(this.e.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> c(HiDataReadOption hiDataReadOption, List<Integer> list, int i, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 5];
        stringBuffer.append("start_time =? and end_time =? and type_id >=? and type_id <=? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        strArr[4] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 5);
        return iih.bAT_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> a(HiDataReadOption hiDataReadOption, List<Integer> list, int i, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 5];
        stringBuffer.append("start_time >=? and start_time <=? and type_id >=? and type_id <=? and merged =? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        strArr[4] = Integer.toString(0);
        iil.a("client_id", list, stringBuffer, strArr, 5);
        return iih.bAT_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> e(HiDataReadOption hiDataReadOption, List<Integer> list, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=?  and type_id =? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAT_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> b(HiDataReadOption hiDataReadOption, List<Integer> list, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=?  and type_id =? and merged =? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(0);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAT_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    private int e(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzP_ = iid.bzP_(hiHealthData, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzP_.remove("sync_status");
        }
        return this.e.update(bzP_, b(), c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i));
    }

    public int d(long j, long j2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.e.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public int c(HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i2));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.e.update(contentValues, b(), c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i));
    }

    private boolean a(long j, long j2, int i) {
        return iih.bAv_(this.e.query(b(), c(j, j2, i), null, null, null));
    }

    public boolean a(HiHealthData hiHealthData, int i, int i2) {
        long b;
        if (a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i)) {
            b = e(hiHealthData, i, i2);
        } else {
            b = b(hiHealthData, i, i2);
        }
        return iil.a(b);
    }

    private String[] c(long j, long j2, int i) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i)};
    }

    public List<HiHealthData> d(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAT_(this.e.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int e(long j, int i, int i2) {
        return this.e.update(iid.bzQ_(i, i2), "_id =? ", new String[]{Long.toString(j)});
    }

    public List<Integer> a(long j, long j2) {
        String[] strArr = {Long.toString(j), Long.toString(j2)};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.e.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<HiHealthData> a(List<Integer> list, int i, int i2, int i3) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return iih.bAT_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", i, i2, i3)));
    }

    private String b() {
        return "start_time =? and end_time =? and client_id =? ";
    }
}
