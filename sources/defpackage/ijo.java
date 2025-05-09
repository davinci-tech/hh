package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ijo {
    private ihl d;

    private ijo() {
        this.d = ihl.a();
    }

    static class e {
        private static final ijo b = new ijo();
    }

    public static ijo b(Context context) {
        return e.b;
    }

    public long b(HiHealthData hiHealthData, int i, int i2) {
        return this.d.insert(iid.bzp_(hiHealthData, i, i2));
    }

    public int b(long j, long j2, int i, int i2) {
        return this.d.delete(c(), e(j, j2, i, i2));
    }

    public List<HiHealthData> a(HiDataReadOption hiDataReadOption, int i, List<Integer> list, ifl iflVar) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        int i2 = hiDataReadOption.getModifiedEndTime() > 0 ? 2 : 0;
        int i3 = i2 + 4;
        String[] strArr = new String[size + i3];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged != ?");
        if (iflVar != null && !TextUtils.isEmpty(iflVar.c())) {
            stringBuffer.append(iil.b(iflVar.c()));
        }
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        if (i2 == 2) {
            stringBuffer.append(" and modified_time >=? and modified_time <=? ");
            strArr[4] = Long.toString(hiDataReadOption.getModifiedStartTime());
            strArr[5] = Long.toString(hiDataReadOption.getModifiedEndTime());
        }
        iil.a("client_id", list, stringBuffer, strArr, i3);
        LogUtil.c("Debug_HealthDataPointStressManager", "queryPointDataListByClientIdsWithOrder() sbSelector = ", stringBuffer, ",sbSelector = ", HiJsonUtil.e(stringBuffer));
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_HealthDataPointStressManager", "queryPointDataListByClientIdsWithOrder() order = ", a2);
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> b(List<Integer> list, long j, long j2, int i, int i2, String[] strArr, int[] iArr, int i3) {
        if (list == null) {
            LogUtil.h("Debug_HealthDataPointStressManager", "queryMergeAggregateHealthPointDataNoAlignType() clientIds is null");
            return null;
        }
        String[] strArr2 = new String[list.size() + 5];
        strArr2[0] = Long.toString(j);
        strArr2[1] = Long.toString(j2);
        strArr2[2] = Integer.toString(i2);
        strArr2[3] = Integer.toString(0);
        strArr2[4] = Integer.toString(2);
        String c = iil.c(list, strArr2, 5, i, i2, strArr, iArr, i3);
        LogUtil.c("Debug_HealthDataPointStressManager", "queryMergeAggregateHealthPointDataNoAlignType() aggregateSQL = ", c, ",selectAgs = ", HiJsonUtil.e(strArr2));
        return iih.bzY_(this.d.rawQuery(c, strArr2), strArr);
    }

    public List<HiHealthData> e(List<Integer> list, long j, long j2, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bzY_(this.d.query(stringBuffer.toString(), strArr, null, null, null), new String[]{"value"});
    }

    public int c(long j) {
        return this.d.delete("_id =? ", new String[]{Long.toString(j)});
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, int i, List<Integer> list, ifl iflVar) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        int i2 = hiDataReadOption.getModifiedEndTime() > 0 ? 2 : 0;
        int i3 = i2 + 5;
        String[] strArr = new String[size + i3];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged =? and sync_status !=? ");
        if (iflVar != null && !TextUtils.isEmpty(iflVar.c())) {
            stringBuffer.append(iil.b(iflVar.c()));
        }
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(0);
        strArr[4] = Integer.toString(2);
        if (i2 == 2) {
            stringBuffer.append(" and modified_time >=? and modified_time <=? ");
            strArr[5] = Long.toString(hiDataReadOption.getModifiedStartTime());
            strArr[6] = Long.toString(hiDataReadOption.getModifiedEndTime());
        }
        iil.a("client_id", list, stringBuffer, strArr, i3);
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_HealthDataPointStressManager", "queryMergePointDataListByClientIdsWithOrder:sbSelector= " + ((Object) stringBuffer));
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> b(long j, long j2, int i, int i2, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time =? and end_time =? and type_id >=? and type_id <=? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAz_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int d(long j, int i, int i2) {
        return this.d.update(iid.bzG_(i, i2), "_id =? ", new String[]{Long.toString(j)});
    }

    public int e(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzr_ = iid.bzr_(hiHealthData, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzr_.remove("sync_status");
        }
        return this.d.update(bzr_, c(), e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i));
    }

    private List<HiHealthData> d(long j, long j2, int i, int i2) {
        return iih.bAD_(this.d.query(c(), e(j, j2, i, i2), null, null, null));
    }

    private boolean e(long j, long j2, int i, int i2, int i3) {
        return iih.bAv_(this.d.query(b(), b(j, j2, i, i2, i3), null, null, null));
    }

    public boolean c(HiHealthData hiHealthData, int i, int i2) {
        long b;
        if (hiHealthData.getSyncStatus() == 2) {
            return true;
        }
        List<HiHealthData> d = d(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i);
        if (d == null || d.isEmpty()) {
            b = b(hiHealthData, i, i2);
        } else {
            HiHealthData hiHealthData2 = d.get(0);
            b = (hiHealthData.getIntValue() == hiHealthData2.getIntValue() && hiHealthData.getMetaData().equals(hiHealthData2.getMetaData())) ? 0L : e(hiHealthData, i, i2);
        }
        return iil.a(b);
    }

    public boolean e(HiHealthData hiHealthData, int i, int i2, int i3, int i4) {
        long b;
        if (e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i2, i3, i)) {
            b = e(hiHealthData, i, i4);
        } else {
            b = b(hiHealthData, i, i4);
        }
        return iil.a(b);
    }

    private String[] e(long j, long j2, int i, int i2) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2)};
    }

    private String[] b(long j, long j2, int i, int i2, int i3) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2), Integer.toString(i3)};
    }

    public List<HiHealthData> a(int i, List<Integer> list, int i2) {
        int size = list.size() + 2;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("client_id =? and sync_status =? ");
        iil.a("type_id", list, stringBuffer, strArr, 2);
        return iih.bAD_(this.d.query(stringBuffer.toString(), strArr, null, null, "start_time DESC  limit 0 ," + i2));
    }

    public List<HiHealthData> e(int i, List<Integer> list, long j, long j2) {
        int size = list.size() + 4;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        strArr[2] = Long.toString(j);
        strArr[3] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        stringBuffer.append("client_id =? and sync_status =? and start_time >=? and start_time <=? ");
        iil.a("type_id", list, stringBuffer, strArr, 4);
        return iih.bAD_(this.d.query(stringBuffer.toString(), strArr, null, null, "start_time DESC "));
    }

    public int d(long j, long j2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.d.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public List<HiHealthData> a(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAz_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> a(int i, int i2, List<Integer> list, int i3) {
        int size = list.size() + 2;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(i2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("type_id =? and sync_status =? ");
        iil.a("client_id", list, stringBuffer, strArr, 2);
        iij.a(stringBuffer, 0, i3);
        return iih.bAz_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> d(List<Integer> list, int i, int i2, int i3) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", i, i2, i3)), null);
    }

    public int e(long j, long j2, int i, List<Integer> list, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged!=?");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return this.d.update(iid.bzz_(i2), stringBuffer.toString(), strArr);
    }

    public int c(long j, long j2, int i, int i2, int i3) {
        return this.d.update(iid.bzz_(i3), c(), e(j, j2, i, i2));
    }

    public List<Integer> c(List<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[list.size() + 1];
        strArr[0] = Integer.toString(0);
        iil.a("type_id", list, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.d.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> b(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[iArr.length + 1];
        strArr[0] = Integer.toString(2);
        iil.a("type_id", iArr, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "type_id");
        return iih.bAJ_(this.d.query(stringBuffer.toString(), strArr, null, null, null), "type_id");
    }

    public List<Integer> e(long j, long j2, int[] iArr) {
        String[] strArr = new String[iArr.length + 2];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.b(stringBuffer, "client_id");
        return iih.bAr_(this.d.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    private String b() {
        return "start_time =? and end_time =? and type_id >=? and type_id <=? and client_id =? ";
    }

    private String c() {
        return "start_time =? and end_time =? and type_id =? and client_id =? ";
    }
}
