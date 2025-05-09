package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class ijj {
    private ihm d;

    private ijj() {
        this.d = ihm.b();
    }

    protected ijj(ihm ihmVar) {
        this.d = ihmVar;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final ijj f13396a = new ijj();
    }

    public static ijj a(Context context) {
        return e.f13396a;
    }

    public int a(long j, long j2, int i, int i2) {
        return this.d.delete(c(), c(j, j2, i, i2));
    }

    public long e(HiHealthData hiHealthData, int i, int i2) {
        return this.d.insert(iid.bzp_(hiHealthData, i, i2));
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, int i, List<Integer> list, ifl iflVar) {
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
        LogUtil.c("Debug_HealthDataPointManager", "queryPointDataListByClientIdsWithOrder() sbSelector = ", stringBuffer, ",sbSelector = ", HiJsonUtil.e(stringBuffer));
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_HealthDataPointManager", "queryPointDataListByClientIdsWithOrder() order = ", a2);
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> a(HiDataReadOption hiDataReadOption, int[] iArr, List<Integer> list) {
        int size = list.size();
        int i = hiDataReadOption.getModifiedEndTime() > 0 ? 2 : 0;
        int length = iArr.length + size + 3 + i;
        String[] strArr = new String[length];
        StringBuffer stringBuffer = new StringBuffer((length * 2) + 64);
        stringBuffer.append("start_time >=? and start_time <=? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(2);
        int d = iij.d(strArr, 3, hiDataReadOption);
        if (i == 2) {
            stringBuffer.append(" and modified_time >=? and modified_time <=? ");
        }
        iil.a("client_id", list, stringBuffer, strArr, d);
        iil.a("type_id", iArr, stringBuffer, strArr, size + d);
        LogUtil.c("Debug_HealthDataPointManager", "queryPointDataListByClientIdsWithOrder() sql = ", stringBuffer, ",selectAgs = ", HiJsonUtil.e(strArr));
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_HealthDataPointManager", "queryPointDataListByClientIdsWithOrder() order = ", a2);
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public int d(long j) {
        return this.d.delete("_id =? ", new String[]{Long.toString(j)});
    }

    public List<HiHealthData> b(HiDataReadOption hiDataReadOption, int i, List<Integer> list, ifl iflVar) {
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
        LogUtil.c("Debug_HealthDataPointManager", "queryMergePointDataListByClientIdsWithOrder:sbSelector= " + ((Object) stringBuffer));
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> c(HiDataReadOption hiDataReadOption, int[] iArr, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        int i = hiDataReadOption.getModifiedEndTime() > 0 ? 2 : 0;
        String[] strArr = new String[iArr.length + size + 3 + i];
        stringBuffer.append("start_time >=? and start_time <=? and merged =? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(0);
        int d = iij.d(strArr, 3, hiDataReadOption);
        if (i == 2) {
            stringBuffer.append(" and modified_time >=? and modified_time <=? ");
        }
        iil.a("client_id", list, stringBuffer, strArr, d);
        iil.a("type_id", iArr, stringBuffer, strArr, size + d);
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_HealthDataPointManager", "queryMergePointDatasWithOrder() sql = ", stringBuffer, ",selectAgs = ", HiJsonUtil.e(strArr));
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> c(HiDataReadOption hiDataReadOption, int[] iArr, List<Integer> list, String str) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[iArr.length + size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and (metadata is NULL  or metadata =?  or metadata= '").append(str).append(Constants.RIGHT_BRACKET);
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(0);
        strArr[3] = "0";
        iil.a("client_id", list, stringBuffer, strArr, 4);
        iil.a("type_id", iArr, stringBuffer, strArr, size + 4);
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> a(long j, long j2, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time =? and end_time =? and type_id =? and sync_status !=? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAz_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> c(long j, long j2, int i, List<Integer> list) {
        if (HiCommonUtil.d(list)) {
            ReleaseLogUtil.d("Debug_HealthDataPointManager", "clientIds is null or empty!");
            return Collections.emptyList();
        }
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        String[] strArr = new String[size + 3];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        return iih.bAz_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int d(long j, int i, int i2) {
        return this.d.update(iid.bzG_(i, i2), "_id =? ", new String[]{Long.toString(j)});
    }

    public int c(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzr_ = iid.bzr_(hiHealthData, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzr_.remove("sync_status");
        }
        if (hiHealthData.getInt("merged") == 2) {
            bzr_.remove("merged");
        }
        return this.d.update(bzr_, c(), c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i));
    }

    public int d(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzr_ = iid.bzr_(hiHealthData, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzr_.remove("sync_status");
        }
        return this.d.update(bzr_, d(), c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i, 2));
    }

    public int b(HiHealthData hiHealthData, int i) {
        ContentValues bzr_ = iid.bzr_(hiHealthData, i);
        if (hiHealthData.getSyncStatus() == 1) {
            bzr_.remove("sync_status");
        }
        if (hiHealthData.getInt("merged") == 2) {
            bzr_.remove("merged");
        }
        return this.d.update(bzr_, "_id =? ", new String[]{Long.toString(hiHealthData.getDataId())});
    }

    private boolean e(long j, long j2, int i, int i2) {
        return iih.bAv_(this.d.query(d(), c(j, j2, i, i2, 2), null, null, null));
    }

    public boolean a(final HiHealthData hiHealthData, int i, int i2) {
        long j;
        LogUtil.c("Debug_HealthDataPointManager", "insertOrUpdatePointData enter!");
        if (e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i)) {
            j = d(hiHealthData, i, i2);
        } else {
            long e2 = e(hiHealthData, i, i2);
            if (i2 != 1) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: ijh
                    @Override // java.lang.Runnable
                    public final void run() {
                        irs.c().process(HiHealthData.this);
                    }
                });
            }
            j = e2;
        }
        return iil.a(j);
    }

    private String[] c(long j, long j2, int i, int i2) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2)};
    }

    private String[] c(long j, long j2, int i, int i2, int i3) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2), Integer.toString(i3)};
    }

    public List<HiHealthData> c(int i, List<Integer> list, int i2) {
        int size = list.size() + 2;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("client_id =? and sync_status =? ");
        iil.a("type_id", list, stringBuffer, strArr, 2);
        return iih.bAD_(this.d.query(stringBuffer.toString(), strArr, null, null, "start_time DESC  limit 0 ," + i2));
    }

    public List<Integer> b(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status =? ");
        String[] strArr = {Long.toString(i)};
        iil.b(stringBuffer, str);
        return iih.bAJ_(this.d.queryEX(new String[]{str}, stringBuffer.toString(), strArr, null, null, null), str);
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

    public List<HiHealthData> d(int i, List<Integer> list, long j, long j2) {
        int size = list.size() + 4;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(2);
        strArr[2] = Long.toString(j);
        strArr[3] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        stringBuffer.append("client_id =? and sync_status !=? and start_time =? and end_time =? ");
        iil.a("type_id", list, stringBuffer, strArr, 4);
        return iih.bAD_(this.d.query(stringBuffer.toString(), strArr, null, null, "start_time DESC "));
    }

    public int e(long j, long j2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        return this.d.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public List<HiHealthData> e(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 3;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time >=? and start_time <=? and sync_status !=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 3);
        iil.a("client_id", list, stringBuffer, strArr, length + 3);
        return iih.bAz_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int c(List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return this.d.delete(stringBuffer.toString(), strArr);
    }

    public List<Integer> a(long j, long j2, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and sync_status !=? ");
        String[] strArr = {Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(2)};
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.d.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> b(long j, long j2, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? and sync_status !=? ");
        String[] strArr = new String[iArr.length + 3];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(2);
        iil.a("type_id", iArr, stringBuffer, strArr, 3);
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.d.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<HiHealthData> b(int[] iArr, int i, List<Integer> list, int i2) {
        int size = list.size();
        int length = iArr.length + size + 1;
        String[] strArr = new String[length];
        strArr[0] = Integer.toString(i);
        StringBuffer stringBuffer = new StringBuffer((length * 2) + 32);
        stringBuffer.append("sync_status =? ");
        iil.a("client_id", list, stringBuffer, strArr, 1);
        iil.a("type_id", iArr, stringBuffer, strArr, size + 1);
        iij.a(stringBuffer, 0, i2);
        LogUtil.c("Debug_HealthDataPointManager", "queryPointDataBySyncStatus() selector = ", stringBuffer, ",selectAgs = ", HiJsonUtil.e(strArr));
        return iih.bAz_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> b(List<Integer> list, int i, int i2, int i3) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", i, i2, i3)), null);
    }

    public List<HiHealthData> c(List<Integer> list, long j, long j2, int i) {
        if (HiCommonUtil.d(list)) {
            ReleaseLogUtil.d("Debug_HealthDataPointManager", "clientIDs is null or empty!");
            return Collections.emptyList();
        }
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAN_(this.d.query(stringBuffer.toString(), strArr, null, null, null), null);
    }

    public List<Integer> b(List<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[list.size() + 1];
        strArr[0] = Integer.toString(0);
        iil.a("type_id", list, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.d.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> c(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[iArr.length + 1];
        strArr[0] = Integer.toString(2);
        iil.a("type_id", iArr, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "type_id");
        return iih.bAJ_(this.d.query(stringBuffer.toString(), strArr, null, null, null), "type_id");
    }

    private String d() {
        return "start_time =? and end_time =? and type_id =? and client_id =? and sync_status !=? ";
    }

    private String c() {
        return "start_time =? and end_time =? and type_id =? and client_id =? ";
    }
}
