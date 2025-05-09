package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iiv {
    private iib e;

    private iiv() {
        this.e = iib.b();
    }

    static class e {
        private static final iiv e = new iiv();
    }

    public static iiv d() {
        return e.e;
    }

    public List<HiHealthData> b(HiDataReadOption hiDataReadOption, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer(128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and type =? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        LogUtil.c("HiH_DataBusinessManager", "queryBusinessDataListByClientIdsWithOrder() selector = ", stringBuffer, ",selector = ", HiJsonUtil.e(stringBuffer));
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("HiH_DataBusinessManager", "queryBusinessDataListByClientIdsWithOrder() order = ", a2);
        return iih.bAm_(this.e.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer(128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and type =? and merged =? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(0);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAm_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> d(long j, long j2, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer(128);
        String[] strArr = new String[size + 3];
        stringBuffer.append("start_time =? and end_time =? and type =? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        return iih.bAy_(this.e.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> e(int i, List<Integer> list, int i2, int i3) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer(128);
        String[] strArr = new String[size + 2];
        stringBuffer.append("client_id =? and sync_status =? ");
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(i2);
        iil.a("type", list, stringBuffer, strArr, 2);
        StringBuffer stringBuffer2 = new StringBuffer("start_time DESC  limit 0,");
        stringBuffer2.append(i3);
        return iih.bAy_(this.e.query(stringBuffer.toString(), strArr, null, null, stringBuffer2.toString()));
    }

    public boolean e(long j, long j2) {
        return iih.bAv_(this.e.query("_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)}, null, null, null));
    }

    public long b(HiHealthData hiHealthData, int i, int i2) {
        return this.e.insert(iid.bzk_(hiHealthData, i, i2));
    }

    public long d(HiHealthData hiHealthData, int i, int i2) {
        LogUtil.c("HiH_DataBusinessManager", "insertOrUpdatePointData enter!");
        long e2 = e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i);
        if (e2 > 0) {
            if (a(hiHealthData, i, i2) > 0) {
                return e2;
            }
            return -1L;
        }
        return b(hiHealthData, i, i2);
    }

    public int a(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzD_ = iid.bzD_(hiHealthData, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzD_.remove("sync_status");
        }
        return this.e.update(bzD_, b(), c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i));
    }

    public int a(long j, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", (Integer) 1);
        return this.e.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public List<HiHealthData> b(long j) {
        return iih.bAy_(this.e.bza_("_id =? ", new String[]{Long.toString(j)}, null, null, null));
    }

    private long e(long j, long j2, int i, int i2) {
        return iih.bAl_(this.e.query(b(), c(j, j2, i, i2), null, null, null));
    }

    private String[] c(long j, long j2, int i, int i2) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2)};
    }

    private String b() {
        return "start_time =? and end_time =? and type =? and client_id =? ";
    }
}
