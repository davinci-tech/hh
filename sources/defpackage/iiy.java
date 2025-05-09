package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iiy {

    /* renamed from: a, reason: collision with root package name */
    private ihk f13391a;

    private iiy() {
        this.f13391a = ihk.d();
    }

    static class d {
        private static final iiy e = new iiy();
    }

    public static iiy e(Context context) {
        return d.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.util.List<com.huawei.hihealth.HiHealthData> r7, android.content.Context r8) {
        /*
            r6 = this;
            r0 = 1
            if (r7 == 0) goto L98
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto Lb
            goto L98
        Lb:
            r1 = 0
            java.lang.Object r2 = r7.get(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            com.huawei.hihealth.HiHealthData r2 = (com.huawei.hihealth.HiHealthData) r2     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            int r2 = r2.getType()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            boolean r2 = defpackage.ivu.i(r8, r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            if (r2 != 0) goto L2b
            java.lang.Object r2 = r7.get(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            com.huawei.hihealth.HiHealthData r2 = (com.huawei.hihealth.HiHealthData) r2     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            int r2 = r2.getType()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            boolean r2 = defpackage.ivu.e(r8, r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            goto L2c
        L2b:
            r2 = r1
        L2c:
            ija r3 = new ija     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r3.<init>()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r7.forEach(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            if (r2 == 0) goto L43
            java.lang.Object r3 = r7.get(r1)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            int r3 = r3.getType()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            defpackage.ivu.j(r8, r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
        L43:
            if (r2 == 0) goto L52
            java.lang.Object r7 = r7.get(r1)
            com.huawei.hihealth.HiHealthData r7 = (com.huawei.hihealth.HiHealthData) r7
            int r7 = r7.getType()
            defpackage.ivu.c(r8, r7)
        L52:
            return r0
        L53:
            r0 = move-exception
            goto L88
        L55:
            r3 = move-exception
            goto L5d
        L57:
            r0 = move-exception
            r2 = r1
            goto L88
        L5a:
            r2 = move-exception
            r3 = r2
            r2 = r1
        L5d:
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L53
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53
            java.lang.String r5 = "Error to insertBatchHiHealthData,cause:"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L53
            r4.append(r3)     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L53
            r0[r1] = r3     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = "Debug_DataPointManager"
            com.huawei.hihealth.util.ReleaseLogUtil.c(r3, r0)     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L87
            java.lang.Object r7 = r7.get(r1)
            com.huawei.hihealth.HiHealthData r7 = (com.huawei.hihealth.HiHealthData) r7
            int r7 = r7.getType()
            defpackage.ivu.c(r8, r7)
        L87:
            return r1
        L88:
            if (r2 == 0) goto L97
            java.lang.Object r7 = r7.get(r1)
            com.huawei.hihealth.HiHealthData r7 = (com.huawei.hihealth.HiHealthData) r7
            int r7 = r7.getType()
            defpackage.ivu.c(r8, r7)
        L97:
            throw r0
        L98:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iiy.a(java.util.List, android.content.Context):boolean");
    }

    /* synthetic */ void e(HiHealthData hiHealthData) {
        if (c(hiHealthData, hiHealthData.getClientId(), 0) <= 0) {
            ReleaseLogUtil.c("Debug_DataPointManager", "Error to insertPointData, hiHealthData=" + hiHealthData);
        }
    }

    public long c(HiHealthData hiHealthData, int i, int i2) {
        return this.f13391a.insert(iid.bzu_(hiHealthData, i, i2));
    }

    public int c(long j, long j2, int i, int i2) {
        return this.f13391a.delete(c(), b(j, j2, i, i2));
    }

    public int b(long j, long j2, int[] iArr, List<Integer> list) {
        int size = list.size();
        int length = iArr.length;
        int i = size + length + 2;
        String[] strArr = new String[i];
        StringBuffer stringBuffer = new StringBuffer((i * 2) + 32);
        stringBuffer.append("start_time >=? and end_time <=? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        LogUtil.c("Debug_DataPointManager", "deletePointDatas() sbSelector = ", stringBuffer, ",selectAgs = ", HiJsonUtil.e(strArr));
        return this.f13391a.delete(stringBuffer.toString(), strArr);
    }

    public List<HiHealthData> b(HiDataReadOption hiDataReadOption, int i, List<Integer> list, ifl iflVar) {
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
        LogUtil.c("Debug_DataPointManager", "queryPointDataListByClientIdsWithOrder() sbSelector = ", stringBuffer, ",sbSelector = ", HiJsonUtil.e(stringBuffer));
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_DataPointManager", "queryPointDataListByClientIdsWithOrder() order = ", a2);
        return iih.bAN_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, int i, List<Integer> list, ifl iflVar) {
        if (list == null) {
            LogUtil.h("Debug_DataPointManager", "queryMergePointDataListByClientIdsWithOrder() clientIds is null");
            return null;
        }
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        int i2 = hiDataReadOption.getModifiedEndTime() > 0 ? 2 : 0;
        int i3 = i2 + 4;
        String[] strArr = new String[size + i3];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged =? ");
        if (iflVar != null && !TextUtils.isEmpty(iflVar.c())) {
            stringBuffer.append(iil.b(iflVar.c()));
        }
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(0);
        if (i2 == 2) {
            stringBuffer.append(" and modified_time >=? and modified_time <=? ");
            strArr[4] = Long.toString(hiDataReadOption.getModifiedStartTime());
            strArr[5] = Long.toString(hiDataReadOption.getModifiedEndTime());
        }
        iil.a("client_id", list, stringBuffer, strArr, i3);
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_DataPointManager", "queryMergePointDataListByClientIdsWithOrder: sbSelector=" + ((Object) stringBuffer));
        return iih.bAN_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, a2), hiDataReadOption.getDeviceUuid());
    }

    public int c(long j, int i, int i2) {
        return this.f13391a.update(iid.bzK_(i2, i), "_id =? ", new String[]{Long.toString(j)});
    }

    public int c(long j, long j2, int i, int i2, int i3) {
        return this.f13391a.update(iid.bzv_(i3), c(), b(j, j2, i, i2));
    }

    public int c(long j, long j2, int i, List<Integer> list, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged!=?");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return this.f13391a.update(iid.bzv_(i2), stringBuffer.toString(), strArr);
    }

    public int a(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzJ_ = iid.bzJ_(hiHealthData, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzJ_.remove("sync_status");
        }
        return this.f13391a.update(bzJ_, c(), b(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i));
    }

    public Double d(long j, long j2, int i, int i2) {
        return iih.bAG_(this.f13391a.query(c(), b(j, j2, i, i2), null, null, null), "value");
    }

    private String[] b(long j, long j2, int i, int i2) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2)};
    }

    public int e(int i, long j, long j2, int i2, int i3) {
        String[] strArr = {Integer.toString(i), Long.toString(j), Long.toString(j2), Integer.toString(i2)};
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i3));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.f13391a.update(contentValues, "client_id =? and start_time =? and end_time =? and type_id =? ", strArr);
    }

    public int a(long j, long j2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.f13391a.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public List<HiHealthData> e(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAz_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<Integer> d(long j, long j2, int i) {
        String[] strArr = {Long.toString(j), Long.toString(j2), Integer.toString(i)};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? ");
        iil.b(stringBuffer, "client_id");
        return iih.bAr_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> d(long j, long j2, int[] iArr) {
        String[] strArr = new String[iArr.length + 2];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.b(stringBuffer, "client_id");
        return iih.bAr_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<HiHealthData> d(List<Integer> list, int i, int i2, int i3) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return iih.bAN_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", i, i2, i3)), null);
    }

    public List<HiHealthData> d(int i, long j, long j2, int i2) {
        return iih.bAN_(this.f13391a.query("start_time >=? and start_time <=? and type_id =? and client_id =? ", new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i2), Integer.toString(i)}, null, null, null), null);
    }

    public List<HiHealthData> e(int i, List<Integer> list, int i2) {
        int size = list.size() + 2;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("client_id =? and sync_status =? ");
        iil.a("type_id", list, stringBuffer, strArr, 2);
        return iih.bAD_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, "start_time DESC  limit 0 ," + i2));
    }

    public List<HiHealthData> c(int i, List<Integer> list, long j, long j2) {
        int size = list.size() + 4;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        strArr[2] = Long.toString(j);
        strArr[3] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("client_id =? and sync_status =? and start_time >=? and start_time <=? ");
        iil.a("type_id", list, stringBuffer, strArr, 4);
        return iih.bAD_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, "start_time DESC "));
    }

    public List<Integer> d(List<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[list.size() + 1];
        strArr[0] = Integer.toString(0);
        iil.a("type_id", list, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<HiHealthData> c(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time >=? and start_time <=? and sync_status != 2");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAz_(this.f13391a.query(stringBuffer.toString(), strArr, null, null, null));
    }

    private String c() {
        return "start_time =? and end_time =? and type_id =? and client_id =? ";
    }
}
